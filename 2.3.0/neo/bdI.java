package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bdI extends bdD {
   private String pieceName;
   private Rotation rotation;
   private boolean overwrite;

   public bdI() {
   }

   public bdI(bfL p_i47214_1_, String p_i47214_2_, BlockPos p_i47214_3_, Rotation p_i47214_4_, boolean overwriteIn) {
      super(0);
      this.pieceName = p_i47214_2_;
      this.templatePosition = p_i47214_3_;
      this.rotation = p_i47214_4_;
      this.overwrite = overwriteIn;
      this.loadTemplate(p_i47214_1_);
   }

   private void loadTemplate(bfL p_191085_1_) {
      bfK template = p_191085_1_.getTemplate((Xx)null, new ResourceLocation("endcity/" + this.pieceName));
      bfD placementsettings = (this.overwrite ? bdK.access$900() : bdK.access$1000()).copy().setRotation(this.rotation);
      this.setup(template, this.templatePosition, placementsettings);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setString("Template", this.pieceName);
      tagCompound.setString("Rot", this.rotation.name());
      tagCompound.setBoolean("OW", this.overwrite);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.pieceName = tagCompound.getString("Template");
      this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
      this.overwrite = tagCompound.getBoolean("OW");
      this.loadTemplate(p_143011_2_);
   }

   protected void handleDataMarker(String function, BlockPos pos, bij worldIn, Random rand, bdy sbb) {
      if (function.startsWith("Chest")) {
         BlockPos blockpos = pos.down();
         if (sbb.isVecInside(blockpos)) {
            Yg tileentity = worldIn.getTileEntity(blockpos);
            if (tileentity instanceof Yn) {
               ((Yn)tileentity).setLootTable(bhq.CHESTS_END_CITY_TREASURE, rand.nextLong());
            }
         }
      } else if (function.startsWith("Sentry")) {
         KD entityshulker = new KD(worldIn);
         entityshulker.setPosition((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
         entityshulker.setAttachmentPos(pos);
         worldIn.spawnEntity(entityshulker);
      } else if (function.startsWith("Elytra")) {
         IZ entityitemframe = new IZ(worldIn, pos, this.rotation.rotate(EnumFacing.SOUTH));
         entityitemframe.setDisplayedItem(new Qy(NK.ELYTRA));
         worldIn.spawnEntity(entityitemframe);
      }

   }
}
