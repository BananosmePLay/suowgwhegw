package neo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.util.vector.Vector3f;

public class bnc {
   private static final float VERTEX_COORD_ACCURACY = 1.0E-6F;

   public bnc() {
   }

   public static sc makeModelCube(String spriteName, int tintIndex) {
      zd textureatlassprite = XH.getMinecraft().getTextureMapBlocks().getAtlasSprite(spriteName);
      return makeModelCube(textureatlassprite, tintIndex);
   }

   public static sc makeModelCube(zd sprite, int tintIndex) {
      List list = new ArrayList();
      EnumFacing[] aenumfacing = EnumFacing.VALUES;
      Map<EnumFacing, List<rK>> map = new HashMap();

      for(int i = 0; i < aenumfacing.length; ++i) {
         EnumFacing enumfacing = aenumfacing[i];
         List list1 = new ArrayList();
         list1.add(makeBakedQuad(enumfacing, sprite, tintIndex));
         map.put(enumfacing, list1);
      }

      sn itemoverridelist = new sn(new ArrayList());
      sc ibakedmodel = new tc(list, map, true, true, sprite, sg.DEFAULT, itemoverridelist);
      return ibakedmodel;
   }

   public static sc joinModelsCube(sc modelBase, sc modelAdd) {
      List<rK> list = new ArrayList();
      list.addAll(modelBase.getQuads((in)null, (EnumFacing)null, 0L));
      list.addAll(modelAdd.getQuads((in)null, (EnumFacing)null, 0L));
      EnumFacing[] aenumfacing = EnumFacing.VALUES;
      Map<EnumFacing, List<rK>> map = new HashMap();

      for(int i = 0; i < aenumfacing.length; ++i) {
         EnumFacing enumfacing = aenumfacing[i];
         List list1 = new ArrayList();
         list1.addAll(modelBase.getQuads((in)null, enumfacing, 0L));
         list1.addAll(modelAdd.getQuads((in)null, enumfacing, 0L));
         map.put(enumfacing, list1);
      }

      boolean flag = modelBase.isAmbientOcclusion();
      boolean flag1 = modelBase.isBuiltInRenderer();
      zd textureatlassprite = modelBase.getParticleTexture();
      sg itemcameratransforms = modelBase.getItemCameraTransforms();
      sn itemoverridelist = modelBase.getOverrides();
      sc ibakedmodel = new tc(list, map, flag, flag1, textureatlassprite, itemcameratransforms, itemoverridelist);
      return ibakedmodel;
   }

   public static rK makeBakedQuad(EnumFacing facing, zd sprite, int tintIndex) {
      Vector3f vector3f = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f vector3f1 = new Vector3f(16.0F, 16.0F, 16.0F);
      rN blockfaceuv = new rN(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
      rS blockpartface = new rS(facing, tintIndex, "#" + facing.getName(), blockfaceuv);
      sE modelrotation = sE.X0_Y0;
      rT blockpartrotation = null;
      boolean flag = false;
      boolean flag1 = true;
      sb facebakery = new sb();
      rK bakedquad = facebakery.makeBakedQuad(vector3f, vector3f1, blockpartface, sprite, facing, (sE)modelrotation, (rT)blockpartrotation, flag, flag1);
      return bakedquad;
   }

   public static sc makeModel(String modelName, String spriteOldName, String spriteNewName) {
      zj texturemap = XH.getMinecraft().getTextureMapBlocks();
      zd textureatlassprite = texturemap.getSpriteSafe(spriteOldName);
      zd textureatlassprite1 = texturemap.getSpriteSafe(spriteNewName);
      return makeModel(modelName, textureatlassprite, textureatlassprite1);
   }

   public static sc makeModel(String modelName, zd spriteOld, zd spriteNew) {
      if (spriteOld != null && spriteNew != null) {
         sC modelmanager = XH.getModelManager();
         if (modelmanager == null) {
            return null;
         } else {
            sD modelresourcelocation = new sD(modelName, "normal");
            sc ibakedmodel = modelmanager.getModel(modelresourcelocation);
            if (ibakedmodel != null && ibakedmodel != modelmanager.getMissingModel()) {
               sc ibakedmodel1 = bnf.duplicateModel(ibakedmodel);
               EnumFacing[] aenumfacing = EnumFacing.VALUES;

               for(int i = 0; i < aenumfacing.length; ++i) {
                  EnumFacing enumfacing = aenumfacing[i];
                  List<rK> list = ibakedmodel1.getQuads((in)null, enumfacing, 0L);
                  replaceTexture(list, spriteOld, spriteNew);
               }

               List<rK> list1 = ibakedmodel1.getQuads((in)null, (EnumFacing)null, 0L);
               replaceTexture(list1, spriteOld, spriteNew);
               return ibakedmodel1;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static void replaceTexture(List<rK> quads, zd spriteOld, zd spriteNew) {
      List<rK> list = new ArrayList();

      Object bakedquad;
      for(Iterator var4 = quads.iterator(); var4.hasNext(); list.add(bakedquad)) {
         bakedquad = (rK)var4.next();
         if (((rK)bakedquad).getSprite() == spriteOld) {
            bakedquad = new rL((rK)bakedquad, spriteNew);
         }
      }

      quads.clear();
      quads.addAll(list);
   }

   public static void snapVertexPosition(Vector3f pos) {
      pos.setX(snapVertexCoord(pos.getX()));
      pos.setY(snapVertexCoord(pos.getY()));
      pos.setZ(snapVertexCoord(pos.getZ()));
   }

   private static float snapVertexCoord(float x) {
      if (x > -1.0E-6F && x < 1.0E-6F) {
         return 0.0F;
      } else {
         return x > 0.999999F && x < 1.000001F ? 1.0F : x;
      }
   }

   public static AxisAlignedBB getOffsetBoundingBox(AxisAlignedBB aabb, cn offsetType, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getZ();
      long k = (long)(i * 3129871) ^ (long)j * 116129781L;
      k = k * k * 42317861L + k * 11L;
      double d0 = ((double)((float)(k >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
      double d1 = ((double)((float)(k >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
      double d2 = 0.0;
      if (offsetType == cn.XYZ) {
         d2 = ((double)((float)(k >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
      }

      return aabb.offset(d0, d2, d1);
   }
}
