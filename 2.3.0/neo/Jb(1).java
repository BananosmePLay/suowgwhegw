package neo;

import com.google.common.collect.Maps;
import java.util.Map;

public enum Jb {
   RIDEABLE(0, "MinecartRideable"),
   CHEST(1, "MinecartChest"),
   FURNACE(2, "MinecartFurnace"),
   TNT(3, "MinecartTNT"),
   SPAWNER(4, "MinecartSpawner"),
   HOPPER(5, "MinecartHopper"),
   COMMAND_BLOCK(6, "MinecartCommandBlock");

   private static final Map<Integer, Jb> BY_ID = Maps.newHashMap();
   private final int id;
   private final String name;

   private Jb(int idIn, String nameIn) {
      this.id = idIn;
      this.name = nameIn;
   }

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public static Jb getById(int idIn) {
      Jb entityminecart$type = (Jb)BY_ID.get(idIn);
      return entityminecart$type == null ? RIDEABLE : entityminecart$type;
   }

   static {
      Jb[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Jb entityminecart$type = var0[var2];
         BY_ID.put(entityminecart$type.getId(), entityminecart$type);
      }

   }
}
