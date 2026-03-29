package neo;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.JsonUtils;

public class sB {
   @VisibleForTesting
   static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(sB.class, new sz()).registerTypeAdapter(te.class, new td()).registerTypeAdapter(tg.class, new tf()).registerTypeAdapter(sS.class, new sR()).registerTypeAdapter(sX.class, new sW()).create();
   private final Map<String, tg> mapVariants = Maps.newHashMap();
   private sS multipart;

   public static sB parseFromReader(Reader reader) {
      return (sB)JsonUtils.fromJson(GSON, reader, sB.class);
   }

   public sB(Map<String, tg> variants, sS multipartIn) {
      this.multipart = multipartIn;
      this.mapVariants.putAll(variants);
   }

   public sB(List<sB> p_i46222_1_) {
      sB modelblockdefinition = null;

      sB modelblockdefinition1;
      for(Iterator var3 = p_i46222_1_.iterator(); var3.hasNext(); this.mapVariants.putAll(modelblockdefinition1.mapVariants)) {
         modelblockdefinition1 = (sB)var3.next();
         if (modelblockdefinition1.hasMultipartData()) {
            this.mapVariants.clear();
            modelblockdefinition = modelblockdefinition1;
         }
      }

      if (modelblockdefinition != null) {
         this.multipart = modelblockdefinition.multipart;
      }

   }

   public boolean hasVariant(String p_188000_1_) {
      return this.mapVariants.get(p_188000_1_) != null;
   }

   public tg getVariant(String p_188004_1_) {
      tg variantlist = (tg)this.mapVariants.get(p_188004_1_);
      if (variantlist == null) {
         throw new sA(this);
      } else {
         return variantlist;
      }
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         if (p_equals_1_ instanceof sB) {
            sB modelblockdefinition = (sB)p_equals_1_;
            if (this.mapVariants.equals(modelblockdefinition.mapVariants)) {
               return this.hasMultipartData() ? this.multipart.equals(modelblockdefinition.multipart) : !modelblockdefinition.hasMultipartData();
            }
         }

         return false;
      }
   }

   public int hashCode() {
      return 31 * this.mapVariants.hashCode() + (this.hasMultipartData() ? this.multipart.hashCode() : 0);
   }

   public Set<tg> getMultipartVariants() {
      Set<tg> set = Sets.newHashSet(this.mapVariants.values());
      if (this.hasMultipartData()) {
         set.addAll(this.multipart.getVariants());
      }

      return set;
   }

   public boolean hasMultipartData() {
      return this.multipart != null;
   }

   public sS getMultipartData() {
      return this.multipart;
   }
}
