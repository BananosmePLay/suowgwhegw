package neo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class bfb extends bfa {
   public beV lastPlaced;
   public beW strongholdPortalRoom;
   public List<bdB> pendingChildren = Lists.newArrayList();

   public bfb() {
   }

   public bfb(int p_i2083_1_, Random p_i2083_2_, int p_i2083_3_, int p_i2083_4_) {
      super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
   }
}
