package net.optifine.config;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemLocator implements IObjectLocator {
   public ItemLocator() {
   }

   public Object getObject(ResourceLocation loc) {
      Item item = Item.getByNameOrId(loc.toString());
      return item;
   }
}
