package neo;

import java.util.ArrayList;
import java.util.List;

public class bov extends bou {
   private boG[] profiles = null;
   private bou[] options = null;
   private static final String NAME_PROFILE = "<profile>";
   private static final String VALUE_CUSTOM = "<custom>";

   public bov(boG[] profiles, bou[] options) {
      super("<profile>", "", detectProfileName(profiles, options), getProfileNames(profiles), detectProfileName(profiles, options, true), (String)null);
      this.profiles = profiles;
      this.options = options;
   }

   public void nextValue() {
      super.nextValue();
      if (this.getValue().equals("<custom>")) {
         super.nextValue();
      }

      this.applyProfileOptions();
   }

   public void updateProfile() {
      boG shaderprofile = this.getProfile(this.getValue());
      if (shaderprofile == null || !bpt.matchProfile(shaderprofile, this.options, false)) {
         String s = detectProfileName(this.profiles, this.options);
         this.setValue(s);
      }

   }

   private void applyProfileOptions() {
      boG shaderprofile = this.getProfile(this.getValue());
      if (shaderprofile != null) {
         String[] astring = shaderprofile.getOptions();

         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            bou shaderoption = this.getOption(s);
            if (shaderoption != null) {
               String s1 = shaderprofile.getValue(s);
               shaderoption.setValue(s1);
            }
         }
      }

   }

   private bou getOption(String name) {
      for(int i = 0; i < this.options.length; ++i) {
         bou shaderoption = this.options[i];
         if (shaderoption.getName().equals(name)) {
            return shaderoption;
         }
      }

      return null;
   }

   private boG getProfile(String name) {
      for(int i = 0; i < this.profiles.length; ++i) {
         boG shaderprofile = this.profiles[i];
         if (shaderprofile.getName().equals(name)) {
            return shaderprofile;
         }
      }

      return null;
   }

   public String getNameText() {
      return bmW.get("of.shaders.profile");
   }

   public String getValueText(String val) {
      return val.equals("<custom>") ? bmW.get("of.general.custom", "<custom>") : bpq.translate("profile." + val, val);
   }

   public String getValueColor(String val) {
      return val.equals("<custom>") ? "§c" : "§a";
   }

   public String getDescriptionText() {
      String s = bpq.translate("profile.comment", (String)null);
      if (s != null) {
         return s;
      } else {
         StringBuffer stringbuffer = new StringBuffer();

         for(int i = 0; i < this.profiles.length; ++i) {
            String s1 = this.profiles[i].getName();
            if (s1 != null) {
               String s2 = bpq.translate("profile." + s1 + ".comment", (String)null);
               if (s2 != null) {
                  stringbuffer.append(s2);
                  if (!s2.endsWith(". ")) {
                     stringbuffer.append(". ");
                  }
               }
            }
         }

         return stringbuffer.toString();
      }
   }

   private static String detectProfileName(boG[] profs, bou[] opts) {
      return detectProfileName(profs, opts, false);
   }

   private static String detectProfileName(boG[] profs, bou[] opts, boolean def) {
      boG shaderprofile = bpt.detectProfile(profs, opts, def);
      return shaderprofile == null ? "<custom>" : shaderprofile.getName();
   }

   private static String[] getProfileNames(boG[] profs) {
      List<String> list = new ArrayList();

      for(int i = 0; i < profs.length; ++i) {
         boG shaderprofile = profs[i];
         list.add(shaderprofile.getName());
      }

      list.add("<custom>");
      String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
      return astring;
   }
}
