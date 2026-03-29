package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class bY {
   private static final Map<ResourceLocation, ci<?>> REGISTRY = Maps.newHashMap();
   public static final X IMPOSSIBLE = (X)register(new X());
   public static final bh PLAYER_KILLED_ENTITY = (bh)register(new bh(new ResourceLocation("player_killed_entity")));
   public static final bh ENTITY_KILLED_PLAYER = (bh)register(new bh(new ResourceLocation("entity_killed_player")));
   public static final R ENTER_BLOCK = (R)register(new R());
   public static final ba INVENTORY_CHANGED = (ba)register(new ba());
   public static final bF RECIPE_UNLOCKED = (bF)register(new bF());
   public static final bz PLAYER_HURT_ENTITY = (bz)register(new bz());
   public static final U ENTITY_HURT_PLAYER = (U)register(new U());
   public static final N ENCHANTED_ITEM = (N)register(new N());
   public static final s BREWED_POTION = (s)register(new s());
   public static final y CONSTRUCT_BEACON = (y)register(new y());
   public static final bR USED_ENDER_EYE = (bR)register(new bR());
   public static final bI SUMMONED_ENTITY = (bI)register(new bI());
   public static final p BRED_ANIMALS = (p)register(new p());
   public static final bC LOCATION = (bC)register(new bC(new ResourceLocation("location")));
   public static final bC SLEPT_IN_BED = (bC)register(new bC(new ResourceLocation("slept_in_bed")));
   public static final E CURED_ZOMBIE_VILLAGER = (E)register(new E());
   public static final bX VILLAGER_TRADE = (bX)register(new bX());
   public static final bd ITEM_DURABILITY_CHANGED = (bd)register(new bd());
   public static final bk LEVITATION = (bk)register(new bk());
   public static final v CHANGED_DIMENSION = (v)register(new v());
   public static final bO TICK = (bO)register(new bO());
   public static final bL TAME_ANIMAL = (bL)register(new bL());
   public static final bw PLACED_BLOCK = (bw)register(new bw());
   public static final B CONSUME_ITEM = (B)register(new B());
   public static final K EFFECTS_CHANGED = (K)register(new K());
   public static final bU USED_TOTEM = (bU)register(new bU());
   public static final bs NETHER_TRAVEL = (bs)register(new bs());

   public bY() {
   }

   private static <T extends ci> T register(T criterion) {
      if (REGISTRY.containsKey(criterion.getId())) {
         throw new IllegalArgumentException("Duplicate criterion id " + criterion.getId());
      } else {
         REGISTRY.put(criterion.getId(), criterion);
         return criterion;
      }
   }

   @Nullable
   public static <T extends cg> ci<T> get(ResourceLocation id) {
      return (ci)REGISTRY.get(id);
   }

   public static Iterable<? extends ci<?>> getAll() {
      return REGISTRY.values();
   }
}
