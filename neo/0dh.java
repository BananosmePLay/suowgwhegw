package neo;

import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3i;
import net.minecraft.entity.Entity;

public class 0dh {
   public int index;
   public boolean pause;
   public Vector3i finalPos;
   public final 0cC pbot;
   public List<0dg> path;
   public Vector3i startPos;
   public boolean isRunning;

   private static void naFve3Og7V(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static float B3sYn1Yw7X(Vector2f var0) {
      return var0.x;
   }

   private static List nDCgOl6zWh(0dh var0) {
      return var0.path;
   }

   private static boolean hNIgMOoTyJ(0dh var0) {
      return var0.isRunning;
   }

   private static 0cD C0UrkGoMAJ(0cC var0) {
      return var0.player;
   }

   private static List _9Y5D0e6Y9/* $FF was: 29Y5D0e6Y9*/(0dh var0) {
      return var0.path;
   }

   public void pause() {
      vXOrFVA5v4(this, (boolean)(22060 ^ -2108 ^ 8546 ^ -32629));
   }

   private static 0cD bk7jrKODlh(0cC var0) {
      return var0.player;
   }

   private static 0cC zTuT7YT5PV(0dh var0) {
      return var0.pbot;
   }

   private static 0cH xRfAyPmLsg(0cC var0) {
      return var0.mc;
   }

   private static Vector3i vyAYaVqMN5(0dg var0) {
      return var0.pos;
   }

   private static void BeClTMN1uG(0dh var0, int var1) {
      var0.index = var1;
   }

   private static 0cD Qx9lUbi1y3(0cC var0) {
      return var0.player;
   }

   private static void iQgKZtFRjl(0dh var0, Vector3i var1) {
      var0.finalPos = var1;
   }

   private static boolean t79wW9r8kC(0dh var0) {
      return var0.isRunning;
   }

   private static 0cE gcCV6mSjGd(0cH var0) {
      return var0.gameSettings;
   }

   private static void _7nbNtO0y9/* $FF was: 27nbNtO0y9*/(0dh var0, int var1) {
      var0.index = var1;
   }

   public void pathFindStop() {
      if (t79wW9r8kC(this) && rhQ9m1lWRq(vds3zC5l0x(this)) != null) {
         b63dFdPy2q(gcCV6mSjGd(xRfAyPmLsg(IgwwGBieaS(this))), (boolean)(4249 ^ -8544 ^ 10650 ^ -6237));
         A2yd5NeAfO(0PJwrcOhKx(UntF1wADvY(Sre4jEIf9A(this))), (boolean)(15191 ^ -16510 ^ 7507 ^ -26234));
      }

      JSFRjmfVmW(this, (boolean)(14479 ^ -1180 ^ 32622 ^ -17275));
      this.pause();
      TeUyMVDg7j(this, 27813 ^ -9866 ^ 12574 ^ -31539);
   }

   private static float iS0grdRqjv(Vector2f var0) {
      return var0.y;
   }

   private static Vector3i lQV6h2Q8iL(0dg var0) {
      return var0.pos;
   }

   private static 0cC NAJlRsHyeO(0dh var0) {
      return var0.pbot;
   }

   private static double _W1IzgnOqi/* $FF was: 1W1IzgnOqi*/(0cD var0) {
      return var0.posY;
   }

   private static Vector3i _RZ0FQKmTN/* $FF was: 7RZ0FQKmTN*/(0dg var0) {
      return var0.pos;
   }

   private static int IwSpVuzlnj(Vector3i var0) {
      return var0.z;
   }

   private static List _2TbwQNoLN/* $FF was: 32TbwQNoLN*/(0dh var0) {
      return var0.path;
   }

   private static double wdw7XVOf4B(Entity var0) {
      return var0.posX;
   }

   private static 0cC kLVJ7vtTB1(0dh var0) {
      return var0.pbot;
   }

   private static 0cD TWJr0PwQ1r(0cC var0) {
      return var0.player;
   }

   private static 0cC n9DlW88Vqk(0dh var0) {
      return var0.pbot;
   }

   private static List vjwq8rQa9Q(0dh var0) {
      return var0.path;
   }

   private static 0cC o1pM5464FA(0dh var0) {
      return var0.pbot;
   }

   private static 0cC _2bQ0msFLy/* $FF was: 82bQ0msFLy*/(0dh var0) {
      return var0.pbot;
   }

   private static 0cC xG2FRMoQIR(0dh var0) {
      return var0.pbot;
   }

   private static 0cD rdAwfbTdJe(0cC var0) {
      return var0.player;
   }

   private static void ryL9nw3MJT(0dh var0, Vector3i var1) {
      var0.startPos = var1;
   }

   public boolean isRunning() {
      return Q0nqOHBg9x(this);
   }

   private static void YFLcltvGbS(0dh var0, List var1) {
      var0.path = var1;
   }

   private static int Ldj4lGsMwe(Vector3i var0) {
      return var0.z;
   }

   private static 0cH UntF1wADvY(0cC var0) {
      return var0.mc;
   }

   private static List NDTnCBBA5y(0dh var0) {
      return var0.path;
   }

   private static 0cC jJec07j2Lh(0dh var0) {
      return var0.pbot;
   }

   private static 0cD tjScFE16o8(0cC var0) {
      return var0.player;
   }

   private static void b63dFdPy2q(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static Vector3i i4mtmbGuLs(0dg var0) {
      return var0.pos;
   }

   private static 0cC IgwwGBieaS(0dh var0) {
      return var0.pbot;
   }

   private static Vector3i VhoN1a6pNh(0dg var0) {
      return var0.pos;
   }

   private static Vector3i qUZdTQ9ST7(0dg var0) {
      return var0.pos;
   }

   private static 0cC Sre4jEIf9A(0dh var0) {
      return var0.pbot;
   }

   private static 0cD QEcsW75yRt(0cC var0) {
      return var0.player;
   }

   private static 0cC obFs6emFrw(0dh var0) {
      return var0.pbot;
   }

   private static int _2A9OSNI7k/* $FF was: 42A9OSNI7k*/(Vector3i var0) {
      return var0.z;
   }

   private static Vector3i _IeOl3TFA7/* $FF was: 2IeOl3TFA7*/(0dh var0) {
      return var0.startPos;
   }

   private static double Mvrd95wYtq(Entity var0) {
      return var0.posY;
   }

   private static int WeHlxnaqVJ(Vector3i var0) {
      return var0.y;
   }

   private static 0cD gJnTFOJRGE(0cC var0) {
      return var0.player;
   }

   private static List gzBvGYFXmZ(0dh var0) {
      return var0.path;
   }

   private static void vXOrFVA5v4(0dh var0, boolean var1) {
      var0.pause = var1;
   }

   private static 0bv vGIQL1QkN1() {
      return 0cc.baritoneDebug;
   }

   public _dh/* $FF was: 0dh*/(0cC pbot) {
      this.pbot = pbot;
      this.path = new ArrayList();
      this.index = 12588 ^ -16141 ^ 31060 ^ -30581;
      this.isRunning = (boolean)(13218 ^ -15789 ^ 13385 ^ -14920);
      this.pause = (boolean)(24886 ^ -2262 ^ 19895 ^ -9302);
      this.startPos = null;
      this.finalPos = null;
   }

   private static 0cH HuJtTZoDcq(0cC var0) {
      return var0.mc;
   }

   private static double rRBtBL7y54(Entity var0) {
      return var0.posX;
   }

   private static void BgeIjiBIG6(0dh var0, boolean var1) {
      var0.isRunning = var1;
   }

   private static int _XBe9BaFBl/* $FF was: 9XBe9BaFBl*/(0dh var0) {
      return var0.index;
   }

   private static 0cC yqbGBsDBK5(0dh var0) {
      return var0.pbot;
   }

   private static 0cC vVfPB9EYjY(0dh var0) {
      return var0.pbot;
   }

   private static List rBDGJLxCJ7(0dh var0) {
      return var0.path;
   }

   private static double YBBlfqL6d6(0cD var0) {
      return var0.posY;
   }

   private static 0cE _PJwrcOhKx/* $FF was: 0PJwrcOhKx*/(0cH var0) {
      return var0.gameSettings;
   }

   private static Vector3i gNY4duOwPT(0dg var0) {
      return var0.pos;
   }

   private static 0cC RFXI4QOY6v(0dh var0) {
      return var0.pbot;
   }

   private static 0cH rhQ9m1lWRq(0cC var0) {
      return var0.mc;
   }

   private static boolean _f0eN6YMWW/* $FF was: 6f0eN6YMWW*/(0bv var0) {
      return var0.value;
   }

   private static double LtDQxIuu05(0cD var0) {
      return var0.posZ;
   }

   private static int wMIWTGioJz(Vector3i var0) {
      return var0.y;
   }

   private static void _9l68gYIaJ/* $FF was: 49l68gYIaJ*/(0dh var0, boolean var1) {
      var0.pause = var1;
   }

   private static boolean gD6bCDEm7F(0dj var0) {
      return var0.scanned;
   }

   private static 0cC EZLprJWDQq(0dh var0) {
      return var0.pbot;
   }

   private static 0cE msv2pSiDjo(0cH var0) {
      return var0.gameSettings;
   }

   private static Vector3i vqYjD9TigR(0dg var0) {
      return var0.pos;
   }

   private static int gGIJASA3pt(0dh var0) {
      return var0.index;
   }

   private static Vector3i TpbTbqYqAo(0dh var0) {
      return var0.finalPos;
   }

   private static void DwlNm1LwDy(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0cD IuVyoxo4gb(0cC var0) {
      return var0.player;
   }

   private static void JSFRjmfVmW(0dh var0, boolean var1) {
      var0.isRunning = var1;
   }

   public boolean shouldWalk() {
      if (!hNIgMOoTyJ(this)) {
         return (boolean)(24084 ^ -9577 ^ 9766 ^ -23899);
      } else {
         return (boolean)(FWi8cV6NdC(this) ? 23588 ^ -18897 ^ 12489 ^ -9534 : 28973 ^ -11915 ^ 29246 ^ -11673);
      }
   }

   private static int MO7id9DGBD(0dh var0) {
      return var0.index;
   }

   private static boolean FWi8cV6NdC(0dh var0) {
      return var0.pause;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String BqdOfagAjX(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 19849 ^ -2073 ^ 27498 ^ -12028; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3423 ^ -11131 ^ 21594 ^ -30550));
      }

      return var1.toString();
   }

   private static int OtNrGSPUUW(Vector3i var0) {
      return var0.x;
   }

   private static int bwjooSrDVx(Vector3i var0) {
      return var0.y;
   }

   private static 0cD atMTdiSRGL(0cC var0) {
      return var0.player;
   }

   private static 0cD zGcN5dN3Sb(0cC var0) {
      return var0.player;
   }

   private static Vector3i _bf5vF94Ox/* $FF was: 1bf5vF94Ox*/(0dg var0) {
      return var0.pos;
   }

   private static 0cC noex6hEKQ2(0dh var0) {
      return var0.pbot;
   }

   private static void LwAQSuxAmT(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static boolean FL6St5GGtA(0cD var0) {
      return var0.onGround;
   }

   private static void TeUyMVDg7j(0dh var0, int var1) {
      var0.index = var1;
   }

   public void pathFindStart(Vector3i pos) {
      this.pathFindStop();
      ryL9nw3MJT(this, new Vector3i(TGCgeid2vY(C0UrkGoMAJ(jJec07j2Lh(this))), 1W1IzgnOqi(sdDenYHOFI(n9DlW88Vqk(this))), LtDQxIuu05(rdAwfbTdJe(obFs6emFrw(this)))));
      iQgKZtFRjl(this, pos);
      0di.scanPath(o1pM5464FA(this), gnniwYfILW(this), dSdmLkR4jT(this));
   }

   public void unPause() {
      49l68gYIaJ(this, (boolean)(21095 ^ -11425 ^ 27340 ^ -5132));
   }

   private static Vector3i dSdmLkR4jT(0dh var0) {
      return var0.finalPos;
   }

   private static int bSL0oQKj8l(0dh var0) {
      return var0.index;
   }

   private static void D6Q2S0jeOm(0dh var0, boolean var1) {
      var0.isRunning = var1;
   }

   public void pathFindWalk() {
      0dj data = 0di.getPathData(2IeOl3TFA7(this), TpbTbqYqAo(this));
      if (data == null) {
         this.pathFindStop();
      } else {
         if (data.onUpdate(atMTdiSRGL(yqbGBsDBK5(this)))) {
            Y1Lr1hoxIE(this, 18874 ^ -30425 ^ 23673 ^ -25372);
         }

         this.copyList(V6bn1QV2cv(data).getPathList());
         if (32TbwQNoLN(this) != null) {
            if (nDCgOl6zWh(this).isEmpty()) {
               QSoi99TJlo(this, (boolean)(10168 ^ -16072 ^ 6289 ^ -495));
               if (gD6bCDEm7F(data)) {
                  D6Q2S0jeOm(this, (boolean)(2441 ^ -24137 ^ 3529 ^ -23050));
                  27nbNtO0y9(this, 23454 ^ -15203 ^ 14440 ^ -22677);
               }
            } else {
               BgeIjiBIG6(this, (boolean)(31892 ^ -22352 ^ 25978 ^ -20129));
            }

            if (this.shouldWalk()) {
               if (!rBDGJLxCJ7(this).isEmpty()) {
                  if (gGIJASA3pt(this) < NDTnCBBA5y(this).size()) {
                     try {
                        0dg point = (0dg)29Y5D0e6Y9(this).get(MO7id9DGBD(this));
                        if (point != null) {
                           double offset = Double.longBitsToDouble(7502405489615062656L ^ 6340476785753474688L);
                           Vector2f angles = 0dm.getBlockAngles((double)eb0I2N3d1T(gNY4duOwPT(point)) + offset, (double)bwjooSrDVx(1bf5vF94Ox(point)), (double)Ldj4lGsMwe(vqYjD9TigR(point)) + offset, TcsVtZqvED(Qx9lUbi1y3(vVfPB9EYjY(this))), YBBlfqL6d6(bk7jrKODlh(NAJlRsHyeO(this))), ViMEfgOTZC(QEcsW75yRt(kLVJ7vtTB1(this))));
                           float nY = 0dm.normalizeYaw(iS0grdRqjv(angles));
                           float nP = 0dm.normalizePitch(B3sYn1Yw7X(angles));
                           if (!Float.isNaN(nY) && !Float.isNaN(nP)) {
                              DwlNm1LwDy(TWJr0PwQ1r(82bQ0msFLy(this)), nY);
                              LwAQSuxAmT(IuVyoxo4gb(VfsOuod9gT(this)), nP);
                           }

                           naFve3Og7V(msv2pSiDjo(HuJtTZoDcq(xG2FRMoQIR(this))), (boolean)(14344 ^ -4227 ^ 31644 ^ -21272));
                           Entity ent = zGcN5dN3Sb(zTuT7YT5PV(this));
                           boolean inside = (double)OtNrGSPUUW(VhoN1a6pNh(point)) <= wdw7XVOf4B(ent) && (double)(Yn2ERuRs6o(qUZdTQ9ST7(point)) + (31053 ^ -11955 ^ 4775 ^ -17754)) >= rRBtBL7y54(ent) && (double)(WeHlxnaqVJ(vyAYaVqMN5(point)) - (32667 ^ -6922 ^ 13390 ^ -20702)) <= eokjDo5oSf(ent) && (double)(wMIWTGioJz(7RZ0FQKmTN(point)) + (22516 ^ -21420 ^ 2151 ^ -3130)) >= Mvrd95wYtq(ent) && (double)42A9OSNI7k(i4mtmbGuLs(point)) <= qjxrwMtLrY(ent) && (double)(IwSpVuzlnj(lQV6h2Q8iL(point)) + (2047 ^ -32545 ^ 25021 ^ -6500)) >= eEij2wMVSF(ent) ? 10639 ^ -8372 ^ 19240 ^ -16918 : 8607 ^ -8438 ^ 2195 ^ -2554;
                           if (inside != 0) {
                              if (bSL0oQKj8l(this) < vjwq8rQa9Q(this).size() - (5151 ^ -25161 ^ 14980 ^ -19667)) {
                                 if (FL6St5GGtA(tjScFE16o8(RFXI4QOY6v(this))) || gJnTFOJRGE(noex6hEKQ2(this)).isInWater()) {
                                    BeClTMN1uG(this, 9XBe9BaFBl(this) + (22609 ^ -8933 ^ 22989 ^ -9082));
                                 }
                              } else {
                                 this.pathFindStop();
                                 if (6f0eN6YMWW(vGIQL1QkN1())) {
                                    0dK.defaultMsg(BqdOfagAjX("ԌԟձԌՎըՅ՞ԇըՋ\u0558Ճ՞ՅՄՏԌԟշԊԌԝըՅ՞Ԋ") + EZLprJWDQq(this).getNickname() + BqdOfagAjX("ԊՖԊՌՃՄՃՙՂՏՎ"));
                                 }
                              }
                           }
                        }
                     } catch (Exception var10) {
                        Exception ex = var10;
                        ex.printStackTrace();
                        this.pathFindStop();
                     }

                  }
               }
            }
         }
      }
   }

   private static double eEij2wMVSF(Entity var0) {
      return var0.posZ;
   }

   private static void QSoi99TJlo(0dh var0, boolean var1) {
      var0.isRunning = var1;
   }

   private static void Y1Lr1hoxIE(0dh var0, int var1) {
      var0.index = var1;
   }

   private static void A2yd5NeAfO(0cE var0, boolean var1) {
      var0.keyBindJump = var1;
   }

   private static Vector3i gnniwYfILW(0dh var0) {
      return var0.startPos;
   }

   private static int Yn2ERuRs6o(Vector3i var0) {
      return var0.x;
   }

   private static double qjxrwMtLrY(Entity var0) {
      return var0.posZ;
   }

   private static boolean Q0nqOHBg9x(0dh var0) {
      return var0.isRunning;
   }

   private static double TcsVtZqvED(0cD var0) {
      return var0.posX;
   }

   private static double TGCgeid2vY(0cD var0) {
      return var0.posX;
   }

   private static 0cC vds3zC5l0x(0dh var0) {
      return var0.pbot;
   }

   private void copyList(List<0dg> listToCopy) {
      gzBvGYFXmZ(this).clear();
      if (!listToCopy.isEmpty()) {
         YFLcltvGbS(this, new ArrayList(listToCopy));
      }
   }

   private static 0dd V6bn1QV2cv(0dj var0) {
      return var0.scanner;
   }

   private static 0cD sdDenYHOFI(0cC var0) {
      return var0.player;
   }

   private static 0cC VfsOuod9gT(0dh var0) {
      return var0.pbot;
   }

   private static double ViMEfgOTZC(0cD var0) {
      return var0.posZ;
   }

   private static double eokjDo5oSf(Entity var0) {
      return var0.posY;
   }

   private static int eb0I2N3d1T(Vector3i var0) {
      return var0.x;
   }
}
