package neo;

public abstract class 0b implements 0eB {
   public String name = ((0a)this.getClass().getAnnotation(0a.class)).name();
   public String description = ((0a)this.getClass().getAnnotation(0a.class)).description();

   public abstract void execute(String[] var1) throws Exception;

   public abstract void error();

   public void sendMessage(String message) {
      0dK.formatMsg(message);
   }

   public _b/* $FF was: 0b*/() {
   }
}
