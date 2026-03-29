package neo;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.vecmath.Vector2f;
import net.minecraft.block.BlockColored;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Session;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class 0cC {
   public Session session;
   public final 0cT serverPinger;
   public boolean deleted;
   public String nickname;
   public 0cL netHandler;
   public String tabFooter;
   public static CopyOnWriteArrayList<0cC> bots = new CopyOnWriteArrayList();
   public String host;
   public final 0cV botBaritone;
   public 0cU world;
   public 0ek captchaDetector;
   public List<BufferedImage> gifExemplars = new ArrayList();
   public 0cP networkManager;
   public 0cD player;
   public String windowTitle;
   public 0ek c;
   public 0ek gameGuardCheck;
   public boolean needAutoRespawn;
   public 0ek stimer2;
   public boolean recorderActive = 32315 ^ -5092 ^ 22817 ^ -13562;
   public final 0ek timeAutoRespawn = new 0ek();
   public String tabHeader;
   public static final Pattern COLOR_PATTERN = Pattern.compile(feMuiekQFB("ͽ̼ͪͼϲ̎ͥ\u0378ͬ̔\u0378̞̓\u0378̇̈̚"));
   public 0ek spammer;
   public final HashMap<String, Object> parameters;
   public int port;
   public 0eq proxy;
   public 0cH mc;
   public 0ek stimer1;
   public 0ek stimer3;
   public int worldId;

   private static void _DTM7OBlBc/* $FF was: 9DTM7OBlBc*/(0cC var0, 0eq var1) {
      var0.proxy = var1;
   }

   private static double z6Y1WjdeTM(0cD var0) {
      return var0.posY;
   }

   public void setDeleted(boolean deleted) {
      tir8nqVYN1(this, deleted);
   }

   private static 0cE IbvlBsjPvm(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH bPmwlvstBT(0cC var0) {
      return var0.mc;
   }

   private static 0ek AoBrnxlRED(0cC var0) {
      return var0.captchaDetector;
   }

   private static int yYGpnzPWIw(Container var0) {
      return var0.windowId;
   }

   private static double cw0lYHJFnL(0cD var0) {
      return var0.posX;
   }

   private static 0cQ jYaIqgfX6Y(0cH var0) {
      return var0.playerController;
   }

   private static void OjY6FXbbL9(PlayerCapabilities var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static 0cD _TThvpTvR5/* $FF was: 3TThvpTvR5*/(0cC var0) {
      return var0.player;
   }

   private static 0bz _rAdexCnOP/* $FF was: 4rAdexCnOP*/() {
      return 0cd.joinFixerDelay;
   }

   private static 0cH yBGNGbDaWj(0cC var0) {
      return var0.mc;
   }

   private static 0cD yq836vub4i(0cC var0) {
      return var0.player;
   }

   public void reconnect(boolean force) {
      if (force || !this.isDeleted()) {
         if (!force && !EKGUB79tBA(0bK.getInstance()).getModule(0ca.class).isModuleState()) {
            this.stopAndRemove();
         } else {
            0et.sleep((long)wNa9bGSqzF(TADVE2CxI4()));
            if (NqDgt1tYJF(YtN5gYAWwq())) {
               0dK.formatMsg(feMuiekQFB("݄ݫܗ͵ͳ̱ͳ̹") + this.getNickname() + feMuiekQFB("͵ͳ̳ͳ̹݊ݠܕݠݪݫݡݯݮܛܒݠݨݭݠ͵ݤݫܗݥͻͻͻ"));
            }

            this.resetParameters();
            if (this.isOnline()) {
               this.disconnect();
               this.stopBot();
               this.connect();
            } else {
               this.startBot();
            }
         }

      }
   }

   public int getIntegerParameter(String key) {
      return (Integer)this.getParameters().get(key);
   }

   private static Container jJB1Xdhisl(0cD var0) {
      return var0.openContainer;
   }

   private static 0cH cCqQ1qNA4g(0cC var0) {
      return var0.mc;
   }

   private static 0dB t7gYqTgkCe(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static EnumHand _ilV4OI0ma/* $FF was: 9ilV4OI0ma*/() {
      return EnumHand.MAIN_HAND;
   }

   public void setPlayHandler(0cL netHandler) {
      w0agxgOcjW(this, netHandler);
   }

   private static void _LvUD1ECsn/* $FF was: 4LvUD1ECsn*/(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0cD _1rfDyg6pJ/* $FF was: 21rfDyg6pJ*/(0cC var0) {
      return var0.player;
   }

   private static String ATvjrN4T8g(0cC var0) {
      return var0.windowTitle;
   }

   public void setProxy(0eq proxy) {
      9DTM7OBlBc(this, proxy);
   }

   private static boolean kBTV8rG12p(0cC var0) {
      return var0.needAutoRespawn;
   }

   private static 0cQ nTc4xDEM2l(0cH var0) {
      return var0.playerController;
   }

   private static float JOh7VlZGGQ(Vector2f var0) {
      return var0.x;
   }

   private static 0cD J8SLPjO23n(0cC var0) {
      return var0.player;
   }

   private static CopyOnWriteArrayList b5cFrWjEdL() {
      return bots;
   }

   public static String stripColor(String input) {
      return BYziCj9WWx().matcher(input).replaceAll(feMuiekQFB(""));
   }

   private static void aSoSp99YOs(0cE var0, boolean var1) {
      var0.keyBindSprint = var1;
   }

   private static void qvpBWTXIce(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cL Qb3eeRU941(0cC var0) {
      return var0.netHandler;
   }

   private static KeyBinding PlpVwMivTy(GameSettings var0) {
      return var0.keyBindForward;
   }

   public void setFlying(boolean value) {
      if (this.isOnline()) {
         OjY6FXbbL9(b0B4GONnrA(W9rcyaLrPF(this)), value);
      }

   }

   private static Channel UXZ9zFf9Ut(0cP var0) {
      return var0.channel;
   }

   private static boolean cPnNeuawO2(0bv var0) {
      return var0.value;
   }

   private static boolean BRaaR65wnw(0bv var0) {
      return var0.value;
   }

   private static double ltyJcajkDl(Entity var0) {
      return var0.posZ;
   }

   private static Container w9IGYOrVQL(0cD var0) {
      return var0.openContainer;
   }

   private static 0ek VybN2wLhge(0cC var0) {
      return var0.c;
   }

   public Session getSession() {
      return DZGjBz7A5g(this);
   }

   private static EnumHand LN6TdROdWb() {
      return EnumHand.MAIN_HAND;
   }

   private static void Vg14mdywOV(0cC var0, String var1) {
      var0.host = var1;
   }

   private static 0cD ovIQFZ1RjC(0cC var0) {
      return var0.player;
   }

   public static void removeBot(0cC pbot) {
      hXa6RJ0sIb().remove(pbot);
   }

   private static boolean AqAEdCJiQO() {
      return 0d.mirror;
   }

   private static boolean edYtxHIWwt(KeyBinding var0) {
      return var0.pressed;
   }

   private static 0cD _NoDMTU1J1/* $FF was: 8NoDMTU1J1*/(0cC var0) {
      return var0.player;
   }

   private static void aqpmWG2GSx(0cD var0, World var1) {
      var0.world = var1;
   }

   private static void DGnCToWihB(InventoryPlayer var0, int var1) {
      var0.currentItem = var1;
   }

   private static boolean NqDgt1tYJF(0bv var0) {
      return var0.value;
   }

   private static void IWjKiZ2PZ5(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0cT v6I4WwD61R(0cC var0) {
      return var0.serverPinger;
   }

   private static void NdZpL4m5YY(0cE var0, boolean var1) {
      var0.keyBindSneak = var1;
   }

   private static GameSettings LUX2rP1AD5() {
      return Minecraft.gameSettings;
   }

   public void swapHands() {
      if (this.isOnline()) {
         this.sendPacket(new CPacketPlayerDigging(zFWqI3zNfU(), cqKlNibam9(), BqOTCkSi40()));
      }

   }

   private static float qMXAKly3xV(Vector2f var0) {
      return var0.y;
   }

   private static double w9DmwwAYky(Entity var0) {
      return var0.posX;
   }

   private static boolean q9KIDYyNLA() {
      return 0d.attack;
   }

   public static void runBot(String nickname, 0eq proxy, String host, int port) {
      0cC pbot = new 0cC(nickname, proxy, host, port);
      pbot.startBot();
   }

   private static double _21VvVtOTj/* $FF was: 421VvVtOTj*/(0cD var0) {
      return var0.posZ;
   }

   private static boolean _rP0a6oroo/* $FF was: 1rP0a6oroo*/() {
      return 0d.joinloop;
   }

   private static 0cD lzj9iTNgOH(0cC var0) {
      return var0.player;
   }

   public String getHost() {
      return wZmYJYwH7p(this);
   }

   private static 0bv DQhzQtCAES() {
      return 0bZ.needLogin;
   }

   private static 0cD W9rcyaLrPF(0cC var0) {
      return var0.player;
   }

   private static 0cE CAnine4zng(0cH var0) {
      return var0.gameSettings;
   }

   private static 0bA aW4FM8PKZn() {
      return 0bZ.registerCommand;
   }

   private static float wNa9bGSqzF(0bz var0) {
      return var0.value;
   }

   private static 0ek YUq2ZzivYn(0cC var0) {
      return var0.spammer;
   }

   private static boolean _YOzynbTX0/* $FF was: 2YOzynbTX0*/(KeyBinding var0) {
      return var0.pressed;
   }

   public void rclickStop() {
      if (this.isOnline()) {
         oWV1UngNvk(vjgqfejRL6(this)).onStoppedUsingItem(dZPYHn1ORA(this));
      }

   }

   private static double AoqRmyJF9x(Entity var0) {
      return var0.posZ;
   }

   public void useItem() {
      if (this.isOnline()) {
         nBzTFxNbJg(cCqQ1qNA4g(this)).processRightClick(BNjBGJ5szk(this), this.getWorld(), 9ilV4OI0ma());
      }

   }

   private static float Sa9SEt4Sw9(0bz var0) {
      return var0.value;
   }

   private static 0cD bvGqk0GC9t(0cC var0) {
      return var0.player;
   }

   private static InventoryPlayer Y02QcJfBML(0cD var0) {
      return var0.inventory;
   }

   public final 0cU getWorld() {
      return JxCogSYXX5(this);
   }

   private static HashMap _jbGJEB1y2/* $FF was: 4jbGJEB1y2*/(0cC var0) {
      return var0.parameters;
   }

   private static 0cD q316lmJtbQ(0cC var0) {
      return var0.player;
   }

   private static void tir8nqVYN1(0cC var0, boolean var1) {
      var0.deleted = var1;
   }

   private static 0cH GirQ8CFQv7(0cC var0) {
      return var0.mc;
   }

   private static 0cD yAgbdSali8(0cC var0) {
      return var0.player;
   }

   private static World KwTvEYC29b(0cD var0) {
      return var0.world;
   }

   private static 0bv _QXB4r4Hv9/* $FF was: 7QXB4r4Hv9*/() {
      return 0cd.gameguard;
   }

   private static EntityEquipmentSlot _Vb32p69Oq/* $FF was: 1Vb32p69Oq*/(ItemArmor var0) {
      return var0.armorType;
   }

   private static 0cH WoaOBzbTUa(0cC var0) {
      return var0.mc;
   }

   private static Container QikMnaaGFS(0cD var0) {
      return var0.openContainer;
   }

   private static float BKl4Z47wTV(0cD var0) {
      return var0.rotationYaw;
   }

   private static ClickType rim5TdjND6() {
      return ClickType.QUICK_MOVE;
   }

   private static int g9epUQ9S4A(Container var0) {
      return var0.windowId;
   }

   private static boolean SrGUyktsbF(0bv var0) {
      return var0.value;
   }

   private static 0cD tT5j4o59E2(0cC var0) {
      return var0.player;
   }

   private static 0bA oD8iRcGGf9() {
      return 0bZ.authCommand;
   }

   private static World ZLDen2jq7J(0cD var0) {
      return var0.world;
   }

   private static EntityPlayerSP Wwu26eTQYI() {
      return Minecraft.player;
   }

   public HashMap<String, Object> getParameters() {
      return 4jbGJEB1y2(this);
   }

   private static float _WxNRlyYLy/* $FF was: 9WxNRlyYLy*/(Vector2f var0) {
      return var0.x;
   }

   private static int FlUYWfUVlU(Container var0) {
      return var0.windowId;
   }

   private static void PFQPnblrDH(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static KeyBinding cjT6cDsezF(GameSettings var0) {
      return var0.keyBindSneak;
   }

   private static 0cD TDWI2IGGeb(0cC var0) {
      return var0.player;
   }

   private static 0cD ojWryDBYCr(0cC var0) {
      return var0.player;
   }

   private static double PuHu8Ml92n(0cD var0) {
      return var0.posX;
   }

   private static 0bN EKGUB79tBA(0bK var0) {
      return var0.moduleManager;
   }

   private static void zZQK5FmTGO(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0bz TADVE2CxI4() {
      return 0ca.reconnecttime;
   }

   private static 0cP LOyS8a73na(0cC var0) {
      return var0.networkManager;
   }

   private static 0cE _PalhUl8Hf/* $FF was: 7PalhUl8Hf*/(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD d4cT5bpLSD(0cC var0) {
      return var0.player;
   }

   private static 0cE sJiUaLvB1V(0cH var0) {
      return var0.gameSettings;
   }

   private static float VQ4SNV9JLg(Vector2f var0) {
      return var0.y;
   }

   private static double enZTKigv3r(Entity var0) {
      return var0.posX;
   }

   private static boolean _dlt78eYY2/* $FF was: 1dlt78eYY2*/(0bv var0) {
      return var0.value;
   }

   private static 0cH zAHAbsQWgA(0cC var0) {
      return var0.mc;
   }

   private static 0ek S6gxtj219V(0cC var0) {
      return var0.c;
   }

   private static 0cD lbrlTNSIGf(0cC var0) {
      return var0.player;
   }

   private static 0cD St1PByqCwt(0cC var0) {
      return var0.player;
   }

   private static void biSvAHbHqc(0cD var0, double var1) {
      var0.motionX = var1;
   }

   private static boolean _YaqMv3JIr/* $FF was: 6YaqMv3JIr*/(0cC var0) {
      return var0.needAutoRespawn;
   }

   public void sendPacket(Packet packet) {
      if (this.getPlayHandler() != null) {
         this.getPlayHandler().sendPacket(packet);
      }

   }

   private static double LbOJ62dDOj(0cD var0) {
      return var0.posY;
   }

   private static 0eq xBQndXqF9G(0cC var0) {
      return var0.proxy;
   }

   private static GameSettings y2l6K93ojt() {
      return Minecraft.gameSettings;
   }

   public void leftClick() {
      TV76u0j84c(this).clickMouse();
   }

   private static 0ek onwBia2hiV(0cC var0) {
      return var0.c;
   }

   private static 0cD qfg9OnqOO9(0cC var0) {
      return var0.player;
   }

   private static 0eq nn20fJX16J(0cC var0) {
      return var0.proxy;
   }

   private static 0cD yUemajdQCG(0cC var0) {
      return var0.player;
   }

   private static void vXj8QLr4Yr(0cC var0, 0cP var1) {
      var0.networkManager = var1;
   }

   public void setPort(int port) {
      g14wrvO2D4(this, port);
   }

   private static CopyOnWriteArrayList _FT46JQwo5/* $FF was: 4FT46JQwo5*/() {
      return bots;
   }

   private static Container qWNKBbh7yh(0cD var0) {
      return var0.openContainer;
   }

   private static 0cD rj6RuBbXn4(0cC var0) {
      return var0.player;
   }

   private static 0cD n0W17siDnE(0cC var0) {
      return var0.player;
   }

   private static void ZgIrIimefj(0cC var0, 0cU var1) {
      var0.world = var1;
   }

   private static int _7lGvB24Tq/* $FF was: 37lGvB24Tq*/(0cC var0) {
      return var0.port;
   }

   private static 0cD pNbyrnJj2M(0cC var0) {
      return var0.player;
   }

   private static 0cQ oWV1UngNvk(0cH var0) {
      return var0.playerController;
   }

   private static 0cD JbZydOy6r2(0cC var0) {
      return var0.player;
   }

   private static 0cD _9xaGButRA/* $FF was: 19xaGButRA*/(0cC var0) {
      return var0.player;
   }

   private static 0cH yKjpNcDbvv(0cC var0) {
      return var0.mc;
   }

   private static void _3rg3L1F6B/* $FF was: 13rg3L1F6B*/(0cC var0, 0cH var1) {
      var0.mc = var1;
   }

   private static 0cD IaBZ1XW6YU(0cC var0) {
      return var0.player;
   }

   private static ClickType XzdWzRSAgS() {
      return ClickType.THROW;
   }

   public void setWorld(0cU newWorld) {
      if (MbSi2tabyz(this) != null) {
         aqpmWG2GSx(D5lvN63Iy3(this), newWorld);
      }

      ZgIrIimefj(this, newWorld);
   }

   private static 0cQ ZQLeHLPMSr(0cH var0) {
      return var0.playerController;
   }

   private static 0cH n0odnCZKK6(0cC var0) {
      return var0.mc;
   }

   private static Pattern BYziCj9WWx() {
      return COLOR_PATTERN;
   }

   public String getTabFooter() {
      return DBO765irlc(this);
   }

   private static ItemMap FI3JJU4doy() {
      return Items.FILLED_MAP;
   }

   public void connect() {
      this.resetParameters();

      try {
         if (iT69xT5MRK(NAe9oJmDsj())) {
            v6I4WwD61R(this).ping(this);
            0et.sleep(4000L);
         }

         InetAddress inetaddress = InetAddress.getByName(this.getHost());
         vXj8QLr4Yr(this, 0cP.createNetworkManagerAndConnect(inetaddress, this.getPort(), this, 7ZeQT7wQNV(this)));
         dVerGweRAs(this).setNetHandler(new 0cJ(this.getNetworkManager(), this));
         tbOZ71EmcO(this).sendPacket(new C00Handshake(this.getHost(), this.getPort(), kOmWAl6b6R()));
         0et.sleep(200L);
         Da6r1XVidF(this).sendPacket(new CPacketLoginStart(this.getSession().getProfile()));
         if (SrGUyktsbF(vQmYHvG4RX()) && !LOyS8a73na(this).isChannelOpen()) {
            if (cPnNeuawO2(rJAavI5bJg()) && xBQndXqF9G(this) != null) {
               0bL.getInstance().getProxyManager().removeProxy(nn20fJX16J(this));
            }

            if (1dlt78eYY2(u1YKd7LDcd()) && 1rP0a6oroo()) {
               this.setProxy(0bL.getInstance().getProxyManager().getProxy());
               0et.sleep((long)Sa9SEt4Sw9(4rAdexCnOP()));
               this.reconnect((boolean)(15027 ^ -22979 ^ 6570 ^ -31451));
            }
         }
      } catch (Exception var2) {
         Exception ex = var2;
         ex.printStackTrace();
      }

   }

   public void clickEntity(int id, boolean visual) {
      if (visual) {
         Entity entity = this.getWorld().getEntityByID(id);
         if (entity != null && 0dm.getDistance(this, 0j1DNrcTFf(entity), eaKnyos9jq(entity), AoqRmyJF9x(entity)) < Float.intBitsToFloat(1909 ^ '냃' ^ '빈' ^ 2000167614 ^ '먨' ^ 1040953 ^ 'ꇗ' ^ 938988166)) {
            Vector2f vector2f = 0dm.getBlockAngles(enZTKigv3r(entity), Y8QLpQbl2R(entity), ltyJcajkDl(entity), PuHu8Ml92n(yq836vub4i(this)), tK7gQN6rx3(lzj9iTNgOH(this)), jr6AnDA9SV(An2JJS8i1v(this)));
            4LvUD1ECsn(bvGqk0GC9t(this), 0dm.normalizeYaw(qMXAKly3xV(vector2f)));
            IWjKiZ2PZ5(7Stjnt4WRe(this), 0dm.normalizePitch(ogbTgNTnBg(vector2f)));
            5Zrv72mudG(J8SLPjO23n(this)).sendPacket(new CPacketUseEntity(entity, FLLTY29WJQ()));
         }
      } else {
         0dK.formatMsg(feMuiekQFB("݄ݫܗ͵ͳ̱ͳ̹") + this.getNickname() + feMuiekQFB("ͳͣ͵݈ݠ͵ܖݯݥݢݥݨ͵ܘݨܗݭܗݭ͵ܔ͵ܗݥݯݭݩ͵̜̑\u0379͵ݫܗݪܕݥݧݮܚܛ͵ݫݤܞܒݨܞݬ͵ݯݮݭݯ͵ݤݠݢ͵ܕݫܗݥܓݭݭͻ"));
         EivJbQQe1r(TDWI2IGGeb(this)).sendPacket(new CPacketUseEntity(id, kon7aNM62D()));
      }

   }

   private static 0cH n1s8Tm7s7n(0cC var0) {
      return var0.mc;
   }

   private static 0cD l4v6sKNlOd(0cC var0) {
      return var0.player;
   }

   private static 0cH ul6hvUt5ti(0cC var0) {
      return var0.mc;
   }

   public void sendMessage(String msg) {
      if (this.isOnline()) {
         this.sendPacket(new CPacketChatMessage(msg));
      }

   }

   private static 0by gba4Z4iM4P() {
      return 0bZ.passwordGenerator;
   }

   private static 0cU JxCogSYXX5(0cC var0) {
      return var0.world;
   }

   private static 0ek TESG8Xg3rq(0cC var0) {
      return var0.c;
   }

   private static CPacketPlayerDigging.Action zFWqI3zNfU() {
      return CPacketPlayerDigging.Action.SWAP_HELD_ITEMS;
   }

   private static 0cH yan9j1gDdT(0cC var0) {
      return var0.mc;
   }

   private static boolean FiwqOn20YB(0cC var0) {
      return var0.deleted;
   }

   public static 0cC getBot(String name) {
      Iterator var1 = getBotList().iterator();

      0cC bot;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         bot = (0cC)var1.next();
      } while(!OXFcmjn0ME(bot).equalsIgnoreCase(name));

      return bot;
   }

   private static float vaKmi6lMPJ(EntityPlayerSP var0) {
      return var0.rotationPitch;
   }

   private static 0cD SegkAq6FKL(0cC var0) {
      return var0.player;
   }

   private static InventoryPlayer QC4ymGXBjQ(0cD var0) {
      return var0.inventory;
   }

   private static void w0agxgOcjW(0cC var0, 0cL var1) {
      var0.netHandler = var1;
   }

   private static 0cH on4wL22bW2(0cC var0) {
      return var0.mc;
   }

   private static Container zDILYu4FTQ(0cD var0) {
      return var0.openContainer;
   }

   public void closeWindow() {
      if (this.isOnline()) {
         Httm3FIV8k(this).closeScreenAndDropStack();
         this.sendPacket(new CPacketCloseWindow(FlUYWfUVlU(jJB1Xdhisl(lbrlTNSIGf(this)))));
         PFQPnblrDH(qWNKBbh7yh(d4cT5bpLSD(this)), 11277 ^ -20239 ^ 9315 ^ -18273);
      }

   }

   private static ClickType _7ByRGTVkr/* $FF was: 47ByRGTVkr*/() {
      return ClickType.PICKUP;
   }

   private static 0cE zMEgiUFrQ6(0cH var0) {
      return var0.gameSettings;
   }

   private static GameSettings bVPs1m45am() {
      return Minecraft.gameSettings;
   }

   private static ItemMap _YwXZPbPUi/* $FF was: 7YwXZPbPUi*/() {
      return Items.FILLED_MAP;
   }

   private static 0cD uZZjBOGNPs(0cC var0) {
      return var0.player;
   }

   private static 0cD yToL12Tc8A(0cC var0) {
      return var0.player;
   }

   private static void yvKFAbdiLt(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0cD An2JJS8i1v(0cC var0) {
      return var0.player;
   }

   private static 0ek VvRjv0ICvR(0cC var0) {
      return var0.timeAutoRespawn;
   }

   public void changeSlot(int slot) {
      if (this.isOnline() && liGIWvreCc(Y02QcJfBML(FCLsdKUp6J(this))) != slot) {
         DGnCToWihB(kTLUYomdE7(qb5paDb6iE(this)), slot);
         this.sendPacket(new CPacketHeldItemChange(slot));
      }

   }

   private static boolean WsolD6EcTp(0bv var0) {
      return var0.value;
   }

   public boolean haveCreative() {
      return (boolean)(this.isOnline() && jYaIqgfX6Y(bPmwlvstBT(this)).isInCreativeMode() ? 16811 ^ -21323 ^ 32186 ^ -28507 : 11408 ^ -29547 ^ 16117 ^ -24848);
   }

   private static CopyOnWriteArrayList S6gDvqlzQ9() {
      return bots;
   }

   private static 0cD _HYHBUVvmA/* $FF was: 4HYHBUVvmA*/(0cC var0) {
      return var0.player;
   }

   private static KeyBinding rVcFTSInFa(GameSettings var0) {
      return var0.keyBindBack;
   }

   public long getLongParameter(String key) {
      return (Long)this.getParameters().get(key);
   }

   private static void gqXdfADXQP(0cE var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static 0bv _sHh393Cdq/* $FF was: 5sHh393Cdq*/() {
      return 0ce.gifCaptchaFix;
   }

   private static 0cD _WnDbtmOHa/* $FF was: 5WnDbtmOHa*/(0cC var0) {
      return var0.player;
   }

   private static 0cD EWo0yLYlaX(0cC var0) {
      return var0.player;
   }

   public static List<0cC> getBotList() {
      return 4FT46JQwo5();
   }

   public void stopAndRemove() {
      this.stopBot();
      removeBot(this);
   }

   private static 0bv vQmYHvG4RX() {
      return 0cd.useProxy;
   }

   private static Session DZGjBz7A5g(0cC var0) {
      return var0.session;
   }

   private static 0cQ v2rN9Ldeph(0cH var0) {
      return var0.playerController;
   }

   private static 0dB irmiYwOoag(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static boolean _OyJewd33W/* $FF was: 4OyJewd33W*/() {
      return 0d.randommove;
   }

   private static 0bv NAe9oJmDsj() {
      return 0cd.pingServer;
   }

   public void tick() {
      try {
         if (AQIBnPIbYn(this).isRiding()) {
            this.getWorld().updateEntities();
         } else {
            aVgAgtHkgs(this).onUpdate();
         }

         v2rN9Ldeph(on4wL22bW2(this)).updateController();
         if (EWo0yLYlaX(this) != null && zAHAbsQWgA(this) != null) {
            lG1vxOSFZ7(this).execTasks();
            this.autoRespawn();
         }

         0dB var10000 = t7gYqTgkCe(0bK.getInstance());
         String var10001 = feMuiekQFB("̴̡̺̻̥̱̰̀");
         Object[] var10002 = new Object[24298 ^ -17686 ^ 5177 ^ -4040];
         var10002[19916 ^ -32194 ^ 30875 ^ -18583] = this;
         var10000.invokeMethod(var10001, var10002);
         this.getBaritone().onUpdate();
         if (0dl.isEnabled() && o4qHh4triR(this).hasReached((double)0dl.getDelay())) {
            YUq2ZzivYn(this).reset();
            this.sendMessage(0dy.format(0dl.getText()));
         }

         if (AqAEdCJiQO()) {
            4RDNbvzDCo(dD3C6wnRZy(this), BdnYseQLWa(j4X7mljllQ()));
            93W4Uxgtql(iIhl6iIwQD(this), vaKmi6lMPJ(Wwu26eTQYI()));
            1vbgWaJJ6j(IbvlBsjPvm(GirQ8CFQv7(this)), 2YOzynbTX0(PlpVwMivTy(V7Bzl1xUIk())));
            8BQIvlp2hN(aQVv2OHcSe(NZ3b1uqa6y(this)), iPdsvJy596(rVcFTSInFa(rHqqtYyfop())));
            fvenTqImU6(I9EabbnyL2(pggQ1RVIkI(this)), edYtxHIWwt(G3y1BFeYFw(4y6TfGBbAk())));
            MUKyAsYtLD(CAnine4zng(n0odnCZKK6(this)), 7nJI29aQSY(mfbGWusihm(tdwDQV3Tty())));
            aSoSp99YOs(sJiUaLvB1V(dB54OOmdCl(this)), lYPPUtcoLx(aWjanibHEG(bVPs1m45am())));
            NdZpL4m5YY(9EyG16jsaI(BlVyJNRmLn(this)), TB65EeOPSw(cjT6cDsezF(y2l6K93ojt())));
            gqXdfADXQP(7PalhUl8Hf(6B1Dd8J14l(this)), Aa43gvlLiN(j3EhgX2AWt(LUX2rP1AD5())));
         }

         if (4OyJewd33W()) {
            0cD var19 = 4HYHBUVvmA(this);
            zZQK5FmTGO(var19, BKl4Z47wTV(var19) + 0dm.normalizeYaw((float)0ej.intRandom(-7988 ^ -25688 ^ 15051 ^ -16824, 14844 ^ -16643 ^ 6112 ^ -28424)));
            this.jump();
            ob0nd0LVXD(98tOtyQJFl(WoaOBzbTUa(this)), (boolean)(5329 ^ -27036 ^ 12237 ^ -21127));
         }

         if (q9KIDYyNLA()) {
            try {
               if (0dm.hasSword(this)) {
                  406aTtzJ7r(LFejQQCzTG(kVksSu97iv(this)), 0dm.getSword(this));
               }

               InventoryPlayer inventory = QC4ymGXBjQ(pNbyrnJj2M(this));
               int[] ArmorSlots = new int[27746 ^ -32671 ^ 10911 ^ -14696];
               int[] ArmorValues = new int[8033 ^ -25354 ^ 1840 ^ -31581];

               int slot;
               ItemStack stack;
               ItemArmor item;
               for(slot = 27157 ^ -31694 ^ 6251 ^ -2484; slot < (15081 ^ -29975 ^ 19702 ^ -782); ++slot) {
                  ArmorSlots[slot] = -25924 ^ -19969 ^ 28725 ^ -23415;
                  stack = inventory.armorItemInSlot(slot);
                  if (!0dm.isNullOrEmpty(stack) && stack.getItem() instanceof ItemArmor) {
                     item = (ItemArmor)stack.getItem();
                     ArmorValues[slot] = 0dm.getArmorValue(item, stack, this);
                  }
               }

               int j;
               for(slot = 2554 ^ -5803 ^ 6260 ^ -1829; slot < (12038 ^ -28124 ^ 26469 ^ -9629); ++slot) {
                  stack = inventory.getStackInSlot(slot);
                  if (!0dm.isNullOrEmpty(stack) && stack.getItem() instanceof ItemArmor) {
                     item = (ItemArmor)stack.getItem();
                     j = 1Vb32p69Oq(item).getIndex();
                     int armorValue = 0dm.getArmorValue(item, stack, this);
                     if (armorValue > ArmorValues[j]) {
                        ArmorSlots[j] = slot;
                        ArmorValues[j] = armorValue;
                     }
                  }
               }

               Integer[] var20 = new Integer[28960 ^ -10718 ^ 16614 ^ -6176];
               var20[19156 ^ -30426 ^ 9870 ^ -6788] = 1028 ^ -11429 ^ 3364 ^ -9605;
               var20[19617 ^ -17083 ^ 26309 ^ -26848] = 7018 ^ -146 ^ 4826 ^ -2337;
               var20[8390 ^ -19767 ^ 10313 ^ -17852] = 32669 ^ -19787 ^ 16828 ^ -29546;
               var20[28967 ^ -11007 ^ 5597 ^ -19976] = 15044 ^ -24350 ^ 22671 ^ -15702;
               ArrayList<Integer> types = new ArrayList(Arrays.asList(var20));
               Collections.shuffle(types);
               Iterator var14 = types.iterator();

               while(var14.hasNext()) {
                  int i1 = (Integer)var14.next();
                  j = ArmorSlots[i1];
                  if (j != (-13361 ^ -13958 ^ 17813 ^ -18209)) {
                     ItemStack oldArmor = inventory.armorItemInSlot(i1);
                     if (0dm.isNullOrEmpty(oldArmor) || inventory.getFirstEmptyStack() != (-18569 ^ -15711 ^ 14680 ^ -19599)) {
                        if (j < (3425 ^ -12240 ^ 9457 ^ -1623)) {
                           j += 36;
                        }

                        if (yZSSQlbhHl(this).hasReached(Double.longBitsToDouble(-5800484741355684159L ^ -1159243850373677375L))) {
                           if (!0dm.isNullOrEmpty(oldArmor)) {
                              this.windowClick((15517 ^ -10278 ^ 31657 ^ -28442) - i1, 3503 ^ -15952 ^ 31763 ^ -20468, rim5TdjND6());
                           }

                           this.windowClick(j, 22448 ^ -31944 ^ 13796 ^ -7828, 2jyurXhFbN());
                           Y9dnRhyVBP(this).reset();
                        }
                        break;
                     }
                  }
               }

               Entity e = 0dm.getByName(wJVVE67ESv());
               if (e != null && e != 4UdT26MtWt(this)) {
                  Vector2f vector2f = 0dm.getBlockAngles(w9DmwwAYky(e), bSiiPPFNRw(e) + Double.longBitsToDouble(8126136684614600507L ^ 5703200085089273659L), 9wDbYLWoOV(e), cw0lYHJFnL(yUemajdQCG(this)), z6Y1WjdeTM(OLkhz0A6G7(this)), 421VvVtOTj(h8xrIZhMn4(this)));
                  yvKFAbdiLt(ggCunZwC8o(this), 0dm.normalizeYaw(VQ4SNV9JLg(vector2f)) + (float)0ej.intRandom(-10856 ^ -17359 ^ 16109 ^ -22343, 24331 ^ -16749 ^ 27833 ^ -29406));
                  eSzlnHWhne(tT5j4o59E2(this), 0dm.normalizePitch(JOh7VlZGGQ(vector2f)) + (float)0ej.intRandom(-29345 ^ -22008 ^ 30075 ^ -21037, 32735 ^ -16608 ^ 18462 ^ -30496));
                  qvpBWTXIce(zMEgiUFrQ6(yBGNGbDaWj(this)), (boolean)(!(MvztlqeHew(this).getDistance(e) <= Float.intBitsToFloat(15558 ^ 104941 ^ 3223 ^ -52511141 ^ 14145 ^ 83553 ^ '덗' ^ -1126247024)) && !IiF9REXP3t(e) ? 6805 ^ -18022 ^ 13239 ^ -28487 : 6099 ^ -10549 ^ 2900 ^ -13748));
                  if ((double)ovIQFZ1RjC(this).getDistance(e) <= Double.longBitsToDouble(8607116569761448316L ^ 3997682351147745660L)) {
                     if ((double)NDJVca4rmX(this).getCooledAttackStrength(Float.intBitsToFloat(2448 ^ 4174985 ^ 564 ^ 1700768420 ^ 31247 ^ 28042 ^ 2184 ^ 1524632196)) >= Double.longBitsToDouble(-7538143070940150709L ^ -6300852127692312184L) && S6gxtj219V(this).hasReached((double)((14483 ^ -25640 ^ 29075 ^ -9692) + 0ej.intRandom(1353 ^ -11550 ^ 8250 ^ -2159, 20961 ^ -11524 ^ 11275 ^ -20622)))) {
                        VybN2wLhge(this).reset();
                        uGJAABXCVd(ul6hvUt5ti(this)).attackEntity(PNUle1l2FU(this), e);
                        PZU3Qqk0Ay(this).swingArm(63hW2qJ18e());
                        JbZydOy6r2(this).resetCooldown();
                     }

                     this.jump();
                  }
               }
            } catch (Exception var9) {
            }
         }

         if (!this.getBooleanParameter(feMuiekQFB("̶̴̶̴̶̡̡̡̥̱̰̰̰̱̽")) && !this.getBooleanParameter(feMuiekQFB("̴̴̡̧̡̠̺̼̯̼̺̻̽")) && AoBrnxlRED(this).hasReached((double)(WsolD6EcTp(5sHh393Cdq()) ? 23046 ^ -28930 ^ 15368 ^ -5260 : 6061 ^ -14855 ^ 15584 ^ -5788))) {
            PJR7dkYeXZ(this).reset();
            0dn captcha = 0dq.getCaptcha(this);
            if (captcha != null) {
               0bL.getInstance().getCaptchaManager().addCaptcha(captcha);
            }
         }
      } catch (Exception var10) {
         Exception ex = var10;
         ex.printStackTrace();
      }

   }

   private static boolean Aa43gvlLiN(KeyBinding var0) {
      return var0.pressed;
   }

   public void setHost(String host) {
      Vg14mdywOV(this, host);
   }

   private static 0cH vjgqfejRL6(0cC var0) {
      return var0.mc;
   }

   public void windowClick(int slot, int mouseButton, ClickType type) {
      9maOYY7r7g(1TSowODqoZ(this)).windowClick(yYGpnzPWIw(zDILYu4FTQ(St1PByqCwt(this))), slot, mouseButton, type, LaAdpPW6D2(this));
   }

   private static 0ek o4qHh4triR(0cC var0) {
      return var0.spammer;
   }

   private static KeyBinding mfbGWusihm(GameSettings var0) {
      return var0.keyBindRight;
   }

   private static 0cH Nzee41gn9w(0cC var0) {
      return var0.mc;
   }

   private static double tK7gQN6rx3(0cD var0) {
      return var0.posY;
   }

   private static 0by UsS7eot58U() {
      return 0cd.gameguardBlock;
   }

   private static String wJVVE67ESv() {
      return 0d.target;
   }

   private static int liGIWvreCc(InventoryPlayer var0) {
      return var0.currentItem;
   }

   private static void _vbgWaJJ6j/* $FF was: 1vbgWaJJ6j*/(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   public String getStringParameter(String key) {
      return (String)this.getParameters().get(key);
   }

   private static String _qFLYFzwWW/* $FF was: 0qFLYFzwWW*/(0cC var0) {
      return var0.windowTitle;
   }

   private static 0cD DyPgtd5YOf(0cC var0) {
      return var0.player;
   }

   private static 0cH lG1vxOSFZ7(0cC var0) {
      return var0.mc;
   }

   private static Container aLe4OsFvJm(0cD var0) {
      return var0.openContainer;
   }

   private static 0cD dD3C6wnRZy(0cC var0) {
      return var0.player;
   }

   private static 0by hLywtWSM6h() {
      return 0cd.antibot;
   }

   private static EnumHand _3hW2qJ18e/* $FF was: 63hW2qJ18e*/() {
      return EnumHand.MAIN_HAND;
   }

   public String getNickname() {
      return m4mQ6Wwxg5(this);
   }

   private static 0cL _Zrv72mudG/* $FF was: 5Zrv72mudG*/(0cD var0) {
      return var0.connection;
   }

   private _cC/* $FF was: 0cC*/(String nickname2, 0eq proxy, String host, int port) {
      this.proxy = proxy;
      this.host = host;
      this.port = port;
      this.nickname = nickname2;
      this.captchaDetector = new 0ek();
      this.gameGuardCheck = new 0ek();
      this.stimer1 = new 0ek();
      this.stimer2 = new 0ek();
      this.stimer3 = new 0ek();
      this.spammer = new 0ek();
      this.c = new 0ek();
      this.needAutoRespawn = (boolean)(27476 ^ -20820 ^ 21076 ^ -26708);
      this.parameters = new HashMap();
      this.resetParameters();
      this.session = new Session(this.nickname, feMuiekQFB(""), feMuiekQFB(""), feMuiekQFB("̸̴̺̻̲̿"));
      this.botBaritone = new 0cV(this);
      this.serverPinger = new 0cT();
      addBot(this);
   }

   private static 0cH TV76u0j84c(0cC var0) {
      return var0.mc;
   }

   private static EnumHand kon7aNM62D() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cD iIhl6iIwQD(0cC var0) {
      return var0.player;
   }

   private static GameSettings tdwDQV3Tty() {
      return Minecraft.gameSettings;
   }

   public int getPort() {
      return 37lGvB24Tq(this);
   }

   public void resetParameters() {
      this.setParameter(feMuiekQFB("̴̴̡̧̡̠̺̼̯̼̺̻̽"), Boolean.valueOf((boolean)(13302 ^ -21937 ^ 9000 ^ -17775)));
      this.setParameter(feMuiekQFB("̷̶̢̡̡̰̱̰̰̰̱"), Boolean.valueOf((boolean)(19236 ^ -18451 ^ 15137 ^ -14360)));
      this.setParameter(feMuiekQFB("̴̸̴̶̶̧̲̰̲̠̱̰̽̾"), Boolean.valueOf((boolean)(8555 ^ -2875 ^ 9987 ^ -3411)));
      this.setParameter(feMuiekQFB("̶̴̶̴̶̡̡̡̥̱̰̰̰̱̽"), Boolean.valueOf((boolean)(27139 ^ -19970 ^ 15736 ^ -6523)));
      this.setParameter(feMuiekQFB("̴̷̴̻̣̼̹̬̥̦̦"), Boolean.valueOf((boolean)(16862 ^ -11333 ^ 32047 ^ -4278)));
   }

   private static 0cP DDjrBrItcD(0cC var0) {
      return var0.networkManager;
   }

   private static double jr6AnDA9SV(0cD var0) {
      return var0.posZ;
   }

   private static 0cV IEc44YVzaD(0cC var0) {
      return var0.botBaritone;
   }

   public int getMapSlot() {
      if (ojWryDBYCr(this).getHeldItemOffhand().getItem().equals(7YwXZPbPUi())) {
         return 28697 ^ -24621 ^ 2092 ^ -6197;
      } else {
         int slot = 4194 ^ -13263 ^ 12479 ^ -4884;

         for(Iterator var2 = 42YbZqan2w(FsGglYTgbK(8NoDMTU1J1(this))).iterator(); var2.hasNext(); ++slot) {
            ItemStack stack = (ItemStack)var2.next();
            if (stack.getItem().equals(FI3JJU4doy())) {
               return slot;
            }
         }

         return -29873 ^ -12028 ^ 9246 ^ -32342;
      }
   }

   private static 0cD aVgAgtHkgs(0cC var0) {
      return var0.player;
   }

   private static 0cD OLkhz0A6G7(0cC var0) {
      return var0.player;
   }

   private static EnumConnectionState kOmWAl6b6R() {
      return EnumConnectionState.LOGIN;
   }

   private static EnumHand FLLTY29WJQ() {
      return EnumHand.MAIN_HAND;
   }

   private static String m4mQ6Wwxg5(0cC var0) {
      return var0.nickname;
   }

   private static String NwiO1B9I5e(0cC var0) {
      return var0.nickname;
   }

   private static String DBO765irlc(0cC var0) {
      return var0.tabFooter;
   }

   private static InventoryPlayer LFejQQCzTG(0cD var0) {
      return var0.inventory;
   }

   private static 0cD BNjBGJ5szk(0cC var0) {
      return var0.player;
   }

   private static EnumFacing BqOTCkSi40() {
      return EnumFacing.DOWN;
   }

   private static 0cD D5lvN63Iy3(0cC var0) {
      return var0.player;
   }

   private static String wZmYJYwH7p(0cC var0) {
      return var0.host;
   }

   private static 0ek _S4yb4yv7u/* $FF was: 4S4yb4yv7u*/(0cC var0) {
      return var0.gameGuardCheck;
   }

   public void changeNickname(String nickname) {
      5iO1DIzFs7(this, nickname);
   }

   private static boolean lYPPUtcoLx(KeyBinding var0) {
      return var0.pressed;
   }

   public final 0cP getNetworkManager() {
      return DDjrBrItcD(this);
   }

   private static void addBot(0cC pbot) {
      S6gDvqlzQ9().add(pbot);
   }

   private static World njae4O72Yp(0cD var0) {
      return var0.world;
   }

   public void clickBlock(int x, int y, int z, String enumFace) {
      BlockPos blockPos = new BlockPos(x, y, z);
      this.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ()), EnumFacing.byName(enumFace), LN6TdROdWb(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ()));
   }

   private static 0eq LETEYGqlZg(0cC var0) {
      return var0.proxy;
   }

   private static 0cD _GiYw6xg3L/* $FF was: 2GiYw6xg3L*/(0cC var0) {
      return var0.player;
   }

   private static double dvLGBFJP6U(0cD var0) {
      return var0.posX;
   }

   private static double _j1DNrcTFf/* $FF was: 0j1DNrcTFf*/(Entity var0) {
      return var0.posX;
   }

   private static 0cD kVksSu97iv(0cC var0) {
      return var0.player;
   }

   private static 0cD _Fi9EvBvVS/* $FF was: 7Fi9EvBvVS*/(0cC var0) {
      return var0.player;
   }

   private static void SJ4DNyPrdS(0cC var0, 0cP var1) {
      var0.networkManager = var1;
   }

   public int currentWindowID() {
      return this.isOnline() ? RhvrTNVaAd(k2NqjAYyZA(2GiYw6xg3L(this))) : 13736 ^ -11034 ^ 18300 ^ -22990;
   }

   public void botTick() {
      if (this.isOnline()) {
         Exception exception;
         try {
            if (BRaaR65wnw(7QXB4r4Hv9()) && Sr1t2yVT3s(this).hasReached(Double.longBitsToDouble(8300349183347215973L ^ 3724340118217903717L)) && !this.getBooleanParameter(feMuiekQFB("̴̸̴̶̶̧̲̰̲̠̱̰̽̾"))) {
               4S4yb4yv7u(this).reset();
               Iterator var7 = 0dm.getAllInBox(new BlockPos(21218 ^ -6200 ^ 2276 ^ -16953, 21581 ^ -30029 ^ 12510 ^ -4589, -9218 ^ -29400 ^ 191 ^ -22122), new BlockPos(27466 ^ -24837 ^ 29561 ^ -31039, 21099 ^ -12273 ^ 7658 ^ -24645, 2282 ^ -22504 ^ 28607 ^ -12468)).iterator();

               while(var7.hasNext()) {
                  BlockPos blockPos = (BlockPos)var7.next();
                  if (KwTvEYC29b(q316lmJtbQ(this)).getBlockState(blockPos).getProperties().size() != 0 && ZLDen2jq7J(qLyYlS97Dy(this)).getBlockState(blockPos).getBlock().getTranslationKey().equalsIgnoreCase(feMuiekQFB("̡̼̹̰ͻ̶̡̹̺̽")) && ((EnumDyeColor)njae4O72Yp(uZZjBOGNPs(this)).getBlockState(blockPos).getValue(RzrNw2BLMv())).toString().equalsIgnoreCase(UsS7eot58U().get())) {
                     if (0dm.getDistance(this, Double.longBitsToDouble(-5078617137208821581L ^ -457360969573271373L), Double.longBitsToDouble(7501029834421391867L ^ 2905669364643222011L), Double.longBitsToDouble(-9191358867303552593L ^ -9191358867303552593L)) < Float.intBitsToFloat(6492 ^ 495511 ^ 12262 ^ 1090253733 ^ '\ufff4' ^ 23066 ^ '\ude47' ^ 8139041)) {
                        wqwWIDdudh(WivGbqGdil(ni4YYNJCNw(this)), (boolean)(24715 ^ -12634 ^ 17345 ^ -4628));
                        this.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(blockPos.getX() - (53 ^ -19814 ^ 1900 ^ -19006), blockPos.getY(), blockPos.getZ()), IB0TOgWZeN(), waACuVHmFD(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ()));
                     } else {
                        Wnvf4XGBNM(Yf6nv5XVGa(n1s8Tm7s7n(this)), (boolean)(19976 ^ -4799 ^ 18787 ^ -5589));
                     }

                     Vector2f vector2f = 0dm.getBlockAngles((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), dvLGBFJP6U(yAgbdSali8(this)) + Double.longBitsToDouble(-6537346807580467283L ^ -7302958744233451603L), LbOJ62dDOj(SegkAq6FKL(this)), KJVp2qjw02(21rfDyg6pJ(this)));
                     gpkGCj9eNz(yToL12Tc8A(this), 0dm.normalizeYaw(uoZwJ5bvVd(vector2f)));
                     etjK6qBBqe(ZtT7afLsgB(this), 0dm.normalizePitch(9WxNRlyYLy(vector2f)));
                  }
               }
            }
         } catch (Exception var6) {
            exception = var6;
            exception.printStackTrace();
         }

         try {
            if (Skw2O2yrnh().is(feMuiekQFB("̴̡̘̠̹̬̗̬̥̦̦"))) {
               if (0qFLYFzwWW(this) != null) {
                  String containerName = ATvjrN4T8g(this).toLowerCase().replace(feMuiekQFB("̾"), feMuiekQFB("ݯ")).replace(feMuiekQFB("̭"), feMuiekQFB("ܐ")).replace(feMuiekQFB("̥"), feMuiekQFB("ܕ")).replace(feMuiekQFB("̴"), feMuiekQFB("ݥ")).replace(feMuiekQFB("̬"), feMuiekQFB("ܖ")).replace(feMuiekQFB("̡"), feMuiekQFB("ܗ")).replace(feMuiekQFB("̶"), feMuiekQFB("ܔ")).replace(feMuiekQFB("̺"), feMuiekQFB("ݫ")).replace(feMuiekQFB("̰"), feMuiekQFB("ݠ")).replace(feMuiekQFB("̷"), feMuiekQFB("ݧ")).replace(feMuiekQFB("̽"), feMuiekQFB("ݨ")).replace(feMuiekQFB("̸"), feMuiekQFB("ݩ"));
                  int slot = 10162 ^ -4182 ^ 4860 ^ -9500;

                  for(Iterator var12 = QikMnaaGFS(5WnDbtmOHa(this)).getInventory().iterator(); var12.hasNext(); ++slot) {
                     ItemStack stack = (ItemStack)var12.next();
                     if (stack.getTranslationKey().equals(feMuiekQFB("̡̼̹̰ͻ̴̴̡̡̼̻̼̻̰̱̹̦̦̽̆̒ͻ̸̹̼̰")) && FGlJBBJatq(this).hasReached(Double.longBitsToDouble(-8197980261369247997L ^ -3583760968151464189L))) {
                        this.windowClick(slot, 615 ^ -14657 ^ 7316 ^ -10164, 47ByRGTVkr());
                        jQWVnecAGv(this).reset();
                     }

                     if (containerName.contains(feMuiekQFB("ݢݠݮܙݠ")) && PotionUtils.getColor(stack) == (21079 ^ -23797 ^ 25085 ^ -28578) && YB38nbhZ3G(this).hasReached(Double.longBitsToDouble(754540760628507023L ^ 5332731256827326863L))) {
                        this.windowClick(slot, 24753 ^ -169 ^ 21336 ^ -13122, 7xskOq0dEn());
                        Sw6fTR7rVL(this).reset();
                     }
                  }
               }
            } else if (hLywtWSM6h().is(feMuiekQFB("̴̸̻̣̼̹̰̻̰̔̇")) && !this.getBooleanParameter(feMuiekQFB("̴̷̴̻̣̼̹̬̥̦̦")) && onwBia2hiV(this).hasReached(Double.longBitsToDouble(7966689645964125381L ^ 3319185936750306501L))) {
               TESG8Xg3rq(this).reset();
               if (w9IGYOrVQL(9qBaeqYV0a(this)) instanceof ContainerRepair) {
                  ContainerRepair containerrepair = (ContainerRepair)7LvGhGGgYS(l4v6sKNlOd(this));
                  ItemStack paper = (ItemStack)containerrepair.getInventory().get(15205 ^ -18793 ^ 10630 ^ -23436);
                  if (paper.getItem().equals(HaYgPHt3fv())) {
                     if (paper.getDisplayName().contains(feMuiekQFB("݇ݧݠݡݭܗݠ͵ܒݭܔݮݫ"))) {
                        String s = paper.getDisplayName().replace(feMuiekQFB("Ϭ"), feMuiekQFB("ͤ")).replace(feMuiekQFB("ϧ"), feMuiekQFB("ͧ")).replace(feMuiekQFB("Ϧ"), feMuiekQFB("ͦ")).replace(feMuiekQFB("⌡"), feMuiekQFB("͡")).replace(feMuiekQFB("⌠"), feMuiekQFB("͠")).replace(feMuiekQFB("⌣"), feMuiekQFB("ͣ")).replace(feMuiekQFB("⌢"), feMuiekQFB("͢")).replace(feMuiekQFB("⌭"), feMuiekQFB("ͭ")).replace(feMuiekQFB("⌬"), feMuiekQFB("ͬ")).replace(feMuiekQFB("⌥"), feMuiekQFB("ͥ"));
                        String number = s.split(feMuiekQFB("ܒݭܔݮݫ͵"))[16491 ^ -26617 ^ 16260 ^ -6167];
                        containerrepair.updateItemName(number);
                        this.sendPacket(new CPacketCustomPayload(feMuiekQFB("̸̴̸̡̛̘̖̩̜̰̰"), (new PacketBuffer(Unpooled.buffer())).writeString(number)));
                        this.windowClick(23920 ^ -30414 ^ 7561 ^ -13879, 4585 ^ -7314 ^ 21609 ^ -22802, bYtvyq7WAJ());
                        this.setParameter(feMuiekQFB("̴̷̴̻̣̼̹̬̥̦̦"), Boolean.valueOf((boolean)(14708 ^ -6843 ^ 15222 ^ -6330)));
                     } else {
                        this.windowClick(23902 ^ -12633 ^ 21974 ^ -14801, 7176 ^ -24811 ^ 22975 ^ -9566, oNGNNuT3kn());
                     }
                  }
               }
            }
         } catch (Exception var5) {
            exception = var5;
            exception.printStackTrace();
         }
      }

   }

   public boolean isOnline() {
      return (boolean)(this.getNetworkManager() != null && Nzee41gn9w(this) != null && DyPgtd5YOf(this) != null && nTc4xDEM2l(4qzHCzQ632(this)) != null ? 21486 ^ -30509 ^ 21988 ^ -28968 : 26112 ^ -393 ^ 5737 ^ -29154);
   }

   public void startBot() {
      Thread botThread = new Thread(() -> {
         this.connect();
         1IGnAyrH2d(this, new 0cH(this));
      });
      botThread.setName(feMuiekQFB("̶̡̡̧̗̺̖̺̻̻̰̺̅\u0378") + NwiO1B9I5e(this) + feMuiekQFB("\u0378") + 0ej.randomNumber(15707 ^ -29292 ^ 32275 ^ -12577));
      botThread.start();
   }

   public void dropItem(int slot) {
      if (this.isOnline()) {
         ZQLeHLPMSr(yKjpNcDbvv(this)).windowClick(g9epUQ9S4A(aLe4OsFvJm(qfg9OnqOO9(this))), slot, 3555 ^ -9964 ^ 20197 ^ -26093, XzdWzRSAgS(), IaBZ1XW6YU(this));
      }

   }

   private static void eSzlnHWhne(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0ek PJR7dkYeXZ(0cC var0) {
      return var0.captchaDetector;
   }

   private static 0ek YB38nbhZ3G(0cC var0) {
      return var0.c;
   }

   private static void Wnvf4XGBNM(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float ogbTgNTnBg(Vector2f var0) {
      return var0.x;
   }

   private static 0cD LaAdpPW6D2(0cC var0) {
      return var0.player;
   }

   private static KeyBinding G3y1BFeYFw(GameSettings var0) {
      return var0.keyBindLeft;
   }

   private static 0cE WivGbqGdil(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD PNUle1l2FU(0cC var0) {
      return var0.player;
   }

   private static InventoryPlayer kTLUYomdE7(0cD var0) {
      return var0.inventory;
   }

   private static 0cE _8tOtyQJFl/* $FF was: 98tOtyQJFl*/(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD h8xrIZhMn4(0cC var0) {
      return var0.player;
   }

   private static void _06aTtzJ7r/* $FF was: 406aTtzJ7r*/(InventoryPlayer var0, int var1) {
      var0.currentItem = var1;
   }

   private static NonNullList _2YbZqan2w/* $FF was: 42YbZqan2w*/(InventoryPlayer var0) {
      return var0.mainInventory;
   }

   public String getTabHeader() {
      return e1A7790jaA(this);
   }

   private static void ob0nd0LVXD(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cE aQVv2OHcSe(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD ggCunZwC8o(0cC var0) {
      return var0.player;
   }

   public void stopBot() {
      this.resetParameters();
      this.disconnect();
      SJ4DNyPrdS(this, (0cP)null);
      this.setWorld((0cU)null);
      fAHbFMObD1(this, (0cD)null);
      13rg3L1F6B(this, (0cH)null);
   }

   private static 0cD dZPYHn1ORA(0cC var0) {
      return var0.player;
   }

   private static 0cQ _maOYY7r7g/* $FF was: 9maOYY7r7g*/(0cH var0) {
      return var0.playerController;
   }

   private static 0cD MbSi2tabyz(0cC var0) {
      return var0.player;
   }

   private static boolean IiF9REXP3t(Entity var0) {
      return var0.isDead;
   }

   private static float uoZwJ5bvVd(Vector2f var0) {
      return var0.y;
   }

   private static void _RDNbvzDCo/* $FF was: 4RDNbvzDCo*/(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static double _wDbYLWoOV/* $FF was: 9wDbYLWoOV*/(Entity var0) {
      return var0.posZ;
   }

   private static ClickType oNGNNuT3kn() {
      return ClickType.PICKUP;
   }

   private static 0cQ nBzTFxNbJg(0cH var0) {
      return var0.playerController;
   }

   private static 0cH _qzHCzQ632/* $FF was: 4qzHCzQ632*/(0cC var0) {
      return var0.mc;
   }

   private void autoRespawn() {
      if (19xaGButRA(this) != null) {
         if (VZgEQWvHw2(this).getHealth() <= Float.intBitsToFloat(5501 ^ 10606 ^ 16226 ^ 438534403 ^ 5109 ^ '迱' ^ 9237 ^ 438516323) && AOnM60aMwd(this).getTime() > 3000L && 6YaqMv3JIr(this)) {
            fVjOLVApbf(this).respawnPlayer();
            prOMy96WQ4(this, (boolean)(13264 ^ -5214 ^ 15596 ^ -7010));
         }

         if (3TThvpTvR5(this).getHealth() <= Float.intBitsToFloat('臫' ^ 1033353 ^ '餭' ^ 1047003000 ^ 24266 ^ 25637 ^ 1172 ^ 1047010636) && !kBTV8rG12p(this)) {
            jyoIjiTGLb(this, (boolean)(5174 ^ -14992 ^ 962 ^ -11643));
            VvRjv0ICvR(this).reset();
         }
      }

   }

   private static void wqwWIDdudh(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static void fAHbFMObD1(0cC var0, 0cD var1) {
      var0.player = var1;
   }

   private static 0ek Sw6fTR7rVL(0cC var0) {
      return var0.c;
   }

   private static ClickType _xskOq0dEn/* $FF was: 7xskOq0dEn*/() {
      return ClickType.PICKUP;
   }

   private static 0cD FCLsdKUp6J(0cC var0) {
      return var0.player;
   }

   private static 0bv YtN5gYAWwq() {
      return 0cc.rejoin;
   }

   private static 0cQ uGJAABXCVd(0cH var0) {
      return var0.playerController;
   }

   private static boolean TIg6TIBHGU(0bv var0) {
      return var0.value;
   }

   private static void prOMy96WQ4(0cC var0, boolean var1) {
      var0.needAutoRespawn = var1;
   }

   private static 0cD VZgEQWvHw2(0cC var0) {
      return var0.player;
   }

   private static double eaKnyos9jq(Entity var0) {
      return var0.posY;
   }

   private static 0cE _EyG16jsaI/* $FF was: 9EyG16jsaI*/(0cH var0) {
      return var0.gameSettings;
   }

   public void rightClick(boolean hold) {
      yan9j1gDdT(this).rightClickMouse();
      if (!hold) {
         this.rclickStop();
      }

   }

   private static 0bv rJAavI5bJg() {
      return 0cd.removebadproxy;
   }

   private static double KJVp2qjw02(0cD var0) {
      return var0.posZ;
   }

   private static ClickType bYtvyq7WAJ() {
      return ClickType.PICKUP;
   }

   private static 0cP dVerGweRAs(0cC var0) {
      return var0.networkManager;
   }

   public boolean isDeleted() {
      return FiwqOn20YB(this);
   }

   private static EnumHand waACuVHmFD() {
      return EnumHand.MAIN_HAND;
   }

   private static KeyBinding j3EhgX2AWt(GameSettings var0) {
      return var0.keyBindJump;
   }

   public void setParameter(String key, Object value) {
      this.getParameters().put(key, value);
   }

   public void auth() {
      this.setParameter(feMuiekQFB("̴̴̡̧̡̠̺̼̯̼̺̻̽"), Boolean.valueOf((boolean)(24319 ^ -22231 ^ 3997 ^ -1974)));
      String var2 = gba4Z4iM4P().get();
      int var3 = -4492 ^ -23721 ^ 12663 ^ -31829;
      switch (var2.hashCode()) {
         case -1517789585:
            if (var2.equals(feMuiekQFB("̶̴̻̼̺̦̦̾́̅"))) {
               var3 = 15835 ^ -24276 ^ 30843 ^ -7026;
            }
            break;
         case -1349088399:
            if (var2.equals(feMuiekQFB("̶̸̡̠̦̺"))) {
               var3 = 18391 ^ -21537 ^ 14 ^ -5113;
            }
            break;
         case -938285885:
            if (var2.equals(feMuiekQFB("̴̸̧̻̱̺"))) {
               var3 = 23036 ^ -19918 ^ 17718 ^ -20744;
            }
      }

      String password;
      switch (var3) {
         case 0:
            password = 0ej.randomString(2748 ^ -6956 ^ 5922 ^ -1728);
            break;
         case 1:
            password = I3DAUtDesQ().get();
            break;
         case 2:
            password = this.getNickname().toLowerCase().substring(12476 ^ -3385 ^ 1182 ^ -14619, 2708 ^ -8668 ^ 17149 ^ -27058) + Math.abs(this.getNickname().toLowerCase().hashCode());
            break;
         default:
            password = 0ej.randomString(17720 ^ -2692 ^ 6880 ^ -21849);
      }

      this.sendMessage(aW4FM8PKZn().get().replace(feMuiekQFB("Ͱ̴̥̦̦"), password));
      if (TIg6TIBHGU(DQhzQtCAES())) {
         this.sendMessage(oD8iRcGGf9().get().replace(feMuiekQFB("Ͱ̴̥̦̦"), password));
      }

      0dK.formatMsg(feMuiekQFB("݄ݫܗ͵ͳ̱ͳ̹") + this.getNickname() + feMuiekQFB("ͳ̳ͳ̹͵ݏݫݩݥݨݡݥ͵ݨݥ͵ݥݧܗݫܕݭݢݥܓݭܛ͵ݫܗݪܕݥݧݮݠݨݥʹ"));
      0cR.trigger(this, feMuiekQFB("̡̺̻̠̔̽"));
      0dB var10000 = irmiYwOoag(0bK.getInstance());
      String var10001 = feMuiekQFB("̡̺̻̠̔̽");
      Object[] var10002 = new Object[30398 ^ -4906 ^ 2314 ^ -27808];
      var10002[23798 ^ -7634 ^ 671 ^ -17337] = this;
      var10002[7135 ^ -19931 ^ 18705 ^ -7958] = password;
      var10000.invokeMethod(var10001, var10002);
   }

   private static 0ek Y9dnRhyVBP(0cC var0) {
      return var0.c;
   }

   private static 0cD _UdT26MtWt/* $FF was: 4UdT26MtWt*/(0cC var0) {
      return var0.player;
   }

   private static 0cD PZU3Qqk0Ay(0cC var0) {
      return var0.player;
   }

   public void disconnect() {
      this.resetParameters();
      if (this.getWorld() != null && this.getNetworkManager() != null && UXZ9zFf9Ut(this.getNetworkManager()) != null) {
         this.getWorld().sendQuittingDisconnectingPacket();
      }

   }

   private static void MUKyAsYtLD(0cE var0, boolean var1) {
      var0.keyBindRight = var1;
   }

   private static GameSettings _y6TfGBbAk/* $FF was: 4y6TfGBbAk*/() {
      return Minecraft.gameSettings;
   }

   public Object getParameter(String key) {
      return this.getParameters().get(key);
   }

   private static boolean TB65EeOPSw(KeyBinding var0) {
      return var0.pressed;
   }

   private static 0cD NDJVca4rmX(0cC var0) {
      return var0.player;
   }

   private static void fvenTqImU6(0cE var0, boolean var1) {
      var0.keyBindLeft = var1;
   }

   private static String OXFcmjn0ME(0cC var0) {
      return var0.nickname;
   }

   private static 0bA I3DAUtDesQ() {
      return 0bZ.customPassword;
   }

   private static Container k2NqjAYyZA(0cD var0) {
      return var0.openContainer;
   }

   private static 0eq _ZeQT7wQNV/* $FF was: 7ZeQT7wQNV*/(0cC var0) {
      return var0.proxy;
   }

   private static CopyOnWriteArrayList hXa6RJ0sIb() {
      return bots;
   }

   private static void etjK6qBBqe(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static void gpkGCj9eNz(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   public void jump() {
      if (1bP2na25pt(7Fi9EvBvVS(this))) {
         n0W17siDnE(this).jump();
      }

   }

   private static void jyoIjiTGLb(0cC var0, boolean var1) {
      var0.needAutoRespawn = var1;
   }

   private static 0cD Httm3FIV8k(0cC var0) {
      return var0.player;
   }

   private static PlayerCapabilities b0B4GONnrA(0cD var0) {
      return var0.capabilities;
   }

   private static 0cD fVjOLVApbf(0cC var0) {
      return var0.player;
   }

   private static 0cE Yf6nv5XVGa(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH _TSowODqoZ/* $FF was: 1TSowODqoZ*/(0cC var0) {
      return var0.mc;
   }

   private static String e1A7790jaA(0cC var0) {
      return var0.tabHeader;
   }

   private static void g14wrvO2D4(0cC var0, int var1) {
      var0.port = var1;
   }

   public static List<0cC> getOnline() {
      return (List)b5cFrWjEdL().stream().filter(0cC::isOnline).collect(Collectors.toList());
   }

   private static double bSiiPPFNRw(Entity var0) {
      return var0.posY;
   }

   private static Container _LvGhGGgYS/* $FF was: 7LvGhGGgYS*/(0cD var0) {
      return var0.openContainer;
   }

   private static 0cD ZtT7afLsgB(0cC var0) {
      return var0.player;
   }

   private static ClickType _jyurXhFbN/* $FF was: 2jyurXhFbN*/() {
      return ClickType.QUICK_MOVE;
   }

   private static 0cL EivJbQQe1r(0cD var0) {
      return var0.connection;
   }

   public void addParameter(String key, Object value) {
      this.getParameters().put(key, value);
   }

   private static EnumFacing IB0TOgWZeN() {
      return EnumFacing.SOUTH;
   }

   private static void _IGnAyrH2d/* $FF was: 1IGnAyrH2d*/(0cC var0, 0cH var1) {
      var0.mc = var1;
   }

   private static boolean _nJI29aQSY/* $FF was: 7nJI29aQSY*/(KeyBinding var0) {
      return var0.pressed;
   }

   private static 0cH pggQ1RVIkI(0cC var0) {
      return var0.mc;
   }

   private static boolean _bP2na25pt/* $FF was: 1bP2na25pt*/(0cD var0) {
      return var0.onGround;
   }

   private static 0cE I9EabbnyL2(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cH _B1Dd8J14l/* $FF was: 6B1Dd8J14l*/(0cC var0) {
      return var0.mc;
   }

   private static 0ek FGlJBBJatq(0cC var0) {
      return var0.c;
   }

   private static 0cP Da6r1XVidF(0cC var0) {
      return var0.networkManager;
   }

   private static 0cP tbOZ71EmcO(0cC var0) {
      return var0.networkManager;
   }

   private static void LWQIelw2Qv(0cD var0, double var1) {
      var0.motionZ = var1;
   }

   private static float BdnYseQLWa(EntityPlayerSP var0) {
      return var0.rotationYaw;
   }

   private static 0cH ni4YYNJCNw(0cC var0) {
      return var0.mc;
   }

   private static 0bv u1YKd7LDcd() {
      return 0cd.joinFixer;
   }

   private static int RhvrTNVaAd(Container var0) {
      return var0.windowId;
   }

   public void strafe(float yaw, double speed, double forward, double direction) {
      if (forward != Double.longBitsToDouble(8253323717747754920L ^ 8253323717747754920L)) {
         if (direction > Double.longBitsToDouble(5384347016641377556L ^ 5384347016641377556L)) {
            yaw += (float)(forward > Double.longBitsToDouble(-5737980196489497974L ^ -5737980196489497974L) ? -31441 ^ -27513 ^ 1959 ^ -5668 : 30215 ^ -23972 ^ 4520 ^ -14882);
         } else if (direction < Double.longBitsToDouble(6592377662002199856L ^ 6592377662002199856L)) {
            yaw += (float)(forward > Double.longBitsToDouble(-2042051315907355925L ^ -2042051315907355925L) ? 32408 ^ -28715 ^ 15803 ^ -13093 : -25128 ^ -20409 ^ 25600 ^ -18868);
         }

         direction = Double.longBitsToDouble(-3538294013807488073L ^ -3538294013807488073L);
         if (forward > Double.longBitsToDouble(2584939384783386539L ^ 2584939384783386539L)) {
            forward = Double.longBitsToDouble(1951770571690422347L ^ 2658835713187590219L);
         } else if (forward < Double.longBitsToDouble(-3550196887010697195L ^ -3550196887010697195L)) {
            forward = Double.longBitsToDouble(-5568225235386461839L ^ 957490624673386865L);
         }
      }

      double cos = Math.cos(Math.toRadians((double)(yaw + Float.intBitsToFloat(30377 ^ '递' ^ 21834 ^ -1007078290 ^ 8226 ^ '赴' ^ 16292 ^ -2125654675))));
      double sin = Math.sin(Math.toRadians((double)(yaw + Float.intBitsToFloat('鯣' ^ 92557 ^ 14814 ^ 748877269 ^ 18530 ^ '薠' ^ 'ﴼ' ^ 1847004827))));
      double x = forward * speed * cos + direction * speed * sin;
      double z = forward * speed * sin - direction * speed * cos;
      biSvAHbHqc(R69vI3e2GZ(this), x);
      LWQIelw2Qv(rj6RuBbXn4(this), z);
   }

   private static GameSettings V7Bzl1xUIk() {
      return Minecraft.gameSettings;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String feMuiekQFB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16954 ^ -17215 ^ 8282 ^ -8543; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 23640 ^ -30109 ^ 7298 ^ -13844));
      }

      return var1.toString();
   }

   private static void _BQIvlp2hN/* $FF was: 8BQIvlp2hN*/(0cE var0, boolean var1) {
      var0.keyBindBack = var1;
   }

   private static 0cD AQIBnPIbYn(0cC var0) {
      return var0.player;
   }

   private static 0by Skw2O2yrnh() {
      return 0cd.antibot;
   }

   private static 0cH BlVyJNRmLn(0cC var0) {
      return var0.mc;
   }

   private static 0cH dB54OOmdCl(0cC var0) {
      return var0.mc;
   }

   private static 0cD MvztlqeHew(0cC var0) {
      return var0.player;
   }

   public 0eq getProxy() {
      return LETEYGqlZg(this);
   }

   public 0cV getBaritone() {
      return IEc44YVzaD(this);
   }

   private static void _iO1DIzFs7/* $FF was: 5iO1DIzFs7*/(0cC var0, String var1) {
      var0.nickname = var1;
   }

   private static boolean iT69xT5MRK(0bv var0) {
      return var0.value;
   }

   private static 0cD _qBaeqYV0a/* $FF was: 9qBaeqYV0a*/(0cC var0) {
      return var0.player;
   }

   private static PropertyEnum RzrNw2BLMv() {
      return BlockColored.COLOR;
   }

   private static 0cD qb5paDb6iE(0cC var0) {
      return var0.player;
   }

   private static 0ek AOnM60aMwd(0cC var0) {
      return var0.timeAutoRespawn;
   }

   public boolean getBooleanParameter(String key) {
      return (Boolean)this.getParameters().get(key);
   }

   private static EntityPlayerSP j4X7mljllQ() {
      return Minecraft.player;
   }

   private static 0ek Sr1t2yVT3s(0cC var0) {
      return var0.gameGuardCheck;
   }

   private static 0cD R69vI3e2GZ(0cC var0) {
      return var0.player;
   }

   private static 0cD _Stjnt4WRe/* $FF was: 7Stjnt4WRe*/(0cC var0) {
      return var0.player;
   }

   private static InventoryPlayer FsGglYTgbK(0cD var0) {
      return var0.inventory;
   }

   private static KeyBinding aWjanibHEG(GameSettings var0) {
      return var0.keyBindSprint;
   }

   private static 0ek jQWVnecAGv(0cC var0) {
      return var0.c;
   }

   private static 0cD qLyYlS97Dy(0cC var0) {
      return var0.player;
   }

   private static GameSettings rHqqtYyfop() {
      return Minecraft.gameSettings;
   }

   public final 0cL getPlayHandler() {
      return Qb3eeRU941(this);
   }

   private static void _3W4Uxgtql/* $FF was: 93W4Uxgtql*/(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static Item HaYgPHt3fv() {
      return Items.PAPER;
   }

   private static boolean iPdsvJy596(KeyBinding var0) {
      return var0.pressed;
   }

   private static 0ek yZSSQlbhHl(0cC var0) {
      return var0.c;
   }

   private static double Y8QLpQbl2R(Entity var0) {
      return var0.posY;
   }

   private static 0cH NZ3b1uqa6y(0cC var0) {
      return var0.mc;
   }

   private static BlockPos cqKlNibam9() {
      return BlockPos.ORIGIN;
   }
}
