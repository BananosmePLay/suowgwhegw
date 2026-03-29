package neo;

public enum bmi {
   IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
   NUMBER("0123456789", "0123456789."),
   OPERATOR("+-*/%!&|<>=", "&|="),
   COMMA(","),
   BRACKET_OPEN("("),
   BRACKET_CLOSE(")");

   private String charsFirst;
   private String charsNext;
   public static final bmi[] VALUES = values();

   private bmi(String charsFirst) {
      this(charsFirst, "");
   }

   private bmi(String charsFirst, String charsNext) {
      this.charsFirst = charsFirst;
      this.charsNext = charsNext;
   }

   public String getCharsFirst() {
      return this.charsFirst;
   }

   public String getCharsNext() {
      return this.charsNext;
   }

   public static bmi getTypeByFirstChar(char ch) {
      for(int i = 0; i < VALUES.length; ++i) {
         bmi tokentype = VALUES[i];
         if (tokentype.getCharsFirst().indexOf(ch) >= 0) {
            return tokentype;
         }
      }

      return null;
   }

   public boolean hasCharNext(char ch) {
      return this.charsNext.indexOf(ch) >= 0;
   }
}
