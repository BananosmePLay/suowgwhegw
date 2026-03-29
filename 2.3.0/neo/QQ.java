package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QQ extends QH {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Pattern SIMPLE_VALUE = Pattern.compile("[A-Za-z0-9._+-]+");
   private final Map<String, QH> tagMap = Maps.newHashMap();

   public QQ() {
   }

   void write(DataOutput output) throws IOException {
      Iterator var2 = this.tagMap.keySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         QH nbtbase = (QH)this.tagMap.get(s);
         writeEntry(s, nbtbase, output);
      }

      output.writeByte(0);
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(384L);
      if (depth > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagMap.clear();

         byte b0;
         while((b0 = readType(input, sizeTracker)) != 0) {
            String s = readKey(input, sizeTracker);
            sizeTracker.read((long)(224 + 16 * s.length()));
            QH nbtbase = readNBT(b0, s, input, depth + 1, sizeTracker);
            if (this.tagMap.put(s, nbtbase) != null) {
               sizeTracker.read(288L);
            }
         }

      }
   }

   public Set<String> getKeySet() {
      return this.tagMap.keySet();
   }

   public byte getId() {
      return 10;
   }

   public int getSize() {
      return this.tagMap.size();
   }

   public void setTag(String key, QH value) {
      this.tagMap.put(key, value);
   }

   public void setByte(String key, byte value) {
      this.tagMap.put(key, new QM(value));
   }

   public void setShort(String key, short value) {
      this.tagMap.put(key, new QZ(value));
   }

   public void setInteger(String key, int value) {
      this.tagMap.put(key, new QU(value));
   }

   public void setLong(String key, long value) {
      this.tagMap.put(key, new QX(value));
   }

   public void setUniqueId(String key, UUID value) {
      this.setLong(key + "Most", value.getMostSignificantBits());
      this.setLong(key + "Least", value.getLeastSignificantBits());
   }

   @Nullable
   public UUID getUniqueId(String key) {
      return new UUID(this.getLong(key + "Most"), this.getLong(key + "Least"));
   }

   public boolean hasUniqueId(String key) {
      return this.hasKey(key + "Most", 99) && this.hasKey(key + "Least", 99);
   }

   public void setFloat(String key, float value) {
      this.tagMap.put(key, new QT(value));
   }

   public void setDouble(String key, double value) {
      this.tagMap.put(key, new QR(value));
   }

   public void setString(String key, String value) {
      this.tagMap.put(key, new Ra(value));
   }

   public void setByteArray(String key, byte[] value) {
      this.tagMap.put(key, new QN(value));
   }

   public void setIntArray(String key, int[] value) {
      this.tagMap.put(key, new QV(value));
   }

   public void setBoolean(String key, boolean value) {
      this.setByte(key, (byte)(value ? 1 : 0));
   }

   public QH getTag(String key) {
      return (QH)this.tagMap.get(key);
   }

   public byte getTagId(String key) {
      QH nbtbase = (QH)this.tagMap.get(key);
      return nbtbase == null ? 0 : nbtbase.getId();
   }

   public boolean hasKey(String key) {
      return this.tagMap.containsKey(key);
   }

   public boolean hasKey(String key, int type) {
      int i = this.getTagId(key);
      if (i == type) {
         return true;
      } else if (type != 99) {
         return false;
      } else {
         return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6;
      }
   }

   public byte getByte(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getByte();
         }
      } catch (ClassCastException var3) {
      }

      return 0;
   }

   public short getShort(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getShort();
         }
      } catch (ClassCastException var3) {
      }

      return 0;
   }

   public int getInteger(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getInt();
         }
      } catch (ClassCastException var3) {
      }

      return 0;
   }

   public long getLong(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getLong();
         }
      } catch (ClassCastException var3) {
      }

      return 0L;
   }

   public float getFloat(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getFloat();
         }
      } catch (ClassCastException var3) {
      }

      return 0.0F;
   }

   public double getDouble(String key) {
      try {
         if (this.hasKey(key, 99)) {
            return ((QJ)this.tagMap.get(key)).getDouble();
         }
      } catch (ClassCastException var3) {
      }

      return 0.0;
   }

   public String getString(String key) {
      try {
         if (this.hasKey(key, 8)) {
            return ((QH)this.tagMap.get(key)).getString();
         }
      } catch (ClassCastException var3) {
      }

      return "";
   }

   public byte[] getByteArray(String key) {
      try {
         if (this.hasKey(key, 7)) {
            return ((QN)this.tagMap.get(key)).getByteArray();
         }
      } catch (ClassCastException var3) {
         ClassCastException classcastexception = var3;
         throw new ReportedException(this.createCrashReport(key, 7, classcastexception));
      }

      return new byte[0];
   }

   public int[] getIntArray(String key) {
      try {
         if (this.hasKey(key, 11)) {
            return ((QV)this.tagMap.get(key)).getIntArray();
         }
      } catch (ClassCastException var3) {
         ClassCastException classcastexception = var3;
         throw new ReportedException(this.createCrashReport(key, 11, classcastexception));
      }

      return new int[0];
   }

   public QQ getCompoundTag(String key) {
      try {
         if (this.hasKey(key, 10)) {
            return (QQ)this.tagMap.get(key);
         }
      } catch (ClassCastException var3) {
         ClassCastException classcastexception = var3;
         throw new ReportedException(this.createCrashReport(key, 10, classcastexception));
      }

      return new QQ();
   }

   public QW getTagList(String key, int type) {
      try {
         if (this.getTagId(key) == 9) {
            QW nbttaglist = (QW)this.tagMap.get(key);
            if (!nbttaglist.isEmpty() && nbttaglist.getTagType() != type) {
               return new QW();
            }

            return nbttaglist;
         }
      } catch (ClassCastException var4) {
         ClassCastException classcastexception = var4;
         throw new ReportedException(this.createCrashReport(key, 9, classcastexception));
      }

      return new QW();
   }

   public boolean getBoolean(String key) {
      return this.getByte(key) != 0;
   }

   public void removeTag(String key) {
      this.tagMap.remove(key);
   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder("{");
      Collection<String> collection = this.tagMap.keySet();
      if (LOGGER.isDebugEnabled()) {
         List<String> list = Lists.newArrayList(this.tagMap.keySet());
         Collections.sort(list);
         collection = list;
      }

      String s;
      for(Iterator var5 = ((Collection)collection).iterator(); var5.hasNext(); stringbuilder.append(handleEscape(s)).append(':').append(this.tagMap.get(s))) {
         s = (String)var5.next();
         if (stringbuilder.length() != 1) {
            stringbuilder.append(',');
         }
      }

      return stringbuilder.append('}').toString();
   }

   public boolean isEmpty() {
      return this.tagMap.isEmpty();
   }

   private Er createCrashReport(final String key, final int expectedType, ClassCastException ex) {
      Er crashreport = Er.makeCrashReport(ex, "Reading NBT data");
      Ey crashreportcategory = crashreport.makeCategoryDepth("Corrupt NBT tag", 1);
      crashreportcategory.addDetail("Tag type found", new Ez<String>() {
         public String call() throws Exception {
            return QH.NBT_TYPES[((QH)QQ.this.tagMap.get(key)).getId()];
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      crashreportcategory.addDetail("Tag type expected", new Ez<String>() {
         public String call() throws Exception {
            return QH.NBT_TYPES[expectedType];
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      crashreportcategory.addCrashSection("Tag name", key);
      return crashreport;
   }

   public QQ copy() {
      QQ nbttagcompound = new QQ();
      Iterator var2 = this.tagMap.keySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         nbttagcompound.setTag(s, ((QH)this.tagMap.get(s)).copy());
      }

      return nbttagcompound;
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) && Objects.equals(this.tagMap.entrySet(), ((QQ)p_equals_1_).tagMap.entrySet());
   }

   public int hashCode() {
      return super.hashCode() ^ this.tagMap.hashCode();
   }

   private static void writeEntry(String name, QH data, DataOutput output) throws IOException {
      output.writeByte(data.getId());
      if (data.getId() != 0) {
         output.writeUTF(name);
         data.write(output);
      }

   }

   private static byte readType(DataInput input, QL sizeTracker) throws IOException {
      return input.readByte();
   }

   private static String readKey(DataInput input, QL sizeTracker) throws IOException {
      return input.readUTF();
   }

   static QH readNBT(byte id, String key, DataInput input, int depth, QL sizeTracker) throws IOException {
      QH nbtbase = QH.create(id);

      try {
         nbtbase.read(input, depth, sizeTracker);
         return nbtbase;
      } catch (IOException var9) {
         IOException ioexception = var9;
         Er crashreport = Er.makeCrashReport(ioexception, "Loading NBT data");
         Ey crashreportcategory = crashreport.makeCategory("NBT Tag");
         crashreportcategory.addCrashSection("Tag name", key);
         crashreportcategory.addCrashSection("Tag type", id);
         throw new ReportedException(crashreport);
      }
   }

   public void merge(QQ other) {
      Iterator var2 = other.tagMap.keySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         QH nbtbase = (QH)other.tagMap.get(s);
         if (nbtbase.getId() == 10) {
            if (this.hasKey(s, 10)) {
               QQ nbttagcompound = this.getCompoundTag(s);
               nbttagcompound.merge((QQ)nbtbase);
            } else {
               this.setTag(s, nbtbase.copy());
            }
         } else {
            this.setTag(s, nbtbase.copy());
         }
      }

   }

   protected static String handleEscape(String p_193582_0_) {
      return SIMPLE_VALUE.matcher(p_193582_0_).matches() ? p_193582_0_ : Ra.quoteAndEscape(p_193582_0_);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public QH copy() {
      return this.copy();
   }
}
