package net.minecraft.client.util;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import net.minecraft.util.ResourceLocation;

public class SearchTree<T> implements ISearchTree<T> {
   protected SuffixArray<T> byName = new SuffixArray();
   protected SuffixArray<T> byId = new SuffixArray();
   private final Function<T, Iterable<String>> nameFunc;
   private final Function<T, Iterable<ResourceLocation>> idFunc;
   private final List<T> contents = Lists.newArrayList();
   private Object2IntMap<T> numericContents = new Object2IntOpenHashMap();

   public SearchTree(Function<T, Iterable<String>> nameFuncIn, Function<T, Iterable<ResourceLocation>> idFuncIn) {
      this.nameFunc = nameFuncIn;
      this.idFunc = idFuncIn;
   }

   public void recalculate() {
      this.byName = new SuffixArray();
      this.byId = new SuffixArray();
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
         return (List)(list1.isEmpty() ? list : Lists.newArrayList((Iterator)(new MergingIterator(list.iterator(), list1.iterator(), this.numericContents))));
      }
   }

   static class MergingIterator<T> extends AbstractIterator<T> {
      private final Iterator<T> leftItr;
      private final Iterator<T> rightItr;
      private final Object2IntMap<T> numbers;
      private T left;
      private T right;

      public MergingIterator(Iterator<T> leftIn, Iterator<T> rightIn, Object2IntMap<T> numbersIn) {
         this.leftItr = leftIn;
         this.rightItr = rightIn;
         this.numbers = numbersIn;
         this.left = leftIn.hasNext() ? leftIn.next() : null;
         this.right = rightIn.hasNext() ? rightIn.next() : null;
      }

      protected T computeNext() {
         if (this.left == null && this.right == null) {
            return this.endOfData();
         } else {
            int i;
            if (this.left == this.right) {
               i = 0;
            } else if (this.left == null) {
               i = 1;
            } else if (this.right == null) {
               i = -1;
            } else {
               i = Integer.compare(this.numbers.getInt(this.left), this.numbers.getInt(this.right));
            }

            T t = i <= 0 ? this.left : this.right;
            if (i <= 0) {
               this.left = this.leftItr.hasNext() ? this.leftItr.next() : null;
            }

            if (i >= 0) {
               this.right = this.rightItr.hasNext() ? this.rightItr.next() : null;
            }

            return t;
         }
      }
   }
}
