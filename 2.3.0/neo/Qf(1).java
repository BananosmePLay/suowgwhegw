package neo;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class Qf extends OL {
   private static final Map<SoundEvent, Qf> RECORDS = Maps.newHashMap();
   private final SoundEvent sound;
   private final String displayName;

   protected Qf(String recordName, SoundEvent soundIn) {
      this.displayName = "item.record." + recordName + ".desc";
      this.sound = soundIn;
      this.maxStackSize = 1;
      this.setCreativeTab(EN.MISC);
      RECORDS.put(this.sound, this);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() == Nk.JUKEBOX && !(Boolean)iblockstate.getValue(et.HAS_RECORD)) {
         if (!worldIn.isRemote) {
            Qy itemstack = player.getHeldItem(hand);
            ((et)Nk.JUKEBOX).insertRecord(worldIn, pos, iblockstate, itemstack);
            worldIn.playEvent((ME)null, 1010, pos, OL.getIdFromItem(this));
            itemstack.shrink(1);
            player.addStat(XV.RECORD_PLAYED);
         }

         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.PASS;
      }
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      tooltip.add(this.getRecordNameLocal());
   }

   public String getRecordNameLocal() {
      return I18n.translateToLocal(this.displayName);
   }

   public On getRarity(Qy stack) {
      return On.RARE;
   }

   @Nullable
   public static Qf getBySound(SoundEvent soundIn) {
      return (Qf)RECORDS.get(soundIn);
   }

   public SoundEvent getSound() {
      return this.sound;
   }
}
