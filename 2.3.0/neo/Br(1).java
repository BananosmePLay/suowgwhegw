package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.util.vector.Matrix4f;

public class Br {
   private final Bn mainFramebuffer;
   private final AA resourceManager;
   private final String shaderGroupName;
   private final List<Bp> listShaders = Lists.newArrayList();
   private final Map<String, Bn> mapFramebuffers = Maps.newHashMap();
   private final List<Bn> listFramebuffers = Lists.newArrayList();
   private Matrix4f projectionMatrix;
   private int mainFramebufferWidth;
   private int mainFramebufferHeight;
   private float time;
   private float lastStamp;

   public Br(zf p_i1050_1_, AA resourceManagerIn, Bn mainFramebufferIn, ResourceLocation p_i1050_4_) throws BN, IOException, JsonSyntaxException {
      this.resourceManager = resourceManagerIn;
      this.mainFramebuffer = mainFramebufferIn;
      this.time = 0.0F;
      this.lastStamp = 0.0F;
      this.mainFramebufferWidth = mainFramebufferIn.framebufferWidth;
      this.mainFramebufferHeight = mainFramebufferIn.framebufferHeight;
      this.shaderGroupName = p_i1050_4_.toString();
      this.resetProjectionMatrix();
      this.parseGroup(p_i1050_1_, p_i1050_4_);
   }

   public void parseGroup(zf p_152765_1_, ResourceLocation p_152765_2_) throws BN, IOException, JsonSyntaxException {
      JsonParser jsonparser = new JsonParser();
      Az iresource = null;

      try {
         iresource = this.resourceManager.getResource(p_152765_2_);
         JsonObject jsonobject = jsonparser.parse(IOUtils.toString(iresource.getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
         int j;
         Iterator var8;
         JsonElement jsonelement1;
         Exception exception;
         BN jsonexception2;
         JsonArray jsonarray1;
         if (JsonUtils.isJsonArray(jsonobject, "targets")) {
            jsonarray1 = jsonobject.getAsJsonArray("targets");
            j = 0;

            for(var8 = jsonarray1.iterator(); var8.hasNext(); ++j) {
               jsonelement1 = (JsonElement)var8.next();

               try {
                  this.initTarget(jsonelement1);
               } catch (Exception var18) {
                  exception = var18;
                  jsonexception2 = BN.forException(exception);
                  jsonexception2.prependJsonKey("targets[" + j + "]");
                  throw jsonexception2;
               }
            }
         }

         if (JsonUtils.isJsonArray(jsonobject, "passes")) {
            jsonarray1 = jsonobject.getAsJsonArray("passes");
            j = 0;

            for(var8 = jsonarray1.iterator(); var8.hasNext(); ++j) {
               jsonelement1 = (JsonElement)var8.next();

               try {
                  this.parsePass(p_152765_1_, jsonelement1);
               } catch (Exception var17) {
                  exception = var17;
                  jsonexception2 = BN.forException(exception);
                  jsonexception2.prependJsonKey("passes[" + j + "]");
                  throw jsonexception2;
               }
            }
         }
      } catch (Exception var19) {
         Exception exception2 = var19;
         BN jsonexception = BN.forException(exception2);
         jsonexception.setFilenameAndFlush(p_152765_2_.getPath());
         throw jsonexception;
      } finally {
         IOUtils.closeQuietly(iresource);
      }

   }

   private void initTarget(JsonElement p_148027_1_) throws BN {
      if (JsonUtils.isString(p_148027_1_)) {
         this.addFramebuffer(p_148027_1_.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
      } else {
         JsonObject jsonobject = JsonUtils.getJsonObject(p_148027_1_, "target");
         String s = JsonUtils.getString(jsonobject, "name");
         int i = JsonUtils.getInt(jsonobject, "width", this.mainFramebufferWidth);
         int j = JsonUtils.getInt(jsonobject, "height", this.mainFramebufferHeight);
         if (this.mapFramebuffers.containsKey(s)) {
            throw new BN(s + " is already defined");
         }

         this.addFramebuffer(s, i, j);
      }

   }

   private void parsePass(zf p_152764_1_, JsonElement json) throws BN, IOException {
      JsonObject jsonobject = JsonUtils.getJsonObject(json, "pass");
      String s = JsonUtils.getString(jsonobject, "name");
      String s1 = JsonUtils.getString(jsonobject, "intarget");
      String s2 = JsonUtils.getString(jsonobject, "outtarget");
      Bn framebuffer = this.getFramebuffer(s1);
      Bn framebuffer1 = this.getFramebuffer(s2);
      if (framebuffer == null) {
         throw new BN("Input target '" + s1 + "' does not exist");
      } else if (framebuffer1 == null) {
         throw new BN("Output target '" + s2 + "' does not exist");
      } else {
         Bp shader = this.addShader(s, framebuffer, framebuffer1);
         JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "auxtargets", (JsonArray)null);
         if (jsonarray != null) {
            int i = 0;

            for(Iterator var12 = jsonarray.iterator(); var12.hasNext(); ++i) {
               JsonElement jsonelement = (JsonElement)var12.next();

               try {
                  JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonelement, "auxtarget");
                  String s4 = JsonUtils.getString(jsonobject1, "name");
                  String s3 = JsonUtils.getString(jsonobject1, "id");
                  Bn framebuffer2 = this.getFramebuffer(s3);
                  if (framebuffer2 == null) {
                     ResourceLocation resourcelocation = new ResourceLocation("textures/effect/" + s3 + ".png");
                     Az iresource = null;

                     try {
                        iresource = this.resourceManager.getResource(resourcelocation);
                     } catch (FileNotFoundException var29) {
                        throw new BN("Render target or texture '" + s3 + "' does not exist");
                     } finally {
                        IOUtils.closeQuietly(iresource);
                     }

                     p_152764_1_.bindTexture(resourcelocation);
                     yR lvt_20_2_ = p_152764_1_.getTexture(resourcelocation);
                     int lvt_21_1_ = JsonUtils.getInt(jsonobject1, "width");
                     int lvt_22_1_ = JsonUtils.getInt(jsonobject1, "height");
                     boolean var23 = JsonUtils.getBoolean(jsonobject1, "bilinear");
                     if (var23) {
                        yh.glTexParameteri(3553, 10241, 9729);
                        yh.glTexParameteri(3553, 10240, 9729);
                     } else {
                        yh.glTexParameteri(3553, 10241, 9728);
                        yh.glTexParameteri(3553, 10240, 9728);
                     }

                     shader.addAuxFramebuffer(s4, lvt_20_2_.getGlTextureId(), lvt_21_1_, lvt_22_1_);
                  } else {
                     shader.addAuxFramebuffer(s4, framebuffer2, framebuffer2.framebufferTextureWidth, framebuffer2.framebufferTextureHeight);
                  }
               } catch (Exception var31) {
                  Exception exception1 = var31;
                  BN jsonexception = BN.forException(exception1);
                  jsonexception.prependJsonKey("auxtargets[" + i + "]");
                  throw jsonexception;
               }
            }
         }

         JsonArray jsonarray1 = JsonUtils.getJsonArray(jsonobject, "uniforms", (JsonArray)null);
         if (jsonarray1 != null) {
            int l = 0;

            for(Iterator var34 = jsonarray1.iterator(); var34.hasNext(); ++l) {
               JsonElement jsonelement1 = (JsonElement)var34.next();

               try {
                  this.initUniform(jsonelement1);
               } catch (Exception var28) {
                  BN jsonexception1 = BN.forException(var28);
                  jsonexception1.prependJsonKey("uniforms[" + l + "]");
                  throw jsonexception1;
               }
            }
         }

      }
   }

   private void initUniform(JsonElement json) throws BN {
      JsonObject jsonobject = JsonUtils.getJsonObject(json, "uniform");
      String s = JsonUtils.getString(jsonobject, "name");
      Bw shaderuniform = ((Bp)this.listShaders.get(this.listShaders.size() - 1)).getShaderManager().getShaderUniform(s);
      if (shaderuniform == null) {
         throw new BN("Uniform '" + s + "' does not exist");
      } else {
         float[] afloat = new float[4];
         int i = 0;

         for(Iterator var7 = JsonUtils.getJsonArray(jsonobject, "values").iterator(); var7.hasNext(); ++i) {
            JsonElement jsonelement = (JsonElement)var7.next();

            try {
               afloat[i] = JsonUtils.getFloat(jsonelement, "value");
            } catch (Exception var11) {
               Exception exception = var11;
               BN jsonexception = BN.forException(exception);
               jsonexception.prependJsonKey("values[" + i + "]");
               throw jsonexception;
            }
         }

         switch (i) {
            case 0:
            default:
               break;
            case 1:
               shaderuniform.set(afloat[0]);
               break;
            case 2:
               shaderuniform.set(afloat[0], afloat[1]);
               break;
            case 3:
               shaderuniform.set(afloat[0], afloat[1], afloat[2]);
               break;
            case 4:
               shaderuniform.set(afloat[0], afloat[1], afloat[2], afloat[3]);
         }

      }
   }

   public Bn getFramebufferRaw(String attributeName) {
      return (Bn)this.mapFramebuffers.get(attributeName);
   }

   public void addFramebuffer(String name, int width, int height) {
      Bn framebuffer = new Bn(width, height, true);
      framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.mapFramebuffers.put(name, framebuffer);
      if (width == this.mainFramebufferWidth && height == this.mainFramebufferHeight) {
         this.listFramebuffers.add(framebuffer);
      }

   }

   public void deleteShaderGroup() {
      Iterator var1 = this.mapFramebuffers.values().iterator();

      while(var1.hasNext()) {
         Bn framebuffer = (Bn)var1.next();
         framebuffer.deleteFramebuffer();
      }

      var1 = this.listShaders.iterator();

      while(var1.hasNext()) {
         Bp shader = (Bp)var1.next();
         shader.deleteShader();
      }

      this.listShaders.clear();
   }

   public Bp addShader(String programName, Bn framebufferIn, Bn framebufferOut) throws BN, IOException {
      Bp shader = new Bp(this.resourceManager, programName, framebufferIn, framebufferOut);
      this.listShaders.add(this.listShaders.size(), shader);
      return shader;
   }

   private void resetProjectionMatrix() {
      this.projectionMatrix = new Matrix4f();
      this.projectionMatrix.setIdentity();
      this.projectionMatrix.m00 = 2.0F / (float)this.mainFramebuffer.framebufferTextureWidth;
      this.projectionMatrix.m11 = 2.0F / (float)(-this.mainFramebuffer.framebufferTextureHeight);
      this.projectionMatrix.m22 = -0.0020001999F;
      this.projectionMatrix.m33 = 1.0F;
      this.projectionMatrix.m03 = -1.0F;
      this.projectionMatrix.m13 = 1.0F;
      this.projectionMatrix.m23 = -1.0001999F;
   }

   public void createBindFramebuffers(int width, int height) {
      this.mainFramebufferWidth = this.mainFramebuffer.framebufferTextureWidth;
      this.mainFramebufferHeight = this.mainFramebuffer.framebufferTextureHeight;
      this.resetProjectionMatrix();
      Iterator var3 = this.listShaders.iterator();

      while(var3.hasNext()) {
         Bp shader = (Bp)var3.next();
         shader.setProjectionMatrix(this.projectionMatrix);
      }

      var3 = this.listFramebuffers.iterator();

      while(var3.hasNext()) {
         Bn framebuffer = (Bn)var3.next();
         framebuffer.createBindFramebuffer(width, height);
      }

   }

   public void render(float partialTicks) {
      if (partialTicks < this.lastStamp) {
         this.time += 1.0F - this.lastStamp;
         this.time += partialTicks;
      } else {
         this.time += partialTicks - this.lastStamp;
      }

      for(this.lastStamp = partialTicks; this.time > 20.0F; this.time -= 20.0F) {
      }

      Iterator var2 = this.listShaders.iterator();

      while(var2.hasNext()) {
         Bp shader = (Bp)var2.next();
         shader.render(this.time / 20.0F);
      }

   }

   public final String getShaderGroupName() {
      return this.shaderGroupName;
   }

   private Bn getFramebuffer(String p_148017_1_) {
      if (p_148017_1_ == null) {
         return null;
      } else {
         return p_148017_1_.equals("minecraft:main") ? this.mainFramebuffer : (Bn)this.mapFramebuffers.get(p_148017_1_);
      }
   }
}
