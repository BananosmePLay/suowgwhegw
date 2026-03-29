package neo;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class f {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = (new GsonBuilder()).registerTypeHierarchyAdapter(a.class, new JsonDeserializer<a>() {
      public a deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "advancement");
         return a.deserialize(jsonobject, p_deserialize_3_);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
         return this.deserialize(var1, var2, var3);
      }
   }).registerTypeAdapter(k.class, new j()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeHierarchyAdapter(Style.class, new Style.Serializer()).registerTypeAdapterFactory(new EnumTypeAdapterFactory()).create();
   private static final d ADVANCEMENT_LIST = new d();
   private final File advancementsDir;
   private boolean hasErrored;

   public f(@Nullable File advancementsDirIn) {
      this.advancementsDir = advancementsDirIn;
      this.reload();
   }

   public void reload() {
      this.hasErrored = false;
      ADVANCEMENT_LIST.clear();
      Map<ResourceLocation, a> map = this.loadCustomAdvancements();
      this.loadBuiltInAdvancements(map);
      ADVANCEMENT_LIST.loadAdvancements(map);
      Iterator var2 = ADVANCEMENT_LIST.getRoots().iterator();

      while(var2.hasNext()) {
         b advancement = (b)var2.next();
         if (advancement.getDisplay() != null) {
            l.layout(advancement);
         }
      }

   }

   public boolean hasErrored() {
      return this.hasErrored;
   }

   private Map<ResourceLocation, a> loadCustomAdvancements() {
      if (this.advancementsDir == null) {
         return Maps.newHashMap();
      } else {
         Map<ResourceLocation, a> map = Maps.newHashMap();
         this.advancementsDir.mkdirs();
         Iterator var2 = FileUtils.listFiles(this.advancementsDir, new String[]{"json"}, true).iterator();

         while(var2.hasNext()) {
            File file1 = (File)var2.next();
            String s = FilenameUtils.removeExtension(this.advancementsDir.toURI().relativize(file1.toURI()).toString());
            String[] astring = s.split("/", 2);
            if (astring.length == 2) {
               ResourceLocation resourcelocation = new ResourceLocation(astring[0], astring[1]);

               try {
                  a advancement$builder = (a)JsonUtils.gsonDeserialize(GSON, FileUtils.readFileToString(file1, StandardCharsets.UTF_8), a.class);
                  if (advancement$builder == null) {
                     LOGGER.error("Couldn't load custom advancement " + resourcelocation + " from " + file1 + " as it's empty or null");
                  } else {
                     map.put(resourcelocation, advancement$builder);
                  }
               } catch (JsonParseException | IllegalArgumentException var8) {
                  RuntimeException jsonparseexception = var8;
                  LOGGER.error("Parsing error loading custom advancement " + resourcelocation, jsonparseexception);
                  this.hasErrored = true;
               } catch (IOException var9) {
                  IOException ioexception = var9;
                  LOGGER.error("Couldn't read custom advancement " + resourcelocation + " from " + file1, ioexception);
                  this.hasErrored = true;
               }
            }
         }

         return map;
      }
   }

   private void loadBuiltInAdvancements(Map<ResourceLocation, a> map) {
      FileSystem filesystem = null;

      try {
         URL url = f.class.getResource("/assets/.mcassetsroot");
         if (url == null) {
            LOGGER.error("Couldn't find .mcassetsroot");
            this.hasErrored = true;
            return;
         }

         URI uri = url.toURI();
         Path path;
         if ("file".equals(uri.getScheme())) {
            path = Paths.get(NP.class.getResource("/assets/minecraft/advancements").toURI());
         } else {
            if (!"jar".equals(uri.getScheme())) {
               LOGGER.error("Unsupported scheme " + uri + " trying to list all built-in advancements (NYI?)");
               this.hasErrored = true;
               return;
            }

            filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
            path = filesystem.getPath("/assets/minecraft/advancements");
         }

         Iterator<Path> iterator = Files.walk(path).iterator();

         while(iterator.hasNext()) {
            Path path1 = (Path)iterator.next();
            if ("json".equals(FilenameUtils.getExtension(path1.toString()))) {
               Path path2 = path.relativize(path1);
               String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
               ResourceLocation resourcelocation = new ResourceLocation("minecraft", s);
               if (!map.containsKey(resourcelocation)) {
                  BufferedReader bufferedreader = null;

                  try {
                     bufferedreader = Files.newBufferedReader(path1);
                     a advancement$builder = (a)JsonUtils.fromJson(GSON, bufferedreader, (Class)a.class);
                     map.put(resourcelocation, advancement$builder);
                  } catch (JsonParseException var25) {
                     JsonParseException jsonparseexception = var25;
                     LOGGER.error("Parsing error loading built-in advancement " + resourcelocation, jsonparseexception);
                     this.hasErrored = true;
                  } catch (IOException var26) {
                     IOException ioexception = var26;
                     LOGGER.error("Couldn't read advancement " + resourcelocation + " from " + path1, ioexception);
                     this.hasErrored = true;
                  } finally {
                     IOUtils.closeQuietly(bufferedreader);
                  }
               }
            }
         }
      } catch (URISyntaxException | IOException var28) {
         Exception urisyntaxexception = var28;
         LOGGER.error("Couldn't get a list of all built-in advancement files", urisyntaxexception);
         this.hasErrored = true;
         return;
      } finally {
         IOUtils.closeQuietly(filesystem);
      }

   }

   @Nullable
   public b getAdvancement(ResourceLocation id) {
      return ADVANCEMENT_LIST.getAdvancement(id);
   }

   public Iterable<b> getAdvancements() {
      return ADVANCEMENT_LIST.getAdvancements();
   }
}
