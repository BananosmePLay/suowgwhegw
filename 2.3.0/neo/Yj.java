package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Yj extends YC implements ITickable, ISidedInventory {
   public static final VW[][] EFFECTS_LIST;
   private static final Set<VW> VALID_EFFECTS;
   private final List<Yi> beamSegments = Lists.newArrayList();
   private long beamRenderCounter;
   private float beamRenderScale;
   private boolean isComplete;
   private int levels = -1;
   @Nullable
   private VW primaryEffect;
   @Nullable
   private VW secondaryEffect;
   private Qy payment;
   private String customName;

   public Yj() {
      this.payment = Qy.EMPTY;
   }

   public void update() {
      if (this.world.getTotalWorldTime() % 80L == 0L) {
         this.updateBeacon();
      }

   }

   public void updateBeacon() {
      if (this.world != null) {
         this.updateSegmentColors();
         this.addEffectsToPlayers();
      }

   }

   private void addEffectsToPlayers() {
      if (this.isComplete && this.levels > 0 && !this.world.isRemote && this.primaryEffect != null) {
         double d0 = (double)(this.levels * 10 + 10);
         int i = 0;
         if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect) {
            i = 1;
         }

         int j = (9 + this.levels * 2) * 20;
         int k = this.pos.getX();
         int l = this.pos.getY();
         int i1 = this.pos.getZ();
         AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).grow(d0).expand(0.0, (double)this.world.getHeight(), 0.0);
         List<ME> list = this.world.getEntitiesWithinAABB(ME.class, axisalignedbb);
         Iterator var10 = list.iterator();

         ME entityplayer1;
         while(var10.hasNext()) {
            entityplayer1 = (ME)var10.next();
            entityplayer1.addPotionEffect(new VZ(this.primaryEffect, j, i, true, true));
         }

         if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect != null) {
            var10 = list.iterator();

            while(var10.hasNext()) {
               entityplayer1 = (ME)var10.next();
               entityplayer1.addPotionEffect(new VZ(this.secondaryEffect, j, 0, true, true));
            }
         }
      }

   }

   private void updateSegmentColors() {
      int i = this.pos.getX();
      int j = this.pos.getY();
      int k = this.pos.getZ();
      int l = this.levels;
      this.levels = 0;
      this.beamSegments.clear();
      this.isComplete = true;
      Yi tileentitybeacon$beamsegment = new Yi(Om.WHITE.getColorComponentValues());
      this.beamSegments.add(tileentitybeacon$beamsegment);
      boolean flag = true;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      int l1;
      for(l1 = j + 1; l1 < 256; ++l1) {
         in iblockstate = this.world.getBlockState(blockpos$mutableblockpos.setPos(i, l1, k));
         float[] afloat;
         if (iblockstate.getBlock() == Nk.STAINED_GLASS) {
            afloat = ((Om)iblockstate.getValue(gN.COLOR)).getColorComponentValues();
         } else {
            if (iblockstate.getBlock() != Nk.STAINED_GLASS_PANE) {
               if (iblockstate.getLightOpacity() >= 15 && iblockstate.getBlock() != Nk.BEDROCK) {
                  this.isComplete = false;
                  this.beamSegments.clear();
                  break;
               }

               tileentitybeacon$beamsegment.incrementHeight();
               continue;
            }

            afloat = ((Om)iblockstate.getValue(gP.COLOR)).getColorComponentValues();
         }

         if (!flag) {
            afloat = new float[]{(tileentitybeacon$beamsegment.getColors()[0] + afloat[0]) / 2.0F, (tileentitybeacon$beamsegment.getColors()[1] + afloat[1]) / 2.0F, (tileentitybeacon$beamsegment.getColors()[2] + afloat[2]) / 2.0F};
         }

         if (Arrays.equals(afloat, tileentitybeacon$beamsegment.getColors())) {
            tileentitybeacon$beamsegment.incrementHeight();
         } else {
            tileentitybeacon$beamsegment = new Yi(afloat);
            this.beamSegments.add(tileentitybeacon$beamsegment);
         }

         flag = false;
      }

      if (this.isComplete) {
         for(l1 = 1; l1 <= 4; this.levels = l1++) {
            int i2 = j - l1;
            if (i2 < 0) {
               break;
            }

            boolean flag1 = true;

            for(int j1 = i - l1; j1 <= i + l1 && flag1; ++j1) {
               for(int k1 = k - l1; k1 <= k + l1; ++k1) {
                  co block = this.world.getBlockState(new BlockPos(j1, i2, k1)).getBlock();
                  if (block != Nk.EMERALD_BLOCK && block != Nk.GOLD_BLOCK && block != Nk.DIAMOND_BLOCK && block != Nk.IRON_BLOCK) {
                     flag1 = false;
                     break;
                  }
               }
            }

            if (!flag1) {
               break;
            }
         }

         if (this.levels == 0) {
            this.isComplete = false;
         }
      }

      if (!this.world.isRemote && l < this.levels) {
         Iterator var14 = this.world.getEntitiesWithinAABB(MG.class, (new AxisAlignedBB((double)i, (double)j, (double)k, (double)i, (double)(j - 4), (double)k)).grow(10.0, 5.0, 10.0)).iterator();

         while(var14.hasNext()) {
            MG entityplayermp = (MG)var14.next();
            bY.CONSTRUCT_BEACON.trigger(entityplayermp, this);
         }
      }

   }

   public List<Yi> getBeamSegments() {
      return this.beamSegments;
   }

   public float shouldBeamRender() {
      if (!this.isComplete) {
         return 0.0F;
      } else {
         int i = (int)(this.world.getTotalWorldTime() - this.beamRenderCounter);
         this.beamRenderCounter = this.world.getTotalWorldTime();
         if (i > 1) {
            this.beamRenderScale -= (float)i / 40.0F;
            if (this.beamRenderScale < 0.0F) {
               this.beamRenderScale = 0.0F;
            }
         }

         this.beamRenderScale += 0.025F;
         if (this.beamRenderScale > 1.0F) {
            this.beamRenderScale = 1.0F;
         }

         return this.beamRenderScale;
      }
   }

   public int getLevels() {
      return this.levels;
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 3, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public double getMaxRenderDistanceSquared() {
      return 65536.0;
   }

   @Nullable
   private static VW isBeaconEffect(int p_184279_0_) {
      VW potion = VW.getPotionById(p_184279_0_);
      return VALID_EFFECTS.contains(potion) ? potion : null;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.primaryEffect = isBeaconEffect(compound.getInteger("Primary"));
      this.secondaryEffect = isBeaconEffect(compound.getInteger("Secondary"));
      this.levels = compound.getInteger("Levels");
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setInteger("Primary", VW.getIdFromPotion(this.primaryEffect));
      compound.setInteger("Secondary", VW.getIdFromPotion(this.secondaryEffect));
      compound.setInteger("Levels", this.levels);
      return compound;
   }

   public int getSizeInventory() {
      return 1;
   }

   public boolean isEmpty() {
      return this.payment.isEmpty();
   }

   public Qy getStackInSlot(int index) {
      return index == 0 ? this.payment : Qy.EMPTY;
   }

   public Qy decrStackSize(int index, int count) {
      if (index == 0 && !this.payment.isEmpty()) {
         if (count >= this.payment.getCount()) {
            Qy itemstack = this.payment;
            this.payment = Qy.EMPTY;
            return itemstack;
         } else {
            return this.payment.splitStack(count);
         }
      } else {
         return Qy.EMPTY;
      }
   }

   public Qy removeStackFromSlot(int index) {
      if (index == 0) {
         Qy itemstack = this.payment;
         this.payment = Qy.EMPTY;
         return itemstack;
      } else {
         return Qy.EMPTY;
      }
   }

   public void setInventorySlotContents(int index, Qy stack) {
      if (index == 0) {
         this.payment = stack;
      }

   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.beacon";
   }

   public boolean hasCustomName() {
      return this.customName != null && !this.customName.isEmpty();
   }

   public void setName(String name) {
      this.customName = name;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public boolean isUsableByPlayer(ME player) {
      if (this.world.getTileEntity(this.pos) != this) {
         return false;
      } else {
         return player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
      }
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      return stack.getItem() == NK.EMERALD || stack.getItem() == NK.DIAMOND || stack.getItem() == NK.GOLD_INGOT || stack.getItem() == NK.IRON_INGOT;
   }

   public String getGuiID() {
      return "minecraft:beacon";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerBeacon(playerInventory, this);
   }

   public int getField(int id) {
      switch (id) {
         case 0:
            return this.levels;
         case 1:
            return VW.getIdFromPotion(this.primaryEffect);
         case 2:
            return VW.getIdFromPotion(this.secondaryEffect);
         default:
            return 0;
      }
   }

   public void setField(int id, int value) {
      switch (id) {
         case 0:
            this.levels = value;
            break;
         case 1:
            this.primaryEffect = isBeaconEffect(value);
            break;
         case 2:
            this.secondaryEffect = isBeaconEffect(value);
      }

   }

   public int getFieldCount() {
      return 3;
   }

   public void clear() {
      this.payment = Qy.EMPTY;
   }

   public boolean receiveClientEvent(int id, int type) {
      if (id == 1) {
         this.updateBeacon();
         return true;
      } else {
         return super.receiveClientEvent(id, type);
      }
   }

   public int[] getSlotsForFace(EnumFacing side) {
      return new int[0];
   }

   public boolean canInsertItem(int index, Qy itemStackIn, EnumFacing direction) {
      return false;
   }

   public boolean canExtractItem(int index, Qy stack, EnumFacing direction) {
      return false;
   }

   static {
      EFFECTS_LIST = new VW[][]{{NL.SPEED, NL.HASTE}, {NL.RESISTANCE, NL.JUMP_BOOST}, {NL.STRENGTH}, {NL.REGENERATION}};
      VALID_EFFECTS = Sets.newHashSet();
      VW[][] var0 = EFFECTS_LIST;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         VW[] apotion = var0[var2];
         Collections.addAll(VALID_EFFECTS, apotion);
      }

   }
}
