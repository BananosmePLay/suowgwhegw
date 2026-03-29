package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class h implements Comparable<h> {
   private final Map<String, ca> criteria = Maps.newHashMap();
   private String[][] requirements = new String[0][];

   public h() {
   }

   public void update(Map<String, bZ> criteriaIn, String[][] requirements) {
      Set<String> set = criteriaIn.keySet();
      Iterator<Map.Entry<String, ca>> iterator = this.criteria.entrySet().iterator();

      while(iterator.hasNext()) {
         Map.Entry<String, ca> entry = (Map.Entry)iterator.next();
         if (!set.contains(entry.getKey())) {
            iterator.remove();
         }
      }

      Iterator var7 = set.iterator();

      while(var7.hasNext()) {
         String s = (String)var7.next();
         if (!this.criteria.containsKey(s)) {
            this.criteria.put(s, new ca(this));
         }
      }

      this.requirements = requirements;
   }

   public boolean isDone() {
      if (this.requirements.length == 0) {
         return false;
      } else {
         String[][] var1 = this.requirements;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String[] astring = var1[var3];
            boolean flag = false;
            String[] var6 = astring;
            int var7 = astring.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String s = var6[var8];
               ca criterionprogress = this.getCriterionProgress(s);
               if (criterionprogress != null && criterionprogress.isObtained()) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean hasProgress() {
      Iterator var1 = this.criteria.values().iterator();

      ca criterionprogress;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         criterionprogress = (ca)var1.next();
      } while(!criterionprogress.isObtained());

      return true;
   }

   public boolean grantCriterion(String criterionIn) {
      ca criterionprogress = (ca)this.criteria.get(criterionIn);
      if (criterionprogress != null && !criterionprogress.isObtained()) {
         criterionprogress.obtain();
         return true;
      } else {
         return false;
      }
   }

   public boolean revokeCriterion(String criterionIn) {
      ca criterionprogress = (ca)this.criteria.get(criterionIn);
      if (criterionprogress != null && criterionprogress.isObtained()) {
         criterionprogress.reset();
         return true;
      } else {
         return false;
      }
   }

   public String toString() {
      return "AdvancementProgress{criteria=" + this.criteria + ", requirements=" + Arrays.deepToString(this.requirements) + '}';
   }

   public void serializeToNetwork(SA p_192104_1_) {
      p_192104_1_.writeVarInt(this.criteria.size());
      Iterator var2 = this.criteria.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, ca> entry = (Map.Entry)var2.next();
         p_192104_1_.writeString((String)entry.getKey());
         ((ca)entry.getValue()).write(p_192104_1_);
      }

   }

   public static h fromNetwork(SA p_192100_0_) {
      h advancementprogress = new h();
      int i = p_192100_0_.readVarInt();

      for(int j = 0; j < i; ++j) {
         advancementprogress.criteria.put(p_192100_0_.readString(32767), ca.read(p_192100_0_, advancementprogress));
      }

      return advancementprogress;
   }

   @Nullable
   public ca getCriterionProgress(String criterionIn) {
      return (ca)this.criteria.get(criterionIn);
   }

   public float getPercent() {
      if (this.criteria.isEmpty()) {
         return 0.0F;
      } else {
         float f = (float)this.requirements.length;
         float f1 = (float)this.countCompletedRequirements();
         return f1 / f;
      }
   }

   @Nullable
   public String getProgressText() {
      if (this.criteria.isEmpty()) {
         return null;
      } else {
         int i = this.requirements.length;
         if (i <= 1) {
            return null;
         } else {
            int j = this.countCompletedRequirements();
            return j + "/" + i;
         }
      }
   }

   private int countCompletedRequirements() {
      int i = 0;
      String[][] var2 = this.requirements;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String[] astring = var2[var4];
         boolean flag = false;
         String[] var7 = astring;
         int var8 = astring.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            String s = var7[var9];
            ca criterionprogress = this.getCriterionProgress(s);
            if (criterionprogress != null && criterionprogress.isObtained()) {
               flag = true;
               break;
            }
         }

         if (flag) {
            ++i;
         }
      }

      return i;
   }

   public Iterable<String> getRemaningCriteria() {
      List<String> list = Lists.newArrayList();
      Iterator var2 = this.criteria.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, ca> entry = (Map.Entry)var2.next();
         if (!((ca)entry.getValue()).isObtained()) {
            list.add(entry.getKey());
         }
      }

      return list;
   }

   public Iterable<String> getCompletedCriteria() {
      List<String> list = Lists.newArrayList();
      Iterator var2 = this.criteria.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, ca> entry = (Map.Entry)var2.next();
         if (((ca)entry.getValue()).isObtained()) {
            list.add(entry.getKey());
         }
      }

      return list;
   }

   @Nullable
   public Date getFirstProgressDate() {
      Date date = null;
      Iterator var2 = this.criteria.values().iterator();

      while(true) {
         ca criterionprogress;
         do {
            do {
               if (!var2.hasNext()) {
                  return date;
               }

               criterionprogress = (ca)var2.next();
            } while(!criterionprogress.isObtained());
         } while(date != null && !criterionprogress.getObtained().before(date));

         date = criterionprogress.getObtained();
      }
   }

   public int compareTo(h p_compareTo_1_) {
      Date date = this.getFirstProgressDate();
      Date date1 = p_compareTo_1_.getFirstProgressDate();
      if (date == null && date1 != null) {
         return 1;
      } else if (date != null && date1 == null) {
         return -1;
      } else {
         return date == null && date1 == null ? 0 : date.compareTo(date1);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((h)var1);
   }

   // $FF: synthetic method
   static Map access$000(h x0) {
      return x0.criteria;
   }
}
