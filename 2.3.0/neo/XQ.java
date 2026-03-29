package neo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class XQ {
   public final String statId;
   private final ITextComponent statName;
   public boolean isIndependent;
   private final XI formatter;
   private final Wo objectiveCriteria;
   private Class<? extends IJsonSerializable> serializableClazz;
   private static final NumberFormat numberFormat;
   public static XI simpleStatType;
   private static final DecimalFormat decimalFormat;
   public static XI timeStatType;
   public static XI distanceStatType;
   public static XI divideByTen;

   public XQ(String statIdIn, ITextComponent statNameIn, XI formatterIn) {
      this.statId = statIdIn;
      this.statName = statNameIn;
      this.formatter = formatterIn;
      this.objectiveCriteria = new Wy(this);
      Wo.INSTANCES.put(this.objectiveCriteria.getName(), this.objectiveCriteria);
   }

   public XQ(String statIdIn, ITextComponent statNameIn) {
      this(statIdIn, statNameIn, simpleStatType);
   }

   public XQ initIndependentStat() {
      this.isIndependent = true;
      return this;
   }

   public XQ registerStat() {
      if (XV.ID_TO_STAT_MAP.containsKey(this.statId)) {
         throw new RuntimeException("Duplicate stat id: \"" + ((XQ)XV.ID_TO_STAT_MAP.get(this.statId)).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
      } else {
         XV.ALL_STATS.add(this);
         XV.ID_TO_STAT_MAP.put(this.statId, this);
         return this;
      }
   }

   public String format(int number) {
      return this.formatter.format(number);
   }

   public ITextComponent getStatName() {
      ITextComponent itextcomponent = this.statName.createCopy();
      itextcomponent.getStyle().setColor(TextFormatting.GRAY);
      return itextcomponent;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         XQ statbase = (XQ)p_equals_1_;
         return this.statId.equals(statbase.statId);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.statId.hashCode();
   }

   public String toString() {
      return "Stat{id=" + this.statId + ", nameId=" + this.statName + ", awardLocallyOnly=" + this.isIndependent + ", formatter=" + this.formatter + ", objectiveCriteria=" + this.objectiveCriteria + '}';
   }

   public Wo getCriteria() {
      return this.objectiveCriteria;
   }

   public Class<? extends IJsonSerializable> getSerializableClazz() {
      return this.serializableClazz;
   }

   static {
      numberFormat = NumberFormat.getIntegerInstance(Locale.US);
      simpleStatType = new XI() {
         public String format(int number) {
            return XQ.numberFormat.format((long)number);
         }
      };
      decimalFormat = new DecimalFormat("########0.00");
      timeStatType = new XI() {
         public String format(int number) {
            double d0 = (double)number / 20.0;
            double d1 = d0 / 60.0;
            double d2 = d1 / 60.0;
            double d3 = d2 / 24.0;
            double d4 = d3 / 365.0;
            if (d4 > 0.5) {
               return XQ.decimalFormat.format(d4) + " y";
            } else if (d3 > 0.5) {
               return XQ.decimalFormat.format(d3) + " d";
            } else if (d2 > 0.5) {
               return XQ.decimalFormat.format(d2) + " h";
            } else {
               return d1 > 0.5 ? XQ.decimalFormat.format(d1) + " m" : d0 + " s";
            }
         }
      };
      distanceStatType = new XI() {
         public String format(int number) {
            double d0 = (double)number / 100.0;
            double d1 = d0 / 1000.0;
            if (d1 > 0.5) {
               return XQ.decimalFormat.format(d1) + " km";
            } else {
               return d0 > 0.5 ? XQ.decimalFormat.format(d0) + " m" : number + " cm";
            }
         }
      };
      divideByTen = new XI() {
         public String format(int number) {
            return XQ.decimalFormat.format((double)number * 0.1);
         }
      };
   }
}
