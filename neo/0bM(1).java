package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class 0bM {
   public final ArrayList<0b> commands = new ArrayList();

   private static ArrayList IJJNVboI4w(0bM var0) {
      return var0.commands;
   }

   public List<0b> getCommands() {
      return JtlhfOrvW6(this);
   }

   public void execute(String args) {
      args = args.substring(8279 ^ -12455 ^ 3430 ^ -7575);
      String[] arguments = args.split(7cglcO4n5y("ފ"));
      Iterator var3 = IJJNVboI4w(this).iterator();

      0b cmd;
      do {
         if (!var3.hasNext()) {
            0dK.formatMsg(7cglcO4n5y("ΰΔΖΚΗΞΚފΗΟފΗΚΓΞΟΗΚދ"));
            return;
         }

         cmd = (0b)var3.next();
      } while(!IDDrF22xjI(cmd).trim().equalsIgnoreCase(arguments[19053 ^ -985 ^ 9455 ^ -27995].trim()));

      String[] argss = (String[])Arrays.copyOfRange(arguments, 28815 ^ -18376 ^ 6120 ^ -8354, arguments.length);

      try {
         cmd.execute(argss);
      } catch (Exception var7) {
         Exception e = var7;
         e.printStackTrace();
         cmd.error();
      }

   }

   private static ArrayList JtlhfOrvW6(0bM var0) {
      return var0.commands;
   }

   public _bM/* $FF was: 0bM*/() {
      0m.register(new 0c(this));
      this.commands.add(new 0h());
      this.commands.add(new 0f());
      this.commands.add(new 0g());
      this.commands.add(new 0j());
      this.commands.add(new 0e());
      this.commands.add(new 0d());
      this.commands.add(new 0i());
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _cglcO4n5y/* $FF was: 7cglcO4n5y*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10229 ^ -26849 ^ 15549 ^ -29609; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 32640 ^ -31449 ^ 7113 ^ -6460));
      }

      return var1.toString();
   }

   private static String IDDrF22xjI(0b var0) {
      return var0.name;
   }
}
