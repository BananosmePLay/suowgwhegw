package neo;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Rb {
   private static final Logger LOGGER = LogManager.getLogger();

   public Rb() {
   }

   @Nullable
   public static GameProfile readGameProfileFromNBT(QQ compound) {
      String s = null;
      String s1 = null;
      if (compound.hasKey("Name", 8)) {
         s = compound.getString("Name");
      }

      if (compound.hasKey("Id", 8)) {
         s1 = compound.getString("Id");
      }

      try {
         UUID uuid;
         try {
            uuid = UUID.fromString(s1);
         } catch (Throwable var12) {
            uuid = null;
         }

         GameProfile gameprofile = new GameProfile(uuid, s);
         if (compound.hasKey("Properties", 10)) {
            QQ nbttagcompound = compound.getCompoundTag("Properties");
            Iterator var6 = nbttagcompound.getKeySet().iterator();

            while(var6.hasNext()) {
               String s2 = (String)var6.next();
               QW nbttaglist = nbttagcompound.getTagList(s2, 10);

               for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                  QQ nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                  String s3 = nbttagcompound1.getString("Value");
                  if (nbttagcompound1.hasKey("Signature", 8)) {
                     gameprofile.getProperties().put(s2, new Property(s2, s3, nbttagcompound1.getString("Signature")));
                  } else {
                     gameprofile.getProperties().put(s2, new Property(s2, s3));
                  }
               }
            }
         }

         return gameprofile;
      } catch (Throwable var13) {
         return null;
      }
   }

   public static QQ writeGameProfile(QQ tagCompound, GameProfile profile) {
      if (!StringUtils.isNullOrEmpty(profile.getName())) {
         tagCompound.setString("Name", profile.getName());
      }

      if (profile.getId() != null) {
         tagCompound.setString("Id", profile.getId().toString());
      }

      if (!profile.getProperties().isEmpty()) {
         QQ nbttagcompound = new QQ();
         Iterator var3 = profile.getProperties().keySet().iterator();

         while(var3.hasNext()) {
            String s = (String)var3.next();
            QW nbttaglist = new QW();

            QQ nbttagcompound1;
            for(Iterator var6 = profile.getProperties().get(s).iterator(); var6.hasNext(); nbttaglist.appendTag(nbttagcompound1)) {
               Property property = (Property)var6.next();
               nbttagcompound1 = new QQ();
               nbttagcompound1.setString("Value", property.getValue());
               if (property.hasSignature()) {
                  nbttagcompound1.setString("Signature", property.getSignature());
               }
            }

            nbttagcompound.setTag(s, nbttaglist);
         }

         tagCompound.setTag("Properties", nbttagcompound);
      }

      return tagCompound;
   }

   @VisibleForTesting
   public static boolean areNBTEquals(QH nbt1, QH nbt2, boolean compareTagList) {
      if (nbt1 == nbt2) {
         return true;
      } else if (nbt1 == null) {
         return true;
      } else if (nbt2 == null) {
         return false;
      } else if (!nbt1.getClass().equals(nbt2.getClass())) {
         return false;
      } else if (nbt1 instanceof QQ) {
         QQ nbttagcompound = (QQ)nbt1;
         QQ nbttagcompound1 = (QQ)nbt2;
         Iterator var11 = nbttagcompound.getKeySet().iterator();

         String s;
         QH nbtbase1;
         do {
            if (!var11.hasNext()) {
               return true;
            }

            s = (String)var11.next();
            nbtbase1 = nbttagcompound.getTag(s);
         } while(areNBTEquals(nbtbase1, nbttagcompound1.getTag(s), compareTagList));

         return false;
      } else if (nbt1 instanceof QW && compareTagList) {
         QW nbttaglist = (QW)nbt1;
         QW nbttaglist1 = (QW)nbt2;
         if (nbttaglist.isEmpty()) {
            return nbttaglist1.isEmpty();
         } else {
            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               QH nbtbase = nbttaglist.get(i);
               boolean flag = false;

               for(int j = 0; j < nbttaglist1.tagCount(); ++j) {
                  if (areNBTEquals(nbtbase, nbttaglist1.get(j), compareTagList)) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return nbt1.equals(nbt2);
      }
   }

   public static QQ createUUIDTag(UUID uuid) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setLong("M", uuid.getMostSignificantBits());
      nbttagcompound.setLong("L", uuid.getLeastSignificantBits());
      return nbttagcompound;
   }

   public static UUID getUUIDFromTag(QQ tag) {
      return new UUID(tag.getLong("M"), tag.getLong("L"));
   }

   public static BlockPos getPosFromTag(QQ tag) {
      return new BlockPos(tag.getInteger("X"), tag.getInteger("Y"), tag.getInteger("Z"));
   }

   public static QQ createPosTag(BlockPos pos) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setInteger("X", pos.getX());
      nbttagcompound.setInteger("Y", pos.getY());
      nbttagcompound.setInteger("Z", pos.getZ());
      return nbttagcompound;
   }

   public static in readBlockState(QQ tag) {
      if (!tag.hasKey("Name", 8)) {
         return Nk.AIR.getDefaultState();
      } else {
         co block = (co)co.REGISTRY.getObject(new ResourceLocation(tag.getString("Name")));
         in iblockstate = block.getDefaultState();
         if (tag.hasKey("Properties", 10)) {
            QQ nbttagcompound = tag.getCompoundTag("Properties");
            ii blockstatecontainer = block.getBlockState();
            Iterator var5 = nbttagcompound.getKeySet().iterator();

            while(var5.hasNext()) {
               String s = (String)var5.next();
               hT<?> iproperty = blockstatecontainer.getProperty(s);
               if (iproperty != null) {
                  iblockstate = setValueHelper(iblockstate, iproperty, s, nbttagcompound, tag);
               }
            }
         }

         return iblockstate;
      }
   }

   private static <T extends Comparable<T>> in setValueHelper(in p_193590_0_, hT<T> p_193590_1_, String p_193590_2_, QQ p_193590_3_, QQ p_193590_4_) {
      Optional<T> optional = p_193590_1_.parseValue(p_193590_3_.getString(p_193590_2_));
      if (optional.isPresent()) {
         return p_193590_0_.withProperty(p_193590_1_, (Comparable)optional.get());
      } else {
         LOGGER.warn("Unable to read property: {} with value: {} for blockstate: {}", p_193590_2_, p_193590_3_.getString(p_193590_2_), p_193590_4_.toString());
         return p_193590_0_;
      }
   }

   public static QQ writeBlockState(QQ tag, in state) {
      tag.setString("Name", ((ResourceLocation)co.REGISTRY.getNameForObject(state.getBlock())).toString());
      if (!state.getProperties().isEmpty()) {
         QQ nbttagcompound = new QQ();
         UnmodifiableIterator unmodifiableiterator = state.getProperties().entrySet().iterator();

         while(unmodifiableiterator.hasNext()) {
            Map.Entry<hT<?>, Comparable<?>> entry = (Map.Entry)unmodifiableiterator.next();
            hT<?> iproperty = (hT)entry.getKey();
            nbttagcompound.setString(iproperty.getName(), getName(iproperty, (Comparable)entry.getValue()));
         }

         tag.setTag("Properties", nbttagcompound);
      }

      return tag;
   }

   private static <T extends Comparable<T>> String getName(hT<T> p_190010_0_, Comparable<?> p_190010_1_) {
      return p_190010_0_.getName(p_190010_1_);
   }
}
