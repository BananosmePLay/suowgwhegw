package neo;

import java.util.Iterator;

public class bqB<T> {
   private bqA<T> first;
   private bqA<T> last;
   private int size;

   public bqB() {
   }

   public void addFirst(bqA<T> node) {
      this.checkNoParent(node);
      if (this.isEmpty()) {
         this.first = node;
         this.last = node;
      } else {
         bqA<T> nodeNext = this.first;
         bqA.access$000(node, nodeNext);
         bqA.access$100(nodeNext, node);
         this.first = node;
      }

      bqA.access$200(node, this);
      ++this.size;
   }

   public void addLast(bqA<T> node) {
      this.checkNoParent(node);
      if (this.isEmpty()) {
         this.first = node;
         this.last = node;
      } else {
         bqA<T> nodePrev = this.last;
         bqA.access$100(node, nodePrev);
         bqA.access$000(nodePrev, node);
         this.last = node;
      }

      bqA.access$200(node, this);
      ++this.size;
   }

   public void addAfter(bqA<T> nodePrev, bqA<T> node) {
      if (nodePrev == null) {
         this.addFirst(node);
      } else if (nodePrev == this.last) {
         this.addLast(node);
      } else {
         this.checkParent(nodePrev);
         this.checkNoParent(node);
         bqA<T> nodeNext = nodePrev.getNext();
         bqA.access$000(nodePrev, node);
         bqA.access$100(node, nodePrev);
         bqA.access$100(nodeNext, node);
         bqA.access$000(node, nodeNext);
         bqA.access$200(node, this);
         ++this.size;
      }
   }

   public bqA<T> remove(bqA<T> node) {
      this.checkParent(node);
      bqA<T> prev = node.getPrev();
      bqA<T> next = node.getNext();
      if (prev != null) {
         bqA.access$000(prev, next);
      } else {
         this.first = next;
      }

      if (next != null) {
         bqA.access$100(next, prev);
      } else {
         this.last = prev;
      }

      bqA.access$100(node, (bqA)null);
      bqA.access$000(node, (bqA)null);
      bqA.access$200(node, (bqB)null);
      --this.size;
      return node;
   }

   public void moveAfter(bqA<T> nodePrev, bqA<T> node) {
      this.remove(node);
      this.addAfter(nodePrev, node);
   }

   public boolean find(bqA<T> nodeFind, bqA<T> nodeFrom, bqA<T> nodeTo) {
      this.checkParent(nodeFrom);
      if (nodeTo != null) {
         this.checkParent(nodeTo);
      }

      bqA node;
      for(node = nodeFrom; node != null && node != nodeTo; node = node.getNext()) {
         if (node == nodeFind) {
            return true;
         }
      }

      if (node != nodeTo) {
         throw new IllegalArgumentException("Sublist is not linked, from: " + nodeFrom + ", to: " + nodeTo);
      } else {
         return false;
      }
   }

   private void checkParent(bqA<T> node) {
      if (bqA.access$300(node) != this) {
         throw new IllegalArgumentException("Node has different parent, node: " + node + ", parent: " + bqA.access$300(node) + ", this: " + this);
      }
   }

   private void checkNoParent(bqA<T> node) {
      if (bqA.access$300(node) != null) {
         throw new IllegalArgumentException("Node has different parent, node: " + node + ", parent: " + bqA.access$300(node) + ", this: " + this);
      }
   }

   public boolean contains(bqA<T> node) {
      return bqA.access$300(node) == this;
   }

   public Iterator<bqA<T>> iterator() {
      Iterator<bqA<T>> iterator = new Iterator<bqA<T>>() {
         bqA<T> node = bqB.this.getFirst();

         public boolean hasNext() {
            return this.node != null;
         }

         public bqA<T> next() {
            bqA<T> node = this.node;
            if (this.node != null) {
               this.node = bqA.access$400(this.node);
            }

            return node;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object next() {
            return this.next();
         }
      };
      return iterator;
   }

   public bqA<T> getFirst() {
      return this.first;
   }

   public bqA<T> getLast() {
      return this.last;
   }

   public int getSize() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size <= 0;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();

      bqA node;
      for(Iterator<bqA<T>> it = this.iterator(); it.hasNext(); sb.append(node.getItem())) {
         node = (bqA)it.next();
         if (sb.length() > 0) {
            sb.append(", ");
         }
      }

      return "" + this.size + " [" + sb.toString() + "]";
   }
}
