package neo;

import com.google.common.eventbus.EventBus;
import de.florianmichael.viamcp.ViaMCP;
import org.lwjgl.opengl.Display;

public class 0bz {
   public 0cG botManager;
   public EventBus field_g;
   public 0cI field_a;
   public 0cS themeManager;
   public 0cL field_j;
   public 0cM field_f;
   public 0cO field_c;
   public 0cU field_b;
   public 0co field_e;
   public 0cK field_i;
   public 0cJ field_h;
   public static 0bz field_d;
   private static String _DSC GG NEOWARECLIENT _;

   private static 0cL method_QV(0bz var0) {
      return var0.field_j;
   }

   public _bz/* $FF was: 0bz*/() {
   }

   private static 0cM method_QR(0bz var0) {
      return var0.field_f;
   }

   private static void method_QH(0bz var0, 0cL var1) {
      var0.field_j = var1;
   }

   public 0cL method_Qw() {
      return method_QV(this);
   }

   private static 0cG method_QU(0bz var0) {
      return var0.botManager;
   }

   public static 0bz method_Qm() {
      return method_QL();
   }

   private static void method_QC(0bz var0, 0cO var1) {
      var0.field_c = var1;
   }

   public 0co method_Qq() {
      return method_QP(this);
   }

   private static 0bz method_QL() {
      return field_d;
   }

   private static 0cK method_QT(0bz var0) {
      return var0.field_i;
   }

   public 0cU method_Qo() {
      return method_QN(this);
   }

   public 0cJ method_Qr() {
      return method_QQ(this);
   }

   private static void method_QB(0bz var0, 0cU var1) {
      var0.field_b = var1;
   }

   private static void method_Qz(0bz var0, EventBus var1) {
      var0.field_g = var1;
   }

   private static void method_QA(0bz var0, 0cI var1) {
      var0.field_a = var1;
   }

   private static 0co method_QP(0bz var0) {
      return var0.field_e;
   }

   private static void method_Qy(0bz var0) {
      field_d = var0;
   }

   public 0cI method_Ql() {
      return method_QK(this);
   }

   private static 0cS method_QS(0bz var0) {
      return var0.themeManager;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Qx(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10058 ^ -63 ^ 7284 ^ -15105; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 26630 ^ -8821 ^ 24813 ^ -10555));
      }

      return var1.toString();
   }

   public 0cS method_Qt() {
      return method_QS(this);
   }

   private static void method_QE(0bz var0, 0cJ var1) {
      var0.field_h = var1;
   }

   public 0cO method_Qp() {
      return method_QO(this);
   }

   private static 0cJ method_QQ(0bz var0) {
      return var0.field_h;
   }

   public EventBus method_Qn() {
      return method_QM(this);
   }

   private static void method_QF(0bz var0, 0cM var1) {
      var0.field_f = var1;
   }

   public 0cK method_Qu() {
      return method_QT(this);
   }

   public 0cG method_Qv() {
      return method_QU(this);
   }

   private static void method_QI(0bz var0, 0cG var1) {
      var0.botManager = var1;
   }

   private static 0cI method_QK(0bz var0) {
      return var0.field_a;
   }

   private static 0cO method_QO(0bz var0) {
      return var0.field_c;
   }

   private static 0cU method_QN(0bz var0) {
      return var0.field_b;
   }

   public void method_Qk() {
      method_Qy(this);
      method_Qz(this, new EventBus());
      method_QA(this, new 0cI());
      method_QB(this, new 0cU());
      method_QC(this, new 0cO());
      Display.setTitle(method_Qx("ϫπϊϲτϗπ΅ϓ") + 0dH.method_bDy());
      0cA.method_bvS(32706 ^ -32018 ^ 27579 ^ -26987);
      method_QD(this, new 0co());
      method_QE(this, new 0cJ());
      method_QF(this, new 0cM());
      0cA.method_bvS(998 ^ -7980 ^ 28503 ^ -29594);
      method_QG(this, new 0cS());
      method_QH(this, new 0cL());
      method_QI(this, new 0cG());
      method_QJ(this, new 0cK());
      0cA.method_bvS(2246 ^ -30771 ^ 10313 ^ -22714);
      0ej.method_bFv();
      ViaMCP.create();
      0Z.method_kX();
      0cf.method_YA();
      0cz.method_btQ();
      0cA.method_bvS(26796 ^ -16963 ^ 157 ^ -10871);
   }

   private static void method_QJ(0bz var0, 0cK var1) {
      var0.field_i = var1;
   }

   private static void method_QD(0bz var0, 0co var1) {
      var0.field_e = var1;
   }

   private static void method_QG(0bz var0, 0cS var1) {
      var0.themeManager = var1;
   }

   public 0cM method_Qs() {
      return method_QR(this);
   }

   private static EventBus method_QM(0bz var0) {
      return var0.field_g;
   }
}
