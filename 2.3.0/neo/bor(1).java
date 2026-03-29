package neo;

public class bor {
   private String name;
   private String value;

   public bor(String name, String value) {
      this.name = name;
      this.value = value;
   }

   public String getName() {
      return this.name;
   }

   public String getValue() {
      return this.value;
   }

   public String getSourceLine() {
      return "#define " + this.name + " " + this.value;
   }

   public String toString() {
      return this.getSourceLine();
   }
}
