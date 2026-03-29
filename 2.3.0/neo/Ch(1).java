package neo;

import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Doubles;
import com.google.gson.JsonParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class Ch implements Dy {
   private static Dz commandListener;
   private static final Splitter COMMA_SPLITTER = Splitter.on(',');
   private static final Splitter EQUAL_SPLITTER = Splitter.on('=').limit(2);

   public Ch() {
   }

   protected static Ei toSyntaxException(JsonParseException e) {
      Throwable throwable = ExceptionUtils.getRootCause(e);
      String s = "";
      if (throwable != null) {
         s = throwable.getMessage();
         if (s.contains("setLenient")) {
            s = s.substring(s.indexOf("to accept ") + 10);
         }
      }

      return new Ei("commands.tellraw.jsonException", new Object[]{s});
   }

   public static QQ entityToNBT(Ig theEntity) {
      QQ nbttagcompound = theEntity.writeToNBT(new QQ());
      if (theEntity instanceof ME) {
         Qy itemstack = ((ME)theEntity).inventory.getCurrentItem();
         if (!itemstack.isEmpty()) {
            nbttagcompound.setTag("SelectedItem", itemstack.writeToNBT(new QQ()));
         }
      }

      return nbttagcompound;
   }

   public int getRequiredPermissionLevel() {
      return 4;
   }

   public List<String> getAliases() {
      return Collections.emptyList();
   }

   public boolean checkPermission(Xx server, DB sender) {
      return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return Collections.emptyList();
   }

   public static int parseInt(String input) throws DD {
      try {
         return Integer.parseInt(input);
      } catch (NumberFormatException var2) {
         throw new DD("commands.generic.num.invalid", new Object[]{input});
      }
   }

   public static int parseInt(String input, int min) throws DD {
      return parseInt(input, min, Integer.MAX_VALUE);
   }

   public static int parseInt(String input, int min, int max) throws DD {
      int i = parseInt(input);
      if (i < min) {
         throw new DD("commands.generic.num.tooSmall", new Object[]{i, min});
      } else if (i > max) {
         throw new DD("commands.generic.num.tooBig", new Object[]{i, max});
      } else {
         return i;
      }
   }

   public static long parseLong(String input) throws DD {
      try {
         return Long.parseLong(input);
      } catch (NumberFormatException var2) {
         throw new DD("commands.generic.num.invalid", new Object[]{input});
      }
   }

   public static long parseLong(String input, long min, long max) throws DD {
      long i = parseLong(input);
      if (i < min) {
         throw new DD("commands.generic.num.tooSmall", new Object[]{i, min});
      } else if (i > max) {
         throw new DD("commands.generic.num.tooBig", new Object[]{i, max});
      } else {
         return i;
      }
   }

   public static BlockPos parseBlockPos(DB sender, String[] args, int startIndex, boolean centerBlock) throws DD {
      BlockPos blockpos = sender.getPosition();
      return new BlockPos(parseDouble((double)blockpos.getX(), args[startIndex], -30000000, 30000000, centerBlock), parseDouble((double)blockpos.getY(), args[startIndex + 1], 0, 256, false), parseDouble((double)blockpos.getZ(), args[startIndex + 2], -30000000, 30000000, centerBlock));
   }

   public static double parseDouble(String input) throws DD {
      try {
         double d0 = Double.parseDouble(input);
         if (!Doubles.isFinite(d0)) {
            throw new DD("commands.generic.num.invalid", new Object[]{input});
         } else {
            return d0;
         }
      } catch (NumberFormatException var3) {
         throw new DD("commands.generic.num.invalid", new Object[]{input});
      }
   }

   public static double parseDouble(String input, double min) throws DD {
      return parseDouble(input, min, Double.MAX_VALUE);
   }

   public static double parseDouble(String input, double min, double max) throws DD {
      double d0 = parseDouble(input);
      if (d0 < min) {
         throw new DD("commands.generic.num.tooSmall", new Object[]{String.format("%.2f", d0), String.format("%.2f", min)});
      } else if (d0 > max) {
         throw new DD("commands.generic.num.tooBig", new Object[]{String.format("%.2f", d0), String.format("%.2f", max)});
      } else {
         return d0;
      }
   }

   public static boolean parseBoolean(String input) throws Ct {
      if (!"true".equals(input) && !"1".equals(input)) {
         if (!"false".equals(input) && !"0".equals(input)) {
            throw new Ct("commands.generic.boolean.invalid", new Object[]{input});
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public static MG getCommandSenderAsPlayer(DB sender) throws DF {
      if (sender instanceof MG) {
         return (MG)sender;
      } else {
         throw new DF("commands.generic.player.unspecified");
      }
   }

   public static List<MG> getPlayers(Xx p_193513_0_, DB p_193513_1_, String p_193513_2_) throws Ct {
      List<MG> list = Ds.getPlayers(p_193513_1_, p_193513_2_);
      return (List)(list.isEmpty() ? Lists.newArrayList(new MG[]{getPlayer(p_193513_0_, (MG)null, p_193513_2_)}) : list);
   }

   public static MG getPlayer(Xx server, DB sender, String target) throws DF, Ct {
      return getPlayer(server, Ds.matchOnePlayer(sender, target), target);
   }

   private static MG getPlayer(Xx p_193512_0_, @Nullable MG p_193512_1_, String p_193512_2_) throws Ct {
      if (p_193512_1_ == null) {
         try {
            p_193512_1_ = p_193512_0_.getPlayerList().getPlayerByUUID(UUID.fromString(p_193512_2_));
         } catch (IllegalArgumentException var4) {
         }
      }

      if (p_193512_1_ == null) {
         p_193512_1_ = p_193512_0_.getPlayerList().getPlayerByUsername(p_193512_2_);
      }

      if (p_193512_1_ == null) {
         throw new DF("commands.generic.player.notFound", new Object[]{p_193512_2_});
      } else {
         return p_193512_1_;
      }
   }

   public static Ig getEntity(Xx server, DB sender, String target) throws Dd, Ct {
      return getEntity(server, sender, target, Ig.class);
   }

   public static <T extends Ig> T getEntity(Xx server, DB sender, String target, Class<? extends T> targetClass) throws Dd, Ct {
      Ig entity = Ds.matchOneEntity(sender, target, targetClass);
      if (entity == null) {
         entity = server.getPlayerList().getPlayerByUsername(target);
      }

      if (entity == null) {
         try {
            UUID uuid = UUID.fromString(target);
            entity = server.getEntityFromUuid(uuid);
            if (entity == null) {
               entity = server.getPlayerList().getPlayerByUUID(uuid);
            }
         } catch (IllegalArgumentException var6) {
            if (target.split("-").length == 5) {
               throw new Dd("commands.generic.entity.invalidUuid", new Object[]{target});
            }
         }
      }

      if (entity != null && targetClass.isAssignableFrom(entity.getClass())) {
         return (Ig)entity;
      } else {
         throw new Dd(target);
      }
   }

   public static List<Ig> getEntityList(Xx server, DB sender, String target) throws Dd, Ct {
      return (List)(Ds.isSelector(target) ? Ds.matchEntities(sender, target, Ig.class) : Lists.newArrayList(new Ig[]{getEntity(server, sender, target)}));
   }

   public static String getPlayerName(Xx server, DB sender, String target) throws DF, Ct {
      try {
         return getPlayer(server, sender, target).getName();
      } catch (Ct var4) {
         Ct commandexception = var4;
         if (Ds.isSelector(target)) {
            throw commandexception;
         } else {
            return target;
         }
      }
   }

   public static String getEntityName(Xx server, DB sender, String target) throws Dd, Ct {
      try {
         return getPlayer(server, sender, target).getName();
      } catch (DF var6) {
         try {
            return getEntity(server, sender, target).getCachedUniqueIdString();
         } catch (Dd var5) {
            Dd entitynotfoundexception = var5;
            if (Ds.isSelector(target)) {
               throw entitynotfoundexception;
            } else {
               return target;
            }
         }
      }
   }

   public static ITextComponent getChatComponentFromNthArg(DB sender, String[] args, int index) throws Ct, DF {
      return getChatComponentFromNthArg(sender, args, index, false);
   }

   public static ITextComponent getChatComponentFromNthArg(DB sender, String[] args, int index, boolean p_147176_3_) throws DF, Ct {
      ITextComponent itextcomponent = new TextComponentString("");

      for(int i = index; i < args.length; ++i) {
         if (i > index) {
            itextcomponent.appendText(" ");
         }

         ITextComponent itextcomponent1 = new TextComponentString(args[i]);
         if (p_147176_3_) {
            ITextComponent itextcomponent2 = Ds.matchEntitiesToTextComponent(sender, args[i]);
            if (itextcomponent2 == null) {
               if (Ds.isSelector(args[i])) {
                  throw new DF("commands.generic.selector.notFound", new Object[]{args[i]});
               }
            } else {
               itextcomponent1 = itextcomponent2;
            }
         }

         itextcomponent.appendSibling((ITextComponent)itextcomponent1);
      }

      return itextcomponent;
   }

   public static String buildString(String[] args, int startPos) {
      StringBuilder stringbuilder = new StringBuilder();

      for(int i = startPos; i < args.length; ++i) {
         if (i > startPos) {
            stringbuilder.append(" ");
         }

         String s = args[i];
         stringbuilder.append(s);
      }

      return stringbuilder.toString();
   }

   public static Cg parseCoordinate(double base, String selectorArg, boolean centerBlock) throws DD {
      return parseCoordinate(base, selectorArg, -30000000, 30000000, centerBlock);
   }

   public static Cg parseCoordinate(double base, String selectorArg, int min, int max, boolean centerBlock) throws DD {
      boolean flag = selectorArg.startsWith("~");
      if (flag && Double.isNaN(base)) {
         throw new DD("commands.generic.num.invalid", new Object[]{base});
      } else {
         double d0 = 0.0;
         if (!flag || selectorArg.length() > 1) {
            boolean flag1 = selectorArg.contains(".");
            if (flag) {
               selectorArg = selectorArg.substring(1);
            }

            d0 += parseDouble(selectorArg);
            if (!flag1 && !flag && centerBlock) {
               d0 += 0.5;
            }
         }

         double d1 = d0 + (flag ? base : 0.0);
         if (min != 0 || max != 0) {
            if (d1 < (double)min) {
               throw new DD("commands.generic.num.tooSmall", new Object[]{String.format("%.2f", d1), min});
            }

            if (d1 > (double)max) {
               throw new DD("commands.generic.num.tooBig", new Object[]{String.format("%.2f", d1), max});
            }
         }

         return new Cg(d1, d0, flag);
      }
   }

   public static double parseDouble(double base, String input, boolean centerBlock) throws DD {
      return parseDouble(base, input, -30000000, 30000000, centerBlock);
   }

   public static double parseDouble(double base, String input, int min, int max, boolean centerBlock) throws DD {
      boolean flag = input.startsWith("~");
      if (flag && Double.isNaN(base)) {
         throw new DD("commands.generic.num.invalid", new Object[]{base});
      } else {
         double d0 = flag ? base : 0.0;
         if (!flag || input.length() > 1) {
            boolean flag1 = input.contains(".");
            if (flag) {
               input = input.substring(1);
            }

            d0 += parseDouble(input);
            if (!flag1 && !flag && centerBlock) {
               d0 += 0.5;
            }
         }

         if (min != 0 || max != 0) {
            if (d0 < (double)min) {
               throw new DD("commands.generic.num.tooSmall", new Object[]{String.format("%.2f", d0), min});
            }

            if (d0 > (double)max) {
               throw new DD("commands.generic.num.tooBig", new Object[]{String.format("%.2f", d0), max});
            }
         }

         return d0;
      }
   }

   public static OL getItemByText(DB sender, String id) throws DD {
      ResourceLocation resourcelocation = new ResourceLocation(id);
      OL item = (OL)OL.REGISTRY.getObject(resourcelocation);
      if (item == null) {
         throw new DD("commands.give.item.notFound", new Object[]{resourcelocation});
      } else {
         return item;
      }
   }

   public static co getBlockByText(DB sender, String id) throws DD {
      ResourceLocation resourcelocation = new ResourceLocation(id);
      if (!co.REGISTRY.containsKey(resourcelocation)) {
         throw new DD("commands.give.block.notFound", new Object[]{resourcelocation});
      } else {
         return (co)co.REGISTRY.getObject(resourcelocation);
      }
   }

   public static in convertArgToBlockState(co p_190794_0_, String p_190794_1_) throws DD, DC {
      try {
         int i = Integer.parseInt(p_190794_1_);
         if (i < 0) {
            throw new DD("commands.generic.num.tooSmall", new Object[]{i, 0});
         } else if (i > 15) {
            throw new DD("commands.generic.num.tooBig", new Object[]{i, 15});
         } else {
            return p_190794_0_.getStateFromMeta(Integer.parseInt(p_190794_1_));
         }
      } catch (RuntimeException var8) {
         try {
            Map<hT<?>, Comparable<?>> map = getBlockStatePropertyValueMap(p_190794_0_, p_190794_1_);
            in iblockstate = p_190794_0_.getDefaultState();

            Map.Entry entry;
            for(Iterator var5 = map.entrySet().iterator(); var5.hasNext(); iblockstate = getBlockState(iblockstate, (hT)entry.getKey(), (Comparable)entry.getValue())) {
               entry = (Map.Entry)var5.next();
            }

            return iblockstate;
         } catch (RuntimeException var7) {
            throw new DC("commands.generic.blockstate.invalid", new Object[]{p_190794_1_, co.REGISTRY.getNameForObject(p_190794_0_)});
         }
      }
   }

   private static <T extends Comparable<T>> in getBlockState(in p_190793_0_, hT<T> p_190793_1_, Comparable<?> p_190793_2_) {
      return p_190793_0_.withProperty(p_190793_1_, p_190793_2_);
   }

   public static Predicate<in> convertArgToBlockStatePredicate(final co p_190791_0_, String p_190791_1_) throws DC {
      if (!"*".equals(p_190791_1_) && !"-1".equals(p_190791_1_)) {
         try {
            final int i = Integer.parseInt(p_190791_1_);
            return new Predicate<in>() {
               public boolean apply(@Nullable in p_apply_1_) {
                  return i == p_apply_1_.getBlock().getMetaFromState(p_apply_1_);
               }

               // $FF: synthetic method
               // $FF: bridge method
               public boolean apply(@Nullable Object var1) {
                  return this.apply((in)var1);
               }
            };
         } catch (RuntimeException var4) {
            final Map<hT<?>, Comparable<?>> map = getBlockStatePropertyValueMap(p_190791_0_, p_190791_1_);
            return new Predicate<in>() {
               public boolean apply(@Nullable in p_apply_1_) {
                  if (p_apply_1_ != null && p_190791_0_ == p_apply_1_.getBlock()) {
                     Iterator var2 = map.entrySet().iterator();

                     Map.Entry entry;
                     do {
                        if (!var2.hasNext()) {
                           return true;
                        }

                        entry = (Map.Entry)var2.next();
                     } while(p_apply_1_.getValue((hT)entry.getKey()).equals(entry.getValue()));

                     return false;
                  } else {
                     return false;
                  }
               }

               // $FF: synthetic method
               // $FF: bridge method
               public boolean apply(@Nullable Object var1) {
                  return this.apply((in)var1);
               }
            };
         }
      } else {
         return Predicates.alwaysTrue();
      }
   }

   private static Map<hT<?>, Comparable<?>> getBlockStatePropertyValueMap(co p_190795_0_, String p_190795_1_) throws DC {
      Map<hT<?>, Comparable<?>> map = Maps.newHashMap();
      if ("default".equals(p_190795_1_)) {
         return p_190795_0_.getDefaultState().getProperties();
      } else {
         ii blockstatecontainer = p_190795_0_.getBlockState();
         Iterator iterator = COMMA_SPLITTER.split(p_190795_1_).iterator();

         while(true) {
            if (!iterator.hasNext()) {
               return map;
            }

            String s = (String)iterator.next();
            Iterator<String> iterator1 = EQUAL_SPLITTER.split(s).iterator();
            if (!iterator1.hasNext()) {
               break;
            }

            hT<?> iproperty = blockstatecontainer.getProperty((String)iterator1.next());
            if (iproperty == null || !iterator1.hasNext()) {
               break;
            }

            Comparable<?> comparable = getValueHelper(iproperty, (String)iterator1.next());
            if (comparable == null) {
               break;
            }

            map.put(iproperty, comparable);
         }

         throw new DC("commands.generic.blockstate.invalid", new Object[]{p_190795_1_, co.REGISTRY.getNameForObject(p_190795_0_)});
      }
   }

   @Nullable
   private static <T extends Comparable<T>> T getValueHelper(hT<T> p_190792_0_, String p_190792_1_) {
      return (Comparable)p_190792_0_.parseValue(p_190792_1_).orNull();
   }

   public static String joinNiceString(Object[] elements) {
      StringBuilder stringbuilder = new StringBuilder();

      for(int i = 0; i < elements.length; ++i) {
         String s = elements[i].toString();
         if (i > 0) {
            if (i == elements.length - 1) {
               stringbuilder.append(" and ");
            } else {
               stringbuilder.append(", ");
            }
         }

         stringbuilder.append(s);
      }

      return stringbuilder.toString();
   }

   public static ITextComponent join(List<ITextComponent> components) {
      ITextComponent itextcomponent = new TextComponentString("");

      for(int i = 0; i < components.size(); ++i) {
         if (i > 0) {
            if (i == components.size() - 1) {
               itextcomponent.appendText(" and ");
            } else if (i > 0) {
               itextcomponent.appendText(", ");
            }
         }

         itextcomponent.appendSibling((ITextComponent)components.get(i));
      }

      return itextcomponent;
   }

   public static String joinNiceStringFromCollection(Collection<String> strings) {
      return joinNiceString(strings.toArray(new String[strings.size()]));
   }

   public static List<String> getTabCompletionCoordinate(String[] inputArgs, int index, @Nullable BlockPos pos) {
      if (pos == null) {
         return Lists.newArrayList(new String[]{"~"});
      } else {
         int i = inputArgs.length - 1;
         String s;
         if (i == index) {
            s = Integer.toString(pos.getX());
         } else if (i == index + 1) {
            s = Integer.toString(pos.getY());
         } else {
            if (i != index + 2) {
               return Collections.emptyList();
            }

            s = Integer.toString(pos.getZ());
         }

         return Lists.newArrayList(new String[]{s});
      }
   }

   public static List<String> getTabCompletionCoordinateXZ(String[] inputArgs, int index, @Nullable BlockPos lookedPos) {
      if (lookedPos == null) {
         return Lists.newArrayList(new String[]{"~"});
      } else {
         int i = inputArgs.length - 1;
         String s;
         if (i == index) {
            s = Integer.toString(lookedPos.getX());
         } else {
            if (i != index + 1) {
               return Collections.emptyList();
            }

            s = Integer.toString(lookedPos.getZ());
         }

         return Lists.newArrayList(new String[]{s});
      }
   }

   public static boolean doesStringStartWith(String original, String region) {
      return region.regionMatches(true, 0, original, 0, original.length());
   }

   public static List<String> getListOfStringsMatchingLastWord(String[] args, String... possibilities) {
      return getListOfStringsMatchingLastWord(args, (Collection)Arrays.asList(possibilities));
   }

   public static List<String> getListOfStringsMatchingLastWord(String[] inputArgs, Collection<?> possibleCompletions) {
      String s = inputArgs[inputArgs.length - 1];
      List<String> list = Lists.newArrayList();
      if (!possibleCompletions.isEmpty()) {
         Iterator var4 = Iterables.transform(possibleCompletions, Functions.toStringFunction()).iterator();

         while(var4.hasNext()) {
            String s1 = (String)var4.next();
            if (doesStringStartWith(s, s1)) {
               list.add(s1);
            }
         }

         if (list.isEmpty()) {
            var4 = possibleCompletions.iterator();

            while(var4.hasNext()) {
               Object object = var4.next();
               if (object instanceof ResourceLocation && doesStringStartWith(s, ((ResourceLocation)object).getPath())) {
                  list.add(String.valueOf(object));
               }
            }
         }
      }

      return list;
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return false;
   }

   public static void notifyCommandListener(DB sender, Dy command, String translationKey, Object... translationArgs) {
      notifyCommandListener(sender, command, 0, translationKey, translationArgs);
   }

   public static void notifyCommandListener(DB sender, Dy command, int flags, String translationKey, Object... translationArgs) {
      if (commandListener != null) {
         commandListener.notifyListener(sender, command, flags, translationKey, translationArgs);
      }

   }

   public static void setCommandListener(Dz listener) {
      commandListener = listener;
   }

   public int compareTo(Dy p_compareTo_1_) {
      return this.getName().compareTo(p_compareTo_1_.getName());
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.compareTo((Dy)var1);
   }
}
