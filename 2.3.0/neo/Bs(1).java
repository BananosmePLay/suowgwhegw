package neo;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bs {
   private static final Logger LOGGER = LogManager.getLogger();
   private static Bs staticShaderLinkHelper;

   public Bs() {
   }

   public static void setNewStaticShaderLinkHelper() {
      staticShaderLinkHelper = new Bs();
   }

   public static Bs getStaticShaderLinkHelper() {
      return staticShaderLinkHelper;
   }

   public void deleteShader(Bv manager) {
      manager.getFragmentShaderLoader().deleteShader(manager);
      manager.getVertexShaderLoader().deleteShader(manager);
      ys.glDeleteProgram(manager.getProgram());
   }

   public int createProgram() throws BN {
      int i = ys.glCreateProgram();
      if (i <= 0) {
         throw new BN("Could not create shader program (returned program ID " + i + ")");
      } else {
         return i;
      }
   }

   public void linkProgram(Bv manager) throws IOException {
      manager.getFragmentShaderLoader().attachShader(manager);
      manager.getVertexShaderLoader().attachShader(manager);
      ys.glLinkProgram(manager.getProgram());
      int i = ys.glGetProgrami(manager.getProgram(), ys.GL_LINK_STATUS);
      if (i == 0) {
         LOGGER.warn("Error encountered when linking program containing VS {} and FS {}. Log output:", manager.getVertexShaderLoader().getShaderFilename(), manager.getFragmentShaderLoader().getShaderFilename());
         LOGGER.warn(ys.glGetProgramInfoLog(manager.getProgram(), 32768));
      }

   }
}
