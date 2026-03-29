package neo;

import javax.annotation.Nullable;

public interface bap {
   int idFor(in var1);

   @Nullable
   in getBlockState(int var1);

   void read(SA var1);

   void write(SA var1);

   int getSerializedSize();
}
