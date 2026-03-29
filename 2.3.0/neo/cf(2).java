package neo;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cf implements ITickable {
   private static final Logger LOGGER = LogManager.getLogger();
   private final File functionDir;
   private final Xx server;
   private final Map<ResourceLocation, Dx> functions = Maps.newHashMap();
   private String currentGameLoopFunctionId = "-";
   private Dx gameLoopFunction;
   private final ArrayDeque<ce> commandQueue = new ArrayDeque();
   private boolean isExecuting = false;
   private final DB gameLoopFunctionSender = new DB() {
      public String getName() {
         return cf.this.currentGameLoopFunctionId;
      }

      public boolean canUseCommand(int permLevel, String commandName) {
         return permLevel <= 2;
      }

      public bij getEntityWorld() {
         return cf.this.server.worlds[0];
      }

      public Xx getServer() {
         return cf.this.server;
      }
   };

   public cf(@Nullable File functionDirIn, Xx serverIn) {
      this.functionDir = functionDirIn;
      this.server = serverIn;
      this.reload();
   }

   @Nullable
   public Dx getFunction(ResourceLocation id) {
      return (Dx)this.functions.get(id);
   }

   public DA getCommandManager() {
      return this.server.getCommandManager();
   }

   public int getMaxCommandChainLength() {
      return this.server.worlds[0].getGameRules().getInt("maxCommandChainLength");
   }

   public Map<ResourceLocation, Dx> getFunctions() {
      return this.functions;
   }

   public void update() {
      String s = this.server.worlds[0].getGameRules().getString("gameLoopFunction");
      if (!s.equals(this.currentGameLoopFunctionId)) {
         this.currentGameLoopFunctionId = s;
         this.gameLoopFunction = this.getFunction(new ResourceLocation(s));
      }

      if (this.gameLoopFunction != null) {
         this.execute(this.gameLoopFunction, this.gameLoopFunctionSender);
      }

   }

   public int execute(Dx function, DB sender) {
      int i = this.getMaxCommandChainLength();
      if (this.isExecuting) {
         if (this.commandQueue.size() < i) {
            this.commandQueue.addFirst(new ce(this, sender, new Dw(function)));
         }

         return 0;
      } else {
         int k;
         try {
            this.isExecuting = true;
            int j = 0;
            Dv[] afunctionobject$entry = function.getEntries();

            for(k = afunctionobject$entry.length - 1; k >= 0; --k) {
               this.commandQueue.push(new ce(this, sender, afunctionobject$entry[k]));
            }

            int l;
            while(!this.commandQueue.isEmpty()) {
               ((ce)this.commandQueue.removeFirst()).execute(this.commandQueue, i);
               ++j;
               if (j >= i) {
                  l = j;
                  return l;
               }
            }

            l = j;
            k = l;
         } finally {
            this.commandQueue.clear();
            this.isExecuting = false;
         }

         return k;
      }
   }

   public void reload() {
      this.functions.clear();
      this.gameLoopFunction = null;
      this.currentGameLoopFunctionId = "-";
      this.loadFunctions();
   }

   private void loadFunctions() {
      if (this.functionDir != null) {
         this.functionDir.mkdirs();
         Iterator var1 = FileUtils.listFiles(this.functionDir, new String[]{"mcfunction"}, true).iterator();

         while(var1.hasNext()) {
            File file1 = (File)var1.next();
            String s = FilenameUtils.removeExtension(this.functionDir.toURI().relativize(file1.toURI()).toString());
            String[] astring = s.split("/", 2);
            if (astring.length == 2) {
               ResourceLocation resourcelocation = new ResourceLocation(astring[0], astring[1]);

               try {
                  this.functions.put(resourcelocation, Dx.create(this, Files.readLines(file1, StandardCharsets.UTF_8)));
               } catch (Throwable var7) {
                  Throwable throwable = var7;
                  LOGGER.error("Couldn't read custom function " + resourcelocation + " from " + file1, throwable);
               }
            }
         }

         if (!this.functions.isEmpty()) {
            LOGGER.info("Loaded " + this.functions.size() + " custom command functions");
         }
      }

   }
}
