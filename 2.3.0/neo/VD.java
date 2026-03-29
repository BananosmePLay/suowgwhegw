package neo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

public class VD implements Sz<Vy> {
   private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(Vt.class, new Vs()).registerTypeAdapter(Vq.class, new Vp()).registerTypeAdapter(Vu.class, new Vr()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeHierarchyAdapter(Style.class, new Style.Serializer()).registerTypeAdapterFactory(new EnumTypeAdapterFactory()).create();
   private Vu response;

   public VD() {
   }

   public VD(Vu responseIn) {
      this.response = responseIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.response = (Vu)JsonUtils.gsonDeserialize(GSON, buf.readString(32767), Vu.class);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(GSON.toJson(this.response));
   }

   public void processPacket(Vy handler) {
      handler.handleServerInfo(this);
   }

   public Vu getResponse() {
      return this.response;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Vy)var1);
   }
}
