package neo;

public class bmn extends jP {
   private static final String CMD_RELOAD_SHADERS = "/reloadShaders";
   private static final String CMD_RELOAD_CHUNKS = "/reloadChunks";

   public bmn(jP guiChat) {
      super(lG.getGuiChatText(guiChat));
   }

   public void sendChatMessage(String msg) {
      if (this.checkCustomCommand(msg)) {
         this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
      } else {
         super.sendChatMessage(msg);
      }

   }

   private boolean checkCustomCommand(String msg) {
      if (msg == null) {
         return false;
      } else {
         msg = msg.trim();
         if (msg.equals("/reloadShaders")) {
            if (XH.isShaders()) {
               bpq.uninit();
               bpq.loadShaderPack();
            }

            return true;
         } else if (msg.equals("/reloadChunks")) {
            this.mc.renderGlobal.loadRenderers();
            return true;
         } else {
            return false;
         }
      }
   }

   public void setCompletions(String... newCompletions) {
      String s = lG.getGuiChatText(this);
      if ("/reloadShaders".startsWith(s)) {
         newCompletions = (String[])((String[])XH.addObjectToArray(newCompletions, "/reloadShaders"));
      }

      if ("/reloadChunks".startsWith(s)) {
         newCompletions = (String[])((String[])XH.addObjectToArray(newCompletions, "/reloadChunks"));
      }

      super.setCompletions(newCompletions);
   }
}
