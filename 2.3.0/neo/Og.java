package neo;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;

public class Og implements NT {
   private final Qy recipeOutput;
   private final NonNullList<NS> recipeItems;
   private final String group;

   public Og(String group, Qy output, NonNullList<NS> ingredients) {
      this.group = group;
      this.recipeOutput = output;
      this.recipeItems = ingredients;
   }

   public String getGroup() {
      return this.group;
   }

   public Qy getRecipeOutput() {
      return this.recipeOutput;
   }

   public NonNullList<NS> getIngredients() {
      return this.recipeItems;
   }

   public NonNullList<Qy> getRemainingItems(InventoryCrafting inv) {
      NonNullList<Qy> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), Qy.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (itemstack.getItem().hasContainerItem()) {
            nonnulllist.set(i, new Qy(itemstack.getItem().getContainerItem()));
         }
      }

      return nonnulllist;
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      List<NS> list = Lists.newArrayList(this.recipeItems);

      for(int i = 0; i < inv.getHeight(); ++i) {
         for(int j = 0; j < inv.getWidth(); ++j) {
            Qy itemstack = inv.getStackInRowAndColumn(j, i);
            if (!itemstack.isEmpty()) {
               boolean flag = false;
               Iterator var8 = list.iterator();

               while(var8.hasNext()) {
                  NS ingredient = (NS)var8.next();
                  if (ingredient.apply(itemstack)) {
                     flag = true;
                     list.remove(ingredient);
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }
         }
      }

      return list.isEmpty();
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      return this.recipeOutput.copy();
   }

   public static Og deserialize(JsonObject json) {
      String s = JsonUtils.getString(json, "group", "");
      NonNullList<NS> nonnulllist = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));
      if (nonnulllist.isEmpty()) {
         throw new JsonParseException("No ingredients for shapeless recipe");
      } else if (nonnulllist.size() > 9) {
         throw new JsonParseException("Too many ingredients for shapeless recipe");
      } else {
         Qy itemstack = Of.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
         return new Og(s, itemstack, nonnulllist);
      }
   }

   private static NonNullList<NS> deserializeIngredients(JsonArray array) {
      NonNullList<NS> nonnulllist = NonNullList.create();

      for(int i = 0; i < array.size(); ++i) {
         NS ingredient = Of.deserializeIngredient(array.get(i));
         if (ingredient != NS.EMPTY) {
            nonnulllist.add(ingredient);
         }
      }

      return nonnulllist;
   }

   public boolean canFit(int width, int height) {
      return width * height >= this.recipeItems.size();
   }
}
