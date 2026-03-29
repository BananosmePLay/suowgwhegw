package neo;

import net.minecraft.util.math.MathHelper;
import org.lwjgl.util.vector.Quaternion;

public class sg {
   public static final sg DEFAULT = new sg();
   public static float offsetTranslateX;
   public static float offsetTranslateY;
   public static float offsetTranslateZ;
   public static float offsetRotationX;
   public static float offsetRotationY;
   public static float offsetRotationZ;
   public static float offsetScaleX;
   public static float offsetScaleY;
   public static float offsetScaleZ;
   public final sp thirdperson_left;
   public final sp thirdperson_right;
   public final sp firstperson_left;
   public final sp firstperson_right;
   public final sp head;
   public final sp gui;
   public final sp ground;
   public final sp fixed;

   private sg() {
      this(sp.DEFAULT, sp.DEFAULT, sp.DEFAULT, sp.DEFAULT, sp.DEFAULT, sp.DEFAULT, sp.DEFAULT, sp.DEFAULT);
   }

   public sg(sg transforms) {
      this.thirdperson_left = transforms.thirdperson_left;
      this.thirdperson_right = transforms.thirdperson_right;
      this.firstperson_left = transforms.firstperson_left;
      this.firstperson_right = transforms.firstperson_right;
      this.head = transforms.head;
      this.gui = transforms.gui;
      this.ground = transforms.ground;
      this.fixed = transforms.fixed;
   }

   public sg(sp thirdperson_leftIn, sp thirdperson_rightIn, sp firstperson_leftIn, sp firstperson_rightIn, sp headIn, sp guiIn, sp groundIn, sp fixedIn) {
      this.thirdperson_left = thirdperson_leftIn;
      this.thirdperson_right = thirdperson_rightIn;
      this.firstperson_left = firstperson_leftIn;
      this.firstperson_right = firstperson_rightIn;
      this.head = headIn;
      this.gui = guiIn;
      this.ground = groundIn;
      this.fixed = fixedIn;
   }

   public void applyTransform(sf type) {
      applyTransformSide(this.getTransform(type), false);
   }

   public static void applyTransformSide(sp vec, boolean leftHand) {
      if (vec != sp.DEFAULT) {
         int i = leftHand ? -1 : 1;
         yh.translate((float)i * (offsetTranslateX + vec.translation.x), offsetTranslateY + vec.translation.y, offsetTranslateZ + vec.translation.z);
         float f = offsetRotationX + vec.rotation.x;
         float f1 = offsetRotationY + vec.rotation.y;
         float f2 = offsetRotationZ + vec.rotation.z;
         if (leftHand) {
            f1 = -f1;
            f2 = -f2;
         }

         yh.rotate(makeQuaternion(f, f1, f2));
         yh.scale(offsetScaleX + vec.scale.x, offsetScaleY + vec.scale.y, offsetScaleZ + vec.scale.z);
      }

   }

   private static Quaternion makeQuaternion(float p_188035_0_, float p_188035_1_, float p_188035_2_) {
      float f = p_188035_0_ * 0.017453292F;
      float f1 = p_188035_1_ * 0.017453292F;
      float f2 = p_188035_2_ * 0.017453292F;
      float f3 = MathHelper.sin(0.5F * f);
      float f4 = MathHelper.cos(0.5F * f);
      float f5 = MathHelper.sin(0.5F * f1);
      float f6 = MathHelper.cos(0.5F * f1);
      float f7 = MathHelper.sin(0.5F * f2);
      float f8 = MathHelper.cos(0.5F * f2);
      return new Quaternion(f3 * f6 * f8 + f4 * f5 * f7, f4 * f5 * f8 - f3 * f6 * f7, f3 * f5 * f8 + f4 * f6 * f7, f4 * f6 * f8 - f3 * f5 * f7);
   }

   public sp getTransform(sf type) {
      switch (type) {
         case THIRD_PERSON_LEFT_HAND:
            return this.thirdperson_left;
         case THIRD_PERSON_RIGHT_HAND:
            return this.thirdperson_right;
         case FIRST_PERSON_LEFT_HAND:
            return this.firstperson_left;
         case FIRST_PERSON_RIGHT_HAND:
            return this.firstperson_right;
         case HEAD:
            return this.head;
         case GUI:
            return this.gui;
         case GROUND:
            return this.ground;
         case FIXED:
            return this.fixed;
         default:
            return sp.DEFAULT;
      }
   }

   public boolean hasCustomTransform(sf type) {
      return this.getTransform(type) != sp.DEFAULT;
   }
}
