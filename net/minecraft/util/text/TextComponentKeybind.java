package net.minecraft.util.text;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;

public class TextComponentKeybind extends TextComponentBase {
   public static Function<String, Supplier<String>> displaySupplierFunction = (p_193635_0_) -> {
      return () -> {
         return p_193635_0_;
      };
   };
   private final String keybind;
   private Supplier<String> displaySupplier;

   public TextComponentKeybind(String keybind) {
      this.keybind = keybind;
   }

   public String getUnformattedComponentText() {
      if (this.displaySupplier == null) {
         this.displaySupplier = (Supplier)displaySupplierFunction.apply(this.keybind);
      }

      return (String)this.displaySupplier.get();
   }

   public TextComponentKeybind createCopy() {
      TextComponentKeybind textcomponentkeybind = new TextComponentKeybind(this.keybind);
      textcomponentkeybind.setStyle(this.getStyle().createShallowCopy());
      Iterator var2 = this.getSiblings().iterator();

      while(var2.hasNext()) {
         ITextComponent itextcomponent = (ITextComponent)var2.next();
         textcomponentkeybind.appendSibling(itextcomponent.createCopy());
      }

      return textcomponentkeybind;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof TextComponentKeybind)) {
         return false;
      } else {
         TextComponentKeybind textcomponentkeybind = (TextComponentKeybind)p_equals_1_;
         return this.keybind.equals(textcomponentkeybind.keybind) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "KeybindComponent{keybind='" + this.keybind + '\'' + ", siblings=" + this.siblings + ", style=" + this.getStyle() + '}';
   }

   public String getKeybind() {
      return this.keybind;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ITextComponent createCopy() {
      return this.createCopy();
   }
}
