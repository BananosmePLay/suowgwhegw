package neo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

class boX extends ly {
   private ArrayList shaderslist;
   private int selectedIndex;
   private long lastClickedCached = 0L;
   final boU shadersGui;

   public boX(boU par1GuiShaders, int width, int height, int top, int bottom, int slotHeight) {
      super(par1GuiShaders.getMc(), width, height, top, bottom, slotHeight);
      this.shadersGui = par1GuiShaders;
      this.updateList();
      this.amountScrolled = 0.0F;
      int i = this.selectedIndex * slotHeight;
      int j = (bottom - top) / 2;
      if (i > j) {
         this.scrollBy(i - j);
      }

   }

   public int getListWidth() {
      return this.width - 20;
   }

   public void updateList() {
      this.shaderslist = bpq.listOfShaders();
      this.selectedIndex = 0;
      int i = 0;

      for(int j = this.shaderslist.size(); i < j; ++i) {
         if (((String)this.shaderslist.get(i)).equals(bpq.currentShaderName)) {
            this.selectedIndex = i;
            break;
         }
      }

   }

   protected int getSize() {
      return this.shaderslist.size();
   }

   protected void elementClicked(int index, boolean doubleClicked, int mouseX, int mouseY) {
      if (index != this.selectedIndex || this.lastClicked != this.lastClickedCached) {
         String s = (String)this.shaderslist.get(index);
         bpa ishaderpack = bpq.getShaderPack(s);
         if (this.checkCompatible(ishaderpack, index)) {
            this.selectIndex(index);
         }
      }

   }

   private void selectIndex(int index) {
      this.selectedIndex = index;
      this.lastClickedCached = this.lastClicked;
      bpq.setShaderPack((String)this.shaderslist.get(index));
      bpq.uninit();
      this.shadersGui.updateButtons();
   }

   private boolean checkCompatible(bpa sp, final int index) {
      if (sp == null) {
         return true;
      } else {
         InputStream inputstream = sp.getResourceAsStream("/shaders/shaders.properties");
         Properties properties = bqN.readProperties(inputstream, "Shaders");
         if (properties == null) {
            return true;
         } else {
            String s = "version.1.12.2";
            String s1 = properties.getProperty(s);
            if (s1 == null) {
               return true;
            } else {
               s1 = s1.trim();
               String s2 = "G5";
               int i = XH.compareRelease(s2, s1);
               if (i >= 0) {
                  return true;
               } else {
                  String s3 = ("HD_U_" + s1).replace('_', ' ');
                  String s4 = Ax.format("of.message.shaders.nv1", s3);
                  String s5 = Ax.format("of.message.shaders.nv2");
                  lL guiyesnocallback = new lL() {
                     public void confirmClicked(boolean result, int id) {
                        if (result) {
                           boX.this.selectIndex(index);
                        }

                        boX.this.mc.displayGuiScreen(boX.this.shadersGui);
                     }
                  };
                  lK guiyesno = new lK(guiyesnocallback, s4, s5, 0);
                  this.mc.displayGuiScreen(guiyesno);
                  return false;
               }
            }
         }
      }
   }

   protected boolean isSelected(int index) {
      return index == this.selectedIndex;
   }

   protected int getScrollBarX() {
      return this.width - 6;
   }

   protected int getContentHeight() {
      return this.getSize() * 18;
   }

   protected void drawBackground() {
   }

   protected void drawSlot(int index, int posX, int posY, int contentY, int mouseX, int mouseY, float partialTicks) {
      String s = (String)this.shaderslist.get(index);
      if (s.equals("OFF")) {
         s = bmW.get("of.options.shaders.packNone");
      } else if (s.equals("(internal)")) {
         s = bmW.get("of.options.shaders.packDefault");
      }

      this.shadersGui.drawCenteredString(s, this.width / 2, posY + 1, 14737632);
   }

   public int getSelectedIndex() {
      return this.selectedIndex;
   }
}
