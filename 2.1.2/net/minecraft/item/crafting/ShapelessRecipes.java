package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
   private final ItemStack recipeOutput;
   private final NonNullList<Ingredient> recipeItems;
   private final String group;

   public ShapelessRecipes(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
      this.group = group;
      this.recipeOutput = output;
      this.recipeItems = ingredients;
   }

   public String getGroup() {
      return this.group;
   }

   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }

   public NonNullList<Ingredient> getIngredients() {
      return this.recipeItems;
   }

   public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
      NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         ItemStack itemstack = inv.getStackInSlot(i);
         if (itemstack.getItem().hasContainerItem()) {
            nonnulllist.set(i, new ItemStack(itemstack.getItem().getContainerItem()));
         }
      }

      return nonnulllist;
   }

   public boolean matches(InventoryCrafting inv, World worldIn) {
      List<Ingredient> list = Lists.newArrayList((Iterable)this.recipeItems);

      for(int i = 0; i < inv.getHeight(); ++i) {
         for(int j = 0; j < inv.getWidth(); ++j) {
            ItemStack itemstack = inv.getStackInRowAndColumn(j, i);
            if (!itemstack.isEmpty()) {
               boolean flag = false;
               Iterator var8 = list.iterator();

               while(var8.hasNext()) {
                  Ingredient ingredient = (Ingredient)var8.next();
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

   public ItemStack getCraftingResult(InventoryCrafting inv) {
      return this.recipeOutput.copy();
   }

   public static ShapelessRecipes deserialize(JsonObject json) {
      String s = JsonUtils.getString(json, "group", "");
      NonNullList<Ingredient> nonnulllist = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));
      if (nonnulllist.isEmpty()) {
         throw new JsonParseException("No ingredients for shapeless recipe");
      } else if (nonnulllist.size() > 9) {
         throw new JsonParseException("Too many ingredients for shapeless recipe");
      } else {
         ItemStack itemstack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
         return new ShapelessRecipes(s, itemstack, nonnulllist);
      }
   }

   private static NonNullList<Ingredient> deserializeIngredients(JsonArray array) {
      NonNullList<Ingredient> nonnulllist = NonNullList.create();

      for(int i = 0; i < array.size(); ++i) {
         Ingredient ingredient = ShapedRecipes.deserializeIngredient(array.get(i));
         if (ingredient != Ingredient.EMPTY) {
            nonnulllist.add(ingredient);
         }
      }

      return nonnulllist;
   }

   public boolean canFit(int width, int height) {
      return width * height >= this.recipeItems.size();
   }
}
