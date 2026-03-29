package neo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class bnC implements bnH {
   public bnC() {
   }

   public Field getField() {
      Class oclass = nC.class;
      Field field = this.getFieldRenderChunksMany();
      if (field == null) {
         XH.log("(Reflector) Field not present: " + oclass.getName() + ".actionKeyF3 (field renderChunksMany not found)");
         return null;
      } else {
         Field field1 = bnS.getFieldAfter(nC.class, field, Boolean.TYPE, 0);
         if (field1 == null) {
            XH.log("(Reflector) Field not present: " + oclass.getName() + ".actionKeyF3");
            return null;
         } else {
            return field1;
         }
      }
   }

   private Field getFieldRenderChunksMany() {
      nC minecraft = nC.getMinecraft();
      boolean flag = minecraft.renderChunksMany;
      Field[] afield = nC.class.getDeclaredFields();
      minecraft.renderChunksMany = true;
      Field[] afield1 = bnS.getFields(minecraft, afield, Boolean.TYPE, Boolean.TRUE);
      minecraft.renderChunksMany = false;
      Field[] afield2 = bnS.getFields(minecraft, afield, Boolean.TYPE, Boolean.FALSE);
      minecraft.renderChunksMany = flag;
      Set<Field> set = new HashSet(Arrays.asList(afield1));
      Set<Field> set1 = new HashSet(Arrays.asList(afield2));
      Set<Field> set2 = new HashSet(set);
      set2.retainAll(set1);
      Field[] afield3 = (Field[])((Field[])set2.toArray(new Field[set2.size()]));
      return afield3.length != 1 ? null : afield3[0];
   }
}
