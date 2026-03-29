package neo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class 0be extends 0bi {
   // $FF: synthetic method
   // $FF: bridge method
   private static String QhbNf5K3ev(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 24762 ^ -2857 ^ 4281 ^ -31532; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21555 ^ -15800 ^ 22667 ^ -13925));
      }

      return var1.toString();
   }

   private static ArrayList _uzFAyEbBd/* $FF was: 3uzFAyEbBd*/() {
      return 0bd.registry;
   }

   private static ArrayList z9abQLGJnI() {
      return 0bd.registry;
   }

   public void loadFile() throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getFile()));

      String line;
      while((line = bufferedReader.readLine()) != null) {
         String[] arguments = line.split(QhbNf5K3ev("ݑ"));
         if (arguments.length > (12300 ^ -20193 ^ 29204 ^ -3323)) {
            3uzFAyEbBd().add(new 0bb(arguments[8036 ^ -22886 ^ 9568 ^ -25442], arguments[23737 ^ -20187 ^ 4820 ^ -183], arguments[15572 ^ -32346 ^ 19320 ^ -2552], arguments.length > (21149 ^ -20478 ^ 19351 ^ -22261) ? 0ba.valueOf(arguments[14813 ^ -2414 ^ 9782 ^ -5766]) : JPDihICa1v()));
         } else {
            Gg1NPNOjcR().add(new 0bb(arguments[11149 ^ -356 ^ 1113 ^ -11960], arguments[14219 ^ -20789 ^ 9558 ^ -17385]));
         }
      }

      bufferedReader.close();
   }

   private static 0ba JPDihICa1v() {
      return 0ba.Unchecked;
   }

   private static ArrayList Gg1NPNOjcR() {
      return 0bd.registry;
   }

   public void saveFile() throws IOException {
      PrintWriter alts = new PrintWriter(new FileWriter(this.getFile()));
      Iterator var2 = z9abQLGJnI().iterator();

      while(var2.hasNext()) {
         0bb alt = (0bb)var2.next();
         if (alt.getMask().equals(QhbNf5K3ev(""))) {
            alts.println(alt.getUsername() + QhbNf5K3ev("ݑ") + alt.getPassword() + QhbNf5K3ev("ݑ") + alt.getUsername() + QhbNf5K3ev("ݑ") + alt.getStatus());
         } else {
            alts.println(alt.getUsername() + QhbNf5K3ev("ݑ") + alt.getPassword() + QhbNf5K3ev("ݑ") + alt.getMask() + QhbNf5K3ev("ݑ") + alt.getStatus());
         }
      }

      alts.close();
   }

   public _be/* $FF was: 0be*/(String name, boolean loadOnStart) {
      super(name, loadOnStart);
   }
}
