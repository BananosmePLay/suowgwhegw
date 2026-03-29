package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.profiler.ISnooperInfo;
import net.minecraft.profiler.Profiler;
import net.minecraft.profiler.Snooper;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0cH implements IThreadListener, ISnooperInfo {
   public final 0cC pbot;
   public 0cE gameSettings = new 0cE();
   public 0cQ playerController;
   public RayTraceResult objectMouseOver;
   public final Queue<FutureTask<?>> scheduledTasks = Queues.newArrayDeque();
   public final Profiler profiler = new Profiler();
   public final Logger LOGGER = LogManager.getLogger();
   public int worldId;

   private static 0cC BFQNINM4Bs(0cH var0) {
      return var0.pbot;
   }

   private static double QIhueyMr9X(Vec3d var0) {
      return var0.x;
   }

   private static EnumActionResult oF6UFtowyb() {
      return EnumActionResult.SUCCESS;
   }

   private static RayTraceResult MTBYi5AgsA(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cE eitvD8IH7a(0cH var0) {
      return var0.gameSettings;
   }

   private static double o4q1aInqDA(Vec3d var0) {
      return var0.z;
   }

   private static void TFzF6nQAv3(0cC var0, 0cD var1) {
      var0.player = var1;
   }

   private static 0cC E1veuSs0rS(0cH var0) {
      return var0.pbot;
   }

   private static 0cC DrbRwnszyK(0cH var0) {
      return var0.pbot;
   }

   private static 0cC isXvQvQrFN(0cH var0) {
      return var0.pbot;
   }

   public void clickMouse() {
      if (mgFLolQTo7(this) != null && !AWEqGrrjVk(5OA6luukCd(this)).isRowingBoat()) {
         switch (ertxBEye6M()[n4GA1VUvda(7le7833mFz(this)).ordinal()]) {
            case 1:
               qDvea8qTJu(this).attackEntity(4FYTraRD6a(tHzL01VjkY(this)), qJbSc94XFa(kqwmnWGFYk(this)));
               break;
            case 2:
               BlockPos blockpos = Oq1MrMj9gW(this).getBlockPos();
               if (qQ2YYdD4tr(this).getWorld().getBlockState(blockpos).getMaterial() != 0PCViabNdr()) {
                  ctQ5eVaATv(this).clickBlock(blockpos, 4AcSgH9beY(1LpgrPiQly(this)));
                  break;
               }
            case 3:
               3Lf1CaAavi(F4YQDymWNA(this)).resetCooldown();
         }

         zcXX3VWbG6(On9wbGw2k6(this)).swingArm(4yeQlntTv7());
      }

   }

   private static 0cD _4UBoSMlob/* $FF was: 64UBoSMlob*/(0cC var0) {
      return var0.player;
   }

   private static Entity qJbSc94XFa(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static 0cQ vb1YnyH44K(0cH var0) {
      return var0.playerController;
   }

   private static 0cD iUWD4Gb3FK(0cC var0) {
      return var0.player;
   }

   private static 0cQ JF5Sgq6ecg(0cH var0) {
      return var0.playerController;
   }

   private static 0cC rlc8HvQxL2(0cH var0) {
      return var0.pbot;
   }

   private static 0cD _kwGiHVtUF/* $FF was: 2kwGiHVtUF*/(0cC var0) {
      return var0.player;
   }

   private static 0cE d9SGwQ3qjU(0cH var0) {
      return var0.gameSettings;
   }

   private static RayTraceResult g5KI7amQJx(0cH var0) {
      return var0.objectMouseOver;
   }

   private static boolean wEQG8FjXQv(0cE var0) {
      return var0.keyBindAttack;
   }

   private static void Q11ovSzbEg(0cH var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static 0cC veDgj7gZ9G(0cH var0) {
      return var0.pbot;
   }

   private static 0cC VeOT5V6WaY(0cH var0) {
      return var0.pbot;
   }

   public void addServerStatsToSnooper(Snooper playerSnooper) {
   }

   private static 0cC WHrUFXJFYq(0cH var0) {
      return var0.pbot;
   }

   private static void h59PoGvmOb(0cH var0, 0cQ var1) {
      var0.playerController = var1;
   }

   public void addServerTypeToSnooper(Snooper playerSnooper) {
   }

   private static EnumFacing XOF2rfh5rV(RayTraceResult var0) {
      return var0.sideHit;
   }

   private static double MI290oPgvE(Vec3d var0) {
      return var0.x;
   }

   private static void JrStaBJwy5(0cH var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static RayTraceResult ifXWLsn6aC(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cC SjzNQS67qd(0cH var0) {
      return var0.pbot;
   }

   private static 0cQ oyAUO7SB2Y(0cH var0) {
      return var0.playerController;
   }

   private static EnumActionResult eygEVJtVIU() {
      return EnumActionResult.SUCCESS;
   }

   private static 0cQ xoB2tHbif8(0cH var0) {
      return var0.playerController;
   }

   private static RayTraceResult Oq1MrMj9gW(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cQ drQmq0vCpg(0cH var0) {
      return var0.playerController;
   }

   private static 0cC qQ2YYdD4tr(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult lBVaoCvQ0U(0cH var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult _LpgrPiQly/* $FF was: 1LpgrPiQly*/(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cH _ZTSl97tBv/* $FF was: 4ZTSl97tBv*/(0cC var0) {
      return var0.mc;
   }

   private static 0cD TXJN6HrAbT(0cC var0) {
      return var0.player;
   }

   private static 0cC b7Z7ODV41V(0cH var0) {
      return var0.pbot;
   }

   private static 0cC ynndvOvy7j(0cH var0) {
      return var0.pbot;
   }

   private static 0cD zcXX3VWbG6(0cC var0) {
      return var0.player;
   }

   private static Entity BIOVwgD5o6(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static 0cC VxB77evF2a(0cH var0) {
      return var0.pbot;
   }

   private static 0cQ jVUFTFD5vC(0cH var0) {
      return var0.playerController;
   }

   private static void d7rBSwIb1N(0cC var0, 0cD var1) {
      var0.player = var1;
   }

   private static RayTraceResult _5BdiC4trr/* $FF was: 15BdiC4trr*/(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cD Ycpmj4GLoT(0cC var0) {
      return var0.player;
   }

   private static 0cC _wnewAderi/* $FF was: 4wnewAderi*/(0cH var0) {
      return var0.pbot;
   }

   private static 0cD YqOerOnNDI(0cC var0) {
      return var0.player;
   }

   private static 0cD mIE9ue9HUO(0cC var0) {
      return var0.player;
   }

   private static 0cD _P5bY9DYBB/* $FF was: 8P5bY9DYBB*/(0cC var0) {
      return var0.player;
   }

   private static 0cD SaeauGCCSU(0cC var0) {
      return var0.player;
   }

   private static 0cD irJOCkJyW8(0cC var0) {
      return var0.player;
   }

   private static 0cL E5QWJNdOOb(0cD var0) {
      return var0.connection;
   }

   private static 0cD _2XYrYln0f/* $FF was: 12XYrYln0f*/(0cC var0) {
      return var0.player;
   }

   private static RayTraceResult njOACxdnWT(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cD wtwknqWlLA(0cC var0) {
      return var0.player;
   }

   public void setDimensionAndSpawnPlayer(int dimension) {
      1GwL3Q4bWk(this).getWorld().setInitialSpawnLocation();
      jSxyrm2tQo(this).getWorld().removeAllEntities();
      int i = 22962 ^ -16243 ^ 28508 ^ -2461;
      String s = null;
      if (6Y6GbZVnbQ(l06d6l47T9(this)) != null) {
         i = KwloOneYIQ(CjsLlazICN(this)).getEntityId();
         ynndvOvy7j(this).getWorld().removeEntity(rLFvrABwDu(24QqQtyTMo(this)));
         s = 2kwGiHVtUF(7LGBo4NjUy(this)).getServerBrand();
      }

      d7rBSwIb1N(8ihDJGyogW(this), new 0cD(Lnb1MPwcGI(this)));
      T7lvBvdZSt(4JE16r7osb(this)).getDataManager().setEntryValues(WLgZ7d9s46(kSrnYnVdyG(this)).getDataManager().getAll());
      SwTGYz7DvA(SaeauGCCSU(6rY8Lk1rfX(this)), dimension);
      OFllvv6Vw6(VeOT5V6WaY(this)).preparePlayerToSpawn();
      gaf7BnuFUo(0jQDLtB1mb(this)).setServerBrand(s);
      LejnNiAkd5(this).getWorld().spawnEntity(YqOerOnNDI(SNkDrqjtLE(this)));
      vb1YnyH44K(this).flipPlayer(aNyseLYJ5q(VxB77evF2a(this)));
      LrLeF2YoyL(eea1vAvmRA(Wbw5CotVJL(this)), new 0cI(eitvD8IH7a(this)));
      wtwknqWlLA(kh3VdLdVfY(this)).setEntityId(i);
      drQmq0vCpg(this).setPlayerCapabilities(50T6Ju2IyD(Da4fqOBlTs(this)));
      mIE9ue9HUO(E1veuSs0rS(this)).setReducedDebug(pLm1Fg9jbF(ohegwqjFJN(this)).hasReducedDebug());
   }

   private static 0cD pLm1Fg9jbF(0cC var0) {
      return var0.player;
   }

   private static 0cD rLFvrABwDu(0cC var0) {
      return var0.player;
   }

   private static 0cQ IBYyNi9wyS(0cH var0) {
      return var0.playerController;
   }

   private static 0cC WliBLAV1Ll(0cH var0) {
      return var0.pbot;
   }

   private static 0cD gaf7BnuFUo(0cC var0) {
      return var0.player;
   }

   private static 0cQ ctQ5eVaATv(0cH var0) {
      return var0.playerController;
   }

   private static 0cD ube0IdrFK2(0cC var0) {
      return var0.player;
   }

   private static 0cC ikE8aykwcr(0cH var0) {
      return var0.pbot;
   }

   private static Queue V66B0WzNqe(0cH var0) {
      return var0.scheduledTasks;
   }

   private static 0cC _JE16r7osb/* $FF was: 4JE16r7osb*/(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult.Type n4GA1VUvda(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0cC qFvRIfBeir(0cH var0) {
      return var0.pbot;
   }

   private static 0cD aNyseLYJ5q(0cC var0) {
      return var0.player;
   }

   private static Logger YqajTQi7bV(0cH var0) {
      return var0.LOGGER;
   }

   private static 0cC KRFAU6f1JY(0cH var0) {
      return var0.pbot;
   }

   private static Material _PCViabNdr/* $FF was: 0PCViabNdr*/() {
      return Material.AIR;
   }

   private static EnumActionResult Or6XcyvJ4q() {
      return EnumActionResult.SUCCESS;
   }

   private static 0cD T7lvBvdZSt(0cC var0) {
      return var0.player;
   }

   private static RayTraceResult VmDObvvWar(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cC LejnNiAkd5(0cH var0) {
      return var0.pbot;
   }

   private static 0cD ghUmgJ64D6(0cC var0) {
      return var0.player;
   }

   private static Queue gwaiOPQqnH(0cH var0) {
      return var0.scheduledTasks;
   }

   private static RayTraceResult UqwcCc1YyX(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cC InyZWXQi32(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult yqqMPzuyRJ(0cH var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult agqG4LFzYN(0cH var0) {
      return var0.objectMouseOver;
   }

   private static EnumActionResult u3jiytVP2g() {
      return EnumActionResult.SUCCESS;
   }

   public void requestCompletions(String prefix) {
      if (prefix.length() >= (31030 ^ -27972 ^ 8201 ^ -13438)) {
         E5QWJNdOOb(12XYrYln0f(WliBLAV1Ll(this))).sendPacket(new CPacketTabComplete(prefix, this.getTargetBlockPos(), (boolean)(11513 ^ -8250 ^ 11265 ^ -8386)));
      }

   }

   private static 0cC fTijPF9WlU(0cH var0) {
      return var0.pbot;
   }

   private static 0cC lKaFzJ6O6m(0cH var0) {
      return var0.pbot;
   }

   private static 0cC UngGRp4qTi(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult BAOB1cA7ex(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cH tijcJWBLe9(0cC var0) {
      return var0.mc;
   }

   private static 0cC _OA6luukCd/* $FF was: 5OA6luukCd*/(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult.Type peddoganRB() {
      return RayTraceResult.Type.BLOCK;
   }

   private static 0cD Nm6ZVvPgHK(0cC var0) {
      return var0.player;
   }

   private static 0cQ pAiI3PG1Vo(0cH var0) {
      return var0.playerController;
   }

   private static RayTraceResult.Type C77aQey2Wb() {
      return RayTraceResult.Type.MISS;
   }

   private static 0cD mI6OVmwQl7(0cC var0) {
      return var0.player;
   }

   private static 0cQ nOOyTHoXlW(0cH var0) {
      return var0.playerController;
   }

   private static int[] ertxBEye6M() {
      return null.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type;
   }

   private static RayTraceResult.Type _WAIItJWCL/* $FF was: 6WAIItJWCL*/(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0cC _LGBo4NjUy/* $FF was: 7LGBo4NjUy*/(0cH var0) {
      return var0.pbot;
   }

   private static Vec3d _SDbTI46d2/* $FF was: 9SDbTI46d2*/(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0cC CjsLlazICN(0cH var0) {
      return var0.pbot;
   }

   private static 0cC j0XiyOroeV(0cH var0) {
      return var0.pbot;
   }

   private static 0cD _0jcRIngMc/* $FF was: 30jcRIngMc*/(0cC var0) {
      return var0.player;
   }

   private static 0cD AWEqGrrjVk(0cC var0) {
      return var0.player;
   }

   private static EnumFacing _cWpOHXdJL/* $FF was: 5cWpOHXdJL*/(RayTraceResult var0) {
      return var0.sideHit;
   }

   private static Material iSJjHJlMeX() {
      return Material.AIR;
   }

   private static 0cC _jQDLtB1mb/* $FF was: 0jQDLtB1mb*/(0cH var0) {
      return var0.pbot;
   }

   private static 0cD _Lf1CaAavi/* $FF was: 3Lf1CaAavi*/(0cC var0) {
      return var0.player;
   }

   private static 0cE PGgQjv1dnq(0cH var0) {
      return var0.gameSettings;
   }

   private static 0cD EQh9AYG4yY(0cC var0) {
      return var0.player;
   }

   private static Vec3d lLTdehS3vl(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0cC l06d6l47T9(0cH var0) {
      return var0.pbot;
   }

   private static 0cD yBlyyGgagc(0cC var0) {
      return var0.player;
   }

   public void runTickKeyboard() {
      this.sendClickBlockToController(wEQG8FjXQv(PGgQjv1dnq(this)));
   }

   private static Vec3d AtK4qa4aiw(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static RayTraceResult jaJvWALGJd(0cH var0) {
      return var0.objectMouseOver;
   }

   public void getMouseOver() {
      float partialTicks = Float.intBitsToFloat('\uec5f' ^ '馏' ^ 12081 ^ 455284258 ^ '\uef70' ^ '뼁' ^ 10985 ^ 614676059);
      if (rlc8HvQxL2(this).getWorld() != null) {
         double d0 = (double)MOBssjvgDs(this).getBlockReachDistance();
         JrStaBJwy5(this, ube0IdrFK2(DrbRwnszyK(this)).rayTrace(d0, partialTicks));
         Vec3d vec3d = D98v6ndsbl(isXvQvQrFN(this)).getPositionEyes(partialTicks);
         boolean flag = 22368 ^ -28331 ^ 21465 ^ -27156;
         double d1 = d0;
         if (pAiI3PG1Vo(this).extendedReach()) {
            d1 = Double.longBitsToDouble(3795835314254241393L ^ 8409773132495314545L);
            d0 = d1;
         } else if (d0 > Double.longBitsToDouble(-7432082310975834665L ^ -2822648092362132009L)) {
            flag = 25319 ^ -30978 ^ 17515 ^ -24461;
         }

         if (lBVaoCvQ0U(this) != null) {
            d1 = tyTlyBD74j(BAOB1cA7ex(this)).distanceTo(vec3d);
         }

         Vec3d vec3d1 = ERAyM3BYkY(lKaFzJ6O6m(this)).getLook(Float.intBitsToFloat(6423 ^ 93553 ^ '靨' ^ -100209628 ^ 11847 ^ 129168 ^ 18802 ^ -981036913));
         Vec3d vec3d2 = vec3d.add(QIhueyMr9X(vec3d1) * d0, X3UIkTTR4m(vec3d1) * d0, DLJ0IGV2Io(vec3d1) * d0);
         Entity pointedEntity = null;
         Vec3d vec3d3 = null;
         List<Entity> list = wrJRlQ9lgU(this).getWorld().getEntitiesInAABBexcluding(np6ooiZOFF(kt1SI3j629(this)), c2DIl82jjw(TnNYviDYNS(this)).getEntityBoundingBox().grow(MI290oPgvE(vec3d1) * d0, WlGIFOZL2y(vec3d1) * d0, o4q1aInqDA(vec3d1) * d0).grow(Double.longBitsToDouble(-4585661054766919796L ^ -23514632240607348L), Double.longBitsToDouble(-7678526076523631617L ^ -6160813002099774465L), Double.longBitsToDouble(8194210850452945245L ^ 5640669861733874013L)), Predicates.and(2wEfV9st9e(), new Predicate<Entity>() {
            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((Entity)var1);
            }

            public boolean apply(@Nullable Entity p_apply_1_) {
               return (boolean)(p_apply_1_ != null && p_apply_1_.canBeCollidedWith() ? 16162 ^ -15511 ^ 5690 ^ -5520 : 15483 ^ -11037 ^ 4065 ^ -6279);
            }
         }));
         double d2 = d1;
         Iterator var15 = list.iterator();

         while(true) {
            while(var15.hasNext()) {
               Entity entity1 = (Entity)var15.next();
               AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
               RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
               if (axisalignedbb.contains(vec3d)) {
                  if (d2 >= Double.longBitsToDouble(-3906450791805433134L ^ -3906450791805433134L)) {
                     pointedEntity = entity1;
                     vec3d3 = raytraceresult == null ? vec3d : 9SDbTI46d2(raytraceresult);
                     d2 = Double.longBitsToDouble(765568833719554286L ^ 765568833719554286L);
                  }
               } else if (raytraceresult != null) {
                  double d3 = vec3d.distanceTo(7xAYIjjjr6(raytraceresult));
                  if (d3 < d2 || d2 == Double.longBitsToDouble(-2613061009517354843L ^ -2613061009517354843L)) {
                     if (entity1.getLowestRidingEntity() == 8P5bY9DYBB(neL213Id3y(this)).getLowestRidingEntity()) {
                        if (d2 == Double.longBitsToDouble(-7209363849409743388L ^ -7209363849409743388L)) {
                           pointedEntity = entity1;
                           vec3d3 = AtK4qa4aiw(raytraceresult);
                        }
                     } else {
                        pointedEntity = entity1;
                        vec3d3 = lLTdehS3vl(raytraceresult);
                        d2 = d3;
                     }
                  }
               }
            }

            if (pointedEntity != null && flag != 0 && vec3d.distanceTo(vec3d3) > Double.longBitsToDouble(-3588781391217926434L ^ -8198215609831629090L)) {
               pointedEntity = null;
               BBj6TKvZhH(this, new RayTraceResult(C77aQey2Wb(), vec3d3, (EnumFacing)null, new BlockPos(vec3d3)));
            }

            if (pointedEntity != null && (d2 < d1 || WgGF3hiNdV(this) == null)) {
               Q11ovSzbEg(this, new RayTraceResult(pointedEntity, vec3d3));
            }
            break;
         }
      }

   }

   private static 0cC neL213Id3y(0cH var0) {
      return var0.pbot;
   }

   private static 0cC F4YQDymWNA(0cH var0) {
      return var0.pbot;
   }

   private static double DLJ0IGV2Io(Vec3d var0) {
      return var0.z;
   }

   private static 0cC TnNYviDYNS(0cH var0) {
      return var0.pbot;
   }

   private static 0cC tHzL01VjkY(0cH var0) {
      return var0.pbot;
   }

   private static 0cQ oLWG4qg99N(0cH var0) {
      return var0.playerController;
   }

   private static int[] idKIJlJ6m4() {
      return null.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type;
   }

   private static 0cD _FYTraRD6a/* $FF was: 4FYTraRD6a*/(0cC var0) {
      return var0.player;
   }

   private static 0cD OFllvv6Vw6(0cC var0) {
      return var0.player;
   }

   private static RayTraceResult.Type FghT1Zaxtu() {
      return RayTraceResult.Type.BLOCK;
   }

   private static 0cC SNkDrqjtLE(0cH var0) {
      return var0.pbot;
   }

   private static 0cD WLgZ7d9s46(0cC var0) {
      return var0.player;
   }

   private static Vec3d _xAYIjjjr6/* $FF was: 7xAYIjjjr6*/(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0cD _VtecxvBHD/* $FF was: 0VtecxvBHD*/(0cC var0) {
      return var0.player;
   }

   private static Entity MY7Yn5YTs6(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static Queue _7PmyI2NJx/* $FF was: 47PmyI2NJx*/(0cH var0) {
      return var0.scheduledTasks;
   }

   private static EnumHand _yeQlntTv7/* $FF was: 4yeQlntTv7*/() {
      return EnumHand.MAIN_HAND;
   }

   private static Vec3d tyTlyBD74j(RayTraceResult var0) {
      return var0.hitVec;
   }

   public <V> ListenableFuture<V> addScheduledTask(Callable<V> callableToSchedule) {
      Validate.notNull(callableToSchedule);
      if (this.isCallingFromMinecraftThread()) {
         try {
            return Futures.immediateFuture(callableToSchedule.call());
         } catch (Exception var3) {
            Exception exception = var3;
            return Futures.immediateFailedCheckedFuture(exception);
         }
      } else {
         ListenableFutureTask<V> listenablefuturetask = ListenableFutureTask.create(callableToSchedule);
         47PmyI2NJx(this).add(listenablefuturetask);
         return listenablefuturetask;
      }
   }

   private static 0cC t20HB454IM(0cH var0) {
      return var0.pbot;
   }

   private static EnumHand NKl4vHUzLs() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cC GAZN5TeK6G(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult WgGF3hiNdV(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cD baelwGRwqc(0cC var0) {
      return var0.player;
   }

   private static 0cC _ihDJGyogW/* $FF was: 8ihDJGyogW*/(0cH var0) {
      return var0.pbot;
   }

   private static 0cC kvypQ6wQs4(0cH var0) {
      return var0.pbot;
   }

   private static 0cD D98v6ndsbl(0cC var0) {
      return var0.player;
   }

   private static 0cC Da4fqOBlTs(0cH var0) {
      return var0.pbot;
   }

   public BlockPos getTargetBlockPos() {
      BlockPos blockpos = null;
      if (MTBYi5AgsA(this) != null && 6WAIItJWCL(agqG4LFzYN(this)) == peddoganRB()) {
         blockpos = jaJvWALGJd(this).getBlockPos();
      }

      return blockpos;
   }

   private static RayTraceResult fKELNMTJV7(0cH var0) {
      return var0.objectMouseOver;
   }

   private void sendClickBlockToController(boolean leftClick) {
      if (!ghUmgJ64D6(Apk5LygW7N(this)).isHandActive()) {
         if (leftClick && VmDObvvWar(this) != null && fKa2VN7AJl(yqqMPzuyRJ(this)) == FghT1Zaxtu()) {
            BlockPos blockpos = yojgmnS8tS(this).getBlockPos();
            if (kvypQ6wQs4(this).getWorld().getBlockState(blockpos).getMaterial() != iSJjHJlMeX() && DBTtDz7rWV(this).onPlayerDamageBlock(blockpos, XOF2rfh5rV(njOACxdnWT(this)))) {
               iUWD4Gb3FK(JjDPOIFTIH(this)).swingArm(NKl4vHUzLs());
            }
         } else {
            jVUFTFD5vC(this).resetBlockRemoving();
         }
      }

   }

   private static RayTraceResult _le7833mFz/* $FF was: 7le7833mFz*/(0cH var0) {
      return var0.objectMouseOver;
   }

   private static void GNo0I26nFQ(0cD var0, MovementInput var1) {
      var0.movementInput = var1;
   }

   private static 0cC Y5Fg9BiIpt(0cH var0) {
      return var0.pbot;
   }

   private static 0cC kt1SI3j629(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult NOp2l2Qa3d(0cH var0) {
      return var0.objectMouseOver;
   }

   public boolean isSnooperEnabled() {
      return (boolean)(4708 ^ -21284 ^ 15774 ^ -31962);
   }

   private static 0cQ DBTtDz7rWV(0cH var0) {
      return var0.playerController;
   }

   private static double X3UIkTTR4m(Vec3d var0) {
      return var0.y;
   }

   private static 0cC _GwL3Q4bWk/* $FF was: 1GwL3Q4bWk*/(0cH var0) {
      return var0.pbot;
   }

   private static 0cC _4QqQtyTMo/* $FF was: 24QqQtyTMo*/(0cH var0) {
      return var0.pbot;
   }

   private static 0cQ xBlI0HeLFB(0cH var0) {
      return var0.playerController;
   }

   private static RayTraceResult.Type fKa2VN7AJl(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static double WlGIFOZL2y(Vec3d var0) {
      return var0.y;
   }

   private static 0cC AKYj7ijsUD(0cH var0) {
      return var0.pbot;
   }

   public _cH/* $FF was: 0cH*/(0cC pbot) {
      this.pbot = pbot;
   }

   private static 0cC kSrnYnVdyG(0cH var0) {
      return var0.pbot;
   }

   private static RayTraceResult mgFLolQTo7(0cH var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult yojgmnS8tS(0cH var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult.Type ng3yTy9Jyn(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0cC Lnb1MPwcGI(0cH var0) {
      return var0.pbot;
   }

   public void rightClickMouse() {
      if (!xoB2tHbif8(this).getIsHittingBlock() && !Ycpmj4GLoT(ikE8aykwcr(this)).isRowingBoat()) {
         EnumHand[] var1 = EnumHand.values();
         int var2 = var1.length;

         for(int var3 = 23633 ^ -1313 ^ 367 ^ -22559; var3 < var2; ++var3) {
            EnumHand enumhand = var1[var3];
            ItemStack itemstack = irJOCkJyW8(Y5Fg9BiIpt(this)).getHeldItem(enumhand);
            if (D5jOZp47So(this) != null) {
               switch (idKIJlJ6m4()[ng3yTy9Jyn(fKELNMTJV7(this)).ordinal()]) {
                  case 1:
                     if (nOOyTHoXlW(this).interactWithEntity(4nbzXtBp3g(qFvRIfBeir(this)), MY7Yn5YTs6(UqwcCc1YyX(this)), tGqjro9wNI(this), enumhand) == u3jiytVP2g()) {
                        return;
                     }

                     if (oLWG4qg99N(this).interactWithEntity(64UBoSMlob(tQ46JlT6D4(this)), BIOVwgD5o6(g5KI7amQJx(this)), enumhand) == Or6XcyvJ4q()) {
                        return;
                     }
                     break;
                  case 2:
                     BlockPos blockpos = 15BdiC4trr(this).getBlockPos();
                     if (KRFAU6f1JY(this).getWorld().getBlockState(blockpos).getMaterial() != C1vGRV4QDl()) {
                        int i = itemstack.getCount();
                        EnumActionResult enumactionresult = JF5Sgq6ecg(this).processRightClickBlock(Nm6ZVvPgHK(P4fSQnJ44V(this)), jwWsPW3FOR(this).getWorld(), blockpos, 5cWpOHXdJL(ifXWLsn6aC(this)), YnUWLyI8B2(NOp2l2Qa3d(this)), enumhand);
                        if (enumactionresult == eygEVJtVIU()) {
                           yBlyyGgagc(GAZN5TeK6G(this)).swingArm(enumhand);
                           return;
                        }
                     }
               }
            }

            if (!itemstack.isEmpty() && oyAUO7SB2Y(this).processRightClick(EQh9AYG4yY(t20HB454IM(this)), fTijPF9WlU(this).getWorld(), enumhand) == oF6UFtowyb()) {
               return;
            }
         }
      }

   }

   private static 0cC JjDPOIFTIH(0cH var0) {
      return var0.pbot;
   }

   private static 0cQ MOBssjvgDs(0cH var0) {
      return var0.playerController;
   }

   private static 0cD _Y6GbZVnbQ/* $FF was: 6Y6GbZVnbQ*/(0cC var0) {
      return var0.player;
   }

   private static void SwTGYz7DvA(0cD var0, int var1) {
      var0.dimension = var1;
   }

   private static Vec3d YnUWLyI8B2(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static RayTraceResult tGqjro9wNI(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cD eea1vAvmRA(0cC var0) {
      return var0.player;
   }

   private static 0cQ qDvea8qTJu(0cH var0) {
      return var0.playerController;
   }

   private static Predicate _wEfV9st9e/* $FF was: 2wEfV9st9e*/() {
      return EntitySelectors.NOT_SPECTATING;
   }

   private static RayTraceResult kqwmnWGFYk(0cH var0) {
      return var0.objectMouseOver;
   }

   private static 0cC On9wbGw2k6(0cH var0) {
      return var0.pbot;
   }

   public boolean isCallingFromMinecraftThread() {
      return (boolean)(14136 ^ -28085 ^ 22084 ^ -3274);
   }

   private static 0cC l7mtve76xV(0cH var0) {
      return var0.pbot;
   }

   private static 0cC ohegwqjFJN(0cH var0) {
      return var0.pbot;
   }

   private static 0cD np6ooiZOFF(0cC var0) {
      return var0.player;
   }

   private static Queue jnD1gfYXNn(0cH var0) {
      return var0.scheduledTasks;
   }

   private static 0cD lZB6gOYSuy(0cC var0) {
      return var0.player;
   }

   private static 0cD KwloOneYIQ(0cC var0) {
      return var0.player;
   }

   private static 0cC ibrJW2Pc7g(0cH var0) {
      return var0.pbot;
   }

   private static void LrLeF2YoyL(0cD var0, MovementInput var1) {
      var0.movementInput = var1;
   }

   public void loadWorld(0cU world) {
      if (world == null) {
         0cL net = AKYj7ijsUD(this).getPlayHandler();
         if (net != null) {
            net.cleanup();
         }

         h59PoGvmOb(this, (0cQ)null);
      }

      ibrJW2Pc7g(this).setWorld(world);
      if (TXJN6HrAbT(UHEq7aFLqL(this)) == null) {
         TFzF6nQAv3(UngGRp4qTi(this), new 0cD(l7mtve76xV(this)));
         xBlI0HeLFB(19VP1SJizv(j0XiyOroeV(this))).flipPlayer(0VtecxvBHD(b7Z7ODV41V(this)));
      }

      if (world != null) {
         mI6OVmwQl7(veDgj7gZ9G(this)).preparePlayerToSpawn();
         SjzNQS67qd(this).getWorld().spawnEntity(baelwGRwqc(WHrUFXJFYq(this)));
         GNo0I26nFQ(lZB6gOYSuy(4wnewAderi(this)), new 0cI(d9SGwQ3qjU(4ZTSl97tBv(nCoU07Nv97(this)))));
         IBYyNi9wyS(tijcJWBLe9(BFQNINM4Bs(this))).setPlayerCapabilities(30jcRIngMc(InyZWXQi32(this)));
      }

   }

   private static 0cC P4fSQnJ44V(0cH var0) {
      return var0.pbot;
   }

   private static 0cC jwWsPW3FOR(0cH var0) {
      return var0.pbot;
   }

   private static 0cD _0T6Ju2IyD/* $FF was: 50T6Ju2IyD*/(0cC var0) {
      return var0.player;
   }

   private static 0cC _rY8Lk1rfX/* $FF was: 6rY8Lk1rfX*/(0cH var0) {
      return var0.pbot;
   }

   private static Material C1vGRV4QDl() {
      return Material.AIR;
   }

   private static RayTraceResult D5jOZp47So(0cH var0) {
      return var0.objectMouseOver;
   }

   private static EnumFacing _AcSgH9beY/* $FF was: 4AcSgH9beY*/(RayTraceResult var0) {
      return var0.sideHit;
   }

   private static 0cC UHEq7aFLqL(0cH var0) {
      return var0.pbot;
   }

   private static 0cC nCoU07Nv97(0cH var0) {
      return var0.pbot;
   }

   private static 0cD _nbzXtBp3g/* $FF was: 4nbzXtBp3g*/(0cC var0) {
      return var0.player;
   }

   private static 0cC wrJRlQ9lgU(0cH var0) {
      return var0.pbot;
   }

   private static 0cC tQ46JlT6D4(0cH var0) {
      return var0.pbot;
   }

   private static 0cD c2DIl82jjw(0cC var0) {
      return var0.player;
   }

   private static 0cC Wbw5CotVJL(0cH var0) {
      return var0.pbot;
   }

   private static void BBj6TKvZhH(0cH var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static 0cC jSxyrm2tQo(0cH var0) {
      return var0.pbot;
   }

   public void execTasks() {
      synchronized(V66B0WzNqe(this)) {
         while(!gwaiOPQqnH(this).isEmpty()) {
            Util.runTask((FutureTask)jnD1gfYXNn(this).poll(), YqajTQi7bV(this));
         }

      }
   }

   private static 0cC Apk5LygW7N(0cH var0) {
      return var0.pbot;
   }

   private static 0cC kh3VdLdVfY(0cH var0) {
      return var0.pbot;
   }

   private static 0cH _9VP1SJizv/* $FF was: 19VP1SJizv*/(0cC var0) {
      return var0.mc;
   }

   public ListenableFuture<Object> addScheduledTask(Runnable runnableToSchedule) {
      Validate.notNull(runnableToSchedule);
      return this.addScheduledTask(Executors.callable(runnableToSchedule));
   }

   private static 0cD ERAyM3BYkY(0cC var0) {
      return var0.player;
   }
}
