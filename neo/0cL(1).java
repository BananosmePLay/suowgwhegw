package neo;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityDragonFireball;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityLlamaSpit;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.network.play.server.SPacketCloseWindow;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketConfirmTransaction;
import net.minecraft.network.play.server.SPacketCooldown;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.network.play.server.SPacketDisplayObjective;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraft.network.play.server.SPacketEntityProperties;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.network.play.server.SPacketKeepAlive;
import net.minecraft.network.play.server.SPacketMaps;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketPlaceGhostRecipe;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketRecipeBook;
import net.minecraft.network.play.server.SPacketRemoveEntityEffect;
import net.minecraft.network.play.server.SPacketResourcePackSend;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketScoreboardObjective;
import net.minecraft.network.play.server.SPacketSelectAdvancementsTab;
import net.minecraft.network.play.server.SPacketServerDifficulty;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.network.play.server.SPacketSignEditorOpen;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.network.play.server.SPacketSpawnPosition;
import net.minecraft.network.play.server.SPacketStatistics;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.network.play.server.SPacketTeams;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketUnloadChunk;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketUpdateHealth;
import net.minecraft.network.play.server.SPacketUpdateScore;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraft.network.play.server.SPacketWindowProperty;
import net.minecraft.network.play.server.SPacketWorldBorder;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0cL implements INetHandlerPlayClient {
   public final GameProfile profile;
   public final 0cC pbot;
   public final 0cP netManager;
   public boolean doneLoadingTerrain;
   public int currentServerMaxPlayers = 30730 ^ -27045 ^ 7953 ^ -3756;
   public final Map<UUID, NetworkPlayerInfo> playerInfoMap = Maps.newHashMap();
   public static final Logger LOGGER = LogManager.getLogger();

   private static 0cC gqijo1h0S6(0cL var0) {
      return var0.pbot;
   }

   private static 0cH _56N4FWelV/* $FF was: 756N4FWelV*/(0cC var0) {
      return var0.mc;
   }

   private static double zbtSLDam2U(0cD var0) {
      return var0.motionX;
   }

   private static 0cC FjUpcgxvW9(0cL var0) {
      return var0.pbot;
   }

   private static SPacketUpdateScore.Action _NSFLuZr4Q/* $FF was: 3NSFLuZr4Q*/() {
      return SPacketUpdateScore.Action.CHANGE;
   }

   private static void VLB2Rr6Iyy(EntityLivingBase var0, float var1) {
      var0.rotationYawHead = var1;
   }

   private static 0cC Gtzg5eC47l(0cL var0) {
      return var0.pbot;
   }

   private static 0cH egTHBokWO9(0cC var0) {
      return var0.mc;
   }

   private static Map fVRTxJznSF(0cL var0) {
      return var0.playerInfoMap;
   }

   private static 0dB qZCpF00cLx(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cC JgVIaA2Q66(0cL var0) {
      return var0.pbot;
   }

   public void handleCamera(SPacketCamera packetIn) {
   }

   private static 0cD YlZgwEU54u(0cC var0) {
      return var0.player;
   }

   private static 0cD jG860lWEev(0cC var0) {
      return var0.player;
   }

   private static 0cP TuSYJoGDnL(0cL var0) {
      return var0.netManager;
   }

   private static 0cH Ag72i2sKn4(0cC var0) {
      return var0.mc;
   }

   private static 0cC aGGZcjB05y(0cL var0) {
      return var0.pbot;
   }

   private static void dxDCgaIr1Q(0cD var0, double var1) {
      var0.prevPosX = var1;
   }

   private static 0cH g2B1WIWi11(0cC var0) {
      return var0.mc;
   }

   private static 0cC aLrLycG4cb(0cL var0) {
      return var0.pbot;
   }

   private static 0cH _3VdFyhGyW/* $FF was: 43VdFyhGyW*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC jtmZ09UbvY(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ggzO1bBwMH(0cL var0) {
      return var0.pbot;
   }

   private static Map OcQhdxtulq(0cL var0) {
      return var0.playerInfoMap;
   }

   private static 0bv VdUj46RQCQ() {
      return 0cd.webSkipper;
   }

   private static 0cC iYlKz4GR9o(0cL var0) {
      return var0.pbot;
   }

   public void handleEffect(SPacketEffect packetIn) {
      0dB var10000 = nzBiLrLjaQ(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փֲ֑֑֒֔փ");
      Object[] var10002 = new Object[12331 ^ -27566 ^ 32006 ^ -9859];
      var10002[4784 ^ -23480 ^ 26997 ^ -8307] = AYJuLiTCE7(this);
      var10002[16180 ^ -8145 ^ 20712 ^ -28686] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0cC bk7WROOFWy(0cL var0) {
      return var0.pbot;
   }

   private static Logger _hVoINOWYZ/* $FF was: 1hVoINOWYZ*/() {
      return LOGGER;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String l1jVoFIYsY(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 13992 ^ -29779 ^ 2173 ^ -19080; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13232 ^ -15103 ^ 25210 ^ -28356));
      }

      return var1.toString();
   }

   private static 0cC nyNPuwloRn(0cL var0) {
      return var0.pbot;
   }

   private static 0cC HiBQFgb9ES(0cL var0) {
      return var0.pbot;
   }

   private static 0bN VyZJPnsxfe(0bK var0) {
      return var0.moduleManager;
   }

   public void cleanup() {
      oB1MoRyDTc(this).setWorld((0cU)null);
   }

   private static 0cH BZvJAGgnSR(0cC var0) {
      return var0.mc;
   }

   private static 0cC QSLdWBmlrM(0cL var0) {
      return var0.pbot;
   }

   private static 0cC AYJuLiTCE7(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _NqbBENqFO/* $FF was: 7NqbBENqFO*/(0cL var0) {
      return var0.pbot;
   }

   private static void _rNStIt2JQ/* $FF was: 7rNStIt2JQ*/(0cD var0, int var1) {
      var0.dimension = var1;
   }

   private static InventoryPlayer yEDaKgIBIk(EntityPlayer var0) {
      return var0.inventory;
   }

   private static String[] zTmWdigRuX() {
      return SPacketChangeGameState.MESSAGE_NAMES;
   }

   private static PlayerCapabilities _KdvQbQTig/* $FF was: 5KdvQbQTig*/(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static double gY8rsHTgyx(EntityPlayer var0) {
      return var0.posX;
   }

   private static 0cH cVyQ2rSB6b(0cC var0) {
      return var0.mc;
   }

   private static 0cC FLgwiAC1tD(0cL var0) {
      return var0.pbot;
   }

   private static 0cH hDB9H2ylQs(0cC var0) {
      return var0.mc;
   }

   public void handleUpdateHealth(SPacketUpdateHealth packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, lO6fUFKEQA(xvNVqFmWZa(this)));
      endpo3Y1P7(NtNmOyWtKQ(this)).setPlayerSPHealth(packetIn.getHealth());
      WLqF74jcjN(9dBMeIrZ7C(this)).getFoodStats().setFoodLevel(packetIn.getFoodLevel());
      Tq5lriWIpJ(WaGPbQlkyH(this)).getFoodStats().setFoodSaturationLevel(packetIn.getSaturationLevel());
   }

   private static boolean KORNhTJsF5(0bv var0) {
      return var0.value;
   }

   private static 0cC dW1G92RDdv(0cL var0) {
      return var0.pbot;
   }

   private static 0dB _3FjJzgZlA/* $FF was: 13FjJzgZlA*/(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static void tn9EgrBeDl(EntityLivingBase var0, double var1) {
      var0.motionX = var1;
   }

   private static 0cH gtPD3Tr3cW(0cC var0) {
      return var0.mc;
   }

   private static 0cD soSqbvJWUj(0cC var0) {
      return var0.player;
   }

   private static 0cC gV15yyDU6x(0cL var0) {
      return var0.pbot;
   }

   private static 0cC bgiV5AGd7j(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Idct4IUt2p(0cL var0) {
      return var0.pbot;
   }

   private static 0cH hKG1RDyBM4(0cC var0) {
      return var0.mc;
   }

   private static void zVbo5lw07v(EntityOtherPlayerMP var0, double var1) {
      var0.prevPosZ = var1;
   }

   private static 0cC sKD67ZY11w(0cL var0) {
      return var0.pbot;
   }

   public void handleRespawn(SPacketRespawn packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 7V8DF0Jkh4(2Hb4iZnp2r(this)));
      if (packetIn.getDimensionID() != u44ghCsvoP(BPbRGn24N9(uWROJvdIe5(this)))) {
         oh9vqoD1b6(this, (boolean)(14573 ^ -22452 ^ 4168 ^ -32535));
         Scoreboard scoreboard = JFS30WbowM(this).getWorld().getScoreboard();
         WorldSettings ws = new WorldSettings(0L, packetIn.getGameType(), (boolean)(13467 ^ -14893 ^ 14144 ^ -14840), dOKtHjggDx(this).getWorld().getWorldInfo().isHardcoreModeEnabled(), packetIn.getWorldType());
         vFUqiF4ngo(k73BtoWJEt(this)).loadWorld(new 0cU(BQDsb9zzdo(this), this, ws, packetIn.getDimensionID(), packetIn.getDifficulty(), BF4CIO82tL(Ag72i2sKn4(SuabSUg9kO(this)))));
         qIBqTVnSv9(this).getWorld().setWorldScoreboard(scoreboard);
         JGHTt63dqA(43rw1NyHV6(o1iqcOiOBt(this)), packetIn.getDimensionID());
      }

      OPm78yn2Mz(Nq9INoLXqO(this)).setDimensionAndSpawnPlayer(packetIn.getDimensionID());
      IMNtQtRWnn(QgZV7N56Vd(YAbJjhqx52(this))).setGameType(packetIn.getGameType());
   }

   private static Container ZAYwaQAEmG(0cD var0) {
      return var0.openContainer;
   }

   private static boolean dSildyxTlF(0bv var0) {
      return var0.value;
   }

   private static 0cD aOIbWICv7g(0cC var0) {
      return var0.player;
   }

   private static 0cC o1iqcOiOBt(0cL var0) {
      return var0.pbot;
   }

   private static 0cD duKB2cRbI0(0cC var0) {
      return var0.player;
   }

   public void handleBlockBreakAnim(SPacketBlockBreakAnim packetIn) {
   }

   private static 0cP b7T5GjsP6q(0cL var0) {
      return var0.netManager;
   }

   private static boolean rnBLdohqoj(0bv var0) {
      return var0.value;
   }

   private static 0cC jmc9Vh9HmS(0cL var0) {
      return var0.pbot;
   }

   private static void _dSHZUNc9e/* $FF was: 4dSHZUNc9e*/(EntityArrow var0, Entity var1) {
      var0.shootingEntity = var1;
   }

   private static 0cH OPm78yn2Mz(0cC var0) {
      return var0.mc;
   }

   private static 0cC _eJSQPO7ga/* $FF was: 6eJSQPO7ga*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ynhemomnaj(0cL var0) {
      return var0.pbot;
   }

   private static 0cC dnRA8LakaG(0cL var0) {
      return var0.pbot;
   }

   private static int RlAX5vMC2l(Container var0) {
      return var0.windowId;
   }

   private static 0dB Bgr431TclP(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0bv djIWgl6Stf() {
      return 0cc.disconnect;
   }

   private static void OSj1nyUIsd(Entity var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static void j5Pdi6daKT(PlayerCapabilities var0, boolean var1) {
      var0.disableDamage = var1;
   }

   private static 0cC rcgGCaQm6A(0cL var0) {
      return var0.pbot;
   }

   private static 0cC otPGGDedam(0cL var0) {
      return var0.pbot;
   }

   private static 0cC SO4tRznvQS(0cL var0) {
      return var0.pbot;
   }

   private static SPacketPlayerPosLook.EnumFlags HrjDFQ81nn() {
      return SPacketPlayerPosLook.EnumFlags.Y;
   }

   private static float DswQWxvbG2(EntityPlayer var0) {
      return var0.rotationPitch;
   }

   private static 0cD t7Xp28UAwE(0cC var0) {
      return var0.player;
   }

   public void handlePlayerListHeaderFooter(SPacketPlayerListHeaderFooter packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, tmw2z1rn0O(woq0MfGOcr(this)));
      ijTVJSTtA4(9qT1QbakNh(this), packetIn.getHeader().getUnformattedText());
      NeRVmo3h8I(HWSrlY7jV0(this), packetIn.getFooter().getUnformattedText());
      0dB var10000 = 13FjJzgZlA(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֧֛֖֎֒օֻ֞քփֿ֖֒֓֒օֱ֘֘փ֒օ");
      Object[] var10002 = new Object[25076 ^ -15512 ^ 27539 ^ -14068];
      var10002[21515 ^ -16653 ^ 16000 ^ -11144] = odjvTLaw1g(this);
      var10002[4086 ^ -8682 ^ 21952 ^ -31711] = 9RuNhLswyD(NyYAea7Vzp(this));
      var10002[5702 ^ -9013 ^ 17904 ^ -28801] = F3DdXUVjLe(NY9j3fGBlo(this));
      var10000.invokeMethod(var10001, var10002);
      if (2sOYaLll0G().is(l1jVoFIYsY("ִ֣֖֖֕ևփ֖֔֟"))) {
         String[] var2 = 0cC.stripColor(tp1916Aguy(yTtGzNenX7(this))).split(l1jVoFIYsY("\u05fd"));
         int var3 = var2.length;

         for(int var4 = 22143 ^ -18670 ^ 31808 ^ -25299; var4 < var3; ++var4) {
            String line = var2[var4];
            if (line.toLowerCase().contains(l1jVoFIYsY("ǍǇǈưǇ")) && !line.toLowerCase().contains(l1jVoFIYsY("ǈƷǉǎǃǂǊǇ"))) {
               gBfcwQyWHj(this).sendMessage(line.split(l1jVoFIYsY("\u05cdח"))[13975 ^ -24071 ^ 1899 ^ -28668]);
            }
         }
      }

   }

   private static 0cC S9kQDts2Ud(0cL var0) {
      return var0.pbot;
   }

   private static 0cH _NaO1bG0sq/* $FF was: 7NaO1bG0sq*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC _dBMeIrZ7C/* $FF was: 9dBMeIrZ7C*/(0cL var0) {
      return var0.pbot;
   }

   private static void vb6zDvTRVB(0cL var0, int var1) {
      var0.currentServerMaxPlayers = var1;
   }

   private static double FZtr9G4two(EntityPlayer var0) {
      return var0.posY;
   }

   private static void _dw9kVA07L/* $FF was: 4dw9kVA07L*/(EntityLivingBase var0, double var1) {
      var0.motionY = var1;
   }

   private static 0bv vJ0DgsoNSA() {
      return 0cc.connecting;
   }

   private static boolean B2Wxjr6i6A(0bv var0) {
      return var0.value;
   }

   public Collection<NetworkPlayerInfo> getPlayerInfoMap() {
      return ByS4OGO2SG(this).values();
   }

   private static double fZaGNy4XSv(0cD var0) {
      return var0.posX;
   }

   private static 0cH D0YIMA2h6D(0cC var0) {
      return var0.mc;
   }

   private static 0dB lEB81QJGB6(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cH _tx6cmmOKe/* $FF was: 2tx6cmmOKe*/(0cC var0) {
      return var0.mc;
   }

   private static 0cH Bq2VqNPOe2(0cC var0) {
      return var0.mc;
   }

   private static 0cD agqxd151Wj(0cC var0) {
      return var0.player;
   }

   public void handleConfirmTransaction(SPacketConfirmTransaction packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, HSZrbSePUF(961iaeqidV(this)));
      Container container = null;
      EntityPlayer entityplayer = WyQ0260dik(ynhemomnaj(this));
      if (packetIn.getWindowId() == 0) {
         container = wOQYy6m7gt(entityplayer);
      } else if (packetIn.getWindowId() == hC9892nL66(pUdBW1ovq4(entityplayer))) {
         container = dZAgQZZJgO(entityplayer);
      }

      if (container != null && !packetIn.wasAccepted()) {
         this.sendPacket(new CPacketConfirmTransaction(packetIn.getWindowId(), packetIn.getActionNumber(), (boolean)(9689 ^ -31749 ^ 188 ^ -22881)));
      }

   }

   private static CPacketClientStatus.State FgnEF2K0rO() {
      return CPacketClientStatus.State.PERFORM_RESPAWN;
   }

   private static 0cD _SfIPBI4uK/* $FF was: 2SfIPBI4uK*/(0cC var0) {
      return var0.player;
   }

   private static 0cD all1aEjvNa(0cC var0) {
      return var0.player;
   }

   public void handleWorldBorder(SPacketWorldBorder packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, uOJB19QnB7(NpPAAvgVIX(this)));
      packetIn.apply(QcjYkxttLB(this).getWorld().getWorldBorder());
   }

   private static 0cC _M94QYpnG2/* $FF was: 2M94QYpnG2*/(0cL var0) {
      return var0.pbot;
   }

   private static double mCfnVINXgr(Entity var0) {
      return var0.posY;
   }

   private static 0cC goUoy0MQBD(0cL var0) {
      return var0.pbot;
   }

   private static Map _XjvcaMHC6/* $FF was: 4XjvcaMHC6*/(0cL var0) {
      return var0.playerInfoMap;
   }

   private static void oaqysQlxeU(0cD var0, double var1) {
      var0.prevPosY = var1;
   }

   private static 0cC gW3nC9YaSo(0cL var0) {
      return var0.pbot;
   }

   private static 0cC E0GLLv1rqP(0cL var0) {
      return var0.pbot;
   }

   private static 0cD LdQ70Q2OXT(0cC var0) {
      return var0.player;
   }

   private static SPacketPlayerPosLook.EnumFlags olgIyj6igG() {
      return SPacketPlayerPosLook.EnumFlags.X;
   }

   private static void ijTVJSTtA4(0cC var0, String var1) {
      var0.tabHeader = var1;
   }

   private static 0cD _YWWdj19wM/* $FF was: 6YWWdj19wM*/(0cC var0) {
      return var0.player;
   }

   private static 0cC yT9YQASrNf(0cL var0) {
      return var0.pbot;
   }

   private static Container pUdBW1ovq4(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cH q7aFQXQv8V(0cC var0) {
      return var0.mc;
   }

   private static SPacketPlayerPosLook.EnumFlags nEfV9j2jw6() {
      return SPacketPlayerPosLook.EnumFlags.X_ROT;
   }

   private static 0cC OVX1hwAooj(0cL var0) {
      return var0.pbot;
   }

   private static 0cC yTtGzNenX7(0cL var0) {
      return var0.pbot;
   }

   private static String[] teljyCRaEP() {
      return SPacketChangeGameState.MESSAGE_NAMES;
   }

   private static 0cH csucz2eI0t(0cC var0) {
      return var0.mc;
   }

   public void handleMultiBlockChange(SPacketMultiBlockChange packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, jIJGe6jqfK(ZHuYGwQix9(this)));
      SPacketMultiBlockChange.BlockUpdateData[] var2 = packetIn.getChangedBlocks();
      int var3 = var2.length;

      for(int var4 = 15586 ^ -11365 ^ 26633 ^ -30864; var4 < var3; ++var4) {
         SPacketMultiBlockChange.BlockUpdateData spacketmultiblockchange$blockupdatedata = var2[var4];
         HiBQFgb9ES(this).getWorld().invalidateRegionAndSetBlock(spacketmultiblockchange$blockupdatedata.getPos(), spacketmultiblockchange$blockupdatedata.getBlockState());
      }

   }

   private static long tyuHnWNW5X(Entity var0) {
      return var0.serverPosZ;
   }

   private static 0cD AOenrfbtxD(0cC var0) {
      return var0.player;
   }

   public void handleChangeGameState(SPacketChangeGameState packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 6j49TaZoF4(s5FbuU2Jps(this)));
      EntityPlayer entityplayer = qVYiHI26GS(rsxFV31i4o(this));
      int i = packetIn.getGameState();
      float f = packetIn.getValue();
      int j = MathHelper.floor(f + Float.intBitsToFloat(14135 ^ 26443 ^ 22585 ^ 1091044713 ^ 7177 ^ 1030749 ^ 21543 ^ 2114453343));
      if (i >= 0 && i < teljyCRaEP().length && zTmWdigRuX()[i] != null) {
         ((EntityPlayer)entityplayer).sendStatusMessage(new TextComponentTranslation(HW0BVdtT6W()[i], new Object[6683 ^ -28437 ^ 5760 ^ -25488]), (boolean)(2183 ^ -12649 ^ 18580 ^ -29052));
      }

      if (i == (20612 ^ -23431 ^ 7056 ^ -4244)) {
         HtM9TRZ0J0(this).getWorld().getWorldInfo().setRaining((boolean)(27268 ^ -11605 ^ 2924 ^ -19646));
         6DO7d5lbG2(this).getWorld().setRainStrength(Float.intBitsToFloat('\ue57e' ^ '볂' ^ 26808 ^ -1291036225 ^ 'ꎅ' ^ 'ﬕ' ^ 32444 ^ -1291039081));
      } else if (i == (2724 ^ -26700 ^ 11159 ^ -18811)) {
         Jonqe1OrBB(this).getWorld().getWorldInfo().setRaining((boolean)(32666 ^ -3799 ^ 2388 ^ -30745));
         4fBApSoKTw(this).getWorld().setRainStrength(Float.intBitsToFloat(32065 ^ 8334827 ^ '\uf1ae' ^ 2067773940 ^ '鮢' ^ 21017 ^ '픺' ^ 1153434737));
      } else if (i == (27176 ^ -795 ^ 8682 ^ -18652)) {
         KvJjJgml25(gOYSKPRgJU(PGR1JHLnGz(this))).setGameType(GameType.getByID(j));
      } else if (i == (8835 ^ -31618 ^ 18477 ^ -4396)) {
         if (j == 0) {
            AtHfjq5eXb(YlZgwEU54u(lGi2hoDdjh(this))).sendPacket(new CPacketClientStatus(FgnEF2K0rO()));
         }
      } else if (i == (396 ^ -8359 ^ 11311 ^ -3331)) {
         jtmZ09UbvY(this).getWorld().setRainStrength(f);
      } else if (i == (2538 ^ -28206 ^ 31766 ^ -7130)) {
         LA4UYgX1Tp(this).getWorld().setThunderStrength(f);
      }

   }

   private static Container BDW6jL6Bvm(EntityPlayer var0) {
      return var0.inventoryContainer;
   }

   private static 0cC YSC10OW1vW(0cL var0) {
      return var0.pbot;
   }

   private static 0cC NnhNon0D90(0cL var0) {
      return var0.pbot;
   }

   private static 0cC gD6Zje0A74(0cL var0) {
      return var0.pbot;
   }

   private static 0cC e4TCOAvRmJ(0cL var0) {
      return var0.pbot;
   }

   private static 0cH _G1BLFSUD5/* $FF was: 0G1BLFSUD5*/(0cC var0) {
      return var0.mc;
   }

   public void handleUpdateTileEntity(SPacketUpdateTileEntity packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, s9j2p2eYG6(r0lw1yUDAB(this)));
      if (4XYDjr47Uw(this).getWorld().isBlockLoaded(packetIn.getPos())) {
         TileEntity tileentity = KLj60XcexF(this).getWorld().getTileEntity(packetIn.getPos());
         int i = packetIn.getTileEntityType();
         boolean flag = i == (4714 ^ -18433 ^ 4551 ^ -19376) && tileentity instanceof TileEntityCommandBlock ? 20852 ^ -1320 ^ 19366 ^ -8181 : 7166 ^ -31649 ^ 18226 ^ -10093;
         if (i == (16136 ^ -23935 ^ 4761 ^ -28911) && tileentity instanceof TileEntityMobSpawner || flag != 0 || i == (22302 ^ -24491 ^ 28004 ^ -26068) && tileentity instanceof TileEntityBeacon || i == (20946 ^ -24087 ^ 24099 ^ -20964) && tileentity instanceof TileEntitySkull || i == (30041 ^ -20617 ^ 8173 ^ -14906) && tileentity instanceof TileEntityFlowerPot || i == (2541 ^ -2786 ^ 5667 ^ -5418) && tileentity instanceof TileEntityBanner || i == (18060 ^ -6286 ^ 13280 ^ -28135) && tileentity instanceof TileEntityStructure || i == (29198 ^ -15058 ^ 29439 ^ -14889) && tileentity instanceof TileEntityEndGateway || i == (1908 ^ -23375 ^ 6490 ^ -17770) && tileentity instanceof TileEntitySign || i == (14549 ^ -17907 ^ 15522 ^ -16784) && tileentity instanceof TileEntityShulkerBox || i == (8204 ^ -17690 ^ 18589 ^ -11652) && tileentity instanceof TileEntityBed) {
            tileentity.readFromNBT(packetIn.getNbtCompound());
         }
      }

   }

   private static 0cC uWROJvdIe5(0cL var0) {
      return var0.pbot;
   }

   private static 0cP p2FzVgVCNr(0cL var0) {
      return var0.netManager;
   }

   private static 0cC _bFWc7hdnc/* $FF was: 7bFWc7hdnc*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _q6iAev0dl/* $FF was: 4q6iAev0dl*/(0cL var0) {
      return var0.pbot;
   }

   private static WorldProvider a0vvSYLO6I(0cU var0) {
      return var0.provider;
   }

   private static 0cC _dVHSrMhJ5/* $FF was: 3dVHSrMhJ5*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cH rjuAFBlof6(0cC var0) {
      return var0.mc;
   }

   public void handleUpdateScore(SPacketUpdateScore packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, qFvLt1RlG9(bb4ede9y1z(this)));
      Scoreboard scoreboard = gQmj7pziSo(this).getWorld().getScoreboard();
      ScoreObjective scoreobjective = scoreboard.getObjective(packetIn.getObjectiveName());
      if (packetIn.getScoreAction() == 3NSFLuZr4Q()) {
         Score score = scoreboard.getOrCreateScore(packetIn.getPlayerName(), scoreobjective);
         score.setScorePoints(packetIn.getScoreValue());
      } else if (packetIn.getScoreAction() == jrPaVI2U8r()) {
         if (StringUtils.isNullOrEmpty(packetIn.getObjectiveName())) {
            scoreboard.removeObjectiveFromEntity(packetIn.getPlayerName(), (ScoreObjective)null);
         } else if (scoreobjective != null) {
            scoreboard.removeObjectiveFromEntity(packetIn.getPlayerName(), scoreobjective);
         }
      }

   }

   private static long zdQqBLNJrq(Entity var0) {
      return var0.serverPosX;
   }

   private static 0cC _OV9njHYES/* $FF was: 2OV9njHYES*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cH oUN269rUtS(0cC var0) {
      return var0.mc;
   }

   private static 0cD Tq5lriWIpJ(0cC var0) {
      return var0.player;
   }

   private static Map ByS4OGO2SG(0cL var0) {
      return var0.playerInfoMap;
   }

   private static 0cC OUdsHddjl3(0cL var0) {
      return var0.pbot;
   }

   private static 0cH AFojx1F4WP(0cC var0) {
      return var0.mc;
   }

   private static 0cC Jonqe1OrBB(0cL var0) {
      return var0.pbot;
   }

   private static double gYieIB3g46(Entity var0) {
      return var0.posX;
   }

   private static void _QxR12yWwy/* $FF was: 9QxR12yWwy*/(Entity var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static void _myNo1oGM7/* $FF was: 6myNo1oGM7*/(EntityOtherPlayerMP var0, double var1) {
      var0.lastTickPosY = var1;
   }

   private static 0cD rxgnUf27q0(0cC var0) {
      return var0.player;
   }

   private static int u44ghCsvoP(0cD var0) {
      return var0.dimension;
   }

   private static PlayerCapabilities G9iOizlwjg(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static 0cH QbMhSFKVaF(0cC var0) {
      return var0.mc;
   }

   private static ItemStack JvyAlAGdRy() {
      return ItemStack.EMPTY;
   }

   private static EnumHand ogqCGAsXMG() {
      return EnumHand.OFF_HAND;
   }

   public void handleCloseWindow(SPacketCloseWindow packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, IcomGba4jT(6eJSQPO7ga(this)));
      yBdBiwD9d9(Lwy8dO1nmB(this)).closeScreenAndDropStack();
   }

   private static 0cC qdllwdvb5t(0cL var0) {
      return var0.pbot;
   }

   private static 0cC AkabbZ2Uh4(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Oc7jdpEGgc(0cL var0) {
      return var0.pbot;
   }

   private static int FwB99abKvl(Container var0) {
      return var0.windowId;
   }

   private static 0cH qGBliT2Paw(0cC var0) {
      return var0.mc;
   }

   private static Container Kt54NAyIdM(EntityPlayer var0) {
      return var0.inventoryContainer;
   }

   private static long _Q5AW3V82n/* $FF was: 7Q5AW3V82n*/(Entity var0) {
      return var0.serverPosY;
   }

   private static 0cH gTQNKXvKGA(0cC var0) {
      return var0.mc;
   }

   private static 0cC QV6zTOWYWy(0cL var0) {
      return var0.pbot;
   }

   private static double DYtNJmENNf(EntityPlayer var0) {
      return var0.posZ;
   }

   private static 0cC Kl9uUxT2NM(0cL var0) {
      return var0.pbot;
   }

   private static void NLbyLVjbg6(EntityOtherPlayerMP var0, double var1) {
      var0.prevPosX = var1;
   }

   private static 0cD _oDANlY0uG/* $FF was: 7oDANlY0uG*/(0cC var0) {
      return var0.player;
   }

   private static 0cC Yyjst6NA12(0cL var0) {
      return var0.pbot;
   }

   private static 0cL AtHfjq5eXb(0cD var0) {
      return var0.connection;
   }

   private static 0cC OllbD7AUI0(0cL var0) {
      return var0.pbot;
   }

   private static String[] HW0BVdtT6W() {
      return SPacketChangeGameState.MESSAGE_NAMES;
   }

   private static PlayerCapabilities _abJJB4Wie/* $FF was: 5abJJB4Wie*/(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static 0cC woq0MfGOcr(0cL var0) {
      return var0.pbot;
   }

   private static 0cL tGVJAAZ35S(0cD var0) {
      return var0.connection;
   }

   private static 0cC _4183TnWSd/* $FF was: 64183TnWSd*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC hXydOXVLu8(0cL var0) {
      return var0.pbot;
   }

   public GameProfile getGameProfile() {
      return JQ1RvMcgGL(this);
   }

   private static 0cC DtvkpSd1Fb(0cL var0) {
      return var0.pbot;
   }

   private static 0cC vXiDunTUz3(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _XYDjr47Uw/* $FF was: 4XYDjr47Uw*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC J2wePSdKXL(0cL var0) {
      return var0.pbot;
   }

   private static 0dB xI1nSjmjgH(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cC MqoJQliT9N(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Lvb8lTKF9T(0cL var0) {
      return var0.pbot;
   }

   private static 0cC lZNNCfIjDq(0cL var0) {
      return var0.pbot;
   }

   private static 0cC FONrQW4AvN(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _fBApSoKTw/* $FF was: 4fBApSoKTw*/(0cL var0) {
      return var0.pbot;
   }

   private static void _ENcyKTbQn/* $FF was: 0ENcyKTbQn*/(Entity var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static Container b1Fm2NpyFs(0cD var0) {
      return var0.openContainer;
   }

   private static 0cC ZvoqvS0aQE(0cL var0) {
      return var0.pbot;
   }

   public void handleStatistics(SPacketStatistics packetIn) {
   }

   private static 0cC IPUbwI4oCg(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _FYdgWLVqx/* $FF was: 4FYdgWLVqx*/(0cL var0) {
      return var0.pbot;
   }

   private static void aSab7UaCUl(0cH var0, 0cQ var1) {
      var0.playerController = var1;
   }

   private static 0cC OKkLuO2j2n(0cL var0) {
      return var0.pbot;
   }

   public void handleEntityHeadLook(SPacketEntityHeadLook packetIn) {
   }

   private static 0cH oxSVtdNC3v(0cC var0) {
      return var0.mc;
   }

   private static SPacketPlayerPosLook.EnumFlags _6kMwTpLXH/* $FF was: 66kMwTpLXH*/() {
      return SPacketPlayerPosLook.EnumFlags.Z;
   }

   private static 0cC h62gdUBgax(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _dnVAwmVWe/* $FF was: 2dnVAwmVWe*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC rNxvoPcgFo(0cL var0) {
      return var0.pbot;
   }

   private static 0cQ KvJjJgml25(0cH var0) {
      return var0.playerController;
   }

   private static 0bN hWjImQEvBj(0bK var0) {
      return var0.moduleManager;
   }

   private static 0cC _W4o9pBa9q/* $FF was: 2W4o9pBa9q*/(0cL var0) {
      return var0.pbot;
   }

   private static 0dB Rmcnij0B8R(0bK var0) {
      return var0.pBotsScriptManager;
   }

   public void handleExplosion(SPacketExplosion packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, hKG1RDyBM4(IPUbwI4oCg(this)));
      Explosion explosion = new Explosion(o9GQou9sK4(this).getWorld(), (Entity)null, packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getStrength(), packetIn.getAffectedBlockPositions());
      explosion.doExplosionB((boolean)(5455 ^ -17812 ^ 10276 ^ -30970));
      0cD var10000 = Dbk6ba4GVp(iYlKz4GR9o(this));
      RL94yVTKg9(var10000, zbtSLDam2U(var10000) + (double)packetIn.getMotionX());
      var10000 = oqz89u1IFN(1XvzT2gbml(this));
      HopMitQlDJ(var10000, 2NWsOILlqY(var10000) + (double)packetIn.getMotionY());
      var10000 = all1aEjvNa(v4T8YaSYo6(this));
      L8i83inE1L(var10000, FOOyicGnBn(var10000) + (double)packetIn.getMotionZ());
   }

   private static 0by _sOYaLll0G/* $FF was: 2sOYaLll0G*/() {
      return 0cd.antibot;
   }

   private static 0cC _yAZNjq2j6/* $FF was: 4yAZNjq2j6*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _FfLVc9Qvt/* $FF was: 7FfLVc9Qvt*/(0cL var0) {
      return var0.pbot;
   }

   private static void C9TU7YeEwB(EntityOtherPlayerMP var0, double var1) {
      var0.lastTickPosX = var1;
   }

   private static 0cC jW4h79oSey(0cL var0) {
      return var0.pbot;
   }

   private static void B76EDgnfdN(Entity var0, long var1) {
      var0.serverPosZ = var1;
   }

   private static void gBVOIvRdhq(0cC var0, String var1) {
      var0.windowTitle = var1;
   }

   private static 0cC y9qLxiYLNB(0cL var0) {
      return var0.pbot;
   }

   public void handleSpawnPainting(SPacketSpawnPainting packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 3isA1s7Brs(FONrQW4AvN(this)));
      EntityPainting entitypainting = new EntityPainting(yT9YQASrNf(this).getWorld(), packetIn.getPosition(), packetIn.getFacing(), packetIn.getTitle());
      entitypainting.setUniqueId(packetIn.getUniqueId());
      VpOjSMFwhd(this).getWorld().addEntityToWorld(packetIn.getEntityID(), entitypainting);
   }

   private static Container tUJIljnvsk(0cD var0) {
      return var0.openContainer;
   }

   private static void aKCyv2hdIm(Entity var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0cH Tb7araJZdw(0cC var0) {
      return var0.mc;
   }

   private static 0cH IcomGba4jT(0cC var0) {
      return var0.mc;
   }

   public void handleEntityAttach(SPacketEntityAttach packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, D0YIMA2h6D(AZnqALrSBT(this)));
      Entity entity = 4FYdgWLVqx(this).getWorld().getEntityByID(packetIn.getEntityId());
      Entity entity1 = VI60iyDEtG(this).getWorld().getEntityByID(packetIn.getVehicleEntityId());
      if (entity instanceof EntityLiving) {
         if (entity1 != null) {
            ((EntityLiving)entity).setLeashHolder(entity1, (boolean)(32012 ^ -14780 ^ 7695 ^ -23225));
         } else {
            ((EntityLiving)entity).clearLeashed((boolean)(23729 ^ -19303 ^ 257 ^ -5847), (boolean)(32132 ^ -21066 ^ 29092 ^ -24170));
         }
      }

   }

   private static 0cH qFvLt1RlG9(0cC var0) {
      return var0.mc;
   }

   private static 0cC SuabSUg9kO(0cL var0) {
      return var0.pbot;
   }

   private static InventoryPlayer _pJSirgQ9z/* $FF was: 2pJSirgQ9z*/(EntityPlayer var0) {
      return var0.inventory;
   }

   private static 0cH TF6GjxzdFe(0cC var0) {
      return var0.mc;
   }

   private static 0cC WowSzV3itD(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ija2w5zXWQ(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _Hb4iZnp2r/* $FF was: 2Hb4iZnp2r*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC t2HSpCgm99(0cL var0) {
      return var0.pbot;
   }

   private static 0cC OI1R8jDFoq(0cL var0) {
      return var0.pbot;
   }

   public void handleChat(SPacketChat packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, QbEkadWmHd(d3J5LYWf0j(this)));
      if (packetIn.getType() != VbpQAs4o18()) {
         0dB var10000 = ifmSyN1i6A(0bK.getInstance());
         String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փִ֖֟փ");
         Object[] var10002 = new Object[19646 ^ -28059 ^ 10158 ^ -1673];
         var10002[16747 ^ -16293 ^ 11630 ^ -21410] = WowSzV3itD(this);
         var10002[7381 ^ -3890 ^ 9141 ^ -12369] = packetIn;
         var10000.invokeMethod(var10001, var10002);
         String message = packetIn.getChatComponent().getFormattedText();
         if (rnBLdohqoj(8OODjKIeLP())) {
            0dK.formatMsg(l1jVoFIYsY("ǐǇƵחב֓ב֛") + 3dVHSrMhJ5(this).getNickname() + l1jVoFIYsY("ב֑ב֛חבօ") + message);
         }

         String cleanedMessage = 0cC.stripColor(message);
         lZNNCfIjDq(this).setParameter(l1jVoFIYsY("֛֖քփ֚֒քք֖\u0590֒"), cleanedMessage);
         if (cleanedMessage.contains(piFv7AYFV2(ZhD0jwQi8s()))) {
            0cR.trigger(2faaaqylkq(this), l1jVoFIYsY("ֺ֘֙֒քք֖\u0590֒"));
         }

         if (VyZJPnsxfe(0bK.getInstance()).getModule(0bZ.class).isModuleState() && !I6eyy9xFYL(this).getBooleanParameter(l1jVoFIYsY("֖ւփ֟֘օ֞֍֖փ֞֘֙")) && (cleanedMessage.contains(l1jVoFIYsY("טօ֒\u0590")) || cleanedMessage.contains(l1jVoFIYsY("ǀǇƷǂǄǂƶƵƷ")) || cleanedMessage.contains(l1jVoFIYsY("ט֛")) || cleanedMessage.contains(l1jVoFIYsY("ǇǅƵǉƷǏǀǏƷ")) && !cleanedMessage.contains(l1jVoFIYsY("ƴǁǂ")))) {
            gqijo1h0S6(this).auth();
         }

         String answer;
         if (KORNhTJsF5(VdUj46RQCQ()) && cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֟փփև"))) {
            if (cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֖֙փ֞֕֘փ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֞֓\u05ca")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֖֔ևփ֖֔֟")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("փ\u05ca"))) {
               0bL.getInstance().getProxyManager().removeProxy(jmc9Vh9HmS(this).getProxy());
               0dK.formatMsg(l1jVoFIYsY("ǩƵǍǌƹưǂǊǏǂחב֓ב֛") + Kl9uUxT2NM(this).getNickname() + l1jVoFIYsY("ב֑ב֛ח֠֒֕ח֤֜֞ևև֒օח֖֛֒֙֕֒֓"));
               aGGZcjB05y(this).stopBot();
            }
         } else if (hWjImQEvBj(0bK.getInstance()).getModule(0cf.class).isModuleState() && !JOilVxbC6F(this).getBooleanParameter(l1jVoFIYsY("ր֒֕֓֒փ֒֔փ֒֓")) && cleanedMessage.contains(l1jVoFIYsY("֟փփև")) && (cleanedMessage.contains(l1jVoFIYsY("֖֙փ֞֕֘փ")) || cleanedMessage.contains(l1jVoFIYsY("֖֔ևփ֖֔֟")) || cleanedMessage.contains(l1jVoFIYsY("֞֓\u05ca")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("փ\u05ca")))) {
            answer = cleanedMessage.split(l1jVoFIYsY("\u05cdטט"))[18584 ^ -29852 ^ 13880 ^ -2619];
            String url = l1jVoFIYsY("֟փփևք\u05cdטט") + answer.split(l1jVoFIYsY("ח"))[15325 ^ -20178 ^ 14576 ^ -19965];
            nvyIeW9NyF(this).setParameter(l1jVoFIYsY("ր֒֕֓֒փ֒֔փ֒֓"), Boolean.valueOf((boolean)(28942 ^ -23736 ^ 28194 ^ -17307)));
            0dK.formatMsg(l1jVoFIYsY("ֳ֒փ֒֔փ֒֓חִ֖֠֒֕ևփֻ֖֢֥֔֟ח\u058bחֵָ֣\u05cdחב֓ב֛") + JVIEIyJOvi(this).getNickname() + l1jVoFIYsY("ב֑ב֛\u05cdחֻ֢֥\u05cdח") + url + l1jVoFIYsY("חב֑ב֛֧օ֘֏֎\u05cdחב֓ב֛") + JNI6nQnOaG(this).getProxy().getType().name().toLowerCase() + l1jVoFIYsY("\u05cdטט") + xFBHtSitFS(this).getProxy().getProxy());
            T22In7ZQPT(0bK.getInstance()).addQueue(new 0dF(url, Swrp7BwFDP(this).getProxy(), bIw01VwVl2(this).getNickname()));
         }

         if ((cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǈƷǉǅǂƷǍǇ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("Ǎǉǃ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǍǇǈưǇ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֖֔ևփ֖֔֟")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("֑֛֞փ֒օ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ƶחǍǇƷƵǏǊǍǏ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǅƼחǅǅǂǌǏ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ƴחǅǇƶ"))) && (cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǊǂǅǂƷǊǉ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǊǂǈƷǇǅǏǌƻǊǉ")) || cleanedMessage.toLowerCase().contains(l1jVoFIYsY("ǂƾǂחƷǇǀ")))) {
            6Oqf2LA4P2(this).setParameter(l1jVoFIYsY("֖֔ևփ֖֔֟֓֒փ֒֔փ֒֓"), Boolean.valueOf((boolean)(23012 ^ -13732 ^ 11846 ^ -16898)));
         }

         if (mQ2s2lW9wI().is(l1jVoFIYsY("ּ֒֎֖֕֘օִ֛֓֞֔֜")) && cleanedMessage.contains(l1jVoFIYsY("ǊǇǁǋǏחǊǇ")) && cleanedMessage.contains(l1jVoFIYsY("ƱǏƳƷƴ")) && cleanedMessage.contains(l1jVoFIYsY("ǍǌǇǅǏǇƵƴƷ"))) {
            answer = cleanedMessage.replaceAll(l1jVoFIYsY("ׇ֬֩ך\u05ce֪"), l1jVoFIYsY(""));
            if (answer.length() > 0) {
               hdbllwVYnA(this).changeSlot(Integer.parseInt(answer) - (27452 ^ -26427 ^ 5925 ^ -6947));
               OVX1hwAooj(this).useItem();
            }
         }

         if (bQLvQe9ory().is(l1jVoFIYsY("ֺ֖փִ֛֟֞֔֜")) && cleanedMessage.contains(l1jVoFIYsY("ǥǇƷǏǇǊƵƼחǉƵǅǂƵǉǅ\u05cd"))) {
            answer = message.split(l1jVoFIYsY("חח"))[25153 ^ -27811 ^ 15634 ^ -13297];
            Lvb8lTKF9T(this).sendMessage(l1jVoFIYsY("י") + answer);
         }

      }
   }

   private static long _dADUna1vI/* $FF was: 1dADUna1vI*/(Entity var0) {
      return var0.serverPosZ;
   }

   private static 0cD _3rw1NyHV6/* $FF was: 43rw1NyHV6*/(0cC var0) {
      return var0.player;
   }

   public void processChunkUnload(SPacketUnloadChunk packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, dd0jQOpoGo(YaJAxeLis6(this)));
      UWzRhXIFSa(this).getWorld().doPreChunk(packetIn.getX(), packetIn.getZ(), (boolean)(23505 ^ -14099 ^ 4980 ^ -32696));
   }

   private static SPacketTitle.Type _1Dy7ehsgr/* $FF was: 11Dy7ehsgr*/() {
      return SPacketTitle.Type.SUBTITLE;
   }

   private static void f69qs5rqjy(EntityPlayer var0, double var1) {
      var0.motionY = var1;
   }

   private static String piFv7AYFV2(0bA var0) {
      return var0.text;
   }

   private static 0dB LlKY8hbX29(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cD an2JyLOjqz(0cC var0) {
      return var0.player;
   }

   private static 0cC CPDOSFdN7F(0cL var0) {
      return var0.pbot;
   }

   public void handleSetExperience(SPacketSetExperience packetIn) {
      WTLaFgSLvK(sJJMls1LE6(this)).setXPStats(packetIn.getExperienceBar(), packetIn.getTotalExperience(), packetIn.getLevel());
   }

   private static 0cC _el7tBeHvf/* $FF was: 0el7tBeHvf*/(0cL var0) {
      return var0.pbot;
   }

   private static String F3DdXUVjLe(0cC var0) {
      return var0.tabFooter;
   }

   public void handleEntityMovement(SPacketEntity packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, gTQNKXvKGA(z669ONGUsr(this)));
      Entity entity = packetIn.getEntity(vBowWVRgOw(this).getWorld());
      if (entity != null) {
         QJnjvgScx3(entity, zdQqBLNJrq(entity) + (long)packetIn.getX());
         7FSIt3BgFI(entity, 7Q5AW3V82n(entity) + (long)packetIn.getY());
         B76EDgnfdN(entity, tyuHnWNW5X(entity) + (long)packetIn.getZ());
         double d0 = (double)rdXJp85my6(entity) / Double.longBitsToDouble(1188049663423427429L ^ 5822253679987667813L);
         double d1 = (double)VnBWkeG6hV(entity) / Double.longBitsToDouble(-5707598127504091006L ^ -1118430107213555582L);
         double d2 = (double)1dADUna1vI(entity) / Double.longBitsToDouble(-357373356730202774L ^ -4919519779256515222L);
         if (!entity.canPassengerSteer()) {
            float f = packetIn.isRotating() ? (float)(packetIn.getYaw() * (21623 ^ -29103 ^ 16338 ^ -7012)) / Float.intBitsToFloat(13847 ^ 494399 ^ 5409 ^ 982773273 ^ 14867 ^ 475214 ^ '\ue1b1' ^ 2031345148) : ITGHQE0lX7(entity);
            float f1 = packetIn.isRotating() ? (float)(packetIn.getPitch() * (7642 ^ -30308 ^ 18131 ^ -11267)) / Float.intBitsToFloat(233683 ^ 212761 ^ 6382 ^ 1734699799 ^ 236968 ^ 24105 ^ 245036 ^ 619029150) : 9NnLGolAwj(entity);
            entity.setPositionAndRotation(d0, d1, d2, f, f1);
            1sG5IynkGo(entity, packetIn.getOnGround());
         }
      }

   }

   private static 0cC Znhd75qSrV(0cL var0) {
      return var0.pbot;
   }

   private static 0cC NpPAAvgVIX(0cL var0) {
      return var0.pbot;
   }

   public void handleDestroyEntities(SPacketDestroyEntities packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 0G1BLFSUD5(otPGGDedam(this)));

      for(int i = 3585 ^ -11151 ^ 28762 ^ -21974; i < packetIn.getEntityIDs().length; ++i) {
         OUdsHddjl3(this).getWorld().removeEntityFromWorld(packetIn.getEntityIDs()[i]);
      }

   }

   private static 0cC bIw01VwVl2(0cL var0) {
      return var0.pbot;
   }

   private static 0cC awo0Ft9H2V(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _rICAN7ptz/* $FF was: 2rICAN7ptz*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cH QbEkadWmHd(0cC var0) {
      return var0.mc;
   }

   private static 0cC _i71SvrhCD/* $FF was: 2i71SvrhCD*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC o9GQou9sK4(0cL var0) {
      return var0.pbot;
   }

   public void handleKeepAlive(SPacketKeepAlive packetIn) {
      this.sendPacket(new CPacketKeepAlive(packetIn.getId()));
      T7rdPV1tBy(this).botTick();
   }

   private static void oh9vqoD1b6(0cL var0, boolean var1) {
      var0.doneLoadingTerrain = var1;
   }

   public void handleCooldown(SPacketCooldown packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, vgYNa7WQjE(0QGFz62A2l(this)));
      if (packetIn.getTicks() == 0) {
         L69gNlVON2(bk7WROOFWy(this)).getCooldownTracker().removeCooldown(packetIn.getItem());
      } else {
         FX1FwW5ltr(6DtIAa2bIJ(this)).getCooldownTracker().setCooldown(packetIn.getItem(), packetIn.getTicks());
      }

   }

   private static 0cD Dbk6ba4GVp(0cC var0) {
      return var0.player;
   }

   private static 0cD endpo3Y1P7(0cC var0) {
      return var0.player;
   }

   public void handleBlockAction(SPacketBlockAction packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, BZvJAGgnSR(gGtQFvKvbN(this)));
      2M94QYpnG2(this).getWorld().addBlockEvent(packetIn.getBlockPosition(), packetIn.getBlockType(), packetIn.getData1(), packetIn.getData2());
   }

   private static 0cH bgjgqW40G5(0cC var0) {
      return var0.mc;
   }

   private static 0cC s5FbuU2Jps(0cL var0) {
      return var0.pbot;
   }

   private static 0cC nnyZItbIoZ(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _9tKGqHI6N/* $FF was: 29tKGqHI6N*/(0cL var0) {
      return var0.pbot;
   }

   private static int[] evan6UO9ZO() {
      return null.$SwitchMap$net$minecraft$network$play$server$SPacketPlayerListItem$Action;
   }

   private static String _RuNhLswyD/* $FF was: 9RuNhLswyD*/(0cC var0) {
      return var0.tabHeader;
   }

   public void handleChunkData(SPacketChunkData packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, oUN269rUtS(wd7CJQWPgZ(this)));
      if (packetIn.isFullChunk()) {
         Vc6AdlTQWI(this).getWorld().doPreChunk(packetIn.getChunkX(), packetIn.getChunkZ(), (boolean)(12596 ^ -29391 ^ 19345 ^ -2155));
      }

      Chunk chunk = CoFEWcS7lA(this).getWorld().getChunk(packetIn.getChunkX(), packetIn.getChunkZ());
      chunk.read(packetIn.getReadBuffer(), packetIn.getExtractedSize(), packetIn.isFullChunk());
      VBdBVMVxkx(this).getWorld().markBlockRangeForRenderUpdate(packetIn.getChunkX() << (10699 ^ -8940 ^ 4112 ^ -6965), 20175 ^ -21252 ^ 7043 ^ -1616, packetIn.getChunkZ() << (24324 ^ -8195 ^ 20556 ^ -12111), (packetIn.getChunkX() << (17724 ^ -240 ^ 12338 ^ -30182)) + (27596 ^ -7842 ^ 27499 ^ -7690), 23301 ^ -6961 ^ 19159 ^ -3043, (packetIn.getChunkZ() << (21137 ^ -28893 ^ 8121 ^ -15857)) + (14614 ^ -24731 ^ 2242 ^ -20802));
      if (!packetIn.isFullChunk() || !(a0vvSYLO6I(9PAJMYTjWq(this).getWorld()) instanceof WorldProviderSurface)) {
         chunk.resetRelightChecks();
      }

      Iterator var3 = packetIn.getTileEntityTags().iterator();

      while(var3.hasNext()) {
         NBTTagCompound nbttagcompound = (NBTTagCompound)var3.next();
         BlockPos blockpos = new BlockPos(nbttagcompound.getInteger(l1jVoFIYsY("֏")), nbttagcompound.getInteger(l1jVoFIYsY("֎")), nbttagcompound.getInteger(l1jVoFIYsY("֍")));
         TileEntity tileentity = QpQXntnI7f(this).getWorld().getTileEntity(blockpos);
         if (tileentity != null) {
            tileentity.readFromNBT(nbttagcompound);
         }
      }

   }

   private static 0cC li8TrE9Rml(0cL var0) {
      return var0.pbot;
   }

   private static 0cC rsxFV31i4o(0cL var0) {
      return var0.pbot;
   }

   private static long VnBWkeG6hV(Entity var0) {
      return var0.serverPosY;
   }

   private static Container DD8VySODiJ(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cH _Y7gxgaWdW/* $FF was: 7Y7gxgaWdW*/(0cC var0) {
      return var0.mc;
   }

   private static 0cD nx3DyoAG96(0cC var0) {
      return var0.player;
   }

   private static 0cC _DtIAa2bIJ/* $FF was: 6DtIAa2bIJ*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC k73BtoWJEt(0cL var0) {
      return var0.pbot;
   }

   public void handleJoinGame(SPacketJoinGame packetIn) {
      0bL.getInstance().getCaptchaManager().removeCaptcha(ipOxbODm99(this));
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, AFojx1F4WP(9p4jJV1M5a(this)));
      aSab7UaCUl(cVyQ2rSB6b(OllbD7AUI0(this)), new 0cQ(3IvQsPfNAP(this)));
      WorldSettings ws = new WorldSettings(0L, packetIn.getGameType(), (boolean)(8336 ^ -28679 ^ 17144 ^ -4719), packetIn.isHardcoreMode(), packetIn.getWorldType());
      wyTyGAd2N8(68ELvZ7HS4(this)).loadWorld(new 0cU(AkabbZ2Uh4(this), this, ws, packetIn.getDimension(), packetIn.getDifficulty(), IlW447G6WJ(vGvoamTHsg(nnyZItbIoZ(this)))));
      7rNStIt2JQ(agqxd151Wj(2dnVAwmVWe(this)), packetIn.getDimension());
      an2JyLOjqz(QgsFStqiB9(this)).setEntityId(packetIn.getPlayerId());
      vb6zDvTRVB(this, packetIn.getMaxPlayers());
      YhqkO0iY3w(aizwksBWrX(this)).setReducedDebug(packetIn.isReducedDebugInfo());
      viT2GiqXHC(8elQFQKYJw(7V671TPVVP(this))).setGameType(packetIn.getGameType());
      GHXW9QdWFa(AdqQeQ9SrD(this)).sendSettingsToServer();
      MAOIgNqkHa(this).sendPacket(new CPacketCustomPayload(l1jVoFIYsY("ִֺ\u058bֵօ֖֙֓"), (new PacketBuffer(Unpooled.buffer())).writeString(ClientBrandRetriever.getClientModName())));
      0dB var10000 = xI1nSjmjgH(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փְֽ֖֚֘֞֙֒");
      Object[] var10002 = new Object[10044 ^ -13231 ^ 5569 ^ -338];
      var10002[16055 ^ -27295 ^ 20414 ^ -7064] = QV6zTOWYWy(this);
      var10002[18791 ^ -12147 ^ 12227 ^ -18904] = packetIn;
      var10000.invokeMethod(var10001, var10002);
      if (B2Wxjr6i6A(vJ0DgsoNSA())) {
         0dK.formatMsg(l1jVoFIYsY("ǦǉƵחב֓ב֛") + VhabVHOZTV(this).getNickname() + l1jVoFIYsY("ב֑ב֛חǨǉǃǍǌƹưǏǌƶƸחǍחƶǂƷǅǂƷƴז"));
      }

      0cR.trigger(QJSBC93j7W(this), l1jVoFIYsY("ֽ֘֙֘֞֙"));
   }

   private static 0cC _zLLZUtGyn/* $FF was: 2zLLZUtGyn*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cH vFUqiF4ngo(0cC var0) {
      return var0.mc;
   }

   public void handleEntityProperties(SPacketEntityProperties packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 7NaO1bG0sq(OdhNMqze5a(this)));
      Entity entity = TuNTl1K2lg(this).getWorld().getEntityByID(packetIn.getEntityId());
      if (entity != null) {
         if (!(entity instanceof EntityLivingBase)) {
            throw new IllegalStateException(l1jVoFIYsY("֤֒օց֒օחփօ֞֒֓חփ֘חւև֖֓փ֒ח֖փփօ֞֕ւփ֒քח֑֘ח֖ח֙֘֙ך֛֞ց֞֙\u0590ח֒֙փ֞փ֎חן֖֔փւ֖֛֛֎\u05cdח") + entity + l1jVoFIYsY("מ"));
         }

         AbstractAttributeMap abstractattributemap = ((EntityLivingBase)entity).getAttributeMap();
         Iterator var4 = packetIn.getSnapshots().iterator();

         while(var4.hasNext()) {
            SPacketEntityProperties.Snapshot spacketentityproperties$snapshot = (SPacketEntityProperties.Snapshot)var4.next();
            IAttributeInstance iattributeinstance = abstractattributemap.getAttributeInstanceByName(spacketentityproperties$snapshot.getName());
            if (iattributeinstance == null) {
               iattributeinstance = abstractattributemap.registerAttribute(new RangedAttribute((IAttribute)null, spacketentityproperties$snapshot.getName(), Double.longBitsToDouble(1077000365120270561L ^ 1072496765492900065L), Double.longBitsToDouble(4459829305385908719L ^ 4464332905013279215L), Double.longBitsToDouble(-8232598338989485291L ^ -986270098237920022L)));
            }

            iattributeinstance.setBaseValue(spacketentityproperties$snapshot.getBaseValue());
            iattributeinstance.removeAllModifiers();
            Iterator var7 = spacketentityproperties$snapshot.getModifiers().iterator();

            while(var7.hasNext()) {
               AttributeModifier attributemodifier = (AttributeModifier)var7.next();
               iattributeinstance.applyModifier(attributemodifier);
            }
         }
      }

   }

   private static 0cC qBxnIiG7He(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ZHuYGwQix9(0cL var0) {
      return var0.pbot;
   }

   private static 0cC xFBHtSitFS(0cL var0) {
      return var0.pbot;
   }

   private static 0cQ IMNtQtRWnn(0cH var0) {
      return var0.playerController;
   }

   private static 0cC nvyIeW9NyF(0cL var0) {
      return var0.pbot;
   }

   private static void JGHTt63dqA(0cD var0, int var1) {
      var0.dimension = var1;
   }

   private static Map allWF9dFRX(0cL var0) {
      return var0.playerInfoMap;
   }

   private static 0cC JK42fYh736(0cL var0) {
      return var0.pbot;
   }

   private static 0by mQ2s2lW9wI() {
      return 0cd.antibot;
   }

   private static 0cD WwjreRF5W7(0cC var0) {
      return var0.player;
   }

   public void handleSpawnGlobalEntity(SPacketSpawnGlobalEntity packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, csucz2eI0t(LhlkASRhIH(this)));
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      Entity entity = null;
      if (packetIn.getType() == (13129 ^ -20100 ^ 13021 ^ -20247)) {
         entity = new EntityLightningBolt(KTbdIoQBOZ(this).getWorld(), d0, d1, d2, (boolean)(10780 ^ -15837 ^ 15253 ^ -11350));
      }

      if (entity != null) {
         9QxR12yWwy(entity, Float.intBitsToFloat(106272 ^ 103352 ^ 951 ^ -1710967817 ^ 21280 ^ '갱' ^ 12920 ^ -1710984783));
         B2KSo1VQSR(entity, Float.intBitsToFloat(517520 ^ 495556 ^ 7053 ^ -2100585025 ^ 501441 ^ 504355 ^ 19187 ^ -2100579721));
         ((Entity)entity).setEntityId(packetIn.getEntityId());
         rNxvoPcgFo(this).getWorld().addWeatherEffect(entity);
      }

   }

   private static 0cC hdbllwVYnA(0cL var0) {
      return var0.pbot;
   }

   private static 0cD oqz89u1IFN(0cC var0) {
      return var0.player;
   }

   private static SPacketPlayerPosLook.EnumFlags JjmQY3q8G4() {
      return SPacketPlayerPosLook.EnumFlags.Y_ROT;
   }

   private static 0cH _ify4AYaTg/* $FF was: 5ify4AYaTg*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC J6aZxt91P6(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _Oqf2LA4P2/* $FF was: 6Oqf2LA4P2*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC qUFyhGeSvi(0cL var0) {
      return var0.pbot;
   }

   private static 0dB gg0PIogUzD(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static void DYAducE1Bm(PlayerCapabilities var0, boolean var1) {
      var0.isCreativeMode = var1;
   }

   private static void OU2AlVrMeB(Entity var0, boolean var1) {
      var0.onGround = var1;
   }

   private static 0cH wyTyGAd2N8(0cC var0) {
      return var0.mc;
   }

   private static 0cL wrMoggrAlY(0cD var0) {
      return var0.connection;
   }

   private static 0dB ifmSyN1i6A(0bK var0) {
      return var0.pBotsScriptManager;
   }

   public void handleRemoveEntityEffect(SPacketRemoveEntityEffect packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, EeyUK1yeQJ(FjUpcgxvW9(this)));
      Entity entity = packetIn.getEntity(oOt99gNodv(this).getWorld());
      if (entity instanceof EntityLivingBase) {
         ((EntityLivingBase)entity).removeActivePotionEffect(packetIn.getPotion());
      }

   }

   private static 0cC VTIRtHB7WD(0cL var0) {
      return var0.pbot;
   }

   private static 0cH mYijto7b2i(0cC var0) {
      return var0.mc;
   }

   private static 0cC QGY1TPaIyb(0cL var0) {
      return var0.pbot;
   }

   private static 0cH VV9Wa8XxvV(0cC var0) {
      return var0.mc;
   }

   private static 0cH _76XrtcTGh/* $FF was: 276XrtcTGh*/(0cC var0) {
      return var0.mc;
   }

   public void handleTeams(SPacketTeams packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, Bq2VqNPOe2(NSqw68d3ex(this)));
      Scoreboard scoreboard = sNHrLaYhjx(this).getWorld().getScoreboard();
      ScorePlayerTeam scoreplayerteam = scoreboard.getTeam(packetIn.getName());
      if (packetIn.getAction() == 0 && scoreplayerteam == null) {
         scoreplayerteam = scoreboard.createTeam(packetIn.getName());
      }

      if (scoreplayerteam != null) {
         scoreplayerteam.setDisplayName(packetIn.getDisplayName());
         scoreplayerteam.setPrefix(packetIn.getPrefix());
         scoreplayerteam.setSuffix(packetIn.getSuffix());
         scoreplayerteam.setColor(TextFormatting.fromColorIndex(packetIn.getColor()));
         scoreplayerteam.setFriendlyFlags(packetIn.getFriendlyFlags());
         Team.EnumVisible team$enumvisible = Team.EnumVisible.getByName(packetIn.getNameTagVisibility());
         if (team$enumvisible != null) {
            scoreplayerteam.setNameTagVisibility(team$enumvisible);
         }

         Team.CollisionRule team$collisionrule = Team.CollisionRule.getByName(packetIn.getCollisionRule());
         if (team$collisionrule != null) {
            scoreplayerteam.setCollisionRule(team$collisionrule);
         }
      }

      Iterator var6;
      String s1;
      if (packetIn.getAction() == 0 || packetIn.getAction() == (2596 ^ -18585 ^ 13883 ^ -29829)) {
         var6 = packetIn.getPlayers().iterator();

         while(var6.hasNext()) {
            s1 = (String)var6.next();
            scoreboard.addPlayerToTeam(s1, packetIn.getName());
         }
      }

      if (packetIn.getAction() == (14048 ^ -27693 ^ 21946 ^ -3955)) {
         var6 = packetIn.getPlayers().iterator();

         while(var6.hasNext()) {
            s1 = (String)var6.next();
            if (scoreplayerteam != null && scoreboard.getPlayersTeam(s1) == scoreplayerteam) {
               scoreboard.removePlayerFromTeam(s1, scoreplayerteam);
            }
         }
      }

   }

   private static 0cC _QGFz62A2l/* $FF was: 0QGFz62A2l*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _V671TPVVP/* $FF was: 7V671TPVVP*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC NyYAea7Vzp(0cL var0) {
      return var0.pbot;
   }

   private static 0cH jIJGe6jqfK(0cC var0) {
      return var0.mc;
   }

   private static 0cH uOJB19QnB7(0cC var0) {
      return var0.mc;
   }

   private static 0cC NW71nIbDtq(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _SmCwFKruq/* $FF was: 9SmCwFKruq*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cH x8qTnZiwW5(0cC var0) {
      return var0.mc;
   }

   private static 0cC VBdBVMVxkx(0cL var0) {
      return var0.pbot;
   }

   public void handleServerDifficulty(SPacketServerDifficulty packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 756N4FWelV(gW3nC9YaSo(this)));
      cS79OXDc1z(this).getWorld().getWorldInfo().setDifficulty(packetIn.getDifficulty());
      yeg2riGB7T(this).getWorld().getWorldInfo().setDifficultyLocked(packetIn.isDifficultyLocked());
   }

   private static 0cD WyQ0260dik(0cC var0) {
      return var0.player;
   }

   private static double BT9Tev3ukW(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0cC oOt99gNodv(0cL var0) {
      return var0.pbot;
   }

   private static void RL94yVTKg9(0cD var0, double var1) {
      var0.motionX = var1;
   }

   private static 0cC z669ONGUsr(0cL var0) {
      return var0.pbot;
   }

   private static 0cC xvNVqFmWZa(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _GrgjtSuL9/* $FF was: 6GrgjtSuL9*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC iT7GW3FFbp(0cL var0) {
      return var0.pbot;
   }

   private static PlayerCapabilities EeOpSRdCrz(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static 0cC tJEHkI2sod(0cL var0) {
      return var0.pbot;
   }

   public void sendPacket(Packet<?> packet) {
      b7T5GjsP6q(this).sendPacket(packet);
   }

   private static 0cH kDywKrmwAR(0cC var0) {
      return var0.mc;
   }

   private static 0cC OdhNMqze5a(0cL var0) {
      return var0.pbot;
   }

   private static void FdsS53bgVb(0cL var0, boolean var1) {
      var0.doneLoadingTerrain = var1;
   }

   private static 0cH _V8DF0Jkh4/* $FF was: 7V8DF0Jkh4*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC VhabVHOZTV(0cL var0) {
      return var0.pbot;
   }

   public void handleAdvancementInfo(SPacketAdvancementInfo packetIn) {
   }

   private static 0cC uUyd1gGjj6(0cL var0) {
      return var0.pbot;
   }

   private static 0cD _LURXAYw5j/* $FF was: 6LURXAYw5j*/(0cC var0) {
      return var0.player;
   }

   private static 0cH nlFLxqKmYY(0cC var0) {
      return var0.mc;
   }

   private static 0cH HSZrbSePUF(0cC var0) {
      return var0.mc;
   }

   private static 0cC dTQJx5nZCo(0cL var0) {
      return var0.pbot;
   }

   private static double tCmjeyNTxP(Entity var0) {
      return var0.posZ;
   }

   private static 0cC boofLOy1GG(0cL var0) {
      return var0.pbot;
   }

   private static void sOoRlev3k2(PlayerCapabilities var0, boolean var1) {
      var0.allowFlying = var1;
   }

   private static float ITGHQE0lX7(Entity var0) {
      return var0.rotationYaw;
   }

   private static Container P41TdNKA43(0cD var0) {
      return var0.openContainer;
   }

   private static 0cC e24a6YwurJ(0cL var0) {
      return var0.pbot;
   }

   private static 0cH V0bQMDvaTU(0cC var0) {
      return var0.mc;
   }

   public void onDisconnect(ITextComponent reason) {
      g2B1WIWi11(Bp7HeENBgB(this)).loadWorld((0cU)null);
      lXNG8P9vVw(this).disconnect();
   }

   public void handleEntityStatus(SPacketEntityStatus packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, qGBliT2Paw(yfSxKC6UOI(this)));
      Entity entity = packetIn.getEntity(Aj5WrPRy2p(this).getWorld());
      if (entity != null && packetIn.getOpCode() != (2858 ^ -9147 ^ 24845 ^ -18825)) {
         if (packetIn.getOpCode() == (22227 ^ -28404 ^ 26449 ^ -24403)) {
            if (entity == jG860lWEev(99rTWSkECw(this))) {
            }
         } else {
            entity.handleStatusUpdate(packetIn.getOpCode());
         }
      }

   }

   private static 0cC tqoUkEPs1J(0cL var0) {
      return var0.pbot;
   }

   private static void zN671JMFyj(EntityPlayer var0, double var1) {
      var0.motionX = var1;
   }

   private static 0cC HWSrlY7jV0(0cL var0) {
      return var0.pbot;
   }

   private static 0cC AtbVnwWrVn(0cL var0) {
      return var0.pbot;
   }

   private static 0cC VnnUTu2jqi(0cL var0) {
      return var0.pbot;
   }

   private static void gAyGhDAjLN(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0cD YhqkO0iY3w(0cC var0) {
      return var0.player;
   }

   private static 0cH QgZV7N56Vd(0cC var0) {
      return var0.mc;
   }

   private static 0cC CoFEWcS7lA(0cL var0) {
      return var0.pbot;
   }

   private static double FOOyicGnBn(0cD var0) {
      return var0.motionZ;
   }

   public void handleSpawnPosition(SPacketSpawnPosition packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, riwEFA6uWt(Q7eF1Vg47v(this)));
      6YWWdj19wM(NW71nIbDtq(this)).setSpawnPoint(packetIn.getSpawnPos(), (boolean)(12322 ^ -18763 ^ 20505 ^ -10609));
      POnvyc4zyv(this).getWorld().getWorldInfo().setSpawn(packetIn.getSpawnPos());
   }

   private static 0cP M32yQGf0kD(0cL var0) {
      return var0.netManager;
   }

   public void handleMoveVehicle(SPacketMoveVehicle packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, QnVn6tbpOS(DtvkpSd1Fb(this)));
      Entity entity = 6LURXAYw5j(xUjmR4tEop(this)).getLowestRidingEntity();
      if (entity != duKB2cRbI0(i36Zxnlf9x(this)) && entity.canPassengerSteer()) {
         entity.setPositionAndRotation(packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getYaw(), packetIn.getPitch());
         M32yQGf0kD(this).sendPacket(new CPacketVehicleMove(entity));
      }

   }

   private static boolean DDukLwBC0h(0bv var0) {
      return var0.value;
   }

   private static 0cC SdlqWOV2yc(0cL var0) {
      return var0.pbot;
   }

   private static 0cD _TwjljBa2f/* $FF was: 1TwjljBa2f*/(0cC var0) {
      return var0.player;
   }

   private static 0cH NKvIq7sbE9(0cC var0) {
      return var0.mc;
   }

   private static 0cC gT0OvGHmDv(0cL var0) {
      return var0.pbot;
   }

   private static long rdXJp85my6(Entity var0) {
      return var0.serverPosX;
   }

   private static void vhQ4i76sdJ(PlayerCapabilities var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static 0cC QpQXntnI7f(0cL var0) {
      return var0.pbot;
   }

   private static 0cC T4DSxJ45nD(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Lwy8dO1nmB(0cL var0) {
      return var0.pbot;
   }

   private static 0cC sJJMls1LE6(0cL var0) {
      return var0.pbot;
   }

   private static 0cD GHXW9QdWFa(0cC var0) {
      return var0.player;
   }

   private static 0cH nzw5xetYJC(0cC var0) {
      return var0.mc;
   }

   private static 0cC x6pintzFjW(0cL var0) {
      return var0.pbot;
   }

   private static double idnH6QdcT1(Entity var0) {
      return var0.posX;
   }

   private static 0cC dSHWoEZUi1(0cL var0) {
      return var0.pbot;
   }

   private static ItemStack _PF4YyYeQf/* $FF was: 5PF4YyYeQf*/() {
      return ItemStack.EMPTY;
   }

   private static 0cD lVnJ7TCImw(0cC var0) {
      return var0.player;
   }

   private static 0cC whoQDJ3p7t(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _zYbdh05Bi/* $FF was: 4zYbdh05Bi*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _XvzT2gbml/* $FF was: 1XvzT2gbml*/(0cL var0) {
      return var0.pbot;
   }

   public void handleHeldItemChange(SPacketHeldItemChange packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 3H1SgtVjet(goUoy0MQBD(this)));
      if (InventoryPlayer.isHotbar(packetIn.getHeldItemHotbarIndex())) {
         YyC6B4avj7(TrSwhqlSO7(nx3DyoAG96(nyNPuwloRn(this))), packetIn.getHeldItemHotbarIndex());
      }

   }

   private static 0cC vBowWVRgOw(0cL var0) {
      return var0.pbot;
   }

   private static Profiler IlW447G6WJ(0cH var0) {
      return var0.profiler;
   }

   private static 0cH lO6fUFKEQA(0cC var0) {
      return var0.mc;
   }

   private static 0cC Q7eF1Vg47v(0cL var0) {
      return var0.pbot;
   }

   private static 0cH Q6Q7juY68G(0cC var0) {
      return var0.mc;
   }

   public void handleEntityEffect(SPacketEntityEffect packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, oxSVtdNC3v(e24a6YwurJ(this)));
      Entity entity = li8TrE9Rml(this).getWorld().getEntityByID(packetIn.getEntityId());
      if (entity instanceof EntityLivingBase) {
         Potion potion = Potion.getPotionById(packetIn.getEffectId());
         if (potion != null) {
            PotionEffect potioneffect = new PotionEffect(potion, packetIn.getDuration(), packetIn.getAmplifier(), packetIn.getIsAmbient(), packetIn.doesShowParticles());
            potioneffect.setPotionDurationMax(packetIn.isMaxDuration());
            ((EntityLivingBase)entity).addPotionEffect(potioneffect);
         }
      }

   }

   private static 0cC odjvTLaw1g(0cL var0) {
      return var0.pbot;
   }

   public void handleDisplayObjective(SPacketDisplayObjective packetIn) {
   }

   private static 0cC QlE0JgDLY7(0cL var0) {
      return var0.pbot;
   }

   private static 0dG T22In7ZQPT(0bK var0) {
      return var0.seleniumManager;
   }

   private static 0cC lrLq2QsMFS(0cL var0) {
      return var0.pbot;
   }

   private static 0cC DWfQe9aSqA(0cL var0) {
      return var0.pbot;
   }

   private static float _NnLGolAwj/* $FF was: 9NnLGolAwj*/(Entity var0) {
      return var0.rotationPitch;
   }

   private static void HAOgbWawwa(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0cC _dBfQevipF/* $FF was: 5dBfQevipF*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC KLj60XcexF(0cL var0) {
      return var0.pbot;
   }

   private static 0cC VpOjSMFwhd(0cL var0) {
      return var0.pbot;
   }

   public void handlePlayerAbilities(SPacketPlayerAbilities packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, ZHoOJcr1DF(ggzO1bBwMH(this)));
      EntityPlayer entityplayer1 = asAtuaJqaN(JgVIaA2Q66(this));
      vhQ4i76sdJ(5KdvQbQTig(entityplayer1), packetIn.isFlying());
      DYAducE1Bm(HNCqNlJGWc(entityplayer1), packetIn.isCreativeMode());
      j5Pdi6daKT(5abJJB4Wie(entityplayer1), packetIn.isInvulnerable());
      sOoRlev3k2(G9iOizlwjg(entityplayer1), packetIn.isAllowFlying());
      Os8P41leQQ(entityplayer1).setFlySpeed(packetIn.getFlySpeed());
      EeOpSRdCrz(entityplayer1).setPlayerWalkSpeed(packetIn.getWalkSpeed());
   }

   private static 0cC Bp7HeENBgB(0cL var0) {
      return var0.pbot;
   }

   private static 0cC HtM9TRZ0J0(0cL var0) {
      return var0.pbot;
   }

   private static void gWa5LJi2gb(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0cH vGvoamTHsg(0cC var0) {
      return var0.mc;
   }

   private static CPacketResourcePackStatus.Action TG1ijaAHoE() {
      return CPacketResourcePackStatus.Action.ACCEPTED;
   }

   private static 0cC B67OShz7a2(0cL var0) {
      return var0.pbot;
   }

   private static 0cD WLqF74jcjN(0cC var0) {
      return var0.player;
   }

   private static 0cH vgYNa7WQjE(0cC var0) {
      return var0.mc;
   }

   private static 0cC _PAJMYTjWq/* $FF was: 9PAJMYTjWq*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cP YMFfDmBbgM(0cL var0) {
      return var0.netManager;
   }

   private static 0cC d3J5LYWf0j(0cL var0) {
      return var0.pbot;
   }

   private static 0cC We9y6L8CGO(0cL var0) {
      return var0.pbot;
   }

   private static void KURLlVUTBT(0cD var0, double var1) {
      var0.prevPosZ = var1;
   }

   private static 0cC i36Zxnlf9x(0cL var0) {
      return var0.pbot;
   }

   private static 0cH dd0jQOpoGo(0cC var0) {
      return var0.mc;
   }

   private static double kxqAG61X2r(EntityPlayer var0) {
      return var0.posZ;
   }

   private static String tp1916Aguy(0cC var0) {
      return var0.tabHeader;
   }

   public void handleEntityEquipment(SPacketEntityEquipment packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, V0bQMDvaTU(OKkLuO2j2n(this)));
      Entity entity = m4owqvvQON(this).getWorld().getEntityByID(packetIn.getEntityID());
      if (entity != null) {
         entity.setItemStackToSlot(packetIn.getEquipmentSlot(), packetIn.getItemStack());
      }

   }

   public void handleSoundEffect(SPacketSoundEffect packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 276XrtcTGh(ePXzgwVyP7(this)));
      0dB var10000 = lEB81QJGB6(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֤֘ւֲ֑֑֙֓֒֔փ");
      Object[] var10002 = new Object[10964 ^ -19997 ^ 27101 ^ -3352];
      var10002[27598 ^ -24777 ^ 17703 ^ -20002] = 2rICAN7ptz(this);
      var10002[32102 ^ -6261 ^ 19605 ^ -10631] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0cC _qT1QbakNh/* $FF was: 9qT1QbakNh*/(0cL var0) {
      return var0.pbot;
   }

   public void handleScoreboardObjective(SPacketScoreboardObjective packetIn) {
   }

   private static 0cH _yLaFnc61d/* $FF was: 9yLaFnc61d*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC EjNCVsnSK8(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _DO7d5lbG2/* $FF was: 6DO7d5lbG2*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _9rTWSkECw/* $FF was: 99rTWSkECw*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC yeg2riGB7T(0cL var0) {
      return var0.pbot;
   }

   public void handleWindowItems(SPacketWindowItems packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, egTHBokWO9(boofLOy1GG(this)));
      EntityPlayer entityplayer = WwjreRF5W7(OI1R8jDFoq(this));
      if (packetIn.getWindowId() == 0) {
         BDW6jL6Bvm(entityplayer).setAll(packetIn.getItemStacks());
      } else if (packetIn.getWindowId() == RlAX5vMC2l(DD8VySODiJ(entityplayer))) {
         I7t2OBGLrA(entityplayer).setAll(packetIn.getItemStacks());
      }

   }

   public void handleParticles(SPacketParticles packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, nlFLxqKmYY(sVgALldDI5(this)));
      0dB var10000 = gg0PIogUzD(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֧֖օփ֛֞֔֒ք");
      Object[] var10002 = new Object[24440 ^ -19655 ^ 4707 ^ -480];
      var10002[32409 ^ -468 ^ 13100 ^ -19559] = NnhNon0D90(this);
      var10002[15054 ^ -3393 ^ 31524 ^ -19628] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0cC GafhjcwdgA(0cL var0) {
      return var0.pbot;
   }

   private static 0cH QnVn6tbpOS(0cC var0) {
      return var0.mc;
   }

   private static 0cC arXBBnRU2i(0cL var0) {
      return var0.pbot;
   }

   public void handleCustomSound(SPacketCustomSound packetIn) {
   }

   public void handleAnimation(SPacketAnimation packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, q7aFQXQv8V(ZvoqvS0aQE(this)));
      Entity entity = iT7GW3FFbp(this).getWorld().getEntityByID(packetIn.getEntityID());
      if (entity != null) {
         EntityLivingBase entitylivingbase1;
         if (packetIn.getAnimationType() == 0) {
            entitylivingbase1 = (EntityLivingBase)entity;
            entitylivingbase1.swingArm(zNDTzwNJAo());
         } else if (packetIn.getAnimationType() == (24968 ^ -25945 ^ 8955 ^ -9769)) {
            entitylivingbase1 = (EntityLivingBase)entity;
            entitylivingbase1.swingArm(ogqCGAsXMG());
         } else if (packetIn.getAnimationType() == (27546 ^ -12542 ^ 18149 ^ -7556)) {
            entity.performHurtAnimation();
         } else if (packetIn.getAnimationType() == (16750 ^ -17366 ^ 30935 ^ -31343)) {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            entityplayer.wakeUpPlayer((boolean)(12393 ^ -28914 ^ 37 ^ -16574), (boolean)(8839 ^ -672 ^ 31445 ^ -23246), (boolean)(20082 ^ -9796 ^ 4778 ^ -31388));
         } else if (packetIn.getAnimationType() != (89 ^ -10147 ^ 310 ^ -9930) && packetIn.getAnimationType() == (16111 ^ -20614 ^ 3373 ^ -25411)) {
         }
      }

   }

   private static Container I7t2OBGLrA(EntityPlayer var0) {
      return var0.openContainer;
   }

   public void handleDisconnect(SPacketDisconnect packetIn) {
      0bL.getInstance().getCaptchaManager().removeCaptcha(lrLq2QsMFS(this));
      if (DDukLwBC0h(djIWgl6Stf())) {
         0dK.formatMsg(l1jVoFIYsY("ǩƵǍǌƹưǂǊǏǂחב֓ב֛") + dW1G92RDdv(this).getNickname() + l1jVoFIYsY("ב֑ב֛ח") + packetIn.getReason().getFormattedText());
      }

      0dB var10000 = Rmcnij0B8R(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փֳ֞ք֔֘֙֙֒֔փ");
      Object[] var10002 = new Object[10195 ^ -1984 ^ 19251 ^ -27486];
      var10002[24077 ^ -7925 ^ 10000 ^ -26602] = qLJbPebo0A(this);
      var10002[10430 ^ -12565 ^ 30901 ^ -24863] = packetIn;
      var10000.invokeMethod(var10001, var10002);
      TuSYJoGDnL(this).closeChannel(packetIn.getReason());
      rGDNLtD9Wr(this).reconnect((boolean)(14324 ^ -19876 ^ 25957 ^ -7987));
   }

   private static 0cH _isA1s7Brs/* $FF was: 3isA1s7Brs*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC _8ELvZ7HS4/* $FF was: 68ELvZ7HS4*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC VI60iyDEtG(0cL var0) {
      return var0.pbot;
   }

   private static 0cC UWzRhXIFSa(0cL var0) {
      return var0.pbot;
   }

   private static Container dZAgQZZJgO(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cD L69gNlVON2(0cC var0) {
      return var0.player;
   }

   private static 0cC HsNoFijQaU(0cL var0) {
      return var0.pbot;
   }

   private static 0cC MGiFa0GKja(0cL var0) {
      return var0.pbot;
   }

   private static Profiler BF4CIO82tL(0cH var0) {
      return var0.profiler;
   }

   private static 0cC _faaaqylkq/* $FF was: 2faaaqylkq*/(0cL var0) {
      return var0.pbot;
   }

   public void handleCustomPayload(SPacketCustomPayload packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, kDywKrmwAR(ija2w5zXWQ(this)));
   }

   private static CPacketResourcePackStatus.Action w7OyLiNjBO() {
      return CPacketResourcePackStatus.Action.SUCCESSFULLY_LOADED;
   }

   private static 0cC xr9oij8Bnb(0cL var0) {
      return var0.pbot;
   }

   private static SPacketPlayerListItem.Action b87YM2sfjc() {
      return SPacketPlayerListItem.Action.REMOVE_PLAYER;
   }

   private static SPacketUpdateScore.Action jrPaVI2U8r() {
      return SPacketUpdateScore.Action.REMOVE;
   }

   private static 0cC YAbJjhqx52(0cL var0) {
      return var0.pbot;
   }

   private static EnumHand zNDTzwNJAo() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cD V2CJhrERjG(0cC var0) {
      return var0.player;
   }

   private static SPacketPlayerListItem.Action LieealtWQB() {
      return SPacketPlayerListItem.Action.ADD_PLAYER;
   }

   private static 0cC JOilVxbC6F(0cL var0) {
      return var0.pbot;
   }

   private static boolean jUiJcY6YIg(0cL var0) {
      return var0.doneLoadingTerrain;
   }

   private static double f6jYOtAFrB(Entity var0) {
      return var0.posY;
   }

   public void handleSpawnPlayer(SPacketSpawnPlayer packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mYijto7b2i(MGiFa0GKja(this)));

      try {
         double d0 = packetIn.getX();
         double d1 = packetIn.getY();
         double d2 = packetIn.getZ();
         float f = (float)(packetIn.getYaw() * (9089 ^ -2092 ^ 22806 ^ -29653)) / Float.intBitsToFloat(262099 ^ 227811 ^ 4251 ^ 1825919186 ^ 8707 ^ 28596 ^ 5348 ^ 794140458);
         float f1 = (float)(packetIn.getPitch() * (23484 ^ -18185 ^ 15677 ^ -8418)) / Float.intBitsToFloat(8592 ^ '靴' ^ '\udc9f' ^ 772617497 ^ '\uf169' ^ 'ꗈ' ^ 5597 ^ 1837964830);
         NetworkPlayerInfo netInfo = this.getPlayerInfo(packetIn.getUniqueId());
         EntityOtherPlayerMP entityotherplayermp = new EntityOtherPlayerMP(VnnUTu2jqi(this).getWorld(), netInfo.getGameProfile());
         NLbyLVjbg6(entityotherplayermp, d0);
         C9TU7YeEwB(entityotherplayermp, d0);
         nNjliGTvZM(entityotherplayermp, d1);
         6myNo1oGM7(entityotherplayermp, d1);
         zVbo5lw07v(entityotherplayermp, d2);
         rd2FOncDJn(entityotherplayermp, d2);
         EntityTracker.updateServerPosition(entityotherplayermp, d0, d1, d2);
         entityotherplayermp.setPositionAndRotation(d0, d1, d2, f, f1);
         y9qLxiYLNB(this).getWorld().addEntityToWorld(packetIn.getEntityID(), entityotherplayermp);
      } catch (Exception var12) {
      }

   }

   private static 0cC KTbdIoQBOZ(0cL var0) {
      return var0.pbot;
   }

   private static 0cC v4T8YaSYo6(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Nq9INoLXqO(0cL var0) {
      return var0.pbot;
   }

   private static double nOKqEEWySh(Entity var0) {
      return var0.posZ;
   }

   public void handleUpdateBossInfo(SPacketUpdateBossInfo packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, Tb7araJZdw(5dBfQevipF(this)));
      0dB var10000 = Bgr431TclP(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֢և֖֓փֵ֒֘քք־֑֙֘");
      Object[] var10002 = new Object[3289 ^ -29439 ^ 15882 ^ -16432];
      var10002[16712 ^ -7035 ^ 32646 ^ -9653] = tJEHkI2sod(this);
      var10002[7382 ^ -15605 ^ 5639 ^ -13861] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0cC NY9j3fGBlo(0cL var0) {
      return var0.pbot;
   }

   private static 0cC V6sBBrgaNO(0cL var0) {
      return var0.pbot;
   }

   private static 0cH jTUNa2aYaO(0cC var0) {
      return var0.mc;
   }

   private static 0cC LhlkASRhIH(0cL var0) {
      return var0.pbot;
   }

   public void handleResourcePack(SPacketResourcePackSend packetIn) {
      0dB var10000 = qZCpF00cLx(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֥֒ք֘ւօ֧֖֤֔֒֔֜֒֙֓");
      Object[] var10002 = new Object[9138 ^ -9195 ^ 18864 ^ -18923];
      var10002[28624 ^ -14112 ^ 26776 ^ -12376] = Gtzg5eC47l(this);
      var10002[22351 ^ -6245 ^ 16047 ^ -29062] = packetIn;
      var10000.invokeMethod(var10001, var10002);
      if (dSildyxTlF(EUJpS2kv6Q())) {
         wrMoggrAlY(x0mXTU52qo(VJY932bW2X(this))).sendPacket(new CPacketResourcePackStatus(TG1ijaAHoE()));
         tGVJAAZ35S(LdQ70Q2OXT(oT5BitIqww(this))).sendPacket(new CPacketResourcePackStatus(w7OyLiNjBO()));
      }

   }

   private static 0cC AZnqALrSBT(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _AZBwlpjOd/* $FF was: 2AZBwlpjOd*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ePXzgwVyP7(0cL var0) {
      return var0.pbot;
   }

   private static 0cH ZHoOJcr1DF(0cC var0) {
      return var0.mc;
   }

   private static Container W9cP8wOiXy(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cC _fHwvTHtVw/* $FF was: 4fHwvTHtVw*/(0cL var0) {
      return var0.pbot;
   }

   private static void vtbVYn9NOB(EntityLivingBase var0, double var1) {
      var0.motionZ = var1;
   }

   private static 0cD asAtuaJqaN(0cC var0) {
      return var0.player;
   }

   public void func_194307_a(SPacketPlaceGhostRecipe p_194307_1_) {
   }

   private static 0cH tWFJwDaerT(0cC var0) {
      return var0.mc;
   }

   private static 0cD yw6RAlh0PD(0cC var0) {
      return var0.player;
   }

   private static 0cC lXNG8P9vVw(0cL var0) {
      return var0.pbot;
   }

   private static 0cC aizwksBWrX(0cL var0) {
      return var0.pbot;
   }

   private static void ymqhGwIJQv(EntityLivingBase var0, float var1) {
      var0.renderYawOffset = var1;
   }

   private static 0cC Vc6AdlTQWI(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _IvQsPfNAP/* $FF was: 3IvQsPfNAP*/(0cL var0) {
      return var0.pbot;
   }

   private static void NeRVmo3h8I(0cC var0, String var1) {
      var0.tabFooter = var1;
   }

   private static 0cD qVYiHI26GS(0cC var0) {
      return var0.player;
   }

   private static 0cC oT5BitIqww(0cL var0) {
      return var0.pbot;
   }

   public void handleCollectItem(SPacketCollectItem packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 5ify4AYaTg(tqoUkEPs1J(this)));
      Entity entity = qdllwdvb5t(this).getWorld().getEntityByID(packetIn.getCollectedItemEntityID());
      EntityLivingBase entitylivingbase = (EntityLivingBase)PhViHkVoQl(this).getWorld().getEntityByID(packetIn.getEntityID());
      if (entitylivingbase == null) {
         EntityLivingBase entitylivingbase = soSqbvJWUj(whoQDJ3p7t(this));
      }

      if (entity != null) {
         if (entity instanceof EntityItem) {
            ((EntityItem)entity).getItem().setCount(packetIn.getAmount());
         }

         V6sBBrgaNO(this).getWorld().removeEntityFromWorld(packetIn.getCollectedItemEntityID());
      }

   }

   private static 0cC Aj5WrPRy2p(0cL var0) {
      return var0.pbot;
   }

   public void handleTabComplete(SPacketTabComplete packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 2tx6cmmOKe(4fHwvTHtVw(this)));
      0dB var10000 = LlKY8hbX29(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փִ֣֖֚֕֘և֛֒փ֒");
      Object[] var10002 = new Object[16337 ^ -19991 ^ 26256 ^ -5974];
      var10002[29325 ^ -24343 ^ 30937 ^ -21827] = SdlqWOV2yc(this);
      var10002[22061 ^ -16575 ^ 27047 ^ -32566] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0cC tYWngaV1u4(0cL var0) {
      return var0.pbot;
   }

   private static 0cC JVIEIyJOvi(0cL var0) {
      return var0.pbot;
   }

   private static 0cC JkTA1l3HGE(0cL var0) {
      return var0.pbot;
   }

   private static 0cC T7rdPV1tBy(0cL var0) {
      return var0.pbot;
   }

   private static 0cC yfSxKC6UOI(0cL var0) {
      return var0.pbot;
   }

   private static 0cC A4C4FB6243(0cL var0) {
      return var0.pbot;
   }

   private static Container wOQYy6m7gt(EntityPlayer var0) {
      return var0.inventoryContainer;
   }

   public NetworkPlayerInfo getPlayerInfo(UUID uniqueId) {
      return (NetworkPlayerInfo)fVRTxJznSF(this).get(uniqueId);
   }

   private static void W4QdN0TvCF(EntityPlayer var0, double var1) {
      var0.motionZ = var1;
   }

   private static PlayerCapabilities HNCqNlJGWc(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static 0cC _p4jJV1M5a/* $FF was: 9p4jJV1M5a*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC L9ZVvjgTeP(0cL var0) {
      return var0.pbot;
   }

   private static 0cC lGi2hoDdjh(0cL var0) {
      return var0.pbot;
   }

   private static 0cC gBfcwQyWHj(0cL var0) {
      return var0.pbot;
   }

   private static 0cH s9j2p2eYG6(0cC var0) {
      return var0.mc;
   }

   private static double _NWsOILlqY/* $FF was: 2NWsOILlqY*/(0cD var0) {
      return var0.motionY;
   }

   private static 0cD yBdBiwD9d9(0cC var0) {
      return var0.player;
   }

   public void handleSpawnExperienceOrb(SPacketSpawnExperienceOrb packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, TF6GjxzdFe(7FfLVc9Qvt(this)));
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      Entity entity = new EntityXPOrb(4zYbdh05Bi(this).getWorld(), d0, d1, d2, packetIn.getXPValue());
      aKCyv2hdIm(entity, Float.intBitsToFloat(14919 ^ '꿞' ^ 18775 ^ 1397246772 ^ 12562 ^ '遠' ^ 22317 ^ 1397252517));
      ndXLSOdoyO(entity, Float.intBitsToFloat(32666 ^ 22711 ^ 3601 ^ 1234143740 ^ 3217 ^ 'ꀽ' ^ 18553 ^ 1234125845));
      ((Entity)entity).setEntityId(packetIn.getEntityID());
      bgiV5AGd7j(this).getWorld().addEntityToWorld(packetIn.getEntityID(), entity);
   }

   public void handleTitle(SPacketTitle packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, NKvIq7sbE9(8qCdFJG9vr(this)));
      if (packetIn.getMessage() != null && packetIn.getType().equals(11Dy7ehsgr())) {
         String text = 0cC.stripColor(packetIn.getMessage().getUnformattedText());
         if (text.equalsIgnoreCase(l1jVoFIYsY("ǥƼחǈƷǉƿǌǏחǈƷǉǅǂƷǍƴז"))) {
            JK42fYh736(this).setParameter(l1jVoFIYsY("\u0590֖֚֒\u0590ւ֖օ֓֔֟֒֔֜"), Boolean.valueOf((boolean)(26561 ^ -15963 ^ 15880 ^ -26515)));
            0cR.trigger(dnRA8LakaG(this), l1jVoFIYsY("ְְ֖֚֘֙֒ւ֖օ֓"));
         }
      }

      0dB var10000 = BJeGepbwKp(0bK.getInstance());
      String var10001 = l1jVoFIYsY("֤֧֖֔֜֒փ֣֞փ֛֒");
      Object[] var10002 = new Object[26040 ^ -19275 ^ 25180 ^ -19629];
      var10002[1585 ^ -28705 ^ 14267 ^ -16811] = 4yAZNjq2j6(this);
      var10002[29386 ^ -20431 ^ 225 ^ -15845] = packetIn;
      var10000.invokeMethod(var10001, var10002);
   }

   private static void _sG5IynkGo/* $FF was: 1sG5IynkGo*/(Entity var0, boolean var1) {
      var0.onGround = var1;
   }

   private static 0cD DDeoldBAIQ(0cC var0) {
      return var0.player;
   }

   private static 0cC QJSBC93j7W(0cL var0) {
      return var0.pbot;
   }

   private static 0cC Swrp7BwFDP(0cL var0) {
      return var0.pbot;
   }

   private static void ndXLSOdoyO(Entity var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cC BQDsb9zzdo(0cL var0) {
      return var0.pbot;
   }

   public void handleSetPassengers(SPacketSetPassengers packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 43VdFyhGyW(vXiDunTUz3(this)));
      Entity entity = sKD67ZY11w(this).getWorld().getEntityByID(packetIn.getEntityId());
      if (entity != null) {
         entity.removePassengers();
         int[] var3 = packetIn.getPassengerIds();
         int var4 = var3.length;

         for(int var5 = 16724 ^ -10052 ^ 13667 ^ -21365; var5 < var4; ++var5) {
            int i = var3[var5];
            Entity entity1 = 2AZBwlpjOd(this).getWorld().getEntityByID(i);
            if (entity1 != null) {
               entity1.startRiding(entity, (boolean)(9764 ^ -4564 ^ 3653 ^ -14772));
            }
         }
      }

   }

   private static 0cC JNI6nQnOaG(0cL var0) {
      return var0.pbot;
   }

   private static 0cC dq2qeVoczM(0cL var0) {
      return var0.pbot;
   }

   private static void QJnjvgScx3(Entity var0, long var1) {
      var0.serverPosX = var1;
   }

   private static Container iQbaQGAdjR(EntityPlayer var0) {
      return var0.inventoryContainer;
   }

   private static void qaOjj2VipH(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static GameProfile JQ1RvMcgGL(0cL var0) {
      return var0.profile;
   }

   public void handleBlockChange(SPacketBlockChange packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, tWFJwDaerT(FLgwiAC1tD(this)));
      2OV9njHYES(this).getWorld().invalidateRegionAndSetBlock(packetIn.getBlockPosition(), packetIn.getBlockState());
   }

   private static 0bv _OODjKIeLP/* $FF was: 8OODjKIeLP*/() {
      return 0cc.chat;
   }

   private static 0cC qLJbPebo0A(0cL var0) {
      return var0.pbot;
   }

   private static ChatType VbpQAs4o18() {
      return ChatType.GAME_INFO;
   }

   private static 0cC AdqQeQ9SrD(0cL var0) {
      return var0.pbot;
   }

   private static 0cC POnvyc4zyv(0cL var0) {
      return var0.pbot;
   }

   private static float W37tZae9aC(EntityPlayer var0) {
      return var0.rotationYaw;
   }

   private static 0cD JTv4s6QMs8(0cC var0) {
      return var0.player;
   }

   private static 0cC qIBqTVnSv9(0cL var0) {
      return var0.pbot;
   }

   private static 0cC m4owqvvQON(0cL var0) {
      return var0.pbot;
   }

   private static void B2KSo1VQSR(Entity var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cC cS79OXDc1z(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ICbFeYYpAN(0cL var0) {
      return var0.pbot;
   }

   private static 0cC sNHrLaYhjx(0cL var0) {
      return var0.pbot;
   }

   private static 0cC _qCdFJG9vr/* $FF was: 8qCdFJG9vr*/(0cL var0) {
      return var0.pbot;
   }

   private static void _yNarWCTIv/* $FF was: 2yNarWCTIv*/(0cC var0, String var1) {
      var0.windowTitle = var1;
   }

   private static void go2LB8SxkA(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0cQ viT2GiqXHC(0cH var0) {
      return var0.playerController;
   }

   private static 0cC r0lw1yUDAB(0cL var0) {
      return var0.pbot;
   }

   private static 0dB nzBiLrLjaQ(0bK var0) {
      return var0.pBotsScriptManager;
   }

   public void handleSignEditorOpen(SPacketSignEditorOpen packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, VV9Wa8XxvV(HsNoFijQaU(this)));
      TileEntity tileentity = e4TCOAvRmJ(this).getWorld().getTileEntity(packetIn.getSignPosition());
      if (!(tileentity instanceof TileEntitySign)) {
         tileentity = new TileEntitySign();
         ((TileEntity)tileentity).setWorld(2zLLZUtGyn(this).getWorld());
         ((TileEntity)tileentity).setPos(packetIn.getSignPosition());
      }

      2SfIPBI4uK(hXydOXVLu8(this)).openEditSign((TileEntitySign)tileentity);
   }

   private static 0cH _H1SgtVjet/* $FF was: 3H1SgtVjet*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC _61iaeqidV/* $FF was: 961iaeqidV*/(0cL var0) {
      return var0.pbot;
   }

   public void handleSpawnMob(SPacketSpawnMob packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, hDB9H2ylQs(tOOFrXN2IT(this)));
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      float f = (float)(packetIn.getYaw() * (21713 ^ -25184 ^ 9462 ^ -4881)) / Float.intBitsToFloat(13060 ^ 243736 ^ 12142 ^ 307618564 ^ 15217 ^ 224351 ^ '웻' ^ 1372968611);
      float f1 = (float)(packetIn.getPitch() * (13853 ^ -5887 ^ 31001 ^ -22675)) / Float.intBitsToFloat('虡' ^ 101552 ^ '뒻' ^ 590002901 ^ 21952 ^ 113089 ^ 202 ^ 1621813364);
      EntityLivingBase entitylivingbase = (EntityLivingBase)EntityList.createEntityByID(packetIn.getEntityType(), B67OShz7a2(this).getWorld());
      if (entitylivingbase != null) {
         EntityTracker.updateServerPosition(entitylivingbase, d0, d1, d2);
         ymqhGwIJQv(entitylivingbase, (float)(packetIn.getHeadPitch() * (26968 ^ -9445 ^ 8812 ^ -28345)) / Float.intBitsToFloat('\uf130' ^ 4154391 ^ 4740 ^ 681559894 ^ '\ueaf4' ^ 4174838 ^ '\uec8f' ^ 1797256568));
         VLB2Rr6Iyy(entitylivingbase, (float)(packetIn.getHeadPitch() * (3586 ^ -197 ^ 27986 ^ -25341)) / Float.intBitsToFloat(117504 ^ 129262 ^ 22794 ^ 265588745 ^ 106598 ^ 113173 ^ 14755 ^ 1280629053));
         Entity[] aentity = entitylivingbase.getParts();
         if (aentity != null) {
            int i = packetIn.getEntityID() - entitylivingbase.getEntityId();
            Entity[] var13 = aentity;
            int var14 = aentity.length;

            for(int var15 = 5834 ^ -15232 ^ 11548 ^ -170; var15 < var14; ++var15) {
               Entity entity = var13[var15];
               entity.setEntityId(entity.getEntityId() + i);
            }
         }

         entitylivingbase.setEntityId(packetIn.getEntityID());
         entitylivingbase.setUniqueId(packetIn.getUniqueId());
         entitylivingbase.setPositionAndRotation(d0, d1, d2, f, f1);
         tn9EgrBeDl(entitylivingbase, (double)((float)packetIn.getVelocityX() / Float.intBitsToFloat(4164895 ^ 4189729 ^ 15187 ^ -241192337 ^ 252365 ^ 256929 ^ 3451 ^ -1268394731)));
         4dw9kVA07L(entitylivingbase, (double)((float)packetIn.getVelocityY() / Float.intBitsToFloat(105262 ^ 76901 ^ 3407 ^ -418501510 ^ 9088 ^ 102857 ^ 106952 ^ -1561033217)));
         vtbVYn9NOB(entitylivingbase, (double)((float)packetIn.getVelocityZ() / Float.intBitsToFloat(23165 ^ 234223 ^ 30823 ^ 541961383 ^ '锺' ^ 504810 ^ 'ﳰ' ^ 1706280562)));
         DWfQe9aSqA(this).getWorld().addEntityToWorld(packetIn.getEntityID(), entitylivingbase);
         List<EntityDataManager.DataEntry<?>> list = packetIn.getDataManagerEntries();
         if (list != null) {
            entitylivingbase.getDataManager().setEntryValues(list);
         }
      } else {
         1hVoINOWYZ().warn(l1jVoFIYsY("֤֜֞ևև֞֙\u0590חֲ֙փ֞փ֎חր֞փ֟ח֞֓ח\u058c֊"), packetIn.getEntityType());
      }

   }

   private static 0cD FX1FwW5ltr(0cC var0) {
      return var0.player;
   }

   public 0cP getNetworkManager() {
      return fyvwJZoZjm(this);
   }

   private static 0cC NSqw68d3ex(0cL var0) {
      return var0.pbot;
   }

   private static int hC9892nL66(Container var0) {
      return var0.windowId;
   }

   private static double IqGSUxehdc(EntityPlayer var0) {
      return var0.posX;
   }

   private static void L8i83inE1L(0cD var0, double var1) {
      var0.motionZ = var1;
   }

   private static 0cC YaJAxeLis6(0cL var0) {
      return var0.pbot;
   }

   public void handleSelectAdvancementsTab(SPacketSelectAdvancementsTab packetIn) {
   }

   public void handlePlayerPosLook(SPacketPlayerPosLook packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, jTUNa2aYaO(4q6iAev0dl(this)));
      EntityPlayer entityplayer = AOenrfbtxD(AwTqGrML4Q(this));
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      float f = packetIn.getYaw();
      float f1 = packetIn.getPitch();
      if (packetIn.getFlags().contains(olgIyj6igG())) {
         d0 += IqGSUxehdc(entityplayer);
      } else {
         zN671JMFyj(entityplayer, Double.longBitsToDouble(8694447049123412009L ^ 8694447049123412009L));
      }

      if (packetIn.getFlags().contains(HrjDFQ81nn())) {
         d1 += FZtr9G4two(entityplayer);
      } else {
         f69qs5rqjy(entityplayer, Double.longBitsToDouble(-3061156020689162783L ^ -3061156020689162783L));
      }

      if (packetIn.getFlags().contains(66kMwTpLXH())) {
         d2 += kxqAG61X2r(entityplayer);
      } else {
         W4QdN0TvCF(entityplayer, Double.longBitsToDouble(-75336325808935849L ^ -75336325808935849L));
      }

      if (packetIn.getFlags().contains(nEfV9j2jw6())) {
         f1 += DswQWxvbG2(entityplayer);
      }

      if (packetIn.getFlags().contains(JjmQY3q8G4())) {
         f += W37tZae9aC(entityplayer);
      }

      ((EntityPlayer)entityplayer).setPositionAndRotation(d0, d1, d2, f, f1);
      YMFfDmBbgM(this).sendPacket(new CPacketConfirmTeleport(packetIn.getTeleportId()));
      p2FzVgVCNr(this).sendPacket(new CPacketPlayer.PositionRotation(gY8rsHTgyx(entityplayer), BT9Tev3ukW(((EntityPlayer)entityplayer).getEntityBoundingBox()), DYtNJmENNf(entityplayer), bVqFrI4Q6d(entityplayer), 4IYX1fDDVD(entityplayer), (boolean)(11944 ^ -15308 ^ 26361 ^ -29595)));
      if (!jUiJcY6YIg(this)) {
         dxDCgaIr1Q(aOIbWICv7g(L9ZVvjgTeP(this)), fZaGNy4XSv(V2CJhrERjG(mAGd9utqqG(this))));
         oaqysQlxeU(JTv4s6QMs8(ogsbKgSvH3(this)), w9Yni4q8S8(lVnJ7TCImw(QlE0JgDLY7(this))));
         KURLlVUTBT(7oDANlY0uG(GafhjcwdgA(this)), yERmuqJsJO(1TwjljBa2f(uUyd1gGjj6(this))));
         FdsS53bgVb(this, (boolean)(11381 ^ -7578 ^ 22092 ^ -26530));
      }

   }

   private static 0cP fyvwJZoZjm(0cL var0) {
      return var0.netManager;
   }

   private static Container _x0lSgSIS4/* $FF was: 6x0lSgSIS4*/(0cD var0) {
      return var0.openContainer;
   }

   private static 0bv EUJpS2kv6Q() {
      return 0cd.RPskip;
   }

   private static double yERmuqJsJO(0cD var0) {
      return var0.posZ;
   }

   private static 0cC sVgALldDI5(0cL var0) {
      return var0.pbot;
   }

   private static PlayerCapabilities Os8P41leQQ(EntityPlayer var0) {
      return var0.capabilities;
   }

   public void handleUseBed(SPacketUseBed packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, nzw5xetYJC(0el7tBeHvf(this)));

      try {
         packetIn.getPlayer(qv3CeOTSQS(this).getWorld()).trySleep(packetIn.getBedPosition());
      } catch (Exception var3) {
      }

   }

   private static 0cD x0mXTU52qo(0cC var0) {
      return var0.player;
   }

   public void handleMaps(SPacketMaps packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 7Y7gxgaWdW(S9kQDts2Ud(this)));

      try {
         MapData mapData = ItemMap.loadMapData(packetIn.getMapId(), aLrLycG4cb(this).getWorld());
         if (mapData == null) {
            String object = l1jVoFIYsY("֖֚և֨") + packetIn.getMapId();
            mapData = new MapData(object);
            64183TnWSd(this).getWorld().setData(object, mapData);
         }

         packetIn.setMapdataTo(mapData);
      } catch (Exception var4) {
         Exception exception = var4;
         exception.printStackTrace();
      }

   }

   private static 0cC qv3CeOTSQS(0cL var0) {
      return var0.pbot;
   }

   private static void HopMitQlDJ(0cD var0, double var1) {
      var0.motionY = var1;
   }

   private static 0cC JFS30WbowM(0cL var0) {
      return var0.pbot;
   }

   private static float bVqFrI4Q6d(EntityPlayer var0) {
      return var0.rotationYaw;
   }

   private static 0cC _DV0rc6izq/* $FF was: 5DV0rc6izq*/(0cL var0) {
      return var0.pbot;
   }

   private static 0cC xUjmR4tEop(0cL var0) {
      return var0.pbot;
   }

   private static 0cC aBSGfOEu9H(0cL var0) {
      return var0.pbot;
   }

   private static 0cC JJ9F6SSepa(0cL var0) {
      return var0.pbot;
   }

   private static 0cC I6eyy9xFYL(0cL var0) {
      return var0.pbot;
   }

   private static float _IYX1fDDVD/* $FF was: 4IYX1fDDVD*/(EntityPlayer var0) {
      return var0.rotationPitch;
   }

   private static 0cH _elQFQKYJw/* $FF was: 8elQFQKYJw*/(0cC var0) {
      return var0.mc;
   }

   private static 0cH _j49TaZoF4/* $FF was: 6j49TaZoF4*/(0cC var0) {
      return var0.mc;
   }

   private static 0cC NtNmOyWtKQ(0cL var0) {
      return var0.pbot;
   }

   private static InventoryPlayer TrSwhqlSO7(0cD var0) {
      return var0.inventory;
   }

   private static 0cC AwTqGrML4Q(0cL var0) {
      return var0.pbot;
   }

   public void handleCombatEvent(SPacketCombatEvent packetIn) {
   }

   private static void _FSIt3BgFI/* $FF was: 7FSIt3BgFI*/(Entity var0, long var1) {
      var0.serverPosY = var1;
   }

   public void handleTimeUpdate(SPacketTimeUpdate packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, wo89vPi1in(JkTA1l3HGE(this)));
      dTQJx5nZCo(this).getWorld().setTotalWorldTime(packetIn.getTotalWorldTime());
      Znhd75qSrV(this).getWorld().setWorldTime(packetIn.getWorldTime());
   }

   private static 0cC QgsFStqiB9(0cL var0) {
      return var0.pbot;
   }

   private static void YyC6B4avj7(InventoryPlayer var0, int var1) {
      var0.currentItem = var1;
   }

   private static double w9Yni4q8S8(0cD var0) {
      return var0.posY;
   }

   private static 0cC PGR1JHLnGz(0cL var0) {
      return var0.pbot;
   }

   public void handleEntityTeleport(SPacketEntityTeleport packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, x8qTnZiwW5(EjNCVsnSK8(this)));
      Entity entity = 7bFWc7hdnc(this).getWorld().getEntityByID(packetIn.getEntityId());
      if (entity != null) {
         double d0 = packetIn.getX();
         double d1 = packetIn.getY();
         double d2 = packetIn.getZ();
         EntityTracker.updateServerPosition(entity, d0, d1, d2);
         if (!entity.canPassengerSteer()) {
            float f = (float)(packetIn.getYaw() * (27726 ^ -8231 ^ 30938 ^ -13787)) / Float.intBitsToFloat(5687 ^ '맒' ^ 24579 ^ -265460932 ^ 20069 ^ 214887 ^ 247407 ^ -1280482377);
            float f1 = (float)(packetIn.getPitch() * (25715 ^ -15557 ^ 11884 ^ -30644)) / Float.intBitsToFloat(29198 ^ 237263 ^ 1357 ^ 695850043 ^ 26775 ^ 215855 ^ '힂' ^ 1794754957);
            if (Math.abs(gYieIB3g46(entity) - d0) < Double.longBitsToDouble(-685380662424442701L ^ -3900950796366976845L) && Math.abs(f6jYOtAFrB(entity) - d1) < Double.longBitsToDouble(-6819665877066581529L ^ -7004313461788771865L) && Math.abs(nOKqEEWySh(entity) - d2) < Double.longBitsToDouble(6371114090072734392L ^ 7478999598405876408L)) {
               entity.setPositionAndRotationDirect(idnH6QdcT1(entity), mCfnVINXgr(entity), tCmjeyNTxP(entity), f, f1, 22299 ^ -20527 ^ 1386 ^ -608, (boolean)(22970 ^ -18390 ^ 12267 ^ -12678));
            } else {
               entity.setPositionAndRotationDirect(d0, d1, d2, f, f1, 15820 ^ -12291 ^ 15069 ^ -14097, (boolean)(17201 ^ -22575 ^ 27032 ^ -29319));
            }

            OU2AlVrMeB(entity, packetIn.getOnGround());
         }
      }

   }

   private static 0cC LA4UYgX1Tp(0cL var0) {
      return var0.pbot;
   }

   public void handleOpenWindow(SPacketOpenWindow packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, gtPD3Tr3cW(arXBBnRU2i(this)));
      0cD entityplayersp = DDeoldBAIQ(2i71SvrhCD(this));
      if (packetIn.getWindowTitle() != null) {
         gBVOIvRdhq(awo0Ft9H2V(this), packetIn.getWindowTitle().getFormattedText());
      } else {
         2yNarWCTIv(AtbVnwWrVn(this), l1jVoFIYsY(""));
      }

      if (l1jVoFIYsY("֚֞֙֒֔օ֖֑փ\u05cd֔֘֙փ֖֞֙֒օ").equals(packetIn.getGuiId())) {
         entityplayersp.displayGUIChest(new InventoryBasic(packetIn.getWindowTitle(), packetIn.getSlotCount()));
         gWa5LJi2gb(ZAYwaQAEmG(entityplayersp), packetIn.getWindowId());
      } else if (l1jVoFIYsY("֚֞֙֒֔օ֖֑փ\u05cdց֛֛֖֞\u0590֒օ").equals(packetIn.getGuiId())) {
         entityplayersp.displayVillagerTradeGui(new NpcMerchant(entityplayersp, packetIn.getWindowTitle()));
         go2LB8SxkA(P41TdNKA43(entityplayersp), packetIn.getWindowId());
      } else if (l1jVoFIYsY("ֲ֙փ֞փ֎ֿ֘օք֒").equals(packetIn.getGuiId())) {
         Entity entity = 9SmCwFKruq(this).getWorld().getEntityByID(packetIn.getEntityId());
         if (entity instanceof AbstractHorse) {
            entityplayersp.openGuiHorseInventory((AbstractHorse)entity, new ContainerHorseChest(packetIn.getWindowTitle(), packetIn.getSlotCount()));
            gAyGhDAjLN(b1Fm2NpyFs(entityplayersp), packetIn.getWindowId());
         }
      } else if (!packetIn.hasSlots()) {
         entityplayersp.displayGui(new LocalBlockIntercommunication(packetIn.getGuiId(), packetIn.getWindowTitle()));
         HAOgbWawwa(6x0lSgSIS4(entityplayersp), packetIn.getWindowId());
      } else {
         IInventory iinventory = new ContainerLocalMenu(packetIn.getGuiId(), packetIn.getWindowTitle(), packetIn.getSlotCount());
         entityplayersp.displayGUIChest(iinventory);
         qaOjj2VipH(tUJIljnvsk(entityplayersp), packetIn.getWindowId());
      }

   }

   private static 0cC QcjYkxttLB(0cL var0) {
      return var0.pbot;
   }

   private static 0cC gGtQFvKvbN(0cL var0) {
      return var0.pbot;
   }

   private static 0cC oB1MoRyDTc(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ogsbKgSvH3(0cL var0) {
      return var0.pbot;
   }

   private static 0cD WTLaFgSLvK(0cC var0) {
      return var0.player;
   }

   private static 0cC jfzatpNCFF(0cL var0) {
      return var0.pbot;
   }

   public void handlePlayerListItem(SPacketPlayerListItem packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, Q6Q7juY68G(5DV0rc6izq(this)));
      Iterator var2 = packetIn.getEntries().iterator();

      while(var2.hasNext()) {
         SPacketPlayerListItem.AddPlayerData spacketplayerlistitem$addplayerdata = (SPacketPlayerListItem.AddPlayerData)var2.next();
         if (packetIn.getAction() == b87YM2sfjc()) {
            4XjvcaMHC6(this).remove(spacketplayerlistitem$addplayerdata.getProfile().getId());
         } else {
            NetworkPlayerInfo networkplayerinfo = (NetworkPlayerInfo)OcQhdxtulq(this).get(spacketplayerlistitem$addplayerdata.getProfile().getId());
            if (packetIn.getAction() == LieealtWQB()) {
               networkplayerinfo = new NetworkPlayerInfo(spacketplayerlistitem$addplayerdata);
               allWF9dFRX(this).put(networkplayerinfo.getGameProfile().getId(), networkplayerinfo);
            }

            if (networkplayerinfo != null) {
               switch (evan6UO9ZO()[packetIn.getAction().ordinal()]) {
                  case 1:
                     networkplayerinfo.setGameType(spacketplayerlistitem$addplayerdata.getGameMode());
                     networkplayerinfo.setResponseTime(spacketplayerlistitem$addplayerdata.getPing());
                     break;
                  case 2:
                     networkplayerinfo.setGameType(spacketplayerlistitem$addplayerdata.getGameMode());
                     break;
                  case 3:
                     networkplayerinfo.setResponseTime(spacketplayerlistitem$addplayerdata.getPing());
                  case 4:
               }
            }
         }
      }

   }

   private static 0by bQLvQe9ory() {
      return 0cd.antibot;
   }

   public void handleSetSlot(SPacketSetSlot packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, QbMhSFKVaF(E0GLLv1rqP(this)));
      EntityPlayer entityplayer = yw6RAlh0PD(MqoJQliT9N(this));
      ItemStack itemstack = packetIn.getStack();
      int i = packetIn.getSlot();
      if (packetIn.getWindowId() == (-2314 ^ -24399 ^ 17291 ^ -5581)) {
         yEDaKgIBIk(entityplayer).setItemStack(itemstack);
      } else if (packetIn.getWindowId() == (-23210 ^ -15471 ^ 3861 ^ -27092)) {
         2pJSirgQ9z(entityplayer).setInventorySlotContents(i, itemstack);
      } else {
         boolean flag = 7106 ^ -8226 ^ 11898 ^ -5530;
         if (packetIn.getWindowId() == 0 && packetIn.getSlot() >= (13004 ^ -11008 ^ 4667 ^ -2605) && i < (32025 ^ -6161 ^ 3457 ^ -26790)) {
            if (!itemstack.isEmpty()) {
               ItemStack itemstack1 = iQbaQGAdjR(entityplayer).getSlot(i).getStack();
               if (itemstack1.isEmpty() || itemstack1.getCount() < itemstack.getCount()) {
                  itemstack.setAnimationsToGo(27906 ^ -18541 ^ 23771 ^ -31153);
               }
            }

            Kt54NAyIdM(entityplayer).putStackInSlot(i, itemstack);
         } else if (packetIn.getWindowId() == FwB99abKvl(W9cP8wOiXy(entityplayer)) && (packetIn.getWindowId() != 0 || flag == 0)) {
            tr4Y3r2yVd(entityplayer).putStackInSlot(i, itemstack);
         }
      }

   }

   private static 0cC WaGPbQlkyH(0cL var0) {
      return var0.pbot;
   }

   public void handleEntityVelocity(SPacketEntityVelocity packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, 9yLaFnc61d(QGY1TPaIyb(this)));
      if (packetIn.getEntityID() == t7Xp28UAwE(rcgGCaQm6A(this)).getEntityId()) {
         rxgnUf27q0(ICbFeYYpAN(this)).setVelocity((double)packetIn.getMotionX() / Double.longBitsToDouble(-9138514309252367882L ^ -4498469686921381386L), (double)packetIn.getMotionY() / Double.longBitsToDouble(3980274901110006310L ^ 8611875274139673126L), (double)packetIn.getMotionZ() / Double.longBitsToDouble(1196341692544079189L ^ 5775024769952142677L));
      }
   }

   public void handleWindowProperty(SPacketWindowProperty packetIn) {
   }

   private static 0cH riwEFA6uWt(0cC var0) {
      return var0.mc;
   }

   private static 0cD BPbRGn24N9(0cC var0) {
      return var0.player;
   }

   private static 0cC TuNTl1K2lg(0cL var0) {
      return var0.pbot;
   }

   private static 0cH EeyUK1yeQJ(0cC var0) {
      return var0.mc;
   }

   public void handleEntityMetadata(SPacketEntityMetadata packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, rjuAFBlof6(We9y6L8CGO(this)));
      Entity entity = JJ9F6SSepa(this).getWorld().getEntityByID(packetIn.getEntityId());
      if (entity != null && packetIn.getDataManagerEntries() != null) {
         entity.getDataManager().setEntryValues(packetIn.getDataManagerEntries());
      }

   }

   private static 0cH gOYSKPRgJU(0cC var0) {
      return var0.mc;
   }

   private static 0cH wo89vPi1in(0cC var0) {
      return var0.mc;
   }

   private static 0cC rGDNLtD9Wr(0cL var0) {
      return var0.pbot;
   }

   private static 0cC wd7CJQWPgZ(0cL var0) {
      return var0.pbot;
   }

   public _cL/* $FF was: 0cL*/(0cC bot) {
      this.pbot = bot;
      this.netManager = this.pbot.getNetworkManager();
      this.profile = this.pbot.getSession().getProfile();
      this.pbot.setPlayHandler(this);
   }

   private static 0cC mAGd9utqqG(0cL var0) {
      return var0.pbot;
   }

   private static 0bA ZhD0jwQi8s() {
      return 0bY.message;
   }

   private static 0cP MAOIgNqkHa(0cL var0) {
      return var0.netManager;
   }

   private static Container tr4Y3r2yVd(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cC VJY932bW2X(0cL var0) {
      return var0.pbot;
   }

   public void handleSpawnObject(SPacketSpawnObject packetIn) {
      PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, bgjgqW40G5(A4C4FB6243(this)));
      double d0 = packetIn.getX();
      double d2 = packetIn.getY();
      double d3 = packetIn.getZ();
      Entity entity = null;
      if (packetIn.getType() == (21773 ^ -15091 ^ 24629 ^ -4033)) {
         entity = EntityMinecart.create(Idct4IUt2p(this).getWorld(), d0, d2, d3, EntityMinecart.Type.getById(packetIn.getData()));
      } else if (packetIn.getType() == (27243 ^ -1383 ^ 31929 ^ -5103)) {
         Entity entity2 = gT0OvGHmDv(this).getWorld().getEntityByID(packetIn.getData());
         if (entity2 instanceof EntityPlayer) {
            entity = new EntityFishHook(h62gdUBgax(this).getWorld(), (EntityPlayer)entity2, d0, d2, d3);
         }

         packetIn.setData(18955 ^ -20943 ^ 31445 ^ -24849);
      } else if (packetIn.getType() == (14602 ^ -32116 ^ 21514 ^ -4176)) {
         entity = new EntityTippedArrow(qUFyhGeSvi(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (18434 ^ -21479 ^ 4075 ^ -5205)) {
         entity = new EntitySpectralArrow(dq2qeVoczM(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (14046 ^ -14530 ^ 12142 ^ -8525)) {
         entity = new EntitySnowball(T4DSxJ45nD(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (8021 ^ -17552 ^ 8096 ^ -17471)) {
         entity = new EntityLlamaSpit(aBSGfOEu9H(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(5421194188062049707L ^ 829985474190362027L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(8624336016260452080L ^ 3967825107791892208L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(-8446081221521819077L ^ -3857827994905593285L));
      } else if (packetIn.getType() == (2217 ^ -27052 ^ 15726 ^ -23596)) {
         entity = new EntityItemFrame(qBxnIiG7He(this).getWorld(), new BlockPos(d0, d2, d3), EnumFacing.byHorizontalIndex(packetIn.getData()));
         packetIn.setData(24808 ^ -3486 ^ 20850 ^ -15368);
      } else if (packetIn.getType() == (31610 ^ -21404 ^ 21085 ^ -31474)) {
         entity = new EntityLeashKnot(Yyjst6NA12(this).getWorld(), new BlockPos(MathHelper.floor(d0), MathHelper.floor(d2), MathHelper.floor(d3)));
         packetIn.setData(31865 ^ -8568 ^ 9717 ^ -30972);
      } else if (packetIn.getType() == (3833 ^ -27669 ^ 3211 ^ -28200)) {
         entity = new EntityEnderPearl(QSLdWBmlrM(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (26986 ^ -27171 ^ 22966 ^ -23223)) {
         entity = new EntityEnderEye(6GrgjtSuL9(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (16861 ^ -8721 ^ 10534 ^ -19112)) {
         entity = new EntityFireworkRocket(gD6Zje0A74(this).getWorld(), d0, d2, d3, JvyAlAGdRy());
      } else if (packetIn.getType() == (19053 ^ -25581 ^ 5838 ^ -16241)) {
         entity = new EntityLargeFireball(t2HSpCgm99(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(3105637867243095742L ^ 7756941488642508478L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(-5742594233981239146L ^ -1084957425605836650L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(3279812853385047827L ^ 7871021567256735507L));
         packetIn.setData(24601 ^ -15691 ^ 16469 ^ -7431);
      } else if (packetIn.getType() == (30355 ^ -12091 ^ 31230 ^ -8203)) {
         entity = new EntityDragonFireball(x6pintzFjW(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(3549857117151977868L ^ 8213686375015014796L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(4481099396784948749L ^ 9119595906744026637L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(8431250998190463578L ^ 3872693381617211994L));
         packetIn.setData(6894 ^ -20443 ^ 17378 ^ -5847);
      } else if (packetIn.getType() == (30773 ^ -31112 ^ 21156 ^ -21335)) {
         entity = new EntitySmallFireball(2W4o9pBa9q(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(7938237564494548765L ^ 3356598999831023389L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(8178980328111533631L ^ 3548505854988709439L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(558993434517016758L ^ 5151750260760613046L));
         packetIn.setData(29246 ^ -19043 ^ 29075 ^ -18896);
      } else if (packetIn.getType() == (16859 ^ -19260 ^ 11427 ^ -9730)) {
         entity = new EntityWitherSkull(YSC10OW1vW(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(7611329961423174991L ^ 2963263302255934799L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(349225509001443648L ^ 4929175223804705088L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(-3808746270248323962L ^ -8386866397702966138L));
         packetIn.setData(1603 ^ -25642 ^ 15243 ^ -23010);
      } else if (packetIn.getType() == (31546 ^ -27581 ^ 14902 ^ -10996)) {
         entity = new EntityShulkerBullet(J2wePSdKXL(this).getWorld(), d0, d2, d3, (double)packetIn.getSpeedX() / Double.longBitsToDouble(4050429627579260754L ^ 8685548437817810770L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(-4227713435945866465L ^ -8796826364145767649L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(2013216201776042273L ^ 6579514380208836897L));
         packetIn.setData(5143 ^ -6716 ^ 2361 ^ -1814);
      } else if (packetIn.getType() == (15807 ^ -24921 ^ 3110 ^ -20736)) {
         entity = new EntityEgg(VTIRtHB7WD(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (3048 ^ -8244 ^ 14490 ^ -4879)) {
         entity = new EntityEvokerFangs(Oc7jdpEGgc(this).getWorld(), d0, d2, d3, Float.intBitsToFloat('\ue455' ^ 4449 ^ '\uec3a' ^ -1151907175 ^ '쟀' ^ 25874 ^ '\uf754' ^ -1151924719), 31417 ^ -13009 ^ 27838 ^ -9432, (EntityLivingBase)null);
      } else if (packetIn.getType() == (18201 ^ -8770 ^ 32655 ^ -6815)) {
         entity = new EntityPotion(xr9oij8Bnb(this).getWorld(), d0, d2, d3, 5PF4YyYeQf());
         packetIn.setData(1615 ^ -461 ^ 24426 ^ -22762);
      } else if (packetIn.getType() == (12306 ^ -6399 ^ 27085 ^ -16747)) {
         entity = new EntityExpBottle(jW4h79oSey(this).getWorld(), d0, d2, d3);
         packetIn.setData(29550 ^ -24036 ^ 15656 ^ -5030);
      } else if (packetIn.getType() == (14652 ^ -5821 ^ 9793 ^ -2497)) {
         entity = new EntityBoat(SO4tRznvQS(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (29424 ^ -2312 ^ 11340 ^ -22410)) {
         entity = new EntityTNTPrimed(gV15yyDU6x(this).getWorld(), d0, d2, d3, (EntityLivingBase)null);
      } else if (packetIn.getType() == (9380 ^ -10505 ^ 8095 ^ -4734)) {
         entity = new EntityArmorStand(7NqbBENqFO(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (12692 ^ -27973 ^ 10234 ^ -31514)) {
         entity = new EntityEnderCrystal(tYWngaV1u4(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (26254 ^ -7558 ^ 10387 ^ -21403)) {
         entity = new EntityItem(dSHWoEZUi1(this).getWorld(), d0, d2, d3);
      } else if (packetIn.getType() == (31437 ^ -828 ^ 24046 ^ -9311)) {
         entity = new EntityFallingBlock(29tKGqHI6N(this).getWorld(), d0, d2, d3, Block.getStateById(packetIn.getData() & (114347 ^ 130782 ^ 3833 ^ '녳')));
         packetIn.setData(25600 ^ -26118 ^ 19796 ^ -20306);
      } else if (packetIn.getType() == (17205 ^ -19921 ^ 17612 ^ -18987)) {
         entity = new EntityAreaEffectCloud(jfzatpNCFF(this).getWorld(), d0, d2, d3);
      }

      if (entity != null) {
         EntityTracker.updateServerPosition((Entity)entity, d0, d2, d3);
         OSj1nyUIsd((Entity)entity, (float)(packetIn.getPitch() * (15120 ^ -8939 ^ 26887 ^ -29078)) / Float.intBitsToFloat(15263 ^ 233035 ^ 16878 ^ 1013314844 ^ 2782 ^ 252931 ^ 25082 ^ 2145760769));
         0ENcyKTbQn((Entity)entity, (float)(packetIn.getYaw() * (12048 ^ -11373 ^ 21444 ^ -20945)) / Float.intBitsToFloat(3307 ^ 111550 ^ 4979 ^ 598272531 ^ '\ue7e1' ^ 22554 ^ '\uf691' ^ 1613303647));
         Entity[] aentity = ((Entity)entity).getParts();
         if (aentity != null) {
            int i = packetIn.getEntityID() - ((Entity)entity).getEntityId();
            Entity[] array = aentity;
            int length = aentity.length;

            for(int j = 2924 ^ -11724 ^ 30655 ^ -20761; j < length; ++j) {
               Entity entity3 = array[j];
               entity3.setEntityId(entity3.getEntityId() + i);
            }
         }

         ((Entity)entity).setEntityId(packetIn.getEntityID());
         ((Entity)entity).setUniqueId(packetIn.getUniqueId());
         J6aZxt91P6(this).getWorld().addEntityToWorld(packetIn.getEntityID(), (Entity)entity);
         if (packetIn.getData() > 0) {
            if (packetIn.getType() == (17482 ^ -6315 ^ 26966 ^ -13707) || packetIn.getType() == (26426 ^ -2597 ^ 25365 ^ -3665)) {
               Entity entity4 = CPDOSFdN7F(this).getWorld().getEntityByID(packetIn.getData() - (5959 ^ -8191 ^ 29192 ^ -31409));
               if (entity4 instanceof EntityLivingBase && entity instanceof EntityArrow) {
                  4dSHZUNc9e((EntityArrow)entity, entity4);
               }
            }

            ((Entity)entity).setVelocity((double)packetIn.getSpeedX() / Double.longBitsToDouble(-8806534871215491426L ^ -4217718694645844322L), (double)packetIn.getSpeedY() / Double.longBitsToDouble(4640910262447257665L ^ 60960547643996225L), (double)packetIn.getSpeedZ() / Double.longBitsToDouble(-7434864269823442573L ^ -2851536855299653261L));
         }
      }

   }

   private static void nNjliGTvZM(EntityOtherPlayerMP var0, double var1) {
      var0.prevPosY = var1;
   }

   public void handleRecipeBook(SPacketRecipeBook packetIn) {
   }

   private static 0dB BJeGepbwKp(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static void rd2FOncDJn(EntityOtherPlayerMP var0, double var1) {
      var0.lastTickPosZ = var1;
   }

   private static 0cC bb4ede9y1z(0cL var0) {
      return var0.pbot;
   }

   private static 0cC ipOxbODm99(0cL var0) {
      return var0.pbot;
   }

   private static 0cC dOKtHjggDx(0cL var0) {
      return var0.pbot;
   }

   private static 0cC PhViHkVoQl(0cL var0) {
      return var0.pbot;
   }

   private static 0cC tOOFrXN2IT(0cL var0) {
      return var0.pbot;
   }

   private static 0cC gQmj7pziSo(0cL var0) {
      return var0.pbot;
   }

   private static 0cH tmw2z1rn0O(0cC var0) {
      return var0.mc;
   }
}
