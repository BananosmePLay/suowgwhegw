package neo;

import java.lang.reflect.Field;

public class 0bf {
   private String className;
   public Class<?> clazz;

   public void setStaticField(String a2, Object v1) throws NoSuchFieldException, IllegalAccessException {
      Field v2 = 9zFFvdKzWw(this).getDeclaredField(a2);
      v2.setAccessible((boolean)(17123 ^ -11743 ^ 14255 ^ -22676));
      Field v3 = Field.class.getDeclaredField(nSdN5G0HwM("ΛΙΒΟΐΟΓ΄΅"));
      v3.setAccessible((boolean)(22912 ^ -12033 ^ 13069 ^ -17805));
      v3.setInt(v2, v2.getModifiers() & (-7942 ^ -16481 ^ 15917 ^ -24921));
      v2.set((Object)null, v1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String nSdN5G0HwM(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 575 ^ -19864 ^ 9356 ^ -27429; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24920 ^ -19920 ^ 11954 ^ -468));
      }

      return var1.toString();
   }

   private static Class _zFFvdKzWw/* $FF was: 9zFFvdKzWw*/(0bf var0) {
      return var0.clazz;
   }

   public _bf/* $FF was: 0bf*/(String v1) {
      try {
         this.clazz = Class.forName(v1);
      } catch (ClassNotFoundException var3) {
         ClassNotFoundException v2 = var3;
         v2.printStackTrace();
      }

   }
}
