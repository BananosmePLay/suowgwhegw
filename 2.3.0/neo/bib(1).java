package neo;

import net.minecraft.util.StringUtils;
import net.minecraft.util.text.translation.I18n;

public class bib implements Comparable<bib> {
   private final String fileName;
   private final String displayName;
   private final long lastTimePlayed;
   private final long sizeOnDisk;
   private final boolean requiresConversion;
   private final bbb gameType;
   private final boolean hardcore;
   private final boolean cheatsEnabled;
   private final String versionName;
   private final int versionId;
   private final boolean versionSnapshot;

   public bib(bhY info, String fileNameIn, String displayNameIn, long sizeOnDiskIn, boolean requiresConversionIn) {
      this.fileName = fileNameIn;
      this.displayName = displayNameIn;
      this.lastTimePlayed = info.getLastTimePlayed();
      this.sizeOnDisk = sizeOnDiskIn;
      this.gameType = info.getGameType();
      this.requiresConversion = requiresConversionIn;
      this.hardcore = info.isHardcoreModeEnabled();
      this.cheatsEnabled = info.areCommandsAllowed();
      this.versionName = info.getVersionName();
      this.versionId = info.getVersionId();
      this.versionSnapshot = info.isVersionSnapshot();
   }

   public String getFileName() {
      return this.fileName;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public boolean requiresConversion() {
      return this.requiresConversion;
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public int compareTo(bib p_compareTo_1_) {
      if (this.lastTimePlayed < p_compareTo_1_.lastTimePlayed) {
         return 1;
      } else {
         return this.lastTimePlayed > p_compareTo_1_.lastTimePlayed ? -1 : this.fileName.compareTo(p_compareTo_1_.fileName);
      }
   }

   public bbb getEnumGameType() {
      return this.gameType;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public boolean getCheatsEnabled() {
      return this.cheatsEnabled;
   }

   public String getVersionName() {
      return StringUtils.isNullOrEmpty(this.versionName) ? I18n.translateToLocal("selectWorld.versionUnknown") : this.versionName;
   }

   public boolean markVersionInList() {
      return this.askToOpenWorld();
   }

   public boolean askToOpenWorld() {
      return this.versionId > 1343;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((bib)var1);
   }
}
