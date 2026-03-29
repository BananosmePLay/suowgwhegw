package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class l {
   private final b advancement;
   private final l parent;
   private final l sibling;
   private final int index;
   private final List<l> children = Lists.newArrayList();
   private l ancestor;
   private l thread;
   private int x;
   private float y;
   private float mod;
   private float change;
   private float shift;

   public l(b advancementIn, @Nullable l parentIn, @Nullable l siblingIn, int indexIn, int xIn) {
      if (advancementIn.getDisplay() == null) {
         throw new IllegalArgumentException("Can't position an invisible advancement!");
      } else {
         this.advancement = advancementIn;
         this.parent = parentIn;
         this.sibling = siblingIn;
         this.index = indexIn;
         this.ancestor = this;
         this.x = xIn;
         this.y = -1.0F;
         l advancementtreenode = null;

         b advancement;
         for(Iterator var7 = advancementIn.getChildren().iterator(); var7.hasNext(); advancementtreenode = this.buildSubTree(advancement, advancementtreenode)) {
            advancement = (b)var7.next();
         }

      }
   }

   @Nullable
   private l buildSubTree(b advancementIn, @Nullable l previous) {
      b advancement;
      if (advancementIn.getDisplay() != null) {
         previous = new l(advancementIn, this, previous, this.children.size() + 1, this.x + 1);
         this.children.add(previous);
      } else {
         for(Iterator var3 = advancementIn.getChildren().iterator(); var3.hasNext(); previous = this.buildSubTree(advancement, previous)) {
            advancement = (b)var3.next();
         }
      }

      return previous;
   }

   private void firstWalk() {
      if (this.children.isEmpty()) {
         if (this.sibling != null) {
            this.y = this.sibling.y + 1.0F;
         } else {
            this.y = 0.0F;
         }
      } else {
         l advancementtreenode = null;

         l advancementtreenode1;
         for(Iterator var2 = this.children.iterator(); var2.hasNext(); advancementtreenode = advancementtreenode1.apportion(advancementtreenode == null ? advancementtreenode1 : advancementtreenode)) {
            advancementtreenode1 = (l)var2.next();
            advancementtreenode1.firstWalk();
         }

         this.executeShifts();
         float f = (((l)this.children.get(0)).y + ((l)this.children.get(this.children.size() - 1)).y) / 2.0F;
         if (this.sibling != null) {
            this.y = this.sibling.y + 1.0F;
            this.mod = this.y - f;
         } else {
            this.y = f;
         }
      }

   }

   private float secondWalk(float p_192319_1_, int p_192319_2_, float p_192319_3_) {
      this.y += p_192319_1_;
      this.x = p_192319_2_;
      if (this.y < p_192319_3_) {
         p_192319_3_ = this.y;
      }

      l advancementtreenode;
      for(Iterator var4 = this.children.iterator(); var4.hasNext(); p_192319_3_ = advancementtreenode.secondWalk(p_192319_1_ + this.mod, p_192319_2_ + 1, p_192319_3_)) {
         advancementtreenode = (l)var4.next();
      }

      return p_192319_3_;
   }

   private void thirdWalk(float yIn) {
      this.y += yIn;
      Iterator var2 = this.children.iterator();

      while(var2.hasNext()) {
         l advancementtreenode = (l)var2.next();
         advancementtreenode.thirdWalk(yIn);
      }

   }

   private void executeShifts() {
      float f = 0.0F;
      float f1 = 0.0F;

      for(int i = this.children.size() - 1; i >= 0; --i) {
         l advancementtreenode = (l)this.children.get(i);
         advancementtreenode.y += f;
         advancementtreenode.mod += f;
         f1 += advancementtreenode.change;
         f += advancementtreenode.shift + f1;
      }

   }

   @Nullable
   private l getFirstChild() {
      if (this.thread != null) {
         return this.thread;
      } else {
         return !this.children.isEmpty() ? (l)this.children.get(0) : null;
      }
   }

   @Nullable
   private l getLastChild() {
      if (this.thread != null) {
         return this.thread;
      } else {
         return !this.children.isEmpty() ? (l)this.children.get(this.children.size() - 1) : null;
      }
   }

   private l apportion(l nodeIn) {
      if (this.sibling == null) {
         return nodeIn;
      } else {
         l advancementtreenode = this;
         l advancementtreenode1 = this;
         l advancementtreenode2 = this.sibling;
         l advancementtreenode3 = (l)this.parent.children.get(0);
         float f = this.mod;
         float f1 = this.mod;
         float f2 = advancementtreenode2.mod;

         float f3;
         for(f3 = advancementtreenode3.mod; advancementtreenode2.getLastChild() != null && advancementtreenode.getFirstChild() != null; f1 += advancementtreenode1.mod) {
            advancementtreenode2 = advancementtreenode2.getLastChild();
            advancementtreenode = advancementtreenode.getFirstChild();
            advancementtreenode3 = advancementtreenode3.getFirstChild();
            advancementtreenode1 = advancementtreenode1.getLastChild();
            advancementtreenode1.ancestor = this;
            float f4 = advancementtreenode2.y + f2 - (advancementtreenode.y + f) + 1.0F;
            if (f4 > 0.0F) {
               advancementtreenode2.getAncestor(this, nodeIn).moveSubtree(this, f4);
               f += f4;
               f1 += f4;
            }

            f2 += advancementtreenode2.mod;
            f += advancementtreenode.mod;
            f3 += advancementtreenode3.mod;
         }

         if (advancementtreenode2.getLastChild() != null && advancementtreenode1.getLastChild() == null) {
            advancementtreenode1.thread = advancementtreenode2.getLastChild();
            advancementtreenode1.mod += f2 - f1;
         } else {
            if (advancementtreenode.getFirstChild() != null && advancementtreenode3.getFirstChild() == null) {
               advancementtreenode3.thread = advancementtreenode.getFirstChild();
               advancementtreenode3.mod += f - f3;
            }

            nodeIn = this;
         }

         return nodeIn;
      }
   }

   private void moveSubtree(l nodeIn, float p_192316_2_) {
      float f = (float)(nodeIn.index - this.index);
      if (f != 0.0F) {
         nodeIn.change -= p_192316_2_ / f;
         this.change += p_192316_2_ / f;
      }

      nodeIn.shift += p_192316_2_;
      nodeIn.y += p_192316_2_;
      nodeIn.mod += p_192316_2_;
   }

   private l getAncestor(l p_192326_1_, l p_192326_2_) {
      return this.ancestor != null && p_192326_1_.parent.children.contains(this.ancestor) ? this.ancestor : p_192326_2_;
   }

   private void updatePosition() {
      if (this.advancement.getDisplay() != null) {
         this.advancement.getDisplay().setPosition((float)this.x, this.y);
      }

      if (!this.children.isEmpty()) {
         Iterator var1 = this.children.iterator();

         while(var1.hasNext()) {
            l advancementtreenode = (l)var1.next();
            advancementtreenode.updatePosition();
         }
      }

   }

   public static void layout(b root) {
      if (root.getDisplay() == null) {
         throw new IllegalArgumentException("Can't position children of an invisible root!");
      } else {
         l advancementtreenode = new l(root, (l)null, (l)null, 1, 0);
         advancementtreenode.firstWalk();
         float f = advancementtreenode.secondWalk(0.0F, 0, advancementtreenode.y);
         if (f < 0.0F) {
            advancementtreenode.thirdWalk(-f);
         }

         advancementtreenode.updatePosition();
      }
   }
}
