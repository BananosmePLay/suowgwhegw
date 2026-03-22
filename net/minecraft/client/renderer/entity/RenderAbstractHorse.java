package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.util.ResourceLocation;

public class RenderAbstractHorse extends RenderLiving<AbstractHorse> {
   private static final Map<Class<?>, ResourceLocation> MAP = Maps.newHashMap();
   private final float scale;

   public RenderAbstractHorse(RenderManager manager) {
      this(manager, 1.0F);
   }

   public RenderAbstractHorse(RenderManager renderManagerIn, float scaleIn) {
      super(renderManagerIn, new ModelHorse(), 0.75F);
      this.scale = scaleIn;
   }

   protected void preRenderCallback(AbstractHorse entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(this.scale, this.scale, this.scale);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   protected ResourceLocation getEntityTexture(AbstractHorse entity) {
      return (ResourceLocation)MAP.get(entity.getClass());
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((AbstractHorse)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((AbstractHorse)var1);
   }

   static {
      MAP.put(EntityDonkey.class, new ResourceLocation("textures/entity/horse/donkey.png"));
      MAP.put(EntityMule.class, new ResourceLocation("textures/entity/horse/mule.png"));
      MAP.put(EntityZombieHorse.class, new ResourceLocation("textures/entity/horse/horse_zombie.png"));
      MAP.put(EntitySkeletonHorse.class, new ResourceLocation("textures/entity/horse/horse_skeleton.png"));
   }
}
