package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;

public class Yz extends Yg {
   private OL flowerPotItem;
   private int flowerPotData;

   public Yz() {
   }

   public Yz(OL potItem, int potData) {
      this.flowerPotItem = potItem;
      this.flowerPotData = potData;
   }

   public static void registerFixesFlowerPot(DataFixer fixer) {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      ResourceLocation resourcelocation = (ResourceLocation)OL.REGISTRY.getNameForObject(this.flowerPotItem);
      compound.setString("Item", resourcelocation == null ? "" : resourcelocation.toString());
      compound.setInteger("Data", this.flowerPotData);
      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      if (compound.hasKey("Item", 8)) {
         this.flowerPotItem = OL.getByNameOrId(compound.getString("Item"));
      } else {
         this.flowerPotItem = OL.getItemById(compound.getInteger("Item"));
      }

      this.flowerPotData = compound.getInteger("Data");
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 5, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public void setItemStack(Qy stack) {
      this.flowerPotItem = stack.getItem();
      this.flowerPotData = stack.getMetadata();
   }

   public Qy getFlowerItemStack() {
      return this.flowerPotItem == null ? Qy.EMPTY : new Qy(this.flowerPotItem, 1, this.flowerPotData);
   }

   @Nullable
   public OL getFlowerPotItem() {
      return this.flowerPotItem;
   }

   public int getFlowerPotData() {
      return this.flowerPotData;
   }
}
