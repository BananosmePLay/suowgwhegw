package neo;

import java.io.IOException;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TJ implements Sz<Ts> {
   public TI eventType;
   public int playerId;
   public int entityId;
   public int duration;
   public ITextComponent deathMessage;

   public TJ() {
   }

   public TJ(CombatTracker tracker, TI eventIn) {
      this(tracker, eventIn, true);
   }

   public TJ(CombatTracker tracker, TI eventIn, boolean showDeathMessage) {
      this.eventType = eventIn;
      Iw entitylivingbase = tracker.getBestAttacker();
      switch (eventIn) {
         case END_COMBAT:
            this.duration = tracker.getCombatDuration();
            this.entityId = entitylivingbase == null ? -1 : entitylivingbase.getEntityId();
            break;
         case ENTITY_DIED:
            this.playerId = tracker.getFighter().getEntityId();
            this.entityId = entitylivingbase == null ? -1 : entitylivingbase.getEntityId();
            if (showDeathMessage) {
               this.deathMessage = tracker.getDeathMessage();
            } else {
               this.deathMessage = new TextComponentString("");
            }
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.eventType = (TI)buf.readEnumValue(TI.class);
      if (this.eventType == TI.END_COMBAT) {
         this.duration = buf.readVarInt();
         this.entityId = buf.readInt();
      } else if (this.eventType == TI.ENTITY_DIED) {
         this.playerId = buf.readVarInt();
         this.entityId = buf.readInt();
         this.deathMessage = buf.readTextComponent();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.eventType);
      if (this.eventType == TI.END_COMBAT) {
         buf.writeVarInt(this.duration);
         buf.writeInt(this.entityId);
      } else if (this.eventType == TI.ENTITY_DIED) {
         buf.writeVarInt(this.playerId);
         buf.writeInt(this.entityId);
         buf.writeTextComponent(this.deathMessage);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleCombatEvent(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
