package neo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.vecmath.Vector3i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class 0dc extends 0dd {
   public final Vector3i from;
   public final Vector3i to;
   public boolean running;
   public World world;

   private static Vector3i gtL1WviYM7(0dg var0) {
      return var0.pos;
   }

   private static CopyOnWriteArrayList _4LQ1xeYym/* $FF was: 04LQ1xeYym*/(0dc var0) {
      return var0.unchecked;
   }

   private static Vector3i h44i5TQybD(0dg var0) {
      return var0.pos;
   }

   private static int P74jdEv4RC(Vector3i var0) {
      return var0.z;
   }

   private static void _R4nI9faVJ/* $FF was: 9R4nI9faVJ*/(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static Vector3i J6YBeuJ1v2(0dg var0) {
      return var0.pos;
   }

   private static World _vGsL78ZOh/* $FF was: 1vGsL78ZOh*/(0dc var0) {
      return var0.world;
   }

   private static int _r0PVTh0wI/* $FF was: 2r0PVTh0wI*/(Vector3i var0) {
      return var0.z;
   }

   private static int RHn9ktfatJ(Vector3i var0) {
      return var0.y;
   }

   private static CopyOnWriteArrayList _3suh7dwSm/* $FF was: 63suh7dwSm*/(0dc var0) {
      return var0.path;
   }

   private static int G9862bRA3L(Vector3i var0) {
      return var0.y;
   }

   private static void _6eHRsyYie/* $FF was: 46eHRsyYie*/(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private List<0dg> getNear(0dg point) {
      List<0dg> list = new ArrayList();
      if (Pz3yrGwxKV(point) >= (12401 ^ -17300 ^ 9185 ^ -20612)) {
         return list;
      } else {
         Vector3i posFrom = new Vector3i(WbDD7YboJG(Sdr5I7eSAf(point)), yjTFGEUSHU(jJjQ9EjUoY(point)), P74jdEv4RC(gtL1WviYM7(point)));

         for(int yoffset = -1886 ^ -2009 ^ 4701 ^ -4832; yoffset < (1344 ^ -20563 ^ 26886 ^ -15383); ++yoffset) {
            Vector3i temp_pos = new Vector3i(PXVnnqFTnY(QFaOVIy2l6(point)) - (7039 ^ -19308 ^ 11264 ^ -31766), OIUtNjXDdp(6aAhbOoNGX(point)) + yoffset, FwXkyuJWzQ(h44i5TQybD(point)));
            0dg near;
            if (0df.canMoveTo(posFrom, temp_pos, DpKXNFF8nB(this))) {
               near = new 0dg(temp_pos);
               wTwTZFcY1O(near, point);
               Fg1l3UKVyr(near, Yw2HaDgUOd(point) + (21845 ^ -10040 ^ 15209 ^ -18699));
               list.add(near);
            }

            temp_pos = new Vector3i(VTwJOkuY2k(kaxv1K2Nfl(point)) + (525 ^ -26126 ^ 4187 ^ -29787), zRI1zeB4fS(vNQfP4ejex(point)) + yoffset, kKkrDO52Mi(g7ptovon41(point)));
            if (0df.canMoveTo(posFrom, temp_pos, oB4eLi77qY(this))) {
               near = new 0dg(temp_pos);
               22HQ36I7i2(near, point);
               pAellorQiI(near, tGnO6SNBwJ(point) + (7808 ^ -27827 ^ 21955 ^ -10225));
               list.add(near);
            }

            temp_pos = new Vector3i(l2JQ7v6cNA(e41jFzg9LO(point)), s6GFRr9H0G(7BH92GDKlY(point)) + yoffset, YNWcLvNieR(tD2Wt1eRHc(point)) - (25617 ^ -26272 ^ 10970 ^ -10326));
            if (0df.canMoveTo(posFrom, temp_pos, nnDFttaJ0m(this))) {
               near = new 0dg(temp_pos);
               46eHRsyYie(near, point);
               DnfTXb9xWl(near, zGqWrnddmd(point) + (5136 ^ -32653 ^ 3582 ^ -26212));
               list.add(near);
            }

            temp_pos = new Vector3i(rKwOHJ1ip6(C06E0eTvgQ(point)), qWjlf4yjPp(J6YBeuJ1v2(point)) + yoffset, 4SmI1yvIVq(ygwdjQPWlT(point)) + (26223 ^ -6201 ^ 28793 ^ -3632));
            if (0df.canMoveTo(posFrom, temp_pos, 1vGsL78ZOh(this))) {
               near = new 0dg(temp_pos);
               aVruQLgnba(near, point);
               0oWn9dHnIq(near, wwnn1TifYD(point) + (16054 ^ -21649 ^ 7264 ^ -30280));
               list.add(near);
            }

            temp_pos = new Vector3i(j5rdgju9SJ(gDwbJnwwBJ(point)) - (8647 ^ -20864 ^ 24888 ^ -4482), 6B9oX9y9Ec(lb9VOkqlWq(point)) + yoffset, g3gYPyo1NG(jrHmxe9NYr(point)) - (812 ^ -27618 ^ 21999 ^ -15652));
            if (0df.canMoveToDiagonal(gotgvQygFl(point), temp_pos, hGG0e0w221(this))) {
               near = new 0dg(temp_pos);
               9R4nI9faVJ(near, point);
               6NWb9ew4nr(near, qOLP7yJFOB(point) + (16542 ^ -2849 ^ 24438 ^ -5322));
               list.add(near);
            }

            temp_pos = new Vector3i(ShkThaGo23(qYv6w79nH6(point)) + (28207 ^ -1704 ^ 20425 ^ -10049), G9862bRA3L(y61rR28l5L(point)) + yoffset, Mzr260ztBe(17HRfe0qzY(point)) - (24239 ^ -15566 ^ 8844 ^ -16624));
            if (0df.canMoveToDiagonal(nntsO1ejZn(point), temp_pos, xjjAI5bt49(this))) {
               near = new 0dg(temp_pos);
               maMQwQkcyB(near, point);
               I2i1WrL9U1(near, pJoOJ4002v(point) + (3271 ^ -26896 ^ 5163 ^ -29155));
               list.add(near);
            }

            temp_pos = new Vector3i(9tAO7bSF4T(l1C27wyq4s(point)) + (21975 ^ -28186 ^ 2521 ^ -12823), n5rW0IJlFE(Etjpphl7mv(point)) + yoffset, uLfsgIIZgC(Vne8pjFJGv(point)) + (29824 ^ -22358 ^ 7517 ^ -16010));
            if (0df.canMoveToDiagonal(qntJVLOFAw(point), temp_pos, nzlj9kJlM8(this))) {
               near = new 0dg(temp_pos);
               NFq47BhSnQ(near, point);
               C5WZiGoiLn(near, JJLVnyr71w(point) + (32070 ^ -24213 ^ 1043 ^ -10177));
               list.add(near);
            }

            temp_pos = new Vector3i(QGZwGwvStM(OpqRjc4WTB(point)) - (2243 ^ -27448 ^ 16289 ^ -23637), 0GiLe0YI1j(rPYt0lGTVI(point)) + yoffset, 2FYr2uKaNd(vy7J791jSe(point)) + (10393 ^ -29851 ^ 1328 ^ -22835));
            if (0df.canMoveToDiagonal(EwJXW2uVvW(point), temp_pos, ZroNOwqwXX(this))) {
               near = new 0dg(temp_pos);
               uKR3mry4tl(near, point);
               ZnaV9gbPGL(near, SjRzq72Onj(point) + (18452 ^ -5441 ^ 3833 ^ -21421));
               list.add(near);
            }
         }

         return list;
      }
   }

   private static Vector3i NjAJDqQTnU(0dg var0) {
      return var0.pos;
   }

   private static void pAellorQiI(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static int yXtZIhwqB2(Vector3i var0) {
      return var0.y;
   }

   private static int s6GFRr9H0G(Vector3i var0) {
      return var0.y;
   }

   private static CopyOnWriteArrayList GotbObzqR2(0dc var0) {
      return var0.unchecked;
   }

   private static int _GiLe0YI1j/* $FF was: 0GiLe0YI1j*/(Vector3i var0) {
      return var0.y;
   }

   private static void wTwTZFcY1O(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static 0dg dzTwXHmX1d(0dg var0) {
      return var0.prev;
   }

   private static CopyOnWriteArrayList v2tNruuDOz(0dc var0) {
      return var0.unchecked;
   }

   private static void _oWn9dHnIq/* $FF was: 0oWn9dHnIq*/(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static int l2JQ7v6cNA(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i _7HRfe0qzY/* $FF was: 17HRfe0qzY*/(0dg var0) {
      return var0.pos;
   }

   private static CopyOnWriteArrayList YjgYStdawS(0dc var0) {
      return var0.unchecked;
   }

   private static void I2i1WrL9U1(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static Vector3i Sdr5I7eSAf(0dg var0) {
      return var0.pos;
   }

   public boolean isRunning() {
      return (boolean)(!in3HvAatQR(this) && LaKKfGyVlC(this) ? 1369 ^ -22483 ^ 16993 ^ -4332 : 9929 ^ -7933 ^ 6992 ^ -9062);
   }

   private static int wwnn1TifYD(0dg var0) {
      return var0.dist;
   }

   private static void nwHk2qcVZe(0dc var0, boolean var1) {
      var0.running = var1;
   }

   private static int tGnO6SNBwJ(0dg var0) {
      return var0.dist;
   }

   public void stop() {
      faFeD2fxbX(this, (boolean)(13231 ^ -4948 ^ 13707 ^ -5496));
   }

   private static int ShkThaGo23(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i EwJXW2uVvW(0dg var0) {
      return var0.pos;
   }

   private static int ygUKgeLuN7(Vector3i var0) {
      return var0.z;
   }

   private static CopyOnWriteArrayList _l01hwqSlZ/* $FF was: 0l01hwqSlZ*/(0dc var0) {
      return var0.unchecked;
   }

   private static int _FYr2uKaNd/* $FF was: 2FYr2uKaNd*/(Vector3i var0) {
      return var0.z;
   }

   private static void aVruQLgnba(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static int YNWcLvNieR(Vector3i var0) {
      return var0.z;
   }

   private static int _SmI1yvIVq/* $FF was: 4SmI1yvIVq*/(Vector3i var0) {
      return var0.z;
   }

   private static void faFeD2fxbX(0dc var0, boolean var1) {
      var0.running = var1;
   }

   private static Vector3i gotgvQygFl(0dg var0) {
      return var0.pos;
   }

   private static void _NWb9ew4nr/* $FF was: 6NWb9ew4nr*/(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static boolean in3HvAatQR(0dc var0) {
      return var0.done;
   }

   private static Vector3i bpuaD2L4O6(0dc var0) {
      return var0.to;
   }

   private static CopyOnWriteArrayList jRtQoRPe4d(0dc var0) {
      return var0.checked;
   }

   private static Vector3i vNQfP4ejex(0dg var0) {
      return var0.pos;
   }

   private static Vector3i ygwdjQPWlT(0dg var0) {
      return var0.pos;
   }

   private static CopyOnWriteArrayList pJwhaByA8h(0dc var0) {
      return var0.checked;
   }

   private static int qOLP7yJFOB(0dg var0) {
      return var0.dist;
   }

   private static void DnfTXb9xWl(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static CopyOnWriteArrayList l1KU4ofgXr(0dc var0) {
      return var0.checked;
   }

   private static Vector3i qntJVLOFAw(0dg var0) {
      return var0.pos;
   }

   private static Vector3i RDqIytcYwE(0dc var0) {
      return var0.to;
   }

   private static Vector3i kaxv1K2Nfl(0dg var0) {
      return var0.pos;
   }

   private static Vector3i jJjQ9EjUoY(0dg var0) {
      return var0.pos;
   }

   private static Vector3i y61rR28l5L(0dg var0) {
      return var0.pos;
   }

   private static int _tAO7bSF4T/* $FF was: 9tAO7bSF4T*/(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i OpqRjc4WTB(0dg var0) {
      return var0.pos;
   }

   private static void DVFJ4dN8Xc(0dc var0, boolean var1) {
      var0.done = var1;
   }

   private static Vector3i _aAhbOoNGX/* $FF was: 6aAhbOoNGX*/(0dg var0) {
      return var0.pos;
   }

   private static CopyOnWriteArrayList nJtqBfiwPz(0dc var0) {
      return var0.unchecked;
   }

   private static Vector3i jrHmxe9NYr(0dg var0) {
      return var0.pos;
   }

   private static Vector3i lb9VOkqlWq(0dg var0) {
      return var0.pos;
   }

   private static int rKwOHJ1ip6(Vector3i var0) {
      return var0.x;
   }

   private static CopyOnWriteArrayList iBTBbarMHv(0dc var0) {
      return var0.path;
   }

   private static Vector3i gDwbJnwwBJ(0dg var0) {
      return var0.pos;
   }

   private static int XoHPqKJdt4(Vector3i var0) {
      return var0.x;
   }

   private static int elqAl7uDnl(Vector3i var0) {
      return var0.z;
   }

   private static void C5WZiGoiLn(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static int FwXkyuJWzQ(Vector3i var0) {
      return var0.z;
   }

   private static CopyOnWriteArrayList ffw2aYIXQ4(0dc var0) {
      return var0.path;
   }

   private static Vector3i l1C27wyq4s(0dg var0) {
      return var0.pos;
   }

   private static int _ftbSeLZS1/* $FF was: 7ftbSeLZS1*/(Vector3i var0) {
      return var0.y;
   }

   public void scan() {
      DVFJ4dN8Xc(this, (boolean)(15653 ^ -5601 ^ 2598 ^ -8932));
      nwHk2qcVZe(this, (boolean)(29764 ^ -26560 ^ 17319 ^ -20574));
      nJtqBfiwPz(this).clear();
      BFzTvTJiaH(this).clear();
      0dg start = new 0dg(Gy7UnIzdBi(this));
      wvAYZAn8nE(start, 14169 ^ -26379 ^ 23831 ^ -3397);
      f8V7RG6CSa(this).add(start);
      int allowedLoops = 31908 ^ 105694 ^ 25331 ^ '芉';
      int loop = 3400 ^ -1776 ^ 24952 ^ -27360;
      0dg end = null;

      while(!0l01hwqSlZ(this).isEmpty() && this.isRunning()) {
         for(Iterator var5 = Z1njfKHlQu(this).iterator(); var5.hasNext(); ++loop) {
            0dg point = (0dg)var5.next();
            if (loop > allowedLoops) {
               yO07tJ8n0E(this).clear();
               break;
            }

            List<0dg> nearlist = this.getNear(point);
            GotbObzqR2(this).remove(point);
            jRtQoRPe4d(this).add(point);
            if (N7s17nCB3O(NjAJDqQTnU(point)) == XoHPqKJdt4(RDqIytcYwE(this)) && RHn9ktfatJ(Atuo7iF6Q3(point)) == O0QiJNOj4B(VcgfhnSsYG(this)) && elqAl7uDnl(ob9cWBtHpr(point)) == 2r0PVTh0wI(bpuaD2L4O6(this))) {
               v2tNruuDOz(this).clear();
               end = point;
               break;
            }

            Iterator var8 = nearlist.iterator();

            while(var8.hasNext()) {
               0dg near = (0dg)var8.next();
               if (!this.contains(04LQ1xeYym(this), near) && !this.contains(pJwhaByA8h(this), near)) {
                  XPSBknV4BN(this).add(near);
               }
            }
         }
      }

      VhL2IrkaqX(this).clear();
      if (end != null) {
         0dg Next = wcO7GkrGjB(end);
         iBTBbarMHv(this).add(end);

         while(Next != null) {
            8eIjh2mJ7d(this).add(Next);
            Next = dzTwXHmX1d(Next);
         }

         a7FW57jnVz(this, reverseList(ffw2aYIXQ4(this)));
         RdO1ITyyrr(this, (boolean)(12328 ^ -3349 ^ 16746 ^ -31832));
      }
   }

   private static int qSGQFw3WSd(Vector3i var0) {
      return var0.x;
   }

   private static void ZnaV9gbPGL(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static Vector3i tuWGaJLvYV(0dg var0) {
      return var0.pos;
   }

   private static World hGG0e0w221(0dc var0) {
      return var0.world;
   }

   private static void wvAYZAn8nE(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static Vector3i _BH92GDKlY/* $FF was: 7BH92GDKlY*/(0dg var0) {
      return var0.pos;
   }

   private static Vector3i tD2Wt1eRHc(0dg var0) {
      return var0.pos;
   }

   private static int OIUtNjXDdp(Vector3i var0) {
      return var0.y;
   }

   private static int g3gYPyo1NG(Vector3i var0) {
      return var0.z;
   }

   private static CopyOnWriteArrayList G1reAjDQ8d(0dc var0) {
      return var0.path;
   }

   private static int zRI1zeB4fS(Vector3i var0) {
      return var0.y;
   }

   private static int JJLVnyr71w(0dg var0) {
      return var0.dist;
   }

   private static Vector3i nntsO1ejZn(0dg var0) {
      return var0.pos;
   }

   private static Vector3i ibBelg1G2l(0dg var0) {
      return var0.pos;
   }

   private static int Pz3yrGwxKV(0dg var0) {
      return var0.dist;
   }

   private static int SjRzq72Onj(0dg var0) {
      return var0.dist;
   }

   private static Vector3i VcgfhnSsYG(0dc var0) {
      return var0.to;
   }

   private static int WbDD7YboJG(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i iCHg91TAPw(0dg var0) {
      return var0.pos;
   }

   private static int yjTFGEUSHU(Vector3i var0) {
      return var0.y;
   }

   public boolean onUpdate(EntityPlayer player) {
      return (boolean)(19828 ^ -32518 ^ 358 ^ -13080);
   }

   private static CopyOnWriteArrayList f8V7RG6CSa(0dc var0) {
      return var0.unchecked;
   }

   private static World xjjAI5bt49(0dc var0) {
      return var0.world;
   }

   private static World nzlj9kJlM8(0dc var0) {
      return var0.world;
   }

   public _dc/* $FF was: 0dc*/(Vector3i from, Vector3i to, World world) {
      this.path = new CopyOnWriteArrayList();
      this.from = from;
      this.to = to;
      this.world = world;
      this.done = (boolean)(25239 ^ -589 ^ 20235 ^ -12241);
   }

   private static Vector3i _H7rnnj8nU/* $FF was: 4H7rnnj8nU*/(0dg var0) {
      return var0.pos;
   }

   private static World nnDFttaJ0m(0dc var0) {
      return var0.world;
   }

   private static void Fg1l3UKVyr(0dg var0, int var1) {
      var0.dist = var1;
   }

   private static int qWjlf4yjPp(Vector3i var0) {
      return var0.y;
   }

   private static Vector3i Gy7UnIzdBi(0dc var0) {
      return var0.from;
   }

   private static CopyOnWriteArrayList XPSBknV4BN(0dc var0) {
      return var0.unchecked;
   }

   private static int n5rW0IJlFE(Vector3i var0) {
      return var0.y;
   }

   private static int VTwJOkuY2k(Vector3i var0) {
      return var0.x;
   }

   private static void uKR3mry4tl(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static Vector3i Vne8pjFJGv(0dg var0) {
      return var0.pos;
   }

   private static boolean LaKKfGyVlC(0dc var0) {
      return var0.running;
   }

   private static CopyOnWriteArrayList BFzTvTJiaH(0dc var0) {
      return var0.checked;
   }

   private static Vector3i b5cahCXuT1(0dg var0) {
      return var0.pos;
   }

   public List<0dg> getPathList() {
      return 63suh7dwSm(this);
   }

   private static int hlhYJSbcVz(Vector3i var0) {
      return var0.z;
   }

   private static Vector3i rPYt0lGTVI(0dg var0) {
      return var0.pos;
   }

   private static int QGZwGwvStM(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i QFaOVIy2l6(0dg var0) {
      return var0.pos;
   }

   private static void _2HQ36I7i2/* $FF was: 22HQ36I7i2*/(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static void RdO1ITyyrr(0dc var0, boolean var1) {
      var0.done = var1;
   }

   private static int kKkrDO52Mi(Vector3i var0) {
      return var0.z;
   }

   public static <T> CopyOnWriteArrayList<T> reverseList(List<T> list) {
      CopyOnWriteArrayList<T> reverse = new CopyOnWriteArrayList(list);
      Collections.reverse(reverse);
      return reverse;
   }

   private static void Dg5AF9FkGX(0dc var0, World var1) {
      var0.world = var1;
   }

   private static Vector3i vy7J791jSe(0dg var0) {
      return var0.pos;
   }

   private static int j5rdgju9SJ(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i qYv6w79nH6(0dg var0) {
      return var0.pos;
   }

   public void onFinished() {
      G1reAjDQ8d(this).clear();
      l1KU4ofgXr(this).clear();
      YjgYStdawS(this).clear();
      Dg5AF9FkGX(this, (World)null);
      this.stop();
   }

   private static Vector3i Etjpphl7mv(0dg var0) {
      return var0.pos;
   }

   private static Vector3i ob9cWBtHpr(0dg var0) {
      return var0.pos;
   }

   private static Vector3i g7ptovon41(0dg var0) {
      return var0.pos;
   }

   private static int GaB7iyJG4D(Vector3i var0) {
      return var0.x;
   }

   private static 0dg wcO7GkrGjB(0dg var0) {
      return var0.prev;
   }

   public boolean contains(CopyOnWriteArrayList<0dg> list, 0dg find) {
      Iterator var3 = list.iterator();

      0dg point;
      do {
         if (!var3.hasNext()) {
            return (boolean)(15596 ^ -12260 ^ 6303 ^ -2961);
         }

         point = (0dg)var3.next();
      } while(qSGQFw3WSd(iCHg91TAPw(point)) != GaB7iyJG4D(tuWGaJLvYV(find)) || 7ftbSeLZS1(rVQrV2TgYg(point)) != yXtZIhwqB2(b5cahCXuT1(find)) || ygUKgeLuN7(ibBelg1G2l(point)) != hlhYJSbcVz(4H7rnnj8nU(find)));

      return (boolean)(23581 ^ -24090 ^ 27152 ^ -26646);
   }

   private static void maMQwQkcyB(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static Vector3i rVQrV2TgYg(0dg var0) {
      return var0.pos;
   }

   private static Vector3i e41jFzg9LO(0dg var0) {
      return var0.pos;
   }

   private static CopyOnWriteArrayList VhL2IrkaqX(0dc var0) {
      return var0.path;
   }

   private static int zGqWrnddmd(0dg var0) {
      return var0.dist;
   }

   private static CopyOnWriteArrayList yO07tJ8n0E(0dc var0) {
      return var0.unchecked;
   }

   private static void NFq47BhSnQ(0dg var0, 0dg var1) {
      var0.prev = var1;
   }

   private static CopyOnWriteArrayList _eIjh2mJ7d/* $FF was: 8eIjh2mJ7d*/(0dc var0) {
      return var0.path;
   }

   private static void a7FW57jnVz(0dc var0, CopyOnWriteArrayList var1) {
      var0.path = var1;
   }

   private static World DpKXNFF8nB(0dc var0) {
      return var0.world;
   }

   private static Vector3i Atuo7iF6Q3(0dg var0) {
      return var0.pos;
   }

   private static Vector3i C06E0eTvgQ(0dg var0) {
      return var0.pos;
   }

   private static int O0QiJNOj4B(Vector3i var0) {
      return var0.y;
   }

   private static int Mzr260ztBe(Vector3i var0) {
      return var0.z;
   }

   private static int pJoOJ4002v(0dg var0) {
      return var0.dist;
   }

   private static int _B9oX9y9Ec/* $FF was: 6B9oX9y9Ec*/(Vector3i var0) {
      return var0.y;
   }

   private static int uLfsgIIZgC(Vector3i var0) {
      return var0.z;
   }

   private static World ZroNOwqwXX(0dc var0) {
      return var0.world;
   }

   private static int N7s17nCB3O(Vector3i var0) {
      return var0.x;
   }

   private static CopyOnWriteArrayList Z1njfKHlQu(0dc var0) {
      return var0.unchecked;
   }

   private static int PXVnnqFTnY(Vector3i var0) {
      return var0.x;
   }

   private static int Yw2HaDgUOd(0dg var0) {
      return var0.dist;
   }

   private static World oB4eLi77qY(0dc var0) {
      return var0.world;
   }
}
