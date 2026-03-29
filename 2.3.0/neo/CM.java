package neo;

import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;

public class CM implements DB {
   private final DB delegate;
   @Nullable
   private final Vec3d positionVector;
   @Nullable
   private final BlockPos position;
   @Nullable
   private final Integer permissionLevel;
   @Nullable
   private final Ig entity;
   @Nullable
   private final Boolean sendCommandFeedback;

   public CM(DB delegateIn, @Nullable Vec3d positionVectorIn, @Nullable BlockPos positionIn, @Nullable Integer permissionLevelIn, @Nullable Ig entityIn, @Nullable Boolean sendCommandFeedbackIn) {
      this.delegate = delegateIn;
      this.positionVector = positionVectorIn;
      this.position = positionIn;
      this.permissionLevel = permissionLevelIn;
      this.entity = entityIn;
      this.sendCommandFeedback = sendCommandFeedbackIn;
   }

   public static CM create(DB sender) {
      return sender instanceof CM ? (CM)sender : new CM(sender, (Vec3d)null, (BlockPos)null, (Integer)null, (Ig)null, (Boolean)null);
   }

   public CM withEntity(Ig entityIn, Vec3d p_193997_2_) {
      return this.entity == entityIn && Objects.equals(this.positionVector, p_193997_2_) ? this : new CM(this.delegate, p_193997_2_, new BlockPos(p_193997_2_), this.permissionLevel, entityIn, this.sendCommandFeedback);
   }

   public CM withPermissionLevel(int level) {
      return this.permissionLevel != null && this.permissionLevel <= level ? this : new CM(this.delegate, this.positionVector, this.position, level, this.entity, this.sendCommandFeedback);
   }

   public CM withSendCommandFeedback(boolean sendCommandFeedbackIn) {
      return this.sendCommandFeedback != null && (!this.sendCommandFeedback || sendCommandFeedbackIn) ? this : new CM(this.delegate, this.positionVector, this.position, this.permissionLevel, this.entity, sendCommandFeedbackIn);
   }

   public CM computePositionVector() {
      return this.positionVector != null ? this : new CM(this.delegate, this.getPositionVector(), this.getPosition(), this.permissionLevel, this.entity, this.sendCommandFeedback);
   }

   public String getName() {
      return this.entity != null ? this.entity.getName() : this.delegate.getName();
   }

   public ITextComponent getDisplayName() {
      return this.entity != null ? this.entity.getDisplayName() : this.delegate.getDisplayName();
   }

   public void sendMessage(ITextComponent component) {
      if (this.sendCommandFeedback == null || this.sendCommandFeedback) {
         this.delegate.sendMessage(component);
      }

   }

   public boolean canUseCommand(int permLevel, String commandName) {
      return this.permissionLevel != null && this.permissionLevel < permLevel ? false : this.delegate.canUseCommand(permLevel, commandName);
   }

   public BlockPos getPosition() {
      if (this.position != null) {
         return this.position;
      } else {
         return this.entity != null ? this.entity.getPosition() : this.delegate.getPosition();
      }
   }

   public Vec3d getPositionVector() {
      if (this.positionVector != null) {
         return this.positionVector;
      } else {
         return this.entity != null ? this.entity.getPositionVector() : this.delegate.getPositionVector();
      }
   }

   public bij getEntityWorld() {
      return this.entity != null ? this.entity.getEntityWorld() : this.delegate.getEntityWorld();
   }

   @Nullable
   public Ig getCommandSenderEntity() {
      return this.entity != null ? this.entity.getCommandSenderEntity() : this.delegate.getCommandSenderEntity();
   }

   public boolean sendCommandFeedback() {
      return this.sendCommandFeedback != null ? this.sendCommandFeedback : this.delegate.sendCommandFeedback();
   }

   public void setCommandStat(CK type, int amount) {
      if (this.entity != null) {
         this.entity.setCommandStat(type, amount);
      } else {
         this.delegate.setCommandStat(type, amount);
      }

   }

   @Nullable
   public Xx getServer() {
      return this.delegate.getServer();
   }
}
