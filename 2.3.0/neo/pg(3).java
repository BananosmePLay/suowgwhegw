package neo;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pg {
   private static final Logger LOGGER = LogManager.getLogger();
   private final nC mc;
   private final List<pf> servers = Lists.newArrayList();

   public pg(nC mcIn) {
      this.mc = mcIn;
      this.loadServerList();
   }

   public void loadServerList() {
      try {
         this.servers.clear();
         QQ nbttagcompound = QF.read(new File(this.mc.gameDir, "servers.dat"));
         if (nbttagcompound == null) {
            return;
         }

         QW nbttaglist = nbttagcompound.getTagList("servers", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            this.servers.add(pf.getServerDataFromNBTCompound(nbttaglist.getCompoundTagAt(i)));
         }
      } catch (Exception var4) {
         Exception exception = var4;
         LOGGER.error("Couldn't load server list", exception);
      }

   }

   public void saveServerList() {
      try {
         QW nbttaglist = new QW();
         Iterator var2 = this.servers.iterator();

         while(var2.hasNext()) {
            pf serverdata = (pf)var2.next();
            nbttaglist.appendTag(serverdata.getNBTCompound());
         }

         QQ nbttagcompound = new QQ();
         nbttagcompound.setTag("servers", nbttaglist);
         QF.safeWrite(nbttagcompound, new File(this.mc.gameDir, "servers.dat"));
      } catch (Exception var4) {
         Exception exception = var4;
         LOGGER.error("Couldn't save server list", exception);
      }

   }

   public pf getServerData(int index) {
      return (pf)this.servers.get(index);
   }

   public void removeServerData(int index) {
      this.servers.remove(index);
   }

   public void addServerData(pf server) {
      this.servers.add(server);
   }

   public int countServers() {
      return this.servers.size();
   }

   public void swapServers(int pos1, int pos2) {
      pf serverdata = this.getServerData(pos1);
      this.servers.set(pos1, this.getServerData(pos2));
      this.servers.set(pos2, serverdata);
      this.saveServerList();
   }

   public void set(int index, pf server) {
      this.servers.set(index, server);
   }

   public static void saveSingleServer(pf server) {
      pg serverlist = new pg(nC.getMinecraft());
      serverlist.loadServerList();

      for(int i = 0; i < serverlist.countServers(); ++i) {
         pf serverdata = serverlist.getServerData(i);
         if (serverdata.serverName.equals(server.serverName) && serverdata.serverIP.equals(server.serverIP)) {
            serverlist.set(i, server);
            break;
         }
      }

      serverlist.saveServerList();
   }
}
