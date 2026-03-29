package neo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhL implements bgm, bgk {
   private static final Logger LOGGER = LogManager.getLogger();
   private final File worldDirectory;
   private final File playersDirectory;
   private final File mapDataDir;
   private final long initializationTime = Xx.getCurrentTimeMillis();
   private final String saveDirectoryName;
   private final bfL structureTemplateManager;
   protected final DataFixer dataFixer;

   public bhL(File p_i46648_1_, String saveDirectoryNameIn, boolean p_i46648_3_, DataFixer dataFixerIn) {
      this.dataFixer = dataFixerIn;
      this.worldDirectory = new File(p_i46648_1_, saveDirectoryNameIn);
      this.worldDirectory.mkdirs();
      this.playersDirectory = new File(this.worldDirectory, "playerdata");
      this.mapDataDir = new File(this.worldDirectory, "data");
      this.mapDataDir.mkdirs();
      this.saveDirectoryName = saveDirectoryNameIn;
      if (p_i46648_3_) {
         this.playersDirectory.mkdirs();
         this.structureTemplateManager = new bfL((new File(this.worldDirectory, "structures")).toString(), dataFixerIn);
      } else {
         this.structureTemplateManager = null;
      }

      this.setSessionLock();
   }

   private void setSessionLock() {
      try {
         File file1 = new File(this.worldDirectory, "session.lock");
         DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));

         try {
            dataoutputstream.writeLong(this.initializationTime);
         } finally {
            dataoutputstream.close();
         }

      } catch (IOException var7) {
         IOException ioexception = var7;
         ioexception.printStackTrace();
         throw new RuntimeException("Failed to check session lock, aborting");
      }
   }

   public File getWorldDirectory() {
      return this.worldDirectory;
   }

   public void checkSessionLock() throws bgf {
      try {
         File file1 = new File(this.worldDirectory, "session.lock");
         DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));

         try {
            if (datainputstream.readLong() != this.initializationTime) {
               throw new bgf("The save is being accessed from another location, aborting");
            }
         } finally {
            datainputstream.close();
         }

      } catch (IOException var7) {
         throw new bgf("Failed to check session lock, aborting");
      }
   }

   public baC getChunkLoader(bil provider) {
      throw new RuntimeException("Old Chunk Storage is no longer supported.");
   }

   @Nullable
   public bhY loadWorldInfo() {
      File file1 = new File(this.worldDirectory, "level.dat");
      if (file1.exists()) {
         bhY worldinfo = bhK.getWorldData(file1, this.dataFixer);
         if (worldinfo != null) {
            return worldinfo;
         }
      }

      file1 = new File(this.worldDirectory, "level.dat_old");
      return file1.exists() ? bhK.getWorldData(file1, this.dataFixer) : null;
   }

   public void saveWorldInfoWithPlayer(bhY worldInformation, @Nullable QQ tagCompound) {
      QQ nbttagcompound = worldInformation.cloneNBTCompound(tagCompound);
      QQ nbttagcompound1 = new QQ();
      nbttagcompound1.setTag("Data", nbttagcompound);

      try {
         File file1 = new File(this.worldDirectory, "level.dat_new");
         File file2 = new File(this.worldDirectory, "level.dat_old");
         File file3 = new File(this.worldDirectory, "level.dat");
         QF.writeCompressed(nbttagcompound1, new FileOutputStream(file1));
         if (file2.exists()) {
            file2.delete();
         }

         file3.renameTo(file2);
         if (file3.exists()) {
            file3.delete();
         }

         file1.renameTo(file3);
         if (file1.exists()) {
            file1.delete();
         }
      } catch (Exception var8) {
         Exception exception = var8;
         exception.printStackTrace();
      }

   }

   public void saveWorldInfo(bhY worldInformation) {
      this.saveWorldInfoWithPlayer(worldInformation, (QQ)null);
   }

   public void writePlayerData(ME player) {
      try {
         QQ nbttagcompound = player.writeToNBT(new QQ());
         File file1 = new File(this.playersDirectory, player.getCachedUniqueIdString() + ".dat.tmp");
         File file2 = new File(this.playersDirectory, player.getCachedUniqueIdString() + ".dat");
         QF.writeCompressed(nbttagcompound, new FileOutputStream(file1));
         if (file2.exists()) {
            file2.delete();
         }

         file1.renameTo(file2);
      } catch (Exception var5) {
         LOGGER.warn("Failed to save player data for {}", player.getName());
      }

   }

   @Nullable
   public QQ readPlayerData(ME player) {
      QQ nbttagcompound = null;

      try {
         File file1 = new File(this.playersDirectory, player.getCachedUniqueIdString() + ".dat");
         if (file1.exists() && file1.isFile()) {
            nbttagcompound = QF.readCompressed(new FileInputStream(file1));
         }
      } catch (Exception var4) {
         LOGGER.warn("Failed to load player data for {}", player.getName());
      }

      if (nbttagcompound != null) {
         player.readFromNBT(this.dataFixer.process(FixTypes.PLAYER, nbttagcompound));
      }

      return nbttagcompound;
   }

   public bgk getPlayerNBTManager() {
      return this;
   }

   public String[] getAvailablePlayerDat() {
      String[] astring = this.playersDirectory.list();
      if (astring == null) {
         astring = new String[0];
      }

      for(int i = 0; i < astring.length; ++i) {
         if (astring[i].endsWith(".dat")) {
            astring[i] = astring[i].substring(0, astring[i].length() - 4);
         }
      }

      return astring;
   }

   public void flush() {
   }

   public File getMapFileFromName(String mapName) {
      return new File(this.mapDataDir, mapName + ".dat");
   }

   public bfL getStructureTemplateManager() {
      return this.structureTemplateManager;
   }
}
