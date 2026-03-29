package neo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class boj {
   public boj() {
   }

   public static InputStream process(InputStream in, String path) throws IOException {
      String s = XH.readInputStream(in, "ASCII");
      String s1 = getMacroHeader(s);
      String s3;
      if (!s1.isEmpty()) {
         s = s1 + s;
         if (bpq.saveFinalShaders) {
            s3 = path.replace(':', '/') + ".pre";
            bpq.saveShader(s3, s);
         }

         s = process(s);
      }

      if (bpq.saveFinalShaders) {
         s3 = path.replace(':', '/');
         bpq.saveShader(s3, s);
      }

      byte[] abyte = s.getBytes("ASCII");
      ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
      return bytearrayinputstream;
   }

   public static String process(String strIn) throws IOException {
      StringReader stringreader = new StringReader(strIn);
      BufferedReader bufferedreader = new BufferedReader(stringreader);
      bok macrostate = new bok();
      StringBuilder stringbuilder = new StringBuilder();

      while(true) {
         String s = bufferedreader.readLine();
         if (s == null) {
            s = stringbuilder.toString();
            return s;
         }

         if (macrostate.processLine(s) && !bok.isMacroLine(s)) {
            stringbuilder.append(s);
            stringbuilder.append("\n");
         }
      }
   }

   private static String getMacroHeader(String str) throws IOException {
      StringBuilder stringbuilder = new StringBuilder();
      List<bou> list = null;
      List<bor> list1 = null;
      StringReader stringreader = new StringReader(str);
      BufferedReader bufferedreader = new BufferedReader(stringreader);

      while(true) {
         String s;
         do {
            s = bufferedreader.readLine();
            if (s == null) {
               return stringbuilder.toString();
            }
         } while(!bok.isMacroLine(s));

         if (stringbuilder.length() == 0) {
            stringbuilder.append(bot.getFixedMacroLines());
         }

         if (list1 == null) {
            list1 = new ArrayList(Arrays.asList(bot.getExtensions()));
         }

         Iterator iterator = list1.iterator();

         while(iterator.hasNext()) {
            bor shadermacro = (bor)iterator.next();
            if (s.contains(shadermacro.getName())) {
               stringbuilder.append(shadermacro.getSourceLine());
               stringbuilder.append("\n");
               iterator.remove();
            }
         }
      }
   }

   private static List<bou> getMacroOptions() {
      List<bou> list = new ArrayList();
      bou[] ashaderoption = bpq.getShaderPackOptions();

      for(int i = 0; i < ashaderoption.length; ++i) {
         bou shaderoption = ashaderoption[i];
         String s = shaderoption.getSourceLine();
         if (s != null && s.startsWith("#")) {
            list.add(shaderoption);
         }
      }

      return list;
   }
}
