package neo;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketTabComplete;
import org.apache.commons.lang3.StringUtils;

@0a(
   name = "plugins",
   description = "Получить список плагинов сервера."
)
public class 0i extends 0b {
   public static boolean inprogress;
   public static StringBuilder cores;
   public final char[] alphabet;
   public static StringBuilder plugins;

   private static StringBuilder m11DdGBGMy() {
      return cores;
   }

   private static void YVwljOlTpw(boolean var0) {
      inprogress = var0;
   }

   private static NetHandlerPlayClient Oh7qgVYPqa(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static NetHandlerPlayClient VNGJwXWXGP(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static StringBuilder djuxiqKvLy() {
      return cores;
   }

   private static void _CdWoVqViC/* $FF was: 4CdWoVqViC*/(StringBuilder var0) {
      plugins = var0;
   }

   private static EntityPlayerSP bEqEqVu4ff() {
      return Minecraft.player;
   }

   private static EntityPlayerSP sLNk3dM844() {
      return Minecraft.player;
   }

   private static String RggI4x5rv0(ServerData var0) {
      return var0.serverIP;
   }

   private static EntityPlayerSP IEIdewTFuA() {
      return Minecraft.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String DwZqdWokYE(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10098 ^ -20720 ^ 9830 ^ -20988; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24029 ^ -31960 ^ 30833 ^ -23934));
      }

      return var1.toString();
   }

   private static StringBuilder VpL5q0GLZg() {
      return plugins;
   }

   private static StringBuilder ACcAolb6jo() {
      return cores;
   }

   private static StringBuilder gSwnKm9aaW() {
      return plugins;
   }

   private static void bwSzB21uwK(boolean var0) {
      inprogress = var0;
   }

   private static boolean _GJdbSQHD4/* $FF was: 7GJdbSQHD4*/() {
      return inprogress;
   }

   private static void s1CGq2XbSa(StringBuilder var0) {
      cores = var0;
   }

   private static NetHandlerPlayClient _TVPbojR31/* $FF was: 6TVPbojR31*/(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static EntityPlayerSP V9ZTF9lt4n() {
      return Minecraft.player;
   }

   private static char[] _Wtd1oUuzl/* $FF was: 8Wtd1oUuzl*/(0i var0) {
      return var0.alphabet;
   }

   private static NetHandlerPlayClient xaYiPNQwu1(EntityPlayerSP var0) {
      return var0.connection;
   }

   public void execute(String[] args) throws Exception {
      if (7GJdbSQHD4()) {
         0dK.formatMsg(DwZqdWokYE("Рѥ%03Ц169EO3;Ц98>G<Ц9=65>;84Ч"));
      } else {
         (new Thread(() -> {
            try {
               bwSzB21uwK((boolean)(11153 ^ -32596 ^ 26587 ^ -13081));
               4CdWoVqViC(new StringBuilder());
               s1CGq2XbSa(new StringBuilder());
               lFqujXz1rd(IEIdewTFuA()).sendPacket(new CPacketTabComplete(DwZqdWokYE("Щѣѵ"), kGP94b27oA().getPosition(), (boolean)(273 ^ -28610 ^ 8894 ^ -19567)));
               0et.sleep(200L);
               6TVPbojR31(Pz6asSfTyJ()).sendPacket(new CPacketTabComplete(DwZqdWokYE("Щѣѵѵ"), TtWm3XGlL2().getPosition(), (boolean)(2881 ^ -7205 ^ 10380 ^ -16362)));
               0et.sleep(200L);
               Oh7qgVYPqa(NVbLvtq3Kg()).sendPacket(new CPacketTabComplete(DwZqdWokYE("ЩѰѯѧѰѣѴѵѯѩѨмѰ"), EI1gBc1eVX().getPosition(), (boolean)(15297 ^ -7494 ^ 26561 ^ -16710)));
               0et.sleep(200L);
               pPRYo1W1Gn(6rOESiDxsG()).sendPacket(new CPacketTabComplete(DwZqdWokYE("ЩѤѩ"), rW3IUJFBzt().getPosition(), (boolean)(9011 ^ -19059 ^ 11653 ^ -17605)));
               0et.sleep(200L);
               char[] var1 = 8Wtd1oUuzl(this);
               int var2 = var1.length;

               for(int var3 = 20269 ^ -23086 ^ 17791 ^ -20608; var3 < var2; ++var3) {
                  char s = var1[var3];
                  ArdWrpJpUT(V9ZTF9lt4n()).sendPacket(new CPacketTabComplete(DwZqdWokYE("Щ") + s, iqbnxbEOjn().getPosition(), (boolean)(15426 ^ -18686 ^ 11385 ^ -22727)));
                  if (s == (15329 ^ -7359 ^ 4620 ^ -13602) && RggI4x5rv0((ServerData)Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData())).toLowerCase().contains(DwZqdWokYE("ѴѣѧѪѪѿѱѩѴѪѢ"))) {
                     0et.sleep(200L);
                     VNGJwXWXGP(sLNk3dM844()).sendPacket(new CPacketTabComplete(DwZqdWokYE("ЩѴѱ"), bEqEqVu4ff().getPosition(), (boolean)(30644 ^ -17186 ^ 6608 ^ -11590)));
                     0et.sleep(200L);
                     xaYiPNQwu1(9jBXn4xxvJ()).sendPacket(new CPacketTabComplete(DwZqdWokYE("ЩѴѣѧѪѪѿ"), QFaH7k0J6G().getPosition(), (boolean)(2503 ^ -29164 ^ 2815 ^ -29396)));
                     0et.sleep(200L);
                     a6d7INE2e6(a4yRqrFZRw()).sendPacket(new CPacketTabComplete(DwZqdWokYE("ЩѭѩѨѢѴЫ"), ISyxKJKdVd().getPosition(), (boolean)(31286 ^ -13362 ^ 651 ^ -19597)));
                  }

                  0et.sleep(200L);
               }

               if (PmABQLo8ZN().toString().contains(DwZqdWokYE("Ъ"))) {
                  0dK.formatMsg(DwZqdWokYE("РѠіѪѳѡѯѨѵЦЮ") + StringUtils.countMatches(VpL5q0GLZg().toString(), DwZqdWokYE("Ъ")) + DwZqdWokYE("ЯмЦ") + EpUAnagTlF().substring(20639 ^ -20829 ^ 25939 ^ -25745, yBjDRBDWu1().toString().length() - (7885 ^ -3919 ^ 7734 ^ -4021)));
               } else {
                  0dK.formatMsg(DwZqdWokYE("Рѥ\u0019=65>;MЦ;3Ц;6?23;MШ"));
               }

               if (djuxiqKvLy().toString().contains(DwZqdWokYE("Ъ"))) {
                  0dK.formatMsg(DwZqdWokYE("РѠѕѣѴѰѣѴЦѥѩѴѣѵмЦ") + m11DdGBGMy().substring(31391 ^ -9054 ^ 29899 ^ -11530, ACcAolb6jo().toString().length() - (23813 ^ -25731 ^ 30645 ^ -20020)));
               } else {
                  0dK.formatMsg(DwZqdWokYE("Рѥ)2F6Ц;3Ц;6?23;MШ"));
               }

               NxWXEDcNIU((boolean)(249 ^ -17055 ^ 10349 ^ -27147));
            } catch (Exception var5) {
               YVwljOlTpw((boolean)(6642 ^ -17523 ^ 8182 ^ -17015));
            }

         })).start();
      }
   }

   private static EntityPlayerSP ISyxKJKdVd() {
      return Minecraft.player;
   }

   private static NetHandlerPlayClient ArdWrpJpUT(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static String _iDN6yr3di/* $FF was: 4iDN6yr3di*/() {
      return 0c.PREFIX;
   }

   private static EntityPlayerSP iqbnxbEOjn() {
      return Minecraft.player;
   }

   private static StringBuilder PmABQLo8ZN() {
      return plugins;
   }

   private static EntityPlayerSP kGP94b27oA() {
      return Minecraft.player;
   }

   private static EntityPlayerSP NVbLvtq3Kg() {
      return Minecraft.player;
   }

   public static void onTabComplete(String[] completions) {
      String[] var1 = completions;
      int var2 = completions.length;

      for(int var3 = 31821 ^ -12436 ^ 12709 ^ -32124; var3 < var2; ++var3) {
         String compleation = var1[var3];
         if (compleation.contains(DwZqdWokYE("м"))) {
            String alias = compleation.split(DwZqdWokYE("м"))[29161 ^ -9620 ^ 10154 ^ -29649].replace(DwZqdWokYE("Щ"), DwZqdWokYE("")).toLowerCase();
            String formate = StringUtils.capitalize(compleation.split(DwZqdWokYE("м"))[21857 ^ -5494 ^ 948 ^ -17313].replace(DwZqdWokYE("Щ"), DwZqdWokYE("")));
            if (!alias.contains(DwZqdWokYE("ѰѣѪѩѥѯѲѿ")) && !alias.contains(DwZqdWokYE("ѵѶѯѡѩѲ")) && !alias.contains(DwZqdWokYE("ѤѳѨѡѣѣѥѩѴѢ")) && !alias.contains(DwZqdWokYE("ѶѧѶѣѴ")) && !alias.contains(DwZqdWokYE("ѫѧѡѫѧ")) && !alias.contains(DwZqdWokYE("ѠѩѴѡѣ")) && !alias.contains(DwZqdWokYE("ѶѳѶѳѴ")) && !alias.contains(DwZqdWokYE("ѤѳѭѭѯѲ"))) {
               if (!gSwnKm9aaW().toString().contains(DwZqdWokYE("Ц") + formate + DwZqdWokYE("Ъ"))) {
                  faQxrqLLNX().append(DwZqdWokYE("Ц")).append(formate).append(DwZqdWokYE("Ъ"));
               }
            } else if (!0vdqFldawL().toString().contains(DwZqdWokYE("Ц") + formate + DwZqdWokYE("Ъ"))) {
               pbg4roLt45().append(DwZqdWokYE("Ц")).append(formate).append(DwZqdWokYE("Ъ"));
            }
         }
      }

   }

   public _i/* $FF was: 0i*/() {
      char[] var10001 = new char[4120 ^ -26816 ^ 15081 ^ -16981];
      var10001[23445 ^ -8754 ^ 10152 ^ -24077] = (char)(5974 ^ -6015 ^ 5790 ^ -5848);
      var10001[693 ^ -26792 ^ 12988 ^ -22704] = (char)(2035 ^ -24749 ^ 24309 ^ -14793);
      var10001[23105 ^ -18928 ^ 29202 ^ -25023] = (char)(13891 ^ -12237 ^ 2338 ^ -4303);
      var10001[7158 ^ -29472 ^ 25785 ^ -3156] = (char)(22103 ^ -17924 ^ 17134 ^ -21215);
      var10001[29161 ^ -27070 ^ 4325 ^ -2230] = (char)(3607 ^ -21045 ^ 11159 ^ -30674);
      var10001[29063 ^ -680 ^ 18307 ^ -13479] = (char)(1273 ^ -30552 ^ 21668 ^ -10093);
      var10001[27454 ^ -20187 ^ 31175 ^ -23590] = (char)(23155 ^ -3890 ^ 31433 ^ -12269);
      var10001[17009 ^ -435 ^ 23890 ^ -7831] = (char)(31436 ^ -3664 ^ 27643 ^ -7953);
      var10001[6340 ^ -12848 ^ 11107 ^ -385] = (char)(20194 ^ -15193 ^ 14051 ^ -17201);
      var10001[31732 ^ -25593 ^ 24920 ^ -31070] = (char)(18521 ^ -8182 ^ 23006 ^ -3609);
      var10001[32404 ^ -18146 ^ 19554 ^ -29726] = (char)(28694 ^ -7012 ^ 8595 ^ -19086);
      var10001[18629 ^ -23792 ^ 16271 ^ -11183] = (char)(4279 ^ -18773 ^ 12306 ^ -27038);
      var10001[8386 ^ -6653 ^ 23687 ^ -26038] = (char)(1143 ^ -55636 ^ '萘' ^ -22866);
      var10001[3217 ^ -19602 ^ 7185 ^ -23581] = (char)(29406 ^ -21417 ^ 14212 ^ -5789);
      var10001[27408 ^ -22498 ^ 30200 ^ -18696] = (char)(2295 ^ -9030 ^ 1960 ^ -11382);
      var10001[24058 ^ -10225 ^ 11825 ^ -21557] = (char)(5003 ^ -26666 ^ 4244 ^ -27463);
      var10001[14984 ^ -16552 ^ 30597 ^ -3515] = (char)(29902 ^ -17979 ^ 24650 ^ -21200);
      var10001[24299 ^ -25540 ^ 27088 ^ -21738] = (char)(15915 ^ -46073 ^ '빙' ^ -13305);
      var10001[9892 ^ -10167 ^ 26828 ^ -27085] = (char)(12516 ^ -28635 ^ 22791 ^ -1611);
      var10001[1470 ^ -17389 ^ 4102 ^ -22088] = (char)(11255 ^ -16460 ^ 4266 ^ -31587);
      var10001[19700 ^ -1607 ^ 22635 ^ -4814] = (char)(14761 ^ -15240 ^ 24641 ^ -25115);
      var10001[10317 ^ -28622 ^ 19254 ^ -3236] = (char)(7054 ^ -11565 ^ 25504 ^ -21877);
      var10001[17423 ^ -16407 ^ 14805 ^ -15835] = (char)(19086 ^ -25828 ^ 31088 ^ -22379);
      var10001[13498 ^ -6422 ^ 7818 ^ -13107] = (char)(31163 ^ -18461 ^ 12807 ^ -985);
      var10001[31912 ^ -8145 ^ 26324 ^ -1461] = (char)(16661 ^ -32573 ^ 20932 ^ -28565);
      var10001[26321 ^ -7252 ^ 28271 ^ -5365] = (char)(13266 ^ -4932 ^ 23347 ^ -31705);
      this.alphabet = var10001;
   }

   private static NetHandlerPlayClient pPRYo1W1Gn(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static EntityPlayerSP EI1gBc1eVX() {
      return Minecraft.player;
   }

   private static void NxWXEDcNIU(boolean var0) {
      inprogress = var0;
   }

   private static EntityPlayerSP rW3IUJFBzt() {
      return Minecraft.player;
   }

   private static StringBuilder yBjDRBDWu1() {
      return plugins;
   }

   private static StringBuilder pbg4roLt45() {
      return cores;
   }

   private static StringBuilder faQxrqLLNX() {
      return plugins;
   }

   private static NetHandlerPlayClient lFqujXz1rd(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static StringBuilder EpUAnagTlF() {
      return plugins;
   }

   private static EntityPlayerSP a4yRqrFZRw() {
      return Minecraft.player;
   }

   private static EntityPlayerSP _jBXn4xxvJ/* $FF was: 9jBXn4xxvJ*/() {
      return Minecraft.player;
   }

   private static EntityPlayerSP TtWm3XGlL2() {
      return Minecraft.player;
   }

   public void error() {
      0dK.formatMsg(4iDN6yr3di() + DwZqdWokYE("ѶѪѳѡѯѨѵЦЫЦ\u00198=EA>DJЦG9>G8<Ц9=65>;84ЦG3F43F6Ш"));
   }

   private static EntityPlayerSP Pz6asSfTyJ() {
      return Minecraft.player;
   }

   private static NetHandlerPlayClient a6d7INE2e6(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static EntityPlayerSP _rOESiDxsG/* $FF was: 6rOESiDxsG*/() {
      return Minecraft.player;
   }

   private static StringBuilder _vdqFldawL/* $FF was: 0vdqFldawL*/() {
      return cores;
   }

   private static EntityPlayerSP QFaH7k0J6G() {
      return Minecraft.player;
   }
}
