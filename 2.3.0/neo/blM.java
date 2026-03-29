package neo;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class blM {
   private blZ expressionResolver;

   public blM(blZ expressionResolver) {
      this.expressionResolver = expressionResolver;
   }

   public blX parseFloat(String str) throws bmd {
      blU iexpression = this.parse(str);
      if (!(iexpression instanceof blX)) {
         throw new bmd("Not a float expression: " + iexpression.getExpressionType());
      } else {
         return (blX)iexpression;
      }
   }

   public blV parseBool(String str) throws bmd {
      blU iexpression = this.parse(str);
      if (!(iexpression instanceof blV)) {
         throw new bmd("Not a boolean expression: " + iexpression.getExpressionType());
      } else {
         return (blV)iexpression;
      }
   }

   public blU parse(String str) throws bmd {
      try {
         bmf[] atoken = bmg.parse(str);
         if (atoken == null) {
            return null;
         } else {
            Deque<bmf> deque = new ArrayDeque(Arrays.asList(atoken));
            return this.parseInfix(deque);
         }
      } catch (IOException var4) {
         IOException ioexception = var4;
         throw new bmd(ioexception.getMessage(), ioexception);
      }
   }

   private blU parseInfix(Deque<bmf> deque) throws bmd {
      if (deque.isEmpty()) {
         return null;
      } else {
         List<blU> list = new LinkedList();
         List<bmf> list1 = new LinkedList();
         blU iexpression = this.parseExpression(deque);
         checkNull(iexpression, "Missing expression");
         list.add(iexpression);

         while(true) {
            bmf token = (bmf)deque.poll();
            if (token == null) {
               return this.makeInfix(list, list1);
            }

            if (token.getType() != bmi.OPERATOR) {
               throw new bmd("Invalid operator: " + token);
            }

            blU iexpression1 = this.parseExpression(deque);
            checkNull(iexpression1, "Missing expression");
            list1.add(token);
            list.add(iexpression1);
         }
      }
   }

   private blU makeInfix(List<blU> listExpr, List<bmf> listOper) throws bmd {
      List<blT> list = new LinkedList();
      Iterator var4 = listOper.iterator();

      while(var4.hasNext()) {
         bmf token = (bmf)var4.next();
         blT functiontype = blT.parse(token.getText());
         checkNull(functiontype, "Invalid operator: " + token);
         list.add(functiontype);
      }

      return this.makeInfixFunc(listExpr, list);
   }

   private blU makeInfixFunc(List<blU> listExpr, List<blT> listFunc) throws bmd {
      if (listExpr.size() != listFunc.size() + 1) {
         throw new bmd("Invalid infix expression, expressions: " + listExpr.size() + ", operators: " + listFunc.size());
      } else if (listExpr.size() == 1) {
         return (blU)listExpr.get(0);
      } else {
         int i = Integer.MAX_VALUE;
         int j = Integer.MIN_VALUE;

         blT functiontype;
         for(Iterator var5 = listFunc.iterator(); var5.hasNext(); j = Math.max(functiontype.getPrecedence(), j)) {
            functiontype = (blT)var5.next();
            i = Math.min(functiontype.getPrecedence(), i);
         }

         if (j >= i && j - i <= 10) {
            for(int k = j; k >= i; --k) {
               this.mergeOperators(listExpr, listFunc, k);
            }

            if (listExpr.size() == 1 && listFunc.size() == 0) {
               return (blU)listExpr.get(0);
            } else {
               throw new bmd("Error merging operators, expressions: " + listExpr.size() + ", operators: " + listFunc.size());
            }
         } else {
            throw new bmd("Invalid infix precedence, min: " + i + ", max: " + j);
         }
      }
   }

   private void mergeOperators(List<blU> listExpr, List<blT> listFuncs, int precedence) throws bmd {
      for(int i = 0; i < listFuncs.size(); ++i) {
         blT functiontype = (blT)listFuncs.get(i);
         if (functiontype.getPrecedence() == precedence) {
            listFuncs.remove(i);
            blU iexpression = (blU)listExpr.remove(i);
            blU iexpression1 = (blU)listExpr.remove(i);
            blU iexpression2 = makeFunction(functiontype, new blU[]{iexpression, iexpression1});
            listExpr.add(i, iexpression2);
            --i;
         }
      }

   }

   private blU parseExpression(Deque<bmf> deque) throws bmd {
      bmf token = (bmf)deque.poll();
      checkNull(token, "Missing expression");
      switch (token.getType()) {
         case NUMBER:
            return makeConstantFloat(token);
         case IDENTIFIER:
            blT functiontype = this.getFunctionType(token, deque);
            if (functiontype != null) {
               return this.makeFunction(functiontype, deque);
            }

            return this.makeVariable(token);
         case BRACKET_OPEN:
            return this.makeBracketed(token, deque);
         case OPERATOR:
            blT functiontype1 = blT.parse(token.getText());
            checkNull(functiontype1, "Invalid operator: " + token);
            if (functiontype1 == blT.PLUS) {
               return this.parseExpression(deque);
            } else {
               blU iexpression;
               if (functiontype1 == blT.MINUS) {
                  iexpression = this.parseExpression(deque);
                  return makeFunction(blT.NEG, new blU[]{iexpression});
               } else if (functiontype1 == blT.NOT) {
                  iexpression = this.parseExpression(deque);
                  return makeFunction(blT.NOT, new blU[]{iexpression});
               }
            }
         default:
            throw new bmd("Invalid expression: " + token);
      }
   }

   private static blU makeConstantFloat(bmf token) throws bmd {
      float f = XH.parseFloat(token.getText(), Float.NaN);
      if (f == Float.NaN) {
         throw new bmd("Invalid float value: " + token);
      } else {
         return new blI(f);
      }
   }

   private blT getFunctionType(bmf token, Deque<bmf> deque) throws bmd {
      bmf tokenNext = (bmf)deque.peek();
      blT type;
      if (tokenNext != null && tokenNext.getType() == bmi.BRACKET_OPEN) {
         type = blT.parse(token.getText());
         checkNull(type, "Unknown function: " + token);
         return type;
      } else {
         type = blT.parse(token.getText());
         if (type == null) {
            return null;
         } else if (type.getParameterCount(new blU[0]) > 0) {
            throw new bmd("Missing arguments: " + type);
         } else {
            return type;
         }
      }
   }

   private blU makeFunction(blT type, Deque<bmf> deque) throws bmd {
      bmf tokenNext;
      if (type.getParameterCount(new blU[0]) != 0 || (tokenNext = (bmf)deque.peek()) != null && tokenNext.getType() == bmi.BRACKET_OPEN) {
         bmf tokenOpen = (bmf)deque.poll();
         Deque<bmf> dequeBracketed = getGroup(deque, bmi.BRACKET_CLOSE, true);
         blU[] exprs = this.parseExpressions(dequeBracketed);
         return makeFunction(type, exprs);
      } else {
         return makeFunction(type, new blU[0]);
      }
   }

   private blU[] parseExpressions(Deque<bmf> deque) throws bmd {
      ArrayList<blU> list = new ArrayList();

      blU expr;
      while((expr = this.parseInfix(getGroup(deque, bmi.COMMA, false))) != null) {
         list.add(expr);
      }

      blU[] exprs = (blU[])list.toArray(new blU[list.size()]);
      return exprs;
   }

   private static blU makeFunction(blT type, blU[] args) throws bmd {
      blN[] aexpressiontype = type.getParameterTypes(args);
      if (args.length != aexpressiontype.length) {
         throw new bmd("Invalid number of arguments, function: \"" + type.getName() + "\", count arguments: " + args.length + ", should be: " + aexpressiontype.length);
      } else {
         for(int i = 0; i < args.length; ++i) {
            blU iexpression = args[i];
            blN expressiontype = iexpression.getExpressionType();
            blN expressiontype1 = aexpressiontype[i];
            if (expressiontype != expressiontype1) {
               throw new bmd("Invalid argument type, function: \"" + type.getName() + "\", index: " + i + ", type: " + expressiontype + ", should be: " + expressiontype1);
            }
         }

         if (type.getExpressionType() == blN.FLOAT) {
            return new blQ(type, args);
         } else if (type.getExpressionType() == blN.BOOL) {
            return new blO(type, args);
         } else if (type.getExpressionType() == blN.FLOAT_ARRAY) {
            return new blR(type, args);
         } else {
            throw new bmd("Unknown function type: " + type.getExpressionType() + ", function: " + type.getName());
         }
      }
   }

   private blU makeVariable(bmf token) throws bmd {
      if (this.expressionResolver == null) {
         throw new bmd("Model variable not found: " + token);
      } else {
         blU iexpression = this.expressionResolver.getExpression(token.getText());
         if (iexpression == null) {
            throw new bmd("Model variable not found: " + token);
         } else {
            return iexpression;
         }
      }
   }

   private blU makeBracketed(bmf token, Deque<bmf> deque) throws bmd {
      Deque<bmf> dequeBracketed = getGroup(deque, bmi.BRACKET_CLOSE, true);
      return this.parseInfix(dequeBracketed);
   }

   private static Deque<bmf> getGroup(Deque<bmf> deque, bmi tokenTypeEnd, boolean tokenEndRequired) throws bmd {
      ArrayDeque<bmf> dequeGroup = new ArrayDeque();
      int level = 0;
      Iterator<bmf> it = deque.iterator();

      while(it.hasNext()) {
         bmf token = (bmf)it.next();
         it.remove();
         if (level == 0 && token.getType() == tokenTypeEnd) {
            return dequeGroup;
         }

         dequeGroup.add(token);
         if (token.getType() == bmi.BRACKET_OPEN) {
            ++level;
         }

         if (token.getType() == bmi.BRACKET_CLOSE) {
            --level;
         }
      }

      if (tokenEndRequired) {
         throw new bmd("Missing end token: " + tokenTypeEnd);
      } else {
         return dequeGroup;
      }
   }

   private static void checkNull(Object obj, String message) throws bmd {
      if (obj == null) {
         throw new bmd(message);
      }
   }
}
