package neo;

import com.mojang.authlib.GameProfile;
import java.io.File;
import java.io.PrintStream;
import java.util.Random;
import java.util.UUID;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NI {
   public static final PrintStream SYSOUT;
   private static boolean alreadyRegistered;
   public static boolean hasErrored;
   private static final Logger LOGGER;

   public NI() {
   }

   public static boolean isRegistered() {
      return alreadyRegistered;
   }

   static void registerDispenserBehaviors() {
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.ARROW, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            Ne entitytippedarrow = new Ne(worldIn, position.getX(), position.getY(), position.getZ());
            entitytippedarrow.pickupStatus = MN.ALLOWED;
            return entitytippedarrow;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.TIPPED_ARROW, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            Ne entitytippedarrow = new Ne(worldIn, position.getX(), position.getY(), position.getZ());
            entitytippedarrow.setPotionEffect(stackIn);
            entitytippedarrow.pickupStatus = MN.ALLOWED;
            return entitytippedarrow;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SPECTRAL_ARROW, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            MO entityarrow = new Nc(worldIn, position.getX(), position.getY(), position.getZ());
            entityarrow.pickupStatus = MN.ALLOWED;
            return entityarrow;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.EGG, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            return new MQ(worldIn, position.getX(), position.getY(), position.getZ());
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SNOWBALL, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            return new Nb(worldIn, position.getX(), position.getY(), position.getZ());
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.EXPERIENCE_BOTTLE, new EQ() {
         protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
            return new IV(worldIn, position.getX(), position.getY(), position.getZ());
         }

         protected float getProjectileInaccuracy() {
            return super.getProjectileInaccuracy() * 0.5F;
         }

         protected float getProjectileVelocity() {
            return super.getProjectileVelocity() * 1.25F;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SPLASH_POTION, new ES() {
         public Qy dispense(ET source, final Qy stack) {
            return (new EQ() {
               protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
                  return new MY(worldIn, position.getX(), position.getY(), position.getZ(), stack.copy());
               }

               protected float getProjectileInaccuracy() {
                  return super.getProjectileInaccuracy() * 0.5F;
               }

               protected float getProjectileVelocity() {
                  return super.getProjectileVelocity() * 1.25F;
               }
            }).dispense(source, stack);
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.LINGERING_POTION, new ES() {
         public Qy dispense(ET source, final Qy stack) {
            return (new EQ() {
               protected IJ getProjectileEntity(bij worldIn, EW position, Qy stackIn) {
                  return new MY(worldIn, position.getX(), position.getY(), position.getZ(), stack.copy());
               }

               protected float getProjectileInaccuracy() {
                  return super.getProjectileInaccuracy() * 0.5F;
               }

               protected float getProjectileVelocity() {
                  return super.getProjectileVelocity() * 1.25F;
               }
            }).dispense(source, stack);
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SPAWN_EGG, new EP() {
         public Qy dispenseStack(ET source, Qy stack) {
            EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
            double d0 = source.getX() + (double)enumfacing.getXOffset();
            double d1 = (double)((float)(source.getBlockPos().getY() + enumfacing.getYOffset()) + 0.2F);
            double d2 = source.getZ() + (double)enumfacing.getZOffset();
            Ig entity = PX.spawnCreature(source.getWorld(), PX.getNamedIdFrom(stack), d0, d1, d2);
            if (entity instanceof Iw && stack.hasDisplayName()) {
               entity.setCustomNameTag(stack.getDisplayName());
            }

            PX.applyItemEntityDataToEntity(source.getWorld(), (ME)null, stack, entity);
            stack.shrink(1);
            return stack;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.FIREWORKS, new EP() {
         public Qy dispenseStack(ET source, Qy stack) {
            EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
            double d0 = source.getX() + (double)enumfacing.getXOffset();
            double d1 = (double)((float)source.getBlockPos().getY() + 0.2F);
            double d2 = source.getZ() + (double)enumfacing.getZOffset();
            IX entityfireworkrocket = new IX(source.getWorld(), d0, d1, d2, stack);
            source.getWorld().spawnEntity(entityfireworkrocket);
            stack.shrink(1);
            return stack;
         }

         protected void playDispenseSound(ET source) {
            source.getWorld().playEvent(1004, source.getBlockPos(), 0);
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.FIRE_CHARGE, new EP() {
         public Qy dispenseStack(ET source, Qy stack) {
            EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
            EW iposition = dk.getDispensePosition(source);
            double d0 = iposition.getX() + (double)((float)enumfacing.getXOffset() * 0.3F);
            double d1 = iposition.getY() + (double)((float)enumfacing.getYOffset() * 0.3F);
            double d2 = iposition.getZ() + (double)((float)enumfacing.getZOffset() * 0.3F);
            bij world = source.getWorld();
            Random random = world.rand;
            double d3 = random.nextGaussian() * 0.05 + (double)enumfacing.getXOffset();
            double d4 = random.nextGaussian() * 0.05 + (double)enumfacing.getYOffset();
            double d5 = random.nextGaussian() * 0.05 + (double)enumfacing.getZOffset();
            world.spawnEntity(new Na(world, d0, d1, d2, d3, d4, d5));
            stack.shrink(1);
            return stack;
         }

         protected void playDispenseSound(ET source) {
            source.getWorld().playEvent(1018, source.getBlockPos(), 0);
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.BOAT, new NF(IQ.OAK));
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SPRUCE_BOAT, new NF(IQ.SPRUCE));
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.BIRCH_BOAT, new NF(IQ.BIRCH));
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.JUNGLE_BOAT, new NF(IQ.JUNGLE));
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.DARK_OAK_BOAT, new NF(IQ.DARK_OAK));
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.ACACIA_BOAT, new NF(IQ.ACACIA));
      ES ibehaviordispenseitem = new EP() {
         private final EP dispenseBehavior = new EP();

         public Qy dispenseStack(ET source, Qy stack) {
            Pe itembucket = (Pe)stack.getItem();
            BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
            return itembucket.tryPlaceContainedLiquid((ME)null, source.getWorld(), blockpos) ? new Qy(NK.BUCKET) : this.dispenseBehavior.dispense(source, stack);
         }
      };
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.LAVA_BUCKET, ibehaviordispenseitem);
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.WATER_BUCKET, ibehaviordispenseitem);
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.BUCKET, new EP() {
         private final EP dispenseBehavior = new EP();

         public Qy dispenseStack(ET source, Qy stack) {
            bij world = source.getWorld();
            BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
            in iblockstate = world.getBlockState(blockpos);
            co block = iblockstate.getBlock();
            hM material = iblockstate.getMaterial();
            OL item;
            if (hM.WATER.equals(material) && block instanceof eB && (Integer)iblockstate.getValue(eB.LEVEL) == 0) {
               item = NK.WATER_BUCKET;
            } else {
               if (!hM.LAVA.equals(material) || !(block instanceof eB) || (Integer)iblockstate.getValue(eB.LEVEL) != 0) {
                  return super.dispenseStack(source, stack);
               }

               item = NK.LAVA_BUCKET;
            }

            world.setBlockToAir(blockpos);
            stack.shrink(1);
            if (stack.isEmpty()) {
               return new Qy(item);
            } else {
               if (((Yt)source.getBlockTileEntity()).addItemStack(new Qy(item)) < 0) {
                  this.dispenseBehavior.dispense(source, new Qy(item));
               }

               return stack;
            }
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.FLINT_AND_STEEL, new NG() {
         protected Qy dispenseStack(ET source, Qy stack) {
            bij world = source.getWorld();
            this.successful = true;
            BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
            if (world.isAirBlock(blockpos)) {
               world.setBlockState(blockpos, Nk.FIRE.getDefaultState());
               if (stack.attemptDamageItem(1, world.rand, (MG)null)) {
                  stack.setCount(0);
               }
            } else if (world.getBlockState(blockpos).getBlock() == Nk.TNT) {
               Nk.TNT.onPlayerDestroy(world, blockpos, Nk.TNT.getDefaultState().withProperty(hl.EXPLODE, true));
               world.setBlockToAir(blockpos);
            } else {
               this.successful = false;
            }

            return stack;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.DYE, new NG() {
         protected Qy dispenseStack(ET source, Qy stack) {
            this.successful = true;
            if (Om.WHITE == Om.byDyeDamage(stack.getMetadata())) {
               bij world = source.getWorld();
               BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
               if (Pq.applyBonemeal(stack, world, blockpos)) {
                  if (!world.isRemote) {
                     world.playEvent(2005, blockpos, 0);
                  }
               } else {
                  this.successful = false;
               }

               return stack;
            } else {
               return super.dispenseStack(source, stack);
            }
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(OL.getItemFromBlock(Nk.TNT), new EP() {
         protected Qy dispenseStack(ET source, Qy stack) {
            bij world = source.getWorld();
            BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
            Jr entitytntprimed = new Jr(world, (double)blockpos.getX() + 0.5, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5, (Iw)null);
            world.spawnEntity(entitytntprimed);
            world.playSound((ME)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, NO.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            stack.shrink(1);
            return stack;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(NK.SKULL, new NG() {
         protected Qy dispenseStack(ET source, Qy stack) {
            bij world = source.getWorld();
            EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
            BlockPos blockpos = source.getBlockPos().offset(enumfacing);
            gE blockskull = Nk.SKULL;
            this.successful = true;
            if (world.isAirBlock(blockpos) && blockskull.canDispenserPlace(world, blockpos, stack)) {
               if (!world.isRemote) {
                  world.setBlockState(blockpos, blockskull.getDefaultState().withProperty(gE.FACING, EnumFacing.UP), 3);
                  Yg tileentity = world.getTileEntity(blockpos);
                  if (tileentity instanceof YR) {
                     if (stack.getMetadata() == 3) {
                        GameProfile gameprofile = null;
                        if (stack.hasTagCompound()) {
                           QQ nbttagcompound = stack.getTagCompound();
                           if (nbttagcompound.hasKey("SkullOwner", 10)) {
                              gameprofile = Rb.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                           } else if (nbttagcompound.hasKey("SkullOwner", 8)) {
                              String s = nbttagcompound.getString("SkullOwner");
                              if (!StringUtils.isNullOrEmpty(s)) {
                                 gameprofile = new GameProfile((UUID)null, s);
                              }
                           }
                        }

                        ((YR)tileentity).setPlayerProfile(gameprofile);
                     } else {
                        ((YR)tileentity).setType(stack.getMetadata());
                     }

                     ((YR)tileentity).setSkullRotation(enumfacing.getOpposite().getHorizontalIndex() * 4);
                     Nk.SKULL.checkWitherSpawn(world, blockpos, (YR)tileentity);
                  }

                  stack.shrink(1);
               }
            } else if (OR.dispenseArmor(source, stack).isEmpty()) {
               this.successful = false;
            }

            return stack;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(OL.getItemFromBlock(Nk.PUMPKIN), new NG() {
         protected Qy dispenseStack(ET source, Qy stack) {
            bij world = source.getWorld();
            BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(dk.FACING));
            fx blockpumpkin = (fx)Nk.PUMPKIN;
            this.successful = true;
            if (world.isAirBlock(blockpos) && blockpumpkin.canDispenserPlace(world, blockpos)) {
               if (!world.isRemote) {
                  world.setBlockState(blockpos, blockpumpkin.getDefaultState(), 3);
               }

               stack.shrink(1);
            } else {
               Qy itemstack = OR.dispenseArmor(source, stack);
               if (itemstack.isEmpty()) {
                  this.successful = false;
               }
            }

            return stack;
         }
      });
      Om[] var1 = Om.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Om enumdyecolor = var1[var3];
         dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(OL.getItemFromBlock(gr.getBlockByColor(enumdyecolor)), new NH());
      }

   }

   public static void register() {
      if (!alreadyRegistered) {
         alreadyRegistered = true;
         redirectOutputToLog();
         SoundEvent.registerSounds();
         co.registerBlocks();
         dN.init();
         VW.registerPotions();
         Fa.registerEnchantments();
         OL.registerItems();
         Wf.registerPotionTypes();
         We.init();
         Ir.init();
         Zi.registerBiomes();
         registerDispenserBehaviors();
         if (!NP.init()) {
            hasErrored = true;
            LOGGER.error("Errors with built-in recipes!");
         }

         XV.init();
         if (LOGGER.isDebugEnabled()) {
            if ((new f((File)null)).hasErrored()) {
               hasErrored = true;
               LOGGER.error("Errors with built-in advancements!");
            }

            if (!bhq.test()) {
               hasErrored = true;
               LOGGER.error("Errors with built-in loot tables");
            }
         }
      }

   }

   private static void redirectOutputToLog() {
      if (LOGGER.isDebugEnabled()) {
         System.setErr(new WF("STDERR", System.err));
         System.setOut(new WF("STDOUT", SYSOUT));
      } else {
         System.setErr(new LoggingPrintStream("STDERR", System.err));
         System.setOut(new LoggingPrintStream("STDOUT", SYSOUT));
      }

   }

   public static void printToSYSOUT(String message) {
      SYSOUT.println(message);
   }

   static {
      SYSOUT = System.out;
      LOGGER = LogManager.getLogger();
   }
}
