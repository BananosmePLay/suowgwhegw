package neo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class bmH {
   private static Map mapConnections = new HashMap();
   public static final String HEADER_USER_AGENT = "User-Agent";
   public static final String HEADER_HOST = "Host";
   public static final String HEADER_ACCEPT = "Accept";
   public static final String HEADER_LOCATION = "Location";
   public static final String HEADER_KEEP_ALIVE = "Keep-Alive";
   public static final String HEADER_CONNECTION = "Connection";
   public static final String HEADER_VALUE_KEEP_ALIVE = "keep-alive";
   public static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
   public static final String HEADER_VALUE_CHUNKED = "chunked";

   public bmH() {
   }

   public static void addRequest(String urlStr, bmF listener) throws IOException {
      addRequest(urlStr, listener, Proxy.NO_PROXY);
   }

   public static void addRequest(String urlStr, bmF listener, Proxy proxy) throws IOException {
      bmM httprequest = makeRequest(urlStr, proxy);
      bmK httppipelinerequest = new bmK(httprequest, listener);
      addRequest(httppipelinerequest);
   }

   public static bmM makeRequest(String urlStr, Proxy proxy) throws IOException {
      URL url = new URL(urlStr);
      if (!url.getProtocol().equals("http")) {
         throw new IOException("Only protocol http is supported: " + url);
      } else {
         String s = url.getFile();
         String s1 = url.getHost();
         int i = url.getPort();
         if (i <= 0) {
            i = 80;
         }

         String s2 = "GET";
         String s3 = "HTTP/1.1";
         Map<String, String> map = new LinkedHashMap();
         map.put("User-Agent", "Java/" + System.getProperty("java.version"));
         map.put("Host", s1);
         map.put("Accept", "text/html, image/gif, image/png");
         map.put("Connection", "keep-alive");
         byte[] abyte = new byte[0];
         bmM httprequest = new bmM(s1, i, proxy, s2, s, s3, map, abyte);
         return httprequest;
      }
   }

   public static void addRequest(bmK pr) {
      bmM httprequest = pr.getHttpRequest();

      for(bmI httppipelineconnection = getConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy()); !httppipelineconnection.addRequest(pr); httppipelineconnection = getConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy())) {
         removeConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy(), httppipelineconnection);
      }

   }

   private static synchronized bmI getConnection(String host, int port, Proxy proxy) {
      String s = makeConnectionKey(host, port, proxy);
      bmI httppipelineconnection = (bmI)mapConnections.get(s);
      if (httppipelineconnection == null) {
         httppipelineconnection = new bmI(host, port, proxy);
         mapConnections.put(s, httppipelineconnection);
      }

      return httppipelineconnection;
   }

   private static synchronized void removeConnection(String host, int port, Proxy proxy, bmI hpc) {
      String s = makeConnectionKey(host, port, proxy);
      bmI httppipelineconnection = (bmI)mapConnections.get(s);
      if (httppipelineconnection == hpc) {
         mapConnections.remove(s);
      }

   }

   private static String makeConnectionKey(String host, int port, Proxy proxy) {
      String s = host + ":" + port + "-" + proxy;
      return s;
   }

   public static byte[] get(String urlStr) throws IOException {
      return get(urlStr, Proxy.NO_PROXY);
   }

   public static byte[] get(String urlStr, Proxy proxy) throws IOException {
      if (urlStr.startsWith("file:")) {
         URL url = new URL(urlStr);
         InputStream inputstream = url.openStream();
         byte[] abyte = XH.readAll(inputstream);
         return abyte;
      } else {
         bmM httprequest = makeRequest(urlStr, proxy);
         bmN httpresponse = executeRequest(httprequest);
         if (httpresponse.getStatus() / 100 != 2) {
            throw new IOException("HTTP response: " + httpresponse.getStatus());
         } else {
            return httpresponse.getBody();
         }
      }
   }

   public static bmN executeRequest(bmM req) throws IOException {
      final Map<String, Object> map = new HashMap();
      String s = "Response";
      String s1 = "Exception";
      bmF httplistener = new bmF() {
         public void finished(bmM req, bmN resp) {
            synchronized(map) {
               map.put("Response", resp);
               map.notifyAll();
            }
         }

         public void failed(bmM req, Exception e) {
            synchronized(map) {
               map.put("Exception", e);
               map.notifyAll();
            }
         }
      };
      synchronized(map) {
         bmK httppipelinerequest = new bmK(req, httplistener);
         addRequest(httppipelinerequest);

         try {
            map.wait();
         } catch (InterruptedException var10) {
            throw new InterruptedIOException("Interrupted");
         }

         Exception exception = (Exception)map.get("Exception");
         if (exception != null) {
            if (exception instanceof IOException) {
               throw (IOException)exception;
            } else if (exception instanceof RuntimeException) {
               throw (RuntimeException)exception;
            } else {
               throw new RuntimeException(exception.getMessage(), exception);
            }
         } else {
            bmN httpresponse = (bmN)map.get("Response");
            if (httpresponse == null) {
               throw new IOException("Response is null");
            } else {
               return httpresponse;
            }
         }
      }
   }

   public static boolean hasActiveRequests() {
      Iterator var0 = mapConnections.values().iterator();

      Object httppipelineconnection;
      do {
         if (!var0.hasNext()) {
            return false;
         }

         httppipelineconnection = var0.next();
      } while(!((bmI)httppipelineconnection).hasActiveRequests());

      return true;
   }
}
