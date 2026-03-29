package neo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class po extends Thread {
   private final pn localServerList;
   private final InetAddress broadcastAddress;
   private final MulticastSocket socket;

   public po(pn list) throws IOException {
      super("LanServerDetector #" + pp.access$000().incrementAndGet());
      this.localServerList = list;
      this.setDaemon(true);
      this.socket = new MulticastSocket(4445);
      this.broadcastAddress = InetAddress.getByName("224.0.2.60");
      this.socket.setSoTimeout(5000);
      this.socket.joinGroup(this.broadcastAddress);
   }

   public void run() {
      byte[] abyte = new byte[1024];

      while(!this.isInterrupted()) {
         DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length);

         try {
            this.socket.receive(datagrampacket);
         } catch (SocketTimeoutException var5) {
            continue;
         } catch (IOException var6) {
            IOException ioexception = var6;
            pp.access$100().error("Couldn't ping server", ioexception);
            break;
         }

         String s = new String(datagrampacket.getData(), datagrampacket.getOffset(), datagrampacket.getLength(), StandardCharsets.UTF_8);
         pp.access$100().debug("{}: {}", datagrampacket.getAddress(), s);
         this.localServerList.addServer(s, datagrampacket.getAddress());
      }

      try {
         this.socket.leaveGroup(this.broadcastAddress);
      } catch (IOException var4) {
      }

      this.socket.close();
   }
}
