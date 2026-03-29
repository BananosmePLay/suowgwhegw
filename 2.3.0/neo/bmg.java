package neo;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class bmg {
   public bmg() {
   }

   public static bmf[] parse(String str) throws IOException, bmd {
      Reader reader = new StringReader(str);
      PushbackReader pushbackreader = new PushbackReader(reader);
      List<bmf> list = new ArrayList();

      while(true) {
         int i = pushbackreader.read();
         if (i < 0) {
            bmf[] atoken = (bmf[])((bmf[])list.toArray(new bmf[list.size()]));
            return atoken;
         }

         char c0 = (char)i;
         if (!Character.isWhitespace(c0)) {
            bmi tokentype = bmi.getTypeByFirstChar(c0);
            if (tokentype == null) {
               throw new bmd("Invalid character: '" + c0 + "', in: " + str);
            }

            bmf token = readToken(c0, tokentype, pushbackreader);
            list.add(token);
         }
      }
   }

   private static bmf readToken(char chFirst, bmi type, PushbackReader pr) throws IOException {
      StringBuffer stringbuffer = new StringBuffer();
      stringbuffer.append(chFirst);

      while(true) {
         int i = pr.read();
         if (i < 0) {
            break;
         }

         char c0 = (char)i;
         if (!type.hasCharNext(c0)) {
            pr.unread(c0);
            break;
         }

         stringbuffer.append(c0);
      }

      return new bmf(type, stringbuffer.toString());
   }
}
