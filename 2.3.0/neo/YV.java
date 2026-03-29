package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class YV extends Yg {
   private String name = "";
   private String author = "";
   private String metadata = "";
   private BlockPos position = new BlockPos(0, 1, 0);
   private BlockPos size;
   private Mirror mirror;
   private Rotation rotation;
   private YU mode;
   private boolean ignoreEntities;
   private boolean powered;
   private boolean showAir;
   private boolean showBoundingBox;
   private float integrity;
   private long seed;

   public YV() {
      this.size = BlockPos.ORIGIN;
      this.mirror = Mirror.NONE;
      this.rotation = Rotation.NONE;
      this.mode = YU.DATA;
      this.ignoreEntities = true;
      this.showBoundingBox = true;
      this.integrity = 1.0F;
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setString("name", this.name);
      compound.setString("author", this.author);
      compound.setString("metadata", this.metadata);
      compound.setInteger("posX", this.position.getX());
      compound.setInteger("posY", this.position.getY());
      compound.setInteger("posZ", this.position.getZ());
      compound.setInteger("sizeX", this.size.getX());
      compound.setInteger("sizeY", this.size.getY());
      compound.setInteger("sizeZ", this.size.getZ());
      compound.setString("rotation", this.rotation.toString());
      compound.setString("mirror", this.mirror.toString());
      compound.setString("mode", this.mode.toString());
      compound.setBoolean("ignoreEntities", this.ignoreEntities);
      compound.setBoolean("powered", this.powered);
      compound.setBoolean("showair", this.showAir);
      compound.setBoolean("showboundingbox", this.showBoundingBox);
      compound.setFloat("integrity", this.integrity);
      compound.setLong("seed", this.seed);
      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.setName(compound.getString("name"));
      this.author = compound.getString("author");
      this.metadata = compound.getString("metadata");
      int i = MathHelper.clamp(compound.getInteger("posX"), -32, 32);
      int j = MathHelper.clamp(compound.getInteger("posY"), -32, 32);
      int k = MathHelper.clamp(compound.getInteger("posZ"), -32, 32);
      this.position = new BlockPos(i, j, k);
      int l = MathHelper.clamp(compound.getInteger("sizeX"), 0, 32);
      int i1 = MathHelper.clamp(compound.getInteger("sizeY"), 0, 32);
      int j1 = MathHelper.clamp(compound.getInteger("sizeZ"), 0, 32);
      this.size = new BlockPos(l, i1, j1);

      try {
         this.rotation = Rotation.valueOf(compound.getString("rotation"));
      } catch (IllegalArgumentException var11) {
         this.rotation = Rotation.NONE;
      }

      try {
         this.mirror = Mirror.valueOf(compound.getString("mirror"));
      } catch (IllegalArgumentException var10) {
         this.mirror = Mirror.NONE;
      }

      try {
         this.mode = YU.valueOf(compound.getString("mode"));
      } catch (IllegalArgumentException var9) {
         this.mode = YU.DATA;
      }

      this.ignoreEntities = compound.getBoolean("ignoreEntities");
      this.powered = compound.getBoolean("powered");
      this.showAir = compound.getBoolean("showair");
      this.showBoundingBox = compound.getBoolean("showboundingbox");
      if (compound.hasKey("integrity")) {
         this.integrity = compound.getFloat("integrity");
      } else {
         this.integrity = 1.0F;
      }

      this.seed = compound.getLong("seed");
      this.updateBlockState();
   }

   private void updateBlockState() {
      if (this.world != null) {
         BlockPos blockpos = this.getPos();
         in iblockstate = this.world.getBlockState(blockpos);
         if (iblockstate.getBlock() == Nk.STRUCTURE_BLOCK) {
            this.world.setBlockState(blockpos, iblockstate.withProperty(hh.MODE, this.mode), 2);
         }
      }

   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 7, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public boolean usedBy(ME player) {
      if (!player.canUseCommandBlock()) {
         return false;
      } else {
         if (player.getEntityWorld().isRemote) {
            player.openEditStructure(this);
         }

         return true;
      }
   }

   public String getName() {
      return this.name;
   }

   public void setName(String nameIn) {
      String s = nameIn;
      char[] var3 = ChatAllowedCharacters.ILLEGAL_STRUCTURE_CHARACTERS;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char c0 = var3[var5];
         s = s.replace(c0, '_');
      }

      this.name = s;
   }

   public void createdBy(Iw p_189720_1_) {
      if (!StringUtils.isNullOrEmpty(p_189720_1_.getName())) {
         this.author = p_189720_1_.getName();
      }

   }

   public BlockPos getPosition() {
      return this.position;
   }

   public void setPosition(BlockPos posIn) {
      this.position = posIn;
   }

   public BlockPos getStructureSize() {
      return this.size;
   }

   public void setSize(BlockPos sizeIn) {
      this.size = sizeIn;
   }

   public Mirror getMirror() {
      return this.mirror;
   }

   public void setMirror(Mirror mirrorIn) {
      this.mirror = mirrorIn;
   }

   public Rotation getRotation() {
      return this.rotation;
   }

   public void setRotation(Rotation rotationIn) {
      this.rotation = rotationIn;
   }

   public String getMetadata() {
      return this.metadata;
   }

   public void setMetadata(String metadataIn) {
      this.metadata = metadataIn;
   }

   public YU getMode() {
      return this.mode;
   }

   public void setMode(YU modeIn) {
      this.mode = modeIn;
      in iblockstate = this.world.getBlockState(this.getPos());
      if (iblockstate.getBlock() == Nk.STRUCTURE_BLOCK) {
         this.world.setBlockState(this.getPos(), iblockstate.withProperty(hh.MODE, modeIn), 2);
      }

   }

   public void nextMode() {
      switch (this.getMode()) {
         case SAVE:
            this.setMode(YU.LOAD);
            break;
         case LOAD:
            this.setMode(YU.CORNER);
            break;
         case CORNER:
            this.setMode(YU.DATA);
            break;
         case DATA:
            this.setMode(YU.SAVE);
      }

   }

   public boolean ignoresEntities() {
      return this.ignoreEntities;
   }

   public void setIgnoresEntities(boolean ignoreEntitiesIn) {
      this.ignoreEntities = ignoreEntitiesIn;
   }

   public float getIntegrity() {
      return this.integrity;
   }

   public void setIntegrity(float integrityIn) {
      this.integrity = integrityIn;
   }

   public long getSeed() {
      return this.seed;
   }

   public void setSeed(long seedIn) {
      this.seed = seedIn;
   }

   public boolean detectSize() {
      if (this.mode != YU.SAVE) {
         return false;
      } else {
         BlockPos blockpos = this.getPos();
         int i = true;
         BlockPos blockpos1 = new BlockPos(blockpos.getX() - 80, 0, blockpos.getZ() - 80);
         BlockPos blockpos2 = new BlockPos(blockpos.getX() + 80, 255, blockpos.getZ() + 80);
         List<YV> list = this.getNearbyCornerBlocks(blockpos1, blockpos2);
         List<YV> list1 = this.filterRelatedCornerBlocks(list);
         if (list1.size() < 1) {
            return false;
         } else {
            bdy structureboundingbox = this.calculateEnclosingBoundingBox(blockpos, list1);
            if (structureboundingbox.maxX - structureboundingbox.minX > 1 && structureboundingbox.maxY - structureboundingbox.minY > 1 && structureboundingbox.maxZ - structureboundingbox.minZ > 1) {
               this.position = new BlockPos(structureboundingbox.minX - blockpos.getX() + 1, structureboundingbox.minY - blockpos.getY() + 1, structureboundingbox.minZ - blockpos.getZ() + 1);
               this.size = new BlockPos(structureboundingbox.maxX - structureboundingbox.minX - 1, structureboundingbox.maxY - structureboundingbox.minY - 1, structureboundingbox.maxZ - structureboundingbox.minZ - 1);
               this.markDirty();
               in iblockstate = this.world.getBlockState(blockpos);
               this.world.notifyBlockUpdate(blockpos, iblockstate, iblockstate, 3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private List<YV> filterRelatedCornerBlocks(List<YV> p_184415_1_) {
      Iterable<YV> iterable = Iterables.filter(p_184415_1_, new Predicate<YV>() {
         public boolean apply(@Nullable YV p_apply_1_) {
            return p_apply_1_.mode == YU.CORNER && YV.this.name.equals(p_apply_1_.name);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((YV)var1);
         }
      });
      return Lists.newArrayList(iterable);
   }

   private List<YV> getNearbyCornerBlocks(BlockPos p_184418_1_, BlockPos p_184418_2_) {
      List<YV> list = Lists.newArrayList();
      Iterator var4 = BlockPos.getAllInBoxMutable(p_184418_1_, p_184418_2_).iterator();

      while(var4.hasNext()) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var4.next();
         in iblockstate = this.world.getBlockState(blockpos$mutableblockpos);
         if (iblockstate.getBlock() == Nk.STRUCTURE_BLOCK) {
            Yg tileentity = this.world.getTileEntity(blockpos$mutableblockpos);
            if (tileentity != null && tileentity instanceof YV) {
               list.add((YV)tileentity);
            }
         }
      }

      return list;
   }

   private bdy calculateEnclosingBoundingBox(BlockPos p_184416_1_, List<YV> p_184416_2_) {
      bdy structureboundingbox;
      if (p_184416_2_.size() > 1) {
         BlockPos blockpos = ((YV)p_184416_2_.get(0)).getPos();
         structureboundingbox = new bdy(blockpos, blockpos);
      } else {
         structureboundingbox = new bdy(p_184416_1_, p_184416_1_);
      }

      Iterator var7 = p_184416_2_.iterator();

      while(var7.hasNext()) {
         YV tileentitystructure = (YV)var7.next();
         BlockPos blockpos1 = tileentitystructure.getPos();
         if (blockpos1.getX() < structureboundingbox.minX) {
            structureboundingbox.minX = blockpos1.getX();
         } else if (blockpos1.getX() > structureboundingbox.maxX) {
            structureboundingbox.maxX = blockpos1.getX();
         }

         if (blockpos1.getY() < structureboundingbox.minY) {
            structureboundingbox.minY = blockpos1.getY();
         } else if (blockpos1.getY() > structureboundingbox.maxY) {
            structureboundingbox.maxY = blockpos1.getY();
         }

         if (blockpos1.getZ() < structureboundingbox.minZ) {
            structureboundingbox.minZ = blockpos1.getZ();
         } else if (blockpos1.getZ() > structureboundingbox.maxZ) {
            structureboundingbox.maxZ = blockpos1.getZ();
         }
      }

      return structureboundingbox;
   }

   public void writeCoordinates(ByteBuf buf) {
      buf.writeInt(this.pos.getX());
      buf.writeInt(this.pos.getY());
      buf.writeInt(this.pos.getZ());
   }

   public boolean save() {
      return this.save(true);
   }

   public boolean save(boolean writeToDisk) {
      if (this.mode == YU.SAVE && !this.world.isRemote && !StringUtils.isNullOrEmpty(this.name)) {
         BlockPos blockpos = this.getPos().add(this.position);
         bis worldserver = (bis)this.world;
         Xx minecraftserver = this.world.getMinecraftServer();
         bfL templatemanager = worldserver.getStructureTemplateManager();
         bfK template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(this.name));
         template.takeBlocksFromWorld(this.world, blockpos, this.size, !this.ignoreEntities, Nk.STRUCTURE_VOID);
         template.setAuthor(this.author);
         return !writeToDisk || templatemanager.writeTemplate(minecraftserver, new ResourceLocation(this.name));
      } else {
         return false;
      }
   }

   public boolean load() {
      return this.load(true);
   }

   public boolean load(boolean requireMatchingSize) {
      if (this.mode == YU.LOAD && !this.world.isRemote && !StringUtils.isNullOrEmpty(this.name)) {
         BlockPos blockpos = this.getPos();
         BlockPos blockpos1 = blockpos.add(this.position);
         bis worldserver = (bis)this.world;
         Xx minecraftserver = this.world.getMinecraftServer();
         bfL templatemanager = worldserver.getStructureTemplateManager();
         bfK template = templatemanager.get(minecraftserver, new ResourceLocation(this.name));
         if (template == null) {
            return false;
         } else {
            if (!StringUtils.isNullOrEmpty(template.getAuthor())) {
               this.author = template.getAuthor();
            }

            BlockPos blockpos2 = template.getSize();
            boolean flag = this.size.equals(blockpos2);
            if (!flag) {
               this.size = blockpos2;
               this.markDirty();
               in iblockstate = this.world.getBlockState(blockpos);
               this.world.notifyBlockUpdate(blockpos, iblockstate, iblockstate, 3);
            }

            if (requireMatchingSize && !flag) {
               return false;
            } else {
               bfD placementsettings = (new bfD()).setMirror(this.mirror).setRotation(this.rotation).setIgnoreEntities(this.ignoreEntities).setChunk((ChunkPos)null).setReplacedBlock((co)null).setIgnoreStructureBlock(false);
               if (this.integrity < 1.0F) {
                  placementsettings.setIntegrity(MathHelper.clamp(this.integrity, 0.0F, 1.0F)).setSeed(this.seed);
               }

               template.addBlocksToWorldChunk(this.world, blockpos1, placementsettings);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public void unloadStructure() {
      bis worldserver = (bis)this.world;
      bfL templatemanager = worldserver.getStructureTemplateManager();
      templatemanager.remove(new ResourceLocation(this.name));
   }

   public boolean isStructureLoadable() {
      if (this.mode == YU.LOAD && !this.world.isRemote) {
         bis worldserver = (bis)this.world;
         Xx minecraftserver = this.world.getMinecraftServer();
         bfL templatemanager = worldserver.getStructureTemplateManager();
         return templatemanager.get(minecraftserver, new ResourceLocation(this.name)) != null;
      } else {
         return false;
      }
   }

   public boolean isPowered() {
      return this.powered;
   }

   public void setPowered(boolean poweredIn) {
      this.powered = poweredIn;
   }

   public boolean showsAir() {
      return this.showAir;
   }

   public void setShowAir(boolean showAirIn) {
      this.showAir = showAirIn;
   }

   public boolean showsBoundingBox() {
      return this.showBoundingBox;
   }

   public void setShowBoundingBox(boolean showBoundingBoxIn) {
      this.showBoundingBox = showBoundingBoxIn;
   }

   @Nullable
   public ITextComponent getDisplayName() {
      return new TextComponentTranslation("structure_block.hover." + YU.access$200(this.mode), new Object[]{this.mode == YU.DATA ? this.metadata : this.name});
   }
}
