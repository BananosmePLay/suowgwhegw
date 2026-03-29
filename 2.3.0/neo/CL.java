package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;

public class CL {
   private static final int NUM_RESULT_TYPES = CK.values().length;
   private static final String[] STRING_RESULT_TYPES;
   private String[] entitiesID;
   private String[] objectives;

   public CL() {
      this.entitiesID = STRING_RESULT_TYPES;
      this.objectives = STRING_RESULT_TYPES;
   }

   public void setCommandStatForSender(Xx server, final DB sender, CK typeIn, int p_184932_4_) {
      String s = this.entitiesID[typeIn.getTypeID()];
      if (s != null) {
         DB icommandsender = new DB() {
            public String getName() {
               return sender.getName();
            }

            public ITextComponent getDisplayName() {
               return sender.getDisplayName();
            }

            public void sendMessage(ITextComponent component) {
               sender.sendMessage(component);
            }

            public boolean canUseCommand(int permLevel, String commandName) {
               return true;
            }

            public BlockPos getPosition() {
               return sender.getPosition();
            }

            public Vec3d getPositionVector() {
               return sender.getPositionVector();
            }

            public bij getEntityWorld() {
               return sender.getEntityWorld();
            }

            public Ig getCommandSenderEntity() {
               return sender.getCommandSenderEntity();
            }

            public boolean sendCommandFeedback() {
               return sender.sendCommandFeedback();
            }

            public void setCommandStat(CK type, int amount) {
               sender.setCommandStat(type, amount);
            }

            public Xx getServer() {
               return sender.getServer();
            }
         };

         String s1;
         try {
            s1 = Ch.getEntityName(server, icommandsender, s);
         } catch (Ct var12) {
            return;
         }

         String s2 = this.objectives[typeIn.getTypeID()];
         if (s2 != null) {
            Ws scoreboard = sender.getEntityWorld().getScoreboard();
            Wz scoreobjective = scoreboard.getObjective(s2);
            if (scoreobjective != null && scoreboard.entityHasObjective(s1, scoreobjective)) {
               Wr score = scoreboard.getOrCreateScore(s1, scoreobjective);
               score.setScorePoints(p_184932_4_);
            }
         }
      }

   }

   public void readStatsFromNBT(QQ tagcompound) {
      if (tagcompound.hasKey("CommandStats", 10)) {
         QQ nbttagcompound = tagcompound.getCompoundTag("CommandStats");
         CK[] var3 = CK.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            CK commandresultstats$type = var3[var5];
            String s = commandresultstats$type.getTypeName() + "Name";
            String s1 = commandresultstats$type.getTypeName() + "Objective";
            if (nbttagcompound.hasKey(s, 8) && nbttagcompound.hasKey(s1, 8)) {
               String s2 = nbttagcompound.getString(s);
               String s3 = nbttagcompound.getString(s1);
               setScoreBoardStat(this, commandresultstats$type, s2, s3);
            }
         }
      }

   }

   public void writeStatsToNBT(QQ tagcompound) {
      QQ nbttagcompound = new QQ();
      CK[] var3 = CK.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         CK commandresultstats$type = var3[var5];
         String s = this.entitiesID[commandresultstats$type.getTypeID()];
         String s1 = this.objectives[commandresultstats$type.getTypeID()];
         if (s != null && s1 != null) {
            nbttagcompound.setString(commandresultstats$type.getTypeName() + "Name", s);
            nbttagcompound.setString(commandresultstats$type.getTypeName() + "Objective", s1);
         }
      }

      if (!nbttagcompound.isEmpty()) {
         tagcompound.setTag("CommandStats", nbttagcompound);
      }

   }

   public static void setScoreBoardStat(CL stats, CK resultType, @Nullable String entityID, @Nullable String objectiveName) {
      if (entityID != null && !entityID.isEmpty() && objectiveName != null && !objectiveName.isEmpty()) {
         if (stats.entitiesID == STRING_RESULT_TYPES || stats.objectives == STRING_RESULT_TYPES) {
            stats.entitiesID = new String[NUM_RESULT_TYPES];
            stats.objectives = new String[NUM_RESULT_TYPES];
         }

         stats.entitiesID[resultType.getTypeID()] = entityID;
         stats.objectives[resultType.getTypeID()] = objectiveName;
      } else {
         removeScoreBoardStat(stats, resultType);
      }

   }

   private static void removeScoreBoardStat(CL resultStatsIn, CK resultTypeIn) {
      if (resultStatsIn.entitiesID != STRING_RESULT_TYPES && resultStatsIn.objectives != STRING_RESULT_TYPES) {
         resultStatsIn.entitiesID[resultTypeIn.getTypeID()] = null;
         resultStatsIn.objectives[resultTypeIn.getTypeID()] = null;
         boolean flag = true;
         CK[] var3 = CK.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            CK commandresultstats$type = var3[var5];
            if (resultStatsIn.entitiesID[commandresultstats$type.getTypeID()] != null && resultStatsIn.objectives[commandresultstats$type.getTypeID()] != null) {
               flag = false;
               break;
            }
         }

         if (flag) {
            resultStatsIn.entitiesID = STRING_RESULT_TYPES;
            resultStatsIn.objectives = STRING_RESULT_TYPES;
         }
      }

   }

   public void addAllStats(CL resultStatsIn) {
      CK[] var2 = CK.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         CK commandresultstats$type = var2[var4];
         setScoreBoardStat(this, commandresultstats$type, resultStatsIn.entitiesID[commandresultstats$type.getTypeID()], resultStatsIn.objectives[commandresultstats$type.getTypeID()]);
      }

   }

   static {
      STRING_RESULT_TYPES = new String[NUM_RESULT_TYPES];
   }
}
