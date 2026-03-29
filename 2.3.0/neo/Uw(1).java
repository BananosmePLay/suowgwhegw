package neo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.text.ITextComponent;

public class Uw implements Sz<Ts> {
   private Uu action;
   private final List<Uv> players = Lists.newArrayList();

   public Uw() {
   }

   public Uw(Uu actionIn, MG... playersIn) {
      this.action = actionIn;
      MG[] var3 = playersIn;
      int var4 = playersIn.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         MG entityplayermp = var3[var5];
         this.players.add(new Uv(this, entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.interactionManager.getGameType(), entityplayermp.getTabListDisplayName()));
      }

   }

   public Uw(Uu actionIn, Iterable<MG> playersIn) {
      this.action = actionIn;
      Iterator var3 = playersIn.iterator();

      while(var3.hasNext()) {
         MG entityplayermp = (MG)var3.next();
         this.players.add(new Uv(this, entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.interactionManager.getGameType(), entityplayermp.getTabListDisplayName()));
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.action = (Uu)buf.readEnumValue(Uu.class);
      int i = buf.readVarInt();

      for(int j = 0; j < i; ++j) {
         GameProfile gameprofile = null;
         int k = 0;
         bbb gametype = null;
         ITextComponent itextcomponent = null;
         switch (this.action) {
            case ADD_PLAYER:
               gameprofile = new GameProfile(buf.readUniqueId(), buf.readString(16));
               int l = buf.readVarInt();
               int i1 = 0;

               for(; i1 < l; ++i1) {
                  String s = buf.readString(32767);
                  String s1 = buf.readString(32767);
                  if (buf.readBoolean()) {
                     gameprofile.getProperties().put(s, new Property(s, s1, buf.readString(32767)));
                  } else {
                     gameprofile.getProperties().put(s, new Property(s, s1));
                  }
               }

               gametype = bbb.getByID(buf.readVarInt());
               k = buf.readVarInt();
               if (buf.readBoolean()) {
                  itextcomponent = buf.readTextComponent();
               }
               break;
            case UPDATE_GAME_MODE:
               gameprofile = new GameProfile(buf.readUniqueId(), (String)null);
               gametype = bbb.getByID(buf.readVarInt());
               break;
            case UPDATE_LATENCY:
               gameprofile = new GameProfile(buf.readUniqueId(), (String)null);
               k = buf.readVarInt();
               break;
            case UPDATE_DISPLAY_NAME:
               gameprofile = new GameProfile(buf.readUniqueId(), (String)null);
               if (buf.readBoolean()) {
                  itextcomponent = buf.readTextComponent();
               }
               break;
            case REMOVE_PLAYER:
               gameprofile = new GameProfile(buf.readUniqueId(), (String)null);
         }

         this.players.add(new Uv(this, gameprofile, k, gametype, itextcomponent));
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.action);
      buf.writeVarInt(this.players.size());
      Iterator var2 = this.players.iterator();

      while(true) {
         while(var2.hasNext()) {
            Uv spacketplayerlistitem$addplayerdata = (Uv)var2.next();
            switch (this.action) {
               case ADD_PLAYER:
                  buf.writeUniqueId(spacketplayerlistitem$addplayerdata.getProfile().getId());
                  buf.writeString(spacketplayerlistitem$addplayerdata.getProfile().getName());
                  buf.writeVarInt(spacketplayerlistitem$addplayerdata.getProfile().getProperties().size());
                  Iterator var4 = spacketplayerlistitem$addplayerdata.getProfile().getProperties().values().iterator();

                  while(var4.hasNext()) {
                     Property property = (Property)var4.next();
                     buf.writeString(property.getName());
                     buf.writeString(property.getValue());
                     if (property.hasSignature()) {
                        buf.writeBoolean(true);
                        buf.writeString(property.getSignature());
                     } else {
                        buf.writeBoolean(false);
                     }
                  }

                  buf.writeVarInt(spacketplayerlistitem$addplayerdata.getGameMode().getID());
                  buf.writeVarInt(spacketplayerlistitem$addplayerdata.getPing());
                  if (spacketplayerlistitem$addplayerdata.getDisplayName() == null) {
                     buf.writeBoolean(false);
                  } else {
                     buf.writeBoolean(true);
                     buf.writeTextComponent(spacketplayerlistitem$addplayerdata.getDisplayName());
                  }
                  break;
               case UPDATE_GAME_MODE:
                  buf.writeUniqueId(spacketplayerlistitem$addplayerdata.getProfile().getId());
                  buf.writeVarInt(spacketplayerlistitem$addplayerdata.getGameMode().getID());
                  break;
               case UPDATE_LATENCY:
                  buf.writeUniqueId(spacketplayerlistitem$addplayerdata.getProfile().getId());
                  buf.writeVarInt(spacketplayerlistitem$addplayerdata.getPing());
                  break;
               case UPDATE_DISPLAY_NAME:
                  buf.writeUniqueId(spacketplayerlistitem$addplayerdata.getProfile().getId());
                  if (spacketplayerlistitem$addplayerdata.getDisplayName() == null) {
                     buf.writeBoolean(false);
                  } else {
                     buf.writeBoolean(true);
                     buf.writeTextComponent(spacketplayerlistitem$addplayerdata.getDisplayName());
                  }
                  break;
               case REMOVE_PLAYER:
                  buf.writeUniqueId(spacketplayerlistitem$addplayerdata.getProfile().getId());
            }
         }

         return;
      }
   }

   public void processPacket(Ts handler) {
      handler.handlePlayerListItem(this);
   }

   public List<Uv> getEntries() {
      return this.players;
   }

   public Uu getAction() {
      return this.action;
   }

   public String toString() {
      return MoreObjects.toStringHelper(this).add("action", this.action).add("entries", this.players).toString();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
