package neo;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import net.minecraft.util.ResourceLocation;

public class BT<T> implements BH<T> {
   protected BX<T> byName = new BX();
   protected BX<T> byId = new BX();
   private final Function<T, Iterable<String>> nameFunc;
   private final Function<T, Iterable<ResourceLocation>> idFunc;
   private final List<T> contents = Lists.newArrayList();
   private Object2IntMap<T> numericContents = new Object2IntOpenHashMap();

   public BT(Function<T, Iterable<String>> nameFuncIn, Function<T, Iterable<ResourceLocation>> idFuncIn) {
      this.nameFunc = nameFuncIn;
      this.idFunc = idFuncIn;
   }

   public void recalculate() {
      this.byName = new BX();
      this.byId = new BX();
      Iterator var1 = this.contents.iterator();

      while(var1.hasNext()) {
         T t = var1.next();
         this.index(t);
      }

      this.byName.generate();
      this.byId.generate();
   }

   public void add(T element) {
      this.numericContents.put(element, this.contents.size());
      this.contents.add(element);
      this.index(element);
   }

   private void index(T element) {
      ((Iterable)this.idFunc.apply(element)).forEach((p_194039_2_) -> {
         this.byId.add(element, p_194039_2_.toString().toLowerCase(Locale.ROOT));
      });
      ((Iterable)this.nameFunc.apply(element)).forEach((p_194041_2_) -> {
         this.byName.add(element, p_194041_2_.toLowerCase(Locale.ROOT));
      });
   }

   public List<T> search(String searchText) {
      List<T> list = this.byName.search(searchText);
      if (searchText.indexOf(58) < 0) {
         return list;
      } else {
         List<T> list1 = this.byId.search(searchText);
         return (List)(list1.isEmpty() ? list : Lists.newArrayList(new BS(list.iterator(), list1.iterator(), this.numericContents)));
      }
   }
}
