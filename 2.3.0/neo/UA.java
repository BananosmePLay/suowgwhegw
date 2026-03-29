package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UA implements Sz<Ts> {
   private Uz state;
   private List<NT> recipes;
   private List<NT> displayedRecipes;
   private boolean guiOpen;
   private boolean filteringCraftable;

   public UA() {
   }

   public UA(Uz stateIn, List<NT> recipesIn, List<NT> displayedRecipesIn, boolean isGuiOpen, boolean p_i47597_5_) {
      this.state = stateIn;
      this.recipes = recipesIn;
      this.displayedRecipes = displayedRecipesIn;
      this.guiOpen = isGuiOpen;
      this.filteringCraftable = p_i47597_5_;
   }

   public void processPacket(Ts handler) {
      handler.handleRecipeBook(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.state = (Uz)buf.readEnumValue(Uz.class);
      this.guiOpen = buf.readBoolean();
      this.filteringCraftable = buf.readBoolean();
      int i = buf.readVarInt();
      this.recipes = Lists.newArrayList();

      int k;
      for(k = 0; k < i; ++k) {
         this.recipes.add(NP.getRecipeById(buf.readVarInt()));
      }

      if (this.state == Uz.INIT) {
         i = buf.readVarInt();
         this.displayedRecipes = Lists.newArrayList();

         for(k = 0; k < i; ++k) {
            this.displayedRecipes.add(NP.getRecipeById(buf.readVarInt()));
         }
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.state);
      buf.writeBoolean(this.guiOpen);
      buf.writeBoolean(this.filteringCraftable);
      buf.writeVarInt(this.recipes.size());
      Iterator var2 = this.recipes.iterator();

      NT irecipe1;
      while(var2.hasNext()) {
         irecipe1 = (NT)var2.next();
         buf.writeVarInt(NP.getIDForRecipe(irecipe1));
      }

      if (this.state == Uz.INIT) {
         buf.writeVarInt(this.displayedRecipes.size());
         var2 = this.displayedRecipes.iterator();

         while(var2.hasNext()) {
            irecipe1 = (NT)var2.next();
            buf.writeVarInt(NP.getIDForRecipe(irecipe1));
         }
      }

   }

   public List<NT> getRecipes() {
      return this.recipes;
   }

   public List<NT> getDisplayedRecipes() {
      return this.displayedRecipes;
   }

   public boolean isGuiOpen() {
      return this.guiOpen;
   }

   public boolean isFilteringCraftable() {
      return this.filteringCraftable;
   }

   public Uz getState() {
      return this.state;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
