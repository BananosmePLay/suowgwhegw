package neo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

public class Aj {
   private final IRegistry<String, Ai<? extends Ad>> metadataSectionSerializerRegistry = new RegistrySimple();
   private final GsonBuilder gsonBuilder = new GsonBuilder();
   private Gson gson;

   public Aj() {
      this.gsonBuilder.registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer());
      this.gsonBuilder.registerTypeHierarchyAdapter(Style.class, new Style.Serializer());
      this.gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
   }

   public <T extends Ad> void registerMetadataSectionType(Ae<T> metadataSectionSerializer, Class<T> clazz) {
      this.metadataSectionSerializerRegistry.putObject(metadataSectionSerializer.getSectionName(), new Ai(this, metadataSectionSerializer, clazz));
      this.gsonBuilder.registerTypeAdapter(clazz, metadataSectionSerializer);
      this.gson = null;
   }

   public <T extends Ad> T parseMetadataSection(String sectionName, JsonObject json) {
      if (sectionName == null) {
         throw new IllegalArgumentException("Metadata section name cannot be null");
      } else if (!json.has(sectionName)) {
         return (Ad)null;
      } else if (!json.get(sectionName).isJsonObject()) {
         throw new IllegalArgumentException("Invalid metadata for '" + sectionName + "' - expected object, found " + json.get(sectionName));
      } else {
         Ai<?> registration = (Ai)this.metadataSectionSerializerRegistry.getObject(sectionName);
         if (registration == null) {
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + sectionName + "'");
         } else {
            return (Ad)this.getGson().fromJson(json.getAsJsonObject(sectionName), registration.clazz);
         }
      }
   }

   private Gson getGson() {
      if (this.gson == null) {
         this.gson = this.gsonBuilder.create();
      }

      return this.gson;
   }
}
