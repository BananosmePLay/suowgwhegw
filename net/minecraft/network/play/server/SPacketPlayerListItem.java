package net.minecraft.network.play.server;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;

public class SPacketPlayerListItem implements Packet<INetHandlerPlayClient> {
   private Action action;
   private final List<AddPlayerData> players = Lists.newArrayList();

   public SPacketPlayerListItem() {
   }

   public SPacketPlayerListItem(Action actionIn, EntityPlayerMP... playersIn) {
      this.action = actionIn;
      EntityPlayerMP[] var3 = playersIn;
      int var4 = playersIn.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EntityPlayerMP entityplayermp = var3[var5];
         this.players.add(new AddPlayerData(entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.interactionManager.getGameType(), entityplayermp.getTabListDisplayName()));
      }

   }

   public SPacketPlayerListItem(Action actionIn, Iterable<EntityPlayerMP> playersIn) {
      this.action = actionIn;
      Iterator var3 = playersIn.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)var3.next();
         this.players.add(new AddPlayerData(entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.interactionManager.getGameType(), entityplayermp.getTabListDisplayName()));
      }

   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.action = (Action)buf.readEnumValue(Action.class);
      int i = buf.readVarInt();

      for(int j = 0; j < i; ++j) {
         GameProfile gameprofile = null;
         int k = 0;
         GameType gametype = null;
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

               gametype = GameType.getByID(buf.readVarInt());
               k = buf.readVarInt();
               if (buf.readBoolean()) {
                  itextcomponent = buf.readTextComponent();
               }
               break;
            case UPDATE_GAME_MODE:
               gameprofile = new GameProfile(buf.readUniqueId(), (String)null);
               gametype = GameType.getByID(buf.readVarInt());
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

         this.players.add(new AddPlayerData(gameprofile, k, gametype, itextcomponent));
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.action);
      buf.writeVarInt(this.players.size());
      Iterator var2 = this.players.iterator();

      while(true) {
         while(var2.hasNext()) {
            AddPlayerData spacketplayerlistitem$addplayerdata = (AddPlayerData)var2.next();
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

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerListItem(this);
   }

   public List<AddPlayerData> getEntries() {
      return this.players;
   }

   public Action getAction() {
      return this.action;
   }

   public String toString() {
      return MoreObjects.toStringHelper((Object)this).add("action", this.action).add("entries", this.players).toString();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }

   public class AddPlayerData {
      private final int ping;
      private final GameType gamemode;
      private final GameProfile profile;
      private final ITextComponent displayName;

      public AddPlayerData(GameProfile profileIn, int latencyIn, GameType gameModeIn, @Nullable ITextComponent displayNameIn) {
         this.profile = profileIn;
         this.ping = latencyIn;
         this.gamemode = gameModeIn;
         this.displayName = displayNameIn;
      }

      public GameProfile getProfile() {
         return this.profile;
      }

      public int getPing() {
         return this.ping;
      }

      public GameType getGameMode() {
         return this.gamemode;
      }

      @Nullable
      public ITextComponent getDisplayName() {
         return this.displayName;
      }

      public String toString() {
         return MoreObjects.toStringHelper((Object)this).add("latency", this.ping).add("gameMode", this.gamemode).add("profile", this.profile).add("displayName", this.displayName == null ? null : ITextComponent.Serializer.componentToJson(this.displayName)).toString();
      }
   }

   public static enum Action {
      ADD_PLAYER,
      UPDATE_GAME_MODE,
      UPDATE_LATENCY,
      UPDATE_DISPLAY_NAME,
      REMOVE_PLAYER;

      private Action() {
      }
   }
}
