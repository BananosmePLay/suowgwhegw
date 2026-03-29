package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class bgi implements bgc {
   private final Xx server;
   private final bis world;

   public bgi(Xx mcServerIn, bis worldServerIn) {
      this.server = mcServerIn;
      this.world = worldServerIn;
   }

   public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
   }

   public void spawnParticle(int id, boolean ignoreRange, boolean minimiseParticleLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
   }

   public void onEntityAdded(Ig entityIn) {
      this.world.getEntityTracker().track(entityIn);
      if (entityIn instanceof MG) {
         this.world.provider.onPlayerAdded((MG)entityIn);
      }

   }

   public void onEntityRemoved(Ig entityIn) {
      this.world.getEntityTracker().untrack(entityIn);
      this.world.getScoreboard().removeEntity(entityIn);
      if (entityIn instanceof MG) {
         this.world.provider.onPlayerRemoved((MG)entityIn);
      }

   }

   public void playSoundToAllNearExcept(@Nullable ME player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
      this.server.getPlayerList().sendToAllNearExcept(player, x, y, z, volume > 1.0F ? (double)(16.0F * volume) : 16.0, this.world.provider.getDimensionType().getId(), new UL(soundIn, category, x, y, z, volume, pitch));
   }

   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
   }

   public void notifyBlockUpdate(bij worldIn, BlockPos pos, in oldState, in newState, int flags) {
      this.world.getPlayerChunkMap().markBlockForUpdate(pos);
   }

   public void notifyLightSet(BlockPos pos) {
   }

   public void playRecord(SoundEvent soundIn, BlockPos pos) {
   }

   public void playEvent(ME player, int type, BlockPos blockPosIn, int data) {
      this.server.getPlayerList().sendToAllNearExcept(player, (double)blockPosIn.getX(), (double)blockPosIn.getY(), (double)blockPosIn.getZ(), 64.0, this.world.provider.getDimensionType().getId(), new TR(type, blockPosIn, data, false));
   }

   public void broadcastSound(int soundID, BlockPos pos, int data) {
      this.server.getPlayerList().sendPacketToAllPlayers(new TR(soundID, pos, data, true));
   }

   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
      Iterator var4 = this.server.getPlayerList().getPlayers().iterator();

      while(var4.hasNext()) {
         MG entityplayermp = (MG)var4.next();
         if (entityplayermp != null && entityplayermp.world == this.world && entityplayermp.getEntityId() != breakerId) {
            double d0 = (double)pos.getX() - entityplayermp.posX;
            double d1 = (double)pos.getY() - entityplayermp.posY;
            double d2 = (double)pos.getZ() - entityplayermp.posZ;
            if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0) {
               entityplayermp.connection.sendPacket(new Tz(breakerId, pos, progress));
            }
         }
      }

   }
}
