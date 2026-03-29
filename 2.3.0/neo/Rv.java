package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Rv {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Map<Class<? extends Ig>, Integer> NEXT_ID_MAP = Maps.newHashMap();
   private final Ig entity;
   private final Map<Integer, Ru<?>> entries = Maps.newHashMap();
   private final ReadWriteLock lock = new ReentrantReadWriteLock();
   private boolean empty = true;
   private boolean dirty;
   public Zi spawnBiome;
   public BlockPos spawnPosition;

   public Rv(Ig entityIn) {
      this.spawnBiome = Nj.PLAINS;
      this.spawnPosition = BlockPos.ORIGIN;
      this.entity = entityIn;
   }

   public static <T> Rd<T> createKey(Class<? extends Ig> clazz, Re<T> serializer) {
      if (LOGGER.isDebugEnabled()) {
         try {
            Class<?> oclass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            if (!oclass.equals(clazz)) {
               LOGGER.debug("defineId called for: {} from {}", clazz, oclass, new RuntimeException());
            }
         } catch (ClassNotFoundException var5) {
         }
      }

      int j;
      if (NEXT_ID_MAP.containsKey(clazz)) {
         j = (Integer)NEXT_ID_MAP.get(clazz) + 1;
      } else {
         int i = 0;
         Class<?> oclass1 = clazz;

         while(oclass1 != Ig.class) {
            oclass1 = oclass1.getSuperclass();
            if (NEXT_ID_MAP.containsKey(oclass1)) {
               i = (Integer)NEXT_ID_MAP.get(oclass1) + 1;
               break;
            }
         }

         j = i;
      }

      if (j > 254) {
         throw new IllegalArgumentException("Data value id is too big with " + j + "! (Max is " + 254 + ")");
      } else {
         NEXT_ID_MAP.put(clazz, j);
         return serializer.createKey(j);
      }
   }

   public <T> void register(Rd<T> key, T value) {
      int i = key.getId();
      if (i > 254) {
         throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 254 + ")");
      } else if (this.entries.containsKey(i)) {
         throw new IllegalArgumentException("Duplicate id value for " + i + "!");
      } else if (Rt.getSerializerId(key.getSerializer()) < 0) {
         throw new IllegalArgumentException("Unregistered serializer " + key.getSerializer() + " for " + i + "!");
      } else {
         this.setEntry(key, value);
      }
   }

   private <T> void setEntry(Rd<T> key, T value) {
      Ru<T> dataentry = new Ru(key, value);
      this.lock.writeLock().lock();
      this.entries.put(key.getId(), dataentry);
      this.empty = false;
      this.lock.writeLock().unlock();
   }

   private <T> Ru<T> getEntry(Rd<T> key) {
      this.lock.readLock().lock();

      Ru dataentry;
      try {
         dataentry = (Ru)this.entries.get(key.getId());
      } catch (Throwable var6) {
         Throwable throwable = var6;
         Er crashreport = Er.makeCrashReport(throwable, "Getting synched entity data");
         Ey crashreportcategory = crashreport.makeCategory("Synched entity data");
         crashreportcategory.addCrashSection("Data ID", key);
         throw new ReportedException(crashreport);
      }

      this.lock.readLock().unlock();
      return dataentry;
   }

   public <T> T get(Rd<T> key) {
      return this.getEntry(key).getValue();
   }

   public <T> void set(Rd<T> key, T value) {
      Ru<T> dataentry = this.getEntry(key);
      if (ObjectUtils.notEqual(value, dataentry.getValue())) {
         dataentry.setValue(value);
         this.entity.notifyDataManagerChange(key);
         dataentry.setDirty(true);
         this.dirty = true;
      }

   }

   public <T> void setDirty(Rd<T> key) {
      Ru.access$002(this.getEntry(key), true);
      this.dirty = true;
   }

   public boolean isDirty() {
      return this.dirty;
   }

   public static void writeEntries(List<Ru<?>> entriesIn, SA buf) throws IOException {
      if (entriesIn != null) {
         int i = 0;

         for(int j = entriesIn.size(); i < j; ++i) {
            Ru<?> dataentry = (Ru)entriesIn.get(i);
            writeEntry(buf, dataentry);
         }
      }

      buf.writeByte(255);
   }

   @Nullable
   public List<Ru<?>> getDirty() {
      List<Ru<?>> list = null;
      if (this.dirty) {
         this.lock.readLock().lock();
         Iterator var2 = this.entries.values().iterator();

         while(var2.hasNext()) {
            Ru<?> dataentry = (Ru)var2.next();
            if (dataentry.isDirty()) {
               dataentry.setDirty(false);
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(dataentry.copy());
            }
         }

         this.lock.readLock().unlock();
      }

      this.dirty = false;
      return list;
   }

   public void writeEntries(SA buf) throws IOException {
      this.lock.readLock().lock();
      Iterator var2 = this.entries.values().iterator();

      while(var2.hasNext()) {
         Ru<?> dataentry = (Ru)var2.next();
         writeEntry(buf, dataentry);
      }

      this.lock.readLock().unlock();
      buf.writeByte(255);
   }

   @Nullable
   public List<Ru<?>> getAll() {
      List<Ru<?>> list = null;
      this.lock.readLock().lock();

      Ru dataentry;
      for(Iterator var2 = this.entries.values().iterator(); var2.hasNext(); list.add(dataentry.copy())) {
         dataentry = (Ru)var2.next();
         if (list == null) {
            list = Lists.newArrayList();
         }
      }

      this.lock.readLock().unlock();
      return list;
   }

   private static <T> void writeEntry(SA buf, Ru<T> entry) throws IOException {
      Rd<T> dataparameter = entry.getKey();
      int i = Rt.getSerializerId(dataparameter.getSerializer());
      if (i < 0) {
         throw new EncoderException("Unknown serializer type " + dataparameter.getSerializer());
      } else {
         buf.writeByte(dataparameter.getId());
         buf.writeVarInt(i);
         dataparameter.getSerializer().write(buf, entry.getValue());
      }
   }

   @Nullable
   public static List<Ru<?>> readEntries(SA buf) throws IOException {
      List<Ru<?>> list = null;

      short i;
      while((i = buf.readUnsignedByte()) != 255) {
         if (list == null) {
            list = Lists.newArrayList();
         }

         int j = buf.readVarInt();
         Re<?> dataserializer = Rt.getSerializer(j);
         if (dataserializer == null) {
            throw new DecoderException("Unknown serializer type " + j);
         }

         list.add(new Ru(dataserializer.createKey(i), dataserializer.read(buf)));
      }

      return list;
   }

   public void setEntryValues(List<Ru<?>> entriesIn) {
      this.lock.writeLock().lock();
      Iterator var2 = entriesIn.iterator();

      while(var2.hasNext()) {
         Ru<?> dataentry = (Ru)var2.next();
         Ru<?> dataentry1 = (Ru)this.entries.get(dataentry.getKey().getId());
         if (dataentry1 != null) {
            this.setEntryValue(dataentry1, dataentry);
            this.entity.notifyDataManagerChange(dataentry.getKey());
         }
      }

      this.lock.writeLock().unlock();
      this.dirty = true;
   }

   protected <T> void setEntryValue(Ru<T> target, Ru<?> source) {
      target.setValue(source.getValue());
   }

   public boolean isEmpty() {
      return this.empty;
   }

   public void setClean() {
      this.dirty = false;
      this.lock.readLock().lock();
      Iterator var1 = this.entries.values().iterator();

      while(var1.hasNext()) {
         Ru<?> dataentry = (Ru)var1.next();
         dataentry.setDirty(false);
      }

      this.lock.readLock().unlock();
   }
}
