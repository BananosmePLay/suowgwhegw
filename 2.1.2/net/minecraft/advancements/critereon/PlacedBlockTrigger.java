package net.minecraft.advancements.critereon;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class PlacedBlockTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("placed_block");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public PlacedBlockTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners placedblocktrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (placedblocktrigger$listeners == null) {
         placedblocktrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, placedblocktrigger$listeners);
      }

      placedblocktrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners placedblocktrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (placedblocktrigger$listeners != null) {
         placedblocktrigger$listeners.remove(listener);
         if (placedblocktrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      Block block = null;
      if (json.has("block")) {
         ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "block"));
         if (!Block.REGISTRY.containsKey(resourcelocation)) {
            throw new JsonSyntaxException("Unknown block type '" + resourcelocation + "'");
         }

         block = (Block)Block.REGISTRY.getObject(resourcelocation);
      }

      Map<IProperty<?>, Object> map = null;
      if (json.has("state")) {
         if (block == null) {
            throw new JsonSyntaxException("Can't define block state without a specific block type");
         }

         BlockStateContainer blockstatecontainer = block.getBlockState();

         IProperty iproperty;
         Optional optional;
         for(Iterator var6 = JsonUtils.getJsonObject(json, "state").entrySet().iterator(); var6.hasNext(); map.put(iproperty, optional.get())) {
            Map.Entry<String, JsonElement> entry = (Map.Entry)var6.next();
            iproperty = blockstatecontainer.getProperty((String)entry.getKey());
            if (iproperty == null) {
               throw new JsonSyntaxException("Unknown block state property '" + (String)entry.getKey() + "' for block '" + Block.REGISTRY.getNameForObject(block) + "'");
            }

            String s = JsonUtils.getString((JsonElement)entry.getValue(), (String)entry.getKey());
            optional = iproperty.parseValue(s);
            if (!optional.isPresent()) {
               throw new JsonSyntaxException("Invalid block state value '" + s + "' for property '" + (String)entry.getKey() + "' on block '" + Block.REGISTRY.getNameForObject(block) + "'");
            }

            if (map == null) {
               map = Maps.newHashMap();
            }
         }
      }

      LocationPredicate locationpredicate = LocationPredicate.deserialize(json.get("location"));
      ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
      return new Instance(block, map, locationpredicate, itempredicate);
   }

   public void trigger(EntityPlayerMP player, BlockPos pos, ItemStack item) {
      IBlockState iblockstate = player.world.getBlockState(pos);
      Listeners placedblocktrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (placedblocktrigger$listeners != null) {
         placedblocktrigger$listeners.trigger(iblockstate, pos, player.getServerWorld(), item);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public ICriterionInstance deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }

   static class Listeners {
      private final PlayerAdvancements playerAdvancements;
      private final Set<ICriterionTrigger.Listener<Instance>> listeners = Sets.newHashSet();

      public Listeners(PlayerAdvancements playerAdvancementsIn) {
         this.playerAdvancements = playerAdvancementsIn;
      }

      public boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      public void add(ICriterionTrigger.Listener<Instance> listener) {
         this.listeners.add(listener);
      }

      public void remove(ICriterionTrigger.Listener<Instance> listener) {
         this.listeners.remove(listener);
      }

      public void trigger(IBlockState state, BlockPos pos, WorldServer world, ItemStack item) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var6 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var6.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var6.next();
            if (((Instance)listener1.getCriterionInstance()).test(state, pos, world, item)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var6 = list.iterator();

            while(var6.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var6.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final Block block;
      private final Map<IProperty<?>, Object> properties;
      private final LocationPredicate location;
      private final ItemPredicate item;

      public Instance(@Nullable Block block, @Nullable Map<IProperty<?>, Object> propertiesIn, LocationPredicate locationIn, ItemPredicate itemIn) {
         super(PlacedBlockTrigger.ID);
         this.block = block;
         this.properties = propertiesIn;
         this.location = locationIn;
         this.item = itemIn;
      }

      public boolean test(IBlockState state, BlockPos pos, WorldServer world, ItemStack item) {
         if (this.block != null && state.getBlock() != this.block) {
            return false;
         } else {
            if (this.properties != null) {
               Iterator var5 = this.properties.entrySet().iterator();

               while(var5.hasNext()) {
                  Map.Entry<IProperty<?>, Object> entry = (Map.Entry)var5.next();
                  if (state.getValue((IProperty)entry.getKey()) != entry.getValue()) {
                     return false;
                  }
               }
            }

            return !this.location.test(world, (float)pos.getX(), (float)pos.getY(), (float)pos.getZ()) ? false : this.item.test(item);
         }
      }
   }
}
