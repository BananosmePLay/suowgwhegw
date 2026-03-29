package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mq extends Ih implements II, IH {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Rd<Integer> PROFESSION;
   private int randomTickDivider;
   private boolean isMating;
   private boolean isPlaying;
   Za village;
   @Nullable
   private ME buyingPlayer;
   @Nullable
   private YX buyingList;
   private int timeUntilReset;
   private boolean needsInitilization;
   private boolean isWillingToMate;
   private int wealth;
   private String lastBuyingPlayer;
   private int careerId;
   private int careerLevel;
   private boolean isLookingForHome;
   private boolean areAdditionalTasksSet;
   private final InventoryBasic villagerInventory;
   private static final Mk[][][][] DEFAULT_TRADE_LIST_MAP;

   public Mq(bij worldIn) {
      this(worldIn, 0);
   }

   public Mq(bij worldIn, int professionId) {
      super(worldIn);
      this.villagerInventory = new InventoryBasic("Items", false, 8);
      this.setProfession(professionId);
      this.setSize(0.6F, 1.95F);
      ((VO)this.getNavigator()).setBreakDoors(true);
      this.setCanPickUpLoot(true);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new Gh(this, Lk.class, 8.0F, 0.6, 0.6));
      this.tasks.addTask(1, new Gh(this, JR.class, 12.0F, 0.8, 0.8));
      this.tasks.addTask(1, new Gh(this, Lf.class, 8.0F, 0.8, 0.8));
      this.tasks.addTask(1, new Gh(this, Lc.class, 8.0F, 0.6, 0.6));
      this.tasks.addTask(1, new Hk(this));
      this.tasks.addTask(1, new GF(this));
      this.tasks.addTask(2, new GJ(this));
      this.tasks.addTask(3, new GZ(this));
      this.tasks.addTask(4, new GU(this, true));
      this.tasks.addTask(5, new GM(this, 0.6));
      this.tasks.addTask(6, new Hm(this));
      this.tasks.addTask(7, new Gw(this));
      this.tasks.addTask(9, new Hr(this, ME.class, 3.0F, 1.0F));
      this.tasks.addTask(9, new Hl(this));
      this.tasks.addTask(9, new Ho(this, 0.6));
      this.tasks.addTask(10, new Hq(this, Iu.class, 8.0F));
   }

   private void setAdditionalAItasks() {
      if (!this.areAdditionalTasksSet) {
         this.areAdditionalTasksSet = true;
         if (this.isChild()) {
            this.tasks.addTask(8, new GY(this, 0.32));
         } else if (this.getProfession() == 0) {
            this.tasks.addTask(6, new GA(this, 0.6));
         }
      }

   }

   protected void onGrowingAdult() {
      if (this.getProfession() == 0) {
         this.tasks.addTask(8, new GA(this, 0.6));
      }

      super.onGrowingAdult();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.5);
   }

   protected void updateAITasks() {
      if (--this.randomTickDivider <= 0) {
         BlockPos blockpos = new BlockPos(this);
         this.world.getVillageCollection().addToVillagerPositionList(blockpos);
         this.randomTickDivider = 70 + this.rand.nextInt(50);
         this.village = this.world.getVillageCollection().getNearestVillage(blockpos, 32);
         if (this.village == null) {
            this.detachHome();
         } else {
            BlockPos blockpos1 = this.village.getCenter();
            this.setHomePosAndDistance(blockpos1, this.village.getVillageRadius());
            if (this.isLookingForHome) {
               this.isLookingForHome = false;
               this.village.setDefaultPlayerReputation(5);
            }
         }
      }

      if (!this.isTrading() && this.timeUntilReset > 0) {
         --this.timeUntilReset;
         if (this.timeUntilReset <= 0) {
            if (this.needsInitilization) {
               Iterator var3 = this.buyingList.iterator();

               while(var3.hasNext()) {
                  YW merchantrecipe = (YW)var3.next();
                  if (merchantrecipe.isRecipeDisabled()) {
                     merchantrecipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                  }
               }

               this.populateBuyingList();
               this.needsInitilization = false;
               if (this.village != null && this.lastBuyingPlayer != null) {
                  this.world.setEntityState(this, (byte)14);
                  this.village.modifyPlayerReputation(this.lastBuyingPlayer, 1);
               }
            }

            this.addPotionEffect(new VZ(NL.REGENERATION, 200, 0));
         }
      }

      super.updateAITasks();
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      boolean flag = itemstack.getItem() == NK.NAME_TAG;
      if (flag) {
         itemstack.interactWithEntity(player, this, hand);
         return true;
      } else if (!this.holdingSpawnEggOfClass(itemstack, this.getClass()) && this.isEntityAlive() && !this.isTrading() && !this.isChild()) {
         if (this.buyingList == null) {
            this.populateBuyingList();
         }

         if (hand == EnumHand.MAIN_HAND) {
            player.addStat(XV.TALKED_TO_VILLAGER);
         }

         if (!this.world.isRemote && !this.buyingList.isEmpty()) {
            this.setCustomer(player);
            player.displayVillagerTradeGui(this);
         } else if (this.buyingList.isEmpty()) {
            return super.processInteract(player, hand);
         }

         return true;
      } else {
         return super.processInteract(player, hand);
      }
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(PROFESSION, 0);
   }

   public static void registerFixesVillager(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Mq.class);
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackDataLists(Mq.class, new String[]{"Inventory"}));
      fixer.registerWalker(FixTypes.ENTITY, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (Ir.getKey(Mq.class).equals(new ResourceLocation(compound.getString("id"))) && compound.hasKey("Offers", 10)) {
               QQ nbttagcompound = compound.getCompoundTag("Offers");
               if (nbttagcompound.hasKey("Recipes", 9)) {
                  QW nbttaglist = nbttagcompound.getTagList("Recipes", 10);

                  for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                     QQ nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                     DataFixesManager.processItemStack(fixer, nbttagcompound1, versionIn, "buy");
                     DataFixesManager.processItemStack(fixer, nbttagcompound1, versionIn, "buyB");
                     DataFixesManager.processItemStack(fixer, nbttagcompound1, versionIn, "sell");
                     nbttaglist.set(i, nbttagcompound1);
                  }
               }
            }

            return compound;
         }
      });
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Profession", this.getProfession());
      compound.setInteger("Riches", this.wealth);
      compound.setInteger("Career", this.careerId);
      compound.setInteger("CareerLevel", this.careerLevel);
      compound.setBoolean("Willing", this.isWillingToMate);
      if (this.buyingList != null) {
         compound.setTag("Offers", this.buyingList.getRecipiesAsTags());
      }

      QW nbttaglist = new QW();

      for(int i = 0; i < this.villagerInventory.getSizeInventory(); ++i) {
         Qy itemstack = this.villagerInventory.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            nbttaglist.appendTag(itemstack.writeToNBT(new QQ()));
         }
      }

      compound.setTag("Inventory", nbttaglist);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setProfession(compound.getInteger("Profession"));
      this.wealth = compound.getInteger("Riches");
      this.careerId = compound.getInteger("Career");
      this.careerLevel = compound.getInteger("CareerLevel");
      this.isWillingToMate = compound.getBoolean("Willing");
      if (compound.hasKey("Offers", 10)) {
         QQ nbttagcompound = compound.getCompoundTag("Offers");
         this.buyingList = new YX(nbttagcompound);
      }

      QW nbttaglist = compound.getTagList("Inventory", 10);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         Qy itemstack = new Qy(nbttaglist.getCompoundTagAt(i));
         if (!itemstack.isEmpty()) {
            this.villagerInventory.addItem(itemstack);
         }
      }

      this.setCanPickUpLoot(true);
      this.setAdditionalAItasks();
   }

   protected boolean canDespawn() {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return this.isTrading() ? NO.ENTITY_VILLAGER_TRADING : NO.ENTITY_VILLAGER_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_VILLAGER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_VILLAGER_DEATH;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_VILLAGER;
   }

   public void setProfession(int professionId) {
      this.dataManager.set(PROFESSION, professionId);
   }

   public int getProfession() {
      return Math.max((Integer)this.dataManager.get(PROFESSION) % 6, 0);
   }

   public boolean isMating() {
      return this.isMating;
   }

   public void setMating(boolean mating) {
      this.isMating = mating;
   }

   public void setPlaying(boolean playing) {
      this.isPlaying = playing;
   }

   public boolean isPlaying() {
      return this.isPlaying;
   }

   public void setRevengeTarget(@Nullable Iw livingBase) {
      super.setRevengeTarget(livingBase);
      if (this.village != null && livingBase != null) {
         this.village.addOrRenewAgressor(livingBase);
         if (livingBase instanceof ME) {
            int i = -1;
            if (this.isChild()) {
               i = -3;
            }

            this.village.modifyPlayerReputation(livingBase.getName(), i);
            if (this.isEntityAlive()) {
               this.world.setEntityState(this, (byte)13);
            }
         }
      }

   }

   public void onDeath(DamageSource cause) {
      if (this.village != null) {
         Ig entity = cause.getTrueSource();
         if (entity != null) {
            if (entity instanceof ME) {
               this.village.modifyPlayerReputation(entity.getName(), -2);
            } else if (entity instanceof Lo) {
               this.village.endMatingSeason();
            }
         } else {
            ME entityplayer = this.world.getClosestPlayerToEntity(this, 16.0);
            if (entityplayer != null) {
               this.village.endMatingSeason();
            }
         }
      }

      super.onDeath(cause);
   }

   public void setCustomer(@Nullable ME player) {
      this.buyingPlayer = player;
   }

   @Nullable
   public ME getCustomer() {
      return this.buyingPlayer;
   }

   public boolean isTrading() {
      return this.buyingPlayer != null;
   }

   public boolean getIsWillingToMate(boolean updateFirst) {
      if (!this.isWillingToMate && updateFirst && this.hasEnoughFoodToBreed()) {
         boolean flag = false;

         for(int i = 0; i < this.villagerInventory.getSizeInventory(); ++i) {
            Qy itemstack = this.villagerInventory.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
               if (itemstack.getItem() == NK.BREAD && itemstack.getCount() >= 3) {
                  flag = true;
                  this.villagerInventory.decrStackSize(i, 3);
               } else if ((itemstack.getItem() == NK.POTATO || itemstack.getItem() == NK.CARROT) && itemstack.getCount() >= 12) {
                  flag = true;
                  this.villagerInventory.decrStackSize(i, 12);
               }
            }

            if (flag) {
               this.world.setEntityState(this, (byte)18);
               this.isWillingToMate = true;
               break;
            }
         }
      }

      return this.isWillingToMate;
   }

   public void setIsWillingToMate(boolean isWillingToMate) {
      this.isWillingToMate = isWillingToMate;
   }

   public void useRecipe(YW recipe) {
      recipe.incrementToolUses();
      this.livingSoundTime = -this.getTalkInterval();
      this.playSound(NO.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      int i = 3 + this.rand.nextInt(4);
      if (recipe.getToolUses() == 1 || this.rand.nextInt(5) == 0) {
         this.timeUntilReset = 40;
         this.needsInitilization = true;
         this.isWillingToMate = true;
         if (this.buyingPlayer != null) {
            this.lastBuyingPlayer = this.buyingPlayer.getName();
         } else {
            this.lastBuyingPlayer = null;
         }

         i += 5;
      }

      if (recipe.getItemToBuy().getItem() == NK.EMERALD) {
         this.wealth += recipe.getItemToBuy().getCount();
      }

      if (recipe.getRewardsExp()) {
         this.world.spawnEntity(new Js(this.world, this.posX, this.posY + 0.5, this.posZ, i));
      }

      if (this.buyingPlayer instanceof MG) {
         bY.VILLAGER_TRADE.trigger((MG)this.buyingPlayer, this, recipe.getItemToSell());
      }

   }

   public void verifySellingItem(Qy stack) {
      if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
         this.livingSoundTime = -this.getTalkInterval();
         this.playSound(stack.isEmpty() ? NO.ENTITY_VILLAGER_NO : NO.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   @Nullable
   public YX getRecipes(ME player) {
      if (this.buyingList == null) {
         this.populateBuyingList();
      }

      return this.buyingList;
   }

   private void populateBuyingList() {
      Mk[][][] aentityvillager$itradelist = DEFAULT_TRADE_LIST_MAP[this.getProfession()];
      if (this.careerId != 0 && this.careerLevel != 0) {
         ++this.careerLevel;
      } else {
         this.careerId = this.rand.nextInt(aentityvillager$itradelist.length) + 1;
         this.careerLevel = 1;
      }

      if (this.buyingList == null) {
         this.buyingList = new YX();
      }

      int i = this.careerId - 1;
      int j = this.careerLevel - 1;
      if (i >= 0 && i < aentityvillager$itradelist.length) {
         Mk[][] aentityvillager$itradelist1 = aentityvillager$itradelist[i];
         if (j >= 0 && j < aentityvillager$itradelist1.length) {
            Mk[] aentityvillager$itradelist2 = aentityvillager$itradelist1[j];
            Mk[] var6 = aentityvillager$itradelist2;
            int var7 = aentityvillager$itradelist2.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               Mk entityvillager$itradelist = var6[var8];
               entityvillager$itradelist.addMerchantRecipe(this, this.buyingList, this.rand);
            }
         }
      }

   }

   public void setRecipes(@Nullable YX recipeList) {
   }

   public bij getWorld() {
      return this.world;
   }

   public BlockPos getPos() {
      return new BlockPos(this);
   }

   public ITextComponent getDisplayName() {
      WE team = this.getTeam();
      String s = this.getCustomNameTag();
      if (s != null && !s.isEmpty()) {
         TextComponentString textcomponentstring = new TextComponentString(WA.formatPlayerName(team, s));
         textcomponentstring.getStyle().setHoverEvent(this.getHoverEvent());
         textcomponentstring.getStyle().setInsertion(this.getCachedUniqueIdString());
         return textcomponentstring;
      } else {
         if (this.buyingList == null) {
            this.populateBuyingList();
         }

         String s1 = null;
         switch (this.getProfession()) {
            case 0:
               if (this.careerId == 1) {
                  s1 = "farmer";
               } else if (this.careerId == 2) {
                  s1 = "fisherman";
               } else if (this.careerId == 3) {
                  s1 = "shepherd";
               } else if (this.careerId == 4) {
                  s1 = "fletcher";
               }
               break;
            case 1:
               if (this.careerId == 1) {
                  s1 = "librarian";
               } else if (this.careerId == 2) {
                  s1 = "cartographer";
               }
               break;
            case 2:
               s1 = "cleric";
               break;
            case 3:
               if (this.careerId == 1) {
                  s1 = "armor";
               } else if (this.careerId == 2) {
                  s1 = "weapon";
               } else if (this.careerId == 3) {
                  s1 = "tool";
               }
               break;
            case 4:
               if (this.careerId == 1) {
                  s1 = "butcher";
               } else if (this.careerId == 2) {
                  s1 = "leather";
               }
               break;
            case 5:
               s1 = "nitwit";
         }

         if (s1 != null) {
            ITextComponent itextcomponent = new TextComponentTranslation("entity.Villager." + s1, new Object[0]);
            itextcomponent.getStyle().setHoverEvent(this.getHoverEvent());
            itextcomponent.getStyle().setInsertion(this.getCachedUniqueIdString());
            if (team != null) {
               itextcomponent.getStyle().setColor(team.getColor());
            }

            return itextcomponent;
         } else {
            return super.getDisplayName();
         }
      }
   }

   public float getEyeHeight() {
      return this.isChild() ? 0.81F : 1.62F;
   }

   public void handleStatusUpdate(byte id) {
      if (id == 12) {
         this.spawnParticles(EnumParticleTypes.HEART);
      } else if (id == 13) {
         this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
      } else if (id == 14) {
         this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
      } else {
         super.handleStatusUpdate(id);
      }

   }

   private void spawnParticles(EnumParticleTypes particleType) {
      for(int i = 0; i < 5; ++i) {
         double d0 = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         this.world.spawnParticle(particleType, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      return this.finalizeMobSpawn(difficulty, livingdata, true);
   }

   public ID finalizeMobSpawn(baL p_190672_1_, @Nullable ID p_190672_2_, boolean p_190672_3_) {
      p_190672_2_ = super.onInitialSpawn(p_190672_1_, p_190672_2_);
      if (p_190672_3_) {
         this.setProfession(this.world.rand.nextInt(6));
      }

      this.setAdditionalAItasks();
      this.populateBuyingList();
      return p_190672_2_;
   }

   public void setLookingForHome() {
      this.isLookingForHome = true;
   }

   public Mq createChild(Ih ageable) {
      Mq entityvillager = new Mq(this.world);
      entityvillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (ID)null);
      return entityvillager;
   }

   public boolean canBeLeashedTo(ME player) {
      return false;
   }

   public void onStruckByLightning(HX lightningBolt) {
      if (!this.world.isRemote && !this.isDead) {
         Lg entitywitch = new Lg(this.world);
         entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         entitywitch.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitywitch)), (ID)null);
         entitywitch.setNoAI(this.isAIDisabled());
         if (this.hasCustomName()) {
            entitywitch.setCustomNameTag(this.getCustomNameTag());
            entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
         }

         this.world.spawnEntity(entitywitch);
         this.setDead();
      }

   }

   public InventoryBasic getVillagerInventory() {
      return this.villagerInventory;
   }

   protected void updateEquipmentIfNeeded(IY itemEntity) {
      Qy itemstack = itemEntity.getItem();
      OL item = itemstack.getItem();
      if (this.canVillagerPickupItem(item)) {
         Qy itemstack1 = this.villagerInventory.addItem(itemstack);
         if (itemstack1.isEmpty()) {
            itemEntity.setDead();
         } else {
            itemstack.setCount(itemstack1.getCount());
         }
      }

   }

   private boolean canVillagerPickupItem(OL itemIn) {
      return itemIn == NK.BREAD || itemIn == NK.POTATO || itemIn == NK.CARROT || itemIn == NK.WHEAT || itemIn == NK.WHEAT_SEEDS || itemIn == NK.BEETROOT || itemIn == NK.BEETROOT_SEEDS;
   }

   public boolean hasEnoughFoodToBreed() {
      return this.hasEnoughItems(1);
   }

   public boolean canAbondonItems() {
      return this.hasEnoughItems(2);
   }

   public boolean wantsMoreFood() {
      boolean flag = this.getProfession() == 0;
      if (flag) {
         return !this.hasEnoughItems(5);
      } else {
         return !this.hasEnoughItems(1);
      }
   }

   private boolean hasEnoughItems(int multiplier) {
      boolean flag = this.getProfession() == 0;

      for(int i = 0; i < this.villagerInventory.getSizeInventory(); ++i) {
         Qy itemstack = this.villagerInventory.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            if (itemstack.getItem() == NK.BREAD && itemstack.getCount() >= 3 * multiplier || itemstack.getItem() == NK.POTATO && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == NK.CARROT && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == NK.BEETROOT && itemstack.getCount() >= 12 * multiplier) {
               return true;
            }

            if (flag && itemstack.getItem() == NK.WHEAT && itemstack.getCount() >= 9 * multiplier) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isFarmItemInInventory() {
      for(int i = 0; i < this.villagerInventory.getSizeInventory(); ++i) {
         Qy itemstack = this.villagerInventory.getStackInSlot(i);
         if (!itemstack.isEmpty() && (itemstack.getItem() == NK.WHEAT_SEEDS || itemstack.getItem() == NK.POTATO || itemstack.getItem() == NK.CARROT || itemstack.getItem() == NK.BEETROOT_SEEDS)) {
            return true;
         }
      }

      return false;
   }

   public boolean replaceItemInInventory(int inventorySlot, Qy itemStackIn) {
      if (super.replaceItemInInventory(inventorySlot, itemStackIn)) {
         return true;
      } else {
         int i = inventorySlot - 300;
         if (i >= 0 && i < this.villagerInventory.getSizeInventory()) {
            this.villagerInventory.setInventorySlotContents(i, itemStackIn);
            return true;
         } else {
            return false;
         }
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   // $FF: synthetic method
   static Logger access$000() {
      return LOGGER;
   }

   static {
      PROFESSION = Rv.createKey(Mq.class, Rt.VARINT);
      DEFAULT_TRADE_LIST_MAP = new Mk[][][][]{{{{new Mi(NK.WHEAT, new Mo(18, 22)), new Mi(NK.POTATO, new Mo(15, 19)), new Mi(NK.CARROT, new Mo(15, 19)), new Mn(NK.BREAD, new Mo(-4, -2))}, {new Mi(OL.getItemFromBlock(Nk.PUMPKIN), new Mo(8, 13)), new Mn(NK.PUMPKIN_PIE, new Mo(-3, -2))}, {new Mi(OL.getItemFromBlock(Nk.MELON_BLOCK), new Mo(7, 12)), new Mn(NK.APPLE, new Mo(-7, -5))}, {new Mn(NK.COOKIE, new Mo(-10, -6)), new Mn(NK.CAKE, new Mo(1, 1))}}, {{new Mi(NK.STRING, new Mo(15, 20)), new Mi(NK.COAL, new Mo(16, 24)), new Mj(NK.FISH, new Mo(6, 6), NK.COOKED_FISH, new Mo(6, 6))}, {new Mm(NK.FISHING_ROD, new Mo(7, 8))}}, {{new Mi(OL.getItemFromBlock(Nk.WOOL), new Mo(16, 22)), new Mn(NK.SHEARS, new Mo(3, 4))}, {new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL)), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 1), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 2), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 3), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 4), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 5), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 6), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 7), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 8), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 9), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 10), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 11), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 12), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 13), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 14), new Mo(1, 2)), new Mn(new Qy(OL.getItemFromBlock(Nk.WOOL), 1, 15), new Mo(1, 2))}}, {{new Mi(NK.STRING, new Mo(15, 20)), new Mn(NK.ARROW, new Mo(-12, -8))}, {new Mn(NK.BOW, new Mo(2, 3)), new Mj(OL.getItemFromBlock(Nk.GRAVEL), new Mo(10, 10), NK.FLINT, new Mo(6, 10))}}}, {{{new Mi(NK.PAPER, new Mo(24, 36)), new Ml()}, {new Mi(NK.BOOK, new Mo(8, 10)), new Mn(NK.COMPASS, new Mo(10, 12)), new Mn(OL.getItemFromBlock(Nk.BOOKSHELF), new Mo(3, 4))}, {new Mi(NK.WRITTEN_BOOK, new Mo(2, 2)), new Mn(NK.CLOCK, new Mo(10, 12)), new Mn(OL.getItemFromBlock(Nk.GLASS), new Mo(-5, -3))}, {new Ml()}, {new Ml()}, {new Mn(NK.NAME_TAG, new Mo(20, 22))}}, {{new Mi(NK.PAPER, new Mo(24, 36))}, {new Mi(NK.COMPASS, new Mo(1, 1))}, {new Mn(NK.MAP, new Mo(7, 11))}, {new Mp(new Mo(12, 20), "Monument", bhF.MONUMENT), new Mp(new Mo(16, 28), "Mansion", bhF.MANSION)}}}, {{{new Mi(NK.ROTTEN_FLESH, new Mo(36, 40)), new Mi(NK.GOLD_INGOT, new Mo(8, 10))}, {new Mn(NK.REDSTONE, new Mo(-4, -1)), new Mn(new Qy(NK.DYE, 1, Om.BLUE.getDyeDamage()), new Mo(-2, -1))}, {new Mn(NK.ENDER_PEARL, new Mo(4, 7)), new Mn(OL.getItemFromBlock(Nk.GLOWSTONE), new Mo(-3, -1))}, {new Mn(NK.EXPERIENCE_BOTTLE, new Mo(3, 11))}}}, {{{new Mi(NK.COAL, new Mo(16, 24)), new Mn(NK.IRON_HELMET, new Mo(4, 6))}, {new Mi(NK.IRON_INGOT, new Mo(7, 9)), new Mn(NK.IRON_CHESTPLATE, new Mo(10, 14))}, {new Mi(NK.DIAMOND, new Mo(3, 4)), new Mm(NK.DIAMOND_CHESTPLATE, new Mo(16, 19))}, {new Mn(NK.CHAINMAIL_BOOTS, new Mo(5, 7)), new Mn(NK.CHAINMAIL_LEGGINGS, new Mo(9, 11)), new Mn(NK.CHAINMAIL_HELMET, new Mo(5, 7)), new Mn(NK.CHAINMAIL_CHESTPLATE, new Mo(11, 15))}}, {{new Mi(NK.COAL, new Mo(16, 24)), new Mn(NK.IRON_AXE, new Mo(6, 8))}, {new Mi(NK.IRON_INGOT, new Mo(7, 9)), new Mm(NK.IRON_SWORD, new Mo(9, 10))}, {new Mi(NK.DIAMOND, new Mo(3, 4)), new Mm(NK.DIAMOND_SWORD, new Mo(12, 15)), new Mm(NK.DIAMOND_AXE, new Mo(9, 12))}}, {{new Mi(NK.COAL, new Mo(16, 24)), new Mm(NK.IRON_SHOVEL, new Mo(5, 7))}, {new Mi(NK.IRON_INGOT, new Mo(7, 9)), new Mm(NK.IRON_PICKAXE, new Mo(9, 11))}, {new Mi(NK.DIAMOND, new Mo(3, 4)), new Mm(NK.DIAMOND_PICKAXE, new Mo(12, 15))}}}, {{{new Mi(NK.PORKCHOP, new Mo(14, 18)), new Mi(NK.CHICKEN, new Mo(14, 18))}, {new Mi(NK.COAL, new Mo(16, 24)), new Mn(NK.COOKED_PORKCHOP, new Mo(-7, -5)), new Mn(NK.COOKED_CHICKEN, new Mo(-8, -6))}}, {{new Mi(NK.LEATHER, new Mo(9, 12)), new Mn(NK.LEATHER_LEGGINGS, new Mo(2, 4))}, {new Mm(NK.LEATHER_CHESTPLATE, new Mo(7, 12))}, {new Mn(NK.SADDLE, new Mo(8, 10))}}}, {new Mk[0][]}};
   }
}
