package neo;

import java.util.ArrayDeque;
import java.util.Deque;

public class bpi {
   private Deque<bpg> stack = new ArrayDeque();

   public bpi() {
   }

   public void push(bpg p) {
      this.stack.addLast(p);
      if (this.stack.size() > 100) {
         throw new RuntimeException("Program stack overflow: " + this.stack.size());
      }
   }

   public bpg pop() {
      if (this.stack.isEmpty()) {
         throw new RuntimeException("Program stack empty");
      } else {
         bpg program = (bpg)this.stack.pollLast();
         return program;
      }
   }
}
