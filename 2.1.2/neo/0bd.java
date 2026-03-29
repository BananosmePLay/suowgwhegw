package neo;

import java.util.ArrayList;

public class 0bd {
   public static ArrayList<0bb> registry = new ArrayList();
   public static 0bb lastAlt;

   private static ArrayList nZFvFSVTHb() {
      return registry;
   }

   public _bd/* $FF was: 0bd*/() {
   }

   public static void removeAccount(0bb alt) {
      4wRITNnIng().remove(alt);

      try {
         6ftZtOvHYF(0bK.getInstance()).saveFiles();
      } catch (Exception var2) {
         Exception e = var2;
         e.printStackTrace();
      }

   }

   public void setLastAlt(0bb alt) {
      4aYif6hga1(alt);
   }

   private static 0bj EqdQY6Ba4L(0bK var0) {
      return var0.fileManager;
   }

   public ArrayList<0bb> getRegistry() {
      return nZFvFSVTHb();
   }

   private static ArrayList _wRITNnIng/* $FF was: 4wRITNnIng*/() {
      return registry;
   }

   private static 0bj _ftZtOvHYF/* $FF was: 6ftZtOvHYF*/(0bK var0) {
      return var0.fileManager;
   }

   private static ArrayList i9Dv9iSVDo() {
      return registry;
   }

   private static 0bj qR1n3qu7OG(0bK var0) {
      return var0.fileManager;
   }

   private static void _aYif6hga1/* $FF was: 4aYif6hga1*/(0bb var0) {
      lastAlt = var0;
   }

   public static void clearAccounts() {
      0aNTHVbnsB().clear();

      try {
         qR1n3qu7OG(0bK.getInstance()).saveFiles();
      } catch (Exception var1) {
         Exception e = var1;
         e.printStackTrace();
      }

   }

   public static void addAccount(0bb alt) {
      i9Dv9iSVDo().add(alt);

      try {
         EqdQY6Ba4L(0bK.getInstance()).saveFiles();
      } catch (Exception var2) {
         Exception e = var2;
         e.printStackTrace();
      }

   }

   private static ArrayList _aNTHVbnsB/* $FF was: 0aNTHVbnsB*/() {
      return registry;
   }
}
