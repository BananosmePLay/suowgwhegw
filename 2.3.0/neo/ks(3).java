package neo;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class ks extends ky {
   private final jU controlsScreen;
   private final nC mc;
   private final kx[] listEntries;
   private int maxListLabelWidth;

   public ks(jU controls, nC mcIn) {
      super(mcIn, controls.width + 45, controls.height, 63, controls.height - 32, 20);
      this.controlsScreen = controls;
      this.mc = mcIn;
      Bl[] akeybinding = (Bl[])((Bl[])ArrayUtils.clone(nC.gameSettings.keyBindings));
      this.listEntries = new kx[akeybinding.length + Bl.getKeybinds().size()];
      Arrays.sort((Object[])akeybinding);
      int i = 0;
      String s = null;
      Bl[] var6 = akeybinding;
      int var7 = akeybinding.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Bl keybinding = var6[var8];
         String s1 = keybinding.getKeyCategory();
         if (!s1.equals(s)) {
            s = s1;
            this.listEntries[i++] = new kq(this, s1);
         }

         int j = mcIn.fontRenderer.getStringWidth(Ax.format(keybinding.getKeyDescription()));
         if (j > this.maxListLabelWidth) {
            this.maxListLabelWidth = j;
         }

         this.listEntries[i++] = new kr(this, keybinding);
      }

   }

   protected int getSize() {
      return this.listEntries.length;
   }

   public kx getListEntry(int index) {
      return this.listEntries[index];
   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 15;
   }

   public int getListWidth() {
      return super.getListWidth() + 32;
   }

   // $FF: synthetic method
   static nC access$100(ks x0) {
      return x0.mc;
   }

   // $FF: synthetic method
   static jU access$200(ks x0) {
      return x0.controlsScreen;
   }

   // $FF: synthetic method
   static int access$300(ks x0) {
      return x0.maxListLabelWidth;
   }
}
