package neo;

import java.io.File;
import javax.annotation.Nullable;
import net.minecraft.util.datafix.DataFixer;

public class bay extends bhL {
   public bay(File p_i46650_1_, String saveDirectoryName, boolean p_i46650_3_, DataFixer dataFixerIn) {
      super(p_i46650_1_, saveDirectoryName, p_i46650_3_, dataFixerIn);
   }

   public baC getChunkLoader(bil provider) {
      File file1 = this.getWorldDirectory();
      File file2;
      if (provider instanceof bio) {
         file2 = new File(file1, "DIM-1");
         file2.mkdirs();
         return new bav(file2, this.dataFixer);
      } else if (provider instanceof bim) {
         file2 = new File(file1, "DIM1");
         file2.mkdirs();
         return new bav(file2, this.dataFixer);
      } else {
         return new bav(file1, this.dataFixer);
      }
   }

   public void saveWorldInfoWithPlayer(bhY worldInformation, @Nullable QQ tagCompound) {
      worldInformation.setSaveVersion(19133);
      super.saveWorldInfoWithPlayer(worldInformation, tagCompound);
   }

   public void flush() {
      try {
         bhN.getThreadedIOInstance().waitForFinish();
      } catch (InterruptedException var2) {
         InterruptedException interruptedexception = var2;
         interruptedexception.printStackTrace();
      }

      baH.clearRegionFileReferences();
   }
}
