package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class Bz implements BA {
   private static final Set<co> TREE_BLOCKS;
   private static final ITextComponent TITLE;
   private static final ITextComponent DESCRIPTION;
   private final BF tutorial;
   private nk toast;
   private int timeWaiting;

   public Bz(BF tutorial) {
      this.tutorial = tutorial;
   }

   public void update() {
      ++this.timeWaiting;
      if (this.tutorial.getGameType() != bbb.SURVIVAL) {
         this.tutorial.setStep(BG.NONE);
      } else {
         if (this.timeWaiting == 1) {
            this.tutorial.getMinecraft();
            jh entityplayersp = nC.player;
            if (entityplayersp != null) {
               Iterator var2 = TREE_BLOCKS.iterator();

               while(var2.hasNext()) {
                  co block = (co)var2.next();
                  if (entityplayersp.inventory.hasItemStack(new Qy(block))) {
                     this.tutorial.setStep(BG.CRAFT_PLANKS);
                     return;
                  }
               }

               if (hasPunchedTreesPreviously(entityplayersp)) {
                  this.tutorial.setStep(BG.CRAFT_PLANKS);
                  return;
               }
            }
         }

         if (this.timeWaiting >= 6000 && this.toast == null) {
            this.toast = new nk(nj.TREE, TITLE, DESCRIPTION, false);
            this.tutorial.getMinecraft().getToastGui().add(this.toast);
         }
      }

   }

   public void onStop() {
      if (this.toast != null) {
         this.toast.hide();
         this.toast = null;
      }

   }

   public void onMouseHover(pm worldIn, RayTraceResult result) {
      if (result.typeOfHit == RayTraceResult.Type.BLOCK && result.getBlockPos() != null) {
         in iblockstate = worldIn.getBlockState(result.getBlockPos());
         if (TREE_BLOCKS.contains(iblockstate.getBlock())) {
            this.tutorial.setStep(BG.PUNCH_TREE);
         }
      }

   }

   public void handleSetSlot(Qy stack) {
      Iterator var2 = TREE_BLOCKS.iterator();

      co block;
      do {
         if (!var2.hasNext()) {
            return;
         }

         block = (co)var2.next();
      } while(stack.getItem() != OL.getItemFromBlock(block));

      this.tutorial.setStep(BG.CRAFT_PLANKS);
   }

   public static boolean hasPunchedTreesPreviously(jh p_194070_0_) {
      Iterator var1 = TREE_BLOCKS.iterator();

      XQ statbase;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         co block = (co)var1.next();
         statbase = XV.getBlockStats(block);
      } while(statbase == null || p_194070_0_.getStatFileWriter().readStat(statbase) <= 0);

      return true;
   }

   static {
      TREE_BLOCKS = Sets.newHashSet(new co[]{Nk.LOG, Nk.LOG2, Nk.LEAVES, Nk.LEAVES2});
      TITLE = new TextComponentTranslation("tutorial.find_tree.title", new Object[0]);
      DESCRIPTION = new TextComponentTranslation("tutorial.find_tree.description", new Object[0]);
   }
}
