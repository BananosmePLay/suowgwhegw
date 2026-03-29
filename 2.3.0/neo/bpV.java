package neo;

public enum bpV {
   BOOL,
   INT,
   FLOAT,
   VEC2,
   VEC3,
   VEC4;

   private bpV() {
   }

   public bpQ makeShaderUniform(String name) {
      switch (this) {
         case BOOL:
            return new bpK(name);
         case INT:
            return new bpK(name);
         case FLOAT:
            return new bpJ(name);
         case VEC2:
            return new bpL(name);
         case VEC3:
            return new bpN(name);
         case VEC4:
            return new bpO(name);
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public void updateUniform(blU expression, bpQ uniform) {
      switch (this) {
         case BOOL:
            this.updateUniformBool((blV)expression, (bpK)uniform);
            return;
         case INT:
            this.updateUniformInt((blX)expression, (bpK)uniform);
            return;
         case FLOAT:
            this.updateUniformFloat((blX)expression, (bpJ)uniform);
            return;
         case VEC2:
            this.updateUniformFloat2((blY)expression, (bpL)uniform);
            return;
         case VEC3:
            this.updateUniformFloat3((blY)expression, (bpN)uniform);
            return;
         case VEC4:
            this.updateUniformFloat4((blY)expression, (bpO)uniform);
            return;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   private void updateUniformBool(blV expression, bpK uniform) {
      boolean flag = expression.eval();
      int i = flag ? 1 : 0;
      uniform.setValue(i);
   }

   private void updateUniformInt(blX expression, bpK uniform) {
      int i = (int)expression.eval();
      uniform.setValue(i);
   }

   private void updateUniformFloat(blX expression, bpJ uniform) {
      float f = expression.eval();
      uniform.setValue(f);
   }

   private void updateUniformFloat2(blY expression, bpL uniform) {
      float[] afloat = expression.eval();
      if (afloat.length != 2) {
         throw new RuntimeException("Value length is not 2, length: " + afloat.length);
      } else {
         uniform.setValue(afloat[0], afloat[1]);
      }
   }

   private void updateUniformFloat3(blY expression, bpN uniform) {
      float[] afloat = expression.eval();
      if (afloat.length != 3) {
         throw new RuntimeException("Value length is not 3, length: " + afloat.length);
      } else {
         uniform.setValue(afloat[0], afloat[1], afloat[2]);
      }
   }

   private void updateUniformFloat4(blY expression, bpO uniform) {
      float[] afloat = expression.eval();
      if (afloat.length != 4) {
         throw new RuntimeException("Value length is not 4, length: " + afloat.length);
      } else {
         uniform.setValue(afloat[0], afloat[1], afloat[2], afloat[3]);
      }
   }

   public boolean matchesExpressionType(blN expressionType) {
      switch (this) {
         case BOOL:
            return expressionType == blN.BOOL;
         case INT:
            return expressionType == blN.FLOAT;
         case FLOAT:
            return expressionType == blN.FLOAT;
         case VEC2:
         case VEC3:
         case VEC4:
            return expressionType == blN.FLOAT_ARRAY;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public static bpV parse(String type) {
      bpV[] auniformtype = values();

      for(int i = 0; i < auniformtype.length; ++i) {
         bpV uniformtype = auniformtype[i];
         if (uniformtype.name().toLowerCase().equals(type)) {
            return uniformtype;
         }
      }

      return null;
   }
}
