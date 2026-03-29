package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class cb {
   private final ITextComponent title;
   private final ITextComponent description;
   private final Qy icon;
   private final ResourceLocation background;
   private final cc frame;
   private final boolean showToast;
   private final boolean announceToChat;
   private final boolean hidden;
   private float x;
   private float y;

   public cb(Qy icon, ITextComponent title, ITextComponent description, @Nullable ResourceLocation background, cc frame, boolean showToast, boolean announceToChat, boolean hidden) {
      this.title = title;
      this.description = description;
      this.icon = icon;
      this.background = background;
      this.frame = frame;
      this.showToast = showToast;
      this.announceToChat = announceToChat;
      this.hidden = hidden;
   }

   public void setPosition(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public ITextComponent getTitle() {
      return this.title;
   }

   public ITextComponent getDescription() {
      return this.description;
   }

   public Qy getIcon() {
      return this.icon;
   }

   @Nullable
   public ResourceLocation getBackground() {
      return this.background;
   }

   public cc getFrame() {
      return this.frame;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public boolean shouldShowToast() {
      return this.showToast;
   }

   public boolean shouldAnnounceToChat() {
      return this.announceToChat;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public static cb deserialize(JsonObject object, JsonDeserializationContext context) {
      ITextComponent itextcomponent = (ITextComponent)JsonUtils.deserializeClass(object, "title", context, ITextComponent.class);
      ITextComponent itextcomponent1 = (ITextComponent)JsonUtils.deserializeClass(object, "description", context, ITextComponent.class);
      if (itextcomponent != null && itextcomponent1 != null) {
         Qy itemstack = deserializeIcon(JsonUtils.getJsonObject(object, "icon"));
         ResourceLocation resourcelocation = object.has("background") ? new ResourceLocation(JsonUtils.getString(object, "background")) : null;
         cc frametype = object.has("frame") ? cc.byName(JsonUtils.getString(object, "frame")) : cc.TASK;
         boolean flag = JsonUtils.getBoolean(object, "show_toast", true);
         boolean flag1 = JsonUtils.getBoolean(object, "announce_to_chat", true);
         boolean flag2 = JsonUtils.getBoolean(object, "hidden", false);
         return new cb(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, flag1, flag2);
      } else {
         throw new JsonSyntaxException("Both title and description must be set");
      }
   }

   private static Qy deserializeIcon(JsonObject object) {
      if (!object.has("item")) {
         throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
      } else {
         OL item = JsonUtils.getItem(object, "item");
         int i = JsonUtils.getInt(object, "data", 0);
         return new Qy(item, 1, i);
      }
   }

   public void write(SA buf) {
      buf.writeTextComponent(this.title);
      buf.writeTextComponent(this.description);
      buf.writeItemStack(this.icon);
      buf.writeEnumValue(this.frame);
      int i = 0;
      if (this.background != null) {
         i |= 1;
      }

      if (this.showToast) {
         i |= 2;
      }

      if (this.hidden) {
         i |= 4;
      }

      buf.writeInt(i);
      if (this.background != null) {
         buf.writeResourceLocation(this.background);
      }

      buf.writeFloat(this.x);
      buf.writeFloat(this.y);
   }

   public static cb read(SA buf) throws IOException {
      ITextComponent itextcomponent = buf.readTextComponent();
      ITextComponent itextcomponent1 = buf.readTextComponent();
      Qy itemstack = buf.readItemStack();
      cc frametype = (cc)buf.readEnumValue(cc.class);
      int i = buf.readInt();
      ResourceLocation resourcelocation = (i & 1) != 0 ? buf.readResourceLocation() : null;
      boolean flag = (i & 2) != 0;
      boolean flag1 = (i & 4) != 0;
      cb displayinfo = new cb(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, false, flag1);
      displayinfo.setPosition(buf.readFloat(), buf.readFloat());
      return displayinfo;
   }
}
