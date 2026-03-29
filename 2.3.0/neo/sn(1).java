package neo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class sn {
   public static final sn NONE = new sn();
   private final List<sm> overrides = Lists.newArrayList();
   private bmS itemOverrideCache;

   private sn() {
   }

   public sn(List<sm> overridesIn) {
      for(int i = overridesIn.size() - 1; i >= 0; --i) {
         this.overrides.add(overridesIn.get(i));
      }

      if (this.overrides.size() > 65) {
         this.itemOverrideCache = bmS.make(this.overrides);
      }

   }

   @Nullable
   public ResourceLocation applyOverride(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
      if (!this.overrides.isEmpty()) {
         if (this.itemOverrideCache != null) {
            ResourceLocation resourcelocation = this.itemOverrideCache.getModelLocation(stack, worldIn, entityIn);
            if (resourcelocation != null) {
               return resourcelocation == bmS.LOCATION_NULL ? null : resourcelocation;
            }
         }

         Iterator var6 = this.overrides.iterator();

         while(var6.hasNext()) {
            sm itemoverride = (sm)var6.next();
            if (itemoverride.matchesItemStack(stack, worldIn, entityIn)) {
               if (this.itemOverrideCache != null) {
                  this.itemOverrideCache.putModelLocation(stack, worldIn, entityIn, itemoverride.getLocation());
               }

               return itemoverride.getLocation();
            }
         }

         if (this.itemOverrideCache != null) {
            this.itemOverrideCache.putModelLocation(stack, worldIn, entityIn, bmS.LOCATION_NULL);
         }
      }

      return null;
   }

   public sc handleItemState(sc p_handleItemState_1_, Qy p_handleItemState_2_, @Nullable bij p_handleItemState_3_, @Nullable Iw p_handleItemState_4_) {
      if (!p_handleItemState_2_.isEmpty() && p_handleItemState_2_.getItem().hasCustomProperties()) {
         ResourceLocation resourcelocation = this.applyOverride(p_handleItemState_2_, p_handleItemState_3_, p_handleItemState_4_);
         if (resourcelocation != null && bnK.ModelLoader_getInventoryVariant.exists()) {
            sD modelresourcelocation = (sD)bnK.call(bnK.ModelLoader_getInventoryVariant, resourcelocation.toString());
            return nC.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(modelresourcelocation);
         }
      }

      return p_handleItemState_1_;
   }

   public ImmutableList<sm> getOverrides() {
      return ImmutableList.copyOf(this.overrides);
   }
}
