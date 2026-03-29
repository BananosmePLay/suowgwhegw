package neo;

public class bop {
   private String name;
   private bou[] shaderOptions;
   private int columns;

   public bop(String name, bou[] shaderOptions, int columns) {
      this.name = name;
      this.shaderOptions = shaderOptions;
      this.columns = columns;
   }

   public String getName() {
      return this.name;
   }

   public bou[] getShaderOptions() {
      return this.shaderOptions;
   }

   public int getColumns() {
      return this.columns;
   }
}
