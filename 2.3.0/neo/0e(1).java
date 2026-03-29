package neo;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class 0e {
   public static final CopyOnWriteArrayList<0a> bots = new CopyOnWriteArrayList();
   private static String _ _;

   public static void add(0a a) {
      1x5ej1Tz67().add(a);
   }

   public _e/* $FF was: 0e*/() {
   }

   private static CopyOnWriteArrayList _x5ej1Tz67/* $FF was: 1x5ej1Tz67*/() {
      return bots;
   }

   public static CopyOnWriteArrayList<0a> getBots() {
      return Bvj6D8E5ko();
   }

   private static CopyOnWriteArrayList QLGsP4GWqn() {
      return bots;
   }

   public static void removeAll(Collection<0a> a) {
      QLGsP4GWqn().removeAll(a);
   }

   public static List<0a> getOnline() {
      return (List)aoiI3iv5Fb().stream().filter(0a::isOnline).collect(Collectors.toList());
   }

   private static CopyOnWriteArrayList Q19hdA186V() {
      return bots;
   }

   private static CopyOnWriteArrayList aoiI3iv5Fb() {
      return bots;
   }

   public static 0a get(String a) {
      return (0a)Q19hdA186V().stream().filter((b) -> {
         return b.getNickname().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   private static CopyOnWriteArrayList Bvj6D8E5ko() {
      return bots;
   }
}
