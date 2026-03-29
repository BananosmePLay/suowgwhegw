package net.minecraft.util.datafix;

import neo.HS;
import neo.HV;
import neo.IN;
import neo.IU;
import neo.IV;
import neo.IW;
import neo.IX;
import neo.IY;
import neo.IZ;
import neo.Ig;
import neo.JA;
import neo.JB;
import neo.JD;
import neo.JJ;
import neo.JK;
import neo.JR;
import neo.JW;
import neo.JX;
import neo.Jd;
import neo.Jg;
import neo.Ji;
import neo.Jj;
import neo.Jk;
import neo.Jn;
import neo.Jo;
import neo.Jz;
import neo.KD;
import neo.KG;
import neo.KH;
import neo.KN;
import neo.KO;
import neo.KW;
import neo.KX;
import neo.Kc;
import neo.Kd;
import neo.Kj;
import neo.Kk;
import neo.Ko;
import neo.LA;
import neo.LB;
import neo.LC;
import neo.LF;
import neo.LL;
import neo.LM;
import neo.LN;
import neo.LQ;
import neo.LY;
import neo.Lc;
import neo.Lf;
import neo.Lg;
import neo.Lh;
import neo.Lk;
import neo.Ll;
import neo.Lz;
import neo.ME;
import neo.MG;
import neo.MO;
import neo.MP;
import neo.MQ;
import neo.MV;
import neo.MY;
import neo.Mb;
import neo.Md;
import neo.Mf;
import neo.Mq;
import neo.Mu;
import neo.Mv;
import neo.Na;
import neo.Nb;
import neo.Nc;
import neo.Ne;
import neo.Nf;
import neo.QQ;
import neo.QW;
import neo.Qy;
import neo.YA;
import neo.YB;
import neo.YG;
import neo.YK;
import neo.YN;
import neo.Yl;
import neo.Yn;
import neo.Yt;
import neo.Yu;
import neo.Yz;
import neo.bav;
import neo.bfK;
import neo.bhY;
import neo.et;
import net.minecraft.util.datafix.fixes.AddBedTileEntity;
import net.minecraft.util.datafix.fixes.ArmorStandSilent;
import net.minecraft.util.datafix.fixes.BannerItemColor;
import net.minecraft.util.datafix.fixes.BedItemColor;
import net.minecraft.util.datafix.fixes.BookPagesStrictJSON;
import net.minecraft.util.datafix.fixes.CookedFishIDTypo;
import net.minecraft.util.datafix.fixes.ElderGuardianSplit;
import net.minecraft.util.datafix.fixes.EntityArmorAndHeld;
import net.minecraft.util.datafix.fixes.EntityHealth;
import net.minecraft.util.datafix.fixes.EntityId;
import net.minecraft.util.datafix.fixes.ForceVBOOn;
import net.minecraft.util.datafix.fixes.HorseSaddle;
import net.minecraft.util.datafix.fixes.HorseSplit;
import net.minecraft.util.datafix.fixes.ItemIntIDToString;
import net.minecraft.util.datafix.fixes.MinecartEntityTypes;
import net.minecraft.util.datafix.fixes.OptionsLowerCaseLanguage;
import net.minecraft.util.datafix.fixes.PaintingDirection;
import net.minecraft.util.datafix.fixes.PotionItems;
import net.minecraft.util.datafix.fixes.PotionWater;
import net.minecraft.util.datafix.fixes.RedundantChanceTags;
import net.minecraft.util.datafix.fixes.RidingToPassengers;
import net.minecraft.util.datafix.fixes.ShulkerBoxEntityColor;
import net.minecraft.util.datafix.fixes.ShulkerBoxItemColor;
import net.minecraft.util.datafix.fixes.ShulkerBoxTileColor;
import net.minecraft.util.datafix.fixes.SignStrictJSON;
import net.minecraft.util.datafix.fixes.SkeletonSplit;
import net.minecraft.util.datafix.fixes.SpawnEggNames;
import net.minecraft.util.datafix.fixes.SpawnerEntityTypes;
import net.minecraft.util.datafix.fixes.StringToUUID;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraft.util.datafix.fixes.TotemItemRename;
import net.minecraft.util.datafix.fixes.ZombieProfToType;
import net.minecraft.util.datafix.fixes.ZombieSplit;

public class DataFixesManager {
   public DataFixesManager() {
   }

   private static void registerFixes(DataFixer fixer) {
      fixer.registerFix(FixTypes.ENTITY, new EntityArmorAndHeld());
      fixer.registerFix(FixTypes.BLOCK_ENTITY, new SignStrictJSON());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new ItemIntIDToString());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new PotionItems());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new SpawnEggNames());
      fixer.registerFix(FixTypes.ENTITY, new MinecartEntityTypes());
      fixer.registerFix(FixTypes.BLOCK_ENTITY, new SpawnerEntityTypes());
      fixer.registerFix(FixTypes.ENTITY, new StringToUUID());
      fixer.registerFix(FixTypes.ENTITY, new EntityHealth());
      fixer.registerFix(FixTypes.ENTITY, new HorseSaddle());
      fixer.registerFix(FixTypes.ENTITY, new PaintingDirection());
      fixer.registerFix(FixTypes.ENTITY, new RedundantChanceTags());
      fixer.registerFix(FixTypes.ENTITY, new RidingToPassengers());
      fixer.registerFix(FixTypes.ENTITY, new ArmorStandSilent());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new BookPagesStrictJSON());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new CookedFishIDTypo());
      fixer.registerFix(FixTypes.ENTITY, new ZombieProfToType());
      fixer.registerFix(FixTypes.OPTIONS, new ForceVBOOn());
      fixer.registerFix(FixTypes.ENTITY, new ElderGuardianSplit());
      fixer.registerFix(FixTypes.ENTITY, new SkeletonSplit());
      fixer.registerFix(FixTypes.ENTITY, new ZombieSplit());
      fixer.registerFix(FixTypes.ENTITY, new HorseSplit());
      fixer.registerFix(FixTypes.BLOCK_ENTITY, new TileEntityId());
      fixer.registerFix(FixTypes.ENTITY, new EntityId());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new BannerItemColor());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new PotionWater());
      fixer.registerFix(FixTypes.ENTITY, new ShulkerBoxEntityColor());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new ShulkerBoxItemColor());
      fixer.registerFix(FixTypes.BLOCK_ENTITY, new ShulkerBoxTileColor());
      fixer.registerFix(FixTypes.OPTIONS, new OptionsLowerCaseLanguage());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new TotemItemRename());
      fixer.registerFix(FixTypes.CHUNK, new AddBedTileEntity());
      fixer.registerFix(FixTypes.ITEM_INSTANCE, new BedItemColor());
   }

   public static DataFixer createFixer() {
      DataFixer datafixer = new DataFixer(1343);
      bhY.registerFixes(datafixer);
      MG.registerFixesPlayerMP(datafixer);
      ME.registerFixesPlayer(datafixer);
      bav.registerFixes(datafixer);
      Qy.registerFixes(datafixer);
      bfK.registerFixes(datafixer);
      Ig.registerFixes(datafixer);
      IN.registerFixesArmorStand(datafixer);
      MO.registerFixesArrow(datafixer);
      Lz.registerFixesBat(datafixer);
      Jz.registerFixesBlaze(datafixer);
      JA.registerFixesCaveSpider(datafixer);
      LA.registerFixesChicken(datafixer);
      LB.registerFixesCow(datafixer);
      JB.registerFixesCreeper(datafixer);
      LC.registerFixesDonkey(datafixer);
      MP.registerFixesDragonFireball(datafixer);
      JD.registerFixesElderGuardian(datafixer);
      HS.registerFixesDragon(datafixer);
      JJ.registerFixesEnderman(datafixer);
      JK.registerFixesEndermite(datafixer);
      JR.registerFixesEvoker(datafixer);
      IW.registerFixesFallingBlock(datafixer);
      IX.registerFixesFireworkRocket(datafixer);
      JW.registerFixesGhast(datafixer);
      JX.registerFixesGiantZombie(datafixer);
      Kc.registerFixesGuardian(datafixer);
      LF.registerFixesHorse(datafixer);
      Kd.registerFixesHusk(datafixer);
      IY.registerFixesItem(datafixer);
      IZ.registerFixesItemFrame(datafixer);
      MV.registerFixesLargeFireball(datafixer);
      Kk.registerFixesMagmaCube(datafixer);
      Jd.registerFixesMinecartChest(datafixer);
      Jg.registerFixesMinecartCommand(datafixer);
      Jj.registerFixesMinecartFurnace(datafixer);
      Jk.registerFixesMinecartHopper(datafixer);
      Ji.registerFixesMinecartEmpty(datafixer);
      Jn.registerFixesMinecartMobSpawner(datafixer);
      Jo.registerFixesMinecartTNT(datafixer);
      LM.registerFixesMule(datafixer);
      LL.registerFixesMooshroom(datafixer);
      LN.registerFixesOcelot(datafixer);
      LQ.registerFixesPig(datafixer);
      Ko.registerFixesPigZombie(datafixer);
      LY.registerFixesRabbit(datafixer);
      Mb.registerFixesSheep(datafixer);
      KD.registerFixesShulker(datafixer);
      KG.registerFixesSilverfish(datafixer);
      KH.registerFixesSkeleton(datafixer);
      Md.registerFixesSkeletonHorse(datafixer);
      KN.registerFixesSlime(datafixer);
      Na.registerFixesSmallFireball(datafixer);
      KO.registerFixesSnowman(datafixer);
      Nb.registerFixesSnowball(datafixer);
      Nc.registerFixesSpectralArrow(datafixer);
      KW.registerFixesSpider(datafixer);
      Mf.registerFixesSquid(datafixer);
      KX.registerFixesStray(datafixer);
      MQ.registerFixesEgg(datafixer);
      IU.registerFixesEnderPearl(datafixer);
      IV.registerFixesExpBottle(datafixer);
      MY.registerFixesPotion(datafixer);
      Ne.registerFixesTippedArrow(datafixer);
      Lc.registerFixesVex(datafixer);
      Mq.registerFixesVillager(datafixer);
      Kj.registerFixesIronGolem(datafixer);
      Lf.registerFixesVindicator(datafixer);
      Lg.registerFixesWitch(datafixer);
      HV.registerFixesWither(datafixer);
      Lh.registerFixesWitherSkeleton(datafixer);
      Nf.registerFixesWitherSkull(datafixer);
      Mu.registerFixesWolf(datafixer);
      Lk.registerFixesZombie(datafixer);
      Mv.registerFixesZombieHorse(datafixer);
      Ll.registerFixesZombieVillager(datafixer);
      YK.registerFixesPiston(datafixer);
      Yz.registerFixesFlowerPot(datafixer);
      YA.registerFixesFurnace(datafixer);
      Yn.registerFixesChest(datafixer);
      Yt.registerFixes(datafixer);
      Yu.registerFixesDropper(datafixer);
      Yl.registerFixesBrewingStand(datafixer);
      YB.registerFixesHopper(datafixer);
      et.registerFixesJukebox(datafixer);
      YG.registerFixesMobSpawner(datafixer);
      YN.registerFixesShulkerBox(datafixer);
      registerFixes(datafixer);
      return datafixer;
   }

   public static QQ processItemStack(IDataFixer fixer, QQ compound, int version, String key) {
      if (compound.hasKey(key, 10)) {
         compound.setTag(key, fixer.process(FixTypes.ITEM_INSTANCE, compound.getCompoundTag(key), version));
      }

      return compound;
   }

   public static QQ processInventory(IDataFixer fixer, QQ compound, int version, String key) {
      if (compound.hasKey(key, 9)) {
         QW nbttaglist = compound.getTagList(key, 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            nbttaglist.set(i, fixer.process(FixTypes.ITEM_INSTANCE, nbttaglist.getCompoundTagAt(i), version));
         }
      }

      return compound;
   }
}
