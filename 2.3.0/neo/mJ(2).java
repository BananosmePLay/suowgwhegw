package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class mJ implements mS {
   private final List<mQ> items = Lists.newArrayList();

   public mJ() {
      this.items.add(new mN());
      this.items.add(new mP());
   }

   public List<mQ> getItems() {
      return this.items;
   }

   public ITextComponent getPrompt() {
      return new TextComponentTranslation("spectatorMenu.root.prompt", new Object[0]);
   }
}
