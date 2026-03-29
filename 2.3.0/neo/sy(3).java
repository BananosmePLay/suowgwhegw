package neo;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class sy {
   private static final Logger LOGGER = LogManager.getLogger();
   @VisibleForTesting
   static final Gson SERIALIZER = (new GsonBuilder()).registerTypeAdapter(sy.class, new sw()).registerTypeAdapter(rQ.class, new rP()).registerTypeAdapter(rS.class, new rR()).registerTypeAdapter(rN.class, new rM()).registerTypeAdapter(sp.class, new so()).registerTypeAdapter(sg.class, new se()).registerTypeAdapter(sm.class, new sl()).create();
   private final List<rQ> elements;
   private final boolean gui3d;
   private final boolean ambientOcclusion;
   private final sg cameraTransforms;
   private final List<sm> overrides;
   public String name = "";
   @VisibleForTesting
   protected final Map<String, String> textures;
   @VisibleForTesting
   protected sy parent;
   @VisibleForTesting
   protected ResourceLocation parentLocation;

   public static sy deserialize(Reader readerIn) {
      return (sy)JsonUtils.gsonDeserialize(SERIALIZER, readerIn, sy.class, false);
   }

   public static sy deserialize(String jsonString) {
      return deserialize((Reader)(new StringReader(jsonString)));
   }

   public sy(@Nullable ResourceLocation parentLocationIn, List<rQ> elementsIn, Map<String, String> texturesIn, boolean ambientOcclusionIn, boolean gui3dIn, sg cameraTransformsIn, List<sm> overridesIn) {
      this.elements = elementsIn;
      this.ambientOcclusion = ambientOcclusionIn;
      this.gui3d = gui3dIn;
      this.textures = texturesIn;
      this.parentLocation = parentLocationIn;
      this.cameraTransforms = cameraTransformsIn;
      this.overrides = overridesIn;
   }

   public List<rQ> getElements() {
      return this.elements.isEmpty() && this.hasParent() ? this.parent.getElements() : this.elements;
   }

   private boolean hasParent() {
      return this.parent != null;
   }

   public boolean isAmbientOcclusion() {
      return this.hasParent() ? this.parent.isAmbientOcclusion() : this.ambientOcclusion;
   }

   public boolean isGui3d() {
      return this.gui3d;
   }

   public boolean isResolved() {
      return this.parentLocation == null || this.parent != null && this.parent.isResolved();
   }

   public void getParentFromMap(Map<ResourceLocation, sy> p_178299_1_) {
      if (this.parentLocation != null) {
         this.parent = (sy)p_178299_1_.get(this.parentLocation);
      }

   }

   public Collection<ResourceLocation> getOverrideLocations() {
      Set<ResourceLocation> set = Sets.newHashSet();
      Iterator var2 = this.overrides.iterator();

      while(var2.hasNext()) {
         sm itemoverride = (sm)var2.next();
         set.add(itemoverride.getLocation());
      }

      return set;
   }

   protected List<sm> getOverrides() {
      return this.overrides;
   }

   public sn createOverrides() {
      return this.overrides.isEmpty() ? sn.NONE : new sn(this.overrides);
   }

   public boolean isTexturePresent(String textureName) {
      return !"missingno".equals(this.resolveTextureName(textureName));
   }

   public String resolveTextureName(String textureName) {
      if (!this.startsWithHash(textureName)) {
         textureName = '#' + textureName;
      }

      return this.resolveTextureName(textureName, new sv(this));
   }

   private String resolveTextureName(String textureName, sv p_178302_2_) {
      if (this.startsWithHash(textureName)) {
         if (this == p_178302_2_.modelExt) {
            LOGGER.warn("Unable to resolve texture due to upward reference: {} in {}", textureName, this.name);
            return "missingno";
         } else {
            String s = (String)this.textures.get(textureName.substring(1));
            if (s == null && this.hasParent()) {
               s = this.parent.resolveTextureName(textureName, p_178302_2_);
            }

            p_178302_2_.modelExt = this;
            if (s != null && this.startsWithHash(s)) {
               s = p_178302_2_.model.resolveTextureName(s, p_178302_2_);
            }

            return s != null && !this.startsWithHash(s) ? s : "missingno";
         }
      } else {
         return textureName;
      }
   }

   private boolean startsWithHash(String hash) {
      return hash.charAt(0) == '#';
   }

   @Nullable
   public ResourceLocation getParentLocation() {
      return this.parentLocation;
   }

   public sy getRootModel() {
      return this.hasParent() ? this.parent.getRootModel() : this;
   }

   public sg getAllTransforms() {
      sp itemtransformvec3f = this.getTransform(sf.THIRD_PERSON_LEFT_HAND);
      sp itemtransformvec3f1 = this.getTransform(sf.THIRD_PERSON_RIGHT_HAND);
      sp itemtransformvec3f2 = this.getTransform(sf.FIRST_PERSON_LEFT_HAND);
      sp itemtransformvec3f3 = this.getTransform(sf.FIRST_PERSON_RIGHT_HAND);
      sp itemtransformvec3f4 = this.getTransform(sf.HEAD);
      sp itemtransformvec3f5 = this.getTransform(sf.GUI);
      sp itemtransformvec3f6 = this.getTransform(sf.GROUND);
      sp itemtransformvec3f7 = this.getTransform(sf.FIXED);
      return new sg(itemtransformvec3f, itemtransformvec3f1, itemtransformvec3f2, itemtransformvec3f3, itemtransformvec3f4, itemtransformvec3f5, itemtransformvec3f6, itemtransformvec3f7);
   }

   private sp getTransform(sf type) {
      return this.parent != null && !this.cameraTransforms.hasCustomTransform(type) ? this.parent.getTransform(type) : this.cameraTransforms.getTransform(type);
   }

   public static void checkModelHierarchy(Map<ResourceLocation, sy> p_178312_0_) {
      Iterator var1 = p_178312_0_.values().iterator();

      while(var1.hasNext()) {
         sy modelblock = (sy)var1.next();

         try {
            sy modelblock1 = modelblock.parent;

            for(sy modelblock2 = modelblock1.parent; modelblock1 != modelblock2; modelblock2 = modelblock2.parent.parent) {
               modelblock1 = modelblock1.parent;
            }

            throw new sx();
         } catch (NullPointerException var5) {
         }
      }

   }
}
