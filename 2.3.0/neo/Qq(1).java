package neo;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import org.apache.commons.lang3.StringUtils;

public class Qq extends OL {
   private static final String[] SKULL_TYPES = new String[]{"skeleton", "wither", "zombie", "char", "creeper", "dragon"};

   public Qq() {
      this.setCreativeTab(EN.DECORATIONS);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (facing == EnumFacing.DOWN) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         boolean flag = block.isReplaceable(worldIn, pos);
         if (!flag) {
            if (!worldIn.getBlockState(pos).getMaterial().isSolid()) {
               return EnumActionResult.FAIL;
            }

            pos = pos.offset(facing);
         }

         Qy itemstack = player.getHeldItem(hand);
         if (player.canPlayerEdit(pos, facing, itemstack) && Nk.SKULL.canPlaceBlockAt(worldIn, pos)) {
            if (worldIn.isRemote) {
               return EnumActionResult.SUCCESS;
            } else {
               worldIn.setBlockState(pos, Nk.SKULL.getDefaultState().withProperty(gE.FACING, facing), 11);
               int i = 0;
               if (facing == EnumFacing.UP) {
                  i = MathHelper.floor((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5) & 15;
               }

               Yg tileentity = worldIn.getTileEntity(pos);
               if (tileentity instanceof YR) {
                  YR tileentityskull = (YR)tileentity;
                  if (itemstack.getMetadata() == 3) {
                     GameProfile gameprofile = null;
                     if (itemstack.hasTagCompound()) {
                        QQ nbttagcompound = itemstack.getTagCompound();
                        if (nbttagcompound.hasKey("SkullOwner", 10)) {
                           gameprofile = Rb.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                        } else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
                           gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                        }
                     }

                     tileentityskull.setPlayerProfile(gameprofile);
                  } else {
                     tileentityskull.setType(itemstack.getMetadata());
                  }

                  tileentityskull.setSkullRotation(i);
                  Nk.SKULL.checkWitherSpawn(worldIn, pos, tileentityskull);
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
      }
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         for(int i = 0; i < SKULL_TYPES.length; ++i) {
            items.add(new Qy(this, 1, i));
         }
      }

   }

   public int getMetadata(int damage) {
      return damage;
   }

   public String getTranslationKey(Qy stack) {
      int i = stack.getMetadata();
      if (i < 0 || i >= SKULL_TYPES.length) {
         i = 0;
      }

      return super.getTranslationKey() + "." + SKULL_TYPES[i];
   }

   public String getItemStackDisplayName(Qy stack) {
      if (stack.getMetadata() == 3 && stack.hasTagCompound()) {
         if (stack.getTagCompound().hasKey("SkullOwner", 8)) {
            return I18n.translateToLocalFormatted("item.skull.player.name", stack.getTagCompound().getString("SkullOwner"));
         }

         if (stack.getTagCompound().hasKey("SkullOwner", 10)) {
            QQ nbttagcompound = stack.getTagCompound().getCompoundTag("SkullOwner");
            if (nbttagcompound.hasKey("Name", 8)) {
               return I18n.translateToLocalFormatted("item.skull.player.name", nbttagcompound.getString("Name"));
            }
         }
      }

      return super.getItemStackDisplayName(stack);
   }

   public boolean updateItemStackNBT(QQ nbt) {
      super.updateItemStackNBT(nbt);
      if (nbt.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbt.getString("SkullOwner"))) {
         GameProfile gameprofile = new GameProfile((UUID)null, nbt.getString("SkullOwner"));
         gameprofile = YR.updateGameProfile(gameprofile);
         nbt.setTag("SkullOwner", Rb.writeGameProfile(new QQ(), gameprofile));
         return true;
      } else {
         return false;
      }
   }
}
