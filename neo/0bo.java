package neo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class 0bo extends GuiScreen {
   public float offset;
   public ResourceLocation resourceLocation;
   public GuiTextField searchField;
   public String status;
   public 0bc loginThread;
   public 0bm remove;
   public 0bm login;
   public static final 0bh altService = new 0bh();
   public 0bb selectedAlt = null;

   private static Session KIeGWGVakC(Minecraft var0) {
      return var0.session;
   }

   private static int X9rqWiI6PP(Minecraft var0) {
      return var0.displayHeight;
   }

   private static 0bm V2U2VWWWNF(0bo var0) {
      return var0.login;
   }

   private static Minecraft _oGifSbZ24/* $FF was: 4oGifSbZ24*/(0bo var0) {
      return var0.mc;
   }

   private static 0bb Akde8ozAeb(0bo var0) {
      return var0.selectedAlt;
   }

   private static void yx05w45Pda(0bo var0, String var1) {
      var0.status = var1;
   }

   private static 0bb ODfgaqdg7H(0bo var0) {
      return var0.selectedAlt;
   }

   private void getDownloadImageSkin(ResourceLocation resourceLocationIn, String username) {
      TextureManager textureManager = LwgOY4j7O5(this).getTextureManager();
      textureManager.getTexture(resourceLocationIn);
      String var10003 = 4mewllagaR("ތސސޔޗߞߋߋމލފދސޅޖߊފށސߋޅޒޅސޅޖߋ߁ޗߋߒߐߊޔފރ");
      Object[] var10004 = new Object[2801 ^ -3798 ^ 3286 ^ -2292];
      var10004[11928 ^ -18999 ^ 9754 ^ -17077] = StringUtils.stripControlCodes(username);
      ThreadDownloadImageData textureObject = new ThreadDownloadImageData((File)null, String.format(var10003, var10004), DefaultPlayerSkin.getDefaultSkin(AbstractClientPlayer.getOfflineUUID(username)), new ImageBufferDownload());
      textureManager.loadTexture(resourceLocationIn, textureObject);
   }

   private static Minecraft NOHWloSs89(0bo var0) {
      return var0.mc;
   }

   private static List GgOSBLDMgC(0bo var0) {
      return var0.buttonList;
   }

   private static Minecraft _oVDMVrwhV/* $FF was: 9oVDMVrwhV*/(0bo var0) {
      return var0.mc;
   }

   private static GuiTextField IuO9PhTYWO(0bo var0) {
      return var0.searchField;
   }

   private static int _S1HbMIYYq/* $FF was: 4S1HbMIYYq*/(0bo var0) {
      return var0.height;
   }

   private boolean isMouseOverAlt(double x, double y, double y1) {
      return (boolean)(x >= (double)((float)vSw7yetBJS(this) / Float.intBitsToFloat(107549 ^ 117902 ^ 3722 ^ 206999652 ^ 116782 ^ 102778 ^ 3402 ^ 1280746083) - Float.intBitsToFloat(31488 ^ 227128 ^ 'ﺡ' ^ 879884011 ^ 8650 ^ 1043632 ^ 22484 ^ 1988599516)) && y >= y1 - Double.longBitsToDouble(5186825588556416548L ^ 570635970501658148L) && x <= (double)pnQwdMyKg0(this) / Double.longBitsToDouble(8534669280160799332L ^ 5298832947895097956L) && y <= y1 + Double.longBitsToDouble(-4284694758811590240L ^ -8881744078450024032L) && x >= Double.longBitsToDouble(-5942216687701453724L ^ -5942216687701453724L) && y >= Double.longBitsToDouble(4931410725659036329L ^ 301569571233811113L) && x <= (double)bzrcBzALeu(this) && y <= (double)(DoBbrLHG2R(this) - (21570 ^ -24488 ^ 16942 ^ -18938)) ? 31256 ^ -1803 ^ 7660 ^ -24832 : 16335 ^ -25732 ^ 21592 ^ -3861);
   }

   private static Minecraft _gNF479L40/* $FF was: 4gNF479L40*/(0bo var0) {
      return var0.mc;
   }

   private static int QYWFuSItv2(0bo var0) {
      return var0.width;
   }

   private static void PSgLB6jai7(0bo var0, 0bc var1) {
      var0.loginThread = var1;
   }

   private static GuiTextField bsj9n9Y7sX(0bo var0) {
      return var0.searchField;
   }

   private static float hwdiSWfFAT(0bo var0) {
      return var0.offset;
   }

   private static int d1wpvpR4vD(0bo var0) {
      return var0.width;
   }

   private static float eQvHB8qw4X(0bo var0) {
      return var0.offset;
   }

   public void initGui() {
      FobUyZFBTW(this, new GuiTextField(GYwbx6jz1e(this), qgZu6eEtn1(M4VmNJJlHQ(this)), dYT2LA0DEW(this) / (12724 ^ -29630 ^ 30497 ^ -13611) + (13126 ^ -24245 ^ 5230 ^ -31209), mQFhP4TFMT(this) - (7250 ^ -19425 ^ 7221 ^ -19346), 3037 ^ -30215 ^ 20139 ^ -13113, 6838 ^ -31081 ^ 17847 ^ -9850));
      List var10000 = bvoZwUinWc(this);
      0bm var10003 = new 0bm(27123 ^ -3225 ^ 24516 ^ -15023, EbAAKYBB3i(this) / (20124 ^ -27524 ^ 11602 ^ -2128) - (20561 ^ -14614 ^ 13930 ^ -24405), CgojWgBHiB(this) - (31666 ^ -11215 ^ 27728 ^ -15389), 29335 ^ -5422 ^ 29751 ^ -5042, 29832 ^ -23351 ^ 15997 ^ -4568, 4mewllagaR("ިދރލފ"));
      wOJ5Ixqve5(this, var10003);
      var10000.add(var10003);
      var10000 = lNAigeqfli(this);
      var10003 = new 0bm(12043 ^ -3978 ^ 19039 ^ -27360, 6CYwT4A7Br(this) / (21373 ^ -959 ^ 3696 ^ -24242) + (23620 ^ -10951 ^ 5142 ^ -25233) + (12269 ^ -12842 ^ 8217 ^ -15761), 4S1HbMIYYq(this) - (15929 ^ -8643 ^ 22337 ^ -18571), 26025 ^ -20901 ^ 7912 ^ -10958, 6250 ^ -16083 ^ 9877 ^ -58, 4mewllagaR("\u07b6ށމދޒށ"));
      g9FqHsD8Ew(this, var10003);
      var10000.add(var10003);
      GgOSBLDMgC(this).add(new 0bm(12461 ^ -9572 ^ 22905 ^ -19637, d1wpvpR4vD(this) / (25649 ^ -27720 ^ 17077 ^ -19138) + (11122 ^ -11170 ^ 1233 ^ -1031) - (32447 ^ -21726 ^ 16082 ^ -5261), dZqrvbribn(this) - (1540 ^ -3160 ^ 6081 ^ -7587), 14988 ^ -7107 ^ 11274 ^ -3334, 29535 ^ -19560 ^ 11184 ^ -5277, 4mewllagaR("ޥހހ߄ޥވސ")));
      GDzKKgBvag(this).add(new 0bm(17546 ^ -8107 ^ 28129 ^ -14021, 15jAqgvgdd(this) / (15095 ^ -2825 ^ 29441 ^ -17149) + (8251 ^ -28035 ^ 30755 ^ -13718), OnAi6gDWIC(this) - (22584 ^ -3196 ^ 16466 ^ -5154), 23866 ^ -11602 ^ 10258 ^ -22598, 6619 ^ -28166 ^ 16847 ^ -13830, 4mewllagaR("\u07b6ޅފހދމ")));
      9bXnkvXmu9(this).add(new 0bm(29442 ^ -21824 ^ 6221 ^ -15992, cOlJ1lgRj6(this) / (6653 ^ -24259 ^ 31754 ^ -15160) - (17985 ^ -29913 ^ 20026 ^ -31962), 6IUr0gINqk(this) - (17408 ^ -26463 ^ 24730 ^ -17373), 32644 ^ -10554 ^ 26458 ^ -12563, 26449 ^ -16821 ^ 9948 ^ -46, 4mewllagaR("ަޅއޏ")));
      81tynHnJts(this).add(new 0bm(116 ^ -16704 ^ 17526 ^ -1334, QYWFuSItv2(this) / (22557 ^ -4905 ^ 21226 ^ -6622) + (12611 ^ -11467 ^ 17812 ^ -22554) + (23128 ^ -27231 ^ 22002 ^ -25993), sAtibibeaa(this) - (16381 ^ -20471 ^ 17721 ^ -13612), 22715 ^ -28679 ^ 21224 ^ -31338, 487 ^ -20077 ^ 21409 ^ -7231, 4mewllagaR("ާވށޅޖ߄ޥވވ")));
      FPGyMaI9Ad(wTNdDY81dL(this), (boolean)(27505 ^ -25868 ^ 3752 ^ -211));
      uJAN2uIidb(jimOjGV8NA(this), (boolean)(21652 ^ -18316 ^ 28438 ^ -31754));
   }

   private static List bvoZwUinWc(0bo var0) {
      return var0.buttonList;
   }

   private static Minecraft ANktyah1o3(0bo var0) {
      return var0.mc;
   }

   private static int ripTvt22zO(Minecraft var0) {
      return var0.displayWidth;
   }

   private static Minecraft _Kz2CvyiBS/* $FF was: 4Kz2CvyiBS*/(0bo var0) {
      return var0.mc;
   }

   private static int CgojWgBHiB(0bo var0) {
      return var0.height;
   }

   private static void _9DHYLNlAG/* $FF was: 69DHYLNlAG*/(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static void lL9erLvVi8(0bo var0, 0bc var1) {
      var0.loginThread = var1;
   }

   private static int FiOqft8l7z(Minecraft var0) {
      return var0.displayHeight;
   }

   private static FontRenderer qgZu6eEtn1(Minecraft var0) {
      return var0.fontRenderer;
   }

   protected void keyTyped(char par1, int par2) {
      G4Tj6GK1WM(this).textboxKeyTyped(par1, par2);
      if ((par1 == (17241 ^ -30212 ^ 30583 ^ -16933) || par1 == (15846 ^ -25133 ^ 9218 ^ -31686)) && isA29Ydsg7(this).isFocused()) {
         7yGzOABtnW(this).setFocused((boolean)(!fyqLkxVo8l(this).isFocused() ? 2237 ^ -23263 ^ 8870 ^ -28869 : 5081 ^ -11759 ^ 25791 ^ -23177));
      }

      try {
         super.keyTyped(par1, par2);
      } catch (IOException var4) {
         IOException e = var4;
         e.printStackTrace();
      }

   }

   private static 0ba JrScdNn0dN() {
      return 0ba.Banned;
   }

   private static float _HFRwqhYtG/* $FF was: 2HFRwqhYtG*/(0bo var0) {
      return var0.offset;
   }

   private static 0bm Qf6UlqQuf1(0bo var0) {
      return var0.remove;
   }

   private static 0ba GdXXLqyVOb() {
      return 0ba.NotWorking;
   }

   private static int pnQwdMyKg0(0bo var0) {
      return var0.width;
   }

   private static Minecraft udIbSjQDoV(0bo var0) {
      return var0.mc;
   }

   private static void dG6W5NOzAS(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static void OFY1ZNyJdO(0bo var0, float var1) {
      var0.offset = var1;
   }

   private boolean isAltInArea(int y) {
      return (boolean)((float)y - jhDH94cYOO(this) <= (float)(jbkdop5H9S(this) - (5516 ^ -5024 ^ 9596 ^ -9054)) ? 17847 ^ -1185 ^ 22635 ^ -6526 : 16293 ^ -10591 ^ 30054 ^ -25502);
   }

   private static int EbAAKYBB3i(0bo var0) {
      return var0.width;
   }

   private static List GDzKKgBvag(0bo var0) {
      return var0.buttonList;
   }

   private static 0ba yqoexkG7zp() {
      return 0ba.NotWorking;
   }

   private static int OZ7IEEzeqP(0bo var0) {
      return var0.height;
   }

   private static void oCxGthqaYO(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static Session _4zEgfolyJ/* $FF was: 44zEgfolyJ*/(Minecraft var0) {
      return var0.session;
   }

   private static void wOJ5Ixqve5(0bo var0, 0bm var1) {
      var0.login = var1;
   }

   private static int vSw7yetBJS(0bo var0) {
      return var0.width;
   }

   private static FontRenderer eg49JVI6Lc(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static ResourceLocation jaBbNDAliF(0bo var0) {
      return var0.resourceLocation;
   }

   private static GuiTextField kQyjEbJerd(0bo var0) {
      return var0.searchField;
   }

   private static void NrmJyoGysr(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static float eOgbrLh2Zx(0bo var0) {
      return var0.offset;
   }

   private static int ABrmo3tfvY(Minecraft var0) {
      return var0.displayWidth;
   }

   private static int _mcbxqE1z5/* $FF was: 0mcbxqE1z5*/(0bo var0) {
      return var0.width;
   }

   private static 0bm NjMNBYwZMB(0bo var0) {
      return var0.remove;
   }

   private static void hWd7JYORju(0bo var0, 0bb var1) {
      var0.selectedAlt = var1;
   }

   private static void FlqmegAT2c(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static int ijJVNBJlt4(0bo var0) {
      return var0.width;
   }

   private static int _CYwT4A7Br/* $FF was: 6CYwT4A7Br*/(0bo var0) {
      return var0.width;
   }

   private static 0bm D4FTBkxynH(0bo var0) {
      return var0.login;
   }

   private static int aImnvvQob4(GuiButton var0) {
      return var0.id;
   }

   private static ResourceLocation ujGvOOproT(0bo var0) {
      return var0.resourceLocation;
   }

   private static TextFormatting Nx6kuGaaIL() {
      return TextFormatting.GREEN;
   }

   public void actionPerformed(GuiButton button) {
      switch (aImnvvQob4(button)) {
         case 0:
         default:
            break;
         case 1:
            0bc var10002 = new 0bc(ODfgaqdg7H(this));
            lL9erLvVi8(this, var10002);
            var10002.start();
            break;
         case 2:
            if (wvk7ShIlOA(this) != null) {
               PSgLB6jai7(this, (0bc)null);
            }

            0bd.removeAccount(Akde8ozAeb(this));
            yx05w45Pda(this, Nx6kuGaaIL() + 4mewllagaR("\u07b6ށމދޒށހߊ"));
            q64LWBCKWt(this, (0bb)null);
            break;
         case 3:
            4gNF479L40(this).displayGuiScreen(new 0bl(this));
            break;
         case 4:
            dSlxKKSB9p(this).displayGuiScreen(new 0bn(this));
            break;
         case 5:
            String randomName = 0ej.randomString(0ej.intRandom(13544 ^ -12390 ^ 20718 ^ -21607, 2734 ^ -1252 ^ 3275 ^ -653));
            0bd.addAccount(new 0bb(randomName, 4mewllagaR("")));
            TcV4CNY6b7(NOHWloSs89(this), new Session(randomName, 4mewllagaR(""), 4mewllagaR(""), 4mewllagaR("މދގޅފރ")));
            break;
         case 6:
            lhSKYvgLFv(this).displayGuiScreen(new 0bp(this));
            break;
         case 7:
            QW14ewgNyF(this).displayGuiScreen(new 0bE());
            break;
         case 8:
            0bd.clearAccounts();
      }

   }

   private static int bzrcBzALeu(0bo var0) {
      return var0.width;
   }

   private static void ZgF4sDNFej(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private static 0bb XFmrTGveZf(0bo var0) {
      return var0.selectedAlt;
   }

   private static float FDzuf67wHx(0bo var0) {
      return var0.offset;
   }

   private static void g9FqHsD8Ew(0bo var0, 0bm var1) {
      var0.remove = var1;
   }

   private static void q64LWBCKWt(0bo var0, 0bb var1) {
      var0.selectedAlt = var1;
   }

   private static 0bc wvk7ShIlOA(0bo var0) {
      return var0.loginThread;
   }

   private static Minecraft lhSKYvgLFv(0bo var0) {
      return var0.mc;
   }

   public void drawScreen(int par1, int par2, float par3) {
      0ew.drawRect(Float.intBitsToFloat(11263 ^ 122456 ^ 7880 ^ 1458499500 ^ 10620 ^ 115450 ^ 22741 ^ 1458485136), Float.intBitsToFloat(516 ^ 'ꓫ' ^ '싖' ^ -1409937078 ^ '\ue688' ^ '됹' ^ 13381 ^ -1409936505), (float)ABrmo3tfvY(77IW3JoJr7(this)), (float)FiOqft8l7z(ca37JSTPbo(this)), new Color(28173 ^ -23776 ^ 26288 ^ -21620, 6601 ^ -3415 ^ 28611 ^ -31566, 6255 ^ -14841 ^ 9589 ^ -1268));
      super.drawScreen(par1, par2, par3);
      if (Mouse.hasWheel()) {
         int wheel = Mouse.getDWheel();
         if (wheel < 0) {
            dG6W5NOzAS(this, (float)((double)56qFdLtFrv(this) + Double.longBitsToDouble(-541365218021013076L ^ -5168250885190776404L)));
            if ((double)NgeZ9eGkJ1(this) < Double.longBitsToDouble(-1638537544148026616L ^ -1638537544148026616L)) {
               OFY1ZNyJdO(this, Float.intBitsToFloat('흟' ^ '뺜' ^ 18713 ^ 1248942670 ^ '\udc6b' ^ '짷' ^ 17285 ^ 1248930957));
            }
         } else if (wheel > 0) {
            iyq8k13uPB(this, (float)((double)l2FNS4KFGk(this) - Double.longBitsToDouble(1701610375401251537L ^ 6316111143595746001L)));
            if ((double)FDzuf67wHx(this) < Double.longBitsToDouble(7983090438757048074L ^ 7983090438757048074L)) {
               69DHYLNlAG(this, Float.intBitsToFloat('\uf605' ^ 223670 ^ 3733 ^ -797313327 ^ '렼' ^ 27730 ^ 'ꮁ' ^ -797304808));
            }
         }
      }

      String altName = 4mewllagaR("ުޅމށߞ߄") + 44zEgfolyJ(ANktyah1o3(this)).getUsername();
      GlStateManager.pushMatrix();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
      oJd3gsxiJa(Minecraft.getMinecraft()).drawCenteredString(altName, (double)((float)93SPn7ZN32(this) / Float.intBitsToFloat(116994 ^ 107402 ^ 9423 ^ -419394870 ^ 4453 ^ 21076 ^ 13270 ^ -1493126038)), Double.longBitsToDouble(6712697687736221717L ^ 2093130369960935445L), -6956 ^ -23115 ^ 31721 ^ -14985);
      GlStateManager.pushMatrix();
      0ew.scissorRect(Float.intBitsToFloat('짦' ^ '꧰' ^ 3686 ^ 829098635 ^ '\uf136' ^ '胖' ^ 14055 ^ 829105148), Float.intBitsToFloat(32196 ^ 226735 ^ 249857 ^ 917337008 ^ 5423 ^ 223429 ^ 235411 ^ 1957249443), (float)ebTDGikPAg(this), (double)(OZ7IEEzeqP(this) - (17314 ^ -10843 ^ 5101 ^ -31272)));
      GL11.glEnable(27206 ^ -1101 ^ 15383 ^ -24077);
      int y = 22048 ^ -9419 ^ 23089 ^ -10494;
      int number = 19984 ^ -4262 ^ 22374 ^ -2516;
      Iterator<0bb> e = this.getAlts().iterator();

      while(e.hasNext()) {
         int var10000;
         if (ripTvt22zO(4Kz2CvyiBS(this)) < (4819 ^ -23251 ^ 6068 ^ -23602) && X9rqWiI6PP(9oVDMVrwhV(this)) < (10307 ^ -22515 ^ 22613 ^ -9313)) {
            var10000 = 12593 ^ -28517 ^ 32273 ^ -8262;
         } else {
            var10000 = 7809 ^ -27911 ^ 22084 ^ -9668;
         }

         0bb alt = (0bb)e.next();
         if (this.isAltInArea(y)) {
            ++number;
            String name = alt.getMask().equals(4mewllagaR("")) ? alt.getUsername() : alt.getMask();
            if (name.equalsIgnoreCase(KIeGWGVakC(4oGifSbZ24(this)).getUsername())) {
               name = 4mewllagaR("݃ޅ") + name;
            }

            String prefix = alt.getStatus().equals(JrScdNn0dN()) ? 4mewllagaR("݃އ") : (alt.getStatus().equals(GdXXLqyVOb()) ? 4mewllagaR("݃މ") : 4mewllagaR(""));
            name = prefix + name + 4mewllagaR("݃ޖ߄");
            String pass = alt.getPassword().equals(4mewllagaR("")) ? 4mewllagaR("݃އުދސ߄ިލއށފޗށ") : 4mewllagaR("݃ޅިލއށފޗށ");
            String numberP = 4mewllagaR("݃ߓ") + number + 4mewllagaR("ߊ߄݃ނ");
            GlStateManager.pushMatrix();
            GlStateManager.color(Float.intBitsToFloat(6686 ^ 257622 ^ 26895 ^ -111043808 ^ 25969 ^ 230840 ^ 15899 ^ -958277451), Float.intBitsToFloat(256685 ^ 251812 ^ 7102 ^ -72231669 ^ 239544 ^ 18542 ^ 241426 ^ -1003375752), Float.intBitsToFloat('躧' ^ '鲃' ^ 1533 ^ -1447867262 ^ 12225 ^ '諸' ^ '켉' ^ -1775025879), Float.intBitsToFloat(1016419 ^ '躑' ^ 1029157 ^ -1961399466 ^ 1038730 ^ 1045452 ^ 18128 ^ -1265123561));
            if (jaBbNDAliF(this) == null) {
               qwmQzGYmIJ(this, AbstractClientPlayer.getLocationSkin(name));
               this.getDownloadImageSkin(ujGvOOproT(this), name);
            } else {
               udIbSjQDoV(this).getTextureManager().bindTexture(ykddGoW4It(this));
               GlStateManager.enableTexture2D();
               Gui.drawScaledCustomSizeModalRect((int)((float)0mcbxqE1z5(this) / Float.intBitsToFloat(1021411 ^ 11883 ^ 1027698 ^ -990409894 ^ 20343 ^ 'ꋇ' ^ 15216 ^ -2064168864) - Float.intBitsToFloat(1042752 ^ 1002897 ^ 7763 ^ 1604810978 ^ 14929 ^ 499446 ^ 519159 ^ 494636336)), (int)((float)y - eOgbrLh2Zx(this)), Float.intBitsToFloat(114937 ^ 77122 ^ 20996 ^ 341863370 ^ 121062 ^ '꼙' ^ 119141 ^ 1432384239), Float.intBitsToFloat('\uf74c' ^ '봩' ^ 22259 ^ 1381721252 ^ '꯶' ^ '쾗' ^ 4428 ^ 324734239), 6855 ^ -12513 ^ 4687 ^ -14433, 24664 ^ -14143 ^ 25626 ^ -13173, 29251 ^ -72 ^ 26883 ^ -6943, 20839 ^ -742 ^ 7596 ^ -20024, Float.intBitsToFloat(18041 ^ '킊' ^ 26276 ^ 1479239091 ^ 15515 ^ '캇' ^ 23024 ^ 447430152), Float.intBitsToFloat(14019 ^ '쿀' ^ 11529 ^ 918246027 ^ 30954 ^ '끢' ^ 18788 ^ 1950025581));
            }

            GlStateManager.popMatrix();
            j9cwVHV5qi(Minecraft.getMinecraft()).drawString(name, (int)((float)GhhDUl0EYA(this) / Float.intBitsToFloat(22470 ^ 108036 ^ 26628 ^ 1501077058 ^ 9675 ^ 104395 ^ 25969 ^ 427348213) - Float.intBitsToFloat('舳' ^ '뀛' ^ 3435 ^ 1786778675 ^ '뒷' ^ 4170523 ^ '젮' ^ 673182962)), (int)((double)y - (double)eQvHB8qw4X(this) + Double.longBitsToDouble(-6899373998998226684L ^ -2282058481036625660L)), -5441 ^ -29857 ^ 14299 ^ -22076);
            eg49JVI6Lc(Minecraft.getMinecraft()).drawString((alt.getStatus().equals(yqoexkG7zp()) ? 4mewllagaR("݃މ") : 4mewllagaR("")) + pass, (int)((float)ijJVNBJlt4(this) / Float.intBitsToFloat(22985 ^ 112056 ^ 2086 ^ 895534066 ^ '꫱' ^ '读' ^ 6013 ^ 1969295826) - Float.intBitsToFloat('끓' ^ 'ꖶ' ^ 14519 ^ 205854144 ^ '閟' ^ 77302 ^ 14961 ^ 1323612810)), (int)((double)y - (double)2HFRwqhYtG(this) + Double.longBitsToDouble(-5862856447339336879L ^ -1257362878399583407L)), 0em.getColor(22112 ^ -11266 ^ 9235 ^ -24093));
            y += 40;
         }
      }

      GL11.glDisable(18097 ^ -26271 ^ 23782 ^ -28889);
      GL11.glPopMatrix();
      if (XFmrTGveZf(this) == null) {
         J9mNNuNGVi(Gec0GemiBT(this), (boolean)(28114 ^ -10022 ^ 15983 ^ -29849));
         ldPlnFm0EY(NjMNBYwZMB(this), (boolean)(7622 ^ -1234 ^ 25842 ^ -32230));
      } else {
         ZgF4sDNFej(D4FTBkxynH(this), (boolean)(1155 ^ -27366 ^ 29019 ^ -7997));
         P4irrjDzSA(Qf6UlqQuf1(this), (boolean)(31342 ^ -17254 ^ 5564 ^ -11447));
      }

      if (Keyboard.isKeyDown(20826 ^ -19094 ^ 23752 ^ -18384)) {
         oCxGthqaYO(this, (float)((double)PFyFUFWn47(this) - Double.longBitsToDouble(-6999537588611767451L ^ -2385036820417272987L)));
      } else if (Keyboard.isKeyDown(15758 ^ -18415 ^ 14562 ^ -16979)) {
         TWKrnTqQSN(this, (float)((double)hwdiSWfFAT(this) + Double.longBitsToDouble(-5026482280893658916L ^ -431121811115489060L)));
      }

      if ((double)AsQseIqEq4(this) < Double.longBitsToDouble(591801816613960606L ^ 591801816613960606L)) {
         NrmJyoGysr(this, Float.intBitsToFloat(506588 ^ '赭' ^ 505067 ^ 1685808782 ^ 513424 ^ 523689 ^ 25942 ^ 1685825723));
      }

   }

   private static int _5jAqgvgdd/* $FF was: 15jAqgvgdd*/(0bo var0) {
      return var0.width;
   }

   private static void TWKrnTqQSN(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static FontRenderer j9cwVHV5qi(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static GuiTextField gAsvy8je3u(0bo var0) {
      return var0.searchField;
   }

   public _bo/* $FF was: 0bo*/() {
      this.status = TextFormatting.DARK_GRAY + 4mewllagaR("ߌ") + TextFormatting.GRAY + 0bd.registry.size() + TextFormatting.DARK_GRAY + 4mewllagaR("ߍ");
   }

   private static float jhDH94cYOO(0bo var0) {
      return var0.offset;
   }

   private static void J9mNNuNGVi(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private static FontRenderer oJd3gsxiJa(Minecraft var0) {
      return var0.fontRenderer;
   }

   private static int _3SPn7ZN32/* $FF was: 93SPn7ZN32*/(0bo var0) {
      return var0.width;
   }

   private static float _6qFdLtFrv/* $FF was: 56qFdLtFrv*/(0bo var0) {
      return var0.offset;
   }

   private static 0bm jimOjGV8NA(0bo var0) {
      return var0.remove;
   }

   private static int dYT2LA0DEW(0bo var0) {
      return var0.width;
   }

   private static GuiTextField isA29Ydsg7(0bo var0) {
      return var0.searchField;
   }

   private static int jbkdop5H9S(0bo var0) {
      return var0.height;
   }

   private static float HP7UwlT4I1(0bo var0) {
      return var0.offset;
   }

   private static 0bm Gec0GemiBT(0bo var0) {
      return var0.login;
   }

   private static Minecraft dSlxKKSB9p(0bo var0) {
      return var0.mc;
   }

   private static void qwmQzGYmIJ(0bo var0, ResourceLocation var1) {
      var0.resourceLocation = var1;
   }

   private static List _1tynHnJts/* $FF was: 81tynHnJts*/(0bo var0) {
      return var0.buttonList;
   }

   private static List _bXnkvXmu9/* $FF was: 9bXnkvXmu9*/(0bo var0) {
      return var0.buttonList;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      kQyjEbJerd(this).mouseClicked(par1, par2, par3);
      if (HP7UwlT4I1(this) < Float.intBitsToFloat(2036 ^ '\ueb57' ^ '\ued67' ^ 158812375 ^ 10022 ^ 105806 ^ 737 ^ 158790042)) {
         FlqmegAT2c(this, Float.intBitsToFloat(11877 ^ '艢' ^ 25987 ^ -1488692470 ^ 17894 ^ '퀏' ^ 26547 ^ -1488690988));
      }

      double y = (double)(Float.intBitsToFloat(123197 ^ 24541 ^ 129253 ^ -688746997 ^ 127241 ^ 85578 ^ 2492 ^ -1796571407) - lvO7oOCyjZ(this));

      for(Iterator<0bb> e = this.getAlts().iterator(); e.hasNext(); y += Double.longBitsToDouble(1449254672438876266L ^ 6077829189468903530L)) {
         0bb alt = (0bb)e.next();
         if (this.isMouseOverAlt((double)par1, (double)par2, y)) {
            if (alt == tbu22bN7JF(this)) {
               this.actionPerformed(V2U2VWWWNF(this));
               return;
            }

            hWd7JYORju(this, alt);
         }
      }

      try {
         super.mouseClicked(par1, par2, par3);
      } catch (IOException var8) {
         IOException e = var8;
         e.printStackTrace();
      }

   }

   private static int DoBbrLHG2R(0bo var0) {
      return var0.height;
   }

   private static void FobUyZFBTW(0bo var0, GuiTextField var1) {
      var0.searchField = var1;
   }

   private static void iyq8k13uPB(0bo var0, float var1) {
      var0.offset = var1;
   }

   private static int GhhDUl0EYA(0bo var0) {
      return var0.width;
   }

   private static ArrayList G1Pfk1BF3c() {
      return 0bd.registry;
   }

   private static Minecraft _7IW3JoJr7/* $FF was: 77IW3JoJr7*/(0bo var0) {
      return var0.mc;
   }

   private static void uJAN2uIidb(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private static float AsQseIqEq4(0bo var0) {
      return var0.offset;
   }

   private static Minecraft M4VmNJJlHQ(0bo var0) {
      return var0.mc;
   }

   private static float PFyFUFWn47(0bo var0) {
      return var0.offset;
   }

   private static GuiTextField G4Tj6GK1WM(0bo var0) {
      return var0.searchField;
   }

   private static int dZqrvbribn(0bo var0) {
      return var0.height;
   }

   private static 0bm wTNdDY81dL(0bo var0) {
      return var0.login;
   }

   private static float lvO7oOCyjZ(0bo var0) {
      return var0.offset;
   }

   private static int mQFhP4TFMT(0bo var0) {
      return var0.height;
   }

   private static List lNAigeqfli(0bo var0) {
      return var0.buttonList;
   }

   private static GuiTextField _yGzOABtnW/* $FF was: 7yGzOABtnW*/(0bo var0) {
      return var0.searchField;
   }

   private static Minecraft LwgOY4j7O5(0bo var0) {
      return var0.mc;
   }

   private static void FPGyMaI9Ad(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private static void TcV4CNY6b7(Minecraft var0, Session var1) {
      var0.session = var1;
   }

   private static Minecraft QW14ewgNyF(0bo var0) {
      return var0.mc;
   }

   private static int ebTDGikPAg(0bo var0) {
      return var0.width;
   }

   private static 0bb tbu22bN7JF(0bo var0) {
      return var0.selectedAlt;
   }

   private static void ldPlnFm0EY(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private List<0bb> getAlts() {
      List<0bb> altList = new ArrayList();
      Iterator iterator = G1Pfk1BF3c().iterator();

      while(true) {
         0bb alt;
         do {
            if (!iterator.hasNext()) {
               return altList;
            }

            alt = (0bb)iterator.next();
         } while(!gAsvy8je3u(this).getText().isEmpty() && !alt.getMask().toLowerCase().contains(bsj9n9Y7sX(this).getText().toLowerCase()) && !alt.getUsername().toLowerCase().contains(IuO9PhTYWO(this).getText().toLowerCase()));

         altList.add(alt);
      }
   }

   private static float l2FNS4KFGk(0bo var0) {
      return var0.offset;
   }

   private static int sAtibibeaa(0bo var0) {
      return var0.height;
   }

   private static GuiTextField fyqLkxVo8l(0bo var0) {
      return var0.searchField;
   }

   private static int cOlJ1lgRj6(0bo var0) {
      return var0.width;
   }

   private static float NgeZ9eGkJ1(0bo var0) {
      return var0.offset;
   }

   private static void P4irrjDzSA(0bm var0, boolean var1) {
      var0.enabled = var1;
   }

   private static int GYwbx6jz1e(0bo var0) {
      return var0.eventButton;
   }

   private static Minecraft ca37JSTPbo(0bo var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _mewllagaR/* $FF was: 4mewllagaR*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25365 ^ -14290 ^ 18212 ^ -5089; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 26099 ^ -19534 ^ 17102 ^ -27797));
      }

      return var1.toString();
   }

   private static int OnAi6gDWIC(0bo var0) {
      return var0.height;
   }

   private static int _IUr0gINqk/* $FF was: 6IUr0gINqk*/(0bo var0) {
      return var0.height;
   }

   private static ResourceLocation ykddGoW4It(0bo var0) {
      return var0.resourceLocation;
   }
}
