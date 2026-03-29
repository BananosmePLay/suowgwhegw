package neo;

import net.minecraft.util.ResourceLocation;

public class Iq {
   public final ResourceLocation spawnedID;
   public final int primaryColor;
   public final int secondaryColor;
   public final XQ killEntityStat;
   public final XQ entityKilledByStat;

   public Iq(ResourceLocation idIn, int primaryColorIn, int secondaryColorIn) {
      this.spawnedID = idIn;
      this.primaryColor = primaryColorIn;
      this.secondaryColor = secondaryColorIn;
      this.killEntityStat = XV.getStatKillEntity(this);
      this.entityKilledByStat = XV.getStatEntityKilledBy(this);
   }
}
