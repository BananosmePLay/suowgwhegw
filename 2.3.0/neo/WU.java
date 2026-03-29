package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

public class WU {
   private static final Predicate<MG> NOT_SPECTATOR = new Predicate<MG>() {
      public boolean apply(@Nullable MG p_apply_1_) {
         return p_apply_1_ != null && !p_apply_1_.isSpectator();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((MG)var1);
      }
   };
   private static final Predicate<MG> CAN_GENERATE_CHUNKS = new Predicate<MG>() {
      public boolean apply(@Nullable MG p_apply_1_) {
         return p_apply_1_ != null && (!p_apply_1_.isSpectator() || p_apply_1_.getServerWorld().getGameRules().getBoolean("spectatorsGenerateChunks"));
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((MG)var1);
      }
   };
   private final bis world;
   private final List<MG> players = Lists.newArrayList();
   private final Long2ObjectMap<WV> entryMap = new Long2ObjectOpenHashMap(4096);
   private final Set<WV> dirtyEntries = Sets.newHashSet();
   private final List<WV> pendingSendToPlayers = Lists.newLinkedList();
   private final List<WV> entriesWithoutChunks = Lists.newLinkedList();
   private final List<WV> entries = Lists.newArrayList();
   private int playerViewRadius;
   private long previousTotalWorldTime;
   private boolean sortMissingChunks = true;
   private boolean sortSendToPlayers = true;
   private final Map<MG, Set<ChunkPos>> mapPlayerPendingEntries = new HashMap();

   public WU(bis serverWorld) {
      this.world = serverWorld;
      this.setPlayerViewRadius(serverWorld.getMinecraftServer().getPlayerList().getViewDistance());
   }

   public bis getWorldServer() {
      return this.world;
   }

   public Iterator<bam> getChunkIterator() {
      final Iterator<WV> iterator = this.entries.iterator();
      return new AbstractIterator<bam>() {
         protected bam computeNext() {
            while(true) {
               if (iterator.hasNext()) {
                  WV playerchunkmapentry = (WV)iterator.next();
                  bam chunk = playerchunkmapentry.getChunk();
                  if (chunk == null) {
                     continue;
                  }

                  if (!chunk.isLightPopulated() && chunk.isTerrainPopulated()) {
                     return chunk;
                  }

                  if (!chunk.wasTicked()) {
                     return chunk;
                  }

                  if (!playerchunkmapentry.hasPlayerMatchingInRange(128.0, WU.NOT_SPECTATOR)) {
                     continue;
                  }

                  return chunk;
               }

               return (bam)this.endOfData();
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         protected Object computeNext() {
            return this.computeNext();
         }
      };
   }

   public void tick() {
      Set<Map.Entry<MG, Set<ChunkPos>>> set = this.mapPlayerPendingEntries.entrySet();
      Iterator iterator = set.iterator();

      while(true) {
         while(true) {
            Map.Entry entry;
            Set set1;
            WV playerchunkmapentry4;
            do {
               if (!iterator.hasNext()) {
                  long j = this.world.getTotalWorldTime();
                  int i1;
                  WV playerchunkmapentry2;
                  if (j - this.previousTotalWorldTime > 8000L) {
                     this.previousTotalWorldTime = j;

                     for(i1 = 0; i1 < this.entries.size(); ++i1) {
                        playerchunkmapentry2 = (WV)this.entries.get(i1);
                        playerchunkmapentry2.update();
                        playerchunkmapentry2.updateChunkInhabitedTime();
                     }
                  }

                  if (!this.dirtyEntries.isEmpty()) {
                     Iterator var13 = this.dirtyEntries.iterator();

                     while(var13.hasNext()) {
                        playerchunkmapentry2 = (WV)var13.next();
                        playerchunkmapentry2.update();
                     }

                     this.dirtyEntries.clear();
                  }

                  if (this.sortMissingChunks && j % 4L == 0L) {
                     this.sortMissingChunks = false;
                     Collections.sort(this.entriesWithoutChunks, new Comparator<WV>() {
                        public int compare(WV p_compare_1_, WV p_compare_2_) {
                           return ComparisonChain.start().compare(p_compare_1_.getClosestPlayerDistance(), p_compare_2_.getClosestPlayerDistance()).result();
                        }

                        // $FF: synthetic method
                        // $FF: bridge method
                        public int compare(Object var1, Object var2) {
                           return this.compare((WV)var1, (WV)var2);
                        }
                     });
                  }

                  if (this.sortSendToPlayers && j % 4L == 2L) {
                     this.sortSendToPlayers = false;
                     Collections.sort(this.pendingSendToPlayers, new Comparator<WV>() {
                        public int compare(WV p_compare_1_, WV p_compare_2_) {
                           return ComparisonChain.start().compare(p_compare_1_.getClosestPlayerDistance(), p_compare_2_.getClosestPlayerDistance()).result();
                        }

                        // $FF: synthetic method
                        // $FF: bridge method
                        public int compare(Object var1, Object var2) {
                           return this.compare((WV)var1, (WV)var2);
                        }
                     });
                  }

                  if (!this.entriesWithoutChunks.isEmpty()) {
                     long l = System.nanoTime() + 50000000L;
                     int j1 = 49;
                     Iterator<WV> iterator2 = this.entriesWithoutChunks.iterator();

                     while(iterator2.hasNext()) {
                        playerchunkmapentry4 = (WV)iterator2.next();
                        if (playerchunkmapentry4.getChunk() == null) {
                           boolean flag = playerchunkmapentry4.hasPlayerMatching(CAN_GENERATE_CHUNKS);
                           if (playerchunkmapentry4.providePlayerChunk(flag)) {
                              iterator2.remove();
                              if (playerchunkmapentry4.sendToPlayers()) {
                                 this.pendingSendToPlayers.remove(playerchunkmapentry4);
                              }

                              --j1;
                              if (j1 < 0 || System.nanoTime() > l) {
                                 break;
                              }
                           }
                        }
                     }
                  }

                  if (!this.pendingSendToPlayers.isEmpty()) {
                     i1 = 81;
                     Iterator<WV> iterator1 = this.pendingSendToPlayers.iterator();

                     while(iterator1.hasNext()) {
                        WV playerchunkmapentry3 = (WV)iterator1.next();
                        if (playerchunkmapentry3.sendToPlayers()) {
                           iterator1.remove();
                           --i1;
                           if (i1 < 0) {
                              break;
                           }
                        }
                     }
                  }

                  if (this.players.isEmpty()) {
                     bil worldprovider = this.world.provider;
                     if (!worldprovider.canRespawnHere()) {
                        this.world.getChunkProvider().queueUnloadAll();
                     }
                  }

                  return;
               }

               entry = (Map.Entry)iterator.next();
               set1 = (Set)entry.getValue();
            } while(set1.isEmpty());

            MG entityplayermp = (MG)entry.getKey();
            if (entityplayermp.getServerWorld() != this.world) {
               iterator.remove();
            } else {
               int i = this.playerViewRadius / 3 + 1;
               if (!XH.isLazyChunkLoading()) {
                  i = this.playerViewRadius * 2 + 1;
               }

               ChunkPos chunkpos;
               for(Iterator var7 = this.getNearest(set1, entityplayermp, i).iterator(); var7.hasNext(); set1.remove(chunkpos)) {
                  chunkpos = (ChunkPos)var7.next();
                  playerchunkmapentry4 = this.getOrCreateEntry(chunkpos.x, chunkpos.z);
                  if (!playerchunkmapentry4.containsPlayer(entityplayermp)) {
                     playerchunkmapentry4.addPlayer(entityplayermp);
                  }
               }
            }
         }
      }
   }

   public boolean contains(int chunkX, int chunkZ) {
      long i = getIndex(chunkX, chunkZ);
      return this.entryMap.get(i) != null;
   }

   @Nullable
   public WV getEntry(int x, int z) {
      return (WV)this.entryMap.get(getIndex(x, z));
   }

   private WV getOrCreateEntry(int chunkX, int chunkZ) {
      long i = getIndex(chunkX, chunkZ);
      WV playerchunkmapentry = (WV)this.entryMap.get(i);
      if (playerchunkmapentry == null) {
         playerchunkmapentry = new WV(this, chunkX, chunkZ);
         this.entryMap.put(i, playerchunkmapentry);
         this.entries.add(playerchunkmapentry);
         if (playerchunkmapentry.getChunk() == null) {
            this.entriesWithoutChunks.add(playerchunkmapentry);
         }

         if (!playerchunkmapentry.sendToPlayers()) {
            this.pendingSendToPlayers.add(playerchunkmapentry);
         }
      }

      return playerchunkmapentry;
   }

   public void markBlockForUpdate(BlockPos pos) {
      int i = pos.getX() >> 4;
      int j = pos.getZ() >> 4;
      WV playerchunkmapentry = this.getEntry(i, j);
      if (playerchunkmapentry != null) {
         playerchunkmapentry.blockChanged(pos.getX() & 15, pos.getY(), pos.getZ() & 15);
      }

   }

   public void addPlayer(MG player) {
      int i = (int)player.posX >> 4;
      int j = (int)player.posZ >> 4;
      player.managedPosX = player.posX;
      player.managedPosZ = player.posZ;
      int k = Math.min(this.playerViewRadius, 8);
      int l = i - k;
      int i1 = i + k;
      int j1 = j - k;
      int k1 = j + k;
      Set<ChunkPos> set = this.getPendingEntriesSafe(player);

      for(int l1 = i - this.playerViewRadius; l1 <= i + this.playerViewRadius; ++l1) {
         for(int i2 = j - this.playerViewRadius; i2 <= j + this.playerViewRadius; ++i2) {
            if (l1 >= l && l1 <= i1 && i2 >= j1 && i2 <= k1) {
               this.getOrCreateEntry(l1, i2).addPlayer(player);
            } else {
               set.add(new ChunkPos(l1, i2));
            }
         }
      }

      this.players.add(player);
      this.markSortPending();
   }

   public void removePlayer(MG player) {
      this.mapPlayerPendingEntries.remove(player);
      int i = (int)player.managedPosX >> 4;
      int j = (int)player.managedPosZ >> 4;

      for(int k = i - this.playerViewRadius; k <= i + this.playerViewRadius; ++k) {
         for(int l = j - this.playerViewRadius; l <= j + this.playerViewRadius; ++l) {
            WV playerchunkmapentry = this.getEntry(k, l);
            if (playerchunkmapentry != null) {
               playerchunkmapentry.removePlayer(player);
            }
         }
      }

      this.players.remove(player);
      this.markSortPending();
   }

   private boolean overlaps(int x1, int z1, int x2, int z2, int radius) {
      int i = x1 - x2;
      int j = z1 - z2;
      if (i >= -radius && i <= radius) {
         return j >= -radius && j <= radius;
      } else {
         return false;
      }
   }

   public void updateMovingPlayer(MG player) {
      int i = (int)player.posX >> 4;
      int j = (int)player.posZ >> 4;
      double d0 = player.managedPosX - player.posX;
      double d1 = player.managedPosZ - player.posZ;
      double d2 = d0 * d0 + d1 * d1;
      if (d2 >= 64.0) {
         int k = (int)player.managedPosX >> 4;
         int l = (int)player.managedPosZ >> 4;
         int i1 = this.playerViewRadius;
         int j1 = i - k;
         int k1 = j - l;
         if (j1 != 0 || k1 != 0) {
            Set<ChunkPos> set = this.getPendingEntriesSafe(player);

            for(int l1 = i - i1; l1 <= i + i1; ++l1) {
               for(int i2 = j - i1; i2 <= j + i1; ++i2) {
                  if (!this.overlaps(l1, i2, k, l, i1)) {
                     if (XH.isLazyChunkLoading()) {
                        set.add(new ChunkPos(l1, i2));
                     } else {
                        this.getOrCreateEntry(l1, i2).addPlayer(player);
                     }
                  }

                  if (!this.overlaps(l1 - j1, i2 - k1, i, j, i1)) {
                     set.remove(new ChunkPos(l1 - j1, i2 - k1));
                     WV playerchunkmapentry = this.getEntry(l1 - j1, i2 - k1);
                     if (playerchunkmapentry != null) {
                        playerchunkmapentry.removePlayer(player);
                     }
                  }
               }
            }

            player.managedPosX = player.posX;
            player.managedPosZ = player.posZ;
            this.markSortPending();
         }
      }

   }

   public boolean isPlayerWatchingChunk(MG player, int chunkX, int chunkZ) {
      WV playerchunkmapentry = this.getEntry(chunkX, chunkZ);
      return playerchunkmapentry != null && playerchunkmapentry.containsPlayer(player) && playerchunkmapentry.isSentToPlayers();
   }

   public void setPlayerViewRadius(int radius) {
      radius = MathHelper.clamp(radius, 3, 64);
      if (radius != this.playerViewRadius) {
         int i = radius - this.playerViewRadius;
         Iterator var3 = Lists.newArrayList(this.players).iterator();

         while(true) {
            while(var3.hasNext()) {
               MG entityplayermp = (MG)var3.next();
               int j = (int)entityplayermp.posX >> 4;
               int k = (int)entityplayermp.posZ >> 4;
               Set<ChunkPos> set = this.getPendingEntriesSafe(entityplayermp);
               int l;
               int i1;
               WV playerchunkmapentry;
               if (i > 0) {
                  for(l = j - radius; l <= j + radius; ++l) {
                     for(i1 = k - radius; i1 <= k + radius; ++i1) {
                        if (XH.isLazyChunkLoading()) {
                           set.add(new ChunkPos(l, i1));
                        } else {
                           playerchunkmapentry = this.getOrCreateEntry(l, i1);
                           if (!playerchunkmapentry.containsPlayer(entityplayermp)) {
                              playerchunkmapentry.addPlayer(entityplayermp);
                           }
                        }
                     }
                  }
               } else {
                  for(l = j - this.playerViewRadius; l <= j + this.playerViewRadius; ++l) {
                     for(i1 = k - this.playerViewRadius; i1 <= k + this.playerViewRadius; ++i1) {
                        if (!this.overlaps(l, i1, j, k, radius)) {
                           set.remove(new ChunkPos(l, i1));
                           playerchunkmapentry = this.getEntry(l, i1);
                           if (playerchunkmapentry != null) {
                              playerchunkmapentry.removePlayer(entityplayermp);
                           }
                        }
                     }
                  }
               }
            }

            this.playerViewRadius = radius;
            this.markSortPending();
            break;
         }
      }

   }

   private void markSortPending() {
      this.sortMissingChunks = true;
      this.sortSendToPlayers = true;
   }

   public static int getFurthestViewableBlock(int distance) {
      return distance * 16 - 16;
   }

   private static long getIndex(int chunkX, int chunkZ) {
      return (long)chunkX + 2147483647L | (long)chunkZ + 2147483647L << 32;
   }

   public void entryChanged(WV entry) {
      this.dirtyEntries.add(entry);
   }

   public void removeEntry(WV entry) {
      ChunkPos chunkpos = entry.getPos();
      long i = getIndex(chunkpos.x, chunkpos.z);
      entry.updateChunkInhabitedTime();
      this.entryMap.remove(i);
      this.entries.remove(entry);
      this.dirtyEntries.remove(entry);
      this.pendingSendToPlayers.remove(entry);
      this.entriesWithoutChunks.remove(entry);
      bam chunk = entry.getChunk();
      if (chunk != null) {
         this.getWorldServer().getChunkProvider().queueUnload(chunk);
      }

   }

   private PriorityQueue<ChunkPos> getNearest(Set<ChunkPos> p_getNearest_1_, MG p_getNearest_2_, int p_getNearest_3_) {
      float f;
      for(f = p_getNearest_2_.rotationYaw + 90.0F; f <= -180.0F; f += 360.0F) {
      }

      while(f > 180.0F) {
         f -= 360.0F;
      }

      double d0 = (double)f * 0.017453292519943295;
      double d1 = (double)p_getNearest_2_.rotationPitch;
      double d2 = d1 * 0.017453292519943295;
      biO chunkposcomparator = new biO(p_getNearest_2_.chunkCoordX, p_getNearest_2_.chunkCoordZ, d0, d2);
      Comparator<ChunkPos> comparator = Collections.reverseOrder(chunkposcomparator);
      PriorityQueue<ChunkPos> priorityqueue = new PriorityQueue(comparator);
      Iterator var14 = p_getNearest_1_.iterator();

      while(var14.hasNext()) {
         ChunkPos chunkpos = (ChunkPos)var14.next();
         if (priorityqueue.size() < p_getNearest_3_) {
            priorityqueue.add(chunkpos);
         } else {
            ChunkPos chunkpos1 = (ChunkPos)priorityqueue.peek();
            if (chunkposcomparator.compare(chunkpos, chunkpos1) < 0) {
               priorityqueue.remove();
               priorityqueue.add(chunkpos);
            }
         }
      }

      return priorityqueue;
   }

   private Set<ChunkPos> getPendingEntriesSafe(MG p_getPendingEntriesSafe_1_) {
      Set<ChunkPos> set = (Set)this.mapPlayerPendingEntries.get(p_getPendingEntriesSafe_1_);
      if (set != null) {
         return set;
      } else {
         int i = Math.min(this.playerViewRadius, 8);
         int j = this.playerViewRadius * 2 + 1;
         int k = i * 2 + 1;
         int l = j * j - k * k;
         l = Math.max(l, 16);
         Set<ChunkPos> hashset = new HashSet(l);
         this.mapPlayerPendingEntries.put(p_getPendingEntriesSafe_1_, hashset);
         return hashset;
      }
   }
}
