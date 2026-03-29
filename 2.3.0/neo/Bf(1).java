package neo;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bf {
   private static final Logger LOGGER = LogManager.getLogger();
   protected nC minecraft;
   private final File dataFile;
   private final Bk[] hotbarSnapshots = new Bk[9];

   public Bf(nC minecraftIn, File dataDir) {
      this.minecraft = minecraftIn;
      this.dataFile = new File(dataDir, "hotbar.nbt");

      for(int i = 0; i < 9; ++i) {
         this.hotbarSnapshots[i] = new Bk();
      }

      this.read();
   }

   public void read() {
      try {
         QQ nbttagcompound = QF.read(this.dataFile);
         if (nbttagcompound == null) {
            return;
         }

         for(int i = 0; i < 9; ++i) {
            this.hotbarSnapshots[i].fromTag(nbttagcompound.getTagList(String.valueOf(i), 10));
         }
      } catch (Exception var3) {
         Exception exception = var3;
         LOGGER.error("Failed to load creative mode options", exception);
      }

   }

   public void write() {
      try {
         QQ nbttagcompound = new QQ();

         for(int i = 0; i < 9; ++i) {
            nbttagcompound.setTag(String.valueOf(i), this.hotbarSnapshots[i].createTag());
         }

         QF.write(nbttagcompound, this.dataFile);
      } catch (Exception var3) {
         Exception exception = var3;
         LOGGER.error("Failed to save creative mode options", exception);
      }

   }

   public Bk getHotbarSnapshot(int index) {
      return this.hotbarSnapshots[index];
   }
}
