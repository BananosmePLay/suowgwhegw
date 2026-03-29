package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;

public class JA extends KW {
   public JA(bij worldIn) {
      super(worldIn);
      this.setSize(0.7F, 0.5F);
   }

   public static void registerFixesCaveSpider(DataFixer fixer) {
      Iu.registerFixesMob(fixer, JA.class);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(12.0);
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      if (super.attackEntityAsMob(entityIn)) {
         if (entityIn instanceof Iw) {
            int i = 0;
            if (this.world.getDifficulty() == baV.NORMAL) {
               i = 7;
            } else if (this.world.getDifficulty() == baV.HARD) {
               i = 15;
            }

            if (i > 0) {
               ((Iw)entityIn).addPotionEffect(new VZ(NL.POISON, i * 20, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      return livingdata;
   }

   public float getEyeHeight() {
      return 0.45F;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_CAVE_SPIDER;
   }
}
