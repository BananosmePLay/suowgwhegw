package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class QD extends OL {
   public QD() {
      this.setMaxStackSize(1);
   }

   public static boolean validBookTagContents(QQ nbt) {
      if (!QC.isNBTValid(nbt)) {
         return false;
      } else if (!nbt.hasKey("title", 8)) {
         return false;
      } else {
         String s = nbt.getString("title");
         return s != null && s.length() <= 32 ? nbt.hasKey("author", 8) : false;
      }
   }

   public static int getGeneration(Qy book) {
      return book.getTagCompound().getInteger("generation");
   }

   public String getItemStackDisplayName(Qy stack) {
      if (stack.hasTagCompound()) {
         QQ nbttagcompound = stack.getTagCompound();
         String s = nbttagcompound.getString("title");
         if (!StringUtils.isNullOrEmpty(s)) {
            return s;
         }
      }

      return super.getItemStackDisplayName(stack);
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      if (stack.hasTagCompound()) {
         QQ nbttagcompound = stack.getTagCompound();
         String s = nbttagcompound.getString("author");
         if (!StringUtils.isNullOrEmpty(s)) {
            tooltip.add(TextFormatting.GRAY + I18n.translateToLocalFormatted("book.byAuthor", s));
         }

         tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("book.generation." + nbttagcompound.getInteger("generation")));
      }

   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (!worldIn.isRemote) {
         this.resolveContents(itemstack, playerIn);
      }

      playerIn.openBook(itemstack, handIn);
      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }

   private void resolveContents(Qy stack, ME player) {
      if (stack.getTagCompound() != null) {
         QQ nbttagcompound = stack.getTagCompound();
         if (!nbttagcompound.getBoolean("resolved")) {
            nbttagcompound.setBoolean("resolved", true);
            if (validBookTagContents(nbttagcompound)) {
               QW nbttaglist = nbttagcompound.getTagList("pages", 8);

               for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                  String s = nbttaglist.getStringTagAt(i);

                  Object itextcomponent;
                  try {
                     ITextComponent itextcomponent = ITextComponent.Serializer.fromJsonLenient(s);
                     itextcomponent = TextComponentUtils.processComponent(player, itextcomponent, player);
                  } catch (Exception var9) {
                     itextcomponent = new TextComponentString(s);
                  }

                  nbttaglist.set(i, new Ra(ITextComponent.Serializer.componentToJson((ITextComponent)itextcomponent)));
               }

               nbttagcompound.setTag("pages", nbttaglist);
               if (player instanceof MG && player.getHeldItemMainhand() == stack) {
                  Slot slot = player.openContainer.getSlotFromInventory(player.inventory, player.inventory.currentItem);
                  ((MG)player).connection.sendPacket(new UJ(0, slot.slotNumber, stack));
               }
            }
         }
      }

   }

   public boolean hasEffect(Qy stack) {
      return true;
   }
}
