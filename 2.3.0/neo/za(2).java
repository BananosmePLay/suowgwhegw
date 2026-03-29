package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class za {
   private final int originX;
   private final int originY;
   private final int width;
   private final int height;
   private List<za> subSlots;
   private yZ holder;

   public za(int originXIn, int originYIn, int widthIn, int heightIn) {
      this.originX = originXIn;
      this.originY = originYIn;
      this.width = widthIn;
      this.height = heightIn;
   }

   public yZ getStitchHolder() {
      return this.holder;
   }

   public int getOriginX() {
      return this.originX;
   }

   public int getOriginY() {
      return this.originY;
   }

   public boolean addSlot(yZ holderIn) {
      if (this.holder != null) {
         return false;
      } else {
         int i = holderIn.getWidth();
         int j = holderIn.getHeight();
         if (i <= this.width && j <= this.height) {
            if (i == this.width && j == this.height) {
               this.holder = holderIn;
               return true;
            } else {
               if (this.subSlots == null) {
                  this.subSlots = Lists.newArrayListWithCapacity(1);
                  this.subSlots.add(new za(this.originX, this.originY, i, j));
                  int k = this.width - i;
                  int l = this.height - j;
                  if (l > 0 && k > 0) {
                     int i1 = Math.max(this.height, k);
                     int j1 = Math.max(this.width, l);
                     if (i1 >= j1) {
                        this.subSlots.add(new za(this.originX, this.originY + j, i, l));
                        this.subSlots.add(new za(this.originX + i, this.originY, k, this.height));
                     } else {
                        this.subSlots.add(new za(this.originX + i, this.originY, k, j));
                        this.subSlots.add(new za(this.originX, this.originY + j, this.width, l));
                     }
                  } else if (k == 0) {
                     this.subSlots.add(new za(this.originX, this.originY + j, i, l));
                  } else if (l == 0) {
                     this.subSlots.add(new za(this.originX + i, this.originY, k, j));
                  }
               }

               Iterator var8 = this.subSlots.iterator();

               za stitcher$slot;
               do {
                  if (!var8.hasNext()) {
                     return false;
                  }

                  stitcher$slot = (za)var8.next();
               } while(!stitcher$slot.addSlot(holderIn));

               return true;
            }
         } else {
            return false;
         }
      }
   }

   public void getAllStitchSlots(List<za> p_94184_1_) {
      if (this.holder != null) {
         p_94184_1_.add(this);
      } else if (this.subSlots != null) {
         Iterator var2 = this.subSlots.iterator();

         while(var2.hasNext()) {
            za stitcher$slot = (za)var2.next();
            stitcher$slot.getAllStitchSlots(p_94184_1_);
         }
      }

   }

   public String toString() {
      return "Slot{originX=" + this.originX + ", originY=" + this.originY + ", width=" + this.width + ", height=" + this.height + ", texture=" + this.holder + ", subSlots=" + this.subSlots + '}';
   }
}
