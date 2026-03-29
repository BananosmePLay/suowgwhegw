package neo;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class baH {
   private static final Map<File, baG> REGIONS_BY_FILE = Maps.newHashMap();

   public baH() {
   }

   public static synchronized baG createOrLoadRegionFile(File worldDir, int chunkX, int chunkZ) {
      File file1 = new File(worldDir, "region");
      File file2 = new File(file1, "r." + (chunkX >> 5) + "." + (chunkZ >> 5) + ".mca");
      baG regionfile = (baG)REGIONS_BY_FILE.get(file2);
      if (regionfile != null) {
         return regionfile;
      } else {
         if (!file1.exists()) {
            file1.mkdirs();
         }

         if (REGIONS_BY_FILE.size() >= 256) {
            clearRegionFileReferences();
         }

         baG regionfile1 = new baG(file2);
         REGIONS_BY_FILE.put(file2, regionfile1);
         return regionfile1;
      }
   }

   public static synchronized baG getRegionFileIfExists(File worldDir, int chunkX, int chunkZ) {
      File file1 = new File(worldDir, "region");
      File file2 = new File(file1, "r." + (chunkX >> 5) + "." + (chunkZ >> 5) + ".mca");
      baG regionfile = (baG)REGIONS_BY_FILE.get(file2);
      if (regionfile != null) {
         return regionfile;
      } else if (file1.exists() && file2.exists()) {
         if (REGIONS_BY_FILE.size() >= 256) {
            clearRegionFileReferences();
         }

         baG regionfile1 = new baG(file2);
         REGIONS_BY_FILE.put(file2, regionfile1);
         return regionfile1;
      } else {
         return null;
      }
   }

   public static synchronized void clearRegionFileReferences() {
      Iterator var0 = REGIONS_BY_FILE.values().iterator();

      while(var0.hasNext()) {
         baG regionfile = (baG)var0.next();

         try {
            if (regionfile != null) {
               regionfile.close();
            }
         } catch (IOException var3) {
            IOException ioexception = var3;
            ioexception.printStackTrace();
         }
      }

      REGIONS_BY_FILE.clear();
   }

   public static DataInputStream getChunkInputStream(File worldDir, int chunkX, int chunkZ) {
      baG regionfile = createOrLoadRegionFile(worldDir, chunkX, chunkZ);
      return regionfile.getChunkDataInputStream(chunkX & 31, chunkZ & 31);
   }

   public static DataOutputStream getChunkOutputStream(File worldDir, int chunkX, int chunkZ) {
      baG regionfile = createOrLoadRegionFile(worldDir, chunkX, chunkZ);
      return regionfile.getChunkDataOutputStream(chunkX & 31, chunkZ & 31);
   }

   public static boolean chunkExists(File worldDir, int chunkX, int chunkZ) {
      baG regionfile = getRegionFileIfExists(worldDir, chunkX, chunkZ);
      return regionfile != null ? regionfile.isChunkSaved(chunkX & 31, chunkZ & 31) : false;
   }
}
