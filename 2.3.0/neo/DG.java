package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class DG extends Ch {
   public DG() {
   }

   public String getName() {
      return "recipe";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.recipe.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.recipe.usage", new Object[0]);
      } else {
         boolean flag = "give".equalsIgnoreCase(args[0]);
         boolean flag1 = "take".equalsIgnoreCase(args[0]);
         if (!flag && !flag1) {
            throw new Ej("commands.recipe.usage", new Object[0]);
         } else {
            Iterator var6 = getPlayers(server, sender, args[1]).iterator();

            while(var6.hasNext()) {
               MG entityplayermp = (MG)var6.next();
               if ("*".equals(args[2])) {
                  if (flag) {
                     entityplayermp.unlockRecipes(this.getRecipes());
                     notifyCommandListener(sender, this, "commands.recipe.give.success.all", new Object[]{entityplayermp.getName()});
                  } else {
                     entityplayermp.resetRecipes(this.getRecipes());
                     notifyCommandListener(sender, this, "commands.recipe.take.success.all", new Object[]{entityplayermp.getName()});
                  }
               } else {
                  NT irecipe = NP.getRecipe(new ResourceLocation(args[2]));
                  if (irecipe == null) {
                     throw new Ct("commands.recipe.unknownrecipe", new Object[]{args[2]});
                  }

                  if (irecipe.isDynamic()) {
                     throw new Ct("commands.recipe.unsupported", new Object[]{args[2]});
                  }

                  List<NT> list = Lists.newArrayList(new NT[]{irecipe});
                  if (flag == entityplayermp.getRecipeBook().isUnlocked(irecipe)) {
                     String s = flag ? "commands.recipe.alreadyHave" : "commands.recipe.dontHave";
                     throw new Ct(s, new Object[]{entityplayermp.getName(), irecipe.getRecipeOutput().getDisplayName()});
                  }

                  if (flag) {
                     entityplayermp.unlockRecipes((List)list);
                     notifyCommandListener(sender, this, "commands.recipe.give.success.one", new Object[]{entityplayermp.getName(), irecipe.getRecipeOutput().getDisplayName()});
                  } else {
                     entityplayermp.resetRecipes(list);
                     notifyCommandListener(sender, this, "commands.recipe.take.success.one", new Object[]{irecipe.getRecipeOutput().getDisplayName(), entityplayermp.getName()});
                  }
               }
            }

         }
      }
   }

   private List<NT> getRecipes() {
      return Lists.newArrayList(NP.REGISTRY);
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"give", "take"});
      } else if (args.length == 2) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length == 3 ? getListOfStringsMatchingLastWord(args, NP.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }
}
