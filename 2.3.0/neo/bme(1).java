package neo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bme {
   public bme() {
   }

   public static void main(String[] args) throws Exception {
      blM expressionparser = new blM((blZ)null);

      while(true) {
         while(true) {
            try {
               InputStreamReader inputstreamreader = new InputStreamReader(System.in);
               BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
               String s = bufferedreader.readLine();
               if (s.length() <= 0) {
                  return;
               }

               blU iexpression = expressionparser.parse(s);
               if (iexpression instanceof blX) {
                  blX iexpressionfloat = (blX)iexpression;
                  float f = iexpressionfloat.eval();
                  System.out.println("" + f);
               }

               if (iexpression instanceof blV) {
                  blV iexpressionbool = (blV)iexpression;
                  boolean flag = iexpressionbool.eval();
                  System.out.println("" + flag);
               }
            } catch (Exception var8) {
               Exception exception = var8;
               exception.printStackTrace();
            }
         }
      }
   }
}
