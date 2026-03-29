package neo;

import java.util.UUID;
import javax.annotation.Nullable;

public interface IF {
   @Nullable
   UUID getOwnerId();

   @Nullable
   Ig getOwner();
}
