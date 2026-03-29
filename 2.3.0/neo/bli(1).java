package neo;

import java.util.HashMap;
import java.util.Map;

public class bli extends bkn {
   private static Map<String, Integer> mapPartFields = null;

   public bli() {
      super(LY.class, "rabbit", 0.3F);
   }

   public nH makeModel() {
      return new ov();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof ov)) {
         return null;
      } else {
         ov modelrabbit = (ov)model;
         Map<String, Integer> map = getMapPartFields();
         if (map.containsKey(modelPart)) {
            int i = (Integer)map.get(modelPart);
            return (ow)bnK.getFieldValue(modelrabbit, bnK.ModelRabbit_renderers, i);
         } else {
            return null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"};
   }

   private static Map<String, Integer> getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap();
         mapPartFields.put("left_foot", 0);
         mapPartFields.put("right_foot", 1);
         mapPartFields.put("left_thigh", 2);
         mapPartFields.put("right_thigh", 3);
         mapPartFields.put("body", 4);
         mapPartFields.put("left_arm", 5);
         mapPartFields.put("right_arm", 6);
         mapPartFields.put("head", 7);
         mapPartFields.put("right_ear", 8);
         mapPartFields.put("left_ear", 9);
         mapPartFields.put("tail", 10);
         mapPartFields.put("nose", 11);
         return mapPartFields;
      }
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wP renderrabbit = new wP(rendermanager);
      renderrabbit.mainModel = modelBase;
      renderrabbit.shadowSize = shadowSize;
      return renderrabbit;
   }
}
