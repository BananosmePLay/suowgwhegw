package neo;

import com.google.common.base.Optional;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IntIdentityHashBiMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.text.ITextComponent;

public class Rt {
   private static final IntIdentityHashBiMap<Re<?>> REGISTRY = new IntIdentityHashBiMap(16);
   public static final Re<Byte> BYTE = new Re<Byte>() {
      public void write(SA buf, Byte value) {
         buf.writeByte(value);
      }

      public Byte read(SA buf) throws IOException {
         return buf.readByte();
      }

      public Rd<Byte> createKey(int id) {
         return new Rd(id, this);
      }

      public Byte copyValue(Byte value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Byte)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Byte)var2);
      }
   };
   public static final Re<Integer> VARINT = new Re<Integer>() {
      public void write(SA buf, Integer value) {
         buf.writeVarInt(value);
      }

      public Integer read(SA buf) throws IOException {
         return buf.readVarInt();
      }

      public Rd<Integer> createKey(int id) {
         return new Rd(id, this);
      }

      public Integer copyValue(Integer value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Integer)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Integer)var2);
      }
   };
   public static final Re<Float> FLOAT = new Re<Float>() {
      public void write(SA buf, Float value) {
         buf.writeFloat(value);
      }

      public Float read(SA buf) throws IOException {
         return buf.readFloat();
      }

      public Rd<Float> createKey(int id) {
         return new Rd(id, this);
      }

      public Float copyValue(Float value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Float)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Float)var2);
      }
   };
   public static final Re<String> STRING = new Re<String>() {
      public void write(SA buf, String value) {
         buf.writeString(value);
      }

      public String read(SA buf) throws IOException {
         return buf.readString(32767);
      }

      public Rd<String> createKey(int id) {
         return new Rd(id, this);
      }

      public String copyValue(String value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((String)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (String)var2);
      }
   };
   public static final Re<ITextComponent> TEXT_COMPONENT = new Re<ITextComponent>() {
      public void write(SA buf, ITextComponent value) {
         buf.writeTextComponent(value);
      }

      public ITextComponent read(SA buf) throws IOException {
         return buf.readTextComponent();
      }

      public Rd<ITextComponent> createKey(int id) {
         return new Rd(id, this);
      }

      public ITextComponent copyValue(ITextComponent value) {
         return value.createCopy();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((ITextComponent)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (ITextComponent)var2);
      }
   };
   public static final Re<Qy> ITEM_STACK = new Re<Qy>() {
      public void write(SA buf, Qy value) {
         buf.writeItemStack(value);
      }

      public Qy read(SA buf) throws IOException {
         return buf.readItemStack();
      }

      public Rd<Qy> createKey(int id) {
         return new Rd(id, this);
      }

      public Qy copyValue(Qy value) {
         return value.copy();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Qy)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Qy)var2);
      }
   };
   public static final Re<Optional<in>> OPTIONAL_BLOCK_STATE = new Re<Optional<in>>() {
      public void write(SA buf, Optional<in> value) {
         if (value.isPresent()) {
            buf.writeVarInt(co.getStateId((in)value.get()));
         } else {
            buf.writeVarInt(0);
         }

      }

      public Optional<in> read(SA buf) throws IOException {
         int i = buf.readVarInt();
         return i == 0 ? Optional.absent() : Optional.of(co.getStateById(i));
      }

      public Rd<Optional<in>> createKey(int id) {
         return new Rd(id, this);
      }

      public Optional<in> copyValue(Optional<in> value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Optional)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Optional)var2);
      }
   };
   public static final Re<Boolean> BOOLEAN = new Re<Boolean>() {
      public void write(SA buf, Boolean value) {
         buf.writeBoolean(value);
      }

      public Boolean read(SA buf) throws IOException {
         return buf.readBoolean();
      }

      public Rd<Boolean> createKey(int id) {
         return new Rd(id, this);
      }

      public Boolean copyValue(Boolean value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Boolean)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Boolean)var2);
      }
   };
   public static final Re<Rotations> ROTATIONS = new Re<Rotations>() {
      public void write(SA buf, Rotations value) {
         buf.writeFloat(value.getX());
         buf.writeFloat(value.getY());
         buf.writeFloat(value.getZ());
      }

      public Rotations read(SA buf) throws IOException {
         return new Rotations(buf.readFloat(), buf.readFloat(), buf.readFloat());
      }

      public Rd<Rotations> createKey(int id) {
         return new Rd(id, this);
      }

      public Rotations copyValue(Rotations value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Rotations)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Rotations)var2);
      }
   };
   public static final Re<BlockPos> BLOCK_POS = new Re<BlockPos>() {
      public void write(SA buf, BlockPos value) {
         buf.writeBlockPos(value);
      }

      public BlockPos read(SA buf) throws IOException {
         return buf.readBlockPos();
      }

      public Rd<BlockPos> createKey(int id) {
         return new Rd(id, this);
      }

      public BlockPos copyValue(BlockPos value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((BlockPos)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (BlockPos)var2);
      }
   };
   public static final Re<Optional<BlockPos>> OPTIONAL_BLOCK_POS = new Re<Optional<BlockPos>>() {
      public void write(SA buf, Optional<BlockPos> value) {
         buf.writeBoolean(value.isPresent());
         if (value.isPresent()) {
            buf.writeBlockPos((BlockPos)value.get());
         }

      }

      public Optional<BlockPos> read(SA buf) throws IOException {
         return !buf.readBoolean() ? Optional.absent() : Optional.of(buf.readBlockPos());
      }

      public Rd<Optional<BlockPos>> createKey(int id) {
         return new Rd(id, this);
      }

      public Optional<BlockPos> copyValue(Optional<BlockPos> value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Optional)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Optional)var2);
      }
   };
   public static final Re<EnumFacing> FACING = new Re<EnumFacing>() {
      public void write(SA buf, EnumFacing value) {
         buf.writeEnumValue(value);
      }

      public EnumFacing read(SA buf) throws IOException {
         return (EnumFacing)buf.readEnumValue(EnumFacing.class);
      }

      public Rd<EnumFacing> createKey(int id) {
         return new Rd(id, this);
      }

      public EnumFacing copyValue(EnumFacing value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((EnumFacing)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (EnumFacing)var2);
      }
   };
   public static final Re<Optional<UUID>> OPTIONAL_UNIQUE_ID = new Re<Optional<UUID>>() {
      public void write(SA buf, Optional<UUID> value) {
         buf.writeBoolean(value.isPresent());
         if (value.isPresent()) {
            buf.writeUniqueId((UUID)value.get());
         }

      }

      public Optional<UUID> read(SA buf) throws IOException {
         return !buf.readBoolean() ? Optional.absent() : Optional.of(buf.readUniqueId());
      }

      public Rd<Optional<UUID>> createKey(int id) {
         return new Rd(id, this);
      }

      public Optional<UUID> copyValue(Optional<UUID> value) {
         return value;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((Optional)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (Optional)var2);
      }
   };
   public static final Re<QQ> COMPOUND_TAG = new Re<QQ>() {
      public void write(SA buf, QQ value) {
         buf.writeCompoundTag(value);
      }

      public QQ read(SA buf) throws IOException {
         return buf.readCompoundTag();
      }

      public Rd<QQ> createKey(int id) {
         return new Rd(id, this);
      }

      public QQ copyValue(QQ value) {
         return value.copy();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object copyValue(Object var1) {
         return this.copyValue((QQ)var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public Object read(SA var1) throws IOException {
         return this.read(var1);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void write(SA var1, Object var2) {
         this.write(var1, (QQ)var2);
      }
   };

   public Rt() {
   }

   public static void registerSerializer(Re<?> serializer) {
      REGISTRY.add(serializer);
   }

   @Nullable
   public static Re<?> getSerializer(int id) {
      return (Re)REGISTRY.get(id);
   }

   public static int getSerializerId(Re<?> serializer) {
      return REGISTRY.getId(serializer);
   }

   static {
      registerSerializer(BYTE);
      registerSerializer(VARINT);
      registerSerializer(FLOAT);
      registerSerializer(STRING);
      registerSerializer(TEXT_COMPONENT);
      registerSerializer(ITEM_STACK);
      registerSerializer(BOOLEAN);
      registerSerializer(ROTATIONS);
      registerSerializer(BLOCK_POS);
      registerSerializer(OPTIONAL_BLOCK_POS);
      registerSerializer(FACING);
      registerSerializer(OPTIONAL_UNIQUE_ID);
      registerSerializer(OPTIONAL_BLOCK_STATE);
      registerSerializer(COMPOUND_TAG);
   }
}
