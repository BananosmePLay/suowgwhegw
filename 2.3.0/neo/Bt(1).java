package neo;

import com.google.common.collect.Maps;
import java.util.Map;

public enum Bt {
   VERTEX("vertex", ".vsh", ys.GL_VERTEX_SHADER),
   FRAGMENT("fragment", ".fsh", ys.GL_FRAGMENT_SHADER);

   private final String shaderName;
   private final String shaderExtension;
   private final int shaderMode;
   private final Map<String, Bu> loadedShaders = Maps.newHashMap();

   private Bt(String shaderNameIn, String shaderExtensionIn, int shaderModeIn) {
      this.shaderName = shaderNameIn;
      this.shaderExtension = shaderExtensionIn;
      this.shaderMode = shaderModeIn;
   }

   public String getShaderName() {
      return this.shaderName;
   }

   private String getShaderExtension() {
      return this.shaderExtension;
   }

   private int getShaderMode() {
      return this.shaderMode;
   }

   private Map<String, Bu> getLoadedShaders() {
      return this.loadedShaders;
   }

   // $FF: synthetic method
   static Map access$000(Bt x0) {
      return x0.getLoadedShaders();
   }

   // $FF: synthetic method
   static String access$100(Bt x0) {
      return x0.getShaderExtension();
   }

   // $FF: synthetic method
   static int access$200(Bt x0) {
      return x0.getShaderMode();
   }
}
