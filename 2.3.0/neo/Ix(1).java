package neo;

import com.google.common.collect.Maps;
import java.util.Map;

public class Ix {
   private static final Map<Class<?>, It> ENTITY_PLACEMENTS = Maps.newHashMap();

   public Ix() {
   }

   public static It getPlacementForEntity(Class<?> entityClass) {
      return (It)ENTITY_PLACEMENTS.get(entityClass);
   }

   static {
      ENTITY_PLACEMENTS.put(Lz.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LA.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LB.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LF.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Md.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Mv.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LC.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LM.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LL.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LN.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LQ.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LY.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(LP.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Mb.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KO.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Mf.class, It.IN_WATER);
      ENTITY_PLACEMENTS.put(Kj.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Mu.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Mq.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(HS.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(HV.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Jz.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JA.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JB.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JJ.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JK.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JW.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(JX.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Kc.class, It.IN_WATER);
      ENTITY_PLACEMENTS.put(Kk.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Ko.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KG.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KH.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Lh.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KX.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KN.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(KW.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Lg.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Lk.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Ll.class, It.ON_GROUND);
      ENTITY_PLACEMENTS.put(Kd.class, It.ON_GROUND);
   }
}
