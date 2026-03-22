package neo;

import io.netty.channel.Channel;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Iterator;
import javax.script.ScriptEngine;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

@0a(
   name = "bots",
   description = "Боты."
)
public class 0d extends 0b {
   public static int delay = 9197 ^ -5501 ^ 1989 ^ -12593;
   public static boolean randommove;
   public static boolean attack;
   public static boolean mirror;
   public static boolean joinloop;
   // $FF: synthetic field
   public static final boolean $assertionsDisabled = !0d.class.desiredAssertionStatus() ? 30175 ^ -28775 ^ 20874 ^ -21555 : 4019 ^ -8776 ^ 28654 ^ -16923;
   public static String target = EBdzclwkMo("");
   public static int npcId = -9892 ^ -9739 ^ 5463 ^ -5631;

   private static double TfEknQFB5Y(Entity var0) {
      return var0.posY;
   }

   private static 0cD _NUtYJrMAg/* $FF was: 2NUtYJrMAg*/(0cC var0) {
      return var0.player;
   }

   private static void _d0DcVNW70/* $FF was: 9d0DcVNW70*/(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cH _1ob47ykA1/* $FF was: 81ob47ykA1*/(0cC var0) {
      return var0.mc;
   }

   private static String F1Qxr4GJi6() {
      return 0c.PREFIX;
   }

   private static String getUnicID() {
      return String.valueOf(Math.abs(String.valueOf(LocalTime.now().getHour()).hashCode() % (27896 ^ -1097 ^ 31369 ^ -4562)));
   }

   public void execute(String[] args) throws Exception {
      (new Thread(() -> {
         try {
            if (args.length == 0) {
               this.error();
            } else {
               int slot;
               if (!args[15653 ^ -9167 ^ 26637 ^ -30439].equalsIgnoreCase(EBdzclwkMo("ٸٴٵٵپٸٯ")) && !args[1213 ^ -32534 ^ 9751 ^ -24000].equalsIgnoreCase(EBdzclwkMo("ٱٴٲٵ"))) {
                  Iterator var29;
                  0cC botxx;
                  int index;
                  String file;
                  StringBuilder slotname;
                  if (args[29560 ^ -15536 ^ 6312 ^ -22400].equalsIgnoreCase(EBdzclwkMo("ٸٳٺٯ"))) {
                     if (args.length > (6740 ^ -812 ^ 5050 ^ -2757)) {
                        slotname = new StringBuilder();

                        for(index = 18995 ^ -26297 ^ 22874 ^ -30161; index < args.length; ++index) {
                           slotname.append(args[index]).append(index < args.length - (9011 ^ -32136 ^ 7216 ^ -17030) ? EBdzclwkMo("ػ") : EBdzclwkMo(""));
                        }

                        for(var29 = 0cC.getOnline().iterator(); var29.hasNext(); 0et.sleep((long)02br6j9M2t())) {
                           botxx = (0cC)var29.next();

                           try {
                              file = 0dy.format(slotname.toString());
                              botxx.sendMessage(file);
                           } catch (Exception var16) {
                           }
                        }
                     } else {
                        0dK.formatMsg(dM2FFLYn4y() + EBdzclwkMo("ٹٴٯ٨ػٸٳٺٯػاɚȥȥȪɒȮȦȣȮإػضػȅəȤɛȫȩȣəɗػɚȥȥȪɒȮȦȣȮػȥəػȣȧȮȦȣػȪȥəȫص"));
                     }
                  } else {
                     Iterator var23;
                     0cC bot;
                     if (args[21138 ^ -7162 ^ 7655 ^ -21645].equalsIgnoreCase(EBdzclwkMo("ٳٴٯٹٺ٩"))) {
                        if (args.length == (12205 ^ -26074 ^ 30744 ^ -12911)) {
                           for(var23 = 0cC.getOnline().iterator(); var23.hasNext(); 0et.sleep((long)e6SblnwlXo())) {
                              bot = (0cC)var23.next();

                              try {
                                 bot.changeSlot(Integer.parseInt(args[13510 ^ -29196 ^ 22282 ^ -4551]));
                                 bot.useItem();
                              } catch (Exception var15) {
                              }
                           }
                        } else {
                           0dK.formatMsg(r4reegynmg() + EBdzclwkMo("ٹٴٯ٨ػٳٴٯٹٺ٩ػاɚȠȥəإػضػȊȥəɐػȦȫȭȣȧȫɕəػȦȫػɚȠȥəػȩػɞȥəȪȫɛȮص"));
                        }
                     } else if (args[3203 ^ -1170 ^ 20540 ^ -22575].equalsIgnoreCase(EBdzclwkMo("ٲٵ٭ٸٷٲٸٰ"))) {
                        if (args.length == (31905 ^ -8272 ^ 29869 ^ -10306)) {
                           for(var23 = 0cC.getOnline().iterator(); var23.hasNext(); 0et.sleep((long)AeyFIRgFlJ())) {
                              bot = (0cC)var23.next();

                              try {
                                 bot.windowClick(Integer.parseInt(args[16360 ^ -16465 ^ 19346 ^ -13356]), 31916 ^ -30786 ^ 27826 ^ -26720, 7t7J2eiPOT());
                              } catch (Exception var14) {
                              }
                           }
                        } else {
                           0dK.formatMsg(fL7ondlt4t() + EBdzclwkMo("ٹٴٯ٨ػٲٵ٭ٸٷٲٸٰػاɚȠȥəإػضػȊȥəɐػȦȫȭȣȧȫɕəػȦȫػɚȠȥəػȩػȣȦȩȮȦəȫɛȮص"));
                        }
                     } else if (args[1436 ^ -26355 ^ 19105 ^ -10704].equalsIgnoreCase(EBdzclwkMo("٨٫ٲٵ"))) {
                        if (args.length == (24615 ^ -14181 ^ 8826 ^ -30011)) {
                           0dK.formatMsg(EBdzclwkMo("ȊȥəɐػȦȫɜȫȠȣػɞȥȯȣəɗػȩȥȡɛɘȨػȣȨɛȥȡȫػ") + args[15662 ^ -25711 ^ 26581 ^ -16021]);
                           var23 = 0cC.getOnline().iterator();

                           while(var23.hasNext()) {
                              bot = (0cC)var23.next();
                              bot.getBaritone().setBotFunction(new 0db(bot, args[882 ^ -25679 ^ 28496 ^ -2158], parseInt(args[6716 ^ -23076 ^ 14170 ^ -30536], 5090 ^ -9812 ^ 18539 ^ -32218)));
                              0et.sleep((long)27EdqliwYK());
                           }
                        } else {
                           0dK.formatMsg(mSXlGWgLAo() + EBdzclwkMo("ٹٴٯ٨ػ٨٫ٲٵػاٵٺٶپإػا٩ٺٿٲٮ٨إػضػȊȥəɐػȦȫɜȦɘəػɞȥȯȣəɗػȩȥȡɛɘȨػȣȨɛȥȡȫص"));
                        }
                     } else {
                        String src;
                        if (args[27545 ^ -22464 ^ 16289 ^ -904].equalsIgnoreCase(EBdzclwkMo("ٶٲ٩٩ٴ٩"))) {
                           ZY3BdwgiNb((boolean)(!w7wntfYsOI() ? 4057 ^ -9802 ^ 31183 ^ -20575 : 10130 ^ -29129 ^ 28897 ^ -9916));
                           src = YdN2e9D3CG() ? EBdzclwkMo("ȦȫɜȫȠȣ") : EBdzclwkMo("ȤȮɛȮɚəȫȠȣ");
                           0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػ") + src + EBdzclwkMo("ػȤȥȩəȥɛɔəɗػȩȫɓȣػȯȮȢɚəȩȣɔص"));
                        } else if (args[9640 ^ -30904 ^ 10050 ^ -31326].equalsIgnoreCase(EBdzclwkMo("ٶٺٵٮٺٷ"))) {
                           if (args.length == (3406 ^ -1275 ^ 7627 ^ -5246)) {
                              0bL.getInstance().getCaptchaManager().sendAnswer(args[17722 ^ -17040 ^ 23543 ^ -23620]);
                           } else {
                              0dK.formatMsg(SbL4PZ6OFG() + EBdzclwkMo("ٹٴٯ٨ػٶٺٵٮٺٷػاȥəȩȮəإػضػȻɘɜȦȥȮػɛȮɓȮȦȣȮػȡȫȤɜȣػȪȥəȫص"));
                           }
                        } else if (args[3391 ^ -19891 ^ 12883 ^ -29407].equalsIgnoreCase(EBdzclwkMo("ٿٲ٨ٸٴٵٵپٸٯ"))) {
                           0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػȥəȡȠɕɜȮȦɐػȥəػɚȮɛȩȮɛȫص"));
                           CwB7HrDXAd((boolean)(14057 ^ -19773 ^ 16252 ^ -17578));
                           var23 = 0cC.getBotList().iterator();

                           while(var23.hasNext()) {
                              bot = (0cC)var23.next();

                              try {
                                 bot.setDeleted((boolean)(22426 ^ -3378 ^ 20812 ^ -3047));
                                 bot.stopAndRemove();
                              } catch (Exception var13) {
                              }
                           }

                           0bL.getInstance().getCaptchaManager().clear();
                        } else if (args[15213 ^ -109 ^ 13944 ^ -3450].equalsIgnoreCase(EBdzclwkMo("ٸٷپٺ٩"))) {
                           0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػȥəȡȠɕɜȮȦɐػȣػȥɜȣɒȮȦɐص"));

                           for(var23 = 0cC.getBotList().iterator(); var23.hasNext(); 0bL.getInstance().getCaptchaManager().clear()) {
                              bot = (0cC)var23.next();

                              try {
                                 bot.setDeleted((boolean)(21040 ^ -23832 ^ 13845 ^ -14644));
                                 hRwGyGr9nT(bot.getNetworkManager()).close();
                              } catch (Exception var12) {
                              }
                           }

                           0cC.getBotList().clear();
                        } else if (args[24784 ^ -18583 ^ 22060 ^ -32363].equalsIgnoreCase(EBdzclwkMo("ٷٴٺٿ٫٩ٴ٣٢"))) {
                           if (args.length == (5506 ^ -2664 ^ 18873 ^ -22112)) {
                              0bL.getInstance().getProxyManager().loadProxy(args[16776 ^ -23990 ^ 22476 ^ -19441], args[26248 ^ -19147 ^ 17420 ^ -26701]);
                           } else {
                              0dK.formatMsg(SIW7IYoPi1() + EBdzclwkMo("ٹٴٯ٨ػٷٴٺٿ٫٩ٴ٣٢ػا٫٩ٴ٣ٲپ٨صٯ٣ٯشَىٗإػا٨ٴٸٰ٨دش٨ٴٸٰ٨خشٳٯٯ٫إػضػȌȫȨɛɘȬȡȫػȤɛȥȡɚȣػȣȬػɟȫȢȠȫػȣȠȣػɚɚɐȠȡȣص"));
                           }
                        } else if (args[5153 ^ -179 ^ 23818 ^ -18842].equalsIgnoreCase(EBdzclwkMo("٩ٺٵٿٴٶٶٴ٭پ"))) {
                           bVgjwFRdVL((boolean)(!w0f9HY1b7A() ? 3849 ^ -27453 ^ 8918 ^ -18147 : 15999 ^ -29129 ^ 23467 ^ -5149));
                           src = hUiQ6BHjLG() ? EBdzclwkMo("ȦȫɜȫȠȣ") : EBdzclwkMo("ȤȮɛȮɚəȫȠȣ");
                           if (!GQeoLINf6d()) {
                              var29 = 0cC.getOnline().iterator();

                              while(var29.hasNext()) {
                                 botxx = (0cC)var29.next();
                                 UtBbXN0B8e(WvtPpfzdtg(YXoE6iGjvW(botxx)), (boolean)(13638 ^ -13573 ^ 32202 ^ -32137));
                              }
                           }

                           0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػ") + src + EBdzclwkMo("ػɛȫȦȯȥȧȦȥػɞȥȯȣəɗص"));
                        } else {
                           int o;
                           if (args[13315 ^ -10671 ^ 29934 ^ -26948].equalsIgnoreCase(EBdzclwkMo("ٲٵ٭ٸٷپٺ٩"))) {
                              0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػȥɜȣɚəȣȠȣػȣȦȩȮȦəȫɛɗ"));
                              var23 = 0cC.getOnline().iterator();

                              while(var23.hasNext()) {
                                 bot = (0cC)var23.next();

                                 for(o = 25726 ^ -9614 ^ 31430 ^ -15158; o < (18705 ^ -32543 ^ 16703 ^ -30495); ++o) {
                                    bot.windowClick(o, 23142 ^ -4461 ^ 29539 ^ -14441, 7DaoU131BX());
                                 }

                                 0et.sleep((long)vD6n0dyv2Z());
                              }
                           } else if (args[13134 ^ -31738 ^ 19620 ^ -1044].equalsIgnoreCase(EBdzclwkMo("٨٫ٺٶٶپ٩"))) {
                              if (args.length == (21462 ^ -22594 ^ 20730 ^ -23408) && args[16624 ^ -27437 ^ 19795 ^ -26255].equalsIgnoreCase(EBdzclwkMo("ٴٽٽ"))) {
                                 0dl.setEnabled((boolean)(5170 ^ -29625 ^ 21182 ^ -13621));
                                 0dK.formatMsg(EBdzclwkMo("ȺȤȫȧȧȮɛػȥɚəȫȦȥȩȠȮȦغ"));
                              } else if (args.length >= (23821 ^ -16535 ^ 21908 ^ -18445)) {
                                 slotname = new StringBuilder();

                                 for(index = 15046 ^ -12390 ^ 17074 ^ -18452; index < args.length; ++index) {
                                    slotname.append(args[index]).append(index < args.length - (18882 ^ -2480 ^ 25034 ^ -8615) ? EBdzclwkMo("ػ") : EBdzclwkMo(""));
                                 }

                                 Y1wdi1u75p().println(EBdzclwkMo("٨٫ٺٶػٯپ٣ٯءػ") + slotname + EBdzclwkMo("ا"));
                                 0dl.setEnabled((boolean)(16296 ^ -17919 ^ 16031 ^ -17609));
                                 0dl.setDelay(parseInt(args[30764 ^ -23345 ^ 7764 ^ -15690], 28915 ^ -31818 ^ 23809 ^ -21076));
                                 0dl.setText(slotname.toString());
                                 0dK.formatMsg(EBdzclwkMo("ȺȤȫȧȧȮɛػȬȫȤɘɒȮȦغػȌȫȯȮɛȭȡȫءػ") + 0dl.getDelay() + EBdzclwkMo("ػȹȮȡɚəءػ") + 0dl.getText());
                              } else {
                                 0dK.formatMsg(1lQMN7vona() + EBdzclwkMo("ٹٴٯ٨ػ٨٫ٺٶٶپ٩ػاٿپٷٺ٢لٶ٨شٴٽٽإػاٯپ٣ٯإػضػȊȥəɐػȦȫɜȦɘəػɚȤȫȧȣəɗػɘȡȫȬȫȦɐȧػəȮȡɚəȥȧص"));
                              }
                           } else if (args[18734 ^ -4848 ^ 19449 ^ -4153].equalsIgnoreCase(EBdzclwkMo("ٺٯٯٺٸٰ"))) {
                              if (args.length == (20958 ^ -19707 ^ 21416 ^ -20111)) {
                                 OBN6GpSdn0(args[25148 ^ -6755 ^ 141 ^ -30931]);
                                 7N7njzWIdO((boolean)(!rSvyidSunw() ? 17914 ^ -12343 ^ 4674 ^ -26512 : 26070 ^ -18264 ^ 28926 ^ -21120));
                                 src = OloGNDR5w6() ? EBdzclwkMo("ȦȫɜȫȠȣ") : EBdzclwkMo("ȤȮɛȮɚəȫȠȣ");
                                 0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػ") + src + EBdzclwkMo("ػȫəȫȡȥȩȫəɗػȣȨɛȥȡȫص"));
                                 if (!eFDOSpI8k7()) {
                                    var29 = 0cC.getOnline().iterator();

                                    while(var29.hasNext()) {
                                       botxx = (0cC)var29.next();
                                       9d0DcVNW70(f23LBw217b(81ob47ykA1(botxx)), (boolean)(20737 ^ -12040 ^ 11005 ^ -21756));
                                    }
                                 }
                              } else if (PvHainvBLd()) {
                                 4tSEqw3J9K((boolean)(19790 ^ -9798 ^ 23540 ^ -12544));
                                 0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػȤȮɛȮɚəȫȠȣػȫəȫȡȥȩȫəɗػȣȨɛȥȡȫص"));
                                 var23 = 0cC.getOnline().iterator();

                                 while(var23.hasNext()) {
                                    bot = (0cC)var23.next();
                                    RYsuw3nGzQ(tG8Ym8yBsO(vcCJdXeYgw(bot)), (boolean)(22753 ^ -4459 ^ 27273 ^ -8963));
                                 }
                              } else {
                                 0dK.formatMsg(1lyylWQMkn() + EBdzclwkMo("ٹٴٯ٨ػٺٯٯٺٸٰػاٵٺٶپإػضػȊȥəɐػȦȫɜȦɘəػȫəȫȡȥȩȫəɗػȣȨɛȥȡȫص"));
                              }
                           } else if (args[9739 ^ -3826 ^ 13941 ^ -7824].equalsIgnoreCase(EBdzclwkMo("٨پٯٿپٷٺ٢"))) {
                              if (args.length == (4052 ^ -11537 ^ 12132 ^ -3491)) {
                                 try {
                                    Y6H2MafwJy(Integer.parseInt(args[29938 ^ -20721 ^ 7694 ^ -14862]));
                                    0dK.formatMsg(EBdzclwkMo("ȸɚəȫȦȥȩȠȮȦȫػȬȫȯȮɛȭȡȫػȧȮȭȯɘػȪȥəȫȧȣػȦȫػؽٿؽٷ") + 7kgSUOxdHb() + EBdzclwkMo("ػؽٽؽٷȧɚص"));
                                 } catch (Exception var11) {
                                    0dK.formatMsg(r66JXGjdxP() + EBdzclwkMo("ٹٴٯ٨ػ٨پٯٿپٷٺ٢ػاٶٲٷٲ٨پٸٴٵٿ٨إػضػȌȫȯȮɛȭȡȫػȧȮȭȯɘػȪȥəȫȧȣػȩػٲٵ٭ٸٷٲٸٰشٳٴٯٹٺ٩شٸٳٺٯص"));
                                 }
                              } else {
                                 0dK.formatMsg(FiL6y2NPuS() + EBdzclwkMo("ٹٴٯ٨ػ٨پٯٿپٷٺ٢ػاٶٲٷٲ٨پٸٴٵٿ٨إػضػȌȫȯȮɛȭȡȫػȧȮȭȯɘػȪȥəȫȧȣػȩػٲٵ٭ٸٷٲٸٰشٳٴٯٹٺ٩شٸٳٺٯص"));
                              }
                           } else if (args[23927 ^ -28898 ^ 23557 ^ -29076].equalsIgnoreCase(EBdzclwkMo("٨ٷٴٯٵٺٶپ"))) {
                              if (args.length > (17503 ^ -5536 ^ 5349 ^ -17701)) {
                                 slotname = new StringBuilder();

                                 for(index = 14912 ^ -70 ^ 11018 ^ -4367; index < args.length; ++index) {
                                    slotname.append(args[index]).append(EBdzclwkMo("ػ"));
                                 }

                                 for(var29 = 0cC.getOnline().iterator(); var29.hasNext(); 0et.sleep((long)1ZkcTwZgIu())) {
                                    botxx = (0cC)var29.next();

                                    try {
                                       slot = 17474 ^ -29838 ^ 12119 ^ -8089;

                                       for(Iterator var41 = BnQf9Xb24w(rWk64NDjjs(botxx)).getInventory().iterator(); var41.hasNext(); ++slot) {
                                          ItemStack stack = (ItemStack)var41.next();
                                          if (0cC.stripColor(stack.getDisplayName()).contains(slotname.substring(2400 ^ -16949 ^ 5185 ^ -24342, slotname.length() - (24907 ^ -31905 ^ 29097 ^ -27716)))) {
                                             botxx.windowClick(slot, 13991 ^ -10084 ^ 31601 ^ -27318, 97eyq9Yrir());
                                          }
                                       }
                                    } catch (Exception var19) {
                                    }
                                 }
                              } else {
                                 0dK.formatMsg(own9V1onBJ() + EBdzclwkMo("ٹٴٯ٨ػ٨ٷٴٯٵٺٶپػاٵٺٶپإػضػȁȠȣȡػȤȥػɚȠȥəɘػȩػȣȦȩȮȦəȫɛȮػȣɚȤȥȠɗȬɘɔػȦȫȬȩȫȦȣȮػȤɛȮȯȧȮəȫص"));
                              }
                           } else if (args[12175 ^ -28884 ^ 20269 ^ -4210].equalsIgnoreCase(EBdzclwkMo("٨ٯٴ٫ٱٴٲٵ"))) {
                              0dK.formatMsg(EBdzclwkMo("ȌȫȤɘɚȡػȪȥəȥȩػȥɚəȫȦȥȩȠȮȦص"));
                              A74UzdD6Gc((boolean)(15571 ^ -3085 ^ 3245 ^ -15475));
                           } else if (args[1399 ^ -32701 ^ 10090 ^ -23970].equalsIgnoreCase(EBdzclwkMo("ٷٴٴٰ"))) {
                              var23 = 0cC.getOnline().iterator();

                              while(var23.hasNext()) {
                                 bot = (0cC)var23.next();
                                 W6jAyL1pGS(Adh8OIIkOo(bot), 0dm.normalizeYaw((float)Integer.parseInt(args[1157 ^ -14399 ^ 13211 ^ -3874])));
                                 A66AlIOBIi(oAEBQNNpL2(bot), 0dm.normalizePitch((float)Integer.parseInt(args[30248 ^ -27713 ^ 2811 ^ -4242])));
                              }
                           } else if (!args[16960 ^ -8459 ^ 29541 ^ -4144].equalsIgnoreCase(EBdzclwkMo("٨پٯ٨ٸ٩ٲ٫ٯ"))) {
                              if (args[12694 ^ -11351 ^ 9279 ^ -14848].equalsIgnoreCase(EBdzclwkMo("ٺٮٯٳ"))) {
                                 for(var23 = 0cC.getOnline().iterator(); var23.hasNext(); 0et.sleep((long)JPQUGipka8())) {
                                    bot = (0cC)var23.next();
                                    if (!bot.getBooleanParameter(EBdzclwkMo("ٺٮٯٳٴ٩ٲ١ٺٯٲٴٵ"))) {
                                       bot.auth();
                                    }
                                 }
                              } else if (args[14851 ^ -24420 ^ 31446 ^ -8119].equalsIgnoreCase(EBdzclwkMo("ټٸ"))) {
                                 System.gc();
                                 0dK.formatMsg(EBdzclwkMo("ȄȫȧɔəɗػȥɜȣɒȮȦȫغ"));
                              } else if (args[31878 ^ -4280 ^ 4059 ^ -25579].equalsIgnoreCase(EBdzclwkMo("٩پٱٴٲٵٺٷٷ"))) {
                                 var23 = 0cC.getOnline().iterator();

                                 while(var23.hasNext()) {
                                    bot = (0cC)var23.next();

                                    try {
                                       bot.disconnect();
                                       bot.reconnect((boolean)(12896 ^ -24728 ^ 1406 ^ -22409));
                                    } catch (Exception var10) {
                                       var10.printStackTrace();
                                    }
                                 }
                              } else {
                                 BlockPos playerPosition;
                                 Vector2f vector2f;
                                 if (args[3371 ^ -28429 ^ 5518 ^ -30634].equalsIgnoreCase(EBdzclwkMo("ٸٷٲٸٰٹٷٴٸٰ"))) {
                                    if (args.length >= (13956 ^ -14438 ^ 13793 ^ -15109)) {
                                       for(var23 = 0cC.getOnline().iterator(); var23.hasNext(); 0et.sleep((long)qPzmRgLqeb())) {
                                          bot = (0cC)var23.next();

                                          try {
                                             playerPosition = new BlockPos(Integer.parseInt(args[15682 ^ -1005 ^ 2097 ^ -13983]), Integer.parseInt(args[11736 ^ -12520 ^ 5761 ^ -3005]), Integer.parseInt(args[8576 ^ -3529 ^ 15638 ^ -4446]));
                                             vector2f = 0dm.getBlockAngles((double)playerPosition.getX(), (double)playerPosition.getY(), (double)playerPosition.getZ(), hrVQWymQUL(9nE44tnBYP(bot)) + Double.longBitsToDouble(-2488248642003051317L ^ -2118953472558670645L), iOJLbnhJd7(tGLJ9iaDJ5(bot)), 4SaWNTQDeJ(DegHS2JoG2(bot)));
                                             EsA9vFSeQb(jtNbIG1WzI(bot), 0dm.normalizeYaw(OwiH63qp7N(vector2f)));
                                             k4SkqICYTB(S6dqOtIcvH(bot), 0dm.normalizePitch(AmZzAb3L4V(vector2f)));
                                             bot.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(playerPosition.getX(), playerPosition.getY(), playerPosition.getZ()), IjVWaVi6jH(), 9BzgNmyQdL(), (float)playerPosition.getX(), (float)playerPosition.getY(), (float)playerPosition.getZ()));
                                          } catch (Exception var9) {
                                             var9.printStackTrace();
                                          }
                                       }
                                    } else {
                                       0dK.formatMsg(Sa1SnvKYbo() + EBdzclwkMo("ٹٴٯ٨ػٸٷٲٸٰٹٷٴٸٰػا٣إػا٢إػا١إػضػȆȫȭȫəɗػȦȫػȪȠȥȡص"));
                                    }
                                 } else if (args[19124 ^ -28319 ^ 27571 ^ -20378].equalsIgnoreCase(EBdzclwkMo("ٵ٫ٸ"))) {
                                    if (args.length >= (13715 ^ -1879 ^ 2743 ^ -14449)) {
                                       if (args[12585 ^ -23799 ^ 25445 ^ -3772].equalsIgnoreCase(EBdzclwkMo("٩پٸ"))) {
                                          if (iSejXRnN07() == (-20306 ^ -5950 ^ 15961 ^ -26165)) {
                                             0dK.formatMsg(EBdzclwkMo("ؽٸȌȫȤȣɚɗػًٕ٘ػɘȭȮػȣȯȮəغ"));
                                             return;
                                          }

                                          0dK.formatMsg(EBdzclwkMo("ȌȫȤȣɚɗػȦȫɜȫȠȫɚɗطػȦȫȭȧȣəȮػȤȡȧػȦȫػًٕ٘ص"));
                                          RibeA7r6Ns(-970 ^ -16942 ^ 6765 ^ -23433);
                                       } else if (args[5361 ^ -20660 ^ 8914 ^ -26258].equalsIgnoreCase(EBdzclwkMo("٫ٷٺ٢"))) {
                                          if (BJW9J6QQW0() < 0) {
                                             0dK.formatMsg(EBdzclwkMo("ȸػȩȫɚػȦȮəػȬȫȤȣɚȫȦȦȥȨȥػًٕ٘ص"));
                                             return;
                                          }

                                          var23 = 0cC.getOnline().iterator();

                                          while(var23.hasNext()) {
                                             bot = (0cC)var23.next();
                                             Entity npc = bot.getWorld().getEntityByID(0QSNYCHNEq());
                                             if (npc != null) {
                                                if (0dm.getDistance(bot, DbTLw6AoEB(npc), dZLtBSgyUu(npc), Jdbl54gWVi(npc)) < Float.intBitsToFloat(20933 ^ '뮧' ^ 28084 ^ -695394550 ^ 232483 ^ '龋' ^ 238301 ^ -1773327959)) {
                                                   vector2f = 0dm.getBlockAngles(8gXhLYpbID(npc), TfEknQFB5Y(npc), poMzqaAV9I(npc), 7ObC4HSfzQ(uWjrNHzBiL(bot)), bDV1DGLVIf(j5Ci6ra6PS(bot)) + Double.longBitsToDouble(3667070380876660286L ^ 937889006690139710L), qG6fapi9FC(eoxlqYWqSi(bot)));
                                                   DO7plVttRt(Nm9jGpVZDG(bot), 0dm.normalizeYaw(AaQ2NDJYXn(vector2f)));
                                                   qdrJ4JoIAa(BInwbv7ALP(bot), 0dm.normalizePitch(UjniUt40O4(vector2f)));
                                                   SugvY32qTi(SJkbhqzxwJ(bot)).sendPacket(new CPacketUseEntity(npc, 6wkL6bmTbz()));
                                                }
                                             } else {
                                                0dK.formatMsg(EBdzclwkMo("Ȋȥəػؽٿؽٷ") + bot.getNickname() + EBdzclwkMo("ؽحػȆȮػȦȫȢȯȮȦȥػɖȦəȣəȣػɚػəȫȡȣȧػْٟطػȥəȤɛȫȩȠɔɕػȥȪɐɜȦɐȢػȡȠȣȡػȪȮȬػɛȥəȫɝȣȣص"));
                                                qZkSaL12TT(2NUtYJrMAg(bot)).sendPacket(new CPacketUseEntity(rjoyry3rNH(), goA1RvsTab()));
                                             }
                                          }
                                       } else if (args[16428 ^ -3607 ^ 22166 ^ -6318].equalsIgnoreCase(EBdzclwkMo("٨ٯٴ٫"))) {
                                          4u5F6xquFB(-24517 ^ -5296 ^ 23584 ^ -5964);
                                          0dK.formatMsg(EBdzclwkMo("ȌȫȤȣɚɗػȡȠȣȡȫػȤȥػًٕ٘ػȥɚəȫȦȥȩȠȮȦȫص"));
                                       } else {
                                          0dK.formatMsg(A0Bg6Jgbxj() + EBdzclwkMo("ٹٴٯ٨ػٵ٫ٸػا٩پٸش٨ٯٴ٫ش٫ٷٺ٢إػضػȌȫȤȣɚɗػȣػȩȥɚȤɛȥȣȬȩȮȯȮȦȣȮػȦȫȭȫəȣɔػȤȥػًٕ٘ص"));
                                       }
                                    } else {
                                       0dK.formatMsg(ixBDCEMyCK() + EBdzclwkMo("ٹٴٯ٨ػٵ٫ٸػا٩پٸش٨ٯٴ٫ش٫ٷٺ٢إػضػȌȫȤȣɚɗػȣػȩȥɚȤɛȥȣȬȩȮȯȮȦȣȮػȦȫȭȫəȣɔػȤȥػًٕ٘ص"));
                                    }
                                 } else if (args[6191 ^ -32407 ^ 1068 ^ -25238].equalsIgnoreCase(EBdzclwkMo("ٿٮٶ٫٫٩ٴ٣٢"))) {
                                    try {
                                       File filex = new File(C31E2FoxnY(Minecraft.getMinecraft()), EBdzclwkMo("شٕپٴٌٺ٩پشٿٮٶ٫٫٩ٴ٣٢صٯ٣ٯ"));
                                       PrintWriter dumpProxy = new PrintWriter(filex);
                                       Iterator var33 = 0cC.getOnline().iterator();

                                       while(var33.hasNext()) {
                                          0cC botx = (0cC)var33.next();
                                          0eq proxyInfo = botx.getProxy();
                                          if (proxyInfo != null && !proxyInfo.getType().equals(AvZNTR3llV()) && !proxyInfo.getProxy().endsWith(EBdzclwkMo("ءخخثت"))) {
                                             dumpProxy.println(proxyInfo.getType().toString() + EBdzclwkMo("ءشش") + (proxyInfo.isAuthenticated() ? proxyInfo.getUsername() + EBdzclwkMo("ء") + proxyInfo.getPassword() : EBdzclwkMo("")) + proxyInfo.getProxy());
                                          }
                                       }

                                       dumpProxy.flush();
                                       dumpProxy.close();
                                       0dK.formatMsg(EBdzclwkMo("ȄɛȥȡɚȣػɚȥɞɛȫȦȮȦɐػȩػɟȫȢȠػ") + filex.getAbsolutePath());
                                    } catch (Exception var18) {
                                    }
                                 } else if (args[2116 ^ -24549 ^ 2577 ^ -23986].equalsIgnoreCase(EBdzclwkMo("ټٴٯٴ"))) {
                                    if (args.length != (28743 ^ -6986 ^ 19534 ^ -10051) && args.length != (19183 ^ -23234 ^ 10894 ^ -15013)) {
                                       0dK.formatMsg(C6OL6o1QsM() + EBdzclwkMo("ٹٴٯ٨ػټٴٯٴػا٣إػا٢إػا١إػضػȊȥəɐػȦȫɜȦɘəػȣȯəȣػȦȫػȡȥȥɛȯȣȦȫəɐص"));
                                    } else {
                                       Vector3i vector;
                                       if (args.length == (22402 ^ -19071 ^ 492 ^ -7187) && args[31616 ^ -10892 ^ 23037 ^ -2296].equalsIgnoreCase(EBdzclwkMo("ٛٶپ"))) {
                                          vector = new Vector3i(OGANx6saP0(BySOGX60r3()), 1dzdPqF1ka(66SFO6tDIb()), mvqcYV24Gw(9QmLDPaEho()));
                                       } else {
                                          if (args.length != (12889 ^ -32426 ^ 31772 ^ -12521)) {
                                             0dK.formatMsg(VC3f5yGsYa() + EBdzclwkMo("ٹٴٯ٨ػټٴٯٴػا٣إػا٢إػا١إػضػȊȥəɐػȦȫɜȦɘəػȣȯəȣػȦȫػȡȥȥɛȯȣȦȫəɐص"));
                                             return;
                                          }

                                          vector = new Vector3i(Integer.parseInt(args[22651 ^ -2610 ^ 4384 ^ -17260]), Integer.parseInt(args[1224 ^ -10086 ^ 24804 ^ -17228]), Integer.parseInt(args[16030 ^ -7611 ^ 5975 ^ -13425]));
                                       }

                                       var29 = 0cC.getOnline().iterator();

                                       while(var29.hasNext()) {
                                          botxx = (0cC)var29.next();
                                          botxx.getBaritone().setBotFunction(new 0da(botxx, vector));
                                          0et.sleep((long)dyMUe1qT06());
                                       }
                                    }
                                 } else if (args[13832 ^ -21179 ^ 28763 ^ -5354].equalsIgnoreCase(EBdzclwkMo("ٺٮٯٴٽٲ٨ٳ"))) {
                                    var23 = 0cC.getOnline().iterator();

                                    while(var23.hasNext()) {
                                       bot = (0cC)var23.next();
                                       bot.getBaritone().setBotFunction(new 0cX(bot));
                                       0et.sleep((long)HVnbCb1e9C());
                                    }
                                 } else if (args[11427 ^ -31214 ^ 31351 ^ -12090].equalsIgnoreCase(EBdzclwkMo("ٽٴٷٷٴ٬"))) {
                                    if (args.length == (1501 ^ -28797 ^ 19020 ^ -16368)) {
                                       var23 = 0cC.getOnline().iterator();

                                       while(var23.hasNext()) {
                                          bot = (0cC)var23.next();
                                          bot.getBaritone().setBotFunction(new 0cY(bot, args[4080 ^ -10347 ^ 22911 ^ -32485]));
                                          0et.sleep((long)biwQmHYa2R());
                                       }
                                    } else {
                                       0dK.formatMsg(kHKLOoi1Jk() + EBdzclwkMo("ٹٴٯ٨ػٽٴٷٷٴ٬ػاٵٺٶپإػضػȊȥəɐػȦȫɜȦɘəػȣȯəȣػȬȫػȣȨɛȥȡȥȧص"));
                                    }
                                 } else if (args[19955 ^ -20170 ^ 10616 ^ -10819].equalsIgnoreCase(EBdzclwkMo("ٹ٨ٯٴ٫"))) {
                                    0dK.formatMsg(EBdzclwkMo("ȉɚȮػȪȥəɐػȤȮɛȮɚəȫȠȣػȩɐȤȥȠȦɔəɗػȯȮȢɚəȩȣȮ"));
                                    var23 = 0cC.getOnline().iterator();

                                    while(var23.hasNext()) {
                                       bot = (0cC)var23.next();
                                       bot.getBaritone().setBotFunction((0cW)null);
                                       0et.sleep((long)iYnoL9lNun());
                                    }
                                 } else if (args[4004 ^ -14839 ^ 24702 ^ -22061].equalsIgnoreCase(EBdzclwkMo("٩پ٫پٺٯ"))) {
                                    var23 = 0cC.getOnline().iterator();

                                    while(var23.hasNext()) {
                                       bot = (0cC)var23.next();
                                       0cR.trigger(bot, EBdzclwkMo("٩پ٫پٺٯ٘ٴٶٶٺٵٿ"));
                                       0et.sleep((long)LVRd9pwBHC());
                                    }
                                 } else if (args[12529 ^ -20538 ^ 2579 ^ -27356].equalsIgnoreCase(EBdzclwkMo("ٽٲټٮ٩پ"))) {
                                    if (args.length == (10512 ^ -15567 ^ 4392 ^ -1269)) {
                                       if (args[16714 ^ -28659 ^ 1339 ^ -11139].equalsIgnoreCase(EBdzclwkMo("ٷٲ٨ٯ"))) {
                                          0dK.formatMsg(EBdzclwkMo("ȺȤȣɚȥȡػɟȣȨɘɛء"));
                                          String[] var42 = (new File(tSGygVGG26(Minecraft.getMinecraft()), EBdzclwkMo("شٕپٴٌٺ٩پشٽٲټٮ٩پ٨ش"))).list();
                                          index = var42.length;

                                          for(o = 29995 ^ -31723 ^ 28786 ^ -32436; o < index; ++o) {
                                             file = var42[o];
                                             0dK.formatMsg(file.replace(EBdzclwkMo("صٱ٨ٴٵ"), EBdzclwkMo("")));
                                          }

                                          return;
                                       }

                                       BlockPos[] figure = 0dk.getFigureByName(args[2021 ^ -7255 ^ 22868 ^ -17127]);
                                       if (figure == null) {
                                          0dK.formatMsg(EBdzclwkMo("ȆȮȣȬȩȮɚəȦȫɔػɟȣȨɘɛȫػ") + args[13173 ^ -12651 ^ 23464 ^ -22967]);
                                          return;
                                       }

                                       0dK.formatMsg(EBdzclwkMo("ȊȥəɐػȦȫɜȫȠȣػȤȥɚəɛȥȮȦȣȮػɟȣȨɘɛȥȢػ") + args[5346 ^ -18807 ^ 25657 ^ -14765]);
                                       index = 3455 ^ -23263 ^ 25390 ^ -13456;
                                       playerPosition = vRUiGxIzj8().getPosition();

                                       for(Iterator var39 = 0cC.getOnline().iterator(); var39.hasNext(); 0et.sleep((long)Gb10wVcvgW())) {
                                          0cC botxxx = (0cC)var39.next();
                                          BlockPos point = figure[index];
                                          BlockPos newPosition = new BlockPos((double)(playerPosition.getX() + point.getX()), ed7Ani4Jq2(IqvVvBWvb9(botxxx)), (double)(playerPosition.getZ() + point.getZ()));
                                          botxxx.getBaritone().setBotFunction(new 0cZ(botxxx, newPosition));
                                          ++index;
                                          if (index >= figure.length) {
                                             index = 747 ^ -8291 ^ 10388 ^ -2590;
                                          }
                                       }
                                    } else {
                                       0dK.formatMsg(DiOKAczIIm() + EBdzclwkMo("ٹٴٯ٨ػٽٲټٮ٩پػا٨٪ٮٺ٩پشٯ٩ٲٺٵټٷپشٷٺ٩ټپٯ٩ٲٺٵټٷپشٸٲ٩ٸٷپشٷٲٵپشضٷٲٵپش٩ٳٴٶٹإػضػȄȥɚəɛȥȮȦȣȮػɟȣȨɘɛɐػȥəȦȥɚȣəȮȠɗȦȥػȩȫɚص"));
                                    }
                                 } else if (args[31804 ^ -4436 ^ 12231 ^ -17065].equalsIgnoreCase(EBdzclwkMo("٫ٿٮٶ٫"))) {
                                    var23 = Thread.getAllStackTraces().keySet().iterator();

                                    while(var23.hasNext()) {
                                       Thread thread = (Thread)var23.next();
                                       0dK.formatMsg(thread.getName() + EBdzclwkMo("ػ") + thread.getClass());
                                    }
                                 } else {
                                    if (Cno1FnagkF(0bK.getInstance()).invokeCommand(args[1938 ^ -30212 ^ 14732 ^ -18462], args)) {
                                       return;
                                    }

                                    this.error();
                                 }
                              }
                           } else if (args.length >= (12977 ^ -5210 ^ 2107 ^ -11986)) {
                              if (args[20378 ^ -2872 ^ 24593 ^ -9406].equalsIgnoreCase(EBdzclwkMo("پٶ٫ٯ٢"))) {
                                 0dK.formatMsg(EBdzclwkMo("ȺȡɛȣȤəػɘȪɛȫȦغ"));
                                 FL5fduiGaa(QRbDlBKjXC(0bK.getInstance())).eval(EBdzclwkMo(""));
                                 f5aHO7b17I(0bK.getInstance()).setActive((boolean)(25643 ^ -1829 ^ 27541 ^ -2203));
                                 return;
                              }

                              if (!(new File(QHzuV9F3In(Minecraft.getMinecraft()), EBdzclwkMo("شٕپٴٌٺ٩پش٨ٸ٩ٲ٫ٯ٨ش") + args[26299 ^ -26653 ^ 13961 ^ -14384] + EBdzclwkMo("صٱ٨"))).exists()) {
                                 0dK.formatMsg(EBdzclwkMo("ȺȡɛȣȤəػ") + args[31631 ^ -3173 ^ 5069 ^ -25640] + EBdzclwkMo("ػȦȮػȦȫȢȯȮȦغ"));
                                 fdNycS0a47(0bK.getInstance()).setActive((boolean)(26913 ^ -25931 ^ 19859 ^ -16889));
                              } else {
                                 src = 0en.readUsingFiles(new File(zjqcQde98A(Minecraft.getMinecraft()), EBdzclwkMo("شٕپٴٌٺ٩پش٨ٸ٩ٲ٫ٯ٨ش") + args[27152 ^ -12646 ^ 22255 ^ -3484] + EBdzclwkMo("صٱ٨")));
                                 if (!wWQUOpbYhA() && src == null) {
                                    throw new AssertionError();
                                 }

                                 if (0dD.check(src).equals(aNYTyoeoPS()) && (args.length == (10940 ^ -3236 ^ 222 ^ -9924) || !args[31889 ^ -32079 ^ 17154 ^ -17120].equals(EBdzclwkMo("ضضٸٴٵٽٲ٩ٶ") + getUnicID()))) {
                                    0dK.formatMsg(EBdzclwkMo("ؽحؽٷȄȥȯȥȬɛȣəȮȠɗɐȦȢػɚȡɛȣȤəغػȄɛȥȩȮɛɗəȮػȡȥȯػȦȫػȦȫȠȣɜȣȮػȥȤȫɚȦɐɞػȧȮəȥȯȥȩػȣػəصȯصػȆȣȡȥؽحؽٷȨȯȫػؽحؽٷȦȮػؽحؽٷȬȫؽحؽٷȤɘɚؽحؽٷȡȫȢəȮػȦȮȣȬȩȮɚəؽحؽٷȦɐȮػɚȡɛȣȤəɐطػȦȮػȩȣȯɔؽحؽٷػȣɞػɛȮȫȠɗȦȥȨȥػȡؽحؽٷȥȯȫغػؽحؽٷȎɚȠȣػؽحؽٷȩɐػȯȮȢؽحؽٷɚəȩȣəȮȠɗȦȥػɞȥəȣəȮػȬؽحؽٷȫȨɛɘȬȣəɗػɚȡɛȣؽحؽٷȤəطػəȥؽحؽٷػȩȩȮȯȣؽحؽٷəȮءػ"));
                                    0dK.defaultMsg(EBdzclwkMo("ؽحؽٷصٹٴٯ٨ػ") + joinArrayElements(args) + EBdzclwkMo("ػضضٸٴٵٽٲ٩ٶ") + getUnicID());
                                    wu2ig7yQ1E(0bK.getInstance()).setActive((boolean)(27727 ^ -25451 ^ 21853 ^ -23161));
                                    return;
                                 }

                                 0dK.formatMsg(EBdzclwkMo("ȺȡɛȣȤəػ") + args[23841 ^ -21180 ^ 27119 ^ -26229] + EBdzclwkMo("ػȬȫȨɛɘȭȮȦغ"));
                                 GPWOlowhlD(0bK.getInstance()).setActive((boolean)(2734 ^ -18788 ^ 31063 ^ -15004));
                                 U8AuMhn6G2(0bK.getInstance()).loadScript(args[13467 ^ -9304 ^ 29821 ^ -25777]);
                                 0dK.formatMsg(EBdzclwkMo("ȃȦɟȥɛȧȫɝȣɔػɚȡɛȣȤəȮءػ"));
                                 0dK.formatMsg(EBdzclwkMo("ȆȫȬȩȫȦȣȮءػ") + OIW92AfEfA(0bK.getInstance()).invokeMethod0(EBdzclwkMo("ټپٯٕٺٶپ")));
                                 0dK.formatMsg(EBdzclwkMo("ȋȩəȥɛءػ") + IPcpFCo4Br(0bK.getInstance()).invokeMethod0(EBdzclwkMo("ټپٯٚٮٯٳٴ٩")));
                              }
                           } else {
                              0dK.formatMsg(BG89zEJmc8() + EBdzclwkMo("ٹٴٯ٨ػ٨پٯ٨ٸ٩ٲ٫ٯػاٽٲٷپٵٺٶپشپٶ٫ٯ٢إػضػȌȫȨɛɘȬȣəɗػȣػȬȫȤɘɚəȣəɗػɚȡɛȣȤəص"));
                           }
                        }
                     }
                  }
               } else if (args.length >= (28244 ^ -24921 ^ 5417 ^ -6695)) {
                  0dx proxyManager = 0bL.getInstance().getProxyManager();
                  0dw nickManager = 0bL.getInstance().getNickManager();
                  if (3BjBgBw6NM(3Jp4yoVBwC()) && proxyManager.getProxyList().size() == 0) {
                     0dK.formatMsg(EBdzclwkMo("ȄɛȥȡɚȣػȦȮػȬȫȨɛɘȭȮȦɐغ"));
                     return;
                  }

                  if (N9JkqylD62().is(EBdzclwkMo("ٝ٩ٴٶٝٲٷپ"))) {
                     nickManager.loadNicks();
                  }

                  if (!PwrvtD26FO(rz1420iZtk())) {
                     0dK.formatMsg(EBdzclwkMo("ؽحؽٷȉɐȪɛȫȦػɛȮȭȣȧػȬȫɞȥȯȫػȪȮȬػȤɛȥȡɚȣغ"));
                     0dK.formatMsg(EBdzclwkMo("ؽحؽٷȆȮػȩɐɚəȫȩȠɔȢəȮػɚȠȣɓȡȥȧػȧȫȠȮȦɗȡɘɕػȬȫȯȮɛȭȡɘػȦȫػȬȫɞȥȯɐ"));
                     0dK.formatMsg(EBdzclwkMo("ؽحؽٷȹȫȡضȭȮطػّٴٲٵػٝٲ٣پ٩ػȦȮػȪɘȯȮəػɛȫȪȥəȫəɗػȩػɖəȥȧػɛȮȭȣȧȮغ"));
                  }

                  String ip = null;
                  slot = 18600 ^ -9459 ^ 7745 ^ -29212;
                  if (args.length >= (28503 ^ -24405 ^ 31666 ^ -19382) && args[32294 ^ -12450 ^ 25287 ^ -11332].contains(EBdzclwkMo("ء"))) {
                     ip = args[10189 ^ -22698 ^ 27709 ^ -4955].split(EBdzclwkMo("ء"))[25181 ^ -19374 ^ 17026 ^ -27507];
                     slot = Integer.parseInt(args[11844 ^ -25459 ^ 23511 ^ -5859].split(EBdzclwkMo("ء"))[15040 ^ -31364 ^ 4615 ^ -21062]);
                  }

                  0dK.formatMsg(EBdzclwkMo("ȌȫȤɘɚȡػؽٿؽٷ") + args[31521 ^ -5129 ^ 22589 ^ -14102] + EBdzclwkMo("ػؽٽؽٷȪȥəȥȩص"));
                  0co.notify(EBdzclwkMo("ٙٴٯ٨ػٟپٹٮټ"), Fys7ywZnVA() + EBdzclwkMo("ȌȫȤɘɚȡػ") + args[19811 ^ -23188 ^ 5575 ^ -567] + EBdzclwkMo("ػȪȥəȥȩصصص"), Jee5nMO7Nr(), 17615 ^ -11656 ^ 15416 ^ -21877);
                  AST5nl4TtH((boolean)(9848 ^ -32697 ^ 32715 ^ -9739));

                  for(int bcount = 5503 ^ -8193 ^ 14365 ^ -3427; bcount < Integer.parseInt(args[1200 ^ -7523 ^ 6046 ^ -3662]); ++bcount) {
                     if (bzDSrLTfF9()) {
                        try {
                           0cC.runBot(nickManager.getBotName(), FcMLyK426Y(qRQUv8oJG8()) ? proxyManager.getProxy() : new 0eq(EBdzclwkMo("ثصثصثصثءةة"), E0H2lYlauA()), ip == null ? 0er.getIp() : ip, ip == null ? 0er.getPort() : slot);
                           0et.sleep(Math.abs(Long.parseLong(args[23583 ^ -20673 ^ 15855 ^ -12595])));
                        } catch (Exception var17) {
                           Exception exceptionx = var17;
                           exceptionx.printStackTrace();
                        }
                     }
                  }
               } else {
                  0dK.formatMsg(4a76zWMyGL() + EBdzclwkMo("ٹٴٯ٨ػٸٴٵٵپٸٯػاȡȥȠضȩȥإػاȬȫȯȮɛȭȡȫإػضػȌȫȤɘɚȡػȪȥəȥȩػȦȫػɚȮɛȩȮɛص"));
               }
            }
         } catch (Exception var20) {
            Exception exception = var20;
            exception.printStackTrace();
         }

      })).start();
   }

   private static String f4DAFBLS21() {
      return 0c.PREFIX;
   }

   private static int vD6n0dyv2Z() {
      return delay;
   }

   private static String O2E36J4H4I() {
      return 0c.PREFIX;
   }

   private static int iSejXRnN07() {
      return npcId;
   }

   private static 0cD tGLJ9iaDJ5(0cC var0) {
      return var0.player;
   }

   private static String LaL4mGBJpO() {
      return 0c.PREFIX;
   }

   private static double iOJLbnhJd7(0cD var0) {
      return var0.posY;
   }

   private static 0dB wu2ig7yQ1E(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cD oAEBQNNpL2(0cC var0) {
      return var0.player;
   }

   private static int BJW9J6QQW0() {
      return npcId;
   }

   private static String A4JG0oXkr9() {
      return 0c.PREFIX;
   }

   private static 0dB Cno1FnagkF(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static double mvqcYV24Gw(EntityPlayerSP var0) {
      return var0.posZ;
   }

   private static String dM2FFLYn4y() {
      return 0c.PREFIX;
   }

   private static boolean wWQUOpbYhA() {
      return $assertionsDisabled;
   }

   private static PrintStream Y1wdi1u75p() {
      return System.out;
   }

   private static ScriptEngine FL5fduiGaa(0dB var0) {
      return var0.engine;
   }

   private static String _lQMN7vona/* $FF was: 1lQMN7vona*/() {
      return 0c.PREFIX;
   }

   private static 0dB IPcpFCo4Br(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static void OBN6GpSdn0(String var0) {
      target = var0;
   }

   private static double qG6fapi9FC(0cD var0) {
      return var0.posZ;
   }

   private static 0cD Adh8OIIkOo(0cC var0) {
      return var0.player;
   }

   private static 0cD SJkbhqzxwJ(0cC var0) {
      return var0.player;
   }

   private static String OWTiEH6lCr() {
      return 0c.PREFIX;
   }

   private static double bDV1DGLVIf(0cD var0) {
      return var0.posY;
   }

   private static String ATZQwnKiPH() {
      return 0c.PREFIX;
   }

   private static 0cD IqvVvBWvb9(0cC var0) {
      return var0.player;
   }

   private static 0dB U8AuMhn6G2(0bK var0) {
      return var0.pBotsScriptManager;
   }

   public _d/* $FF was: 0d*/() {
   }

   private static double OGANx6saP0(EntityPlayerSP var0) {
      return var0.posX;
   }

   private static double hrVQWymQUL(0cD var0) {
      return var0.posX;
   }

   private static EnumFacing IjVWaVi6jH() {
      return EnumFacing.SOUTH;
   }

   private static int AeyFIRgFlJ() {
      return delay;
   }

   private static File C31E2FoxnY(Minecraft var0) {
      return var0.gameDir;
   }

   private static float AmZzAb3L4V(Vector2f var0) {
      return var0.x;
   }

   private static boolean PwrvtD26FO(0bv var0) {
      return var0.value;
   }

   private static int _2br6j9M2t/* $FF was: 02br6j9M2t*/() {
      return delay;
   }

   private static double _dzdPqF1ka/* $FF was: 1dzdPqF1ka*/(EntityPlayerSP var0) {
      return var0.posY;
   }

   private static String SIW7IYoPi1() {
      return 0c.PREFIX;
   }

   private static 0cm Jee5nMO7Nr() {
      return 0cm.SUCCESS;
   }

   private static String LnlCn2gvT9() {
      return 0c.PREFIX;
   }

   private static int rjoyry3rNH() {
      return npcId;
   }

   private static int _kgSUOxdHb/* $FF was: 7kgSUOxdHb*/() {
      return delay;
   }

   private static String Sa1SnvKYbo() {
      return 0c.PREFIX;
   }

   private static void A74UzdD6Gc(boolean var0) {
      joinloop = var0;
   }

   private static int _QSNYCHNEq/* $FF was: 0QSNYCHNEq*/() {
      return npcId;
   }

   private static String RQc7Vtybsl() {
      return 0c.PREFIX;
   }

   private static int _7EdqliwYK/* $FF was: 27EdqliwYK*/() {
      return delay;
   }

   private static String VC3f5yGsYa() {
      return 0c.PREFIX;
   }

   private static double _gXhLYpbID/* $FF was: 8gXhLYpbID*/(Entity var0) {
      return var0.posX;
   }

   private static int HVnbCb1e9C() {
      return delay;
   }

   private static EnumHand goA1RvsTab() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cH vcCJdXeYgw(0cC var0) {
      return var0.mc;
   }

   private static void EsA9vFSeQb(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static double Jdbl54gWVi(Entity var0) {
      return var0.posZ;
   }

   private static 0cD eoxlqYWqSi(0cC var0) {
      return var0.player;
   }

   private static Container BnQf9Xb24w(0cD var0) {
      return var0.openContainer;
   }

   private static void UtBbXN0B8e(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static String vH9xqu2Bqd() {
      return 0c.PREFIX;
   }

   private static 0bv qRQUv8oJG8() {
      return 0cd.useProxy;
   }

   private static float OwiH63qp7N(Vector2f var0) {
      return var0.y;
   }

   private static 0dC aNYTyoeoPS() {
      return 0dC.WARNING;
   }

   private static 0cD jtNbIG1WzI(0cC var0) {
      return var0.player;
   }

   private static 0cD _nE44tnBYP/* $FF was: 9nE44tnBYP*/(0cC var0) {
      return var0.player;
   }

   private static String r66JXGjdxP() {
      return 0c.PREFIX;
   }

   private static 0dB GPWOlowhlD(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static int biwQmHYa2R() {
      return delay;
   }

   private static String _dyPX6JR17/* $FF was: 2dyPX6JR17*/() {
      return 0c.PREFIX;
   }

   private static String bJOLXlw9Ml() {
      return 0c.PREFIX;
   }

   private static String JLRadtCTkM() {
      return 0c.PREFIX;
   }

   private static String eS4VNB6wtw() {
      return 0c.PREFIX;
   }

   private static int JPQUGipka8() {
      return delay;
   }

   private static int LVRd9pwBHC() {
      return delay;
   }

   private static String tQvYWJPTkA() {
      return 0c.PREFIX;
   }

   private static void DO7plVttRt(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static boolean OloGNDR5w6() {
      return attack;
   }

   private static boolean rSvyidSunw() {
      return attack;
   }

   private static 0cH YXoE6iGjvW(0cC var0) {
      return var0.mc;
   }

   private static String eDSGFBiK9q() {
      return 0c.PREFIX;
   }

   private static void k4SkqICYTB(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static TextFormatting Fys7ywZnVA() {
      return TextFormatting.GREEN;
   }

   private static String GqdgsNG4iQ() {
      return 0c.PREFIX;
   }

   private static void CwB7HrDXAd(boolean var0) {
      joinloop = var0;
   }

   private static String mSXlGWgLAo() {
      return 0c.PREFIX;
   }

   private static void ZY3BdwgiNb(boolean var0) {
      mirror = var0;
   }

   private static double _SaWNTQDeJ/* $FF was: 4SaWNTQDeJ*/(0cD var0) {
      return var0.posZ;
   }

   private static String QSIeegBrYS() {
      return 0c.PREFIX;
   }

   private static void bVgjwFRdVL(boolean var0) {
      randommove = var0;
   }

   private static boolean bzDSrLTfF9() {
      return joinloop;
   }

   private static double poMzqaAV9I(Entity var0) {
      return var0.posZ;
   }

   private static boolean hUiQ6BHjLG() {
      return randommove;
   }

   private static String _wOUatAXVH/* $FF was: 8wOUatAXVH*/() {
      return 0c.PREFIX;
   }

   private static String CCdYQaA9nk() {
      return 0c.PREFIX;
   }

   private static String A0Bg6Jgbxj() {
      return 0c.PREFIX;
   }

   public void error() {
      0dK.formatMsg(EBdzclwkMo("ȁȥȧȫȦȯɐػȊȥəȥȩءػ"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + tQvYWJPTkA() + EBdzclwkMo("ٹٴٯ٨ػٸٴٵٵپٸٯػاȡȥȠضȩȥإػاȬȫȯȮɛȭȡȫإػضػȌȫȤɘɚȡػȪȥəȥȩػȦȫػɚȮɛȩȮɛص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + QSIeegBrYS() + EBdzclwkMo("ٹٴٯ٨ػ٨ٯٴ٫ٱٴٲٵػضػȅɚəȫȦȥȩȣəɗػȬȫȤɘɚȡػȪȥəȥȩص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + T0LvNdI7w1() + EBdzclwkMo("ٹٴٯ٨ػٸٳٺٯػاɚȥȥȪɒȮȦȣȮإػضػȅəȤɛȫȩȣəɗػɚȥȥȪɒȮȦȣȮػȥəػȣȧȮȦȣػȪȥəȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + eS4VNB6wtw() + EBdzclwkMo("ٹٴٯ٨ػٲٵ٭ٸٷپٺ٩ػضػȊȥəɐػȩɐȡȣȦɘəػȩɚȮػɛȮɚɘɛɚɐػȣȬػȣȦȩȮȦəȫɛɔص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + oIFarEvpnv() + EBdzclwkMo("ٹٴٯ٨ػٷٴٺٿ٫٩ٴ٣٢ػا٫٩ٴ٣ٲپ٨صٯ٣ٯشَىٗإػا٨ٴٸٰ٨دش٨ٴٸٰ٨خشٳٯٯ٫إػضػȌȫȨɛɘȬȡȫػȤɛȥȡɚȣػȣȬػɟȫȢȠȫػȣȠȣػɚɚɐȠȡȣص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + eDSGFBiK9q() + EBdzclwkMo("ٹٴٯ٨ػٿٲ٨ٸٴٵٵپٸٯػضػȅəȡȠɕɜȣəɗػȪȥəȥȩػȥəػɚȮɛȩȮɛȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + f4DAFBLS21() + EBdzclwkMo("ٹٴٯ٨ػٳٴٯٹٺ٩ػاɚȠȥəإػضػȊȥəɐػȦȫȭȣȧȫɕəػȦȫػɚȠȥəػȩػɞȥəȪȫɛȮص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + bJOLXlw9Ml() + EBdzclwkMo("ٹٴٯ٨ػٲٵ٭ٸٷٲٸٰػاɚȠȥəإػضػȊȥəɐػȦȫȭȣȧȫɕəػȦȫػɚȠȥəػȩػȣȦȩȮȦəȫɛȮص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + eiV2R9y6B2() + EBdzclwkMo("ٹٴٯ٨ػٶٺٵٮٺٷػاȥəȩȮəإػضػȻɘɜȦȥȮػɛȮɓȮȦȣȮػȡȫȤɜȣػȪȥəȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + ATZQwnKiPH() + EBdzclwkMo("ٹٴٯ٨ػٽٴٷٷٴ٬ػاٵٺٶپإػضػȊȥəɐػȦȫɜȦɘəػȣȯəȣػȬȫػȣȨɛȥȡȥȧشȧȥȪȥȧص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + RQc7Vtybsl() + EBdzclwkMo("ٹٴٯ٨ػ٩ٺٵٿٴٶٶٴ٭پػضػȊȥəɐػȦȫɜȦɘəػɛȫȦȯȥȧȦȥػɞȥȯȣəɗػȣػȤɛɐȨȫəɗص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + UeRGaAFjlo() + EBdzclwkMo("ٹٴٯ٨ػ٨٫ٲٵػاٵٺٶپإػا٩ٺٿٲٮ٨إػضػȊȥəɐػȦȫɜȦɘəػɞȥȯȣəɗػȩȥȡɛɘȨػȣȨɛȥȡȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + A4JG0oXkr9() + EBdzclwkMo("ٹٴٯ٨ػٶٲ٩٩ٴ٩ػضػȊȥəɐػȦȫɜȦɘəػȤȥȩəȥɛɔəɗػȩȫɓȣػȯȮȢɚəȩȣɔص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + LaL4mGBJpO() + EBdzclwkMo("ٹٴٯ٨ػ٨٫ٺٶٶپ٩ػاٿپٷٺ٢لٶ٨شٴٽٽإػاٯپ٣ٯإػضػȊȥəɐػȦȫɜȦɘəػɚȤȫȧȣəɗػɘȡȫȬȫȦɐȧػəȮȡɚəȥȧص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + g2m3Itw6Ah() + EBdzclwkMo("ٹٴٯ٨ػٸٷپٺ٩ػضػȄɛȣȦɘȯȣəȮȠɗȦȥȮػȥəȡȠɕɜȮȦȣȮػȪȥəȥȩػȣػȥɜȣɚəȡȫػȣɞػɚȤȣɚȡȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + pG6vauWdeS() + EBdzclwkMo("ٹٴٯ٨ػٺٯٯٺٸٰػاٵٺٶپإػضػȊȥəɐػȦȫɜȦɘəػȫəȫȡȥȩȫəɗػȣȨɛȥȡȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + O2E36J4H4I() + EBdzclwkMo("ٹٴٯ٨ػٷٴٴٰػا٢ٺ٬إػا٫ٲٯٸٳإػضػȸɚəȫȦȥȩȣəɗػȦȫȤɛȫȩȠȮȦȣɔػȩȬȨȠɔȯȫػȪȥəȫȧص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + F1Qxr4GJi6() + EBdzclwkMo("ٹٴٯ٨ػ٨پٯٿپٷٺ٢ػاٶٲٷٲ٨پٸٴٵٿ٨إػضػȌȫȯȮɛȭȡȫػȧȮȭȯɘػȪȥəȫȧȣػȩػٲٵ٭ٸٷٲٸٰشٳٴٯٹٺ٩شٸٳٺٯص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + 1wg6oytPsh() + EBdzclwkMo("ٹٴٯ٨ػ٨ٷٴٯٵٺٶپػاٵٺٶپإػضػȁȠȣȡػȤȥػɚȠȥəɘػȩػȣȦȩȮȦəȫɛȮػȣɚȤȥȠɗȬɘɔػȦȫȬȩȫȦȣȮػȤɛȮȯȧȮəȫص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + OWTiEH6lCr() + EBdzclwkMo("ٹٴٯ٨ػ٨پٯ٨ٸ٩ٲ٫ٯػاٽٲٷپٵٺٶپشپٶ٫ٯ٢إػضػȌȫȨɛɘȬȣəɗػȣػȬȫȤɘɚəȣəɗػɚȡɛȣȤəص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + USYJzTG1rx() + EBdzclwkMo("ٹٴٯ٨ػٺٮٯٳػضػȻȮȨȮɚəɛȣɛɘȮəػȩɚȮɞػȪȥəȥȩص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + JLRadtCTkM() + EBdzclwkMo("ٹٴٯ٨ػټٸػضػȼȣɚəȣəػȥȬɘطػɘȪȣɛȫȮəػȠȫȨȣص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + nVD02Cj0by() + EBdzclwkMo("ٹٴٯ٨ػ٩پٱٴٲٵٺٷٷػضػȊȥəɐػȤȮɛȮȬȫȢȯɘəػȦȫػɚȮɛȩȮɛص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + LnlCn2gvT9() + EBdzclwkMo("ٹٴٯ٨ػٸٷٲٸٰٹٷٴٸٰػا٣إػا٢إػا١إػضػȆȫȭȫəɗػȦȫػȪȠȥȡص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + GqdgsNG4iQ() + EBdzclwkMo("ٹٴٯ٨ػٵ٫ٸػا٩پٸش٨ٯٴ٫ش٫ٷٺ٢إػضػȌȫȤȣɚɗػȣػȩȥɚȤɛȥȣȬȩȮȯȮȦȣȮػȦȫȭȫəȣɔػȤȥػًٕ٘ص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + CCdYQaA9nk() + EBdzclwkMo("ٹٴٯ٨ػٹ٨ٯٴ٫ػضػȅɚəȫȩȦȥȩȣəɗػٽٴٷٷٴ٬شټٴٯٴشٺٮٯٴٽٲ٨ٳص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + vH9xqu2Bqd() + EBdzclwkMo("ٹٴٯ٨ػ٩پ٫پٺٯػضػȉȥɚȤɛȥȣȬȩȮȯȮȦȣȮػȬȫȤȣɚȫȦȦȥȨȥػȩػٚٸٯٲٴٵىپٸٴ٩ٿپ٩ص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + 8wOUatAXVH() + EBdzclwkMo("ٹٴٯ٨ػٺٮٯٴٽٲ٨ٳػضػȊȥəɐػȦȫɜȦɘəɗػɛɐȪȫɜȣəɗص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + 2dyPX6JR17() + EBdzclwkMo("ٹٴٯ٨ػټٴٯٴػا٣إػا٢إػا١إػضػȊȥəɐػȦȫɜȦɘəػȣȯəȣػȦȫػȡȥȥɛȯȣȦȫəɐص"));
      0dK.defaultMsg(EBdzclwkMo("ػ") + D7XtNv16l7() + EBdzclwkMo("ٹٴٯ٨ػٽٲټٮ٩پػا٨٪ٮٺ٩پشٯ٩ٲٺٵټٷپشٷٺ٩ټپٯ٩ٲٺٵټٷپشٸٲ٩ٸٷپشٷٲٵپشضٷٲٵپش٩ٳٴٶٹإػضػȄȥɚəɛȥȮȦȣȮػɟȣȨɘɛɐػȥəȦȥɚȣəȮȠɗȦȥػȩȫɚص"));
   }

   private static String r4reegynmg() {
      return 0c.PREFIX;
   }

   private static 0cD uWjrNHzBiL(0cC var0) {
      return var0.player;
   }

   private static 0ep AvZNTR3llV() {
      return 0ep.NO_PROXY;
   }

   private static void Y6H2MafwJy(int var0) {
      delay = var0;
   }

   private static File zjqcQde98A(Minecraft var0) {
      return var0.gameDir;
   }

   private static String own9V1onBJ() {
      return 0c.PREFIX;
   }

   private static void _u5F6xquFB/* $FF was: 4u5F6xquFB*/(int var0) {
      npcId = var0;
   }

   private static 0dB OIW92AfEfA(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static void A66AlIOBIi(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static String kHKLOoi1Jk() {
      return 0c.PREFIX;
   }

   private static boolean _BjBgBw6NM/* $FF was: 3BjBgBw6NM*/(0bv var0) {
      return var0.value;
   }

   private static int Gb10wVcvgW() {
      return delay;
   }

   private static String ixBDCEMyCK() {
      return 0c.PREFIX;
   }

   private static String C6OL6o1QsM() {
      return 0c.PREFIX;
   }

   private static String eiV2R9y6B2() {
      return 0c.PREFIX;
   }

   private static double _ObC4HSfzQ/* $FF was: 7ObC4HSfzQ*/(0cD var0) {
      return var0.posX;
   }

   private static String _a76zWMyGL/* $FF was: 4a76zWMyGL*/() {
      return 0c.PREFIX;
   }

   private static void _N7njzWIdO/* $FF was: 7N7njzWIdO*/(boolean var0) {
      attack = var0;
   }

   private static ClickType _DaoU131BX/* $FF was: 7DaoU131BX*/() {
      return ClickType.THROW;
   }

   private static String FiL6y2NPuS() {
      return 0c.PREFIX;
   }

   private static float UjniUt40O4(Vector2f var0) {
      return var0.x;
   }

   private static 0cD j5Ci6ra6PS(0cC var0) {
      return var0.player;
   }

   private static 0cL qZkSaL12TT(0cD var0) {
      return var0.connection;
   }

   private static void W6jAyL1pGS(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static int dyMUe1qT06() {
      return delay;
   }

   private static EntityPlayerSP vRUiGxIzj8() {
      return Minecraft.player;
   }

   private static boolean w0f9HY1b7A() {
      return randommove;
   }

   private static String _wg6oytPsh/* $FF was: 1wg6oytPsh*/() {
      return 0c.PREFIX;
   }

   private static 0bv _Jp4yoVBwC/* $FF was: 3Jp4yoVBwC*/() {
      return 0cd.useProxy;
   }

   private static 0dB f5aHO7b17I(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static int qPzmRgLqeb() {
      return delay;
   }

   private static String USYJzTG1rx() {
      return 0c.PREFIX;
   }

   private static Channel hRwGyGr9nT(0cP var0) {
      return var0.channel;
   }

   public static int parseInt(String s, int defaultVal) {
      return s.matches(EBdzclwkMo("ضؤهٿذ")) ? Integer.parseInt(s) : defaultVal;
   }

   private static double dZLtBSgyUu(Entity var0) {
      return var0.posY;
   }

   private static String nVD02Cj0by() {
      return 0c.PREFIX;
   }

   private static 0dB QRbDlBKjXC(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static 0cD Nm9jGpVZDG(0cC var0) {
      return var0.player;
   }

   private static 0bv rz1420iZtk() {
      return 0cd.useProxy;
   }

   private static String UeRGaAFjlo() {
      return 0c.PREFIX;
   }

   private static void _tSEqw3J9K/* $FF was: 4tSEqw3J9K*/(boolean var0) {
      attack = var0;
   }

   private static 0cD BInwbv7ALP(0cC var0) {
      return var0.player;
   }

   private static 0cE tG8Ym8yBsO(0cH var0) {
      return var0.gameSettings;
   }

   public static String joinArrayElements(String[] arr) {
      StringBuilder result = new StringBuilder();

      for(int i = 20378 ^ -15590 ^ 6828 ^ -27092; i < arr.length; ++i) {
         result.append(arr[i]);
         if (i < arr.length - (30594 ^ -8187 ^ 6885 ^ -29341)) {
            result.append(EBdzclwkMo("ػ"));
         }
      }

      return result.toString();
   }

   private static 0cL SugvY32qTi(0cD var0) {
      return var0.connection;
   }

   private static String _lyylWQMkn/* $FF was: 1lyylWQMkn*/() {
      return 0c.PREFIX;
   }

   private static void RYsuw3nGzQ(0cE var0, boolean var1) {
      var0.keyBindForward = var1;
   }

   private static 0cE f23LBw217b(0cH var0) {
      return var0.gameSettings;
   }

   private static int iYnoL9lNun() {
      return delay;
   }

   private static boolean FcMLyK426Y(0bv var0) {
      return var0.value;
   }

   private static EntityPlayerSP _QmLDPaEho/* $FF was: 9QmLDPaEho*/() {
      return Minecraft.player;
   }

   private static String SbL4PZ6OFG() {
      return 0c.PREFIX;
   }

   private static EntityPlayerSP BySOGX60r3() {
      return Minecraft.player;
   }

   private static boolean GQeoLINf6d() {
      return randommove;
   }

   private static 0cD rWk64NDjjs(0cC var0) {
      return var0.player;
   }

   private static float AaQ2NDJYXn(Vector2f var0) {
      return var0.y;
   }

   private static double DbTLw6AoEB(Entity var0) {
      return var0.posX;
   }

   private static String oIFarEvpnv() {
      return 0c.PREFIX;
   }

   private static int e6SblnwlXo() {
      return delay;
   }

   private static 0dB fdNycS0a47(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static double ed7Ani4Jq2(0cD var0) {
      return var0.posY;
   }

   private static ClickType _7eyq9Yrir/* $FF was: 97eyq9Yrir*/() {
      return ClickType.PICKUP;
   }

   private static int _ZkcTwZgIu/* $FF was: 1ZkcTwZgIu*/() {
      return delay;
   }

   private static EnumHand _wkL6bmTbz/* $FF was: 6wkL6bmTbz*/() {
      return EnumHand.MAIN_HAND;
   }

   private static EntityPlayerSP _6SFO6tDIb/* $FF was: 66SFO6tDIb*/() {
      return Minecraft.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String EBdzclwkMo(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 26498 ^ -5512 ^ 19246 ^ -14636; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 15461 ^ -29883 ^ 19904 ^ -773));
      }

      return var1.toString();
   }

   private static EnumHand _BzgNmyQdL/* $FF was: 9BzgNmyQdL*/() {
      return EnumHand.MAIN_HAND;
   }

   private static boolean eFDOSpI8k7() {
      return attack;
   }

   private static void AST5nl4TtH(boolean var0) {
      joinloop = var0;
   }

   private static File QHzuV9F3In(Minecraft var0) {
      return var0.gameDir;
   }

   private static boolean PvHainvBLd() {
      return attack;
   }

   private static 0cD DegHS2JoG2(0cC var0) {
      return var0.player;
   }

   private static 0cD S6dqOtIcvH(0cC var0) {
      return var0.player;
   }

   private static String D7XtNv16l7() {
      return 0c.PREFIX;
   }

   private static String DiOKAczIIm() {
      return 0c.PREFIX;
   }

   private static 0cE WvtPpfzdtg(0cH var0) {
      return var0.gameSettings;
   }

   private static String pG6vauWdeS() {
      return 0c.PREFIX;
   }

   private static String BG89zEJmc8() {
      return 0c.PREFIX;
   }

   private static void qdrJ4JoIAa(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static ClickType _t7J2eiPOT/* $FF was: 7t7J2eiPOT*/() {
      return ClickType.PICKUP;
   }

   private static void RibeA7r6Ns(int var0) {
      npcId = var0;
   }

   private static String g2m3Itw6Ah() {
      return 0c.PREFIX;
   }

   private static 0by N9JkqylD62() {
      return 0cd.nickstype;
   }

   private static boolean YdN2e9D3CG() {
      return mirror;
   }

   private static boolean w7wntfYsOI() {
      return mirror;
   }

   private static File tSGygVGG26(Minecraft var0) {
      return var0.gameDir;
   }

   private static String fL7ondlt4t() {
      return 0c.PREFIX;
   }

   private static 0ep E0H2lYlauA() {
      return 0ep.NO_PROXY;
   }

   private static String T0LvNdI7w1() {
      return 0c.PREFIX;
   }
}
