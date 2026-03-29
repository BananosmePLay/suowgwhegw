package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bdt {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Map<String, Class<? extends beM>> startNameToClassMap = Maps.newHashMap();
   private static final Map<Class<? extends beM>, String> startClassToNameMap = Maps.newHashMap();
   private static final Map<String, Class<? extends bdB>> componentNameToClassMap = Maps.newHashMap();
   private static final Map<Class<? extends bdB>, String> componentClassToNameMap = Maps.newHashMap();

   public bdt() {
   }

   private static void registerStructure(Class<? extends beM> startClass, String structureName) {
      startNameToClassMap.put(structureName, startClass);
      startClassToNameMap.put(startClass, structureName);
   }

   static void registerStructureComponent(Class<? extends bdB> componentClass, String componentName) {
      componentNameToClassMap.put(componentName, componentClass);
      componentClassToNameMap.put(componentClass, componentName);
   }

   public static String getStructureStartName(beM start) {
      return (String)startClassToNameMap.get(start.getClass());
   }

   public static String getStructureComponentName(bdB component) {
      return (String)componentClassToNameMap.get(component.getClass());
   }

   @Nullable
   public static beM getStructureStart(QQ tagCompound, bij worldIn) {
      beM structurestart = null;

      try {
         Class<? extends beM> oclass = (Class)startNameToClassMap.get(tagCompound.getString("id"));
         if (oclass != null) {
            structurestart = (beM)oclass.newInstance();
         }
      } catch (Exception var4) {
         Exception exception = var4;
         LOGGER.warn("Failed Start with id {}", tagCompound.getString("id"));
         exception.printStackTrace();
      }

      if (structurestart != null) {
         structurestart.readStructureComponentsFromNBT(worldIn, tagCompound);
      } else {
         LOGGER.warn("Skipping Structure with id {}", tagCompound.getString("id"));
      }

      return structurestart;
   }

   public static bdB getStructureComponent(QQ tagCompound, bij worldIn) {
      bdB structurecomponent = null;

      try {
         Class<? extends bdB> oclass = (Class)componentNameToClassMap.get(tagCompound.getString("id"));
         if (oclass != null) {
            structurecomponent = (bdB)oclass.newInstance();
         }
      } catch (Exception var4) {
         Exception exception = var4;
         LOGGER.warn("Failed Piece with id {}", tagCompound.getString("id"));
         exception.printStackTrace();
      }

      if (structurecomponent != null) {
         structurecomponent.readStructureBaseNBT(worldIn, tagCompound);
      } else {
         LOGGER.warn("Skipping Piece with id {}", tagCompound.getString("id"));
      }

      return structurecomponent;
   }

   static {
      registerStructure(bdS.class, "Mineshaft");
      registerStructure(bdu.class, "Village");
      registerStructure(bdi.class, "Fortress");
      registerStructure(bdm.class, "Stronghold");
      registerStructure(bdk.class, "Temple");
      registerStructure(bem.class, "Monument");
      registerStructure(bde.class, "EndCity");
      registerStructure(bfM.class, "Mansion");
      bdR.registerStructurePieces();
      bfz.registerVillagePieces();
      bel.registerNetherFortressPieces();
      bfh.registerStrongholdPieces();
      bdd.registerScatteredFeaturePieces();
      beL.registerOceanMonumentPieces();
      bdK.registerPieces();
      bfY.registerWoodlandMansionPieces();
   }
}
