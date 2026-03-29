package net.minecraft.item.crafting;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CraftingManager {
   private static final Logger LOGGER = LogManager.getLogger();
   private static int nextAvailableId;
   public static final RegistryNamespaced<ResourceLocation, IRecipe> REGISTRY = new RegistryNamespaced();

   public CraftingManager() {
   }

   public static boolean init() {
      try {
         register((String)"armordye", new RecipesArmorDyes());
         register((String)"bookcloning", new RecipeBookCloning());
         register((String)"mapcloning", new RecipesMapCloning());
         register((String)"mapextending", new RecipesMapExtending());
         register((String)"fireworks", new RecipeFireworks());
         register((String)"repairitem", new RecipeRepairItem());
         register((String)"tippedarrow", new RecipeTippedArrow());
         register((String)"bannerduplicate", new RecipesBanners.RecipeDuplicatePattern());
         register((String)"banneraddpattern", new RecipesBanners.RecipeAddPattern());
         register((String)"shielddecoration", new ShieldRecipes.Decoration());
         register((String)"shulkerboxcoloring", new ShulkerBoxRecipes.ShulkerBoxColoring());
         return parseJsonRecipes();
      } catch (Throwable var1) {
         return false;
      }
   }

   private static boolean parseJsonRecipes() {
      FileSystem filesystem = null;
      Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

      try {
         boolean flag1;
         try {
            URL url = CraftingManager.class.getResource("/assets/.mcassetsroot");
            if (url == null) {
               LOGGER.error("Couldn't find .mcassetsroot");
               flag1 = false;
               return flag1;
            } else {
               URI uri = url.toURI();
               boolean var35;
               Path path;
               if ("file".equals(uri.getScheme())) {
                  path = Paths.get(CraftingManager.class.getResource("/assets/minecraft/recipes").toURI());
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
            }
         } catch (URISyntaxException | IOException var30) {
            Exception urisyntaxexception = var30;
            LOGGER.error("Couldn't get a list of all recipe files", urisyntaxexception);
            flag1 = false;
            boolean var4 = flag1;
            return var4;
         }
      } finally {
         IOUtils.closeQuietly(filesystem);
      }
   }

   private static IRecipe parseRecipeJson(JsonObject json) {
      String s = JsonUtils.getString(json, "type");
      if ("crafting_shaped".equals(s)) {
         return ShapedRecipes.deserialize(json);
      } else if ("crafting_shapeless".equals(s)) {
         return ShapelessRecipes.deserialize(json);
      } else {
         throw new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'");
      }
   }

   public static void register(String name, IRecipe recipe) {
      register(new ResourceLocation(name), recipe);
   }

   public static void register(ResourceLocation name, IRecipe recipe) {
      if (REGISTRY.containsKey(name)) {
         throw new IllegalStateException("Duplicate recipe ignored with ID " + name);
      } else {
         REGISTRY.register(nextAvailableId++, name, recipe);
      }
   }

   public static ItemStack findMatchingResult(InventoryCrafting craftMatrix, World worldIn) {
      Iterator var2 = REGISTRY.iterator();

      IRecipe irecipe;
      do {
         if (!var2.hasNext()) {
            return ItemStack.EMPTY;
         }

         irecipe = (IRecipe)var2.next();
      } while(!irecipe.matches(craftMatrix, worldIn));

      return irecipe.getCraftingResult(craftMatrix);
   }

   @Nullable
   public static IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
      Iterator var2 = REGISTRY.iterator();

      IRecipe irecipe;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         irecipe = (IRecipe)var2.next();
      } while(!irecipe.matches(craftMatrix, worldIn));

      return irecipe;
   }

   public static NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
      Iterator var2 = REGISTRY.iterator();

      while(var2.hasNext()) {
         IRecipe irecipe = (IRecipe)var2.next();
         if (irecipe.matches(craftMatrix, worldIn)) {
            return irecipe.getRemainingItems(craftMatrix);
         }
      }

      NonNullList<ItemStack> nonnulllist = NonNullList.withSize(craftMatrix.getSizeInventory(), ItemStack.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         nonnulllist.set(i, craftMatrix.getStackInSlot(i));
      }

      return nonnulllist;
   }

   @Nullable
   public static IRecipe getRecipe(ResourceLocation name) {
      return (IRecipe)REGISTRY.getObject(name);
   }

   public static int getIDForRecipe(IRecipe recipe) {
      return REGISTRY.getIDForObject(recipe);
   }

   @Nullable
   public static IRecipe getRecipeById(int id) {
      return (IRecipe)REGISTRY.getObjectById(id);
   }
}
