package neo;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class 0cL extends ArrayList<0dq> implements 0cE<0dq> {
   private static String _ _;

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_bxP((0dq)var1);
   }

   public void init() {
      0bz.method_Qm().method_Qn().register(this);

      try {
         if (!0ee.fileExists(0ed.method_bFf(method_bxU("ХфѯѥѝѫѸѯХѧѫѩѸѥФѠѹѥѤ")))) {
            return;
         }

         JSONArray c = new JSONArray(0ee.readFile(0ed.method_bFf(method_bxU("ХфѯѥѝѫѸѯХѧѫѩѸѥФѠѹѥѤ"))));

         for(int b = 25078 ^ -11572 ^ 10149 ^ -27489; b < c.length(); ++b) {
            JSONObject a = c.getJSONObject(b);
            this.method_bxP(new 0dq(a.getString(method_bxU("Ѥѫѧѯ")), a.getInt(method_bxU("ѡѯѳ")), a.getString(method_bxU("ѩѥѧѧѫѤѮ"))));
         }
      } catch (Exception var4) {
         Exception d = var4;
         d.printStackTrace();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bxU(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7583 ^ -19922 ^ 23352 ^ -2935; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2352 ^ -30465 ^ 29246 ^ -2053));
      }

      return var1.toString();
   }

   @Subscribe
   private void method_bxR(0dd b) {
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         0dq a = (0dq)var2.next();
         if (a.method_bAI() == (-18227 ^ -10976 ^ 22532 ^ -13802)) {
            a.method_bAK(b.method_bzX());
            0ek.addMessage(0cT.method_byX(method_bxU("ѩѥѧѧѫѤѮФѧѫѩѸѥФѩѸѯѫѾѯФѣѤѬѥ")));
            this.method_bxO();
         } else if (a.method_bAI() == b.method_bzX() && method_bxV() != null) {
            method_bxW().sendChatMessage(a.method_bAJ());
         }
      }

   }

   private static jh method_bxW() {
      return nC.player;
   }

   public boolean method_bxQ(String a) {
      return this.stream().anyMatch((b) -> {
         return b.method_bAH().equalsIgnoreCase(a);
      });
   }

   public _cL/* $FF was: 0cL*/() {
      this.init();
   }

   public void method_bxO() {
      try {
         JSONArray a = new JSONArray();
         this.forEach((bx) -> {
            a.put((Object)bx.method_bAL());
         });
         0ee.saveFile(0ed.method_bFf(method_bxU("ХфѯѥѝѫѸѯХѧѫѩѸѥФѠѹѥѤ")), a.toString());
      } catch (Exception var2) {
         Exception b = var2;
         b.printStackTrace();
      }

   }

   public void method_bxP(0dq a) {
      this.add(a);
   }

   private static jh method_bxV() {
      return nC.player;
   }
}
