package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Zb extends bhZ {
   private bij world;
   private final List<BlockPos> villagerPositionsList = Lists.newArrayList();
   private final List<Zc> newDoors = Lists.newArrayList();
   private final List<Za> villageList = Lists.newArrayList();
   private int tickCounter;

   public Zb(String name) {
      super(name);
   }

   public Zb(bij worldIn) {
      super(fileNameForProvider(worldIn.provider));
      this.world = worldIn;
      this.markDirty();
   }

   public void setWorldsForAll(bij worldIn) {
      this.world = worldIn;
      Iterator var2 = this.villageList.iterator();

      while(var2.hasNext()) {
         Za village = (Za)var2.next();
         village.setWorld(worldIn);
      }

   }

   public void addToVillagerPositionList(BlockPos pos) {
      if (this.villagerPositionsList.size() <= 64 && !this.positionInList(pos)) {
         this.villagerPositionsList.add(pos);
      }

   }

   public void tick() {
      ++this.tickCounter;
      Iterator var1 = this.villageList.iterator();

      while(var1.hasNext()) {
         Za village = (Za)var1.next();
         village.tick(this.tickCounter);
      }

      this.removeAnnihilatedVillages();
      this.dropOldestVillagerPosition();
      this.addNewDoorsToVillageOrCreateVillage();
      if (this.tickCounter % 400 == 0) {
         this.markDirty();
      }

   }

   private void removeAnnihilatedVillages() {
      Iterator<Za> iterator = this.villageList.iterator();

      while(iterator.hasNext()) {
         Za village = (Za)iterator.next();
         if (village.isAnnihilated()) {
            iterator.remove();
            this.markDirty();
         }
      }

   }

   public List<Za> getVillageList() {
      return this.villageList;
   }

   public Za getNearestVillage(BlockPos doorBlock, int radius) {
      Za village = null;
      double d0 = 3.4028234663852886E38;
      Iterator var6 = this.villageList.iterator();

      while(var6.hasNext()) {
         Za village1 = (Za)var6.next();
         double d1 = village1.getCenter().distanceSq(doorBlock);
         if (d1 < d0) {
            float f = (float)(radius + village1.getVillageRadius());
            if (d1 <= (double)(f * f)) {
               village = village1;
               d0 = d1;
            }
         }
      }

      return village;
   }

   private void dropOldestVillagerPosition() {
      if (!this.villagerPositionsList.isEmpty()) {
         this.addDoorsAround((BlockPos)this.villagerPositionsList.remove(0));
      }

   }

   private void addNewDoorsToVillageOrCreateVillage() {
      for(int i = 0; i < this.newDoors.size(); ++i) {
         Zc villagedoorinfo = (Zc)this.newDoors.get(i);
         Za village = this.getNearestVillage(villagedoorinfo.getDoorBlockPos(), 32);
         if (village == null) {
            village = new Za(this.world);
            this.villageList.add(village);
            this.markDirty();
         }

         village.addVillageDoorInfo(villagedoorinfo);
      }

      this.newDoors.clear();
   }

   private void addDoorsAround(BlockPos central) {
      int i = true;
      int j = true;
      int k = true;

      for(int l = -16; l < 16; ++l) {
         for(int i1 = -4; i1 < 4; ++i1) {
            for(int j1 = -16; j1 < 16; ++j1) {
               BlockPos blockpos = central.add(l, i1, j1);
               if (this.isWoodDoor(blockpos)) {
                  Zc villagedoorinfo = this.checkDoorExistence(blockpos);
                  if (villagedoorinfo == null) {
                     this.addToNewDoorsList(blockpos);
                  } else {
                     villagedoorinfo.setLastActivityTimestamp(this.tickCounter);
                  }
               }
            }
         }
      }

   }

   @Nullable
   private Zc checkDoorExistence(BlockPos doorBlock) {
      Iterator var2 = this.newDoors.iterator();

      Zc villagedoorinfo;
      do {
         if (!var2.hasNext()) {
            var2 = this.villageList.iterator();

            Zc villagedoorinfo1;
            do {
               if (!var2.hasNext()) {
                  return null;
               }

               Za village = (Za)var2.next();
               villagedoorinfo1 = village.getExistedDoor(doorBlock);
            } while(villagedoorinfo1 == null);

            return villagedoorinfo1;
         }

         villagedoorinfo = (Zc)var2.next();
      } while(villagedoorinfo.getDoorBlockPos().getX() != doorBlock.getX() || villagedoorinfo.getDoorBlockPos().getZ() != doorBlock.getZ() || Math.abs(villagedoorinfo.getDoorBlockPos().getY() - doorBlock.getY()) > 1);

      return villagedoorinfo;
   }

   private void addToNewDoorsList(BlockPos doorBlock) {
      EnumFacing enumfacing = do.getFacing(this.world, doorBlock);
      EnumFacing enumfacing1 = enumfacing.getOpposite();
      int i = this.countBlocksCanSeeSky(doorBlock, enumfacing, 5);
      int j = this.countBlocksCanSeeSky(doorBlock, enumfacing1, i + 1);
      if (i != j) {
         this.newDoors.add(new Zc(doorBlock, i < j ? enumfacing : enumfacing1, this.tickCounter));
      }

   }

   private int countBlocksCanSeeSky(BlockPos centerPos, EnumFacing direction, int limitation) {
      int i = 0;

      for(int j = 1; j <= 5; ++j) {
         if (this.world.canSeeSky(centerPos.offset(direction, j))) {
            ++i;
            if (i >= limitation) {
               return i;
            }
         }
      }

      return i;
   }

   private boolean positionInList(BlockPos pos) {
      Iterator var2 = this.villagerPositionsList.iterator();

      BlockPos blockpos;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         blockpos = (BlockPos)var2.next();
      } while(!blockpos.equals(pos));

      return true;
   }

   private boolean isWoodDoor(BlockPos doorPos) {
      in iblockstate = this.world.getBlockState(doorPos);
      co block = iblockstate.getBlock();
      if (block instanceof do) {
         return iblockstate.getMaterial() == hM.WOOD;
      } else {
         return false;
      }
   }

   public void readFromNBT(QQ nbt) {
      this.tickCounter = nbt.getInteger("Tick");
      QW nbttaglist = nbt.getTagList("Villages", 10);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         Za village = new Za();
         village.readVillageDataFromNBT(nbttagcompound);
         this.villageList.add(village);
      }

   }

   public QQ writeToNBT(QQ compound) {
      compound.setInteger("Tick", this.tickCounter);
      QW nbttaglist = new QW();
      Iterator var3 = this.villageList.iterator();

      while(var3.hasNext()) {
         Za village = (Za)var3.next();
         QQ nbttagcompound = new QQ();
         village.writeVillageDataToNBT(nbttagcompound);
         nbttaglist.appendTag(nbttagcompound);
      }

      compound.setTag("Villages", nbttaglist);
      return compound;
   }

   public static String fileNameForProvider(bil provider) {
      return "villages" + provider.getDimensionType().getSuffix();
   }
}
