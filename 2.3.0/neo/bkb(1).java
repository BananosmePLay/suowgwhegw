package neo;

public enum bkb implements blV {
   IS_ALIVE("is_alive"),
   IS_BURNING("is_burning"),
   IS_CHILD("is_child"),
   IS_GLOWING("is_glowing"),
   IS_HURT("is_hurt"),
   IS_IN_LAVA("is_in_lava"),
   IS_IN_WATER("is_in_water"),
   IS_INVISIBLE("is_invisible"),
   IS_ON_GROUND("is_on_ground"),
   IS_RIDDEN("is_ridden"),
   IS_RIDING("is_riding"),
   IS_SNEAKING("is_sneaking"),
   IS_SPRINTING("is_sprinting"),
   IS_WET("is_wet");

   private String name;
   private wC renderManager;
   private static final bkb[] VALUES = values();

   private bkb(String name) {
      this.name = name;
      this.renderManager = nC.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }

   public boolean eval() {
      vI render = this.renderManager.renderRender;
      if (render == null) {
         return false;
      } else {
         if (render instanceof wy) {
            wy renderlivingbase = (wy)render;
            Iw entitylivingbase = renderlivingbase.renderEntity;
            if (entitylivingbase == null) {
               return false;
            }

            switch (this) {
               case IS_ALIVE:
                  return entitylivingbase.isEntityAlive();
               case IS_BURNING:
                  return entitylivingbase.isBurning();
               case IS_CHILD:
                  return entitylivingbase.isChild();
               case IS_GLOWING:
                  return entitylivingbase.isGlowing();
               case IS_HURT:
                  return entitylivingbase.hurtTime > 0;
               case IS_IN_LAVA:
                  return entitylivingbase.isInLava();
               case IS_IN_WATER:
                  return entitylivingbase.isInWater();
               case IS_INVISIBLE:
                  return entitylivingbase.isInvisible();
               case IS_ON_GROUND:
                  return entitylivingbase.onGround;
               case IS_RIDDEN:
                  return entitylivingbase.isBeingRidden();
               case IS_RIDING:
                  return entitylivingbase.isRiding();
               case IS_SNEAKING:
                  return entitylivingbase.isSneaking();
               case IS_SPRINTING:
                  return entitylivingbase.isSprinting();
               case IS_WET:
                  return entitylivingbase.isWet();
            }
         }

         return false;
      }
   }

   public static bkb parse(String str) {
      if (str == null) {
         return null;
      } else {
         for(int i = 0; i < VALUES.length; ++i) {
            bkb renderentityparameterbool = VALUES[i];
            if (renderentityparameterbool.getName().equals(str)) {
               return renderentityparameterbool;
            }
         }

         return null;
      }
   }
}
