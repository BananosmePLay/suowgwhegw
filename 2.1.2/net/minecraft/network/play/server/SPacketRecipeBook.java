package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketRecipeBook implements Packet<INetHandlerPlayClient> {
   private State state;
   private List<IRecipe> recipes;
   private List<IRecipe> displayedRecipes;
   private boolean guiOpen;
   private boolean filteringCraftable;

   public SPacketRecipeBook() {
   }

   public SPacketRecipeBook(State stateIn, List<IRecipe> recipesIn, List<IRecipe> displayedRecipesIn, boolean isGuiOpen, boolean p_i47597_5_) {
      this.state = stateIn;
      this.recipes = recipesIn;
      this.displayedRecipes = displayedRecipesIn;
      this.guiOpen = isGuiOpen;
      this.filteringCraftable = p_i47597_5_;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleRecipeBook(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.state = (State)buf.readEnumValue(State.class);
      this.guiOpen = buf.readBoolean();
      this.filteringCraftable = buf.readBoolean();
      int i = buf.readVarInt();
      this.recipes = Lists.newArrayList();

      int k;
      for(k = 0; k < i; ++k) {
         this.recipes.add(CraftingManager.getRecipeById(buf.readVarInt()));
      }

      if (this.state == SPacketRecipeBook.State.INIT) {
         i = buf.readVarInt();
         this.displayedRecipes = Lists.newArrayList();

         for(k = 0; k < i; ++k) {
            this.displayedRecipes.add(CraftingManager.getRecipeById(buf.readVarInt()));
         }
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.state);
      buf.writeBoolean(this.guiOpen);
      buf.writeBoolean(this.filteringCraftable);
      buf.writeVarInt(this.recipes.size());
      Iterator var2 = this.recipes.iterator();

      IRecipe irecipe1;
      while(var2.hasNext()) {
         irecipe1 = (IRecipe)var2.next();
         buf.writeVarInt(CraftingManager.getIDForRecipe(irecipe1));
      }

      if (this.state == SPacketRecipeBook.State.INIT) {
         buf.writeVarInt(this.displayedRecipes.size());
         var2 = this.displayedRecipes.iterator();

         while(var2.hasNext()) {
            irecipe1 = (IRecipe)var2.next();
            buf.writeVarInt(CraftingManager.getIDForRecipe(irecipe1));
         }
      }

   }

   public List<IRecipe> getRecipes() {
      return this.recipes;
   }

   public List<IRecipe> getDisplayedRecipes() {
      return this.displayedRecipes;
   }

   public boolean isGuiOpen() {
      return this.guiOpen;
   }

   public boolean isFilteringCraftable() {
      return this.filteringCraftable;
   }

   public State getState() {
      return this.state;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }

   public static enum State {
      INIT,
      ADD,
      REMOVE;

      private State() {
      }
   }
}
