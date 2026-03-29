package neo;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.MathHelper;

public class bhn {
   private final bhi[] lootEntries;
   private final bgv[] poolConditions;
   private final bhC rolls;
   private final bhC bonusRolls;

   public bhn(bhi[] lootEntriesIn, bgv[] poolConditionsIn, bhC rollsIn, bhC bonusRollsIn) {
      this.lootEntries = lootEntriesIn;
      this.poolConditions = poolConditionsIn;
      this.rolls = rollsIn;
      this.bonusRolls = bonusRollsIn;
   }

   protected void createLootRoll(Collection<Qy> stacks, Random rand, bhg context) {
      List<bhi> list = Lists.newArrayList();
      int i = 0;
      bhi[] var6 = this.lootEntries;
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         bhi lootentry = var6[var8];
         if (bgx.testAllConditions(lootentry.conditions, rand, context)) {
            int j = lootentry.getEffectiveWeight(context.getLuck());
            if (j > 0) {
               list.add(lootentry);
               i += j;
            }
         }
      }

      if (i != 0 && !list.isEmpty()) {
         int k = rand.nextInt(i);
         Iterator var12 = list.iterator();

         while(var12.hasNext()) {
            bhi lootentry1 = (bhi)var12.next();
            k -= lootentry1.getEffectiveWeight(context.getLuck());
            if (k < 0) {
               lootentry1.addLoot(stacks, rand, context);
               return;
            }
         }
      }

   }

   public void generateLoot(Collection<Qy> stacks, Random rand, bhg context) {
      if (bgx.testAllConditions(this.poolConditions, rand, context)) {
         int i = this.rolls.generateInt(rand) + MathHelper.floor(this.bonusRolls.generateFloat(rand) * context.getLuck());

         for(int j = 0; j < i; ++j) {
            this.createLootRoll(stacks, rand, context);
         }
      }

   }

   // $FF: synthetic method
   static bhi[] access$000(bhn x0) {
      return x0.lootEntries;
   }

   // $FF: synthetic method
   static bhC access$100(bhn x0) {
      return x0.rolls;
   }

   // $FF: synthetic method
   static bhC access$200(bhn x0) {
      return x0.bonusRolls;
   }

   // $FF: synthetic method
   static bgv[] access$300(bhn x0) {
      return x0.poolConditions;
   }
}
