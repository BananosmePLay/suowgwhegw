package neo;

import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.text.translation.I18n;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bax extends bhK {
   private static final Logger LOGGER = LogManager.getLogger();

   public bax(File dir, DataFixer dataFixerIn) {
      super(dir, dataFixerIn);
   }

   public String getName() {
      return "Anvil";
   }

   public List<bib> getSaveList() throws iy {
      if (this.savesDirectory != null && this.savesDirectory.exists() && this.savesDirectory.isDirectory()) {
         List<bib> list = Lists.newArrayList();
         File[] afile = this.savesDirectory.listFiles();
         File[] var3 = afile;
         int var4 = afile.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file1 = var3[var5];
            if (file1.isDirectory()) {
               String s = file1.getName();
               bhY worldinfo = this.getWorldInfo(s);
               if (worldinfo != null && (worldinfo.getSaveVersion() == 19132 || worldinfo.getSaveVersion() == 19133)) {
                  boolean flag = worldinfo.getSaveVersion() != this.getSaveVersion();
                  String s1 = worldinfo.getWorldName();
                  if (StringUtils.isEmpty(s1)) {
                     s1 = s;
                  }

                  long i = 0L;
                  list.add(new bib(worldinfo, s, s1, 0L, flag));
               }
            }
         }

         return list;
      } else {
         throw new iy(I18n.translateToLocal("selectWorld.load_folder_access"));
      }
   }

   protected int getSaveVersion() {
      return 19133;
   }

   public void flushCache() {
      baH.clearRegionFileReferences();
   }

   public bgm getSaveLoader(String saveName, boolean storePlayerdata) {
      return new bay(this.savesDirectory, saveName, storePlayerdata, this.dataFixer);
   }

   public boolean isConvertible(String saveName) {
      bhY worldinfo = this.getWorldInfo(saveName);
      return worldinfo != null && worldinfo.getSaveVersion() == 19132;
   }

   public boolean isOldMapFormat(String saveName) {
      bhY worldinfo = this.getWorldInfo(saveName);
      return worldinfo != null && worldinfo.getSaveVersion() != this.getSaveVersion();
   }

   public boolean convertMapFormat(String filename, IProgressUpdate progressCallback) {
      progressCallback.setLoadingProgress(0);
      List<File> list = Lists.newArrayList();
      List<File> list1 = Lists.newArrayList();
      List<File> list2 = Lists.newArrayList();
      File file1 = new File(this.savesDirectory, filename);
      File file2 = new File(file1, "DIM-1");
      File file3 = new File(file1, "DIM1");
      LOGGER.info("Scanning folders...");
      this.addRegionFilesToCollection(file1, list);
      if (file2.exists()) {
         this.addRegionFilesToCollection(file2, list1);
      }

      if (file3.exists()) {
         this.addRegionFilesToCollection(file3, list2);
      }

      int i = list.size() + list1.size() + list2.size();
      LOGGER.info("Total conversion count is {}", i);
      bhY worldinfo = this.getWorldInfo(filename);
      Object biomeprovider;
      if (worldinfo != null && worldinfo.getTerrainType() == bix.FLAT) {
         biomeprovider = new ZM(Nj.PLAINS);
      } else {
         biomeprovider = new ZL(worldinfo);
      }

      this.convertFile(new File(file1, "region"), list, (ZL)biomeprovider, 0, i, progressCallback);
      this.convertFile(new File(file2, "region"), list1, new ZM(Nj.HELL), list.size(), i, progressCallback);
      this.convertFile(new File(file3, "region"), list2, new ZM(Nj.SKY), list.size() + list1.size(), i, progressCallback);
      worldinfo.setSaveVersion(19133);
      if (worldinfo.getTerrainType() == bix.DEFAULT_1_1) {
         worldinfo.setTerrainType(bix.DEFAULT);
      }

      this.createFile(filename);
      bgm isavehandler = this.getSaveLoader(filename, false);
      isavehandler.saveWorldInfo(worldinfo);
      return true;
   }

   private void createFile(String filename) {
      File file1 = new File(this.savesDirectory, filename);
      if (!file1.exists()) {
         LOGGER.warn("Unable to create level.dat_mcr backup");
      } else {
         File file2 = new File(file1, "level.dat");
         if (!file2.exists()) {
            LOGGER.warn("Unable to create level.dat_mcr backup");
         } else {
            File file3 = new File(file1, "level.dat_mcr");
            if (!file2.renameTo(file3)) {
               LOGGER.warn("Unable to create level.dat_mcr backup");
            }
         }
      }

   }

   private void convertFile(File baseFolder, Iterable<File> regionFiles, ZL p_75813_3_, int p_75813_4_, int p_75813_5_, IProgressUpdate progress) {
      Iterator var7 = regionFiles.iterator();

      while(var7.hasNext()) {
         File file1 = (File)var7.next();
         this.convertChunks(baseFolder, file1, p_75813_3_, p_75813_4_, p_75813_5_, progress);
         ++p_75813_4_;
         int i = (int)Math.round(100.0 * (double)p_75813_4_ / (double)p_75813_5_);
         progress.setLoadingProgress(i);
      }

   }

   private void convertChunks(File baseFolder, File p_75811_2_, ZL biomeSource, int p_75811_4_, int p_75811_5_, IProgressUpdate progressCallback) {
      try {
         String s = p_75811_2_.getName();
         baG regionfile = new baG(p_75811_2_);
         baG regionfile1 = new baG(new File(baseFolder, s.substring(0, s.length() - ".mcr".length()) + ".mca"));

         for(int i = 0; i < 32; ++i) {
            int j;
            for(j = 0; j < 32; ++j) {
               if (regionfile.isChunkSaved(i, j) && !regionfile1.isChunkSaved(i, j)) {
                  DataInputStream datainputstream = regionfile.getChunkDataInputStream(i, j);
                  if (datainputstream == null) {
                     LOGGER.warn("Failed to fetch input stream");
                  } else {
                     QQ nbttagcompound = QF.read(datainputstream);
                     datainputstream.close();
                     QQ nbttagcompound1 = nbttagcompound.getCompoundTag("Level");
                     baz chunkloader$anvilconverterdata = baA.load(nbttagcompound1);
                     QQ nbttagcompound2 = new QQ();
                     QQ nbttagcompound3 = new QQ();
                     nbttagcompound2.setTag("Level", nbttagcompound3);
                     baA.convertToAnvilFormat(chunkloader$anvilconverterdata, nbttagcompound3, biomeSource);
                     DataOutputStream dataoutputstream = regionfile1.getChunkDataOutputStream(i, j);
                     QF.write(nbttagcompound2, (DataOutput)dataoutputstream);
                     dataoutputstream.close();
                  }
               }
            }

            j = (int)Math.round(100.0 * (double)(p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));
            int l = (int)Math.round(100.0 * (double)((i + 1) * 32 + p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));
            if (l > j) {
               progressCallback.setLoadingProgress(l);
            }
         }

         regionfile.close();
         regionfile1.close();
      } catch (IOException var19) {
         IOException ioexception = var19;
         ioexception.printStackTrace();
      }

   }

   private void addRegionFilesToCollection(File worldDir, Collection<File> collection) {
      File file1 = new File(worldDir, "region");
      File[] afile = file1.listFiles(new FilenameFilter() {
         public boolean accept(File p_accept_1_, String p_accept_2_) {
            return p_accept_2_.endsWith(".mcr");
         }
      });
      if (afile != null) {
         Collections.addAll(collection, afile);
      }

   }
}
