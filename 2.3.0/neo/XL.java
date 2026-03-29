package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XL extends XK {
   private static final Logger LOGGER = LogManager.getLogger();

   public XL() {
   }

   public void add(List<NT> recipesIn, MG player) {
      List<NT> list = Lists.newArrayList();
      Iterator var4 = recipesIn.iterator();

      while(var4.hasNext()) {
         NT irecipe = (NT)var4.next();
         if (!this.recipes.get(getRecipeId(irecipe)) && !irecipe.isDynamic()) {
            this.unlock(irecipe);
            this.markNew(irecipe);
            list.add(irecipe);
            bY.RECIPE_UNLOCKED.trigger(player, irecipe);
         }
      }

      this.sendPacket(Uz.ADD, player, list);
   }

   public void remove(List<NT> recipesIn, MG player) {
      List<NT> list = Lists.newArrayList();
      Iterator var4 = recipesIn.iterator();

      while(var4.hasNext()) {
         NT irecipe = (NT)var4.next();
         if (this.recipes.get(getRecipeId(irecipe))) {
            this.lock(irecipe);
            list.add(irecipe);
         }
      }

      this.sendPacket(Uz.REMOVE, player, list);
   }

   private void sendPacket(Uz state, MG player, List<NT> recipesIn) {
      player.connection.sendPacket(new UA(state, recipesIn, Collections.emptyList(), this.isGuiOpen, this.isFilteringCraftable));
   }

   public QQ write() {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setBoolean("isGuiOpen", this.isGuiOpen);
      nbttagcompound.setBoolean("isFilteringCraftable", this.isFilteringCraftable);
      QW nbttaglist = new QW();
      Iterator var3 = this.getRecipes().iterator();

      while(var3.hasNext()) {
         NT irecipe = (NT)var3.next();
         nbttaglist.appendTag(new Ra(((ResourceLocation)NP.REGISTRY.getNameForObject(irecipe)).toString()));
      }

      nbttagcompound.setTag("recipes", nbttaglist);
      QW nbttaglist1 = new QW();
      Iterator var7 = this.getDisplayedRecipes().iterator();

      while(var7.hasNext()) {
         NT irecipe1 = (NT)var7.next();
         nbttaglist1.appendTag(new Ra(((ResourceLocation)NP.REGISTRY.getNameForObject(irecipe1)).toString()));
      }

      nbttagcompound.setTag("toBeDisplayed", nbttaglist1);
      return nbttagcompound;
   }

   public void read(QQ tag) {
      this.isGuiOpen = tag.getBoolean("isGuiOpen");
      this.isFilteringCraftable = tag.getBoolean("isFilteringCraftable");
      QW nbttaglist = tag.getTagList("recipes", 8);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         ResourceLocation resourcelocation = new ResourceLocation(nbttaglist.getStringTagAt(i));
         NT irecipe = NP.getRecipe(resourcelocation);
         if (irecipe == null) {
            LOGGER.info("Tried to load unrecognized recipe: {} removed now.", resourcelocation);
         } else {
            this.unlock(irecipe);
         }
      }

      QW nbttaglist1 = tag.getTagList("toBeDisplayed", 8);

      for(int j = 0; j < nbttaglist1.tagCount(); ++j) {
         ResourceLocation resourcelocation1 = new ResourceLocation(nbttaglist1.getStringTagAt(j));
         NT irecipe1 = NP.getRecipe(resourcelocation1);
         if (irecipe1 == null) {
            LOGGER.info("Tried to load unrecognized recipe: {} removed now.", resourcelocation1);
         } else {
            this.markNew(irecipe1);
         }
      }

   }

   private List<NT> getRecipes() {
      List<NT> list = Lists.newArrayList();

      for(int i = this.recipes.nextSetBit(0); i >= 0; i = this.recipes.nextSetBit(i + 1)) {
         list.add(NP.REGISTRY.getObjectById(i));
      }

      return list;
   }

   private List<NT> getDisplayedRecipes() {
      List<NT> list = Lists.newArrayList();

      for(int i = this.newRecipes.nextSetBit(0); i >= 0; i = this.newRecipes.nextSetBit(i + 1)) {
         list.add(NP.REGISTRY.getObjectById(i));
      }

      return list;
   }

   public void init(MG player) {
      player.connection.sendPacket(new UA(Uz.INIT, this.getRecipes(), this.getDisplayedRecipes(), this.isGuiOpen, this.isFilteringCraftable));
   }
}
