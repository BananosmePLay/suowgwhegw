package neo;

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
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cl {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(h.class, new g()).registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer()).setPrettyPrinting().create();
   private static final TypeToken<Map<ResourceLocation, h>> MAP_TOKEN = new TypeToken<Map<ResourceLocation, h>>() {
   };
   private final Xx server;
   private final File progressFile;
   private final Map<b, h> progress = Maps.newLinkedHashMap();
   private final Set<b> visible = Sets.newLinkedHashSet();
   private final Set<b> visibilityChanged = Sets.newLinkedHashSet();
   private final Set<b> progressChanged = Sets.newLinkedHashSet();
   private MG player;
   @Nullable
   private b lastSelectedTab;
   private boolean isFirstPacket = true;

   public cl(Xx server, File p_i47422_2_, MG player) {
      this.server = server;
      this.progressFile = p_i47422_2_;
      this.player = player;
      this.load();
   }

   public void setPlayer(MG player) {
      this.player = player;
   }

   public void dispose() {
      Iterator var1 = bY.getAll().iterator();

      while(var1.hasNext()) {
         ci<?> icriteriontrigger = (ci)var1.next();
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
         b advancement = (b)var1.next();
         this.registerListeners(advancement);
      }

   }

   private void ensureAllVisible() {
      List<b> list = Lists.newArrayList();
      Iterator var2 = this.progress.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<b, h> entry = (Map.Entry)var2.next();
         if (((h)entry.getValue()).isDone()) {
            list.add(entry.getKey());
            this.progressChanged.add(entry.getKey());
         }
      }

      var2 = list.iterator();

      while(var2.hasNext()) {
         b advancement = (b)var2.next();
         this.ensureVisibility(advancement);
      }

   }

   private void checkForAutomaticTriggers() {
      Iterator var1 = this.server.getAdvancementManager().getAdvancements().iterator();

      while(var1.hasNext()) {
         b advancement = (b)var1.next();
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
            Map<ResourceLocation, h> map = (Map)JsonUtils.gsonDeserialize(GSON, s, MAP_TOKEN.getType());
            if (map == null) {
               throw new JsonParseException("Found null for advancements");
            }

            Stream<Map.Entry<ResourceLocation, h>> stream = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));
            Iterator var4 = ((List)stream.collect(Collectors.toList())).iterator();

            while(var4.hasNext()) {
               Map.Entry<ResourceLocation, h> entry = (Map.Entry)var4.next();
               b advancement = this.server.getAdvancementManager().getAdvancement((ResourceLocation)entry.getKey());
               if (advancement == null) {
                  LOGGER.warn("Ignored advancement '" + entry.getKey() + "' in progress file " + this.progressFile + " - it doesn't exist anymore?");
               } else {
                  this.startProgress(advancement, (h)entry.getValue());
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
      Map<ResourceLocation, h> map = Maps.newHashMap();
      Iterator var2 = this.progress.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<b, h> entry = (Map.Entry)var2.next();
         h advancementprogress = (h)entry.getValue();
         if (advancementprogress.hasProgress()) {
            map.put(((b)entry.getKey()).getId(), advancementprogress);
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

   public boolean grantCriterion(b p_192750_1_, String p_192750_2_) {
      boolean flag = false;
      h advancementprogress = this.getProgress(p_192750_1_);
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

   public boolean revokeCriterion(b p_192744_1_, String p_192744_2_) {
      boolean flag = false;
      h advancementprogress = this.getProgress(p_192744_1_);
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

   private void registerListeners(b p_193764_1_) {
      h advancementprogress = this.getProgress(p_193764_1_);
      if (!advancementprogress.isDone()) {
         Iterator var3 = p_193764_1_.getCriteria().entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry<String, bZ> entry = (Map.Entry)var3.next();
            ca criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
            if (criterionprogress != null && !criterionprogress.isObtained()) {
               cg icriterioninstance = ((bZ)entry.getValue()).getCriterionInstance();
               if (icriterioninstance != null) {
                  ci<cg> icriteriontrigger = bY.get(icriterioninstance.getId());
                  if (icriteriontrigger != null) {
                     icriteriontrigger.addListener(this, new ch(icriterioninstance, p_193764_1_, (String)entry.getKey()));
                  }
               }
            }
         }
      }

   }

   private void unregisterListeners(b p_193765_1_) {
      h advancementprogress = this.getProgress(p_193765_1_);
      Iterator var3 = p_193765_1_.getCriteria().entrySet().iterator();

      while(true) {
         Map.Entry entry;
         ca criterionprogress;
         do {
            do {
               if (!var3.hasNext()) {
                  return;
               }

               entry = (Map.Entry)var3.next();
               criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
            } while(criterionprogress == null);
         } while(!criterionprogress.isObtained() && !advancementprogress.isDone());

         cg icriterioninstance = ((bZ)entry.getValue()).getCriterionInstance();
         if (icriterioninstance != null) {
            ci<cg> icriteriontrigger = bY.get(icriterioninstance.getId());
            if (icriteriontrigger != null) {
               icriteriontrigger.removeListener(this, new ch(icriterioninstance, p_193765_1_, (String)entry.getKey()));
            }
         }
      }
   }

   public void flushDirty(MG p_192741_1_) {
      if (!this.visibilityChanged.isEmpty() || !this.progressChanged.isEmpty()) {
         Map<ResourceLocation, h> map = Maps.newHashMap();
         Set<b> set = Sets.newLinkedHashSet();
         Set<ResourceLocation> set1 = Sets.newLinkedHashSet();
         Iterator var5 = this.progressChanged.iterator();

         b advancement1;
         while(var5.hasNext()) {
            advancement1 = (b)var5.next();
            if (this.visible.contains(advancement1)) {
               map.put(advancement1.getId(), this.progress.get(advancement1));
            }
         }

         var5 = this.visibilityChanged.iterator();

         while(var5.hasNext()) {
            advancement1 = (b)var5.next();
            if (this.visible.contains(advancement1)) {
               set.add(advancement1);
            } else {
               set1.add(advancement1.getId());
            }
         }

         if (!map.isEmpty() || !set.isEmpty() || !set1.isEmpty()) {
            p_192741_1_.connection.sendPacket(new Tw(this.isFirstPacket, set, set1, map));
            this.visibilityChanged.clear();
            this.progressChanged.clear();
         }
      }

      this.isFirstPacket = false;
   }

   public void setSelectedTab(@Nullable b p_194220_1_) {
      b advancement = this.lastSelectedTab;
      if (p_194220_1_ != null && p_194220_1_.getParent() == null && p_194220_1_.getDisplay() != null) {
         this.lastSelectedTab = p_194220_1_;
      } else {
         this.lastSelectedTab = null;
      }

      if (advancement != this.lastSelectedTab) {
         this.player.connection.sendPacket(new UF(this.lastSelectedTab == null ? null : this.lastSelectedTab.getId()));
      }

   }

   public h getProgress(b advancementIn) {
      h advancementprogress = (h)this.progress.get(advancementIn);
      if (advancementprogress == null) {
         advancementprogress = new h();
         this.startProgress(advancementIn, advancementprogress);
      }

      return advancementprogress;
   }

   private void startProgress(b p_192743_1_, h p_192743_2_) {
      p_192743_2_.update(p_192743_1_.getCriteria(), p_192743_1_.getRequirements());
      this.progress.put(p_192743_1_, p_192743_2_);
   }

   private void ensureVisibility(b p_192742_1_) {
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
         b advancement = (b)var4.next();
         this.ensureVisibility(advancement);
      }

   }

   private boolean shouldBeVisible(b p_192738_1_) {
      for(int i = 0; p_192738_1_ != null && i <= 2; ++i) {
         if (i == 0 && this.hasCompletedChildrenOrSelf(p_192738_1_)) {
            return true;
         }

         if (p_192738_1_.getDisplay() == null) {
            return false;
         }

         h advancementprogress = this.getProgress(p_192738_1_);
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

   private boolean hasCompletedChildrenOrSelf(b p_192746_1_) {
      h advancementprogress = this.getProgress(p_192746_1_);
      if (advancementprogress.isDone()) {
         return true;
      } else {
         Iterator var3 = p_192746_1_.getChildren().iterator();

         b advancement;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            advancement = (b)var3.next();
         } while(!this.hasCompletedChildrenOrSelf(advancement));

         return true;
      }
   }
}
