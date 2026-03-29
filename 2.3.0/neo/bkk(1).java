package neo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class bkk {
   private static Map<String, bkn> mapModelAdapters = makeMapModelAdapters();

   public bkk() {
   }

   private static Map<String, bkn> makeMapModelAdapters() {
      Map<String, bkn> map = new LinkedHashMap();
      addModelAdapter(map, new bko());
      addModelAdapter(map, new bkq());
      addModelAdapter(map, new bkt());
      addModelAdapter(map, new bku());
      addModelAdapter(map, new bkw());
      addModelAdapter(map, new bkz());
      addModelAdapter(map, new bkA());
      addModelAdapter(map, new bkB());
      addModelAdapter(map, new bkD());
      addModelAdapter(map, new bkC());
      addModelAdapter(map, new bkF());
      addModelAdapter(map, new bkG());
      addModelAdapter(map, new bkH());
      addModelAdapter(map, new bkI());
      addModelAdapter(map, new bkJ());
      addModelAdapter(map, new bkK());
      addModelAdapter(map, new bkL());
      addModelAdapter(map, new bkM());
      addModelAdapter(map, new bkQ());
      addModelAdapter(map, new bkR());
      addModelAdapter(map, new bkT());
      addModelAdapter(map, new bkU());
      addModelAdapter(map, new bkV());
      addModelAdapter(map, new bkW());
      addModelAdapter(map, new bkX());
      addModelAdapter(map, new bkZ());
      addModelAdapter(map, new bkY());
      addModelAdapter(map, new bla());
      addModelAdapter(map, new blb());
      addModelAdapter(map, new blc());
      addModelAdapter(map, new bld());
      addModelAdapter(map, new ble());
      addModelAdapter(map, new blf());
      addModelAdapter(map, new blg());
      addModelAdapter(map, new bli());
      addModelAdapter(map, new blj());
      addModelAdapter(map, new bll());
      addModelAdapter(map, new bln());
      addModelAdapter(map, new blp());
      addModelAdapter(map, new blq());
      addModelAdapter(map, new blr());
      addModelAdapter(map, new bls());
      addModelAdapter(map, new blt());
      addModelAdapter(map, new blu());
      addModelAdapter(map, new blv());
      addModelAdapter(map, new blw());
      addModelAdapter(map, new blx());
      addModelAdapter(map, new bly());
      addModelAdapter(map, new blz());
      addModelAdapter(map, new blA());
      addModelAdapter(map, new blB());
      addModelAdapter(map, new blC());
      addModelAdapter(map, new blD());
      addModelAdapter(map, new blE());
      addModelAdapter(map, new blF());
      addModelAdapter(map, new blG());
      addModelAdapter(map, new blH());
      addModelAdapter(map, new blk());
      addModelAdapter(map, new bkp());
      addModelAdapter(map, new bkr());
      addModelAdapter(map, new bkv());
      addModelAdapter(map, new bkx());
      addModelAdapter(map, new bky());
      addModelAdapter(map, new bkE());
      addModelAdapter(map, new bkN());
      addModelAdapter(map, new bkO());
      addModelAdapter(map, new bkP());
      addModelAdapter(map, new blm());
      addModelAdapter(map, new blo());
      return map;
   }

   private static void addModelAdapter(Map<String, bkn> map, bkn modelAdapter) {
      addModelAdapter(map, modelAdapter, modelAdapter.getName());
      String[] astring = modelAdapter.getAliases();
      if (astring != null) {
         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            addModelAdapter(map, modelAdapter, s);
         }
      }

      nH modelbase = modelAdapter.makeModel();
      String[] astring1 = modelAdapter.getModelRendererNames();

      for(int j = 0; j < astring1.length; ++j) {
         String s1 = astring1[j];
         ow modelrenderer = modelAdapter.getModelRenderer(modelbase, s1);
         if (modelrenderer == null) {
            XH.warn("Model renderer not found, model: " + modelAdapter.getName() + ", name: " + s1);
         }
      }

   }

   private static void addModelAdapter(Map<String, bkn> map, bkn modelAdapter, String name) {
      if (map.containsKey(name)) {
         XH.warn("Model adapter already registered for id: " + name + ", class: " + modelAdapter.getEntityClass().getName());
      }

      map.put(name, modelAdapter);
   }

   public static bkn getModelAdapter(String name) {
      return (bkn)mapModelAdapters.get(name);
   }

   public static String[] getModelNames() {
      Set<String> set = mapModelAdapters.keySet();
      String[] astring = (String[])((String[])set.toArray(new String[set.size()]));
      return astring;
   }
}
