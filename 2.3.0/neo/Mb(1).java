package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Mb extends Ly {
   private static final Rd<Byte> DYE_COLOR;
   private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container() {
      public boolean canInteractWith(ME playerIn) {
         return false;
      }
   }, 2, 1);
   private static final Map<Om, float[]> DYE_TO_RGB;
   private int sheepTimer;
   private Go entityAIEatGrass;

   private static float[] createSheepColor(Om p_192020_0_) {
      float[] afloat = p_192020_0_.getColorComponentValues();
      float f = 0.75F;
      return new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
   }

   public static float[] getDyeRgb(Om dyeColor) {
      return (float[])DYE_TO_RGB.get(dyeColor);
   }

   public Mb(bij worldIn) {
      super(worldIn);
      this.setSize(0.9F, 1.3F);
      this.inventoryCrafting.setInventorySlotContents(0, new Qy(NK.DYE));
      this.inventoryCrafting.setInventorySlotContents(1, new Qy(NK.DYE));
   }

   protected void initEntityAI() {
      this.entityAIEatGrass = new Go(this);
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new GX(this, 1.25));
      this.tasks.addTask(2, new GI(this, 1.0));
      this.tasks.addTask(3, new Hj(this, 1.1, NK.WHEAT, false));
      this.tasks.addTask(4, new Gz(this, 1.1));
      this.tasks.addTask(5, this.entityAIEatGrass);
      this.tasks.addTask(6, new Ho(this, 1.0));
      this.tasks.addTask(7, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(8, new GH(this));
   }

   protected void updateAITasks() {
      this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
      super.updateAITasks();
   }

   public void onLivingUpdate() {
      if (this.world.isRemote) {
         this.sheepTimer = Math.max(0, this.sheepTimer - 1);
      }

      super.onLivingUpdate();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(8.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.23000000417232513);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(DYE_COLOR, (byte)0);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      if (this.getSheared()) {
         return bhq.ENTITIES_SHEEP;
      } else {
         switch (this.getFleeceColor()) {
            case WHITE:
            default:
               return bhq.ENTITIES_SHEEP_WHITE;
            case ORANGE:
               return bhq.ENTITIES_SHEEP_ORANGE;
            case MAGENTA:
               return bhq.ENTITIES_SHEEP_MAGENTA;
            case LIGHT_BLUE:
               return bhq.ENTITIES_SHEEP_LIGHT_BLUE;
            case YELLOW:
               return bhq.ENTITIES_SHEEP_YELLOW;
            case LIME:
               return bhq.ENTITIES_SHEEP_LIME;
            case PINK:
               return bhq.ENTITIES_SHEEP_PINK;
            case GRAY:
               return bhq.ENTITIES_SHEEP_GRAY;
            case SILVER:
               return bhq.ENTITIES_SHEEP_SILVER;
            case CYAN:
               return bhq.ENTITIES_SHEEP_CYAN;
            case PURPLE:
               return bhq.ENTITIES_SHEEP_PURPLE;
            case BLUE:
               return bhq.ENTITIES_SHEEP_BLUE;
            case BROWN:
               return bhq.ENTITIES_SHEEP_BROWN;
            case GREEN:
               return bhq.ENTITIES_SHEEP_GREEN;
            case RED:
               return bhq.ENTITIES_SHEEP_RED;
            case BLACK:
               return bhq.ENTITIES_SHEEP_BLACK;
         }
      }
   }

   public void handleStatusUpdate(byte id) {
      if (id == 10) {
         this.sheepTimer = 40;
      } else {
         super.handleStatusUpdate(id);
      }

   }

   public float getHeadRotationPointY(float p_70894_1_) {
      if (this.sheepTimer <= 0) {
         return 0.0F;
      } else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
         return 1.0F;
      } else {
         return this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F;
      }
   }

   public float getHeadRotationAngleX(float p_70890_1_) {
      if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
         float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
         return 0.62831855F + 0.2199115F * MathHelper.sin(f * 28.7F);
      } else {
         return this.sheepTimer > 0 ? 0.62831855F : this.rotationPitch * 0.017453292F;
      }
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.SHEARS && !this.getSheared() && !this.isChild()) {
         if (!this.world.isRemote) {
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);

            for(int j = 0; j < i; ++j) {
               IY entityitem = this.entityDropItem(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, this.getFleeceColor().getMetadata()), 1.0F);
               entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
               entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
               entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
            }
         }

         itemstack.damageItem(1, player);
         this.playSound(NO.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
      }

      return super.processInteract(player, hand);
   }

   public static void registerFixesSheep(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Mb.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Sheared", this.getSheared());
      compound.setByte("Color", (byte)this.getFleeceColor().getMetadata());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setSheared(compound.getBoolean("Sheared"));
      this.setFleeceColor(Om.byMetadata(compound.getByte("Color")));
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_SHEEP_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_SHEEP_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_SHEEP_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
   }

   public Om getFleeceColor() {
      return Om.byMetadata((Byte)this.dataManager.get(DYE_COLOR) & 15);
   }

   public void setFleeceColor(Om color) {
      byte b0 = (Byte)this.dataManager.get(DYE_COLOR);
      this.dataManager.set(DYE_COLOR, (byte)(b0 & 240 | color.getMetadata() & 15));
   }

   public boolean getSheared() {
      return ((Byte)this.dataManager.get(DYE_COLOR) & 16) != 0;
   }

   public void setSheared(boolean sheared) {
      byte b0 = (Byte)this.dataManager.get(DYE_COLOR);
      if (sheared) {
         this.dataManager.set(DYE_COLOR, (byte)(b0 | 16));
      } else {
         this.dataManager.set(DYE_COLOR, (byte)(b0 & -17));
      }

   }

   public static Om getRandomSheepColor(Random random) {
      int i = random.nextInt(100);
      if (i < 5) {
         return Om.BLACK;
      } else if (i < 10) {
         return Om.GRAY;
      } else if (i < 15) {
         return Om.SILVER;
      } else if (i < 18) {
         return Om.BROWN;
      } else {
         return random.nextInt(500) == 0 ? Om.PINK : Om.WHITE;
      }
   }

   public Mb createChild(Ih ageable) {
      Mb entitysheep = (Mb)ageable;
      Mb entitysheep1 = new Mb(this.world);
      entitysheep1.setFleeceColor(this.getDyeColorMixFromParents(this, entitysheep));
      return entitysheep1;
   }

   public void eatGrassBonus() {
      this.setSheared(false);
      if (this.isChild()) {
         this.addGrowth(60);
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      livingdata = super.onInitialSpawn(difficulty, livingdata);
      this.setFleeceColor(getRandomSheepColor(this.world.rand));
      return livingdata;
   }

   private Om getDyeColorMixFromParents(Ly father, Ly mother) {
      int i = ((Mb)father).getFleeceColor().getDyeDamage();
      int j = ((Mb)mother).getFleeceColor().getDyeDamage();
      this.inventoryCrafting.getStackInSlot(0).setItemDamage(i);
      this.inventoryCrafting.getStackInSlot(1).setItemDamage(j);
      Qy itemstack = NP.findMatchingResult(this.inventoryCrafting, ((Mb)father).world);
      int k;
      if (itemstack.getItem() == NK.DYE) {
         k = itemstack.getMetadata();
      } else {
         k = this.world.rand.nextBoolean() ? i : j;
      }

      return Om.byDyeDamage(k);
   }

   public float getEyeHeight() {
      return 0.95F * this.height;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   static {
      DYE_COLOR = Rv.createKey(Mb.class, Rt.BYTE);
      DYE_TO_RGB = Maps.newEnumMap(Om.class);
      Om[] var0 = Om.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Om enumdyecolor = var0[var2];
         DYE_TO_RGB.put(enumdyecolor, createSheepColor(enumdyecolor));
      }

      DYE_TO_RGB.put(Om.WHITE, new float[]{0.9019608F, 0.9019608F, 0.9019608F});
   }
}
