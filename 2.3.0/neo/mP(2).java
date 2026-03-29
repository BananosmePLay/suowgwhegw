package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class mP implements mS, mQ {
   private final List<mQ> items = Lists.newArrayList();

   public mP() {
      nC minecraft = nC.getMinecraft();
      Iterator var2 = minecraft.world.getScoreboard().getTeams().iterator();

      while(var2.hasNext()) {
         WA scoreplayerteam = (WA)var2.next();
         this.items.add(new mO(this, scoreplayerteam));
      }

   }

   public List<mQ> getItems() {
      return this.items;
   }

   public ITextComponent getPrompt() {
      return new TextComponentTranslation("spectatorMenu.team_teleport.prompt", new Object[0]);
   }

   public void selectItem(mY menu) {
      menu.selectCategory(this);
   }

   public ITextComponent getSpectatorName() {
      return new TextComponentTranslation("spectatorMenu.team_teleport", new Object[0]);
   }

   public void renderIcon(float brightness, int alpha) {
      nC.getMinecraft().getTextureManager().bindTexture(lB.SPECTATOR_WIDGETS);
      jI.drawModalRectWithCustomSizedTexture(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public boolean isEnabled() {
      Iterator var1 = this.items.iterator();

      mQ ispectatormenuobject;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         ispectatormenuobject = (mQ)var1.next();
      } while(!ispectatormenuobject.isEnabled());

      return true;
   }
}
