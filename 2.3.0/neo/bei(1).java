package neo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class bei extends beb {
   public beg lastPlaced;
   public List<beg> primaryWeights;
   public List<beg> secondaryWeights;
   public List<bdB> pendingChildren = Lists.newArrayList();

   public bei() {
   }

   public bei(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_) {
      super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
      this.primaryWeights = Lists.newArrayList();
      beg[] var4 = bel.access$100();
      int var5 = var4.length;

      int var6;
      beg structurenetherbridgepieces$pieceweight1;
      for(var6 = 0; var6 < var5; ++var6) {
         structurenetherbridgepieces$pieceweight1 = var4[var6];
         structurenetherbridgepieces$pieceweight1.placeCount = 0;
         this.primaryWeights.add(structurenetherbridgepieces$pieceweight1);
      }

      this.secondaryWeights = Lists.newArrayList();
      var4 = bel.access$200();
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         structurenetherbridgepieces$pieceweight1 = var4[var6];
         structurenetherbridgepieces$pieceweight1.placeCount = 0;
         this.secondaryWeights.add(structurenetherbridgepieces$pieceweight1);
      }

   }
}
