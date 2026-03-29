package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class Dx {
   private final Dv[] entries;

   public Dx(Dv[] entriesIn) {
      this.entries = entriesIn;
   }

   public Dv[] getEntries() {
      return this.entries;
   }

   public static Dx create(cf functionManagerIn, List<String> commands) {
      List<Dv> list = Lists.newArrayListWithCapacity(commands.size());
      Iterator var3 = commands.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         s = s.trim();
         if (!s.startsWith("#") && !s.isEmpty()) {
            String[] astring = s.split(" ", 2);
            String s1 = astring[0];
            if (!functionManagerIn.getCommandManager().getCommands().containsKey(s1)) {
               if (s1.startsWith("//")) {
                  throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "' (if you intended to make a comment, use '#' not '//')");
               }

               if (s1.startsWith("/") && s1.length() > 1) {
                  throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "' (did you mean '" + s1.substring(1) + "'? Do not use a preceding forwards slash.)");
               }

               throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "'");
            }

            list.add(new Du(s));
         }
      }

      return new Dx((Dv[])((Dv[])list.toArray(new Dv[list.size()])));
   }
}
