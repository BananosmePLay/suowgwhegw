package neo;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bcZ extends bcY {
   private static final ResourceLocation IGLOO_TOP_ID = new ResourceLocation("igloo/igloo_top");
   private static final ResourceLocation IGLOO_MIDDLE_ID = new ResourceLocation("igloo/igloo_middle");
   private static final ResourceLocation IGLOO_BOTTOM_ID = new ResourceLocation("igloo/igloo_bottom");

   public bcZ() {
   }

   public bcZ(Random rand, int x, int z) {
      super(rand, x, 64, z, 7, 5, 8);
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -1)) {
         return false;
      } else {
         bdy structureboundingbox = this.getBoundingBox();
         BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
         Rotation[] arotation = Rotation.values();
         Xx minecraftserver = worldIn.getMinecraftServer();
         bfL templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
         bfD placementsettings = (new bfD()).setRotation(arotation[randomIn.nextInt(arotation.length)]).setReplacedBlock(Nk.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
         bfK template = templatemanager.getTemplate(minecraftserver, IGLOO_TOP_ID);
         template.addBlocksToWorldChunk(worldIn, blockpos, placementsettings);
         if (randomIn.nextDouble() < 0.5) {
            bfK template1 = templatemanager.getTemplate(minecraftserver, IGLOO_MIDDLE_ID);
            bfK template2 = templatemanager.getTemplate(minecraftserver, IGLOO_BOTTOM_ID);
            int i = randomIn.nextInt(8) + 4;

            for(int j = 0; j < i; ++j) {
               BlockPos blockpos1 = template.calculateConnectedPos(placementsettings, new BlockPos(3, -1 - j * 3, 5), placementsettings, new BlockPos(1, 2, 1));
               template1.addBlocksToWorldChunk(worldIn, blockpos.add(blockpos1), placementsettings);
            }

            BlockPos blockpos4 = blockpos.add(template.calculateConnectedPos(placementsettings, new BlockPos(3, -1 - i * 3, 5), placementsettings, new BlockPos(3, 5, 7)));
            template2.addBlocksToWorldChunk(worldIn, blockpos4, placementsettings);
            Map<BlockPos, String> map = template2.getDataBlocks(blockpos4, placementsettings);
            Iterator var16 = map.entrySet().iterator();

            while(var16.hasNext()) {
               Map.Entry<BlockPos, String> entry = (Map.Entry)var16.next();
               if ("chest".equals(entry.getValue())) {
                  BlockPos blockpos2 = (BlockPos)entry.getKey();
                  worldIn.setBlockState(blockpos2, Nk.AIR.getDefaultState(), 3);
                  Yg tileentity = worldIn.getTileEntity(blockpos2.down());
                  if (tileentity instanceof Yn) {
                     ((Yn)tileentity).setLootTable(bhq.CHESTS_IGLOO_CHEST, randomIn.nextLong());
                  }
               }
            }
         } else {
            BlockPos blockpos3 = bfK.transformedBlockPos(placementsettings, new BlockPos(3, 0, 5));
            worldIn.setBlockState(blockpos.add(blockpos3), Nk.SNOW.getDefaultState(), 3);
         }

         return true;
      }
   }
}
