package net.minecraft.util.datafix.fixes;

import java.util.Locale;
import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class OptionsLowerCaseLanguage implements IFixableData {
   public OptionsLowerCaseLanguage() {
   }

   public int getFixVersion() {
      return 816;
   }

   public QQ fixTagCompound(QQ compound) {
      if (compound.hasKey("lang", 8)) {
         compound.setString("lang", compound.getString("lang").toLowerCase(Locale.ROOT));
      }

      return compound;
   }
}
