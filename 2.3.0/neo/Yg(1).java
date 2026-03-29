package neo;

import javax.annotation.Nullable;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Yg {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final RegistryNamespaced<ResourceLocation, Class<? extends Yg>> REGISTRY = new RegistryNamespaced();
   protected bij world;
   protected BlockPos pos;
   protected boolean tileEntityInvalid;
   private int blockMetadata;
   protected co blockType;

   public Yg() {
      this.pos = BlockPos.ORIGIN;
      this.blockMetadata = -1;
   }

   private static void register(String id, Class<? extends Yg> clazz) {
      REGISTRY.putObject(new ResourceLocation(id), clazz);
   }

   @Nullable
   public static ResourceLocation getKey(Class<? extends Yg> clazz) {
      return (ResourceLocation)REGISTRY.getNameForObject(clazz);
   }

   public bij getWorld() {
      return this.world;
   }

   public void setWorld(bij worldIn) {
      this.world = worldIn;
   }

   public boolean hasWorld() {
      return this.world != null;
   }

   public void readFromNBT(QQ compound) {
      this.pos = new BlockPos(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
   }

   public QQ writeToNBT(QQ compound) {
      return this.writeInternal(compound);
   }

   private QQ writeInternal(QQ compound) {
      ResourceLocation resourcelocation = (ResourceLocation)REGISTRY.getNameForObject(this.getClass());
      if (resourcelocation == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         compound.setString("id", resourcelocation.toString());
         compound.setInteger("x", this.pos.getX());
         compound.setInteger("y", this.pos.getY());
         compound.setInteger("z", this.pos.getZ());
         return compound;
      }
   }

   @Nullable
   public static Yg create(bij worldIn, QQ compound) {
      Yg tileentity = null;
      String s = compound.getString("id");

      Throwable throwable;
      try {
         Class<? extends Yg> oclass = (Class)REGISTRY.getObject(new ResourceLocation(s));
         if (oclass != null) {
            tileentity = (Yg)oclass.newInstance();
         }
      } catch (Throwable var6) {
         throwable = var6;
         LOGGER.error("Failed to create block entity {}", s, throwable);
      }

      if (tileentity != null) {
         try {
            tileentity.setWorldCreate(worldIn);
            tileentity.readFromNBT(compound);
         } catch (Throwable var5) {
            throwable = var5;
            LOGGER.error("Failed to load data for block entity {}", s, throwable);
            tileentity = null;
         }
      } else {
         LOGGER.warn("Skipping BlockEntity with id {}", s);
      }

      return tileentity;
   }

   protected void setWorldCreate(bij worldIn) {
   }

   public int getBlockMetadata() {
      if (this.blockMetadata == -1) {
         in iblockstate = this.world.getBlockState(this.pos);
         this.blockMetadata = iblockstate.getBlock().getMetaFromState(iblockstate);
      }

      return this.blockMetadata;
   }

   public void markDirty() {
      if (this.world != null) {
         in iblockstate = this.world.getBlockState(this.pos);
         this.blockMetadata = iblockstate.getBlock().getMetaFromState(iblockstate);
         this.world.markChunkDirty(this.pos, this);
         if (this.getBlockType() != Nk.AIR) {
            this.world.updateComparatorOutputLevel(this.pos, this.getBlockType());
         }
      }

   }

   public double getDistanceSq(double x, double y, double z) {
      double d0 = (double)this.pos.getX() + 0.5 - x;
      double d1 = (double)this.pos.getY() + 0.5 - y;
      double d2 = (double)this.pos.getZ() + 0.5 - z;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public double getMaxRenderDistanceSquared() {
      return 4096.0;
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public co getBlockType() {
      if (this.blockType == null && this.world != null) {
         this.blockType = this.world.getBlockState(this.pos).getBlock();
      }

      return this.blockType;
   }

   @Nullable
   public Vg getUpdatePacket() {
      return null;
   }

   public QQ getUpdateTag() {
      return this.writeInternal(new QQ());
   }

   public boolean isInvalid() {
      return this.tileEntityInvalid;
   }

   public void invalidate() {
      this.tileEntityInvalid = true;
   }

   public void validate() {
      this.tileEntityInvalid = false;
   }

   public boolean receiveClientEvent(int id, int type) {
      return false;
   }

   public void updateContainingBlockInfo() {
      this.blockType = null;
      this.blockMetadata = -1;
   }

   public void addInfoToCrashReport(Ey reportCategory) {
      reportCategory.addDetail("Name", new Ez<String>() {
         public String call() throws Exception {
            return Yg.REGISTRY.getNameForObject(Yg.this.getClass()) + " // " + Yg.this.getClass().getCanonicalName();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      if (this.world != null) {
         Ey.addBlockInfo(reportCategory, this.pos, this.getBlockType(), this.getBlockMetadata());
         reportCategory.addDetail("Actual block type", new Ez<String>() {
            public String call() throws Exception {
               int i = co.getIdFromBlock(Yg.this.world.getBlockState(Yg.this.pos).getBlock());

               try {
                  return String.format("ID #%d (%s // %s)", i, co.getBlockById(i).getTranslationKey(), co.getBlockById(i).getClass().getCanonicalName());
               } catch (Throwable var3) {
                  return "ID #" + i;
               }
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         reportCategory.addDetail("Actual block data value", new Ez<String>() {
            public String call() throws Exception {
               in iblockstate = Yg.this.world.getBlockState(Yg.this.pos);
               int i = iblockstate.getBlock().getMetaFromState(iblockstate);
               if (i < 0) {
                  return "Unknown? (Got " + i + ")";
               } else {
                  String s = String.format("%4s", Integer.toBinaryString(i)).replace(" ", "0");
                  return String.format("%1$d / 0x%1$X / 0b%2$s", i, s);
               }
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
      }

   }

   public void setPos(BlockPos posIn) {
      this.pos = posIn.toImmutable();
   }

   public boolean onlyOpsCanSetNbt() {
      return false;
   }

   @Nullable
   public ITextComponent getDisplayName() {
      return null;
   }

   public void rotate(Rotation rotationIn) {
   }

   public void mirror(Mirror mirrorIn) {
   }

   static {
      register("furnace", YA.class);
      register("chest", Yn.class);
      register("ender_chest", Yw.class);
      register("jukebox", es.class);
      register("dispenser", Yt.class);
      register("dropper", Yu.class);
      register("sign", YQ.class);
      register("mob_spawner", YG.class);
      register("noteblock", YH.class);
      register("piston", YK.class);
      register("brewing_stand", Yl.class);
      register("enchanting_table", Yv.class);
      register("end_portal", Yy.class);
      register("beacon", Yj.class);
      register("skull", YR.class);
      register("daylight_detector", Ys.class);
      register("hopper", YB.class);
      register("comparator", Yr.class);
      register("flower_pot", Yz.class);
      register("banner", Yh.class);
      register("structure_block", YV.class);
      register("end_gateway", Yx.class);
      register("command_block", Yq.class);
      register("shulker_box", YN.class);
      register("bed", Yk.class);
   }
}
