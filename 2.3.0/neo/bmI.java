package neo;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class bmI {
   private String host;
   private int port;
   private Proxy proxy;
   private List<bmK> listRequests;
   private List<bmK> listRequestsSend;
   private Socket socket;
   private InputStream inputStream;
   private OutputStream outputStream;
   private bmL httpPipelineSender;
   private bmJ httpPipelineReceiver;
   private int countRequests;
   private boolean responseReceived;
   private long keepaliveTimeoutMs;
   private int keepaliveMaxCount;
   private long timeLastActivityMs;
   private boolean terminated;
   private static final String LF = "\n";
   public static final int TIMEOUT_CONNECT_MS = 5000;
   public static final int TIMEOUT_READ_MS = 5000;
   private static final Pattern patternFullUrl = Pattern.compile("^[a-zA-Z]+://.*");

   public bmI(String host, int port) {
      this(host, port, Proxy.NO_PROXY);
   }

   public bmI(String host, int port, Proxy proxy) {
      this.host = null;
      this.port = 0;
      this.proxy = Proxy.NO_PROXY;
      this.listRequests = new LinkedList();
      this.listRequestsSend = new LinkedList();
      this.socket = null;
      this.inputStream = null;
      this.outputStream = null;
      this.httpPipelineSender = null;
      this.httpPipelineReceiver = null;
      this.countRequests = 0;
      this.responseReceived = false;
      this.keepaliveTimeoutMs = 5000L;
      this.keepaliveMaxCount = 1000;
      this.timeLastActivityMs = System.currentTimeMillis();
      this.terminated = false;
      this.host = host;
      this.port = port;
      this.proxy = proxy;
      this.httpPipelineSender = new bmL(this);
      this.httpPipelineSender.start();
      this.httpPipelineReceiver = new bmJ(this);
      this.httpPipelineReceiver.start();
   }

   public synchronized boolean addRequest(bmK pr) {
      if (this.isClosed()) {
         return false;
      } else {
         this.addRequest(pr, this.listRequests);
         this.addRequest(pr, this.listRequestsSend);
         ++this.countRequests;
         return true;
      }
   }

   private void addRequest(bmK pr, List<bmK> list) {
      list.add(pr);
      this.notifyAll();
   }

   public synchronized void setSocket(Socket s) throws IOException {
      if (!this.terminated) {
         if (this.socket != null) {
            throw new IllegalArgumentException("Already connected");
         }

         this.socket = s;
         this.socket.setTcpNoDelay(true);
         this.inputStream = this.socket.getInputStream();
         this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
         this.onActivity();
         this.notifyAll();
      }

   }

   public synchronized OutputStream getOutputStream() throws IOException, InterruptedException {
      while(this.outputStream == null) {
         this.checkTimeout();
         this.wait(1000L);
      }

      return this.outputStream;
   }

   public synchronized InputStream getInputStream() throws IOException, InterruptedException {
      while(this.inputStream == null) {
         this.checkTimeout();
         this.wait(1000L);
      }

      return this.inputStream;
   }

   public synchronized bmK getNextRequestSend() throws InterruptedException, IOException {
      if (this.listRequestsSend.size() <= 0 && this.outputStream != null) {
         this.outputStream.flush();
      }

      return this.getNextRequest(this.listRequestsSend, true);
   }

   public synchronized bmK getNextRequestReceive() throws InterruptedException {
      return this.getNextRequest(this.listRequests, false);
   }

   private bmK getNextRequest(List<bmK> list, boolean remove) throws InterruptedException {
      while(list.size() <= 0) {
         this.checkTimeout();
         this.wait(1000L);
      }

      this.onActivity();
      if (remove) {
         return (bmK)list.remove(0);
      } else {
         return (bmK)list.get(0);
      }
   }

   private void checkTimeout() {
      if (this.socket != null) {
         long i = this.keepaliveTimeoutMs;
         if (this.listRequests.size() > 0) {
            i = 5000L;
         }

         long j = System.currentTimeMillis();
         if (j > this.timeLastActivityMs + i) {
            this.terminate(new InterruptedException("Timeout " + i));
         }
      }

   }

   private void onActivity() {
      this.timeLastActivityMs = System.currentTimeMillis();
   }

   public synchronized void onRequestSent(bmK pr) {
      if (!this.terminated) {
         this.onActivity();
      }

   }

   public synchronized void onResponseReceived(bmK pr, bmN resp) {
      if (!this.terminated) {
         this.responseReceived = true;
         this.onActivity();
         if (this.listRequests.size() <= 0 || this.listRequests.get(0) != pr) {
            throw new IllegalArgumentException("Response out of order: " + pr);
         }

         this.listRequests.remove(0);
         pr.setClosed(true);
         String s = resp.getHeader("Location");
         if (resp.getStatus() / 100 == 3 && s != null && pr.getHttpRequest().getRedirects() < 5) {
            try {
               s = this.normalizeUrl(s, pr.getHttpRequest());
               bmM httprequest = bmH.makeRequest(s, pr.getHttpRequest().getProxy());
               httprequest.setRedirects(pr.getHttpRequest().getRedirects() + 1);
               bmK httppipelinerequest = new bmK(httprequest, pr.getHttpListener());
               bmH.addRequest(httppipelinerequest);
            } catch (IOException var6) {
               IOException ioexception = var6;
               pr.getHttpListener().failed(pr.getHttpRequest(), ioexception);
            }
         } else {
            bmF httplistener = pr.getHttpListener();
            httplistener.finished(pr.getHttpRequest(), resp);
         }

         this.checkResponseHeader(resp);
      }

   }

   private String normalizeUrl(String url, bmM hr) {
      if (patternFullUrl.matcher(url).matches()) {
         return url;
      } else if (url.startsWith("//")) {
         return "http:" + url;
      } else {
         String s = hr.getHost();
         if (hr.getPort() != 80) {
            s = s + ":" + hr.getPort();
         }

         if (url.startsWith("/")) {
            return "http://" + s + url;
         } else {
            String s1 = hr.getFile();
            int i = s1.lastIndexOf("/");
            return i >= 0 ? "http://" + s + s1.substring(0, i + 1) + url : "http://" + s + "/" + url;
         }
      }
   }

   private void checkResponseHeader(bmN resp) {
      String s = resp.getHeader("Connection");
      if (s != null && !s.toLowerCase().equals("keep-alive")) {
         this.terminate(new EOFException("Connection not keep-alive"));
      }

      String s1 = resp.getHeader("Keep-Alive");
      if (s1 != null) {
         String[] astring = XH.tokenize(s1, ",;");

         for(int i = 0; i < astring.length; ++i) {
            String s2 = astring[i];
            String[] astring1 = this.split(s2, '=');
            if (astring1.length >= 2) {
               int k;
               if (astring1[0].equals("timeout")) {
                  k = XH.parseInt(astring1[1], -1);
                  if (k > 0) {
                     this.keepaliveTimeoutMs = (long)(k * 1000);
                  }
               }

               if (astring1[0].equals("max")) {
                  k = XH.parseInt(astring1[1], -1);
                  if (k > 0) {
                     this.keepaliveMaxCount = k;
                  }
               }
            }
         }
      }

   }

   private String[] split(String str, char separator) {
      int i = str.indexOf(separator);
      if (i < 0) {
         return new String[]{str};
      } else {
         String s = str.substring(0, i);
         String s1 = str.substring(i + 1);
         return new String[]{s, s1};
      }
   }

   public synchronized void onExceptionSend(bmK pr, Exception e) {
      this.terminate(e);
   }

   public synchronized void onExceptionReceive(bmK pr, Exception e) {
      this.terminate(e);
   }

   private synchronized void terminate(Exception e) {
      if (!this.terminated) {
         this.terminated = true;
         this.terminateRequests(e);
         if (this.httpPipelineSender != null) {
            this.httpPipelineSender.interrupt();
         }

         if (this.httpPipelineReceiver != null) {
            this.httpPipelineReceiver.interrupt();
         }

         try {
            if (this.socket != null) {
               this.socket.close();
            }
         } catch (IOException var3) {
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
      }

   }

   private void terminateRequests(Exception e) {
      if (this.listRequests.size() > 0) {
         bmK httppipelinerequest1;
         if (!this.responseReceived) {
            httppipelinerequest1 = (bmK)this.listRequests.remove(0);
            httppipelinerequest1.getHttpListener().failed(httppipelinerequest1.getHttpRequest(), e);
            httppipelinerequest1.setClosed(true);
         }

         while(this.listRequests.size() > 0) {
            httppipelinerequest1 = (bmK)this.listRequests.remove(0);
            bmH.addRequest(httppipelinerequest1);
         }
      }

   }

   public synchronized boolean isClosed() {
      if (this.terminated) {
         return true;
      } else {
         return this.countRequests >= this.keepaliveMaxCount;
      }
   }

   public int getCountRequests() {
      return this.countRequests;
   }

   public synchronized boolean hasActiveRequests() {
      return this.listRequests.size() > 0;
   }

   public String getHost() {
      return this.host;
   }

   public int getPort() {
      return this.port;
   }

   public Proxy getProxy() {
      return this.proxy;
   }
}
