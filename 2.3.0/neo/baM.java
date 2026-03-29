package neo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum baM {
   OVERWORLD(0, "overworld", "", bip.class),
   NETHER(-1, "the_nether", "_nether", bio.class),
   THE_END(1, "the_end", "_end", bim.class);

   private final int id;
   private final String name;
   private final String suffix;
   private final Class<? extends bil> clazz;

   private baM(int idIn, String nameIn, String suffixIn, Class clazzIn) {
      this.id = idIn;
      this.name = nameIn;
      this.suffix = suffixIn;
      this.clazz = clazzIn;
   }

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getSuffix() {
      return this.suffix;
   }

   public bil createDimension() {
      try {
         Constructor<? extends bil> constructor = this.clazz.getConstructor();
         return (bil)constructor.newInstance();
      } catch (NoSuchMethodException var2) {
         NoSuchMethodException nosuchmethodexception = var2;
         throw new Error("Could not create new dimension", nosuchmethodexception);
      } catch (InvocationTargetException var3) {
         InvocationTargetException invocationtargetexception = var3;
         throw new Error("Could not create new dimension", invocationtargetexception);
      } catch (InstantiationException var4) {
         InstantiationException instantiationexception = var4;
         throw new Error("Could not create new dimension", instantiationexception);
      } catch (IllegalAccessException var5) {
         IllegalAccessException illegalaccessexception = var5;
         throw new Error("Could not create new dimension", illegalaccessexception);
      }
   }

   public static baM getById(int id) {
      baM[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         baM dimensiontype = var1[var3];
         if (dimensiontype.getId() == id) {
            return dimensiontype;
         }
      }

      throw new IllegalArgumentException("Invalid dimension id " + id);
   }

   public static baM byName(String nameIn) {
      baM[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         baM dimensiontype = var1[var3];
         if (dimensiontype.getName().equals(nameIn)) {
            return dimensiontype;
         }
      }

      throw new IllegalArgumentException("Invalid dimension " + nameIn);
   }
}
