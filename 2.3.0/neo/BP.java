package neo;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BP extends XK {
   public static final Map<EN, List<mB>> RECIPES_BY_TAB = Maps.newHashMap();
   public static final List<mB> ALL_RECIPES = Lists.newArrayList();

   public BP() {
   }

   private static mB newRecipeList(EN srcTab) {
      mB recipelist = new mB();
      ALL_RECIPES.add(recipelist);
      ((List)RECIPES_BY_TAB.computeIfAbsent(srcTab, (p_194085_0_) -> {
         return new ArrayList();
      })).add(recipelist);
      ((List)RECIPES_BY_TAB.computeIfAbsent(EN.SEARCH, (p_194083_0_) -> {
         return new ArrayList();
      })).add(recipelist);
      return recipelist;
   }

   private static EN getItemStackTab(Qy stackIn) {
      EN creativetabs = stackIn.getItem().getCreativeTab();
      if (creativetabs != EN.BUILDING_BLOCKS && creativetabs != EN.TOOLS && creativetabs != EN.REDSTONE) {
         return creativetabs == EN.COMBAT ? EN.TOOLS : EN.MISC;
      } else {
         return creativetabs;
      }
   }

   static {
      Table<EN, String, mB> table = HashBasedTable.create();
      Iterator var1 = NP.REGISTRY.iterator();

      while(var1.hasNext()) {
         NT irecipe = (NT)var1.next();
         if (!irecipe.isDynamic()) {
            EN creativetabs = getItemStackTab(irecipe.getRecipeOutput());
            String s = irecipe.getGroup();
            mB recipelist1;
            if (s.isEmpty()) {
               recipelist1 = newRecipeList(creativetabs);
            } else {
               recipelist1 = (mB)table.get(creativetabs, s);
               if (recipelist1 == null) {
                  recipelist1 = newRecipeList(creativetabs);
                  table.put(creativetabs, s, recipelist1);
               }
            }

            recipelist1.add(irecipe);
         }
      }

   }
}
