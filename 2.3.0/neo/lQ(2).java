package neo;

class lQ extends lN {
   private final VW effect;
   private final int tier;
   // $FF: synthetic field
   final lR this$0;

   public lQ(lR this$0, int buttonId, int x, int y, VW effectIn, int tierIn) {
      super(buttonId, x, y, lU.INVENTORY_BACKGROUND, effectIn.getStatusIconIndex() % 8 * 18, 198 + effectIn.getStatusIconIndex() / 8 * 18);
      this.this$0 = this$0;
      this.effect = effectIn;
      this.tier = tierIn;
   }

   public void drawButtonForegroundLayer(int mouseX, int mouseY) {
      String s = Ax.format(this.effect.getName());
      if (this.tier >= 3 && this.effect != NL.REGENERATION) {
         s = s + " II";
      }

      this.this$0.drawHoveringText(s, mouseX, mouseY);
   }

   // $FF: synthetic method
   static VW access$000(lQ x0) {
      return x0.effect;
   }

   // $FF: synthetic method
   static int access$100(lQ x0) {
      return x0.tier;
   }
}
