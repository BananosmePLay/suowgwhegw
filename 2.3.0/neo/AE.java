package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.util.text.translation.LanguageMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AE implements AB {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Aj metadataSerializer;
   private String currentLanguage;
   protected static final AH CURRENT_LOCALE = new AH();
   private final Map<String, AD> languageMap = Maps.newHashMap();

   public AE(Aj theMetadataSerializerIn, String currentLanguageIn) {
      this.metadataSerializer = theMetadataSerializerIn;
      this.currentLanguage = currentLanguageIn;
      Ax.setLocale(CURRENT_LOCALE);
   }

   public void parseLanguageMetadata(List<AC> resourcesPacks) {
      this.languageMap.clear();
      Iterator var2 = resourcesPacks.iterator();

      while(var2.hasNext()) {
         AC iresourcepack = (AC)var2.next();

         try {
            Af languagemetadatasection = (Af)iresourcepack.getPackMetadata(this.metadataSerializer, "language");
            if (languagemetadatasection != null) {
               Iterator var5 = languagemetadatasection.getLanguages().iterator();

               while(var5.hasNext()) {
                  AD language = (AD)var5.next();
                  if (!this.languageMap.containsKey(language.getLanguageCode())) {
                     this.languageMap.put(language.getLanguageCode(), language);
                  }
               }
            }
         } catch (RuntimeException var7) {
            RuntimeException runtimeexception = var7;
            LOGGER.warn("Unable to parse language metadata section of resourcepack: {}", iresourcepack.getPackName(), runtimeexception);
         } catch (IOException var8) {
            IOException ioexception = var8;
            LOGGER.warn("Unable to parse language metadata section of resourcepack: {}", iresourcepack.getPackName(), ioexception);
         }
      }

   }

   public void onResourceManagerReload(AA resourceManager) {
      List<String> list = Lists.newArrayList(new String[]{"en_us"});
      if (!"en_us".equals(this.currentLanguage)) {
         list.add(this.currentLanguage);
      }

      CURRENT_LOCALE.loadLocaleDataFiles(resourceManager, list);
      LanguageMap.replaceWith(CURRENT_LOCALE.properties);
   }

   public boolean isCurrentLocaleUnicode() {
      return CURRENT_LOCALE.isUnicode();
   }

   public boolean isCurrentLanguageBidirectional() {
      return this.getCurrentLanguage() != null && this.getCurrentLanguage().isBidirectional();
   }

   public void setCurrentLanguage(AD currentLanguageIn) {
      this.currentLanguage = currentLanguageIn.getLanguageCode();
   }

   public AD getCurrentLanguage() {
      String s = this.languageMap.containsKey(this.currentLanguage) ? this.currentLanguage : "en_us";
      return (AD)this.languageMap.get(s);
   }

   public SortedSet<AD> getLanguages() {
      return Sets.newTreeSet(this.languageMap.values());
   }

   public AD getLanguage(String p_191960_1_) {
      return (AD)this.languageMap.get(p_191960_1_);
   }
}
