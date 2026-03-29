package neo;

import java.util.HashMap;
import java.util.Map;

public class blc extends bkn {
   private static Map<String, Integer> mapPartFields = null;

   public blc() {
      super(LN.class, "ocelot", 0.4F);
   }

   public nH makeModel() {
      return new oo();
   }

   public ow getModelRenderer(nH model, String modelPart) {
      if (!(model instanceof oo)) {
         return null;
      } else {
         oo modelocelot = (oo)model;
         Map<String, Integer> map = getMapPartFields();
         if (map.containsKey(modelPart)) {
            int i = (Integer)map.get(modelPart);
            return (ow)bnK.getFieldValue(modelocelot, bnK.ModelOcelot_ModelRenderers, i);
         } else {
            return null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
   }

   private static Map<String, Integer> getMapPartFields() {
      if (mapPartFields != null) {
         return mapPartFields;
      } else {
         mapPartFields = new HashMap();
         mapPartFields.put("back_left_leg", 0);
         mapPartFields.put("back_right_leg", 1);
         mapPartFields.put("front_left_leg", 2);
         mapPartFields.put("front_right_leg", 3);
         mapPartFields.put("tail", 4);
         mapPartFields.put("tail2", 5);
         mapPartFields.put("head", 6);
         mapPartFields.put("body", 7);
         return mapPartFields;
      }
   }

   public bkm makeEntityRender(nH modelBase, float shadowSize) {
      wC rendermanager = nC.getMinecraft().getRenderManager();
      wG renderocelot = new wG(rendermanager);
      renderocelot.mainModel = modelBase;
      renderocelot.shadowSize = shadowSize;
      return renderocelot;
   }
}
