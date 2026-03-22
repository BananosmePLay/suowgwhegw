package neo;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

public class 0cB {
   public static final String[] noDebugModules;
   public String suffix;
   public final 0bV moduleCategory;
   public final String moduleName;
   public static Minecraft mc = Minecraft.getMinecraft();
   public boolean moduleState;
   public int moduleKey;
   public boolean opened = 23510 ^ -15283 ^ 13181 ^ -21273;
   public List<0bC> settingList = new ArrayList();

   private static List CBPTtlVr4r(0cB var0) {
      return var0.settingList;
   }

   private static TextFormatting GkCvDDt43J() {
      return TextFormatting.RED;
   }

   private static String OSbXKRpD7n(0bC var0) {
      return var0.name;
   }

   public boolean isModuleState() {
      return ips6bGlogL(this);
   }

   private static 0dN b4sYyJwT8W(0bK var0) {
      return var0.configManager;
   }

   private static int GVLeaRFhLk(0cB var0) {
      return var0.moduleKey;
   }

   private static String J49WEVgJAi(0bC var0) {
      return var0.name;
   }

   private static String GXlSOZaiRO(0cB var0) {
      return var0.moduleName;
   }

   private static List _hbfbLojbV/* $FF was: 6hbfbLojbV*/(0cB var0) {
      return var0.settingList;
   }

   private static String[] JtSt6zaeFB() {
      return noDebugModules;
   }

   static {
      String[] var10000 = new String[23122 ^ -29036 ^ 11458 ^ -2046];
      var10000[14995 ^ -26221 ^ 11194 ^ -30534] = FJEiaeLWGd("\u0380ίΪΠΨ΄ζΪ");
      var10000[25529 ^ -17522 ^ 32682 ^ -22628] = FJEiaeLWGd("·ΪΰΠάαΧΑΓ\u0380");
      var10000[12410 ^ -2264 ^ 27569 ^ -21279] = FJEiaeLWGd("\u0381άη\u0381\u03a2αΪηάέΦΐΦηηΪέΤΰ");
      var10000[30803 ^ -19163 ^ 19928 ^ -32595] = FJEiaeLWGd("\u0381άηϣ·ΦΡζΤ");
      var10000[4488 ^ -28037 ^ 11728 ^ -20953] = FJEiaeLWGd("\u0381άηΐΦηηΪέΤΰ");
      var10000[23749 ^ -23006 ^ 28232 ^ -27478] = FJEiaeLWGd("\u0380\u03a2γηΠΫ\u03a2ϣΎ\u03a2έ\u03a2ΤΦα");
      noDebugModules = var10000;
   }

   private static String _HQiAdN9rw/* $FF was: 8HQiAdN9rw*/(0bC var0) {
      return var0.name;
   }

   private static void Ou96LBgG1w(0cB var0, String var1) {
      var0.suffix = var1;
   }

   private static String _lvvjj16Yo/* $FF was: 8lvvjj16Yo*/(0bC var0) {
      return var0.name;
   }

   public void setBind(int bind) {
      fOVydpUevr(this, bind);
   }

   public String getModuleName() {
      return r4OXo9IQa0(this);
   }

   private static void OMFg6SJwVk(0cB var0, boolean var1) {
      var0.moduleState = var1;
   }

   private static 0dN VeWd6WLtmg(0bK var0) {
      return var0.configManager;
   }

   private static 0bV _D8kpXwG1i/* $FF was: 4D8kpXwG1i*/(0cB var0) {
      return var0.moduleCategory;
   }

   private static void H97fPL9pdV(0cB var0, String var1) {
      var0.suffix = var1;
   }

   private static 0bV bq5xs6462Z(0cB var0) {
      return var0.moduleCategory;
   }

   private static TextFormatting hlOj4BB22S() {
      return TextFormatting.GREEN;
   }

   private static String TgVJL6HNgt(0cB var0) {
      return var0.moduleName;
   }

   private static void JlHS3TVS2J(0cB var0, boolean var1) {
      var0.moduleState = var1;
   }

   public String getSuffix() {
      return Vl3Jp6iIS9(this) == null ? zRYkM1u0bQ(this) : BVUOezwVac(this);
   }

   private static String QgX0wv4oNu(0bC var0) {
      return var0.name;
   }

   public void addSetting(0bC... setting) {
      6hbfbLojbV(this).addAll(Arrays.asList(setting));
   }

   private static boolean bI3eWODYe9(0bv var0) {
      return var0.value;
   }

   public void toggle() {
      OMFg6SJwVk(this, (boolean)(!qAR7qGHXW4(this) ? 11063 ^ -16744 ^ 8610 ^ -19444 : 27290 ^ -25249 ^ 3143 ^ -1150));
      if (gthHJNd3RG(this)) {
         this.onEnable();
      } else {
         this.onDisable();
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String FJEiaeLWGd(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14094 ^ -369 ^ 22200 ^ -24775; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24623 ^ -19492 ^ 13625 ^ -6903));
      }

      return var1.toString();
   }

   public void load(JsonObject object) {
      if (object != null) {
         if (object.has(FJEiaeLWGd("ΰη\u03a2ηΦ"))) {
            this.setState(object.get(FJEiaeLWGd("ΰη\u03a2ηΦ")).getAsBoolean());
         }

         if (object.has(FJEiaeLWGd("ΨΦκΊέΧΦλ"))) {
            this.setBind(object.get(FJEiaeLWGd("ΨΦκΊέΧΦλ")).getAsInt());
         }

         Iterator var2 = this.getSetting().iterator();

         while(var2.hasNext()) {
            0bC set = (0bC)var2.next();
            JsonObject propertiesObject = object.getAsJsonObject(FJEiaeLWGd("ΐΦηηΪέΤΰ"));
            if (set != null && propertiesObject != null && propertiesObject.has(8lvvjj16Yo(set))) {
               if (set instanceof 0bv) {
                  ((0bv)set).setBoolValue(propertiesObject.get(8HQiAdN9rw(set)).getAsBoolean());
               } else if (set instanceof 0by) {
                  ((0by)set).setListMode(propertiesObject.get(SQGqFoBiNl(set)).getAsString());
               } else if (set instanceof 0bz) {
                  ((0bz)set).setValueNumber(propertiesObject.get(Fx7dl8W7te(set)).getAsFloat());
               } else if (set instanceof 0bw) {
                  ((0bw)set).setColorValue(propertiesObject.get(gDHRyQoxG2(set)).getAsInt());
               } else if (set instanceof 0bA) {
                  ((0bA)set).setTextValue(propertiesObject.get(OSbXKRpD7n(set)).getAsString());
               }
            }
         }
      }

   }

   private static 0dN IgWbcbkF3b(0bK var0) {
      return var0.configManager;
   }

   private static 0cm _fwrOy6unb/* $FF was: 7fwrOy6unb*/() {
      return 0cm.SUCCESS;
   }

   private static String WiF4dbTnuL(0bC var0) {
      return var0.name;
   }

   public int getBind() {
      return GVLeaRFhLk(this);
   }

   private static String Vl3Jp6iIS9(0cB var0) {
      return var0.suffix;
   }

   private static String F7Y1b6tC2P(0cB var0) {
      return var0.moduleName;
   }

   private static String DysayXJdlJ(0cB var0) {
      return var0.moduleName;
   }

   private static 0cm q3NM5AVaNJ() {
      return 0cm.SUCCESS;
   }

   public List<0bC> getSetting() {
      return CBPTtlVr4r(this);
   }

   private static 0bV _wgRrWgapr/* $FF was: 6wgRrWgapr*/(0cB var0) {
      return var0.moduleCategory;
   }

   private static String Fx7dl8W7te(0bC var0) {
      return var0.name;
   }

   public void onDisable() {
      try {
         if (IgWbcbkF3b(0bK.getInstance()) != null) {
            b4sYyJwT8W(0bK.getInstance()).saveConfig(FJEiaeLWGd("ΧΦΥ\u03a2ζίη"));
         }
      } catch (Exception var2) {
         Exception exception = var2;
         exception.printStackTrace();
      }

      Stream var10000 = Arrays.stream(0i6AoJMtNr());
      String var10001 = DysayXJdlJ(this);
      var10001.getClass();
      if (var10000.noneMatch(var10001::equals) && !4D8kpXwG1i(this).equals(7e9ra6FSbg())) {
         0co.notify(FJEiaeLWGd("ΎάΧζίΦϣ·ΦΡζΤ"), GXlSOZaiRO(this) + FJEiaeLWGd("ϣδ\u03a2ΰϣ") + GkCvDDt43J() + FJEiaeLWGd("ΧΪΰ\u03a2ΡίΦΧϢ"), 7fwrOy6unb(), 11114 ^ -30175 ^ 23366 ^ -1527);
      }

      0m.unregister(this);
   }

   public JsonObject save() {
      JsonObject object = new JsonObject();
      object.addProperty(FJEiaeLWGd("ΰη\u03a2ηΦ"), this.isModuleState());
      object.addProperty(FJEiaeLWGd("ΨΦκΊέΧΦλ"), this.getBind());
      JsonObject propertiesObject = new JsonObject();

      for(Iterator var3 = this.getSetting().iterator(); var3.hasNext(); object.add(FJEiaeLWGd("ΐΦηηΪέΤΰ"), propertiesObject)) {
         0bC set = (0bC)var3.next();
         if (this.getSetting() != null) {
            if (set instanceof 0bv) {
               propertiesObject.addProperty(WiF4dbTnuL(set), bI3eWODYe9((0bv)set));
            } else if (set instanceof 0by) {
               propertiesObject.addProperty(6Y1nK2t7Iw(set), ((0by)set).get());
            } else if (set instanceof 0bz) {
               propertiesObject.addProperty(J49WEVgJAi(set), jTb7bmVgCS((0bz)set));
            } else if (set instanceof 0bw) {
               propertiesObject.addProperty(QgX0wv4oNu(set), sNQjFcGLFB((0bw)set));
            } else if (set instanceof 0bA) {
               propertiesObject.addProperty(RVD2FThjTV(set), ((0bA)set).get());
            }
         }
      }

      return object;
   }

   public void setState(boolean moduleState) {
      if (moduleState) {
         0m.register(this);
      } else {
         0m.unregister(this);
      }

      JlHS3TVS2J(this, moduleState);
   }

   private static String zRYkM1u0bQ(0cB var0) {
      return var0.moduleName;
   }

   public _cB/* $FF was: 0cB*/(String name, 0bV category) {
      this.moduleCategory = category;
      this.moduleName = name;
      this.moduleKey = 23606 ^ -18697 ^ 9134 ^ -13969;
   }

   private static void fOVydpUevr(0cB var0, int var1) {
      var0.moduleKey = var1;
   }

   private static String r4OXo9IQa0(0cB var0) {
      return var0.moduleName;
   }

   private static String _Y1nK2t7Iw/* $FF was: 6Y1nK2t7Iw*/(0bC var0) {
      return var0.name;
   }

   private static String[] _i6AoJMtNr/* $FF was: 0i6AoJMtNr*/() {
      return noDebugModules;
   }

   public void setSuffix(String suffix) {
      Ou96LBgG1w(this, suffix);
      H97fPL9pdV(this, TgVJL6HNgt(this) + FJEiaeLWGd("ϣ") + suffix);
   }

   private static boolean ips6bGlogL(0cB var0) {
      return var0.moduleState;
   }

   private static String _9HXzxNlvP/* $FF was: 49HXzxNlvP*/(0cB var0) {
      return var0.moduleName;
   }

   private static String SQGqFoBiNl(0bC var0) {
      return var0.name;
   }

   private static int sNQjFcGLFB(0bw var0) {
      return var0.color;
   }

   private static String BVUOezwVac(0cB var0) {
      return var0.suffix;
   }

   private static String RVD2FThjTV(0bC var0) {
      return var0.name;
   }

   private static String gDHRyQoxG2(0bC var0) {
      return var0.name;
   }

   public void onEnable() {
      try {
         if (75TyjgVEcB(0bK.getInstance()) != null) {
            VeWd6WLtmg(0bK.getInstance()).saveConfig(FJEiaeLWGd("ΧΦΥ\u03a2ζίη"));
         }
      } catch (Exception var2) {
         Exception exception = var2;
         exception.printStackTrace();
      }

      Stream var10000 = Arrays.stream(JtSt6zaeFB());
      String var10001 = F7Y1b6tC2P(this);
      var10001.getClass();
      if (var10000.noneMatch(var10001::equals) && !6wgRrWgapr(this).equals(1X2gObxigt())) {
         0co.notify(FJEiaeLWGd("ΎάΧζίΦϣ·ΦΡζΤ"), 49HXzxNlvP(this) + FJEiaeLWGd("ϣδ\u03a2ΰϣ") + hlOj4BB22S() + FJEiaeLWGd("Φέ\u03a2ΡίΦΧϢ"), q3NM5AVaNJ(), 14722 ^ -5171 ^ 26854 ^ -17747);
      }

      0m.register(this);
   }

   private static boolean gthHJNd3RG(0cB var0) {
      return var0.moduleState;
   }

   public 0bV getModuleCategory() {
      return bq5xs6462Z(this);
   }

   private static float jTb7bmVgCS(0bz var0) {
      return var0.value;
   }

   private static 0dN _5TyjgVEcB/* $FF was: 75TyjgVEcB*/(0bK var0) {
      return var0.configManager;
   }

   private static 0bV _X2gObxigt/* $FF was: 1X2gObxigt*/() {
      return 0bV.Themes;
   }

   private static 0bV _e9ra6FSbg/* $FF was: 7e9ra6FSbg*/() {
      return 0bV.Themes;
   }

   private static boolean qAR7qGHXW4(0cB var0) {
      return var0.moduleState;
   }
}
