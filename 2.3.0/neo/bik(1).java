package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

public final class bik {
   private static final int MOB_COUNT_DIV = (int)Math.pow(17.0, 2.0);
   private final Set<ChunkPos> eligibleChunksForSpawning = Sets.newHashSet();
   private Map<Class, Iu> mapSampleEntitiesByClass = new HashMap();
   private int lastPlayerChunkX = Integer.MAX_VALUE;
   private int lastPlayerChunkZ = Integer.MAX_VALUE;
   private int countChunkPos;

   public bik() {
   }

   public int findChunksForSpawning(bis worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
      if (!spawnHostileMobs && !spawnPeacefulMobs) {
         return 0;
      } else {
         boolean flag = true;
         ME entityplayer = null;
         if (worldServerIn.playerEntities.size() == 1) {
            entityplayer = (ME)worldServerIn.playerEntities.get(0);
            if (this.eligibleChunksForSpawning.size() > 0 && entityplayer != null && entityplayer.chunkCoordX == this.lastPlayerChunkX && entityplayer.chunkCoordZ == this.lastPlayerChunkZ) {
               flag = false;
            }
         }

         int k4;
         int i1;
         if (flag) {
            this.eligibleChunksForSpawning.clear();
            k4 = 0;
            Iterator var8 = worldServerIn.playerEntities.iterator();

            label209:
            while(true) {
               ME entityplayer1;
               do {
                  if (!var8.hasNext()) {
                     this.countChunkPos = k4;
                     if (entityplayer != null) {
                        this.lastPlayerChunkX = entityplayer.chunkCoordX;
                        this.lastPlayerChunkZ = entityplayer.chunkCoordZ;
                     }
                     break label209;
                  }

                  entityplayer1 = (ME)var8.next();
               } while(entityplayer1.isSpectator());

               int j = MathHelper.floor(entityplayer1.posX / 16.0);
               int k = MathHelper.floor(entityplayer1.posZ / 16.0);
               int l = true;

               for(i1 = -8; i1 <= 8; ++i1) {
                  for(int j1 = -8; j1 <= 8; ++j1) {
                     boolean flag1 = i1 == -8 || i1 == 8 || j1 == -8 || j1 == 8;
                     ChunkPos chunkpos = new ChunkPos(i1 + j, j1 + k);
                     if (!this.eligibleChunksForSpawning.contains(chunkpos)) {
                        ++k4;
                        if (!flag1 && worldServerIn.getWorldBorder().contains(chunkpos)) {
                           WV playerchunkmapentry = worldServerIn.getPlayerChunkMap().getEntry(chunkpos.x, chunkpos.z);
                           if (playerchunkmapentry != null && playerchunkmapentry.isSentToPlayers()) {
                              this.eligibleChunksForSpawning.add(chunkpos);
                           }
                        }
                     }
                  }
               }
            }
         }

         k4 = 0;
         BlockPos blockpos1 = worldServerIn.getSpawnPoint();
         biN blockposm = new biN(0, 0, 0);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
         IC[] var44 = IC.values();
         int var45 = var44.length;

         label182:
         for(i1 = 0; i1 < var45; ++i1) {
            IC enumcreaturetype = var44[i1];
            if ((!enumcreaturetype.getPeacefulCreature() || spawnPeacefulMobs) && (enumcreaturetype.getPeacefulCreature() || spawnHostileMobs) && (!enumcreaturetype.getAnimal() || spawnOnSetTickRate)) {
               int l4 = bnK.ForgeWorld_countEntities.exists() ? bnK.callInt(worldServerIn, bnK.ForgeWorld_countEntities, enumcreaturetype, true) : worldServerIn.countEntities(enumcreaturetype.getCreatureClass());
               int i5 = enumcreaturetype.getMaxNumberOfCreature() * this.countChunkPos / MOB_COUNT_DIV;
               if (l4 <= i5) {
                  Collection<ChunkPos> collection = this.eligibleChunksForSpawning;
                  if (bnK.ForgeHooksClient.exists()) {
                     ArrayList<ChunkPos> arraylist = Lists.newArrayList((Iterable)collection);
                     Collections.shuffle(arraylist);
                     collection = arraylist;
                  }

                  Iterator var50 = ((Collection)collection).iterator();

                  label179:
                  while(true) {
                     int k1;
                     int l1;
                     int i2;
                     in iblockstate;
                     do {
                        if (!var50.hasNext()) {
                           continue label182;
                        }

                        ChunkPos chunkpos1 = (ChunkPos)var50.next();
                        BlockPos blockpos = getRandomChunkPosition(worldServerIn, chunkpos1.x, chunkpos1.z, blockposm);
                        k1 = ((BlockPos)blockpos).getX();
                        l1 = ((BlockPos)blockpos).getY();
                        i2 = ((BlockPos)blockpos).getZ();
                        iblockstate = worldServerIn.getBlockState(blockpos);
                     } while(iblockstate.isNormalCube());

                     int j2 = 0;

                     for(int k2 = 0; k2 < 3; ++k2) {
                        int l2 = k1;
                        int i3 = l1;
                        int j3 = i2;
                        int k3 = true;
                        Zg biome$spawnlistentry = null;
                        ID ientitylivingdata = null;
                        int l3 = MathHelper.ceil(Math.random() * 4.0);

                        for(int i4 = 0; i4 < l3; ++i4) {
                           l2 += worldServerIn.rand.nextInt(6) - worldServerIn.rand.nextInt(6);
                           i3 += worldServerIn.rand.nextInt(1) - worldServerIn.rand.nextInt(1);
                           j3 += worldServerIn.rand.nextInt(6) - worldServerIn.rand.nextInt(6);
                           blockpos$mutableblockpos.setPos(l2, i3, j3);
                           float f = (float)l2 + 0.5F;
                           float f1 = (float)j3 + 0.5F;
                           if (!worldServerIn.isAnyPlayerWithinRangeAt((double)f, (double)i3, (double)f1, 24.0) && blockpos1.distanceSq((double)f, (double)i3, (double)f1) >= 576.0) {
                              if (biome$spawnlistentry == null) {
                                 biome$spawnlistentry = worldServerIn.getSpawnListEntryForTypeAt(enumcreaturetype, blockpos$mutableblockpos);
                                 if (biome$spawnlistentry == null) {
                                    break;
                                 }
                              }

                              if (worldServerIn.canCreatureTypeSpawnHere(enumcreaturetype, biome$spawnlistentry, blockpos$mutableblockpos) && canCreatureTypeSpawnAtLocation(Ix.getPlacementForEntity(biome$spawnlistentry.entityClass), worldServerIn, blockpos$mutableblockpos)) {
                                 Iu entityliving;
                                 try {
                                    entityliving = (Iu)this.mapSampleEntitiesByClass.get(biome$spawnlistentry.entityClass);
                                    if (entityliving == null) {
                                       if (bnK.ForgeBiomeSpawnListEntry_newInstance.exists()) {
                                          entityliving = (Iu)bnK.call(biome$spawnlistentry, bnK.ForgeBiomeSpawnListEntry_newInstance, worldServerIn);
                                       } else {
                                          entityliving = (Iu)biome$spawnlistentry.entityClass.getConstructor(bij.class).newInstance(worldServerIn);
                                       }

                                       this.mapSampleEntitiesByClass.put(biome$spawnlistentry.entityClass, entityliving);
                                    }
                                 } catch (Exception var40) {
                                    Exception exception = var40;
                                    exception.printStackTrace();
                                    return k4;
                                 }

                                 entityliving.setLocationAndAngles((double)f, (double)i3, (double)f1, worldServerIn.rand.nextFloat() * 360.0F, 0.0F);
                                 boolean flag2 = bnK.ForgeEventFactory_canEntitySpawn.exists() ? bnQ.canEntitySpawn(entityliving, worldServerIn, f, (float)i3, f1) : entityliving.getCanSpawnHere() && entityliving.isNotColliding();
                                 if (flag2) {
                                    this.mapSampleEntitiesByClass.remove(biome$spawnlistentry.entityClass);
                                    if (!bnQ.doSpecialSpawn(entityliving, worldServerIn, f, i3, f1)) {
                                       ientitylivingdata = entityliving.onInitialSpawn(worldServerIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
                                    }

                                    if (entityliving.isNotColliding()) {
                                       ++j2;
                                       worldServerIn.spawnEntity(entityliving);
                                    } else {
                                       entityliving.setDead();
                                    }

                                    int j4 = bnK.ForgeEventFactory_getMaxSpawnPackSize.exists() ? bnK.callInt(bnK.ForgeEventFactory_getMaxSpawnPackSize, entityliving) : entityliving.getMaxSpawnedInChunk();
                                    if (j2 >= j4) {
                                       continue label179;
                                    }
                                 }

                                 k4 += j2;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return k4;
      }
   }

   private static BlockPos getRandomChunkPosition(bij worldIn, int x, int z) {
      bam chunk = worldIn.getChunk(x, z);
      int i = x * 16 + worldIn.rand.nextInt(16);
      int j = z * 16 + worldIn.rand.nextInt(16);
      int k = MathHelper.roundUp(chunk.getHeight(new BlockPos(i, 0, j)) + 1, 16);
      int l = worldIn.rand.nextInt(k > 0 ? k : chunk.getTopFilledSegment() + 16 - 1);
      return new BlockPos(i, l, j);
   }

   private static biN getRandomChunkPosition(bij p_getRandomChunkPosition_0_, int p_getRandomChunkPosition_1_, int p_getRandomChunkPosition_2_, biN p_getRandomChunkPosition_3_) {
      bam chunk = p_getRandomChunkPosition_0_.getChunk(p_getRandomChunkPosition_1_, p_getRandomChunkPosition_2_);
      int i = p_getRandomChunkPosition_1_ * 16 + p_getRandomChunkPosition_0_.rand.nextInt(16);
      int j = p_getRandomChunkPosition_2_ * 16 + p_getRandomChunkPosition_0_.rand.nextInt(16);
      int k = MathHelper.roundUp(chunk.getHeightValue(i & 15, j & 15) + 1, 16);
      int l = p_getRandomChunkPosition_0_.rand.nextInt(k > 0 ? k : chunk.getTopFilledSegment() + 16 - 1);
      p_getRandomChunkPosition_3_.setXyz(i, l, j);
      return p_getRandomChunkPosition_3_;
   }

   public static boolean isValidEmptySpawnBlock(in state) {
      if (state.isBlockNormalCube()) {
         return false;
      } else if (state.canProvidePower()) {
         return false;
      } else if (state.getMaterial().isLiquid()) {
         return false;
      } else {
         return !fK.isRailBlock(state);
      }
   }

   public static boolean canCreatureTypeSpawnAtLocation(It spawnPlacementTypeIn, bij worldIn, BlockPos pos) {
      if (!worldIn.getWorldBorder().contains(pos)) {
         return false;
      } else {
         return spawnPlacementTypeIn == null ? false : spawnPlacementTypeIn.canSpawnAt(worldIn, pos);
      }
   }

   public static boolean canCreatureTypeSpawnBody(It p_canCreatureTypeSpawnBody_0_, bij p_canCreatureTypeSpawnBody_1_, BlockPos p_canCreatureTypeSpawnBody_2_) {
      in iblockstate = p_canCreatureTypeSpawnBody_1_.getBlockState(p_canCreatureTypeSpawnBody_2_);
      if (p_canCreatureTypeSpawnBody_0_ == It.IN_WATER) {
         return iblockstate.getMaterial() == hM.WATER && p_canCreatureTypeSpawnBody_1_.getBlockState(p_canCreatureTypeSpawnBody_2_.down()).getMaterial() == hM.WATER && !p_canCreatureTypeSpawnBody_1_.getBlockState(p_canCreatureTypeSpawnBody_2_.up()).isNormalCube();
      } else {
         BlockPos blockpos = p_canCreatureTypeSpawnBody_2_.down();
         in iblockstate1 = p_canCreatureTypeSpawnBody_1_.getBlockState(blockpos);
         boolean flag = bnK.ForgeBlock_canCreatureSpawn.exists() ? bnK.callBoolean(iblockstate1.getBlock(), bnK.ForgeBlock_canCreatureSpawn, iblockstate1, p_canCreatureTypeSpawnBody_1_, blockpos, p_canCreatureTypeSpawnBody_0_) : iblockstate1.isTopSolid();
         if (!flag) {
            return false;
         } else {
            co block = p_canCreatureTypeSpawnBody_1_.getBlockState(blockpos).getBlock();
            boolean flag1 = block != Nk.BEDROCK && block != Nk.BARRIER;
            return flag1 && isValidEmptySpawnBlock(iblockstate) && isValidEmptySpawnBlock(p_canCreatureTypeSpawnBody_1_.getBlockState(p_canCreatureTypeSpawnBody_2_.up()));
         }
      }
   }

   public static void performWorldGenSpawning(bij worldIn, Zi biomeIn, int centerX, int centerZ, int diameterX, int diameterZ, Random randomIn) {
      List<Zg> list = biomeIn.getSpawnableList(IC.CREATURE);
      if (!list.isEmpty()) {
         while(randomIn.nextFloat() < biomeIn.getSpawningChance()) {
            Zg biome$spawnlistentry = (Zg)WeightedRandom.getRandomItem(worldIn.rand, list);
            int i = biome$spawnlistentry.minGroupCount + randomIn.nextInt(1 + biome$spawnlistentry.maxGroupCount - biome$spawnlistentry.minGroupCount);
            ID ientitylivingdata = null;
            int j = centerX + randomIn.nextInt(diameterX);
            int k = centerZ + randomIn.nextInt(diameterZ);
            int l = j;
            int i1 = k;

            for(int j1 = 0; j1 < i; ++j1) {
               boolean flag = false;

               for(int k1 = 0; !flag && k1 < 4; ++k1) {
                  BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(j, 0, k));
                  if (canCreatureTypeSpawnAtLocation(It.ON_GROUND, worldIn, blockpos)) {
                     Iu entityliving;
                     try {
                        if (bnK.ForgeBiomeSpawnListEntry_newInstance.exists()) {
                           entityliving = (Iu)bnK.call(biome$spawnlistentry, bnK.ForgeBiomeSpawnListEntry_newInstance, worldIn);
                        } else {
                           entityliving = (Iu)biome$spawnlistentry.entityClass.getConstructor(bij.class).newInstance(worldIn);
                        }
                     } catch (Exception var21) {
                        Exception exception = var21;
                        exception.printStackTrace();
                        continue;
                     }

                     if (bnK.ForgeEventFactory_canEntitySpawn.exists()) {
                        Object object = bnK.call(bnK.ForgeEventFactory_canEntitySpawn, entityliving, worldIn, (float)j + 0.5F, blockpos.getY(), (float)k + 0.5F, false);
                        if (object == bnQ.EVENT_RESULT_DENY) {
                           continue;
                        }
                     }

                     entityliving.setLocationAndAngles((double)((float)j + 0.5F), (double)blockpos.getY(), (double)((float)k + 0.5F), randomIn.nextFloat() * 360.0F, 0.0F);
                     worldIn.spawnEntity(entityliving);
                     ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
                     flag = true;
                  }

                  j += randomIn.nextInt(5) - randomIn.nextInt(5);

                  for(k += randomIn.nextInt(5) - randomIn.nextInt(5); j < centerX || j >= centerX + diameterX || k < centerZ || k >= centerZ + diameterX; k = i1 + randomIn.nextInt(5) - randomIn.nextInt(5)) {
                     j = l + randomIn.nextInt(5) - randomIn.nextInt(5);
                  }
               }
            }
         }
      }

   }
}
