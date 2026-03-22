package neo;

public class 0bW {
   public String message;
   public boolean leftKeyDown;
   public int integer;
   public boolean forwardKeyDown;
   public boolean keyBindSneak;
   public boolean keyBindJump;
   public boolean backKeyDown;
   public boolean rightKeyDown;
   public float pitch;
   public boolean keyBindSprint;
   public float yaw;
   public 0bX actionType;

   public _bW/* $FF was: 0bW*/(0bX actionType, int integer) {
      this.actionType = actionType;
      this.integer = integer;
   }

   public _bW/* $FF was: 0bW*/(0bX actionType, String message) {
      this.actionType = actionType;
      this.message = message;
   }

   public _bW/* $FF was: 0bW*/(0bX actionType, boolean forwardKeyDown, boolean backKeyDown, boolean leftKeyDown, boolean rightKeyDown, float yaw, float pitch, boolean keyBindSneak, boolean keyBindSprint, boolean keyBindJump) {
      this.actionType = actionType;
      this.forwardKeyDown = forwardKeyDown;
      this.backKeyDown = backKeyDown;
      this.leftKeyDown = leftKeyDown;
      this.rightKeyDown = rightKeyDown;
      this.yaw = yaw;
      this.pitch = pitch;
      this.keyBindSneak = keyBindSneak;
      this.keyBindSprint = keyBindSprint;
      this.keyBindJump = keyBindJump;
   }
}
