package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class oY {
   private static final Logger LOGGER = LogManager.getLogger();
   private final nC mc;
   private final d advancementList = new d();
   private final Map<b, h> advancementToProgress = Maps.newHashMap();
   @Nullable
   private oX listener;
   @Nullable
   private b selectedTab;

   public oY(nC p_i47380_1_) {
      this.mc = p_i47380_1_;
   }

   public void read(Tw packetIn) {
      if (packetIn.isFirstSync()) {
         this.advancementList.clear();
         this.advancementToProgress.clear();
      }

      this.advancementList.removeAll(packetIn.getAdvancementsToRemove());
      this.advancementList.loadAdvancements(packetIn.getAdvancementsToAdd());
      Iterator var2 = packetIn.getProgressUpdates().entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<ResourceLocation, h> entry = (Map.Entry)var2.next();
         b advancement = this.advancementList.getAdvancement((ResourceLocation)entry.getKey());
         if (advancement != null) {
            h advancementprogress = (h)entry.getValue();
            advancementprogress.update(advancement.getCriteria(), advancement.getRequirements());
            this.advancementToProgress.put(advancement, advancementprogress);
            if (this.listener != null) {
               this.listener.onUpdateAdvancementProgress(advancement, advancementprogress);
            }

            if (!packetIn.isFirstSync() && advancementprogress.isDone() && advancement.getDisplay() != null && advancement.getDisplay().shouldShowToast()) {
               this.mc.getToastGui().add(new mZ(advancement));
            }
         } else {
            LOGGER.warn("Server informed client about progress for unknown advancement " + entry.getKey());
         }
      }

   }

   public d getAdvancementList() {
      return this.advancementList;
   }

   public void setSelectedTab(@Nullable b advancementIn, boolean tellServer) {
      py nethandlerplayclient = this.mc.getConnection();
      if (nethandlerplayclient != null && advancementIn != null && tellServer) {
         nethandlerplayclient.sendPacket(Tj.openedTab(advancementIn));
      }

      if (this.selectedTab != advancementIn) {
         this.selectedTab = advancementIn;
         if (this.listener != null) {
            this.listener.setSelectedTab(advancementIn);
         }
      }

   }

   public void setListener(@Nullable oX listenerIn) {
      this.listener = listenerIn;
      this.advancementList.setListener(listenerIn);
      if (listenerIn != null) {
         Iterator var2 = this.advancementToProgress.entrySet().iterator();

         while(var2.hasNext()) {
            Map.Entry<b, h> entry = (Map.Entry)var2.next();
            listenerIn.onUpdateAdvancementProgress((b)entry.getKey(), (h)entry.getValue());
         }

         listenerIn.setSelectedTab(this.selectedTab);
      }

   }
}
