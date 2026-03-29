package neo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class bnz {
   public String name = null;
   public String basePath = null;
   public ResourceLocation[] resourceLocations = null;
   public bnA[] rules = null;

   public bnz(String path, ResourceLocation[] variants) {
      biS connectedparser = new biS("RandomEntities");
      this.name = connectedparser.parseName(path);
      this.basePath = connectedparser.parseBasePath(path);
      this.resourceLocations = variants;
   }

   public bnz(Properties props, String path, ResourceLocation baseResLoc) {
      biS connectedparser = new biS("RandomEntities");
      this.name = connectedparser.parseName(path);
      this.basePath = connectedparser.parseBasePath(path);
      this.rules = this.parseRules(props, path, baseResLoc, connectedparser);
   }

   public ResourceLocation getTextureLocation(ResourceLocation loc, bmR randomEntity) {
      int j;
      if (this.rules != null) {
         for(j = 0; j < this.rules.length; ++j) {
            bnA randomentityrule = this.rules[j];
            if (randomentityrule.matches(randomEntity)) {
               return randomentityrule.getTextureLocation(loc, randomEntity.getId());
            }
         }
      }

      if (this.resourceLocations != null) {
         j = randomEntity.getId();
         int k = j % this.resourceLocations.length;
         return this.resourceLocations[k];
      } else {
         return loc;
      }
   }

   private bnA[] parseRules(Properties props, String pathProps, ResourceLocation baseResLoc, biS cp) {
      List list = new ArrayList();
      int i = props.size();

      for(int j = 0; j < i; ++j) {
         int k = j + 1;
         String s = props.getProperty("textures." + k);
         if (s == null) {
            s = props.getProperty("skins." + k);
         }

         if (s != null) {
            bnA randomentityrule = new bnA(props, pathProps, baseResLoc, k, s, cp);
            if (randomentityrule.isValid(pathProps)) {
               list.add(randomentityrule);
            }
         }
      }

      bnA[] arandomentityrule = (bnA[])((bnA[])list.toArray(new bnA[list.size()]));
      return arandomentityrule;
   }

   public boolean isValid(String path) {
      if (this.resourceLocations == null && this.rules == null) {
         XH.warn("No skins specified: " + path);
         return false;
      } else {
         int j;
         if (this.rules != null) {
            for(j = 0; j < this.rules.length; ++j) {
               bnA randomentityrule = this.rules[j];
               if (!randomentityrule.isValid(path)) {
                  return false;
               }
            }
         }

         if (this.resourceLocations != null) {
            for(j = 0; j < this.resourceLocations.length; ++j) {
               ResourceLocation resourcelocation = this.resourceLocations[j];
               if (!XH.hasResource(resourcelocation)) {
                  XH.warn("Texture not found: " + resourcelocation.getPath());
                  return false;
               }
            }
         }

         return true;
      }
   }

   public boolean isDefault() {
      if (this.rules != null) {
         return false;
      } else {
         return this.resourceLocations == null;
      }
   }
}
