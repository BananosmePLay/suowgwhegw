package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ir {
   public static final ResourceLocation LIGHTNING_BOLT = new ResourceLocation("lightning_bolt");
   private static final ResourceLocation PLAYER = new ResourceLocation("player");
   private static final Logger LOGGER = LogManager.getLogger();
   public static final RegistryNamespaced<ResourceLocation, Class<? extends Ig>> REGISTRY = new RegistryNamespaced();
   public static final Map<ResourceLocation, Iq> ENTITY_EGGS = Maps.newLinkedHashMap();
   public static final Set<ResourceLocation> KNOWN_TYPES = Sets.newHashSet();
   private static final List<String> OLD_NAMES = Lists.newArrayList();

   public Ir() {
   }

   @Nullable
   public static ResourceLocation getKey(Ig entityIn) {
      return getKey(entityIn.getClass());
   }

   @Nullable
   public static ResourceLocation getKey(Class<? extends Ig> entityIn) {
      return (ResourceLocation)REGISTRY.getNameForObject(entityIn);
   }

   @Nullable
   public static String getEntityString(Ig entityIn) {
      int i = REGISTRY.getIDForObject(entityIn.getClass());
      return i == -1 ? null : (String)OLD_NAMES.get(i);
   }

   @Nullable
   public static String getTranslationName(@Nullable ResourceLocation entityType) {
      int i = REGISTRY.getIDForObject(REGISTRY.getObject(entityType));
      return i == -1 ? null : (String)OLD_NAMES.get(i);
   }

   @Nullable
   public static Class<? extends Ig> getClassFromID(int entityID) {
      return (Class)REGISTRY.getObjectById(entityID);
   }

   @Nullable
   public static Class<? extends Ig> getClassFromName(String p_192839_0_) {
      return (Class)REGISTRY.getObject(new ResourceLocation(p_192839_0_));
   }

   @Nullable
   public static Ig newEntity(@Nullable Class<? extends Ig> clazz, bij worldIn) {
      if (clazz == null) {
         return null;
      } else {
         try {
            return (Ig)clazz.getConstructor(bij.class).newInstance(worldIn);
         } catch (Exception var3) {
            Exception exception = var3;
            exception.printStackTrace();
            return null;
         }
      }
   }

   @Nullable
   public static Ig createEntityByID(int entityID, bij worldIn) {
      return newEntity(getClassFromID(entityID), worldIn);
   }

   @Nullable
   public static Ig createEntityByIDFromName(ResourceLocation name, bij worldIn) {
      return newEntity((Class)REGISTRY.getObject(name), worldIn);
   }

   @Nullable
   public static Ig createEntityFromNBT(QQ nbt, bij worldIn) {
      ResourceLocation resourcelocation = new ResourceLocation(nbt.getString("id"));
      Ig entity = createEntityByIDFromName(resourcelocation, worldIn);
      if (entity != null) {
         entity.readFromNBT(nbt);
      }

      return entity;
   }

   public static Set<ResourceLocation> getEntityNameList() {
      return KNOWN_TYPES;
   }

   public static boolean isMatchingName(Ig entityIn, ResourceLocation entityName) {
      ResourceLocation resourcelocation = getKey(entityIn.getClass());
      if (resourcelocation != null) {
         return resourcelocation.equals(entityName);
      } else if (entityIn instanceof ME) {
         return PLAYER.equals(entityName);
      } else {
         return entityIn instanceof HX ? LIGHTNING_BOLT.equals(entityName) : false;
      }
   }

   public static boolean isRegistered(ResourceLocation entityName) {
      return PLAYER.equals(entityName) || getEntityNameList().contains(entityName);
   }

   public static String getValidTypeNames() {
      StringBuilder stringbuilder = new StringBuilder();
      Iterator var1 = getEntityNameList().iterator();

      while(var1.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var1.next();
         stringbuilder.append(resourcelocation).append(", ");
      }

      stringbuilder.append(PLAYER);
      return stringbuilder.toString();
   }

   public static void init() {
      register(1, "item", IY.class, "Item");
      register(2, "xp_orb", Js.class, "XPOrb");
      register(3, "area_effect_cloud", Ii.class, "AreaEffectCloud");
      register(4, "elder_guardian", JD.class, "ElderGuardian");
      register(5, "wither_skeleton", Lh.class, "WitherSkeleton");
      register(6, "stray", KX.class, "Stray");
      register(7, "egg", MQ.class, "ThrownEgg");
      register(8, "leash_knot", Ip.class, "LeashKnot");
      register(9, "painting", Jq.class, "Painting");
      register(10, "arrow", Ne.class, "Arrow");
      register(11, "snowball", Nb.class, "Snowball");
      register(12, "fireball", MV.class, "Fireball");
      register(13, "small_fireball", Na.class, "SmallFireball");
      register(14, "ender_pearl", IU.class, "ThrownEnderpearl");
      register(15, "eye_of_ender_signal", IT.class, "EyeOfEnderSignal");
      register(16, "potion", MY.class, "ThrownPotion");
      register(17, "xp_bottle", IV.class, "ThrownExpBottle");
      register(18, "item_frame", IZ.class, "ItemFrame");
      register(19, "wither_skull", Nf.class, "WitherSkull");
      register(20, "tnt", Jr.class, "PrimedTnt");
      register(21, "falling_block", IW.class, "FallingSand");
      register(22, "fireworks_rocket", IX.class, "FireworksRocketEntity");
      register(23, "husk", Kd.class, "Husk");
      register(24, "spectral_arrow", Nc.class, "SpectralArrow");
      register(25, "shulker_bullet", MZ.class, "ShulkerBullet");
      register(26, "dragon_fireball", MP.class, "DragonFireball");
      register(27, "zombie_villager", Ll.class, "ZombieVillager");
      register(28, "skeleton_horse", Md.class, "SkeletonHorse");
      register(29, "zombie_horse", Mv.class, "ZombieHorse");
      register(30, "armor_stand", IN.class, "ArmorStand");
      register(31, "donkey", LC.class, "Donkey");
      register(32, "mule", LM.class, "Mule");
      register(33, "evocation_fangs", MR.class, "EvocationFangs");
      register(34, "evocation_illager", JR.class, "EvocationIllager");
      register(35, "vex", Lc.class, "Vex");
      register(36, "vindication_illager", Lf.class, "VindicationIllager");
      register(37, "illusion_illager", Kh.class, "IllusionIllager");
      register(40, "commandblock_minecart", Jg.class, Jb.COMMAND_BLOCK.getName());
      register(41, "boat", IR.class, "Boat");
      register(42, "minecart", Ji.class, Jb.RIDEABLE.getName());
      register(43, "chest_minecart", Jd.class, Jb.CHEST.getName());
      register(44, "furnace_minecart", Jj.class, Jb.FURNACE.getName());
      register(45, "tnt_minecart", Jo.class, Jb.TNT.getName());
      register(46, "hopper_minecart", Jk.class, Jb.HOPPER.getName());
      register(47, "spawner_minecart", Jn.class, Jb.SPAWNER.getName());
      register(50, "creeper", JB.class, "Creeper");
      register(51, "skeleton", KH.class, "Skeleton");
      register(52, "spider", KW.class, "Spider");
      register(53, "giant", JX.class, "Giant");
      register(54, "zombie", Lk.class, "Zombie");
      register(55, "slime", KN.class, "Slime");
      register(56, "ghast", JW.class, "Ghast");
      register(57, "zombie_pigman", Ko.class, "PigZombie");
      register(58, "enderman", JJ.class, "Enderman");
      register(59, "cave_spider", JA.class, "CaveSpider");
      register(60, "silverfish", KG.class, "Silverfish");
      register(61, "blaze", Jz.class, "Blaze");
      register(62, "magma_cube", Kk.class, "LavaSlime");
      register(63, "ender_dragon", HS.class, "EnderDragon");
      register(64, "wither", HV.class, "WitherBoss");
      register(65, "bat", Lz.class, "Bat");
      register(66, "witch", Lg.class, "Witch");
      register(67, "endermite", JK.class, "Endermite");
      register(68, "guardian", Kc.class, "Guardian");
      register(69, "shulker", KD.class, "Shulker");
      register(90, "pig", LQ.class, "Pig");
      register(91, "sheep", Mb.class, "Sheep");
      register(92, "cow", LB.class, "Cow");
      register(93, "chicken", LA.class, "Chicken");
      register(94, "squid", Mf.class, "Squid");
      register(95, "wolf", Mu.class, "Wolf");
      register(96, "mooshroom", LL.class, "MushroomCow");
      register(97, "snowman", KO.class, "SnowMan");
      register(98, "ocelot", LN.class, "Ozelot");
      register(99, "villager_golem", Kj.class, "VillagerGolem");
      register(100, "horse", LF.class, "Horse");
      register(101, "rabbit", LY.class, "Rabbit");
      register(102, "polar_bear", Kv.class, "PolarBear");
      register(103, "llama", LK.class, "Llama");
      register(104, "llama_spit", MW.class, "LlamaSpit");
      register(105, "parrot", LP.class, "Parrot");
      register(120, "villager", Mq.class, "Villager");
      register(200, "ender_crystal", IS.class, "EnderCrystal");
      addSpawnInfo("bat", 4996656, 986895);
      addSpawnInfo("blaze", 16167425, 16775294);
      addSpawnInfo("cave_spider", 803406, 11013646);
      addSpawnInfo("chicken", 10592673, 16711680);
      addSpawnInfo("cow", 4470310, 10592673);
      addSpawnInfo("creeper", 894731, 0);
      addSpawnInfo("donkey", 5457209, 8811878);
      addSpawnInfo("elder_guardian", 13552826, 7632531);
      addSpawnInfo("enderman", 1447446, 0);
      addSpawnInfo("endermite", 1447446, 7237230);
      addSpawnInfo("evocation_illager", 9804699, 1973274);
      addSpawnInfo("ghast", 16382457, 12369084);
      addSpawnInfo("guardian", 5931634, 15826224);
      addSpawnInfo("horse", 12623485, 15656192);
      addSpawnInfo("husk", 7958625, 15125652);
      addSpawnInfo("llama", 12623485, 10051392);
      addSpawnInfo("magma_cube", 3407872, 16579584);
      addSpawnInfo("mooshroom", 10489616, 12040119);
      addSpawnInfo("mule", 1769984, 5321501);
      addSpawnInfo("ocelot", 15720061, 5653556);
      addSpawnInfo("parrot", 894731, 16711680);
      addSpawnInfo("pig", 15771042, 14377823);
      addSpawnInfo("polar_bear", 15921906, 9803152);
      addSpawnInfo("rabbit", 10051392, 7555121);
      addSpawnInfo("sheep", 15198183, 16758197);
      addSpawnInfo("shulker", 9725844, 5060690);
      addSpawnInfo("silverfish", 7237230, 3158064);
      addSpawnInfo("skeleton", 12698049, 4802889);
      addSpawnInfo("skeleton_horse", 6842447, 15066584);
      addSpawnInfo("slime", 5349438, 8306542);
      addSpawnInfo("spider", 3419431, 11013646);
      addSpawnInfo("squid", 2243405, 7375001);
      addSpawnInfo("stray", 6387319, 14543594);
      addSpawnInfo("vex", 8032420, 15265265);
      addSpawnInfo("villager", 5651507, 12422002);
      addSpawnInfo("vindication_illager", 9804699, 2580065);
      addSpawnInfo("witch", 3407872, 5349438);
      addSpawnInfo("wither_skeleton", 1315860, 4672845);
      addSpawnInfo("wolf", 14144467, 13545366);
      addSpawnInfo("zombie", 44975, 7969893);
      addSpawnInfo("zombie_horse", 3232308, 9945732);
      addSpawnInfo("zombie_pigman", 15373203, 5009705);
      addSpawnInfo("zombie_villager", 5651507, 7969893);
      KNOWN_TYPES.add(LIGHTNING_BOLT);
   }

   private static void register(int id, String name, Class<? extends Ig> clazz, String oldName) {
      try {
         clazz.getConstructor(bij.class);
      } catch (NoSuchMethodException var5) {
         throw new RuntimeException("Invalid class " + clazz + " no constructor taking " + bij.class.getName());
      }

      if ((clazz.getModifiers() & 1024) == 1024) {
         throw new RuntimeException("Invalid abstract class " + clazz);
      } else {
         ResourceLocation resourcelocation = new ResourceLocation(name);
         REGISTRY.register(id, resourcelocation, clazz);
         KNOWN_TYPES.add(resourcelocation);

         while(OLD_NAMES.size() <= id) {
            OLD_NAMES.add((Object)null);
         }

         OLD_NAMES.set(id, oldName);
      }
   }

   protected static Iq addSpawnInfo(String id, int primaryColor, int secondaryColor) {
      ResourceLocation resourcelocation = new ResourceLocation(id);
      return (Iq)ENTITY_EGGS.put(resourcelocation, new Iq(resourcelocation, primaryColor, secondaryColor));
   }
}
