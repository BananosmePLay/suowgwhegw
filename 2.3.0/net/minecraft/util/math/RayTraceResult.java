package net.minecraft.util.math;

import neo.Ig;
import net.minecraft.util.EnumFacing;

public class RayTraceResult {
   private BlockPos blockPos;
   public Type typeOfHit;
   public EnumFacing sideHit;
   public Vec3d hitVec;
   public Ig entityHit;

   public RayTraceResult(Vec3d hitVecIn, EnumFacing sideHitIn, BlockPos blockPosIn) {
      this(RayTraceResult.Type.BLOCK, hitVecIn, sideHitIn, blockPosIn);
   }

   public RayTraceResult(Vec3d hitVecIn, EnumFacing sideHitIn) {
      this(RayTraceResult.Type.BLOCK, hitVecIn, sideHitIn, BlockPos.ORIGIN);
   }

   public RayTraceResult(Ig entityIn) {
      this(entityIn, new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ));
   }

   public RayTraceResult(Type typeIn, Vec3d hitVecIn, EnumFacing sideHitIn, BlockPos blockPosIn) {
      this.typeOfHit = typeIn;
      this.blockPos = blockPosIn;
      this.sideHit = sideHitIn;
      this.hitVec = new Vec3d(hitVecIn.x, hitVecIn.y, hitVecIn.z);
   }

   public RayTraceResult(Ig entityHitIn, Vec3d hitVecIn) {
      this.typeOfHit = RayTraceResult.Type.ENTITY;
      this.entityHit = entityHitIn;
      this.hitVec = hitVecIn;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public String toString() {
      return "HitResult{type=" + this.typeOfHit + ", blockpos=" + this.blockPos + ", f=" + this.sideHit + ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
   }

   public static enum Type {
      MISS,
      BLOCK,
      ENTITY;

      private Type() {
      }
   }
}
