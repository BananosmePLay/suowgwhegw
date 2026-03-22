package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nullable;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;

public class SPacketTitle implements Packet<INetHandlerPlayClient> {
   private Type type;
   private ITextComponent message;
   private int fadeInTime;
   private int displayTime;
   private int fadeOutTime;

   public SPacketTitle() {
   }

   public SPacketTitle(Type typeIn, ITextComponent messageIn) {
      this(typeIn, messageIn, -1, -1, -1);
   }

   public SPacketTitle(int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
      this(SPacketTitle.Type.TIMES, (ITextComponent)null, fadeInTimeIn, displayTimeIn, fadeOutTimeIn);
   }

   public SPacketTitle(Type typeIn, @Nullable ITextComponent messageIn, int fadeInTimeIn, int displayTimeIn, int fadeOutTimeIn) {
      this.type = typeIn;
      this.message = messageIn;
      this.fadeInTime = fadeInTimeIn;
      this.displayTime = displayTimeIn;
      this.fadeOutTime = fadeOutTimeIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.type = (Type)buf.readEnumValue(Type.class);
      if (this.type == SPacketTitle.Type.TITLE || this.type == SPacketTitle.Type.SUBTITLE || this.type == SPacketTitle.Type.ACTIONBAR) {
         this.message = buf.readTextComponent();
      }

      if (this.type == SPacketTitle.Type.TIMES) {
         this.fadeInTime = buf.readInt();
         this.displayTime = buf.readInt();
         this.fadeOutTime = buf.readInt();
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.type);
      if (this.type == SPacketTitle.Type.TITLE || this.type == SPacketTitle.Type.SUBTITLE || this.type == SPacketTitle.Type.ACTIONBAR) {
         buf.writeTextComponent(this.message);
      }

      if (this.type == SPacketTitle.Type.TIMES) {
         buf.writeInt(this.fadeInTime);
         buf.writeInt(this.displayTime);
         buf.writeInt(this.fadeOutTime);
      }

   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTitle(this);
   }

   public Type getType() {
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
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }

   public static enum Type {
      TITLE,
      SUBTITLE,
      ACTIONBAR,
      TIMES,
      CLEAR,
      RESET;

      private Type() {
      }

      public static Type byName(String name) {
         Type[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Type spackettitle$type = var1[var3];
            if (spackettitle$type.name().equalsIgnoreCase(name)) {
               return spackettitle$type;
            }
         }

         return TITLE;
      }

      public static String[] getNames() {
         String[] astring = new String[values().length];
         int i = 0;
         Type[] var2 = values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Type spackettitle$type = var2[var4];
            astring[i++] = spackettitle$type.name().toLowerCase(Locale.ROOT);
         }

         return astring;
      }
   }
}
