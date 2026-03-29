package net.minecraft.client.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.ItemStack;

public class SearchTreeManager implements IResourceManagerReloadListener {
   public static final Key<ItemStack> ITEMS = new Key();
   public static final Key<RecipeList> RECIPES = new Key();
   private final Map<Key<?>, SearchTree<?>> trees = Maps.newHashMap();

   public SearchTreeManager() {
   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      Iterator var2 = this.trees.values().iterator();

      while(var2.hasNext()) {
         SearchTree<?> searchtree = (SearchTree)var2.next();
         searchtree.recalculate();
      }

   }

   public <T> void register(Key<T> key, SearchTree<T> searchTreeIn) {
      this.trees.put(key, searchTreeIn);
   }

   public <T> ISearchTree<T> get(Key<T> key) {
      return (ISearchTree)this.trees.get(key);
   }

   public static class Key<T> {
      public Key() {
      }
   }
}
