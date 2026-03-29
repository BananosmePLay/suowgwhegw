package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;

public class qO {
   private static final ResourceLocation PARTICLE_TEXTURES = new ResourceLocation("textures/particle/particles.png");
   protected bij worldObj;
   private final ArrayDeque<pM>[][] fxLayers = new ArrayDeque[4][];
   private final Queue<qi> particleEmitters = Queues.newArrayDeque();
   private final zf renderer;
   private final Random rand = new Random();
   private final Map<Integer, pK> particleTypes = Maps.newHashMap();
   private final Queue<pM> queueEntityFX = Queues.newArrayDeque();

   public qO(bij worldIn, zf rendererIn) {
      this.worldObj = worldIn;
      this.renderer = rendererIn;

      for(int i = 0; i < 4; ++i) {
         this.fxLayers[i] = new ArrayDeque[2];

         for(int j = 0; j < 2; ++j) {
            this.fxLayers[i][j] = Queues.newArrayDeque();
         }
      }

      this.registerVanillaParticles();
   }

   private void registerVanillaParticles() {
      this.registerParticle(EnumParticleTypes.EXPLOSION_NORMAL.getParticleID(), new qn());
      this.registerParticle(EnumParticleTypes.SPIT.getParticleID(), new rn());
      this.registerParticle(EnumParticleTypes.WATER_BUBBLE.getParticleID(), new pT());
      this.registerParticle(EnumParticleTypes.WATER_SPLASH.getParticleID(), new rp());
      this.registerParticle(EnumParticleTypes.WATER_WAKE.getParticleID(), new rA());
      this.registerParticle(EnumParticleTypes.WATER_DROP.getParticleID(), new qV());
      this.registerParticle(EnumParticleTypes.SUSPENDED.getParticleID(), new rr());
      this.registerParticle(EnumParticleTypes.SUSPENDED_DEPTH.getParticleID(), new rt());
      this.registerParticle(EnumParticleTypes.CRIT.getParticleID(), new pY());
      this.registerParticle(EnumParticleTypes.CRIT_MAGIC.getParticleID(), new pZ());
      this.registerParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), new rd());
      this.registerParticle(EnumParticleTypes.SMOKE_LARGE.getParticleID(), new ra());
      this.registerParticle(EnumParticleTypes.SPELL.getParticleID(), new ri());
      this.registerParticle(EnumParticleTypes.SPELL_INSTANT.getParticleID(), new rj());
      this.registerParticle(EnumParticleTypes.SPELL_MOB.getParticleID(), new rk());
      this.registerParticle(EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID(), new rh());
      this.registerParticle(EnumParticleTypes.SPELL_WITCH.getParticleID(), new rl());
      this.registerParticle(EnumParticleTypes.DRIP_WATER.getParticleID(), new qg());
      this.registerParticle(EnumParticleTypes.DRIP_LAVA.getParticleID(), new qf());
      this.registerParticle(EnumParticleTypes.VILLAGER_ANGRY.getParticleID(), new qE());
      this.registerParticle(EnumParticleTypes.VILLAGER_HAPPY.getParticleID(), new ru());
      this.registerParticle(EnumParticleTypes.TOWN_AURA.getParticleID(), new rt());
      this.registerParticle(EnumParticleTypes.NOTE.getParticleID(), new qR());
      this.registerParticle(EnumParticleTypes.PORTAL.getParticleID(), new qT());
      this.registerParticle(EnumParticleTypes.ENCHANTMENT_TABLE.getParticleID(), new qj());
      this.registerParticle(EnumParticleTypes.FLAME.getParticleID(), new qA());
      this.registerParticle(EnumParticleTypes.LAVA.getParticleID(), new qI());
      this.registerParticle(EnumParticleTypes.FOOTSTEP.getParticleID(), new qC());
      this.registerParticle(EnumParticleTypes.CLOUD.getParticleID(), new pV());
      this.registerParticle(EnumParticleTypes.REDSTONE.getParticleID(), new qX());
      this.registerParticle(EnumParticleTypes.FALLING_DUST.getParticleID(), new qt());
      this.registerParticle(EnumParticleTypes.SNOWBALL.getParticleID(), new pR());
      this.registerParticle(EnumParticleTypes.SNOW_SHOVEL.getParticleID(), new rf());
      this.registerParticle(EnumParticleTypes.SLIME.getParticleID(), new pQ());
      this.registerParticle(EnumParticleTypes.HEART.getParticleID(), new qF());
      this.registerParticle(EnumParticleTypes.BARRIER.getParticleID(), new pI());
      this.registerParticle(EnumParticleTypes.ITEM_CRACK.getParticleID(), new pP());
      this.registerParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(), new qb());
      this.registerParticle(EnumParticleTypes.BLOCK_DUST.getParticleID(), new pN());
      this.registerParticle(EnumParticleTypes.EXPLOSION_HUGE.getParticleID(), new qp());
      this.registerParticle(EnumParticleTypes.EXPLOSION_LARGE.getParticleID(), new qr());
      this.registerParticle(EnumParticleTypes.FIREWORKS_SPARK.getParticleID(), new qv());
      this.registerParticle(EnumParticleTypes.MOB_APPEARANCE.getParticleID(), new qP());
      this.registerParticle(EnumParticleTypes.DRAGON_BREATH.getParticleID(), new qd());
      this.registerParticle(EnumParticleTypes.END_ROD.getParticleID(), new ql());
      this.registerParticle(EnumParticleTypes.DAMAGE_INDICATOR.getParticleID(), new pX());
      this.registerParticle(EnumParticleTypes.SWEEP_ATTACK.getParticleID(), new rw());
      this.registerParticle(EnumParticleTypes.TOTEM.getParticleID(), new ry());
   }

   public void registerParticle(int id, pK particleFactory) {
      this.particleTypes.put(id, particleFactory);
   }

   public void emitParticleAtEntity(Ig entityIn, EnumParticleTypes particleTypes) {
      this.particleEmitters.add(new qi(this.worldObj, entityIn, particleTypes));
   }

   public void emitParticleAtEntity(Ig p_191271_1_, EnumParticleTypes p_191271_2_, int p_191271_3_) {
      this.particleEmitters.add(new qi(this.worldObj, p_191271_1_, p_191271_2_, p_191271_3_));
   }

   @Nullable
   public pM spawnEffectParticle(int particleId, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
      pK iparticlefactory = (pK)this.particleTypes.get(particleId);
      if (iparticlefactory != null) {
         pM particle = iparticlefactory.createParticle(particleId, this.worldObj, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
         if (particle != null) {
            this.addEffect(particle);
            return particle;
         }
      }

      return null;
   }

   public void addEffect(pM effect) {
      if (effect != null && (!(effect instanceof qx) || XH.isFireworkParticles())) {
         this.queueEntityFX.add(effect);
      }

   }

   public void updateEffects() {
      for(int i = 0; i < 4; ++i) {
         this.updateEffectLayer(i);
      }

      if (!this.particleEmitters.isEmpty()) {
         List<qi> list = Lists.newArrayList();
         Iterator var2 = this.particleEmitters.iterator();

         while(var2.hasNext()) {
            qi particleemitter = (qi)var2.next();
            particleemitter.onUpdate();
            if (!particleemitter.isAlive()) {
               list.add(particleemitter);
            }
         }

         this.particleEmitters.removeAll(list);
      }

      if (!this.queueEntityFX.isEmpty()) {
         for(pM particle = (pM)this.queueEntityFX.poll(); particle != null; particle = (pM)this.queueEntityFX.poll()) {
            int j = particle.getFXLayer();
            int k = particle.shouldDisableDepth() ? 0 : 1;
            if (this.fxLayers[j][k].size() >= 16384) {
               this.fxLayers[j][k].removeFirst();
            }

            if (!(particle instanceof pJ) || !this.reuseBarrierParticle(particle, this.fxLayers[j][k])) {
               this.fxLayers[j][k].add(particle);
            }
         }
      }

   }

   private void updateEffectLayer(int layer) {
      this.worldObj.profiler.startSection(String.valueOf(layer));

      for(int i = 0; i < 2; ++i) {
         this.worldObj.profiler.startSection(String.valueOf(i));
         this.tickParticleList(this.fxLayers[layer][i]);
         this.worldObj.profiler.endSection();
      }

      this.worldObj.profiler.endSection();
   }

   private void tickParticleList(Queue<pM> p_187240_1_) {
      if (!p_187240_1_.isEmpty()) {
         Iterator<pM> iterator = p_187240_1_.iterator();

         while(iterator.hasNext()) {
            pM particle = (pM)iterator.next();
            this.tickParticle(particle);
            if (!particle.isAlive()) {
               iterator.remove();
            }
         }
      }

   }

   private void tickParticle(final pM particle) {
      try {
         particle.onUpdate();
      } catch (Throwable var6) {
         Throwable throwable = var6;
         Er crashreport = Er.makeCrashReport(throwable, "Ticking Particle");
         Ey crashreportcategory = crashreport.makeCategory("Particle being ticked");
         final int i = particle.getFXLayer();
         crashreportcategory.addDetail("Particle", new Ez<String>() {
            public String call() throws Exception {
               return particle.toString();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         crashreportcategory.addDetail("Particle Type", new Ez<String>() {
            public String call() throws Exception {
               if (i == 0) {
                  return "MISC_TEXTURE";
               } else if (i == 1) {
                  return "TERRAIN_TEXTURE";
               } else {
                  return i == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + i;
               }
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   public void renderParticles(Ig entityIn, float partialTicks) {
      float f = rF.getRotationX();
      float f1 = rF.getRotationZ();
      float f2 = rF.getRotationYZ();
      float f3 = rF.getRotationXY();
      float f4 = rF.getRotationXZ();
      pM.interpPosX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
      pM.interpPosY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
      pM.interpPosZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
      pM.cameraViewDir = entityIn.getLook(partialTicks);
      yh.enableBlend();
      yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
      yh.alphaFunc(516, 0.003921569F);

      for(int i = 0; i < 3; ++i) {
         final int j = i;

         for(int k = 0; k < 2; ++k) {
            if (!this.fxLayers[j][k].isEmpty()) {
               switch (k) {
                  case 0:
                     yh.depthMask(false);
                     break;
                  case 1:
                     yh.depthMask(true);
               }

               switch (j) {
                  case 0:
                  default:
                     this.renderer.bindTexture(PARTICLE_TEXTURES);
                     break;
                  case 1:
                     this.renderer.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
               }

               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               yN tessellator = yN.getInstance();
               tN bufferbuilder = tessellator.getBuffer();
               bufferbuilder.begin(7, zK.PARTICLE_POSITION_TEX_COLOR_LMAP);
               Iterator var13 = this.fxLayers[j][k].iterator();

               while(var13.hasNext()) {
                  final pM particle = (pM)var13.next();

                  try {
                     particle.renderParticle(bufferbuilder, entityIn, partialTicks, f, f4, f1, f2, f3);
                  } catch (Throwable var18) {
                     Throwable throwable = var18;
                     Er crashreport = Er.makeCrashReport(throwable, "Rendering Particle");
                     Ey crashreportcategory = crashreport.makeCategory("Particle being rendered");
                     crashreportcategory.addDetail("Particle", new Ez<String>() {
                        public String call() throws Exception {
                           return particle.toString();
                        }

                        // $FF: synthetic method
                        // $FF: bridge method
                        public Object call() throws Exception {
                           return this.call();
                        }
                     });
                     crashreportcategory.addDetail("Particle Type", new Ez<String>() {
                        public String call() throws Exception {
                           if (j == 0) {
                              return "MISC_TEXTURE";
                           } else if (j == 1) {
                              return "TERRAIN_TEXTURE";
                           } else {
                              return j == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + j;
                           }
                        }

                        // $FF: synthetic method
                        // $FF: bridge method
                        public Object call() throws Exception {
                           return this.call();
                        }
                     });
                     throw new ReportedException(crashreport);
                  }
               }

               tessellator.draw();
            }
         }
      }

      yh.depthMask(true);
      yh.disableBlend();
      yh.alphaFunc(516, 0.1F);
   }

   public void renderLitParticles(Ig entityIn, float partialTick) {
      float f = 0.017453292F;
      float f1 = MathHelper.cos(entityIn.rotationYaw * 0.017453292F);
      float f2 = MathHelper.sin(entityIn.rotationYaw * 0.017453292F);
      float f3 = -f2 * MathHelper.sin(entityIn.rotationPitch * 0.017453292F);
      float f4 = f1 * MathHelper.sin(entityIn.rotationPitch * 0.017453292F);
      float f5 = MathHelper.cos(entityIn.rotationPitch * 0.017453292F);

      for(int i = 0; i < 2; ++i) {
         Queue<pM> queue = this.fxLayers[3][i];
         if (!queue.isEmpty()) {
            yN tessellator = yN.getInstance();
            tN bufferbuilder = tessellator.getBuffer();
            Iterator var13 = queue.iterator();

            while(var13.hasNext()) {
               pM particle = (pM)var13.next();
               particle.renderParticle(bufferbuilder, entityIn, partialTick, f1, f5, f2, f3, f4);
            }
         }
      }

   }

   public void clearEffects(@Nullable bij worldIn) {
      this.worldObj = worldIn;

      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 2; ++j) {
            this.fxLayers[i][j].clear();
         }
      }

      this.particleEmitters.clear();
   }

   public void addBlockDestroyEffects(BlockPos pos, in state) {
      boolean flag;
      if (bnK.ForgeBlock_addDestroyEffects.exists() && bnK.ForgeBlock_isAir.exists()) {
         co block = state.getBlock();
         flag = !bnK.callBoolean(block, bnK.ForgeBlock_isAir, state, this.worldObj, pos) && !bnK.callBoolean(block, bnK.ForgeBlock_addDestroyEffects, this.worldObj, pos, this);
      } else {
         flag = state.getMaterial() != hM.AIR;
      }

      if (flag) {
         state = state.getActualState(this.worldObj, pos);
         int l = true;

         for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
               for(int k = 0; k < 4; ++k) {
                  double d0 = ((double)i + 0.5) / 4.0;
                  double d1 = ((double)j + 0.5) / 4.0;
                  double d2 = ((double)k + 0.5) / 4.0;
                  this.addEffect((new qc(this.worldObj, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, d0 - 0.5, d1 - 0.5, d2 - 0.5, state)).setBlockPos(pos));
               }
            }
         }
      }

   }

   public void addBlockHitEffects(BlockPos pos, EnumFacing side) {
      in iblockstate = this.worldObj.getBlockState(pos);
      if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
         int i = pos.getX();
         int j = pos.getY();
         int k = pos.getZ();
         float f = 0.1F;
         AxisAlignedBB axisalignedbb = iblockstate.getBoundingBox(this.worldObj, pos);
         double d0 = (double)i + this.rand.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - 0.20000000298023224) + 0.10000000149011612 + axisalignedbb.minX;
         double d1 = (double)j + this.rand.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - 0.20000000298023224) + 0.10000000149011612 + axisalignedbb.minY;
         double d2 = (double)k + this.rand.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - 0.20000000298023224) + 0.10000000149011612 + axisalignedbb.minZ;
         if (side == EnumFacing.DOWN) {
            d1 = (double)j + axisalignedbb.minY - 0.10000000149011612;
         }

         if (side == EnumFacing.UP) {
            d1 = (double)j + axisalignedbb.maxY + 0.10000000149011612;
         }

         if (side == EnumFacing.NORTH) {
            d2 = (double)k + axisalignedbb.minZ - 0.10000000149011612;
         }

         if (side == EnumFacing.SOUTH) {
            d2 = (double)k + axisalignedbb.maxZ + 0.10000000149011612;
         }

         if (side == EnumFacing.WEST) {
            d0 = (double)i + axisalignedbb.minX - 0.10000000149011612;
         }

         if (side == EnumFacing.EAST) {
            d0 = (double)i + axisalignedbb.maxX + 0.10000000149011612;
         }

         this.addEffect((new qc(this.worldObj, d0, d1, d2, 0.0, 0.0, 0.0, iblockstate)).setBlockPos(pos).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
      }

   }

   public String getStatistics() {
      int i = 0;

      for(int j = 0; j < 4; ++j) {
         for(int k = 0; k < 2; ++k) {
            i += this.fxLayers[j][k].size();
         }
      }

      return "" + i;
   }

   private boolean reuseBarrierParticle(pM p_reuseBarrierParticle_1_, ArrayDeque<pM> p_reuseBarrierParticle_2_) {
      Iterator var3 = p_reuseBarrierParticle_2_.iterator();

      pM particle;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         particle = (pM)var3.next();
      } while(!(particle instanceof pJ) || p_reuseBarrierParticle_1_.prevPosX != particle.prevPosX || p_reuseBarrierParticle_1_.prevPosY != particle.prevPosY || p_reuseBarrierParticle_1_.prevPosZ != particle.prevPosZ);

      particle.particleAge = 0;
      return true;
   }

   public void addBlockHitEffects(BlockPos p_addBlockHitEffects_1_, RayTraceResult p_addBlockHitEffects_2_) {
      in iblockstate = this.worldObj.getBlockState(p_addBlockHitEffects_1_);
      if (iblockstate != null) {
         boolean flag = bnK.callBoolean(iblockstate.getBlock(), bnK.ForgeBlock_addHitEffects, iblockstate, this.worldObj, p_addBlockHitEffects_2_, this);
         if (iblockstate != null && !flag) {
            this.addBlockHitEffects(p_addBlockHitEffects_1_, p_addBlockHitEffects_2_.sideHit);
         }
      }

   }
}
