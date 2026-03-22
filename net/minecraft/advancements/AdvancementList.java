package net.minecraft.advancements;

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancementList {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map<ResourceLocation, Advancement> advancements = Maps.newHashMap();
   private final Set<Advancement> roots = Sets.newLinkedHashSet();
   private final Set<Advancement> nonRoots = Sets.newLinkedHashSet();
   private Listener listener;

   public AdvancementList() {
   }

   private void remove(Advancement advancementIn) {
      Iterator var2 = advancementIn.getChildren().iterator();

      while(var2.hasNext()) {
         Advancement advancement = (Advancement)var2.next();
         this.remove(advancement);
      }

      LOGGER.info("Forgot about advancement " + advancementIn.getId());
      this.advancements.remove(advancementIn.getId());
      if (advancementIn.getParent() == null) {
         this.roots.remove(advancementIn);
         if (this.listener != null) {
            this.listener.rootAdvancementRemoved(advancementIn);
         }
      } else {
         this.nonRoots.remove(advancementIn);
         if (this.listener != null) {
            this.listener.nonRootAdvancementRemoved(advancementIn);
         }
      }

   }

   public void removeAll(Set<ResourceLocation> ids) {
      Iterator var2 = ids.iterator();

      while(var2.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var2.next();
         Advancement advancement = (Advancement)this.advancements.get(resourcelocation);
         if (advancement == null) {
            LOGGER.warn("Told to remove advancement " + resourcelocation + " but I don't know what that is");
         } else {
            this.remove(advancement);
         }
      }

   }

   public void loadAdvancements(Map<ResourceLocation, Advancement.Builder> advancementsIn) {
      Function<ResourceLocation, Advancement> function = Functions.forMap(this.advancements, (Object)null);

      label42:
      while(!advancementsIn.isEmpty()) {
         boolean flag = false;
         Iterator<Map.Entry<ResourceLocation, Advancement.Builder>> iterator = advancementsIn.entrySet().iterator();

         Map.Entry entry1;
         while(iterator.hasNext()) {
            entry1 = (Map.Entry)iterator.next();
            ResourceLocation resourcelocation = (ResourceLocation)entry1.getKey();
            Advancement.Builder advancement$builder = (Advancement.Builder)entry1.getValue();
            if (advancement$builder.resolveParent(function)) {
               Advancement advancement = advancement$builder.build(resourcelocation);
               this.advancements.put(resourcelocation, advancement);
               flag = true;
               iterator.remove();
               if (advancement.getParent() == null) {
                  this.roots.add(advancement);
                  if (this.listener != null) {
                     this.listener.rootAdvancementAdded(advancement);
                  }
               } else {
                  this.nonRoots.add(advancement);
                  if (this.listener != null) {
                     this.listener.nonRootAdvancementAdded(advancement);
                  }
               }
            }
         }

         if (!flag) {
            iterator = advancementsIn.entrySet().iterator();

            while(true) {
               if (!iterator.hasNext()) {
                  break label42;
               }

               entry1 = (Map.Entry)iterator.next();
               LOGGER.error("Couldn't load advancement " + entry1.getKey() + ": " + entry1.getValue());
            }
         }
      }

      LOGGER.info("Loaded " + this.advancements.size() + " advancements");
   }

   public void clear() {
      this.advancements.clear();
      this.roots.clear();
      this.nonRoots.clear();
      if (this.listener != null) {
         this.listener.advancementsCleared();
      }

   }

   public Iterable<Advancement> getRoots() {
      return this.roots;
   }

   public Iterable<Advancement> getAdvancements() {
      return this.advancements.values();
   }

   @Nullable
   public Advancement getAdvancement(ResourceLocation id) {
      return (Advancement)this.advancements.get(id);
   }

   public void setListener(@Nullable Listener listenerIn) {
      this.listener = listenerIn;
      if (listenerIn != null) {
         Iterator var2 = this.roots.iterator();

         Advancement advancement1;
         while(var2.hasNext()) {
            advancement1 = (Advancement)var2.next();
            listenerIn.rootAdvancementAdded(advancement1);
         }

         var2 = this.nonRoots.iterator();

         while(var2.hasNext()) {
            advancement1 = (Advancement)var2.next();
            listenerIn.nonRootAdvancementAdded(advancement1);
         }
      }

   }

   public interface Listener {
      void rootAdvancementAdded(Advancement var1);

      void rootAdvancementRemoved(Advancement var1);

      void nonRootAdvancementAdded(Advancement var1);

      void nonRootAdvancementRemoved(Advancement var1);

      void advancementsCleared();
   }
}
