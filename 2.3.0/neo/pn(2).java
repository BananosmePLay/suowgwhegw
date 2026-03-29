package neo;

import com.google.common.collect.Lists;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class pn {
   private final List<pq> listOfLanServers = Lists.newArrayList();
   boolean wasUpdated;

   public pn() {
   }

   public synchronized boolean getWasUpdated() {
      return this.wasUpdated;
   }

   public synchronized void setWasNotUpdated() {
      this.wasUpdated = false;
   }

   public synchronized List<pq> getLanServers() {
      return Collections.unmodifiableList(this.listOfLanServers);
   }

   public synchronized void addServer(String pingResponse, InetAddress ipAddress) {
      String s = ph.getMotdFromPingResponse(pingResponse);
      String s1 = ph.getAdFromPingResponse(pingResponse);
      if (s1 != null) {
         s1 = ipAddress.getHostAddress() + ":" + s1;
         boolean flag = false;
         Iterator var6 = this.listOfLanServers.iterator();

         while(var6.hasNext()) {
            pq lanserverinfo = (pq)var6.next();
            if (lanserverinfo.getServerIpPort().equals(s1)) {
               lanserverinfo.updateLastSeen();
               flag = true;
               break;
            }
         }

         if (!flag) {
            this.listOfLanServers.add(new pq(s, s1));
            this.wasUpdated = true;
         }
      }

   }
}
