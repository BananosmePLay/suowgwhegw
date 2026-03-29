package neo;

import net.minecraft.util.IntHashMap;
import net.minecraft.util.math.MathHelper;

public abstract class VG {
   protected bfZ blockaccess;
   protected Iu entity;
   protected final IntHashMap<VR> pointMap = new IntHashMap();
   protected int entitySizeX;
   protected int entitySizeY;
   protected int entitySizeZ;
   protected boolean canEnterDoors;
   protected boolean canOpenDoors;
   protected boolean canSwim;

   public VG() {
   }

   public void init(bfZ sourceIn, Iu mob) {
      this.blockaccess = sourceIn;
      this.entity = mob;
      this.pointMap.clearMap();
      this.entitySizeX = MathHelper.floor(mob.width + 1.0F);
      this.entitySizeY = MathHelper.floor(mob.height + 1.0F);
      this.entitySizeZ = MathHelper.floor(mob.width + 1.0F);
   }

   public void postProcess() {
      this.blockaccess = null;
      this.entity = null;
   }

   protected VR openPoint(int x, int y, int z) {
      int i = VR.makeHash(x, y, z);
      VR pathpoint = (VR)this.pointMap.lookup(i);
      if (pathpoint == null) {
         pathpoint = new VR(x, y, z);
         this.pointMap.addKey(i, pathpoint);
      }

      return pathpoint;
   }

   public abstract VR getStart();

   public abstract VR getPathPointToCoords(double var1, double var3, double var5);

   public abstract int findPathOptions(VR[] var1, VR var2, VR var3, float var4);

   public abstract VQ getPathNodeType(bfZ var1, int var2, int var3, int var4, Iu var5, int var6, int var7, int var8, boolean var9, boolean var10);

   public abstract VQ getPathNodeType(bfZ var1, int var2, int var3, int var4);

   public void setCanEnterDoors(boolean canEnterDoorsIn) {
      this.canEnterDoors = canEnterDoorsIn;
   }

   public void setCanOpenDoors(boolean canOpenDoorsIn) {
      this.canOpenDoors = canOpenDoorsIn;
   }

   public void setCanSwim(boolean canSwimIn) {
      this.canSwim = canSwimIn;
   }

   public boolean getCanEnterDoors() {
      return this.canEnterDoors;
   }

   public boolean getCanOpenDoors() {
      return this.canOpenDoors;
   }

   public boolean getCanSwim() {
      return this.canSwim;
   }
}
