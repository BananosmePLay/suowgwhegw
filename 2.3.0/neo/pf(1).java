package neo;

import java.awt.image.BufferedImage;

public class pf {
   public String serverName;
   public String serverIP;
   public String populationInfo;
   public String serverMOTD;
   public long pingToServer;
   public int version = 340;
   public String gameVersion = "1.12.2";
   public boolean pinged;
   public String playerList;
   private pe resourceMode;
   private String serverIcon;
   public BufferedImage iconRender;
   private boolean lanServer;

   public pf(String name, String ip, boolean isLan) {
      this.resourceMode = pe.PROMPT;
      this.serverName = name;
      this.serverIP = ip;
      this.lanServer = isLan;
   }

   public QQ getNBTCompound() {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("name", this.serverName);
      nbttagcompound.setString("ip", this.serverIP);
      if (this.serverIcon != null) {
         nbttagcompound.setString("icon", this.serverIcon);
      }

      if (this.resourceMode == pe.ENABLED) {
         nbttagcompound.setBoolean("acceptTextures", true);
      } else if (this.resourceMode == pe.DISABLED) {
         nbttagcompound.setBoolean("acceptTextures", false);
      }

      return nbttagcompound;
   }

   public pe getResourceMode() {
      return this.resourceMode;
   }

   public void setResourceMode(pe mode) {
      this.resourceMode = mode;
   }

   public static pf getServerDataFromNBTCompound(QQ nbtCompound) {
      pf serverdata = new pf(nbtCompound.getString("name"), nbtCompound.getString("ip"), false);
      if (nbtCompound.hasKey("icon", 8)) {
         serverdata.setBase64EncodedIconData(nbtCompound.getString("icon"));
      }

      if (nbtCompound.hasKey("acceptTextures", 1)) {
         if (nbtCompound.getBoolean("acceptTextures")) {
            serverdata.setResourceMode(pe.ENABLED);
         } else {
            serverdata.setResourceMode(pe.DISABLED);
         }
      } else {
         serverdata.setResourceMode(pe.PROMPT);
      }

      return serverdata;
   }

   public String getBase64EncodedIconData() {
      return this.serverIcon;
   }

   public void setBase64EncodedIconData(String icon) {
      this.serverIcon = icon;
   }

   public boolean isOnLAN() {
      return this.lanServer;
   }

   public void copyFrom(pf serverDataIn) {
      this.serverIP = serverDataIn.serverIP;
      this.serverName = serverDataIn.serverName;
      this.setResourceMode(serverDataIn.getResourceMode());
      this.serverIcon = serverDataIn.serverIcon;
      this.lanServer = serverDataIn.lanServer;
   }
}
