package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bv {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Bq DEFAULT_SHADER_UNIFORM = new Bq();
   private static Bv staticShaderManager;
   private static int currentProgram = -1;
   private static boolean lastCull = true;
   private final Map<String, Object> shaderSamplers = Maps.newHashMap();
   private final List<String> samplerNames = Lists.newArrayList();
   private final List<Integer> shaderSamplerLocations = Lists.newArrayList();
   private final List<Bw> shaderUniforms = Lists.newArrayList();
   private final List<Integer> shaderUniformLocations = Lists.newArrayList();
   private final Map<String, Bw> mappedShaderUniforms = Maps.newHashMap();
   private final int program;
   private final String programFilename;
   private final boolean useFaceCulling;
   private boolean isDirty;
   private final BK blendingMode;
   private final List<Integer> attribLocations;
   private final List<String> attributes;
   private final Bu vertexShaderLoader;
   private final Bu fragmentShaderLoader;

   public Bv(AA resourceManager, String programName) throws BN, IOException {
      JsonParser jsonparser = new JsonParser();
      ResourceLocation resourcelocation = new ResourceLocation("shaders/program/" + programName + ".json");
      this.programFilename = programName;
      Az iresource = null;

      try {
         iresource = resourceManager.getResource(resourcelocation);
         JsonObject jsonobject = jsonparser.parse(IOUtils.toString(iresource.getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
         String s = JsonUtils.getString(jsonobject, "vertex");
         String s1 = JsonUtils.getString(jsonobject, "fragment");
         JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "samplers", (JsonArray)null);
         if (jsonarray != null) {
            int i = 0;

            for(Iterator var11 = jsonarray.iterator(); var11.hasNext(); ++i) {
               JsonElement jsonelement = (JsonElement)var11.next();

               try {
                  this.parseSampler(jsonelement);
               } catch (Exception var25) {
                  Exception exception2 = var25;
                  BN jsonexception1 = BN.forException(exception2);
                  jsonexception1.prependJsonKey("samplers[" + i + "]");
                  throw jsonexception1;
               }
            }
         }

         JsonArray jsonarray1 = JsonUtils.getJsonArray(jsonobject, "attributes", (JsonArray)null);
         Iterator var33;
         if (jsonarray1 != null) {
            int j = 0;
            this.attribLocations = Lists.newArrayListWithCapacity(jsonarray1.size());
            this.attributes = Lists.newArrayListWithCapacity(jsonarray1.size());

            for(var33 = jsonarray1.iterator(); var33.hasNext(); ++j) {
               JsonElement jsonelement1 = (JsonElement)var33.next();

               try {
                  this.attributes.add(JsonUtils.getString(jsonelement1, "attribute"));
               } catch (Exception var24) {
                  BN jsonexception2 = BN.forException(var24);
                  jsonexception2.prependJsonKey("attributes[" + j + "]");
                  throw jsonexception2;
               }
            }
         } else {
            this.attribLocations = null;
            this.attributes = null;
         }

         JsonArray jsonarray2 = JsonUtils.getJsonArray(jsonobject, "uniforms", (JsonArray)null);
         if (jsonarray2 != null) {
            int k = 0;

            for(Iterator var36 = jsonarray2.iterator(); var36.hasNext(); ++k) {
               JsonElement jsonelement2 = (JsonElement)var36.next();

               try {
                  this.parseUniform(jsonelement2);
               } catch (Exception var23) {
                  BN jsonexception3 = BN.forException(var23);
                  jsonexception3.prependJsonKey("uniforms[" + k + "]");
                  throw jsonexception3;
               }
            }
         }

         this.blendingMode = BK.parseBlendNode(JsonUtils.getJsonObject(jsonobject, "blend", (JsonObject)null));
         this.useFaceCulling = JsonUtils.getBoolean(jsonobject, "cull", true);
         this.vertexShaderLoader = Bu.loadShader(resourceManager, Bt.VERTEX, s);
         this.fragmentShaderLoader = Bu.loadShader(resourceManager, Bt.FRAGMENT, s1);
         this.program = Bs.getStaticShaderLinkHelper().createProgram();
         Bs.getStaticShaderLinkHelper().linkProgram(this);
         this.setupUniforms();
         if (this.attributes != null) {
            var33 = this.attributes.iterator();

            while(var33.hasNext()) {
               String s2 = (String)var33.next();
               int l = ys.glGetAttribLocation(this.program, s2);
               this.attribLocations.add(l);
            }
         }
      } catch (Exception var26) {
         Exception exception3 = var26;
         BN jsonexception = BN.forException(exception3);
         jsonexception.setFilenameAndFlush(resourcelocation.getPath());
         throw jsonexception;
      } finally {
         IOUtils.closeQuietly(iresource);
      }

      this.markDirty();
   }

   public void deleteShader() {
      Bs.getStaticShaderLinkHelper().deleteShader(this);
   }

   public void endShader() {
      ys.glUseProgram(0);
      currentProgram = -1;
      staticShaderManager = null;
      lastCull = true;

      for(int i = 0; i < this.shaderSamplerLocations.size(); ++i) {
         if (this.shaderSamplers.get(this.samplerNames.get(i)) != null) {
            yh.setActiveTexture(ys.defaultTexUnit + i);
            yh.bindTexture(0);
         }
      }

   }

   public void useShader() {
      this.isDirty = false;
      staticShaderManager = this;
      this.blendingMode.apply();
      if (this.program != currentProgram) {
         ys.glUseProgram(this.program);
         currentProgram = this.program;
      }

      if (this.useFaceCulling) {
         yh.enableCull();
      } else {
         yh.disableCull();
      }

      for(int i = 0; i < this.shaderSamplerLocations.size(); ++i) {
         if (this.shaderSamplers.get(this.samplerNames.get(i)) != null) {
            yh.setActiveTexture(ys.defaultTexUnit + i);
            yh.enableTexture2D();
            Object object = this.shaderSamplers.get(this.samplerNames.get(i));
            int j = -1;
            if (object instanceof Bn) {
               j = ((Bn)object).framebufferTexture;
            } else if (object instanceof yR) {
               j = ((yR)object).getGlTextureId();
            } else if (object instanceof Integer) {
               j = (Integer)object;
            }

            if (j != -1) {
               yh.bindTexture(j);
               ys.glUniform1i(ys.glGetUniformLocation(this.program, (CharSequence)this.samplerNames.get(i)), i);
            }
         }
      }

      Iterator var4 = this.shaderUniforms.iterator();

      while(var4.hasNext()) {
         Bw shaderuniform = (Bw)var4.next();
         shaderuniform.upload();
      }

   }

   public void markDirty() {
      this.isDirty = true;
   }

   @Nullable
   public Bw getShaderUniform(String name) {
      return (Bw)this.mappedShaderUniforms.get(name);
   }

   public Bw getShaderUniformOrDefault(String name) {
      Bw shaderuniform = this.getShaderUniform(name);
      return (Bw)(shaderuniform == null ? DEFAULT_SHADER_UNIFORM : shaderuniform);
   }

   private void setupUniforms() {
      int i = 0;

      for(int j = 0; i < this.samplerNames.size(); ++j) {
         String s = (String)this.samplerNames.get(i);
         int k = ys.glGetUniformLocation(this.program, s);
         if (k == -1) {
            LOGGER.warn("Shader {}could not find sampler named {} in the specified shader program.", this.programFilename, s);
            this.shaderSamplers.remove(s);
            this.samplerNames.remove(j);
            --j;
         } else {
            this.shaderSamplerLocations.add(k);
         }

         ++i;
      }

      Iterator var6 = this.shaderUniforms.iterator();

      while(var6.hasNext()) {
         Bw shaderuniform = (Bw)var6.next();
         String s1 = shaderuniform.getShaderName();
         int l = ys.glGetUniformLocation(this.program, s1);
         if (l == -1) {
            LOGGER.warn("Could not find uniform named {} in the specified shader program.", s1);
         } else {
            this.shaderUniformLocations.add(l);
            shaderuniform.setUniformLocation(l);
            this.mappedShaderUniforms.put(s1, shaderuniform);
         }
      }

   }

   private void parseSampler(JsonElement element) throws BN {
      JsonObject jsonobject = JsonUtils.getJsonObject(element, "sampler");
      String s = JsonUtils.getString(jsonobject, "name");
      if (!JsonUtils.isString(jsonobject, "file")) {
         this.shaderSamplers.put(s, (Object)null);
         this.samplerNames.add(s);
      } else {
         this.samplerNames.add(s);
      }

   }

   public void addSamplerTexture(String name, Object samplerTexture) {
      if (this.shaderSamplers.containsKey(name)) {
         this.shaderSamplers.remove(name);
      }

      this.shaderSamplers.put(name, samplerTexture);
      this.markDirty();
   }

   private void parseUniform(JsonElement element) throws BN {
      JsonObject jsonobject = JsonUtils.getJsonObject(element, "uniform");
      String s = JsonUtils.getString(jsonobject, "name");
      int i = Bw.parseType(JsonUtils.getString(jsonobject, "type"));
      int j = JsonUtils.getInt(jsonobject, "count");
      float[] afloat = new float[Math.max(j, 16)];
      JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "values");
      if (jsonarray.size() != j && jsonarray.size() > 1) {
         throw new BN("Invalid amount of values specified (expected " + j + ", found " + jsonarray.size() + ")");
      } else {
         int k = 0;

         for(Iterator var9 = jsonarray.iterator(); var9.hasNext(); ++k) {
            JsonElement jsonelement = (JsonElement)var9.next();

            try {
               afloat[k] = JsonUtils.getFloat(jsonelement, "value");
            } catch (Exception var13) {
               Exception exception = var13;
               BN jsonexception = BN.forException(exception);
               jsonexception.prependJsonKey("values[" + k + "]");
               throw jsonexception;
            }
         }

         if (j > 1 && jsonarray.size() == 1) {
            while(k < j) {
               afloat[k] = afloat[0];
               ++k;
            }
         }

         int l = j > 1 && j <= 4 && i < 8 ? j - 1 : 0;
         Bw shaderuniform = new Bw(s, i + l, j, this);
         if (i <= 3) {
            shaderuniform.set((int)afloat[0], (int)afloat[1], (int)afloat[2], (int)afloat[3]);
         } else if (i <= 7) {
            shaderuniform.setSafe(afloat[0], afloat[1], afloat[2], afloat[3]);
         } else {
            shaderuniform.set(afloat);
         }

         this.shaderUniforms.add(shaderuniform);
      }
   }

   public Bu getVertexShaderLoader() {
      return this.vertexShaderLoader;
   }

   public Bu getFragmentShaderLoader() {
      return this.fragmentShaderLoader;
   }

   public int getProgram() {
      return this.program;
   }
}
