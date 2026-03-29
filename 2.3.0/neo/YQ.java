package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;

public class YQ extends Yg {
   public final ITextComponent[] signText = new ITextComponent[]{new TextComponentString(""), new TextComponentString(""), new TextComponentString(""), new TextComponentString("")};
   public int lineBeingEdited = -1;
   private boolean isEditable = true;
   private ME player;
   private final CL stats = new CL();

   public YQ() {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);

      for(int i = 0; i < 4; ++i) {
         String s = ITextComponent.Serializer.componentToJson(this.signText[i]);
         compound.setString("Text" + (i + 1), s);
      }

      this.stats.writeStatsToNBT(compound);
      return compound;
   }

   protected void setWorldCreate(bij worldIn) {
      this.setWorld(worldIn);
   }

   public void readFromNBT(QQ compound) {
      this.isEditable = false;
      super.readFromNBT(compound);
      DB icommandsender = new DB() {
         public String getName() {
            return "Sign";
         }

         public boolean canUseCommand(int permLevel, String commandName) {
            return true;
         }

         public BlockPos getPosition() {
            return YQ.this.pos;
         }

         public Vec3d getPositionVector() {
            return new Vec3d((double)YQ.this.pos.getX() + 0.5, (double)YQ.this.pos.getY() + 0.5, (double)YQ.this.pos.getZ() + 0.5);
         }

         public bij getEntityWorld() {
            return YQ.this.world;
         }

         public Xx getServer() {
            return YQ.this.world.getMinecraftServer();
         }
      };

      for(int i = 0; i < 4; ++i) {
         String s = compound.getString("Text" + (i + 1));
         ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s);

         try {
            this.signText[i] = TextComponentUtils.processComponent(icommandsender, itextcomponent, (Ig)null);
         } catch (Ct var7) {
            this.signText[i] = itextcomponent;
         }
      }

      this.stats.readStatsFromNBT(compound);
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 9, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   public boolean getIsEditable() {
      return this.isEditable;
   }

   public void setEditable(boolean isEditableIn) {
      this.isEditable = isEditableIn;
      if (!isEditableIn) {
         this.player = null;
      }

   }

   public void setPlayer(ME playerIn) {
      this.player = playerIn;
   }

   public ME getPlayer() {
      return this.player;
   }

   public boolean executeCommand(final ME playerIn) {
      DB icommandsender = new DB() {
         public String getName() {
            return playerIn.getName();
         }

         public ITextComponent getDisplayName() {
            return playerIn.getDisplayName();
         }

         public void sendMessage(ITextComponent component) {
         }

         public boolean canUseCommand(int permLevel, String commandName) {
            return permLevel <= 2;
         }

         public BlockPos getPosition() {
            return YQ.this.pos;
         }

         public Vec3d getPositionVector() {
            return new Vec3d((double)YQ.this.pos.getX() + 0.5, (double)YQ.this.pos.getY() + 0.5, (double)YQ.this.pos.getZ() + 0.5);
         }

         public bij getEntityWorld() {
            return playerIn.getEntityWorld();
         }

         public Ig getCommandSenderEntity() {
            return playerIn;
         }

         public boolean sendCommandFeedback() {
            return false;
         }

         public void setCommandStat(CK type, int amount) {
            if (YQ.this.world != null && !YQ.this.world.isRemote) {
               YQ.this.stats.setCommandStatForSender(YQ.this.world.getMinecraftServer(), this, type, amount);
            }

         }

         public Xx getServer() {
            return playerIn.getServer();
         }
      };
      ITextComponent[] var3 = this.signText;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ITextComponent itextcomponent = var3[var5];
         Style style = itextcomponent == null ? null : itextcomponent.getStyle();
         if (style != null && style.getClickEvent() != null) {
            ClickEvent clickevent = style.getClickEvent();
            if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
               playerIn.getServer().getCommandManager().executeCommand(icommandsender, clickevent.getValue());
            }
         }
      }

      return true;
   }

   public CL getStats() {
      return this.stats;
   }
}
