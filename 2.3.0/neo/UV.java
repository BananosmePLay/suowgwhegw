package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class UV implements Sz<Ts> {
   private String name = "";
   private String displayName = "";
   private String prefix = "";
   private String suffix = "";
   private String nameTagVisibility;
   private String collisionRule;
   private int color;
   private final Collection<String> players;
   private int action;
   private int friendlyFlags;

   public UV() {
      this.nameTagVisibility = WD.ALWAYS.internalName;
      this.collisionRule = WC.ALWAYS.name;
      this.color = -1;
      this.players = Lists.newArrayList();
   }

   public UV(WA teamIn, int actionIn) {
      this.nameTagVisibility = WD.ALWAYS.internalName;
      this.collisionRule = WC.ALWAYS.name;
      this.color = -1;
      this.players = Lists.newArrayList();
      this.name = teamIn.getName();
      this.action = actionIn;
      if (actionIn == 0 || actionIn == 2) {
         this.displayName = teamIn.getDisplayName();
         this.prefix = teamIn.getPrefix();
         this.suffix = teamIn.getSuffix();
         this.friendlyFlags = teamIn.getFriendlyFlags();
         this.nameTagVisibility = teamIn.getNameTagVisibility().internalName;
         this.collisionRule = teamIn.getCollisionRule().name;
         this.color = teamIn.getColor().getColorIndex();
      }

      if (actionIn == 0) {
         this.players.addAll(teamIn.getMembershipCollection());
      }

   }

   public UV(WA teamIn, Collection<String> playersIn, int actionIn) {
      this.nameTagVisibility = WD.ALWAYS.internalName;
      this.collisionRule = WC.ALWAYS.name;
      this.color = -1;
      this.players = Lists.newArrayList();
      if (actionIn != 3 && actionIn != 4) {
         throw new IllegalArgumentException("Method must be join or leave for player constructor");
      } else if (playersIn != null && !playersIn.isEmpty()) {
         this.action = actionIn;
         this.name = teamIn.getName();
         this.players.addAll(playersIn);
      } else {
         throw new IllegalArgumentException("Players cannot be null/empty");
      }
   }

   public void readPacketData(SA buf) throws IOException {
      this.name = buf.readString(16);
      this.action = buf.readByte();
      if (this.action == 0 || this.action == 2) {
         this.displayName = buf.readString(32);
         this.prefix = buf.readString(16);
         this.suffix = buf.readString(16);
         this.friendlyFlags = buf.readByte();
         this.nameTagVisibility = buf.readString(32);
         this.collisionRule = buf.readString(32);
         this.color = buf.readByte();
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         int i = buf.readVarInt();

         for(int j = 0; j < i; ++j) {
            this.players.add(buf.readString(40));
         }
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.name);
      buf.writeByte(this.action);
      if (this.action == 0 || this.action == 2) {
         buf.writeString(this.displayName);
         buf.writeString(this.prefix);
         buf.writeString(this.suffix);
         buf.writeByte(this.friendlyFlags);
         buf.writeString(this.nameTagVisibility);
         buf.writeString(this.collisionRule);
         buf.writeByte(this.color);
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         buf.writeVarInt(this.players.size());
         Iterator var2 = this.players.iterator();

         while(var2.hasNext()) {
            String s = (String)var2.next();
            buf.writeString(s);
         }
      }

   }

   public void processPacket(Ts handler) {
      handler.handleTeams(this);
   }

   public String getName() {
      return this.name;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public String getSuffix() {
      return this.suffix;
   }

   public Collection<String> getPlayers() {
      return this.players;
   }

   public int getAction() {
      return this.action;
   }

   public int getFriendlyFlags() {
      return this.friendlyFlags;
   }

   public int getColor() {
      return this.color;
   }

   public String getNameTagVisibility() {
      return this.nameTagVisibility;
   }

   public String getCollisionRule() {
      return this.collisionRule;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
