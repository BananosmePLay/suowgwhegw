package neo;

import java.util.Random;
import java.util.UUID;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgQ extends bgI {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bgO[] modifiers;

   public bgQ(bgv[] conditionsIn, bgO[] modifiersIn) {
      super(conditionsIn);
      this.modifiers = modifiersIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      bgO[] var4 = this.modifiers;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         bgO setattributes$modifier = var4[var6];
         UUID uuid = bgO.access$000(setattributes$modifier);
         if (uuid == null) {
            uuid = UUID.randomUUID();
         }

         EntityEquipmentSlot entityequipmentslot = bgO.access$100(setattributes$modifier)[rand.nextInt(bgO.access$100(setattributes$modifier).length)];
         stack.addAttributeModifier(bgO.access$200(setattributes$modifier), new FW(uuid, bgO.access$300(setattributes$modifier), (double)bgO.access$400(setattributes$modifier).generateFloat(rand), bgO.access$500(setattributes$modifier)), entityequipmentslot);
      }

      return stack;
   }

   // $FF: synthetic method
   static bgO[] access$600(bgQ x0) {
      return x0.modifiers;
   }
}
