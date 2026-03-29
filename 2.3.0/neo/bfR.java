package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bfR extends bdD {
   private String templateName;
   private Rotation rotation;
   private Mirror mirror;

   public bfR() {
   }

   public bfR(bfL p_i47355_1_, String p_i47355_2_, BlockPos p_i47355_3_, Rotation p_i47355_4_) {
      this(p_i47355_1_, p_i47355_2_, p_i47355_3_, p_i47355_4_, Mirror.NONE);
   }

   public bfR(bfL p_i47356_1_, String p_i47356_2_, BlockPos p_i47356_3_, Rotation p_i47356_4_, Mirror p_i47356_5_) {
      super(0);
      this.templateName = p_i47356_2_;
      this.templatePosition = p_i47356_3_;
      this.rotation = p_i47356_4_;
      this.mirror = p_i47356_5_;
      this.loadTemplate(p_i47356_1_);
   }

   private void loadTemplate(bfL p_191081_1_) {
      bfK template = p_191081_1_.getTemplate((Xx)null, new ResourceLocation("mansion/" + this.templateName));
      bfD placementsettings = (new bfD()).setIgnoreEntities(true).setRotation(this.rotation).setMirror(this.mirror);
      this.setup(template, this.templatePosition, placementsettings);
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setString("Template", this.templateName);
      tagCompound.setString("Rot", this.placeSettings.getRotation().name());
      tagCompound.setString("Mi", this.placeSettings.getMirror().name());
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.templateName = tagCompound.getString("Template");
      this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
      this.mirror = Mirror.valueOf(tagCompound.getString("Mi"));
      this.loadTemplate(p_143011_2_);
   }

   protected void handleDataMarker(String function, BlockPos pos, bij worldIn, Random rand, bdy sbb) {
      if (function.startsWith("Chest")) {
         Rotation rotation = this.placeSettings.getRotation();
         in iblockstate = Nk.CHEST.getDefaultState();
         if ("ChestWest".equals(function)) {
            iblockstate = iblockstate.withProperty(cT.FACING, rotation.rotate(EnumFacing.WEST));
         } else if ("ChestEast".equals(function)) {
            iblockstate = iblockstate.withProperty(cT.FACING, rotation.rotate(EnumFacing.EAST));
         } else if ("ChestSouth".equals(function)) {
            iblockstate = iblockstate.withProperty(cT.FACING, rotation.rotate(EnumFacing.SOUTH));
         } else if ("ChestNorth".equals(function)) {
            iblockstate = iblockstate.withProperty(cT.FACING, rotation.rotate(EnumFacing.NORTH));
         }

         this.generateChest(worldIn, sbb, rand, pos, bhq.CHESTS_WOODLAND_MANSION, iblockstate);
      } else if ("Mage".equals(function)) {
         JR entityevoker = new JR(worldIn);
         entityevoker.enablePersistence();
         entityevoker.moveToBlockPosAndAngles(pos, 0.0F, 0.0F);
         worldIn.spawnEntity(entityevoker);
         worldIn.setBlockState(pos, Nk.AIR.getDefaultState(), 2);
      } else if ("Warrior".equals(function)) {
         Lf entityvindicator = new Lf(worldIn);
         entityvindicator.enablePersistence();
         entityvindicator.moveToBlockPosAndAngles(pos, 0.0F, 0.0F);
         entityvindicator.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvindicator)), (ID)null);
         worldIn.spawnEntity(entityvindicator);
         worldIn.setBlockState(pos, Nk.AIR.getDefaultState(), 2);
      }

   }
}
