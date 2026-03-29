package neo;

import net.minecraft.util.ResourceLocation;

public class bcm {
   private final int version;
   private in layerMaterial;
   private int layerCount;
   private int layerMinimumY;

   public bcm(int p_i45467_1_, co layerMaterialIn) {
      this(3, p_i45467_1_, layerMaterialIn);
   }

   public bcm(int p_i45627_1_, int height, co layerMaterialIn) {
      this.layerCount = 1;
      this.version = p_i45627_1_;
      this.layerCount = height;
      this.layerMaterial = layerMaterialIn.getDefaultState();
   }

   public bcm(int p_i45628_1_, int p_i45628_2_, co layerMaterialIn, int p_i45628_4_) {
      this(p_i45628_1_, p_i45628_2_, layerMaterialIn);
      this.layerMaterial = layerMaterialIn.getStateFromMeta(p_i45628_4_);
   }

   public int getLayerCount() {
      return this.layerCount;
   }

   public in getLayerMaterial() {
      return this.layerMaterial;
   }

   private co getLayerMaterialBlock() {
      return this.layerMaterial.getBlock();
   }

   private int getFillBlockMeta() {
      return this.layerMaterial.getBlock().getMetaFromState(this.layerMaterial);
   }

   public int getMinY() {
      return this.layerMinimumY;
   }

   public void setMinY(int minY) {
      this.layerMinimumY = minY;
   }

   public String toString() {
      String s;
      if (this.version >= 3) {
         ResourceLocation resourcelocation = (ResourceLocation)co.REGISTRY.getNameForObject(this.getLayerMaterialBlock());
         s = resourcelocation == null ? "null" : resourcelocation.toString();
         if (this.layerCount > 1) {
            s = this.layerCount + "*" + s;
         }
      } else {
         s = Integer.toString(co.getIdFromBlock(this.getLayerMaterialBlock()));
         if (this.layerCount > 1) {
            s = this.layerCount + "x" + s;
         }
      }

      int i = this.getFillBlockMeta();
      if (i > 0) {
         s = s + ":" + i;
      }

      return s;
   }
}
