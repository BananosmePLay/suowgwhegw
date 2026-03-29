package neo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NP {
   private static final Logger LOGGER = LogManager.getLogger();
   private static int nextAvailableId;
   public static final RegistryNamespaced<ResourceLocation, NT> REGISTRY = new RegistryNamespaced();

   public NP() {
   }

   public static boolean init() {
      try {
         register((String)"armordye", new NY());
         register((String)"bookcloning", new NV());
         register((String)"mapcloning", new Oc());
         register((String)"mapextending", new Od());
         register((String)"fireworks", new NW());
         register((String)"repairitem", new NX());
         register((String)"tippedarrow", new Oe());
         register((String)"bannerduplicate", new Oa());
         register((String)"banneraddpattern", new NZ());
         register((String)"shielddecoration", new Oh());
         register((String)"shulkerboxcoloring", new Oj());
         return parseJsonRecipes();
      } catch (Throwable var1) {
         return false;
      }
   }

   private static boolean parseJsonRecipes() {
      FileSystem filesystem = null;
      Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

      boolean var4;
      try {
         boolean flag1;
         try {
            URL url = NP.class.getResource("/assets/.mcassetsroot");
            if (url == null) {
               LOGGER.error("Couldn't find .mcassetsroot");
               flag1 = false;
               return flag1;
            }

            URI uri = url.toURI();
            boolean var35;
            Path path;
            if ("file".equals(uri.getScheme())) {
               path = Paths.get(NP.class.getResource("/assets/minecraft/recipes").toURI());
            } else {
               if (!"jar".equals(uri.getScheme())) {
                  LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                  boolean flag2 = false;
                  var35 = flag2;
                  return var35;
               }

               filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
               path = filesystem.getPath("/assets/minecraft/recipes");
            }

            Iterator<Path> iterator = Files.walk(path).iterator();

            while(iterator.hasNext()) {
               Path path1 = (Path)iterator.next();
               if ("json".equals(FilenameUtils.getExtension(path1.toString()))) {
                  Path path2 = path.relativize(path1);
                  String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                  ResourceLocation resourcelocation = new ResourceLocation(s);
                  BufferedReader bufferedreader = null;

                  try {
                     boolean flag;
                     boolean var14;
                     try {
                        bufferedreader = Files.newBufferedReader(path1);
                        register(s, parseRecipeJson((JsonObject)JsonUtils.fromJson(gson, bufferedreader, (Class)JsonObject.class)));
                     } catch (JsonParseException var27) {
                        JsonParseException jsonparseexception = var27;
                        LOGGER.error("Parsing error loading recipe " + resourcelocation, jsonparseexception);
                        flag = false;
                        var14 = flag;
                        return var14;
                     } catch (IOException var28) {
                        IOException ioexception = var28;
                        LOGGER.error("Couldn't read recipe " + resourcelocation + " from " + path1, ioexception);
                        flag = false;
                        var14 = flag;
                        return var14;
                     }
                  } finally {
                     IOUtils.closeQuietly(bufferedreader);
                  }
               }
            }

            var35 = true;
            return var35;
         } catch (URISyntaxException | IOException var30) {
            Exception urisyntaxexception = var30;
            LOGGER.error("Couldn't get a list of all recipe files", urisyntaxexception);
            flag1 = false;
            var4 = flag1;
         }
      } finally {
         IOUtils.closeQuietly(filesystem);
      }

      return var4;
   }

   private static NT parseRecipeJson(JsonObject json) {
      String s = JsonUtils.getString(json, "type");
      if ("crafting_shaped".equals(s)) {
         return Of.deserialize(json);
      } else if ("crafting_shapeless".equals(s)) {
         return Og.deserialize(json);
      } else {
         throw new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
      }
   }

   public static void register(String name, NT recipe) {
      register(new ResourceLocation(name), recipe);
   }

   public static void register(ResourceLocation name, NT recipe) {
      if (REGISTRY.containsKey(name)) {
         throw new IllegalStateException("Duplicate recipe ignored with ID " + name);
      } else {
         REGISTRY.register(nextAvailableId++, name, recipe);
      }
   }

   public static Qy findMatchingResult(InventoryCrafting craftMatrix, bij worldIn) {
      Iterator var2 = REGISTRY.iterator();

      NT irecipe;
      do {
         if (!var2.hasNext()) {
            return Qy.EMPTY;
         }

         irecipe = (NT)var2.next();
      } while(!irecipe.matches(craftMatrix, worldIn));

      return irecipe.getCraftingResult(craftMatrix);
   }

   @Nullable
   public static NT findMatchingRecipe(InventoryCrafting craftMatrix, bij worldIn) {
      Iterator var2 = REGISTRY.iterator();

      NT irecipe;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         irecipe = (NT)var2.next();
      } while(!irecipe.matches(craftMatrix, worldIn));

      return irecipe;
   }

   public static NonNullList<Qy> getRemainingItems(InventoryCrafting craftMatrix, bij worldIn) {
      Iterator var2 = REGISTRY.iterator();

      while(var2.hasNext()) {
         NT irecipe = (NT)var2.next();
         if (irecipe.matches(craftMatrix, worldIn)) {
            return irecipe.getRemainingItems(craftMatrix);
         }
      }

      NonNullList<Qy> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), Qy.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         nonnulllist.set(i, craftMatrix.getStackInSlot(i));
      }

      return nonnulllist;
   }

   @Nullable
   public static NT getRecipe(ResourceLocation name) {
      return (NT)REGISTRY.getObject(name);
   }

   public static int getIDForRecipe(NT recipe) {
      return REGISTRY.getIDForObject(recipe);
   }

   @Nullable
   public static NT getRecipeById(int id) {
      return (NT)REGISTRY.getObjectById(id);
   }
}
