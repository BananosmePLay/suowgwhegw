package neo;

public class bob {
   private int position = -1;
   private int size = 0;
   private bqA<bob> node = new bqA(this);

   public bob() {
   }

   public int getPosition() {
      return this.position;
   }

   public int getSize() {
      return this.size;
   }

   public int getPositionNext() {
      return this.position + this.size;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public bqA<bob> getNode() {
      return this.node;
   }

   public bob getPrev() {
      bqA<bob> node = this.node.getPrev();
      return node == null ? null : (bob)node.getItem();
   }

   public bob getNext() {
      bqA<bob> node = this.node.getNext();
      return node == null ? null : (bob)node.getItem();
   }

   public String toString() {
      return "" + this.position + "/" + this.size + "/" + (this.position + this.size);
   }
}
