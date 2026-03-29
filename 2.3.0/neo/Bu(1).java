package neo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;

public class Bu {
   private final Bt shaderType;
   private final String shaderFilename;
   private final int shader;
   private int shaderAttachCount;

   private Bu(Bt type, int shaderId, String filename) {
      this.shaderType = type;
      this.shader = shaderId;
      this.shaderFilename = filename;
   }

   public void attachShader(Bv manager) {
      ++this.shaderAttachCount;
      ys.glAttachShader(manager.getProgram(), this.shader);
   }

   public void deleteShader(Bv manager) {
      --this.shaderAttachCount;
      if (this.shaderAttachCount <= 0) {
         ys.glDeleteShader(this.shader);
         Bt.access$000(this.shaderType).remove(this.shaderFilename);
      }

   }

   public String getShaderFilename() {
      return this.shaderFilename;
   }

   public static Bu loadShader(AA resourceManager, Bt type, String filename) throws IOException {
      Bu shaderloader = (Bu)Bt.access$000(type).get(filename);
      if (shaderloader == null) {
         ResourceLocation resourcelocation = new ResourceLocation("shaders/program/" + filename + Bt.access$100(type));
         Az iresource = resourceManager.getResource(resourcelocation);

         try {
            byte[] abyte = IOUtils.toByteArray(new BufferedInputStream(iresource.getInputStream()));
            ByteBuffer bytebuffer = BufferUtils.createByteBuffer(abyte.length);
            bytebuffer.put(abyte);
            bytebuffer.position(0);
            int i = ys.glCreateShader(Bt.access$200(type));
            ys.glShaderSource(i, bytebuffer);
            ys.glCompileShader(i);
            if (ys.glGetShaderi(i, ys.GL_COMPILE_STATUS) == 0) {
               String s = StringUtils.trim(ys.glGetShaderInfoLog(i, 32768));
               BN jsonexception = new BN("Couldn't compile " + type.getShaderName() + " program: " + s);
               jsonexception.setFilenameAndFlush(resourcelocation.getPath());
               throw jsonexception;
            }

            shaderloader = new Bu(type, i, filename);
            Bt.access$000(type).put(filename, shaderloader);
         } finally {
            IOUtils.closeQuietly(iresource);
         }
      }

      return shaderloader;
   }
}
