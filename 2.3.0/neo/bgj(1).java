package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class bgj extends bhY {
   private final bhY delegate;

   public bgj(bhY worldInfoIn) {
      this.delegate = worldInfoIn;
   }

   public QQ cloneNBTCompound(@Nullable QQ nbt) {
      return this.delegate.cloneNBTCompound(nbt);
   }

   public long getSeed() {
      return this.delegate.getSeed();
   }

   public int getSpawnX() {
      return this.delegate.getSpawnX();
   }

   public int getSpawnY() {
      return this.delegate.getSpawnY();
   }

   public int getSpawnZ() {
      return this.delegate.getSpawnZ();
   }

   public long getWorldTotalTime() {
      return this.delegate.getWorldTotalTime();
   }

   public long getWorldTime() {
      return this.delegate.getWorldTime();
   }

   public long getSizeOnDisk() {
      return this.delegate.getSizeOnDisk();
   }

   public QQ getPlayerNBTTagCompound() {
      return this.delegate.getPlayerNBTTagCompound();
   }

   public String getWorldName() {
      return this.delegate.getWorldName();
   }

   public int getSaveVersion() {
      return this.delegate.getSaveVersion();
   }

   public long getLastTimePlayed() {
      return this.delegate.getLastTimePlayed();
   }

   public boolean isThundering() {
      return this.delegate.isThundering();
   }

   public int getThunderTime() {
      return this.delegate.getThunderTime();
   }

   public boolean isRaining() {
      return this.delegate.isRaining();
   }

   public int getRainTime() {
      return this.delegate.getRainTime();
   }

   public bbb getGameType() {
      return this.delegate.getGameType();
   }

   public void setSpawnX(int x) {
   }

   public void setSpawnY(int y) {
   }

   public void setSpawnZ(int z) {
   }

   public void setWorldTotalTime(long time) {
   }

   public void setWorldTime(long time) {
   }

   public void setSpawn(BlockPos spawnPoint) {
   }

   public void setWorldName(String worldName) {
   }

   public void setSaveVersion(int version) {
   }

   public void setThundering(boolean thunderingIn) {
   }

   public void setThunderTime(int time) {
   }

   public void setRaining(boolean isRaining) {
   }

   public void setRainTime(int time) {
   }

   public boolean isMapFeaturesEnabled() {
      return this.delegate.isMapFeaturesEnabled();
   }

   public boolean isHardcoreModeEnabled() {
      return this.delegate.isHardcoreModeEnabled();
   }

   public bix getTerrainType() {
      return this.delegate.getTerrainType();
   }

   public void setTerrainType(bix type) {
   }

   public boolean areCommandsAllowed() {
      return this.delegate.areCommandsAllowed();
   }

   public void setAllowCommands(boolean allow) {
   }

   public boolean isInitialized() {
      return this.delegate.isInitialized();
   }

   public void setServerInitialized(boolean initializedIn) {
   }

   public bba getGameRulesInstance() {
      return this.delegate.getGameRulesInstance();
   }

   public baV getDifficulty() {
      return this.delegate.getDifficulty();
   }

   public void setDifficulty(baV newDifficulty) {
   }

   public boolean isDifficultyLocked() {
      return this.delegate.isDifficultyLocked();
   }

   public void setDifficultyLocked(boolean locked) {
   }

   public void setDimensionData(baM dimensionIn, QQ compound) {
      this.delegate.setDimensionData(dimensionIn, compound);
   }

   public QQ getDimensionData(baM dimensionIn) {
      return this.delegate.getDimensionData(dimensionIn);
   }
}
