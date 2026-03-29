package neo;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class baf extends bae {
   private final Set<MG> players = Sets.newHashSet();
   private final Set<MG> readOnlyPlayers;
   private boolean visible;

   public baf(ITextComponent nameIn, bac colorIn, bad overlayIn) {
      super(MathHelper.getRandomUUID(), nameIn, colorIn, overlayIn);
      this.readOnlyPlayers = Collections.unmodifiableSet(this.players);
      this.visible = true;
   }

   public void setPercent(float percentIn) {
      if (percentIn != this.percent) {
         super.setPercent(percentIn);
         this.sendUpdate(Vb.UPDATE_PCT);
      }

   }

   public void setColor(bac colorIn) {
      if (colorIn != this.color) {
         super.setColor(colorIn);
         this.sendUpdate(Vb.UPDATE_STYLE);
      }

   }

   public void setOverlay(bad overlayIn) {
      if (overlayIn != this.overlay) {
         super.setOverlay(overlayIn);
         this.sendUpdate(Vb.UPDATE_STYLE);
      }

   }

   public bae setDarkenSky(boolean darkenSkyIn) {
      if (darkenSkyIn != this.darkenSky) {
         super.setDarkenSky(darkenSkyIn);
         this.sendUpdate(Vb.UPDATE_PROPERTIES);
      }

      return this;
   }

   public bae setPlayEndBossMusic(boolean playEndBossMusicIn) {
      if (playEndBossMusicIn != this.playEndBossMusic) {
         super.setPlayEndBossMusic(playEndBossMusicIn);
         this.sendUpdate(Vb.UPDATE_PROPERTIES);
      }

      return this;
   }

   public bae setCreateFog(boolean createFogIn) {
      if (createFogIn != this.createFog) {
         super.setCreateFog(createFogIn);
         this.sendUpdate(Vb.UPDATE_PROPERTIES);
      }

      return this;
   }

   public void setName(ITextComponent nameIn) {
      if (!Objects.equal(nameIn, this.name)) {
         super.setName(nameIn);
         this.sendUpdate(Vb.UPDATE_NAME);
      }

   }

   private void sendUpdate(Vb operationIn) {
      if (this.visible) {
         Vc spacketupdatebossinfo = new Vc(operationIn, this);
         Iterator var3 = this.players.iterator();

         while(var3.hasNext()) {
            MG entityplayermp = (MG)var3.next();
            entityplayermp.connection.sendPacket(spacketupdatebossinfo);
         }
      }

   }

   public void addPlayer(MG player) {
      if (this.players.add(player) && this.visible) {
         player.connection.sendPacket(new Vc(Vb.ADD, this));
      }

   }

   public void removePlayer(MG player) {
      if (this.players.remove(player) && this.visible) {
         player.connection.sendPacket(new Vc(Vb.REMOVE, this));
      }

   }

   public void setVisible(boolean visibleIn) {
      if (visibleIn != this.visible) {
         this.visible = visibleIn;
         Iterator var2 = this.players.iterator();

         while(var2.hasNext()) {
            MG entityplayermp = (MG)var2.next();
            entityplayermp.connection.sendPacket(new Vc(visibleIn ? Vb.ADD : Vb.REMOVE, this));
         }
      }

   }

   public Collection<MG> getPlayers() {
      return this.readOnlyPlayers;
   }
}
