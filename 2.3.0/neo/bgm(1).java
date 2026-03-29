package neo;

import java.io.File;
import javax.annotation.Nullable;

public interface bgm {
   @Nullable
   bhY loadWorldInfo();

   void checkSessionLock() throws bgf;

   baC getChunkLoader(bil var1);

   void saveWorldInfoWithPlayer(bhY var1, QQ var2);

   void saveWorldInfo(bhY var1);

   bgk getPlayerNBTManager();

   void flush();

   File getWorldDirectory();

   File getMapFileFromName(String var1);

   bfL getStructureTemplateManager();
}
