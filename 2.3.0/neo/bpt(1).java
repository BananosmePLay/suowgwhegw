package neo;

public class bpt {
   public bpt() {
   }

   public static bou getShaderOption(String name, bou[] opts) {
      if (opts == null) {
         return null;
      } else {
         for(int i = 0; i < opts.length; ++i) {
            bou shaderoption = opts[i];
            if (shaderoption.getName().equals(name)) {
               return shaderoption;
            }
         }

         return null;
      }
   }

   public static boG detectProfile(boG[] profs, bou[] opts, boolean def) {
      if (profs == null) {
         return null;
      } else {
         for(int i = 0; i < profs.length; ++i) {
            boG shaderprofile = profs[i];
            if (matchProfile(shaderprofile, opts, def)) {
               return shaderprofile;
            }
         }

         return null;
      }
   }

   public static boolean matchProfile(boG prof, bou[] opts, boolean def) {
      if (prof == null) {
         return false;
      } else if (opts == null) {
         return false;
      } else {
         String[] astring = prof.getOptions();

         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            bou shaderoption = getShaderOption(s, opts);
            if (shaderoption != null) {
               String s1 = def ? shaderoption.getValueDefault() : shaderoption.getValue();
               String s2 = prof.getValue(s);
               if (!XH.equals(s1, s2)) {
                  return false;
               }
            }
         }

         return true;
      }
   }
}
