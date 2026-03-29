package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;

public class bnq {
   private String player = null;
   public static final String CONFIG_ITEMS = "items";
   public static final String ITEM_TYPE = "type";
   public static final String ITEM_ACTIVE = "active";

   public bnq(String player) {
      this.player = player;
   }

   public bnp parsePlayerConfiguration(JsonElement je) {
      if (je == null) {
         throw new JsonParseException("JSON object is null, player: " + this.player);
      } else {
         JsonObject jsonobject = (JsonObject)je;
         bnp playerconfiguration = new bnp();
         JsonArray jsonarray = (JsonArray)jsonobject.get("items");
         if (jsonarray != null) {
            for(int i = 0; i < jsonarray.size(); ++i) {
               JsonObject jsonobject1 = (JsonObject)jsonarray.get(i);
               boolean flag = bqx.getBoolean(jsonobject1, "active", true);
               if (flag) {
                  String s = bqx.getString(jsonobject1, "type");
                  if (s == null) {
                     XH.warn("Item type is null, player: " + this.player);
                  } else {
                     String s1 = bqx.getString(jsonobject1, "model");
                     if (s1 == null) {
                        s1 = "items/" + s + "/model.cfg";
                     }

                     bnt playeritemmodel = this.downloadModel(s1);
                     if (playeritemmodel != null) {
                        if (!playeritemmodel.isUsePlayerTexture()) {
                           String s2 = bqx.getString(jsonobject1, "texture");
                           if (s2 == null) {
                              s2 = "items/" + s + "/users/" + this.player + ".png";
                           }

                           BufferedImage bufferedimage = this.downloadTextureImage(s2);
                           if (bufferedimage == null) {
                              continue;
                           }

                           playeritemmodel.setTextureImage(bufferedimage);
                           ResourceLocation resourcelocation = new ResourceLocation("optifine.net", s2);
                           playeritemmodel.setTextureLocation(resourcelocation);
                        }

                        playerconfiguration.addPlayerItemModel(playeritemmodel);
                     }
                  }
               }
            }
         }

         return playerconfiguration;
      }
   }

   private BufferedImage downloadTextureImage(String texturePath) {
      String s = bmO.getPlayerItemsUrl() + "/" + texturePath;

      try {
         byte[] abyte = bmH.get(s, nC.getMinecraft().getProxy());
         BufferedImage bufferedimage = ImageIO.read(new ByteArrayInputStream(abyte));
         return bufferedimage;
      } catch (IOException var5) {
         IOException ioexception = var5;
         XH.warn("Error loading item texture " + texturePath + ": " + ioexception.getClass().getName() + ": " + ioexception.getMessage());
         return null;
      }
   }

   private bnt downloadModel(String modelPath) {
      String s = bmO.getPlayerItemsUrl() + "/" + modelPath;

      try {
         byte[] abyte = bmH.get(s, nC.getMinecraft().getProxy());
         String s1 = new String(abyte, "ASCII");
         JsonParser jsonparser = new JsonParser();
         JsonObject jsonobject = (JsonObject)jsonparser.parse(s1);
         bnt playeritemmodel = bnu.parseItemModel(jsonobject);
         return playeritemmodel;
      } catch (Exception var8) {
         Exception exception = var8;
         XH.warn("Error loading item model " + modelPath + ": " + exception.getClass().getName() + ": " + exception.getMessage());
         return null;
      }
   }
}
