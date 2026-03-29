package neo;

import com.google.common.base.Optional;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class KD extends JY implements Lo {
   private static final UUID COVERED_ARMOR_BONUS_ID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
   private static final FW COVERED_ARMOR_BONUS_MODIFIER;
   protected static final Rd<EnumFacing> ATTACHED_FACE;
   protected static final Rd<Optional<BlockPos>> ATTACHED_BLOCK_POS;
   protected static final Rd<Byte> PEEK_TICK;
   protected static final Rd<Byte> COLOR;
   public static final Om DEFAULT_COLOR;
   private float prevPeekAmount;
   private float peekAmount;
   private BlockPos currentAttachmentPosition;
   private int clientSideTeleportInterpolation;

   public KD(bij worldIn) {
      super(worldIn);
      this.setSize(1.0F, 1.0F);
      this.prevRenderYawOffset = 180.0F;
      this.renderYawOffset = 180.0F;
      this.isImmuneToFire = true;
      this.currentAttachmentPosition = null;
      this.experienceValue = 5;
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      this.renderYawOffset = 180.0F;
      this.prevRenderYawOffset = 180.0F;
      this.rotationYaw = 180.0F;
      this.prevRotationYaw = 180.0F;
      this.rotationYawHead = 180.0F;
      this.prevRotationYawHead = 180.0F;
      return super.onInitialSpawn(difficulty, livingdata);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(4, new Kx(this));
      this.tasks.addTask(7, new KB(this));
      this.tasks.addTask(8, new GH(this));
      this.targetTasks.addTask(1, new GB(this, true, new Class[0]));
      this.targetTasks.addTask(2, new Ky(this, this));
      this.targetTasks.addTask(3, new KA(this));
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public SoundCategory getSoundCategory() {
      return SoundCategory.HOSTILE;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_SHULKER_AMBIENT;
   }

   public void playLivingSound() {
      if (!this.isClosed()) {
         super.playLivingSound();
      }

   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_SHULKER_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isClosed() ? NO.ENTITY_SHULKER_HURT_CLOSED : NO.ENTITY_SHULKER_HURT;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(ATTACHED_FACE, EnumFacing.DOWN);
      this.dataManager.register(ATTACHED_BLOCK_POS, Optional.absent());
      this.dataManager.register(PEEK_TICK, (byte)0);
      this.dataManager.register(COLOR, (byte)DEFAULT_COLOR.getMetadata());
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(30.0);
   }

   protected Ij createBodyHelper() {
      return new KC(this, this);
   }

   public static void registerFixesShulker(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KD.class);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.dataManager.set(ATTACHED_FACE, EnumFacing.byIndex(compound.getByte("AttachFace")));
      this.dataManager.set(PEEK_TICK, compound.getByte("Peek"));
      this.dataManager.set(COLOR, compound.getByte("Color"));
      if (compound.hasKey("APX")) {
         int i = compound.getInteger("APX");
         int j = compound.getInteger("APY");
         int k = compound.getInteger("APZ");
         this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
      } else {
         this.dataManager.set(ATTACHED_BLOCK_POS, Optional.absent());
      }

   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setByte("AttachFace", (byte)((EnumFacing)this.dataManager.get(ATTACHED_FACE)).getIndex());
      compound.setByte("Peek", (Byte)this.dataManager.get(PEEK_TICK));
      compound.setByte("Color", (Byte)this.dataManager.get(COLOR));
      BlockPos blockpos = this.getAttachmentPos();
      if (blockpos != null) {
         compound.setInteger("APX", blockpos.getX());
         compound.setInteger("APY", blockpos.getY());
         compound.setInteger("APZ", blockpos.getZ());
      }

   }

   public void onUpdate() {
      super.onUpdate();
      BlockPos blockpos = (BlockPos)((Optional)this.dataManager.get(ATTACHED_BLOCK_POS)).orNull();
      if (blockpos == null && !this.world.isRemote) {
         blockpos = new BlockPos(this);
         this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
      }

      float f1;
      if (this.isRiding()) {
         blockpos = null;
         f1 = this.getRidingEntity().rotationYaw;
         this.rotationYaw = f1;
         this.renderYawOffset = f1;
         this.prevRenderYawOffset = f1;
         this.clientSideTeleportInterpolation = 0;
      } else if (!this.world.isRemote) {
         in iblockstate = this.world.getBlockState(blockpos);
         if (iblockstate.getMaterial() != hM.AIR) {
            EnumFacing enumfacing3;
            if (iblockstate.getBlock() == Nk.PISTON_EXTENSION) {
               enumfacing3 = (EnumFacing)iblockstate.getValue(ff.FACING);
               if (this.world.isAirBlock(blockpos.offset(enumfacing3))) {
                  blockpos = blockpos.offset(enumfacing3);
                  this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
               } else {
                  this.tryTeleportToNewPosition();
               }
            } else if (iblockstate.getBlock() == Nk.PISTON_HEAD) {
               enumfacing3 = (EnumFacing)iblockstate.getValue(fi.FACING);
               if (this.world.isAirBlock(blockpos.offset(enumfacing3))) {
                  blockpos = blockpos.offset(enumfacing3);
                  this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
               } else {
                  this.tryTeleportToNewPosition();
               }
            } else {
               this.tryTeleportToNewPosition();
            }
         }

         BlockPos blockpos1 = blockpos.offset(this.getAttachmentFacing());
         if (!this.world.isBlockNormalCube(blockpos1, false)) {
            boolean flag = false;
            EnumFacing[] var5 = EnumFacing.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               EnumFacing enumfacing1 = var5[var7];
               blockpos1 = blockpos.offset(enumfacing1);
               if (this.world.isBlockNormalCube(blockpos1, false)) {
                  this.dataManager.set(ATTACHED_FACE, enumfacing1);
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               this.tryTeleportToNewPosition();
            }
         }

         BlockPos blockpos2 = blockpos.offset(this.getAttachmentFacing().getOpposite());
         if (this.world.isBlockNormalCube(blockpos2, false)) {
            this.tryTeleportToNewPosition();
         }
      }

      f1 = (float)this.getPeekTick() * 0.01F;
      this.prevPeekAmount = this.peekAmount;
      if (this.peekAmount > f1) {
         this.peekAmount = MathHelper.clamp(this.peekAmount - 0.05F, f1, 1.0F);
      } else if (this.peekAmount < f1) {
         this.peekAmount = MathHelper.clamp(this.peekAmount + 0.05F, 0.0F, f1);
      }

      if (blockpos != null) {
         if (this.world.isRemote) {
            if (this.clientSideTeleportInterpolation > 0 && this.currentAttachmentPosition != null) {
               --this.clientSideTeleportInterpolation;
            } else {
               this.currentAttachmentPosition = blockpos;
            }
         }

         this.posX = (double)blockpos.getX() + 0.5;
         this.posY = (double)blockpos.getY();
         this.posZ = (double)blockpos.getZ() + 0.5;
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.lastTickPosX = this.posX;
         this.lastTickPosY = this.posY;
         this.lastTickPosZ = this.posZ;
         double d3 = 0.5 - (double)MathHelper.sin((0.5F + this.peekAmount) * 3.1415927F) * 0.5;
         double d4 = 0.5 - (double)MathHelper.sin((0.5F + this.prevPeekAmount) * 3.1415927F) * 0.5;
         double d5 = d3 - d4;
         double d0 = 0.0;
         double d1 = 0.0;
         double d2 = 0.0;
         EnumFacing enumfacing2 = this.getAttachmentFacing();
         switch (enumfacing2) {
            case DOWN:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5, this.posY, this.posZ - 0.5, this.posX + 0.5, this.posY + 1.0 + d3, this.posZ + 0.5));
               d1 = d5;
               break;
            case UP:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5, this.posY - d3, this.posZ - 0.5, this.posX + 0.5, this.posY + 1.0, this.posZ + 0.5));
               d1 = -d5;
               break;
            case NORTH:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5, this.posY, this.posZ - 0.5, this.posX + 0.5, this.posY + 1.0, this.posZ + 0.5 + d3));
               d2 = d5;
               break;
            case SOUTH:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5, this.posY, this.posZ - 0.5 - d3, this.posX + 0.5, this.posY + 1.0, this.posZ + 0.5));
               d2 = -d5;
               break;
            case WEST:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5, this.posY, this.posZ - 0.5, this.posX + 0.5 + d3, this.posY + 1.0, this.posZ + 0.5));
               d0 = d5;
               break;
            case EAST:
               this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5 - d3, this.posY, this.posZ - 0.5, this.posX + 0.5, this.posY + 1.0, this.posZ + 0.5));
               d0 = -d5;
         }

         if (d5 > 0.0) {
            List<Ig> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
            if (!list.isEmpty()) {
               Iterator var17 = list.iterator();

               while(var17.hasNext()) {
                  Ig entity = (Ig)var17.next();
                  if (!(entity instanceof KD) && !entity.noClip) {
                     entity.move(Lq.SHULKER, d0, d1, d2);
                  }
               }
            }
         }
      }

   }

   public void move(Lq type, double x, double y, double z) {
      if (type == Lq.SHULKER_BOX) {
         this.tryTeleportToNewPosition();
      } else {
         super.move(type, x, y, z);
      }

   }

   public void setPosition(double x, double y, double z) {
      super.setPosition(x, y, z);
      if (this.dataManager != null && this.ticksExisted != 0) {
         Optional<BlockPos> optional = (Optional)this.dataManager.get(ATTACHED_BLOCK_POS);
         Optional<BlockPos> optional1 = Optional.of(new BlockPos(x, y, z));
         if (!optional1.equals(optional)) {
            this.dataManager.set(ATTACHED_BLOCK_POS, optional1);
            this.dataManager.set(PEEK_TICK, (byte)0);
            this.isAirBorne = true;
         }
      }

   }

   protected boolean tryTeleportToNewPosition() {
      if (!this.isAIDisabled() && this.isEntityAlive()) {
         BlockPos blockpos = new BlockPos(this);

         for(int i = 0; i < 5; ++i) {
            BlockPos blockpos1 = blockpos.add(8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17));
            if (blockpos1.getY() > 0 && this.world.isAirBlock(blockpos1) && this.world.isInsideWorldBorder(this) && this.world.getCollisionBoxes(this, new AxisAlignedBB(blockpos1)).isEmpty()) {
               boolean flag = false;
               EnumFacing[] var5 = EnumFacing.values();
               int var6 = var5.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  EnumFacing enumfacing = var5[var7];
                  if (this.world.isBlockNormalCube(blockpos1.offset(enumfacing), false)) {
                     this.dataManager.set(ATTACHED_FACE, enumfacing);
                     flag = true;
                     break;
                  }
               }

               if (flag) {
                  this.playSound(NO.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
                  this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos1));
                  this.dataManager.set(PEEK_TICK, (byte)0);
                  this.setAttackTarget((Iw)null);
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevRenderYawOffset = 180.0F;
      this.renderYawOffset = 180.0F;
      this.rotationYaw = 180.0F;
   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (ATTACHED_BLOCK_POS.equals(key) && this.world.isRemote && !this.isRiding()) {
         BlockPos blockpos = this.getAttachmentPos();
         if (blockpos != null) {
            if (this.currentAttachmentPosition == null) {
               this.currentAttachmentPosition = blockpos;
            } else {
               this.clientSideTeleportInterpolation = 6;
            }

            this.posX = (double)blockpos.getX() + 0.5;
            this.posY = (double)blockpos.getY();
            this.posZ = (double)blockpos.getZ() + 0.5;
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.lastTickPosX = this.posX;
            this.lastTickPosY = this.posY;
            this.lastTickPosZ = this.posZ;
         }
      }

      super.notifyDataManagerChange(key);
   }

   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
      this.newPosRotationIncrements = 0;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isClosed()) {
         Ig entity = source.getImmediateSource();
         if (entity instanceof MO) {
            return false;
         }
      }

      if (super.attackEntityFrom(source, amount)) {
         if ((double)this.getHealth() < (double)this.getMaxHealth() * 0.5 && this.rand.nextInt(4) == 0) {
            this.tryTeleportToNewPosition();
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean isClosed() {
      return this.getPeekTick() == 0;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox() {
      return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
   }

   public EnumFacing getAttachmentFacing() {
      return (EnumFacing)this.dataManager.get(ATTACHED_FACE);
   }

   @Nullable
   public BlockPos getAttachmentPos() {
      return (BlockPos)((Optional)this.dataManager.get(ATTACHED_BLOCK_POS)).orNull();
   }

   public void setAttachmentPos(@Nullable BlockPos pos) {
      this.dataManager.set(ATTACHED_BLOCK_POS, Optional.fromNullable(pos));
   }

   public int getPeekTick() {
      return (Byte)this.dataManager.get(PEEK_TICK);
   }

   public void updateArmorModifier(int p_184691_1_) {
      if (!this.world.isRemote) {
         this.getEntityAttribute(Ni.ARMOR).removeModifier(COVERED_ARMOR_BONUS_MODIFIER);
         if (p_184691_1_ == 0) {
            this.getEntityAttribute(Ni.ARMOR).applyModifier(COVERED_ARMOR_BONUS_MODIFIER);
            this.playSound(NO.ENTITY_SHULKER_CLOSE, 1.0F, 1.0F);
         } else {
            this.playSound(NO.ENTITY_SHULKER_OPEN, 1.0F, 1.0F);
         }
      }

      this.dataManager.set(PEEK_TICK, (byte)p_184691_1_);
   }

   public float getClientPeekAmount(float p_184688_1_) {
      return this.prevPeekAmount + (this.peekAmount - this.prevPeekAmount) * p_184688_1_;
   }

   public int getClientTeleportInterp() {
      return this.clientSideTeleportInterpolation;
   }

   public BlockPos getOldAttachPos() {
      return this.currentAttachmentPosition;
   }

   public float getEyeHeight() {
      return 0.5F;
   }

   public int getVerticalFaceSpeed() {
      return 180;
   }

   public int getHorizontalFaceSpeed() {
      return 180;
   }

   public void applyEntityCollision(Ig entityIn) {
   }

   public float getCollisionBorderSize() {
      return 0.0F;
   }

   public boolean isAttachedToBlock() {
      return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_SHULKER;
   }

   public Om getColor() {
      return Om.byMetadata((Byte)this.dataManager.get(COLOR));
   }

   // $FF: synthetic method
   static Random access$100(KD x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$200(KD x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$300(KD x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$400(KD x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$500(KD x0) {
      return x0.rand;
   }

   static {
      COVERED_ARMOR_BONUS_MODIFIER = (new FW(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 20.0, 0)).setSaved(false);
      ATTACHED_FACE = Rv.createKey(KD.class, Rt.FACING);
      ATTACHED_BLOCK_POS = Rv.createKey(KD.class, Rt.OPTIONAL_BLOCK_POS);
      PEEK_TICK = Rv.createKey(KD.class, Rt.BYTE);
      COLOR = Rv.createKey(KD.class, Rt.BYTE);
      DEFAULT_COLOR = Om.PURPLE;
   }
}
