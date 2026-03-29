package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.math.MathHelper;

public class zb {
   private final int mipmapLevelStitcher;
   private final Set<yZ> setStitchHolders = Sets.newHashSetWithExpectedSize(256);
   private final List<za> stitchSlots = Lists.newArrayListWithCapacity(256);
   private int currentWidth;
   private int currentHeight;
   private final int maxWidth;
   private final int maxHeight;
   private final int maxTileDimension;

   public zb(int maxWidthIn, int maxHeightIn, int maxTileDimensionIn, int mipmapLevelStitcherIn) {
      this.mipmapLevelStitcher = mipmapLevelStitcherIn;
      this.maxWidth = maxWidthIn;
      this.maxHeight = maxHeightIn;
      this.maxTileDimension = maxTileDimensionIn;
   }

   public int getCurrentWidth() {
      return this.currentWidth;
   }

   public int getCurrentHeight() {
      return this.currentHeight;
   }

   public void addSprite(zd textureAtlas) {
      yZ stitcher$holder = new yZ(textureAtlas, this.mipmapLevelStitcher);
      if (this.maxTileDimension > 0) {
         stitcher$holder.setNewDimension(this.maxTileDimension);
      }

      this.setStitchHolders.add(stitcher$holder);
   }

   public void doStitch() {
      yZ[] astitcher$holder = (yZ[])((yZ[])this.setStitchHolders.toArray(new yZ[this.setStitchHolders.size()]));
      Arrays.sort(astitcher$holder);
      yZ[] var2 = astitcher$holder;
      int var3 = astitcher$holder.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         yZ stitcher$holder = var2[var4];
         if (!this.allocateSlot(stitcher$holder)) {
            String s = String.format("Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?", stitcher$holder.getAtlasSprite().getIconName(), stitcher$holder.getAtlasSprite().getIconWidth(), stitcher$holder.getAtlasSprite().getIconHeight(), this.currentWidth, this.currentHeight, this.maxWidth, this.maxHeight);
            throw new yM(stitcher$holder, s);
         }
      }

      this.currentWidth = MathHelper.smallestEncompassingPowerOfTwo(this.currentWidth);
      this.currentHeight = MathHelper.smallestEncompassingPowerOfTwo(this.currentHeight);
   }

   public List<zd> getStichSlots() {
      List<za> list = Lists.newArrayList();
      Iterator var2 = this.stitchSlots.iterator();

      while(var2.hasNext()) {
         za stitcher$slot = (za)var2.next();
         stitcher$slot.getAllStitchSlots(list);
      }

      List<zd> list1 = Lists.newArrayList();
      Iterator var8 = list.iterator();

      while(var8.hasNext()) {
         za stitcher$slot1 = (za)var8.next();
         yZ stitcher$holder = stitcher$slot1.getStitchHolder();
         zd textureatlassprite = stitcher$holder.getAtlasSprite();
         textureatlassprite.initSprite(this.currentWidth, this.currentHeight, stitcher$slot1.getOriginX(), stitcher$slot1.getOriginY(), stitcher$holder.isRotated());
         list1.add(textureatlassprite);
      }

      return list1;
   }

   private static int getMipmapDimension(int p_147969_0_, int p_147969_1_) {
      return (p_147969_0_ >> p_147969_1_) + ((p_147969_0_ & (1 << p_147969_1_) - 1) == 0 ? 0 : 1) << p_147969_1_;
   }

   private boolean allocateSlot(yZ p_94310_1_) {
      zd textureatlassprite = p_94310_1_.getAtlasSprite();
      boolean flag = textureatlassprite.getIconWidth() != textureatlassprite.getIconHeight();

      for(int i = 0; i < this.stitchSlots.size(); ++i) {
         if (((za)this.stitchSlots.get(i)).addSlot(p_94310_1_)) {
            return true;
         }

         if (flag) {
            p_94310_1_.rotate();
            if (((za)this.stitchSlots.get(i)).addSlot(p_94310_1_)) {
               return true;
            }

            p_94310_1_.rotate();
         }
      }

      return this.expandAndAllocateSlot(p_94310_1_);
   }

   private boolean expandAndAllocateSlot(yZ p_94311_1_) {
      int i = Math.min(p_94311_1_.getWidth(), p_94311_1_.getHeight());
      int j = Math.max(p_94311_1_.getWidth(), p_94311_1_.getHeight());
      int k = MathHelper.smallestEncompassingPowerOfTwo(this.currentWidth);
      int l = MathHelper.smallestEncompassingPowerOfTwo(this.currentHeight);
      int i1 = MathHelper.smallestEncompassingPowerOfTwo(this.currentWidth + i);
      int j1 = MathHelper.smallestEncompassingPowerOfTwo(this.currentHeight + i);
      boolean flag = i1 <= this.maxWidth;
      boolean flag1 = j1 <= this.maxHeight;
      if (!flag && !flag1) {
         return false;
      } else {
         int k1 = bqD.roundDownToPowerOfTwo(this.currentHeight);
         boolean flag2 = flag && i1 <= 2 * k1;
         if (this.currentWidth == 0 && this.currentHeight == 0) {
            flag2 = true;
         }

         za stitcher$slot;
         if (flag2) {
            if (p_94311_1_.getWidth() > p_94311_1_.getHeight()) {
               p_94311_1_.rotate();
            }

            if (this.currentHeight == 0) {
               this.currentHeight = p_94311_1_.getHeight();
            }

            stitcher$slot = new za(this.currentWidth, 0, p_94311_1_.getWidth(), this.currentHeight);
            this.currentWidth += p_94311_1_.getWidth();
         } else {
            stitcher$slot = new za(0, this.currentHeight, this.currentWidth, p_94311_1_.getHeight());
            this.currentHeight += p_94311_1_.getHeight();
         }

         stitcher$slot.addSlot(p_94311_1_);
         this.stitchSlots.add(stitcher$slot);
         return true;
      }
   }

   // $FF: synthetic method
   static int access$000(int x0, int x1) {
      return getMipmapDimension(x0, x1);
   }
}
