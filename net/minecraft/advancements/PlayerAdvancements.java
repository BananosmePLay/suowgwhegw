package net.minecraft.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import net.minecraft.network.play.server.SPacketSelectAdvancementsTab;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerAdvancements {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(AdvancementProgress.class, new AdvancementProgress.Serializer()).registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer()).setPrettyPrinting().create();
   private static final TypeToken<Map<ResourceLocation, AdvancementProgress>> MAP_TOKEN = new TypeToken<Map<ResourceLocation, AdvancementProgress>>() {
   };
   private final MinecraftServer server;
   private final File progressFile;
   private final Map<Advancement, AdvancementProgress> progress = Maps.newLinkedHashMap();
   private final Set<Advancement> visible = Sets.newLinkedHashSet();
   private final Set<Advancement> visibilityChanged = Sets.newLinkedHashSet();
   private final Set<Advancement> progressChanged = Sets.newLinkedHashSet();
   private EntityPlayerMP player;
   @Nullable
   private Advancement lastSelectedTab;
   private boolean isFirstPacket = true;

   public PlayerAdvancements(MinecraftServer server, File p_i47422_2_, EntityPlayerMP player) {
      this.server = server;
      this.progressFile = p_i47422_2_;
      this.player = player;
      this.load();
   }

   public void setPlayer(EntityPlayerMP player) {
      this.player = player;
   }

   public void dispose() {
      Iterator var1 = CriteriaTriggers.getAll().iterator();

      while(var1.hasNext()) {
         ICriterionTrigger<?> icriteriontrigger = (ICriterionTrigger)var1.next();
         icriteriontrigger.removeAllListeners(this);
      }

   }

   public void reload() {
      this.dispose();
      this.progress.clear();
      this.visible.clear();
      this.visibilityChanged.clear();
      this.progressChanged.clear();
      this.isFirstPacket = true;
      this.lastSelectedTab = null;
      this.load();
   }

   private void registerListeners() {
      Iterator var1 = this.server.getAdvancementManager().getAdvancements().iterator();

      while(var1.hasNext()) {
         Advancement advancement = (Advancement)var1.next();
         this.registerListeners(advancement);
      }

   }

   private void ensureAllVisible() {
      List<Advancement> list = Lists.newArrayList();
      Iterator var2 = this.progress.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<Advancement, AdvancementProgress> entry = (Map.Entry)var2.next();
         if (((AdvancementProgress)entry.getValue()).isDone()) {
            list.add(entry.getKey());
            this.progressChanged.add(entry.getKey());
         }
      }

      var2 = list.iterator();

      while(var2.hasNext()) {
         Advancement advancement = (Advancement)var2.next();
         this.ensureVisibility(advancement);
      }

   }

   private void checkForAutomaticTriggers() {
      Iterator var1 = this.server.getAdvancementManager().getAdvancements().iterator();

      while(var1.hasNext()) {
         Advancement advancement = (Advancement)var1.next();
         if (advancement.getCriteria().isEmpty()) {
            this.grantCriterion(advancement, "");
            advancement.getRewards().apply(this.player);
         }
      }

   }

   private void load() {
      if (this.progressFile.isFile()) {
         try {
            String s = Files.toString(this.progressFile, StandardCharsets.UTF_8);
            Map<ResourceLocation, AdvancementProgress> map = (Map)JsonUtils.gsonDeserialize(GSON, s, MAP_TOKEN.getType());
            if (map == null) {
               throw new JsonParseException("Found null for advancements");
            }

            Stream<Map.Entry<ResourceLocation, AdvancementProgress>> stream = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));
            Iterator var4 = ((List)stream.collect(Collectors.toList())).iterator();

            while(var4.hasNext()) {
               Map.Entry<ResourceLocation, AdvancementProgress> entry = (Map.Entry)var4.next();
               Advancement advancement = this.server.getAdvancementManager().getAdvancement((ResourceLocation)entry.getKey());
               if (advancement == null) {
                  LOGGER.warn("Ignored advancement '" + entry.getKey() + "' in progress file " + this.progressFile + " - it doesn't exist anymore?");
               } else {
                  this.startProgress(advancement, (AdvancementProgress)entry.getValue());
               }
            }
         } catch (JsonParseException var7) {
            JsonParseException jsonparseexception = var7;
            LOGGER.error("Couldn't parse player advancements in " + this.progressFile, jsonparseexception);
         } catch (IOException var8) {
            IOException ioexception = var8;
            LOGGER.error("Couldn't access player advancements in " + this.progressFile, ioexception);
         }
      }

      this.checkForAutomaticTriggers();
      this.ensureAllVisible();
      this.registerListeners();
   }

   public void save() {
      Map<ResourceLocation, AdvancementProgress> map = Maps.newHashMap();
      Iterator var2 = this.progress.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<Advancement, AdvancementProgress> entry = (Map.Entry)var2.next();
         AdvancementProgress advancementprogress = (AdvancementProgress)entry.getValue();
         if (advancementprogress.hasProgress()) {
            map.put(((Advancement)entry.getKey()).getId(), advancementprogress);
         }
      }

      if (this.progressFile.getParentFile() != null) {
         this.progressFile.getParentFile().mkdirs();
      }

      try {
         Files.write(GSON.toJson(map), this.progressFile, StandardCharsets.UTF_8);
      } catch (IOException var5) {
         IOException ioexception = var5;
         LOGGER.error("Couldn't save player advancements to " + this.progressFile, ioexception);
      }

   }

   public boolean grantCriterion(Advancement p_192750_1_, String p_192750_2_) {
      boolean flag = false;
      AdvancementProgress advancementprogress = this.getProgress(p_192750_1_);
      boolean flag1 = advancementprogress.isDone();
      if (advancementprogress.grantCriterion(p_192750_2_)) {
         this.unregisterListeners(p_192750_1_);
         this.progressChanged.add(p_192750_1_);
         flag = true;
         if (!flag1 && advancementprogress.isDone()) {
            p_192750_1_.getRewards().apply(this.player);
            if (p_192750_1_.getDisplay() != null && p_192750_1_.getDisplay().shouldAnnounceToChat() && this.player.world.getGameRules().getBoolean("announceAdvancements")) {
               this.server.getPlayerList().sendMessage(new TextComponentTranslation("chat.type.advancement." + p_192750_1_.getDisplay().getFrame().getName(), new Object[]{this.player.getDisplayName(), p_192750_1_.getDisplayText()}));
            }
         }
      }

      if (advancementprogress.isDone()) {
         this.ensureVisibility(p_192750_1_);
      }

      return flag;
   }

   public boolean revokeCriterion(Advancement p_192744_1_, String p_192744_2_) {
      boolean flag = false;
      AdvancementProgress advancementprogress = this.getProgress(p_192744_1_);
      if (advancementprogress.revokeCriterion(p_192744_2_)) {
         this.registerListeners(p_192744_1_);
         this.progressChanged.add(p_192744_1_);
         flag = true;
      }

      if (!advancementprogress.hasProgress()) {
         this.ensureVisibility(p_192744_1_);
      }

      return flag;
   }

   private void registerListeners(Advancement p_193764_1_) {
      AdvancementProgress advancementprogress = this.getProgress(p_193764_1_);
      if (!advancementprogress.isDone()) {
         Iterator var3 = p_193764_1_.getCriteria().entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry<String, Criterion> entry = (Map.Entry)var3.next();
            CriterionProgress criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
            if (criterionprogress != null && !criterionprogress.isObtained()) {
               ICriterionInstance icriterioninstance = ((Criterion)entry.getValue()).getCriterionInstance();
               if (icriterioninstance != null) {
                  ICriterionTrigger<ICriterionInstance> icriteriontrigger = CriteriaTriggers.get(icriterioninstance.getId());
                  if (icriteriontrigger != null) {
                     icriteriontrigger.addListener(this, new ICriterionTrigger.Listener(icriterioninstance, p_193764_1_, (String)entry.getKey()));
                  }
               }
            }
         }
      }

   }

   private void unregisterListeners(Advancement p_193765_1_) {
      AdvancementProgress advancementprogress = this.getProgress(p_193765_1_);
      Iterator var3 = p_193765_1_.getCriteria().entrySet().iterator();

      while(true) {
         Map.Entry entry;
         CriterionProgress criterionprogress;
         do {
            do {
               if (!var3.hasNext()) {
                  return;
               }

               entry = (Map.Entry)var3.next();
               criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
            } while(criterionprogress == null);
         } while(!criterionprogress.isObtained() && !advancementprogress.isDone());

         ICriterionInstance icriterioninstance = ((Criterion)entry.getValue()).getCriterionInstance();
         if (icriterioninstance != null) {
            ICriterionTrigger<ICriterionInstance> icriteriontrigger = CriteriaTriggers.get(icriterioninstance.getId());
            if (icriteriontrigger != null) {
               icriteriontrigger.removeListener(this, new ICriterionTrigger.Listener(icriterioninstance, p_193765_1_, (String)entry.getKey()));
            }
         }
      }
   }

   public void flushDirty(EntityPlayerMP p_192741_1_) {
      if (!this.visibilityChanged.isEmpty() || !this.progressChanged.isEmpty()) {
         Map<ResourceLocation, AdvancementProgress> map = Maps.newHashMap();
         Set<Advancement> set = Sets.newLinkedHashSet();
         Set<ResourceLocation> set1 = Sets.newLinkedHashSet();
         Iterator var5 = this.progressChanged.iterator();

         Advancement advancement1;
         while(var5.hasNext()) {
            advancement1 = (Advancement)var5.next();
            if (this.visible.contains(advancement1)) {
               map.put(advancement1.getId(), this.progress.get(advancement1));
            }
         }

         var5 = this.visibilityChanged.iterator();

         while(var5.hasNext()) {
            advancement1 = (Advancement)var5.next();
            if (this.visible.contains(advancement1)) {
               set.add(advancement1);
            } else {
               set1.add(advancement1.getId());
            }
         }

         if (!map.isEmpty() || !set.isEmpty() || !set1.isEmpty()) {
            p_192741_1_.connection.sendPacket(new SPacketAdvancementInfo(this.isFirstPacket, set, set1, map));
            this.visibilityChanged.clear();
            this.progressChanged.clear();
         }
      }

      this.isFirstPacket = false;
   }

   public void setSelectedTab(@Nullable Advancement p_194220_1_) {
      Advancement advancement = this.lastSelectedTab;
      if (p_194220_1_ != null && p_194220_1_.getParent() == null && p_194220_1_.getDisplay() != null) {
         this.lastSelectedTab = p_194220_1_;
      } else {
         this.lastSelectedTab = null;
      }

      if (advancement != this.lastSelectedTab) {
         this.player.connection.sendPacket(new SPacketSelectAdvancementsTab(this.lastSelectedTab == null ? null : this.lastSelectedTab.getId()));
      }

   }

   public AdvancementProgress getProgress(Advancement advancementIn) {
      AdvancementProgress advancementprogress = (AdvancementProgress)this.progress.get(advancementIn);
      if (advancementprogress == null) {
         advancementprogress = new AdvancementProgress();
         this.startProgress(advancementIn, advancementprogress);
      }

      return advancementprogress;
   }

   private void startProgress(Advancement p_192743_1_, AdvancementProgress p_192743_2_) {
      p_192743_2_.update(p_192743_1_.getCriteria(), p_192743_1_.getRequirements());
      this.progress.put(p_192743_1_, p_192743_2_);
   }

   private void ensureVisibility(Advancement p_192742_1_) {
      boolean flag = this.shouldBeVisible(p_192742_1_);
      boolean flag1 = this.visible.contains(p_192742_1_);
      if (flag && !flag1) {
         this.visible.add(p_192742_1_);
         this.visibilityChanged.add(p_192742_1_);
         if (this.progress.containsKey(p_192742_1_)) {
            this.progressChanged.add(p_192742_1_);
         }
      } else if (!flag && flag1) {
         this.visible.remove(p_192742_1_);
         this.visibilityChanged.add(p_192742_1_);
      }

      if (flag != flag1 && p_192742_1_.getParent() != null) {
         this.ensureVisibility(p_192742_1_.getParent());
      }

      Iterator var4 = p_192742_1_.getChildren().iterator();

      while(var4.hasNext()) {
         Advancement advancement = (Advancement)var4.next();
         this.ensureVisibility(advancement);
      }

   }

   private boolean shouldBeVisible(Advancement p_192738_1_) {
      for(int i = 0; p_192738_1_ != null && i <= 2; ++i) {
         if (i == 0 && this.hasCompletedChildrenOrSelf(p_192738_1_)) {
            return true;
         }

         if (p_192738_1_.getDisplay() == null) {
            return false;
         }

         AdvancementProgress advancementprogress = this.getProgress(p_192738_1_);
         if (advancementprogress.isDone()) {
            return true;
         }

         if (p_192738_1_.getDisplay().isHidden()) {
            return false;
         }

         p_192738_1_ = p_192738_1_.getParent();
      }

      return false;
   }

   private boolean hasCompletedChildrenOrSelf(Advancement p_192746_1_) {
      AdvancementProgress advancementprogress = this.getProgress(p_192746_1_);
      if (advancementprogress.isDone()) {
         return true;
      } else {
         Iterator var3 = p_192746_1_.getChildren().iterator();

         Advancement advancement;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            advancement = (Advancement)var3.next();
         } while(!this.hasCompletedChildrenOrSelf(advancement));

         return true;
      }
   }
}
