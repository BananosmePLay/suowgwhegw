package neo;

import net.minecraft.util.DamageSource;

public class bhd {
   private final bis world;
   private float luck;
   private Ig lootedEntity;
   private ME player;
   private DamageSource damageSource;

   public bhd(bis worldIn) {
      this.world = worldIn;
   }

   public bhd withLuck(float luckIn) {
      this.luck = luckIn;
      return this;
   }

   public bhd withLootedEntity(Ig entityIn) {
      this.lootedEntity = entityIn;
      return this;
   }

   public bhd withPlayer(ME playerIn) {
      this.player = playerIn;
      return this;
   }

   public bhd withDamageSource(DamageSource dmgSource) {
      this.damageSource = dmgSource;
      return this;
   }

   public bhg build() {
      return new bhg(this.luck, this.world, this.world.getLootTableManager(), this.lootedEntity, this.player, this.damageSource);
   }
}
