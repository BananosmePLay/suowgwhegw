package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class Cd extends Ch {
   public Cd() {
   }

   public String getName() {
      return "advancement";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.advancement.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.advancement.usage", new Object[0]);
      } else {
         Cb advancementcommand$actiontype = Cb.byName(args[0]);
         if (advancementcommand$actiontype != null) {
            if (args.length < 3) {
               throw advancementcommand$actiontype.wrongUsage();
            }

            MG entityplayermp = getPlayer(server, sender, args[1]);
            Cc advancementcommand$mode = Cc.byName(args[2]);
            if (advancementcommand$mode == null) {
               throw advancementcommand$actiontype.wrongUsage();
            }

            this.perform(server, sender, args, entityplayermp, advancementcommand$actiontype, advancementcommand$mode);
         } else {
            if (!"test".equals(args[0])) {
               throw new Ej("commands.advancement.usage", new Object[0]);
            }

            if (args.length == 3) {
               this.testAdvancement(sender, getPlayer(server, sender, args[1]), findAdvancement(server, args[2]));
            } else {
               if (args.length != 4) {
                  throw new Ej("commands.advancement.test.usage", new Object[0]);
               }

               this.testCriterion(sender, getPlayer(server, sender, args[1]), findAdvancement(server, args[2]), args[3]);
            }
         }

      }
   }

   private void perform(Xx server, DB sender, String[] args, MG player, Cb p_193516_5_, Cc p_193516_6_) throws Ct {
      if (p_193516_6_ == Cc.EVERYTHING) {
         if (args.length != 3) {
            throw p_193516_6_.usage(p_193516_5_);
         }

         int j = p_193516_5_.perform(player, server.getAdvancementManager().getAdvancements());
         if (j == 0) {
            throw p_193516_6_.fail(p_193516_5_, player.getName());
         }

         p_193516_6_.success(sender, this, p_193516_5_, player.getName(), j);
      } else {
         if (args.length < 4) {
            throw p_193516_6_.usage(p_193516_5_);
         }

         b advancement = findAdvancement(server, args[3]);
         if (p_193516_6_ == Cc.ONLY && args.length == 5) {
            String s = args[4];
            if (!advancement.getCriteria().keySet().contains(s)) {
               throw new Ct("commands.advancement.criterionNotFound", new Object[]{advancement.getId(), args[4]});
            }

            if (!p_193516_5_.performCriterion(player, advancement, s)) {
               throw new Ct(p_193516_5_.baseTranslationKey + ".criterion.failed", new Object[]{advancement.getId(), player.getName(), s});
            }

            notifyCommandListener(sender, this, p_193516_5_.baseTranslationKey + ".criterion.success", new Object[]{advancement.getId(), player.getName(), s});
         } else {
            if (args.length != 4) {
               throw p_193516_6_.usage(p_193516_5_);
            }

            List<b> list = this.getAdvancements(advancement, p_193516_6_);
            int i = p_193516_5_.perform(player, (Iterable)list);
            if (i == 0) {
               throw p_193516_6_.fail(p_193516_5_, advancement.getId(), player.getName());
            }

            p_193516_6_.success(sender, this, p_193516_5_, advancement.getId(), player.getName(), i);
         }
      }

   }

   private void addChildren(b p_193515_1_, List<b> p_193515_2_) {
      Iterator var3 = p_193515_1_.getChildren().iterator();

      while(var3.hasNext()) {
         b advancement = (b)var3.next();
         p_193515_2_.add(advancement);
         this.addChildren(advancement, p_193515_2_);
      }

   }

   private List<b> getAdvancements(b p_193514_1_, Cc p_193514_2_) {
      List<b> list = Lists.newArrayList();
      if (p_193514_2_.parents) {
         for(b advancement = p_193514_1_.getParent(); advancement != null; advancement = advancement.getParent()) {
            list.add(advancement);
         }
      }

      list.add(p_193514_1_);
      if (p_193514_2_.children) {
         this.addChildren(p_193514_1_, list);
      }

      return list;
   }

   private void testCriterion(DB p_192554_1_, MG p_192554_2_, b p_192554_3_, String p_192554_4_) throws Ct {
      cl playeradvancements = p_192554_2_.getAdvancements();
      ca criterionprogress = playeradvancements.getProgress(p_192554_3_).getCriterionProgress(p_192554_4_);
      if (criterionprogress == null) {
         throw new Ct("commands.advancement.criterionNotFound", new Object[]{p_192554_3_.getId(), p_192554_4_});
      } else if (!criterionprogress.isObtained()) {
         throw new Ct("commands.advancement.test.criterion.notDone", new Object[]{p_192554_2_.getName(), p_192554_3_.getId(), p_192554_4_});
      } else {
         notifyCommandListener(p_192554_1_, this, "commands.advancement.test.criterion.success", new Object[]{p_192554_2_.getName(), p_192554_3_.getId(), p_192554_4_});
      }
   }

   private void testAdvancement(DB p_192552_1_, MG p_192552_2_, b p_192552_3_) throws Ct {
      h advancementprogress = p_192552_2_.getAdvancements().getProgress(p_192552_3_);
      if (!advancementprogress.isDone()) {
         throw new Ct("commands.advancement.test.advancement.notDone", new Object[]{p_192552_2_.getName(), p_192552_3_.getId()});
      } else {
         notifyCommandListener(p_192552_1_, this, "commands.advancement.test.advancement.success", new Object[]{p_192552_2_.getName(), p_192552_3_.getId()});
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"grant", "revoke", "test"});
      } else {
         Cb advancementcommand$actiontype = Cb.byName(args[0]);
         if (advancementcommand$actiontype != null) {
            if (args.length == 2) {
               return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
            }

            if (args.length == 3) {
               return getListOfStringsMatchingLastWord(args, Cc.NAMES);
            }

            Cc advancementcommand$mode = Cc.byName(args[2]);
            if (advancementcommand$mode != null && advancementcommand$mode != Cc.EVERYTHING) {
               if (args.length == 4) {
                  return getListOfStringsMatchingLastWord(args, this.getAdvancementNames(server));
               }

               if (args.length == 5 && advancementcommand$mode == Cc.ONLY) {
                  b advancement = server.getAdvancementManager().getAdvancement(new ResourceLocation(args[3]));
                  if (advancement != null) {
                     return getListOfStringsMatchingLastWord(args, advancement.getCriteria().keySet());
                  }
               }
            }
         }

         if ("test".equals(args[0])) {
            if (args.length == 2) {
               return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
            }

            if (args.length == 3) {
               return getListOfStringsMatchingLastWord(args, this.getAdvancementNames(server));
            }

            if (args.length == 4) {
               b advancement1 = server.getAdvancementManager().getAdvancement(new ResourceLocation(args[2]));
               if (advancement1 != null) {
                  return getListOfStringsMatchingLastWord(args, advancement1.getCriteria().keySet());
               }
            }
         }

         return Collections.emptyList();
      }
   }

   private List<ResourceLocation> getAdvancementNames(Xx server) {
      List<ResourceLocation> list = Lists.newArrayList();
      Iterator var3 = server.getAdvancementManager().getAdvancements().iterator();

      while(var3.hasNext()) {
         b advancement = (b)var3.next();
         list.add(advancement.getId());
      }

      return list;
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return args.length > 1 && ("grant".equals(args[0]) || "revoke".equals(args[0]) || "test".equals(args[0])) && index == 1;
   }

   public static b findAdvancement(Xx server, String id) throws Ct {
      b advancement = server.getAdvancementManager().getAdvancement(new ResourceLocation(id));
      if (advancement == null) {
         throw new Ct("commands.advancement.advancementNotFound", new Object[]{id});
      } else {
         return advancement;
      }
   }
}
