package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Xj<K, V extends Xm<K>> {
   protected static final Logger LOGGER = LogManager.getLogger();
   protected final Gson gson;
   private final File saveFile;
   private final Map<String, V> values = Maps.newHashMap();
   private boolean lanServer = true;
   private static final ParameterizedType USER_LIST_ENTRY_TYPE = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{Xm.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };

   public Xj(File saveFile) {
      this.saveFile = saveFile;
      GsonBuilder gsonbuilder = (new GsonBuilder()).setPrettyPrinting();
      gsonbuilder.registerTypeHierarchyAdapter(Xm.class, new Xi(this));
      this.gson = gsonbuilder.create();
   }

   public boolean isLanServer() {
      return this.lanServer;
   }

   public void setLanServer(boolean state) {
      this.lanServer = state;
   }

   public void addEntry(V entry) {
      this.values.put(this.getObjectKey(entry.getValue()), entry);

      try {
         this.writeChanges();
      } catch (IOException var3) {
         IOException ioexception = var3;
         LOGGER.warn("Could not save the list after adding a user.", ioexception);
      }

   }

   public V getEntry(K obj) {
      this.removeExpired();
      return (Xm)this.values.get(this.getObjectKey(obj));
   }

   public void removeEntry(K entry) {
      this.values.remove(this.getObjectKey(entry));

      try {
         this.writeChanges();
      } catch (IOException var3) {
         IOException ioexception = var3;
         LOGGER.warn("Could not save the list after removing a user.", ioexception);
      }

   }

   public String[] getKeys() {
      return (String[])((String[])this.values.keySet().toArray(new String[this.values.size()]));
   }

   protected String getObjectKey(K obj) {
      return obj.toString();
   }

   protected boolean hasEntry(K entry) {
      return this.values.containsKey(this.getObjectKey(entry));
   }

   private void removeExpired() {
      List<K> list = Lists.newArrayList();
      Iterator var2 = this.values.values().iterator();

      while(var2.hasNext()) {
         V v = (Xm)var2.next();
         if (v.hasBanExpired()) {
            list.add(v.getValue());
         }
      }

      var2 = list.iterator();

      while(var2.hasNext()) {
         K k = var2.next();
         this.values.remove(k);
      }

   }

   protected Xm<K> createEntry(JsonObject entryData) {
      return new Xm((Object)null, entryData);
   }

   protected Map<String, V> getValues() {
      return this.values;
   }

   public void writeChanges() throws IOException {
      Collection<V> collection = this.values.values();
      String s = this.gson.toJson(collection);
      BufferedWriter bufferedwriter = null;

      try {
         bufferedwriter = Files.newWriter(this.saveFile, StandardCharsets.UTF_8);
         bufferedwriter.write(s);
      } finally {
         IOUtils.closeQuietly(bufferedwriter);
      }

   }
}
