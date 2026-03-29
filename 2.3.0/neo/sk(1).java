package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

public class sk {
   public static final List<String> LAYERS = Lists.newArrayList(new String[]{"layer0", "layer1", "layer2", "layer3", "layer4"});

   public sk() {
   }

   @Nullable
   public sy makeItemModel(zj textureMapIn, sy blockModel) {
      Map<String, String> map = Maps.newHashMap();
      List<rQ> list = Lists.newArrayList();

      for(int i = 0; i < LAYERS.size(); ++i) {
         String s = (String)LAYERS.get(i);
         if (!blockModel.isTexturePresent(s)) {
            break;
         }

         String s1 = blockModel.resolveTextureName(s);
         map.put(s, s1);
         zd textureatlassprite = textureMapIn.getAtlasSprite((new ResourceLocation(s1)).toString());
         list.addAll(this.getBlockParts(i, s, textureatlassprite));
      }

      if (list.isEmpty()) {
         return null;
      } else {
         map.put("particle", blockModel.isTexturePresent("particle") ? blockModel.resolveTextureName("particle") : (String)map.get("layer0"));
         return new sy((ResourceLocation)null, list, map, false, false, blockModel.getAllTransforms(), blockModel.getOverrides());
      }
   }

   private List<rQ> getBlockParts(int tintIndex, String p_178394_2_, zd p_178394_3_) {
      Map<EnumFacing, rS> map = Maps.newHashMap();
      map.put(EnumFacing.SOUTH, new rS((EnumFacing)null, tintIndex, p_178394_2_, new rN(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0)));
      map.put(EnumFacing.NORTH, new rS((EnumFacing)null, tintIndex, p_178394_2_, new rN(new float[]{16.0F, 0.0F, 0.0F, 16.0F}, 0)));
      List<rQ> list = Lists.newArrayList();
      list.add(new rQ(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), map, (rT)null, true));
      list.addAll(this.getBlockParts(p_178394_3_, p_178394_2_, tintIndex));
      return list;
   }

   private List<rQ> getBlockParts(zd p_178397_1_, String p_178397_2_, int p_178397_3_) {
      float f = (float)p_178397_1_.getIconWidth();
      float f1 = (float)p_178397_1_.getIconHeight();
      List<rQ> list = Lists.newArrayList();
      Iterator var7 = this.getSpans(p_178397_1_).iterator();

      while(var7.hasNext()) {
         si itemmodelgenerator$span = (si)var7.next();
         float f2 = 0.0F;
         float f3 = 0.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 0.0F;
         float f7 = 0.0F;
         float f8 = 0.0F;
         float f9 = 0.0F;
         float f10 = 0.0F;
         float f11 = 0.0F;
         float f12 = (float)itemmodelgenerator$span.getMin();
         float f13 = (float)itemmodelgenerator$span.getMax();
         float f14 = (float)itemmodelgenerator$span.getAnchor();
         sj itemmodelgenerator$spanfacing = itemmodelgenerator$span.getFacing();
         switch (itemmodelgenerator$spanfacing) {
            case UP:
               f6 = f12;
               f2 = f12;
               f4 = f7 = f13 + 1.0F;
               f8 = f14;
               f3 = f14;
               f9 = f14;
               f5 = f14;
               f10 = 16.0F / f;
               f11 = 16.0F / (f1 - 1.0F);
               break;
            case DOWN:
               f9 = f14;
               f8 = f14;
               f6 = f12;
               f2 = f12;
               f4 = f7 = f13 + 1.0F;
               f3 = f14 + 1.0F;
               f5 = f14 + 1.0F;
               f10 = 16.0F / f;
               f11 = 16.0F / (f1 - 1.0F);
               break;
            case LEFT:
               f6 = f14;
               f2 = f14;
               f7 = f14;
               f4 = f14;
               f9 = f12;
               f3 = f12;
               f5 = f8 = f13 + 1.0F;
               f10 = 16.0F / (f - 1.0F);
               f11 = 16.0F / f1;
               break;
            case RIGHT:
               f7 = f14;
               f6 = f14;
               f2 = f14 + 1.0F;
               f4 = f14 + 1.0F;
               f9 = f12;
               f3 = f12;
               f5 = f8 = f13 + 1.0F;
               f10 = 16.0F / (f - 1.0F);
               f11 = 16.0F / f1;
         }

         float f15 = 16.0F / f;
         float f16 = 16.0F / f1;
         f2 *= f15;
         f4 *= f15;
         f3 *= f16;
         f5 *= f16;
         f3 = 16.0F - f3;
         f5 = 16.0F - f5;
         f6 *= f10;
         f7 *= f10;
         f8 *= f11;
         f9 *= f11;
         Map<EnumFacing, rS> map = Maps.newHashMap();
         map.put(itemmodelgenerator$spanfacing.getFacing(), new rS((EnumFacing)null, p_178397_3_, p_178397_2_, new rN(new float[]{f6, f8, f7, f9}, 0)));
         switch (itemmodelgenerator$spanfacing) {
            case UP:
               list.add(new rQ(new Vector3f(f2, f3, 7.5F), new Vector3f(f4, f3, 8.5F), map, (rT)null, true));
               break;
            case DOWN:
               list.add(new rQ(new Vector3f(f2, f5, 7.5F), new Vector3f(f4, f5, 8.5F), map, (rT)null, true));
               break;
            case LEFT:
               list.add(new rQ(new Vector3f(f2, f3, 7.5F), new Vector3f(f2, f5, 8.5F), map, (rT)null, true));
               break;
            case RIGHT:
               list.add(new rQ(new Vector3f(f4, f3, 7.5F), new Vector3f(f4, f5, 8.5F), map, (rT)null, true));
         }
      }

      return list;
   }

   private List<si> getSpans(zd p_178393_1_) {
      int i = p_178393_1_.getIconWidth();
      int j = p_178393_1_.getIconHeight();
      List<si> list = Lists.newArrayList();

      for(int k = 0; k < p_178393_1_.getFrameCount(); ++k) {
         int[] aint = p_178393_1_.getFrameTextureData(k)[0];

         for(int l = 0; l < j; ++l) {
            for(int i1 = 0; i1 < i; ++i1) {
               boolean flag = !this.isTransparent(aint, i1, l, i, j);
               this.checkTransition(sj.UP, list, aint, i1, l, i, j, flag);
               this.checkTransition(sj.DOWN, list, aint, i1, l, i, j, flag);
               this.checkTransition(sj.LEFT, list, aint, i1, l, i, j, flag);
               this.checkTransition(sj.RIGHT, list, aint, i1, l, i, j, flag);
            }
         }
      }

      return list;
   }

   private void checkTransition(sj p_178396_1_, List<si> p_178396_2_, int[] p_178396_3_, int p_178396_4_, int p_178396_5_, int p_178396_6_, int p_178396_7_, boolean p_178396_8_) {
      boolean flag = this.isTransparent(p_178396_3_, p_178396_4_ + p_178396_1_.getXOffset(), p_178396_5_ + p_178396_1_.getYOffset(), p_178396_6_, p_178396_7_) && p_178396_8_;
      if (flag) {
         this.createOrExpandSpan(p_178396_2_, p_178396_1_, p_178396_4_, p_178396_5_);
      }

   }

   private void createOrExpandSpan(List<si> p_178395_1_, sj p_178395_2_, int p_178395_3_, int p_178395_4_) {
      si itemmodelgenerator$span = null;
      Iterator var6 = p_178395_1_.iterator();

      while(var6.hasNext()) {
         si itemmodelgenerator$span1 = (si)var6.next();
         if (itemmodelgenerator$span1.getFacing() == p_178395_2_) {
            int i = sj.access$000(p_178395_2_) ? p_178395_4_ : p_178395_3_;
            if (itemmodelgenerator$span1.getAnchor() == i) {
               itemmodelgenerator$span = itemmodelgenerator$span1;
               break;
            }
         }
      }

      int j = sj.access$000(p_178395_2_) ? p_178395_4_ : p_178395_3_;
      int k = sj.access$000(p_178395_2_) ? p_178395_3_ : p_178395_4_;
      if (itemmodelgenerator$span == null) {
         p_178395_1_.add(new si(p_178395_2_, k, j));
      } else {
         itemmodelgenerator$span.expand(k);
      }

   }

   private boolean isTransparent(int[] p_178391_1_, int p_178391_2_, int p_178391_3_, int p_178391_4_, int p_178391_5_) {
      if (p_178391_2_ >= 0 && p_178391_3_ >= 0 && p_178391_2_ < p_178391_4_ && p_178391_3_ < p_178391_5_) {
         return (p_178391_1_[p_178391_3_ * p_178391_4_ + p_178391_2_] >> 24 & 255) == 0;
      } else {
         return true;
      }
   }
}
