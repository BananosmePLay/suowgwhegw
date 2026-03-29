package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;

public class bhg {
   private final float luck;
   private final bis world;
   private final bht lootTableManager;
   @Nullable
   private final Ig lootedEntity;
   @Nullable
   private final ME player;
   @Nullable
   private final DamageSource damageSource;
   private final Set<bhp> lootTables = Sets.newLinkedHashSet();

   public bhg(float luckIn, bis worldIn, bht lootTableManagerIn, @Nullable Ig lootedEntityIn, @Nullable ME playerIn, @Nullable DamageSource damageSourceIn) {
      this.luck = luckIn;
      this.world = worldIn;
      this.lootTableManager = lootTableManagerIn;
      this.lootedEntity = lootedEntityIn;
      this.player = playerIn;
      this.damageSource = damageSourceIn;
   }

   @Nullable
   public Ig getLootedEntity() {
      return this.lootedEntity;
   }

   @Nullable
   public Ig getKillerPlayer() {
      return this.player;
   }

   @Nullable
   public Ig getKiller() {
      return this.damageSource == null ? null : this.damageSource.getTrueSource();
   }

   public boolean addLootTable(bhp lootTableIn) {
      return this.lootTables.add(lootTableIn);
   }

   public void removeLootTable(bhp lootTableIn) {
      this.lootTables.remove(lootTableIn);
   }

   public bht getLootTableManager() {
      return this.lootTableManager;
   }

   public float getLuck() {
      return this.luck;
   }

   @Nullable
   public Ig getEntity(bhf target) {
      switch (target) {
         case THIS:
            return this.getLootedEntity();
         case KILLER:
            return this.getKiller();
         case KILLER_PLAYER:
            return this.getKillerPlayer();
         default:
            return null;
      }
   }
}
