package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class DX extends Ch {
   public DX() {
   }

   public String getName() {
      return "scoreboard";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.scoreboard.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (!this.handleUserWildcards(server, sender, args)) {
         if (args.length < 1) {
            throw new Ej("commands.scoreboard.usage", new Object[0]);
         }

         if ("objectives".equalsIgnoreCase(args[0])) {
            if (args.length == 1) {
               throw new Ej("commands.scoreboard.objectives.usage", new Object[0]);
            }

            if ("list".equalsIgnoreCase(args[1])) {
               this.listObjectives(sender, server);
            } else if ("add".equalsIgnoreCase(args[1])) {
               if (args.length < 4) {
                  throw new Ej("commands.scoreboard.objectives.add.usage", new Object[0]);
               }

               this.addObjective(sender, args, 2, server);
            } else if ("remove".equalsIgnoreCase(args[1])) {
               if (args.length != 3) {
                  throw new Ej("commands.scoreboard.objectives.remove.usage", new Object[0]);
               }

               this.removeObjective(sender, args[2], server);
            } else {
               if (!"setdisplay".equalsIgnoreCase(args[1])) {
                  throw new Ej("commands.scoreboard.objectives.usage", new Object[0]);
               }

               if (args.length != 3 && args.length != 4) {
                  throw new Ej("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
               }

               this.setDisplayObjective(sender, args, 2, server);
            }
         } else if ("players".equalsIgnoreCase(args[0])) {
            if (args.length == 1) {
               throw new Ej("commands.scoreboard.players.usage", new Object[0]);
            }

            if ("list".equalsIgnoreCase(args[1])) {
               if (args.length > 3) {
                  throw new Ej("commands.scoreboard.players.list.usage", new Object[0]);
               }

               this.listPlayers(sender, args, 2, server);
            } else if ("add".equalsIgnoreCase(args[1])) {
               if (args.length < 5) {
                  throw new Ej("commands.scoreboard.players.add.usage", new Object[0]);
               }

               this.addPlayerScore(sender, args, 2, server);
            } else if ("remove".equalsIgnoreCase(args[1])) {
               if (args.length < 5) {
                  throw new Ej("commands.scoreboard.players.remove.usage", new Object[0]);
               }

               this.addPlayerScore(sender, args, 2, server);
            } else if ("set".equalsIgnoreCase(args[1])) {
               if (args.length < 5) {
                  throw new Ej("commands.scoreboard.players.set.usage", new Object[0]);
               }

               this.addPlayerScore(sender, args, 2, server);
            } else if ("reset".equalsIgnoreCase(args[1])) {
               if (args.length != 3 && args.length != 4) {
                  throw new Ej("commands.scoreboard.players.reset.usage", new Object[0]);
               }

               this.resetPlayerScore(sender, args, 2, server);
            } else if ("enable".equalsIgnoreCase(args[1])) {
               if (args.length != 4) {
                  throw new Ej("commands.scoreboard.players.enable.usage", new Object[0]);
               }

               this.enablePlayerTrigger(sender, args, 2, server);
            } else if ("test".equalsIgnoreCase(args[1])) {
               if (args.length != 5 && args.length != 6) {
                  throw new Ej("commands.scoreboard.players.test.usage", new Object[0]);
               }

               this.testPlayerScore(sender, args, 2, server);
            } else if ("operation".equalsIgnoreCase(args[1])) {
               if (args.length != 7) {
                  throw new Ej("commands.scoreboard.players.operation.usage", new Object[0]);
               }

               this.applyPlayerOperation(sender, args, 2, server);
            } else {
               if (!"tag".equalsIgnoreCase(args[1])) {
                  throw new Ej("commands.scoreboard.players.usage", new Object[0]);
               }

               if (args.length < 4) {
                  throw new Ej("commands.scoreboard.players.tag.usage", new Object[0]);
               }

               this.applyPlayerTag(server, sender, args, 2);
            }
         } else {
            if (!"teams".equalsIgnoreCase(args[0])) {
               throw new Ej("commands.scoreboard.usage", new Object[0]);
            }

            if (args.length == 1) {
               throw new Ej("commands.scoreboard.teams.usage", new Object[0]);
            }

            if ("list".equalsIgnoreCase(args[1])) {
               if (args.length > 3) {
                  throw new Ej("commands.scoreboard.teams.list.usage", new Object[0]);
               }

               this.listTeams(sender, args, 2, server);
            } else if ("add".equalsIgnoreCase(args[1])) {
               if (args.length < 3) {
                  throw new Ej("commands.scoreboard.teams.add.usage", new Object[0]);
               }

               this.addTeam(sender, args, 2, server);
            } else if ("remove".equalsIgnoreCase(args[1])) {
               if (args.length != 3) {
                  throw new Ej("commands.scoreboard.teams.remove.usage", new Object[0]);
               }

               this.removeTeam(sender, args, 2, server);
            } else if ("empty".equalsIgnoreCase(args[1])) {
               if (args.length != 3) {
                  throw new Ej("commands.scoreboard.teams.empty.usage", new Object[0]);
               }

               this.emptyTeam(sender, args, 2, server);
            } else if (!"join".equalsIgnoreCase(args[1])) {
               if ("leave".equalsIgnoreCase(args[1])) {
                  if (args.length < 3 && !(sender instanceof ME)) {
                     throw new Ej("commands.scoreboard.teams.leave.usage", new Object[0]);
                  }

                  this.leaveTeam(sender, args, 2, server);
               } else {
                  if (!"option".equalsIgnoreCase(args[1])) {
                     throw new Ej("commands.scoreboard.teams.usage", new Object[0]);
                  }

                  if (args.length != 4 && args.length != 5) {
                     throw new Ej("commands.scoreboard.teams.option.usage", new Object[0]);
                  }

                  this.setTeamOption(sender, args, 2, server);
               }
            } else {
               if (args.length < 4 && (args.length != 3 || !(sender instanceof ME))) {
                  throw new Ej("commands.scoreboard.teams.join.usage", new Object[0]);
               }

               this.joinTeam(sender, args, 2, server);
            }
         }
      }

   }

   private boolean handleUserWildcards(Xx server, DB sender, String[] args) throws Ct {
      int i = -1;

      for(int j = 0; j < args.length; ++j) {
         if (this.isUsernameIndex(args, j) && "*".equals(args[j])) {
            if (i >= 0) {
               throw new Ct("commands.scoreboard.noMultiWildcard", new Object[0]);
            }

            i = j;
         }
      }

      if (i < 0) {
         return false;
      } else {
         List<String> list1 = Lists.newArrayList(this.getScoreboard(server).getObjectiveNames());
         String s = args[i];
         List<String> list = Lists.newArrayList();
         Iterator var8 = list1.iterator();

         while(var8.hasNext()) {
            String s1 = (String)var8.next();
            args[i] = s1;

            try {
               this.execute(server, sender, args);
               list.add(s1);
            } catch (Ct var12) {
               Ct commandexception = var12;
               TextComponentTranslation textcomponenttranslation = new TextComponentTranslation(commandexception.getMessage(), commandexception.getErrorObjects());
               textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
               sender.sendMessage(textcomponenttranslation);
            }
         }

         args[i] = s;
         sender.setCommandStat(CK.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new Ej("commands.scoreboard.allMatchesFailed", new Object[0]);
         } else {
            return true;
         }
      }
   }

   protected Ws getScoreboard(Xx server) {
      return server.getWorld(0).getScoreboard();
   }

   protected Wz convertToObjective(String name, boolean forWrite, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      Wz scoreobjective = scoreboard.getObjective(name);
      if (scoreobjective == null) {
         throw new Ct("commands.scoreboard.objectiveNotFound", new Object[]{name});
      } else if (forWrite && scoreobjective.getCriteria().isReadOnly()) {
         throw new Ct("commands.scoreboard.objectiveReadOnly", new Object[]{name});
      } else {
         return scoreobjective;
      }
   }

   protected WA convertToTeam(String name, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      WA scoreplayerteam = scoreboard.getTeam(name);
      if (scoreplayerteam == null) {
         throw new Ct("commands.scoreboard.teamNotFound", new Object[]{name});
      } else {
         return scoreplayerteam;
      }
   }

   protected void addObjective(DB sender, String[] commandArgs, int argStartIndex, Xx server) throws Ct {
      String s = commandArgs[argStartIndex++];
      String s1 = commandArgs[argStartIndex++];
      Ws scoreboard = this.getScoreboard(server);
      Wo iscorecriteria = (Wo)Wo.INSTANCES.get(s1);
      if (iscorecriteria == null) {
         throw new Ej("commands.scoreboard.objectives.add.wrongType", new Object[]{s1});
      } else if (scoreboard.getObjective(s) != null) {
         throw new Ct("commands.scoreboard.objectives.add.alreadyExists", new Object[]{s});
      } else if (s.length() > 16) {
         throw new Ei("commands.scoreboard.objectives.add.tooLong", new Object[]{s, 16});
      } else if (s.isEmpty()) {
         throw new Ej("commands.scoreboard.objectives.add.usage", new Object[0]);
      } else {
         if (commandArgs.length > argStartIndex) {
            String s2 = getChatComponentFromNthArg(sender, commandArgs, argStartIndex).getUnformattedText();
            if (s2.length() > 32) {
               throw new Ei("commands.scoreboard.objectives.add.displayTooLong", new Object[]{s2, 32});
            }

            if (s2.isEmpty()) {
               scoreboard.addScoreObjective(s, iscorecriteria);
            } else {
               scoreboard.addScoreObjective(s, iscorecriteria).setDisplayName(s2);
            }
         } else {
            scoreboard.addScoreObjective(s, iscorecriteria);
         }

         notifyCommandListener(sender, this, "commands.scoreboard.objectives.add.success", new Object[]{s});
      }
   }

   protected void addTeam(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      String s = args[startIndex++];
      Ws scoreboard = this.getScoreboard(server);
      if (scoreboard.getTeam(s) != null) {
         throw new Ct("commands.scoreboard.teams.add.alreadyExists", new Object[]{s});
      } else if (s.length() > 16) {
         throw new Ei("commands.scoreboard.teams.add.tooLong", new Object[]{s, 16});
      } else if (s.isEmpty()) {
         throw new Ej("commands.scoreboard.teams.add.usage", new Object[0]);
      } else {
         if (args.length > startIndex) {
            String s1 = getChatComponentFromNthArg(sender, args, startIndex).getUnformattedText();
            if (s1.length() > 32) {
               throw new Ei("commands.scoreboard.teams.add.displayTooLong", new Object[]{s1, 32});
            }

            if (s1.isEmpty()) {
               scoreboard.createTeam(s);
            } else {
               scoreboard.createTeam(s).setDisplayName(s1);
            }
         } else {
            scoreboard.createTeam(s);
         }

         notifyCommandListener(sender, this, "commands.scoreboard.teams.add.success", new Object[]{s});
      }
   }

   protected void setTeamOption(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      WA scoreplayerteam = this.convertToTeam(args[startIndex++], server);
      if (scoreplayerteam != null) {
         String s = args[startIndex++].toLowerCase(Locale.ROOT);
         if (!"color".equalsIgnoreCase(s) && !"friendlyfire".equalsIgnoreCase(s) && !"seeFriendlyInvisibles".equalsIgnoreCase(s) && !"nametagVisibility".equalsIgnoreCase(s) && !"deathMessageVisibility".equalsIgnoreCase(s) && !"collisionRule".equalsIgnoreCase(s)) {
            throw new Ej("commands.scoreboard.teams.option.usage", new Object[0]);
         }

         if (args.length == 4) {
            if ("color".equalsIgnoreCase(s)) {
               throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceStringFromCollection(TextFormatting.getValidValues(true, false))});
            }

            if (!"friendlyfire".equalsIgnoreCase(s) && !"seeFriendlyInvisibles".equalsIgnoreCase(s)) {
               if (!"nametagVisibility".equalsIgnoreCase(s) && !"deathMessageVisibility".equalsIgnoreCase(s)) {
                  if ("collisionRule".equalsIgnoreCase(s)) {
                     throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceString(WC.getNames())});
                  }

                  throw new Ej("commands.scoreboard.teams.option.usage", new Object[0]);
               }

               throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceString(WD.getNames())});
            }

            throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceStringFromCollection(Arrays.asList("true", "false"))});
         }

         String s1 = args[startIndex];
         if ("color".equalsIgnoreCase(s)) {
            TextFormatting textformatting = TextFormatting.getValueByName(s1);
            if (textformatting == null || textformatting.isFancyStyling()) {
               throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceStringFromCollection(TextFormatting.getValidValues(true, false))});
            }

            scoreplayerteam.setColor(textformatting);
            scoreplayerteam.setPrefix(textformatting.toString());
            scoreplayerteam.setSuffix(TextFormatting.RESET.toString());
         } else if ("friendlyfire".equalsIgnoreCase(s)) {
            if (!"true".equalsIgnoreCase(s1) && !"false".equalsIgnoreCase(s1)) {
               throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceStringFromCollection(Arrays.asList("true", "false"))});
            }

            scoreplayerteam.setAllowFriendlyFire("true".equalsIgnoreCase(s1));
         } else if ("seeFriendlyInvisibles".equalsIgnoreCase(s)) {
            if (!"true".equalsIgnoreCase(s1) && !"false".equalsIgnoreCase(s1)) {
               throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceStringFromCollection(Arrays.asList("true", "false"))});
            }

            scoreplayerteam.setSeeFriendlyInvisiblesEnabled("true".equalsIgnoreCase(s1));
         } else {
            WD team$enumvisible1;
            if ("nametagVisibility".equalsIgnoreCase(s)) {
               team$enumvisible1 = WD.getByName(s1);
               if (team$enumvisible1 == null) {
                  throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceString(WD.getNames())});
               }

               scoreplayerteam.setNameTagVisibility(team$enumvisible1);
            } else if ("deathMessageVisibility".equalsIgnoreCase(s)) {
               team$enumvisible1 = WD.getByName(s1);
               if (team$enumvisible1 == null) {
                  throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceString(WD.getNames())});
               }

               scoreplayerteam.setDeathMessageVisibility(team$enumvisible1);
            } else if ("collisionRule".equalsIgnoreCase(s)) {
               WC team$collisionrule = WC.getByName(s1);
               if (team$collisionrule == null) {
                  throw new Ej("commands.scoreboard.teams.option.noValue", new Object[]{s, joinNiceString(WC.getNames())});
               }

               scoreplayerteam.setCollisionRule(team$collisionrule);
            }
         }

         notifyCommandListener(sender, this, "commands.scoreboard.teams.option.success", new Object[]{s, scoreplayerteam.getName(), s1});
      }

   }

   protected void removeTeam(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      WA scoreplayerteam = this.convertToTeam(args[startIndex], server);
      if (scoreplayerteam != null) {
         scoreboard.removeTeam(scoreplayerteam);
         notifyCommandListener(sender, this, "commands.scoreboard.teams.remove.success", new Object[]{scoreplayerteam.getName()});
      }

   }

   protected void listTeams(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      if (args.length > startIndex) {
         WA scoreplayerteam = this.convertToTeam(args[startIndex], server);
         if (scoreplayerteam == null) {
            return;
         }

         Collection<String> collection = scoreplayerteam.getMembershipCollection();
         sender.setCommandStat(CK.QUERY_RESULT, collection.size());
         if (collection.isEmpty()) {
            throw new Ct("commands.scoreboard.teams.list.player.empty", new Object[]{scoreplayerteam.getName()});
         }

         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("commands.scoreboard.teams.list.player.count", new Object[]{collection.size(), scoreplayerteam.getName()});
         textcomponenttranslation.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation);
         sender.sendMessage(new TextComponentString(joinNiceString(collection.toArray())));
      } else {
         Collection<WA> collection1 = scoreboard.getTeams();
         sender.setCommandStat(CK.QUERY_RESULT, collection1.size());
         if (collection1.isEmpty()) {
            throw new Ct("commands.scoreboard.teams.list.empty", new Object[0]);
         }

         TextComponentTranslation textcomponenttranslation1 = new TextComponentTranslation("commands.scoreboard.teams.list.count", new Object[]{collection1.size()});
         textcomponenttranslation1.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation1);
         Iterator var12 = collection1.iterator();

         while(var12.hasNext()) {
            WA scoreplayerteam1 = (WA)var12.next();
            sender.sendMessage(new TextComponentTranslation("commands.scoreboard.teams.list.entry", new Object[]{scoreplayerteam1.getName(), scoreplayerteam1.getDisplayName(), scoreplayerteam1.getMembershipCollection().size()}));
         }
      }

   }

   protected void joinTeam(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = args[startIndex++];
      Set<String> set = Sets.newHashSet();
      Set<String> set1 = Sets.newHashSet();
      String s1;
      if (sender instanceof ME && startIndex == args.length) {
         s1 = getCommandSenderAsPlayer(sender).getName();
         if (scoreboard.addPlayerToTeam(s1, s)) {
            set.add(s1);
         } else {
            set1.add(s1);
         }
      } else {
         label50:
         while(true) {
            while(true) {
               if (startIndex >= args.length) {
                  break label50;
               }

               s1 = args[startIndex++];
               if (Ds.isSelector(s1)) {
                  Iterator var13 = getEntityList(server, sender, s1).iterator();

                  while(var13.hasNext()) {
                     Ig entity = (Ig)var13.next();
                     String s3 = getEntityName(server, sender, entity.getCachedUniqueIdString());
                     if (scoreboard.addPlayerToTeam(s3, s)) {
                        set.add(s3);
                     } else {
                        set1.add(s3);
                     }
                  }
               } else {
                  String s2 = getEntityName(server, sender, s1);
                  if (scoreboard.addPlayerToTeam(s2, s)) {
                     set.add(s2);
                  } else {
                     set1.add(s2);
                  }
               }
            }
         }
      }

      if (!set.isEmpty()) {
         sender.setCommandStat(CK.AFFECTED_ENTITIES, set.size());
         notifyCommandListener(sender, this, "commands.scoreboard.teams.join.success", new Object[]{set.size(), s, joinNiceString(set.toArray(new String[set.size()]))});
      }

      if (!set1.isEmpty()) {
         throw new Ct("commands.scoreboard.teams.join.failure", new Object[]{set1.size(), s, joinNiceString(set1.toArray(new String[set1.size()]))});
      }
   }

   protected void leaveTeam(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      Set<String> set = Sets.newHashSet();
      Set<String> set1 = Sets.newHashSet();
      String s;
      if (sender instanceof ME && startIndex == args.length) {
         s = getCommandSenderAsPlayer(sender).getName();
         if (scoreboard.removePlayerFromTeams(s)) {
            set.add(s);
         } else {
            set1.add(s);
         }
      } else {
         label50:
         while(true) {
            while(true) {
               if (startIndex >= args.length) {
                  break label50;
               }

               s = args[startIndex++];
               if (Ds.isSelector(s)) {
                  Iterator var12 = getEntityList(server, sender, s).iterator();

                  while(var12.hasNext()) {
                     Ig entity = (Ig)var12.next();
                     String s2 = getEntityName(server, sender, entity.getCachedUniqueIdString());
                     if (scoreboard.removePlayerFromTeams(s2)) {
                        set.add(s2);
                     } else {
                        set1.add(s2);
                     }
                  }
               } else {
                  String s1 = getEntityName(server, sender, s);
                  if (scoreboard.removePlayerFromTeams(s1)) {
                     set.add(s1);
                  } else {
                     set1.add(s1);
                  }
               }
            }
         }
      }

      if (!set.isEmpty()) {
         sender.setCommandStat(CK.AFFECTED_ENTITIES, set.size());
         notifyCommandListener(sender, this, "commands.scoreboard.teams.leave.success", new Object[]{set.size(), joinNiceString(set.toArray(new String[set.size()]))});
      }

      if (!set1.isEmpty()) {
         throw new Ct("commands.scoreboard.teams.leave.failure", new Object[]{set1.size(), joinNiceString(set1.toArray(new String[set1.size()]))});
      }
   }

   protected void emptyTeam(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      WA scoreplayerteam = this.convertToTeam(args[startIndex], server);
      if (scoreplayerteam != null) {
         Collection<String> collection = Lists.newArrayList(scoreplayerteam.getMembershipCollection());
         sender.setCommandStat(CK.AFFECTED_ENTITIES, collection.size());
         if (collection.isEmpty()) {
            throw new Ct("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{scoreplayerteam.getName()});
         }

         Iterator var8 = collection.iterator();

         while(var8.hasNext()) {
            String s = (String)var8.next();
            scoreboard.removePlayerFromTeam(s, scoreplayerteam);
         }

         notifyCommandListener(sender, this, "commands.scoreboard.teams.empty.success", new Object[]{collection.size(), scoreplayerteam.getName()});
      }

   }

   protected void removeObjective(DB sender, String name, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      Wz scoreobjective = this.convertToObjective(name, false, server);
      scoreboard.removeObjective(scoreobjective);
      notifyCommandListener(sender, this, "commands.scoreboard.objectives.remove.success", new Object[]{name});
   }

   protected void listObjectives(DB sender, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      Collection<Wz> collection = scoreboard.getScoreObjectives();
      if (collection.isEmpty()) {
         throw new Ct("commands.scoreboard.objectives.list.empty", new Object[0]);
      } else {
         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("commands.scoreboard.objectives.list.count", new Object[]{collection.size()});
         textcomponenttranslation.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation);
         Iterator var6 = collection.iterator();

         while(var6.hasNext()) {
            Wz scoreobjective = (Wz)var6.next();
            sender.sendMessage(new TextComponentTranslation("commands.scoreboard.objectives.list.entry", new Object[]{scoreobjective.getName(), scoreobjective.getDisplayName(), scoreobjective.getCriteria().getName()}));
         }

      }
   }

   protected void setDisplayObjective(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = args[startIndex++];
      int i = Ws.getObjectiveDisplaySlotNumber(s);
      Wz scoreobjective = null;
      if (args.length == 4) {
         scoreobjective = this.convertToObjective(args[startIndex], false, server);
      }

      if (i < 0) {
         throw new Ct("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{s});
      } else {
         scoreboard.setObjectiveInDisplaySlot(i, scoreobjective);
         if (scoreobjective != null) {
            notifyCommandListener(sender, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{Ws.getObjectiveDisplaySlot(i), scoreobjective.getName()});
         } else {
            notifyCommandListener(sender, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{Ws.getObjectiveDisplaySlot(i)});
         }

      }
   }

   protected void listPlayers(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      if (args.length > startIndex) {
         String s = getEntityName(server, sender, args[startIndex]);
         Map<Wz, Wr> map = scoreboard.getObjectivesForEntity(s);
         sender.setCommandStat(CK.QUERY_RESULT, map.size());
         if (map.isEmpty()) {
            throw new Ct("commands.scoreboard.players.list.player.empty", new Object[]{s});
         }

         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("commands.scoreboard.players.list.player.count", new Object[]{map.size(), s});
         textcomponenttranslation.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation);
         Iterator var9 = map.values().iterator();

         while(var9.hasNext()) {
            Wr score = (Wr)var9.next();
            sender.sendMessage(new TextComponentTranslation("commands.scoreboard.players.list.player.entry", new Object[]{score.getScorePoints(), score.getObjective().getDisplayName(), score.getObjective().getName()}));
         }
      } else {
         Collection<String> collection = scoreboard.getObjectiveNames();
         sender.setCommandStat(CK.QUERY_RESULT, collection.size());
         if (collection.isEmpty()) {
            throw new Ct("commands.scoreboard.players.list.empty", new Object[0]);
         }

         TextComponentTranslation textcomponenttranslation1 = new TextComponentTranslation("commands.scoreboard.players.list.count", new Object[]{collection.size()});
         textcomponenttranslation1.getStyle().setColor(TextFormatting.DARK_GREEN);
         sender.sendMessage(textcomponenttranslation1);
         sender.sendMessage(new TextComponentString(joinNiceString(collection.toArray())));
      }

   }

   protected void addPlayerScore(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      String s = args[startIndex - 1];
      int i = startIndex;
      String s1 = getEntityName(server, sender, args[startIndex++]);
      if (s1.length() > 40) {
         throw new Ei("commands.scoreboard.players.name.tooLong", new Object[]{s1, 40});
      } else {
         Wz scoreobjective = this.convertToObjective(args[startIndex++], true, server);
         int j = "set".equalsIgnoreCase(s) ? parseInt(args[startIndex++]) : parseInt(args[startIndex++], 0);
         if (args.length > startIndex) {
            Ig entity = getEntity(server, sender, args[i]);

            try {
               QQ nbttagcompound = QG.getTagFromJson(buildString(args, startIndex));
               QQ nbttagcompound1 = entityToNBT(entity);
               if (!Rb.areNBTEquals(nbttagcompound, nbttagcompound1, true)) {
                  throw new Ct("commands.scoreboard.players.set.tagMismatch", new Object[]{s1});
               }
            } catch (QI var13) {
               QI nbtexception = var13;
               throw new Ct("commands.scoreboard.players.set.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         Ws scoreboard = this.getScoreboard(server);
         Wr score = scoreboard.getOrCreateScore(s1, scoreobjective);
         if ("set".equalsIgnoreCase(s)) {
            score.setScorePoints(j);
         } else if ("add".equalsIgnoreCase(s)) {
            score.increaseScore(j);
         } else {
            score.decreaseScore(j);
         }

         notifyCommandListener(sender, this, "commands.scoreboard.players.set.success", new Object[]{scoreobjective.getName(), s1, score.getScorePoints()});
      }
   }

   protected void resetPlayerScore(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = getEntityName(server, sender, args[startIndex++]);
      if (args.length > startIndex) {
         Wz scoreobjective = this.convertToObjective(args[startIndex++], false, server);
         scoreboard.removeObjectiveFromEntity(s, scoreobjective);
         notifyCommandListener(sender, this, "commands.scoreboard.players.resetscore.success", new Object[]{scoreobjective.getName(), s});
      } else {
         scoreboard.removeObjectiveFromEntity(s, (Wz)null);
         notifyCommandListener(sender, this, "commands.scoreboard.players.reset.success", new Object[]{s});
      }

   }

   protected void enablePlayerTrigger(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = getPlayerName(server, sender, args[startIndex++]);
      if (s.length() > 40) {
         throw new Ei("commands.scoreboard.players.name.tooLong", new Object[]{s, 40});
      } else {
         Wz scoreobjective = this.convertToObjective(args[startIndex], false, server);
         if (scoreobjective.getCriteria() != Wo.TRIGGER) {
            throw new Ct("commands.scoreboard.players.enable.noTrigger", new Object[]{scoreobjective.getName()});
         } else {
            Wr score = scoreboard.getOrCreateScore(s, scoreobjective);
            score.setLocked(false);
            notifyCommandListener(sender, this, "commands.scoreboard.players.enable.success", new Object[]{scoreobjective.getName(), s});
         }
      }
   }

   protected void testPlayerScore(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = getEntityName(server, sender, args[startIndex++]);
      if (s.length() > 40) {
         throw new Ei("commands.scoreboard.players.name.tooLong", new Object[]{s, 40});
      } else {
         Wz scoreobjective = this.convertToObjective(args[startIndex++], false, server);
         if (!scoreboard.entityHasObjective(s, scoreobjective)) {
            throw new Ct("commands.scoreboard.players.test.notFound", new Object[]{scoreobjective.getName(), s});
         } else {
            int i = args[startIndex].equals("*") ? Integer.MIN_VALUE : parseInt(args[startIndex]);
            ++startIndex;
            int j = startIndex < args.length && !args[startIndex].equals("*") ? parseInt(args[startIndex], i) : Integer.MAX_VALUE;
            Wr score = scoreboard.getOrCreateScore(s, scoreobjective);
            if (score.getScorePoints() >= i && score.getScorePoints() <= j) {
               notifyCommandListener(sender, this, "commands.scoreboard.players.test.success", new Object[]{score.getScorePoints(), i, j});
            } else {
               throw new Ct("commands.scoreboard.players.test.failed", new Object[]{score.getScorePoints(), i, j});
            }
         }
      }
   }

   protected void applyPlayerOperation(DB sender, String[] args, int startIndex, Xx server) throws Ct {
      Ws scoreboard = this.getScoreboard(server);
      String s = getEntityName(server, sender, args[startIndex++]);
      Wz scoreobjective = this.convertToObjective(args[startIndex++], true, server);
      String s1 = args[startIndex++];
      String s2 = getEntityName(server, sender, args[startIndex++]);
      Wz scoreobjective1 = this.convertToObjective(args[startIndex], false, server);
      if (s.length() > 40) {
         throw new Ei("commands.scoreboard.players.name.tooLong", new Object[]{s, 40});
      } else if (s2.length() > 40) {
         throw new Ei("commands.scoreboard.players.name.tooLong", new Object[]{s2, 40});
      } else {
         Wr score = scoreboard.getOrCreateScore(s, scoreobjective);
         if (!scoreboard.entityHasObjective(s2, scoreobjective1)) {
            throw new Ct("commands.scoreboard.players.operation.notFound", new Object[]{scoreobjective1.getName(), s2});
         } else {
            Wr score1 = scoreboard.getOrCreateScore(s2, scoreobjective1);
            if ("+=".equals(s1)) {
               score.setScorePoints(score.getScorePoints() + score1.getScorePoints());
            } else if ("-=".equals(s1)) {
               score.setScorePoints(score.getScorePoints() - score1.getScorePoints());
            } else if ("*=".equals(s1)) {
               score.setScorePoints(score.getScorePoints() * score1.getScorePoints());
            } else if ("/=".equals(s1)) {
               if (score1.getScorePoints() != 0) {
                  score.setScorePoints(score.getScorePoints() / score1.getScorePoints());
               }
            } else if ("%=".equals(s1)) {
               if (score1.getScorePoints() != 0) {
                  score.setScorePoints(score.getScorePoints() % score1.getScorePoints());
               }
            } else if ("=".equals(s1)) {
               score.setScorePoints(score1.getScorePoints());
            } else if ("<".equals(s1)) {
               score.setScorePoints(Math.min(score.getScorePoints(), score1.getScorePoints()));
            } else if (">".equals(s1)) {
               score.setScorePoints(Math.max(score.getScorePoints(), score1.getScorePoints()));
            } else {
               if (!"><".equals(s1)) {
                  throw new Ct("commands.scoreboard.players.operation.invalidOperation", new Object[]{s1});
               }

               int i = score.getScorePoints();
               score.setScorePoints(score1.getScorePoints());
               score1.setScorePoints(i);
            }

            notifyCommandListener(sender, this, "commands.scoreboard.players.operation.success", new Object[0]);
         }
      }
   }

   protected void applyPlayerTag(Xx server, DB sender, String[] args, int startIndex) throws Ct {
      String s = getEntityName(server, sender, args[startIndex]);
      Ig entity = getEntity(server, sender, args[startIndex++]);
      String s1 = args[startIndex++];
      Set<String> set = entity.getTags();
      if ("list".equals(s1)) {
         if (!set.isEmpty()) {
            TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("commands.scoreboard.players.tag.list", new Object[]{s});
            textcomponenttranslation.getStyle().setColor(TextFormatting.DARK_GREEN);
            sender.sendMessage(textcomponenttranslation);
            sender.sendMessage(new TextComponentString(joinNiceString(set.toArray())));
         }

         sender.setCommandStat(CK.QUERY_RESULT, set.size());
      } else {
         if (args.length < 5) {
            throw new Ej("commands.scoreboard.players.tag.usage", new Object[0]);
         }

         String s2 = args[startIndex++];
         if (args.length > startIndex) {
            try {
               QQ nbttagcompound = QG.getTagFromJson(buildString(args, startIndex));
               QQ nbttagcompound1 = entityToNBT(entity);
               if (!Rb.areNBTEquals(nbttagcompound, nbttagcompound1, true)) {
                  throw new Ct("commands.scoreboard.players.tag.tagMismatch", new Object[]{s});
               }
            } catch (QI var12) {
               QI nbtexception = var12;
               throw new Ct("commands.scoreboard.players.tag.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         if ("add".equals(s1)) {
            if (!entity.addTag(s2)) {
               throw new Ct("commands.scoreboard.players.tag.tooMany", new Object[]{1024});
            }

            notifyCommandListener(sender, this, "commands.scoreboard.players.tag.success.add", new Object[]{s2});
         } else {
            if (!"remove".equals(s1)) {
               throw new Ej("commands.scoreboard.players.tag.usage", new Object[0]);
            }

            if (!entity.removeTag(s2)) {
               throw new Ct("commands.scoreboard.players.tag.notFound", new Object[]{s2});
            }

            notifyCommandListener(sender, this, "commands.scoreboard.players.tag.success.remove", new Object[]{s2});
         }
      }

   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"objectives", "players", "teams"});
      } else {
         if ("objectives".equalsIgnoreCase(args[0])) {
            if (args.length == 2) {
               return getListOfStringsMatchingLastWord(args, new String[]{"list", "add", "remove", "setdisplay"});
            }

            if ("add".equalsIgnoreCase(args[1])) {
               if (args.length == 4) {
                  Set<String> set = Wo.INSTANCES.keySet();
                  return getListOfStringsMatchingLastWord(args, set);
               }
            } else if ("remove".equalsIgnoreCase(args[1])) {
               if (args.length == 3) {
                  return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(false, server));
               }
            } else if ("setdisplay".equalsIgnoreCase(args[1])) {
               if (args.length == 3) {
                  return getListOfStringsMatchingLastWord(args, Ws.getDisplaySlotStrings());
               }

               if (args.length == 4) {
                  return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(false, server));
               }
            }
         } else if ("players".equalsIgnoreCase(args[0])) {
            if (args.length == 2) {
               return getListOfStringsMatchingLastWord(args, new String[]{"set", "add", "remove", "reset", "list", "enable", "test", "operation", "tag"});
            }

            if (!"set".equalsIgnoreCase(args[1]) && !"add".equalsIgnoreCase(args[1]) && !"remove".equalsIgnoreCase(args[1]) && !"reset".equalsIgnoreCase(args[1])) {
               if ("enable".equalsIgnoreCase(args[1])) {
                  if (args.length == 3) {
                     return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
                  }

                  if (args.length == 4) {
                     return getListOfStringsMatchingLastWord(args, this.getTriggerNames(server));
                  }
               } else if (!"list".equalsIgnoreCase(args[1]) && !"test".equalsIgnoreCase(args[1])) {
                  if ("operation".equalsIgnoreCase(args[1])) {
                     if (args.length == 3) {
                        return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getObjectiveNames());
                     }

                     if (args.length == 4) {
                        return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(true, server));
                     }

                     if (args.length == 5) {
                        return getListOfStringsMatchingLastWord(args, new String[]{"+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><"});
                     }

                     if (args.length == 6) {
                        return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
                     }

                     if (args.length == 7) {
                        return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(false, server));
                     }
                  } else if ("tag".equalsIgnoreCase(args[1])) {
                     if (args.length == 3) {
                        return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getObjectiveNames());
                     }

                     if (args.length == 4) {
                        return getListOfStringsMatchingLastWord(args, new String[]{"add", "remove", "list"});
                     }
                  }
               } else {
                  if (args.length == 3) {
                     return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getObjectiveNames());
                  }

                  if (args.length == 4 && "test".equalsIgnoreCase(args[1])) {
                     return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(false, server));
                  }
               }
            } else {
               if (args.length == 3) {
                  return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
               }

               if (args.length == 4) {
                  return getListOfStringsMatchingLastWord(args, this.getObjectiveNames(true, server));
               }
            }
         } else if ("teams".equalsIgnoreCase(args[0])) {
            if (args.length == 2) {
               return getListOfStringsMatchingLastWord(args, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
            }

            if ("join".equalsIgnoreCase(args[1])) {
               if (args.length == 3) {
                  return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getTeamNames());
               }

               if (args.length >= 4) {
                  return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
               }
            } else {
               if ("leave".equalsIgnoreCase(args[1])) {
                  return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
               }

               if (!"empty".equalsIgnoreCase(args[1]) && !"list".equalsIgnoreCase(args[1]) && !"remove".equalsIgnoreCase(args[1])) {
                  if ("option".equalsIgnoreCase(args[1])) {
                     if (args.length == 3) {
                        return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getTeamNames());
                     }

                     if (args.length == 4) {
                        return getListOfStringsMatchingLastWord(args, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility", "collisionRule"});
                     }

                     if (args.length == 5) {
                        if ("color".equalsIgnoreCase(args[3])) {
                           return getListOfStringsMatchingLastWord(args, TextFormatting.getValidValues(true, false));
                        }

                        if ("nametagVisibility".equalsIgnoreCase(args[3]) || "deathMessageVisibility".equalsIgnoreCase(args[3])) {
                           return getListOfStringsMatchingLastWord(args, WD.getNames());
                        }

                        if ("collisionRule".equalsIgnoreCase(args[3])) {
                           return getListOfStringsMatchingLastWord(args, WC.getNames());
                        }

                        if ("friendlyfire".equalsIgnoreCase(args[3]) || "seeFriendlyInvisibles".equalsIgnoreCase(args[3])) {
                           return getListOfStringsMatchingLastWord(args, new String[]{"true", "false"});
                        }
                     }
                  }
               } else if (args.length == 3) {
                  return getListOfStringsMatchingLastWord(args, this.getScoreboard(server).getTeamNames());
               }
            }
         }

         return Collections.emptyList();
      }
   }

   protected List<String> getObjectiveNames(boolean writableOnly, Xx server) {
      Collection<Wz> collection = this.getScoreboard(server).getScoreObjectives();
      List<String> list = Lists.newArrayList();
      Iterator var5 = collection.iterator();

      while(true) {
         Wz scoreobjective;
         do {
            if (!var5.hasNext()) {
               return list;
            }

            scoreobjective = (Wz)var5.next();
         } while(writableOnly && scoreobjective.getCriteria().isReadOnly());

         list.add(scoreobjective.getName());
      }
   }

   protected List<String> getTriggerNames(Xx server) {
      Collection<Wz> collection = this.getScoreboard(server).getScoreObjectives();
      List<String> list = Lists.newArrayList();
      Iterator var4 = collection.iterator();

      while(var4.hasNext()) {
         Wz scoreobjective = (Wz)var4.next();
         if (scoreobjective.getCriteria() == Wo.TRIGGER) {
            list.add(scoreobjective.getName());
         }
      }

      return list;
   }

   public boolean isUsernameIndex(String[] args, int index) {
      if (!"players".equalsIgnoreCase(args[0])) {
         if ("teams".equalsIgnoreCase(args[0])) {
            return index == 2;
         } else {
            return false;
         }
      } else if (args.length > 1 && "operation".equalsIgnoreCase(args[1])) {
         return index == 2 || index == 5;
      } else {
         return index == 2;
      }
   }
}
