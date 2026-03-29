package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;

public class bp {
   public static final bp ANY = new bp((QQ)null);
   @Nullable
   private final QQ tag;

   public bp(@Nullable QQ tag) {
      this.tag = tag;
   }

   public boolean test(Qy item) {
      return this == ANY ? true : this.test((QH)item.getTagCompound());
   }

   public boolean test(Ig entityIn) {
      return this == ANY ? true : this.test((QH)Ch.entityToNBT(entityIn));
   }

   public boolean test(@Nullable QH nbt) {
      if (nbt == null) {
         return this == ANY;
      } else {
         return this.tag == null || Rb.areNBTEquals(this.tag, nbt, true);
      }
   }

   public static bp deserialize(@Nullable JsonElement json) {
      if (json != null && !json.isJsonNull()) {
         QQ nbttagcompound;
         try {
            nbttagcompound = QG.getTagFromJson(JsonUtils.getString(json, "nbt"));
         } catch (QI var3) {
            QI nbtexception = var3;
            throw new JsonSyntaxException("Invalid nbt tag: " + nbtexception.getMessage());
         }

         return new bp(nbttagcompound);
      } else {
         return ANY;
      }
   }
}
