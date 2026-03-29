package neo;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.vecmath.Vector3i;
import net.minecraft.entity.player.EntityPlayer;

public abstract class 0dd {
   public boolean done;
   public CopyOnWriteArrayList<0dg> unchecked = new CopyOnWriteArrayList();
   public CopyOnWriteArrayList<0dg> checked = new CopyOnWriteArrayList();
   protected CopyOnWriteArrayList<0dg> path;

   private static Vector3i Baj11vaown(0dg var0) {
      return var0.pos;
   }

   private static int VY1Tu5i46o(Vector3i var0) {
      return var0.z;
   }

   private static int tja5GOL4gW(Vector3i var0) {
      return var0.y;
   }

   private static Vector3i NGeUnPO1z4(0dg var0) {
      return var0.pos;
   }

   private static Vector3i X4IoLWt6eG(0dg var0) {
      return var0.pos;
   }

   public abstract void onFinished();

   public abstract List<0dg> getPathList();

   private static Vector3i ILfdAyGONX(0dg var0) {
      return var0.pos;
   }

   public abstract boolean onUpdate(EntityPlayer var1);

   public abstract boolean isRunning();

   public abstract void stop();

   public abstract void scan();

   private static int ogjzdAwEAR(Vector3i var0) {
      return var0.x;
   }

   private static Vector3i qjBrSRXP3T(0dg var0) {
      return var0.pos;
   }

   public _dd/* $FF was: 0dd*/() {
   }

   public boolean contains(CopyOnWriteArrayList<0dg> list, 0dg find) {
      Iterator var3 = list.iterator();

      0dg point;
      do {
         if (!var3.hasNext()) {
            return (boolean)(18652 ^ -23257 ^ 17037 ^ -20618);
         }

         point = (0dg)var3.next();
      } while(ogjzdAwEAR(ObAx1FNDlt(point)) != wTNjyFxDJJ(Baj11vaown(find)) || jeJCB7jg22(ILfdAyGONX(point)) != tja5GOL4gW(NGeUnPO1z4(find)) || iO3RVBlDeN(qjBrSRXP3T(point)) != VY1Tu5i46o(X4IoLWt6eG(find)));

      return (boolean)(16025 ^ -32585 ^ 5860 ^ -22325);
   }

   private static int iO3RVBlDeN(Vector3i var0) {
      return var0.z;
   }

   private static int wTNjyFxDJJ(Vector3i var0) {
      return var0.x;
   }

   private static int jeJCB7jg22(Vector3i var0) {
      return var0.y;
   }

   private static Vector3i ObAx1FNDlt(0dg var0) {
      return var0.pos;
   }
}
