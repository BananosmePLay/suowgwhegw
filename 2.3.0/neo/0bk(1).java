package neo;

import java.net.InetAddress;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class 0bk {
   public final 0a bot;
   public 0bi networkmanager;
   private static int _DSC GG NEOWARECLIENT _;

   private static 0bi sSJM9yabUO(0bk var0) {
      return var0.networkmanager;
   }

   private static 0a V7BJezg4Rd(0bk var0) {
      return var0.bot;
   }

   private static 0a U7O5PBruuc(0bk var0) {
      return var0.bot;
   }

   private static void vDOzXt9u7Y(0bk var0, 0bi var1) {
      var0.networkmanager = var1;
   }

   private static 0bi TVjEalBeeH(0bk var0) {
      return var0.networkmanager;
   }

   // $FF: synthetic method
   static 0a access$100(0bk a) {
      return ZrdM4OLZAx(a);
   }

   public void ping() {
      try {
         vDOzXt9u7Y(this, 0bi.createNetworkManagerAndConnect(J9llcTnDLc(this), InetAddress.getByName(9tobw6Byx6(this).getHost()), U7O5PBruuc(this).getPort()));
         sSJM9yabUO(this).setNetHandler(new 0bj(this));
         TVjEalBeeH(this).sendPacket(new RD(vdaN1meLZN(this).getHost(), 629WCsR13R(this).getPort(), A7FbN6OiYb()));
         9Ga2r9DGpD(this).sendPacket(new Vw());
      } catch (Exception var2) {
         Exception a = var2;
         a.printStackTrace();
         this.connect();
      }

   }

   private static 0a _tobw6Byx6/* $FF was: 9tobw6Byx6*/(0bk var0) {
      return var0.bot;
   }

   private static 0a _29WCsR13R/* $FF was: 629WCsR13R*/(0bk var0) {
      return var0.bot;
   }

   private static RB A7FbN6OiYb() {
      return RB.STATUS;
   }

   private static 0bi eyjbobBxR2(0bk var0) {
      return var0.networkmanager;
   }

   private void connect() {
      0eh.method_bFu(12581 ^ -32732 ^ 2639 ^ -17250);
      V7BJezg4Rd(this).connect();
   }

   private static 0bi _cv86QmKpn/* $FF was: 2cv86QmKpn*/(0bk var0) {
      return var0.networkmanager;
   }

   // $FF: synthetic method
   static void access$200(0bk a) {
      a.connect();
   }

   // $FF: synthetic method
   static 0bi access$000(0bk a) {
      return 2cv86QmKpn(a);
   }

   private static 0a vdaN1meLZN(0bk var0) {
      return var0.bot;
   }

   public void pingPendingNetworks() {
      if (L3r5gstqHm(this) != null) {
         eyjbobBxR2(this).processReceivedPackets();
      }

   }

   public _bk/* $FF was: 0bk*/(0a a) {
      this.bot = a;
   }

   private static 0bi _Ga2r9DGpD/* $FF was: 9Ga2r9DGpD*/(0bk var0) {
      return var0.networkmanager;
   }

   private static 0a J9llcTnDLc(0bk var0) {
      return var0.bot;
   }

   private static 0a ZrdM4OLZAx(0bk var0) {
      return var0.bot;
   }

   private static 0bi L3r5gstqHm(0bk var0) {
      return var0.networkmanager;
   }
}
