package neo;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.math.MathHelper;

public enum blT {
   PLUS(10, blN.FLOAT, "+", new blN[]{blN.FLOAT, blN.FLOAT}),
   MINUS(10, blN.FLOAT, "-", new blN[]{blN.FLOAT, blN.FLOAT}),
   MUL(11, blN.FLOAT, "*", new blN[]{blN.FLOAT, blN.FLOAT}),
   DIV(11, blN.FLOAT, "/", new blN[]{blN.FLOAT, blN.FLOAT}),
   MOD(11, blN.FLOAT, "%", new blN[]{blN.FLOAT, blN.FLOAT}),
   NEG(12, blN.FLOAT, "neg", new blN[]{blN.FLOAT}),
   PI(blN.FLOAT, "pi", new blN[0]),
   SIN(blN.FLOAT, "sin", new blN[]{blN.FLOAT}),
   COS(blN.FLOAT, "cos", new blN[]{blN.FLOAT}),
   ASIN(blN.FLOAT, "asin", new blN[]{blN.FLOAT}),
   ACOS(blN.FLOAT, "acos", new blN[]{blN.FLOAT}),
   TAN(blN.FLOAT, "tan", new blN[]{blN.FLOAT}),
   ATAN(blN.FLOAT, "atan", new blN[]{blN.FLOAT}),
   ATAN2(blN.FLOAT, "atan2", new blN[]{blN.FLOAT, blN.FLOAT}),
   TORAD(blN.FLOAT, "torad", new blN[]{blN.FLOAT}),
   TODEG(blN.FLOAT, "todeg", new blN[]{blN.FLOAT}),
   MIN(blN.FLOAT, "min", (new bmc()).first(blN.FLOAT).repeat(blN.FLOAT)),
   MAX(blN.FLOAT, "max", (new bmc()).first(blN.FLOAT).repeat(blN.FLOAT)),
   CLAMP(blN.FLOAT, "clamp", new blN[]{blN.FLOAT, blN.FLOAT, blN.FLOAT}),
   ABS(blN.FLOAT, "abs", new blN[]{blN.FLOAT}),
   FLOOR(blN.FLOAT, "floor", new blN[]{blN.FLOAT}),
   CEIL(blN.FLOAT, "ceil", new blN[]{blN.FLOAT}),
   EXP(blN.FLOAT, "exp", new blN[]{blN.FLOAT}),
   FRAC(blN.FLOAT, "frac", new blN[]{blN.FLOAT}),
   LOG(blN.FLOAT, "log", new blN[]{blN.FLOAT}),
   POW(blN.FLOAT, "pow", new blN[]{blN.FLOAT, blN.FLOAT}),
   RANDOM(blN.FLOAT, "random", new blN[0]),
   ROUND(blN.FLOAT, "round", new blN[]{blN.FLOAT}),
   SIGNUM(blN.FLOAT, "signum", new blN[]{blN.FLOAT}),
   SQRT(blN.FLOAT, "sqrt", new blN[]{blN.FLOAT}),
   FMOD(blN.FLOAT, "fmod", new blN[]{blN.FLOAT, blN.FLOAT}),
   TIME(blN.FLOAT, "time", new blN[0]),
   IF(blN.FLOAT, "if", (new bmc()).first(blN.BOOL, blN.FLOAT).repeat(blN.BOOL, blN.FLOAT).last(blN.FLOAT)),
   NOT(12, blN.BOOL, "!", new blN[]{blN.BOOL}),
   AND(3, blN.BOOL, "&&", new blN[]{blN.BOOL, blN.BOOL}),
   OR(2, blN.BOOL, "||", new blN[]{blN.BOOL, blN.BOOL}),
   GREATER(8, blN.BOOL, ">", new blN[]{blN.FLOAT, blN.FLOAT}),
   GREATER_OR_EQUAL(8, blN.BOOL, ">=", new blN[]{blN.FLOAT, blN.FLOAT}),
   SMALLER(8, blN.BOOL, "<", new blN[]{blN.FLOAT, blN.FLOAT}),
   SMALLER_OR_EQUAL(8, blN.BOOL, "<=", new blN[]{blN.FLOAT, blN.FLOAT}),
   EQUAL(7, blN.BOOL, "==", new blN[]{blN.FLOAT, blN.FLOAT}),
   NOT_EQUAL(7, blN.BOOL, "!=", new blN[]{blN.FLOAT, blN.FLOAT}),
   BETWEEN(7, blN.BOOL, "between", new blN[]{blN.FLOAT, blN.FLOAT, blN.FLOAT}),
   EQUALS(7, blN.BOOL, "equals", new blN[]{blN.FLOAT, blN.FLOAT, blN.FLOAT}),
   IN(blN.BOOL, "in", (new bmc()).first(blN.FLOAT).repeat(blN.FLOAT).last(blN.FLOAT)),
   SMOOTH(blN.FLOAT, "smooth", (new bmc()).first(blN.FLOAT).repeat(blN.FLOAT).maxCount(4)),
   TRUE(blN.BOOL, "true", new blN[0]),
   FALSE(blN.BOOL, "false", new blN[0]),
   VEC2(blN.FLOAT_ARRAY, "vec2", new blN[]{blN.FLOAT, blN.FLOAT}),
   VEC3(blN.FLOAT_ARRAY, "vec3", new blN[]{blN.FLOAT, blN.FLOAT, blN.FLOAT}),
   VEC4(blN.FLOAT_ARRAY, "vec4", new blN[]{blN.FLOAT, blN.FLOAT, blN.FLOAT, blN.FLOAT});

   private int precedence;
   private blN expressionType;
   private String name;
   private bma parameters;
   public static blT[] VALUES = values();
   private static final Map<Integer, Float> mapSmooth = new HashMap();

   private blT(blN expressionType, String name, blN... parameterTypes) {
      this(0, expressionType, name, (blN[])parameterTypes);
   }

   private blT(int precedence, blN expressionType, String name, blN... parameterTypes) {
      this(precedence, expressionType, name, (bma)(new bmb(parameterTypes)));
   }

   private blT(blN expressionType, String name, bma parameters) {
      this(0, expressionType, name, (bma)parameters);
   }

   private blT(int precedence, blN expressionType, String name, bma parameters) {
      this.precedence = precedence;
      this.expressionType = expressionType;
      this.name = name;
      this.parameters = parameters;
   }

   public String getName() {
      return this.name;
   }

   public int getPrecedence() {
      return this.precedence;
   }

   public blN getExpressionType() {
      return this.expressionType;
   }

   public bma getParameters() {
      return this.parameters;
   }

   public int getParameterCount(blU[] arguments) {
      return this.parameters.getParameterTypes(arguments).length;
   }

   public blN[] getParameterTypes(blU[] arguments) {
      return this.parameters.getParameterTypes(arguments);
   }

   public float evalFloat(blU[] args) {
      int k;
      switch (this) {
         case PLUS:
            return evalFloat(args, 0) + evalFloat(args, 1);
         case MINUS:
            return evalFloat(args, 0) - evalFloat(args, 1);
         case MUL:
            return evalFloat(args, 0) * evalFloat(args, 1);
         case DIV:
            return evalFloat(args, 0) / evalFloat(args, 1);
         case MOD:
            float f = evalFloat(args, 0);
            float f1 = evalFloat(args, 1);
            return f - f1 * (float)((int)(f / f1));
         case NEG:
            return -evalFloat(args, 0);
         case PI:
            return MathHelper.PI;
         case SIN:
            return MathHelper.sin(evalFloat(args, 0));
         case COS:
            return MathHelper.cos(evalFloat(args, 0));
         case ASIN:
            return bqD.asin(evalFloat(args, 0));
         case ACOS:
            return bqD.acos(evalFloat(args, 0));
         case TAN:
            return (float)Math.tan((double)evalFloat(args, 0));
         case ATAN:
            return (float)Math.atan((double)evalFloat(args, 0));
         case ATAN2:
            return (float)MathHelper.atan2((double)evalFloat(args, 0), (double)evalFloat(args, 1));
         case TORAD:
            return bqD.toRad(evalFloat(args, 0));
         case TODEG:
            return bqD.toDeg(evalFloat(args, 0));
         case MIN:
            return this.getMin(args);
         case MAX:
            return this.getMax(args);
         case CLAMP:
            return MathHelper.clamp(evalFloat(args, 0), evalFloat(args, 1), evalFloat(args, 2));
         case ABS:
            return MathHelper.abs(evalFloat(args, 0));
         case EXP:
            return (float)Math.exp((double)evalFloat(args, 0));
         case FLOOR:
            return (float)MathHelper.floor(evalFloat(args, 0));
         case CEIL:
            return (float)MathHelper.ceil(evalFloat(args, 0));
         case FRAC:
            return (float)MathHelper.frac((double)evalFloat(args, 0));
         case LOG:
            return (float)Math.log((double)evalFloat(args, 0));
         case POW:
            return (float)Math.pow((double)evalFloat(args, 0), (double)evalFloat(args, 1));
         case RANDOM:
            return (float)Math.random();
         case ROUND:
            return (float)Math.round(evalFloat(args, 0));
         case SIGNUM:
            return Math.signum(evalFloat(args, 0));
         case SQRT:
            return MathHelper.sqrt(evalFloat(args, 0));
         case FMOD:
            float f2 = evalFloat(args, 0);
            float f3 = evalFloat(args, 1);
            return f2 - f3 * (float)MathHelper.floor(f2 / f3);
         case TIME:
            nC minecraft = nC.getMinecraft();
            bij world = minecraft.world;
            if (world == null) {
               return 0.0F;
            }

            return (float)(((bij)world).getTotalWorldTime() % 24000L) + minecraft.getRenderPartialTicks();
         case IF:
            int i = (args.length - 1) / 2;

            for(k = 0; k < i; ++k) {
               int l = k * 2;
               if (evalBool(args, l)) {
                  return evalFloat(args, l + 1);
               }
            }

            return evalFloat(args, i * 2);
         case SMOOTH:
            k = (int)evalFloat(args, 0);
            float f4 = evalFloat(args, 1);
            float f5 = args.length > 2 ? evalFloat(args, 2) : 1.0F;
            float f6 = args.length > 3 ? evalFloat(args, 3) : f5;
            float f7 = bpT.getSmoothValue(k, f4, f5, f6);
            return f7;
         default:
            XH.warn("Unknown function type: " + this);
            return 0.0F;
      }
   }

   private float getMin(blU[] exprs) {
      if (exprs.length == 2) {
         return Math.min(evalFloat(exprs, 0), evalFloat(exprs, 1));
      } else {
         float f = evalFloat(exprs, 0);

         for(int i = 1; i < exprs.length; ++i) {
            float f1 = evalFloat(exprs, i);
            if (f1 < f) {
               f = f1;
            }
         }

         return f;
      }
   }

   private float getMax(blU[] exprs) {
      if (exprs.length == 2) {
         return Math.max(evalFloat(exprs, 0), evalFloat(exprs, 1));
      } else {
         float f = evalFloat(exprs, 0);

         for(int i = 1; i < exprs.length; ++i) {
            float f1 = evalFloat(exprs, i);
            if (f1 > f) {
               f = f1;
            }
         }

         return f;
      }
   }

   private static float evalFloat(blU[] exprs, int index) {
      blX iexpressionfloat = (blX)exprs[index];
      float f = iexpressionfloat.eval();
      return f;
   }

   public boolean evalBool(blU[] args) {
      switch (this) {
         case TRUE:
            return true;
         case FALSE:
            return false;
         case NOT:
            return !evalBool(args, 0);
         case AND:
            return evalBool(args, 0) && evalBool(args, 1);
         case OR:
            return evalBool(args, 0) || evalBool(args, 1);
         case GREATER:
            return evalFloat(args, 0) > evalFloat(args, 1);
         case GREATER_OR_EQUAL:
            return evalFloat(args, 0) >= evalFloat(args, 1);
         case SMALLER:
            return evalFloat(args, 0) < evalFloat(args, 1);
         case SMALLER_OR_EQUAL:
            return evalFloat(args, 0) <= evalFloat(args, 1);
         case EQUAL:
            return evalFloat(args, 0) == evalFloat(args, 1);
         case NOT_EQUAL:
            return evalFloat(args, 0) != evalFloat(args, 1);
         case BETWEEN:
            float f = evalFloat(args, 0);
            return f >= evalFloat(args, 1) && f <= evalFloat(args, 2);
         case EQUALS:
            float f1 = evalFloat(args, 0) - evalFloat(args, 1);
            float f2 = evalFloat(args, 2);
            return Math.abs(f1) <= f2;
         case IN:
            float f3 = evalFloat(args, 0);

            for(int i = 1; i < args.length; ++i) {
               float f4 = evalFloat(args, i);
               if (f3 == f4) {
                  return true;
               }
            }

            return false;
         default:
            XH.warn("Unknown function type: " + this);
            return false;
      }
   }

   private static boolean evalBool(blU[] exprs, int index) {
      blV iexpressionbool = (blV)exprs[index];
      boolean flag = iexpressionbool.eval();
      return flag;
   }

   public float[] evalFloatArray(blU[] args) {
      switch (this) {
         case VEC2:
            return new float[]{evalFloat(args, 0), evalFloat(args, 1)};
         case VEC3:
            return new float[]{evalFloat(args, 0), evalFloat(args, 1), evalFloat(args, 2)};
         case VEC4:
            return new float[]{evalFloat(args, 0), evalFloat(args, 1), evalFloat(args, 2), evalFloat(args, 3)};
         default:
            XH.warn("Unknown function type: " + this);
            return null;
      }
   }

   public static blT parse(String str) {
      for(int i = 0; i < VALUES.length; ++i) {
         blT functiontype = VALUES[i];
         if (functiontype.getName().equals(str)) {
            return functiontype;
         }
      }

      return null;
   }
}
