package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;

public class OV extends OX {
   public OV() {
      super(Nk.STANDING_BANNER);
      this.maxStackSize = 16;
      this.setCreativeTab(EN.DECORATIONS);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      boolean flag = iblockstate.getBlock().isReplaceable(worldIn, pos);
      if (facing != EnumFacing.DOWN && (iblockstate.getMaterial().isSolid() || flag) && (!flag || facing == EnumFacing.UP)) {
         pos = pos.offset(facing);
         Qy itemstack = player.getHeldItem(hand);
         if (player.canPlayerEdit(pos, facing, itemstack) && Nk.STANDING_BANNER.canPlaceBlockAt(worldIn, pos)) {
            if (worldIn.isRemote) {
               return EnumActionResult.SUCCESS;
            } else {
               pos = flag ? pos.down() : pos;
               if (facing == EnumFacing.UP) {
                  int i = MathHelper.floor((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
                  worldIn.setBlockState(pos, Nk.STANDING_BANNER.getDefaultState().withProperty(gV.ROTATION, i), 3);
               } else {
                  worldIn.setBlockState(pos, Nk.WALL_BANNER.getDefaultState().withProperty(hB.FACING, facing), 3);
               }

               Yg tileentity = worldIn.getTileEntity(pos);
               if (tileentity instanceof Yh) {
                  ((Yh)tileentity).setItemValues(itemstack, false);
               }

               if (player instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)player, pos, itemstack);
               }

               itemstack.shrink(1);
               return EnumActionResult.SUCCESS;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public String getItemStackDisplayName(Qy stack) {
      String s = "item.banner.";
      Om enumdyecolor = getBaseColor(stack);
      s = s + enumdyecolor.getTranslationKey() + ".name";
      return I18n.translateToLocal(s);
   }

   public static void appendHoverTextFromTileEntityTag(Qy stack, List<String> p_185054_1_) {
      QQ nbttagcompound = stack.getSubCompound("BlockEntityTag");
      if (nbttagcompound != null && nbttagcompound.hasKey("Patterns")) {
         QW nbttaglist = nbttagcompound.getTagList("Patterns", 10);

         for(int i = 0; i < nbttaglist.tagCount() && i < 6; ++i) {
            QQ nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            Om enumdyecolor = Om.byDyeDamage(nbttagcompound1.getInteger("Color"));
            XW bannerpattern = XW.byHash(nbttagcompound1.getString("Pattern"));
            if (bannerpattern != null) {
               p_185054_1_.add(I18n.translateToLocal("item.banner." + bannerpattern.getFileName() + "." + enumdyecolor.getTranslationKey()));
            }
         }
      }

   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      appendHoverTextFromTileEntityTag(stack, tooltip);
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         Om[] var3 = Om.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Om enumdyecolor = var3[var5];
            items.add(makeBanner(enumdyecolor, (QW)null));
         }
      }

   }

   public static Qy makeBanner(Om color, @Nullable QW patterns) {
      Qy itemstack = new Qy(NK.BANNER, 1, color.getDyeDamage());
      if (patterns != null && !patterns.isEmpty()) {
         itemstack.getOrCreateSubCompound("BlockEntityTag").setTag("Patterns", patterns.copy());
      }

      return itemstack;
   }

   public EN getCreativeTab() {
      return EN.DECORATIONS;
   }

   public static Om getBaseColor(Qy stack) {
      return Om.byDyeDamage(stack.getMetadata() & 15);
   }
}
