package neo;

import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;

public class k {
   public static final k EMPTY;
   private final int experience;
   private final ResourceLocation[] loot;
   private final ResourceLocation[] recipes;
   private final Dt function;

   public k(int experience, ResourceLocation[] loot, ResourceLocation[] recipes, Dt function) {
      this.experience = experience;
      this.loot = loot;
      this.recipes = recipes;
      this.function = function;
   }

   public void apply(final MG player) {
      player.addExperience(this.experience);
      bhg lootcontext = (new bhd(player.getServerWorld())).withLootedEntity(player).build();
      boolean flag = false;
      ResourceLocation[] var4 = this.loot;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ResourceLocation resourcelocation = var4[var6];
         Iterator var8 = player.world.getLootTableManager().getLootTableFromLocation(resourcelocation).generateLootForPools(player.getRNG(), lootcontext).iterator();

         while(var8.hasNext()) {
            Qy itemstack = (Qy)var8.next();
            if (player.addItemStackToInventory(itemstack)) {
               player.world.playSound((ME)null, player.posX, player.posY, player.posZ, NO.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
               flag = true;
            } else {
               IY entityitem = player.dropItem(itemstack, false);
               if (entityitem != null) {
                  entityitem.setNoPickupDelay();
                  entityitem.setOwner(player.getName());
               }
            }
         }
      }

      if (flag) {
         player.inventoryContainer.detectAndSendChanges();
      }

      if (this.recipes.length > 0) {
         player.unlockRecipes(this.recipes);
      }

      final Xx minecraftserver = player.server;
      Dx functionobject = this.function.get(minecraftserver.getFunctionManager());
      if (functionobject != null) {
         DB icommandsender = new DB() {
            public String getName() {
               return player.getName();
            }

            public ITextComponent getDisplayName() {
               return player.getDisplayName();
            }

            public void sendMessage(ITextComponent component) {
            }

            public boolean canUseCommand(int permLevel, String commandName) {
               return permLevel <= 2;
            }

            public BlockPos getPosition() {
               return player.getPosition();
            }

            public Vec3d getPositionVector() {
               return player.getPositionVector();
            }

            public bij getEntityWorld() {
               return player.world;
            }

            public Ig getCommandSenderEntity() {
               return player;
            }

            public boolean sendCommandFeedback() {
               return minecraftserver.worlds[0].getGameRules().getBoolean("commandBlockOutput");
            }

            public void setCommandStat(CK type, int amount) {
               player.setCommandStat(type, amount);
            }

            public Xx getServer() {
               return player.getServer();
            }
         };
         minecraftserver.getFunctionManager().execute(functionobject, icommandsender);
      }

   }

   public String toString() {
      return "AdvancementRewards{experience=" + this.experience + ", loot=" + Arrays.toString((Object[])this.loot) + ", recipes=" + Arrays.toString((Object[])this.recipes) + ", function=" + this.function + '}';
   }

   static {
      EMPTY = new k(0, new ResourceLocation[0], new ResourceLocation[0], Dt.EMPTY);
   }
}
