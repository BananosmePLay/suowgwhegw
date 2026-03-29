package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Jq extends Io {
   public Jp art;

   public Jq(bij worldIn) {
      super(worldIn);
   }

   public Jq(bij worldIn, BlockPos pos, EnumFacing facing) {
      super(worldIn, pos);
      List<Jp> list = Lists.newArrayList();
      int i = 0;
      Jp[] var6 = Jp.values();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Jp entitypainting$enumart = var6[var8];
         this.art = entitypainting$enumart;
         this.updateFacingWithBoundingBox(facing);
         if (this.onValidSurface()) {
            list.add(entitypainting$enumart);
            int j = entitypainting$enumart.sizeX * entitypainting$enumart.sizeY;
            if (j > i) {
               i = j;
            }
         }
      }

      if (!list.isEmpty()) {
         Iterator<Jp> iterator = list.iterator();

         while(iterator.hasNext()) {
            Jp entitypainting$enumart1 = (Jp)iterator.next();
            if (entitypainting$enumart1.sizeX * entitypainting$enumart1.sizeY < i) {
               iterator.remove();
            }
         }

         this.art = (Jp)list.get(this.rand.nextInt(list.size()));
      }

      this.updateFacingWithBoundingBox(facing);
   }

   public Jq(bij worldIn, BlockPos pos, EnumFacing facing, String title) {
      this(worldIn, pos, facing);
      Jp[] var5 = Jp.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Jp entitypainting$enumart = var5[var7];
         if (entitypainting$enumart.title.equals(title)) {
            this.art = entitypainting$enumart;
            break;
         }
      }

      this.updateFacingWithBoundingBox(facing);
   }

   public void writeEntityToNBT(QQ compound) {
      compound.setString("Motive", this.art.title);
      super.writeEntityToNBT(compound);
   }

   public void readEntityFromNBT(QQ compound) {
      String s = compound.getString("Motive");
      Jp[] var3 = Jp.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Jp entitypainting$enumart = var3[var5];
         if (entitypainting$enumart.title.equals(s)) {
            this.art = entitypainting$enumart;
         }
      }

      if (this.art == null) {
         this.art = Jp.KEBAB;
      }

      super.readEntityFromNBT(compound);
   }

   public int getWidthPixels() {
      return this.art.sizeX;
   }

   public int getHeightPixels() {
      return this.art.sizeY;
   }

   public void onBroken(@Nullable Ig brokenEntity) {
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         this.playSound(NO.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
         if (brokenEntity instanceof ME) {
            ME entityplayer = (ME)brokenEntity;
            if (entityplayer.capabilities.isCreativeMode) {
               return;
            }
         }

         this.entityDropItem(new Qy(NK.PAINTING), 0.0F);
      }

   }

   public void playPlaceSound() {
      this.playSound(NO.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
   }

   public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
      this.setPosition(x, y, z);
   }

   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
      BlockPos blockpos = this.hangingPosition.add(x - this.posX, y - this.posY, z - this.posZ);
      this.setPosition((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
   }
}
