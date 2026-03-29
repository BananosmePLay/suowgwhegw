package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;

public class CS extends Ch {
   public CS() {
   }

   public String getName() {
      return "spreadplayers";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.spreadplayers.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 6) {
         throw new Ej("commands.spreadplayers.usage", new Object[0]);
      } else {
         int i = 0;
         BlockPos blockpos = sender.getPosition();
         double d0 = parseDouble((double)blockpos.getX(), args[i++], true);
         double d1 = parseDouble((double)blockpos.getZ(), args[i++], true);
         double d2 = parseDouble(args[i++], 0.0);
         double d3 = parseDouble(args[i++], d2 + 1.0);
         boolean flag = parseBoolean(args[i++]);
         List<Ig> list = Lists.newArrayList();

         while(i < args.length) {
            String s = args[i++];
            if (Ds.isSelector(s)) {
               List<Ig> list1 = Ds.matchEntities(sender, s, Ig.class);
               if (list1.isEmpty()) {
                  throw new Dd("commands.generic.selector.notFound", new Object[]{s});
               }

               list.addAll(list1);
            } else {
               ME entityplayer = server.getPlayerList().getPlayerByUsername(s);
               if (entityplayer == null) {
                  throw new DF("commands.generic.player.notFound", new Object[]{s});
               }

               list.add(entityplayer);
            }
         }

         sender.setCommandStat(CK.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new Dd("commands.spreadplayers.noop");
         } else {
            sender.sendMessage(new TextComponentTranslation("commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[]{list.size(), d3, d0, d1, d2}));
            this.spread(sender, list, new CR(d0, d1), d2, d3, ((Ig)list.get(0)).world, flag);
         }
      }
   }

   private void spread(DB sender, List<Ig> p_110669_2_, CR pos, double spreadDistance, double maxRange, bij worldIn, boolean respectTeams) throws Ct {
      Random random = new Random();
      double d0 = pos.x - maxRange;
      double d1 = pos.z - maxRange;
      double d2 = pos.x + maxRange;
      double d3 = pos.z + maxRange;
      CR[] acommandspreadplayers$position = this.createInitialPositions(random, respectTeams ? this.getNumberOfTeams(p_110669_2_) : p_110669_2_.size(), d0, d1, d2, d3);
      int i = this.spreadPositions(pos, spreadDistance, worldIn, random, d0, d1, d2, d3, acommandspreadplayers$position, respectTeams);
      double d4 = this.setPlayerPositions(p_110669_2_, worldIn, acommandspreadplayers$position, respectTeams);
      notifyCommandListener(sender, this, "commands.spreadplayers.success." + (respectTeams ? "teams" : "players"), new Object[]{acommandspreadplayers$position.length, pos.x, pos.z});
      if (acommandspreadplayers$position.length > 1) {
         sender.sendMessage(new TextComponentTranslation("commands.spreadplayers.info." + (respectTeams ? "teams" : "players"), new Object[]{String.format("%.2f", d4), i}));
      }

   }

   private int getNumberOfTeams(List<Ig> p_110667_1_) {
      Set<WE> set = Sets.newHashSet();
      Iterator var3 = p_110667_1_.iterator();

      while(var3.hasNext()) {
         Ig entity = (Ig)var3.next();
         if (entity instanceof ME) {
            set.add(entity.getTeam());
         } else {
            set.add((Object)null);
         }
      }

      return set.size();
   }

   private int spreadPositions(CR p_110668_1_, double p_110668_2_, bij worldIn, Random random, double minX, double minZ, double maxX, double maxZ, CR[] p_110668_14_, boolean respectTeams) throws Ct {
      boolean flag = true;
      double d0 = 3.4028234663852886E38;

      int i;
      for(i = 0; i < 10000 && flag; ++i) {
         flag = false;
         d0 = 3.4028234663852886E38;

         int k;
         CR commandspreadplayers$position3;
         for(int j = 0; j < p_110668_14_.length; ++j) {
            CR commandspreadplayers$position = p_110668_14_[j];
            k = 0;
            commandspreadplayers$position3 = new CR();

            for(int l = 0; l < p_110668_14_.length; ++l) {
               if (j != l) {
                  CR commandspreadplayers$position2 = p_110668_14_[l];
                  double d1 = commandspreadplayers$position.dist(commandspreadplayers$position2);
                  d0 = Math.min(d1, d0);
                  if (d1 < p_110668_2_) {
                     ++k;
                     commandspreadplayers$position3.x += commandspreadplayers$position2.x - commandspreadplayers$position.x;
                     commandspreadplayers$position3.z += commandspreadplayers$position2.z - commandspreadplayers$position.z;
                  }
               }
            }

            if (k > 0) {
               commandspreadplayers$position3.x /= (double)k;
               commandspreadplayers$position3.z /= (double)k;
               double d2 = (double)commandspreadplayers$position3.getLength();
               if (d2 > 0.0) {
                  commandspreadplayers$position3.normalize();
                  commandspreadplayers$position.moveAway(commandspreadplayers$position3);
               } else {
                  commandspreadplayers$position.randomize(random, minX, minZ, maxX, maxZ);
               }

               flag = true;
            }

            if (commandspreadplayers$position.clamp(minX, minZ, maxX, maxZ)) {
               flag = true;
            }
         }

         if (!flag) {
            CR[] var28 = p_110668_14_;
            int var29 = p_110668_14_.length;

            for(k = 0; k < var29; ++k) {
               commandspreadplayers$position3 = var28[k];
               if (!commandspreadplayers$position3.isSafe(worldIn)) {
                  commandspreadplayers$position3.randomize(random, minX, minZ, maxX, maxZ);
                  flag = true;
               }
            }
         }
      }

      if (i >= 10000) {
         throw new Ct("commands.spreadplayers.failure." + (respectTeams ? "teams" : "players"), new Object[]{p_110668_14_.length, p_110668_1_.x, p_110668_1_.z, String.format("%.2f", d0)});
      } else {
         return i;
      }
   }

   private double setPlayerPositions(List<Ig> p_110671_1_, bij worldIn, CR[] p_110671_3_, boolean p_110671_4_) {
      double d0 = 0.0;
      int i = 0;
      Map<WE, CR> map = Maps.newHashMap();

      for(int j = 0; j < p_110671_1_.size(); ++j) {
         Ig entity = (Ig)p_110671_1_.get(j);
         CR commandspreadplayers$position;
         if (p_110671_4_) {
            WE team = entity instanceof ME ? entity.getTeam() : null;
            if (!map.containsKey(team)) {
               map.put(team, p_110671_3_[i++]);
            }

            commandspreadplayers$position = (CR)map.get(team);
         } else {
            commandspreadplayers$position = p_110671_3_[i++];
         }

         entity.setPositionAndUpdate((double)((float)MathHelper.floor(commandspreadplayers$position.x) + 0.5F), (double)commandspreadplayers$position.getSpawnY(worldIn), (double)MathHelper.floor(commandspreadplayers$position.z) + 0.5);
         double d2 = Double.MAX_VALUE;
         CR[] var14 = p_110671_3_;
         int var15 = p_110671_3_.length;

         for(int var16 = 0; var16 < var15; ++var16) {
            CR commandspreadplayers$position1 = var14[var16];
            if (commandspreadplayers$position != commandspreadplayers$position1) {
               double d1 = commandspreadplayers$position.dist(commandspreadplayers$position1);
               d2 = Math.min(d1, d2);
            }
         }

         d0 += d2;
      }

      d0 /= (double)p_110671_1_.size();
      return d0;
   }

   private CR[] createInitialPositions(Random p_110670_1_, int p_110670_2_, double p_110670_3_, double p_110670_5_, double p_110670_7_, double p_110670_9_) {
      CR[] acommandspreadplayers$position = new CR[p_110670_2_];

      for(int i = 0; i < acommandspreadplayers$position.length; ++i) {
         CR commandspreadplayers$position = new CR();
         commandspreadplayers$position.randomize(p_110670_1_, p_110670_3_, p_110670_5_, p_110670_7_, p_110670_9_);
         acommandspreadplayers$position[i] = commandspreadplayers$position;
      }

      return acommandspreadplayers$position;
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length >= 1 && args.length <= 2 ? getTabCompletionCoordinateXZ(args, 0, targetPos) : Collections.emptyList();
   }
}
