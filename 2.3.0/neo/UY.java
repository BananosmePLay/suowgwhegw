package neo;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;

public class UY implements Sz<Ts> {
   private UX type;
   private ITextComponent message;
   private int fadeInTime;
   private int displayTime;
   private int fadeOutTime;

   public UY() {
   }

   public UY(UX typeIn, ITextComponent messageIn) {
      this(typeIn, messageIn, -1, -1, -1);
   }

   public UY(int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
      this(UX.TIMES, (ITextComponent)null, fadeInTimeIn, displayTimeIn, fadeOutTimeIn);
   }

   public UY(UX typeIn, @Nullable ITextComponent messageIn, int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
      this.type = typeIn;
      this.message = messageIn;
      this.fadeInTime = fadeInTimeIn;
      this.displayTime = displayTimeIn;
      this.fadeOutTime = fadeOutTimeIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.type = (UX)buf.readEnumValue(UX.class);
      if (this.type == UX.TITLE || this.type == UX.SUBTITLE || this.type == UX.ACTIONBAR) {
         this.message = buf.readTextComponent();
      }

      if (this.type == UX.TIMES) {
         this.fadeInTime = buf.readInt();
         this.displayTime = buf.readInt();
         this.fadeOutTime = buf.readInt();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.type);
      if (this.type == UX.TITLE || this.type == UX.SUBTITLE || this.type == UX.ACTIONBAR) {
         buf.writeTextComponent(this.message);
      }

      if (this.type == UX.TIMES) {
         buf.writeInt(this.fadeInTime);
         buf.writeInt(this.displayTime);
         buf.writeInt(this.fadeOutTime);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleTitle(this);
   }

   public UX getType() {
      return this.type;
   }

   public ITextComponent getMessage() {
      return this.message;
   }

   public int getFadeInTime() {
      return this.fadeInTime;
   }

   public int getDisplayTime() {
      return this.displayTime;
   }

   public int getFadeOutTime() {
      return this.fadeOutTime;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
