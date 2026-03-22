package neo;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.MapData;

public class 0dq {
   private static InventoryPlayer Ib9ryOGobA(0cD var0) {
      return var0.inventory;
   }

   private static List _FMT1VeDXG/* $FF was: 6FMT1VeDXG*/(0cC var0) {
      return var0.gifExemplars;
   }

   private static EnumFacing C0H1CSuJeW() {
      return EnumFacing.EAST;
   }

   private static NonNullList EnUvMs7POI(InventoryPlayer var0) {
      return var0.mainInventory;
   }

   public static 0dn getCaptcha(0cC bot) {
      if (bot.getWorld() == null) {
         return null;
      } else {
         int mapItemSlot = bot.getMapSlot();
         if (mapItemSlot != (-9745 ^ -2771 ^ 6429 ^ -13792)) {
            Item mapItem = mapItemSlot == (21819 ^ -23095 ^ 31184 ^ -30449) ? NMz27hsTuS(bot).getHeldItemOffhand().getItem() : ((ItemStack)EnUvMs7POI(Ib9ryOGobA(rd9LrKA2Gf(bot))).get(mapItemSlot)).getItem();
            ItemMap map = (ItemMap)mapItem;
            MapData mapData = map.getMapData(mapItem.getDefaultInstance(), bot.getWorld());
            BufferedImage captchaFrame = mapToImage(getMapData(3OBhitIXyv(mapData)), (boolean)(22909 ^ -211 ^ 17349 ^ -6763));
            return new 0dn(0dt.imageToHash(captchaFrame), captchaFrame, new ArrayList(), bot);
         } else {
            Stream var10000 = 9WBAfIvO7H(bot.getWorld()).stream().filter((entity) -> {
               return (boolean)(entity instanceof EntityItemFrame && checkFrame(entity, vwBrVlQg6t(bot)) ? 15198 ^ -13107 ^ 6934 ^ -4988 : 30568 ^ -23453 ^ 8221 ^ -3306);
            });
            EntityItemFrame.class.getClass();
            List<EntityItemFrame> frames = (List)var10000.map(EntityItemFrame.class::cast).collect(Collectors.toList());
            if (frames.size() == 0) {
               var10000 = kCe1WTOmgu(bot.getWorld()).stream().filter((entity) -> {
                  return entity instanceof EntityItemFrame;
               });
               EntityItemFrame.class.getClass();
               frames = (List)var10000.map(EntityItemFrame.class::cast).collect(Collectors.toList());
            }

            if (frames.size() <= 0) {
               return null;
            } else {
               boolean rotation = checkRot(frames);
               double minXZ = Double.longBitsToDouble(-1973210723107885617L ^ -6538592572621083185L);
               double minY = Double.longBitsToDouble(861830821725774834L ^ 5344129464793211890L);
               double maxXZ = Double.longBitsToDouble(-99227951409875759L ^ 4575508461800699089L);
               double maxY = Double.longBitsToDouble(4515566097967292688L ^ -51083924186390256L);

               double frameY;
               for(Iterator var12 = frames.iterator(); var12.hasNext(); maxY = Math.max(maxY, frameY)) {
                  EntityItemFrame frame = (EntityItemFrame)var12.next();
                  double frameXZ = rotation ? 9atjrJof2Z(frame) : 9QyL3q2jJQ(frame);
                  frameY = O9QnBdwXPT(frame);
                  minXZ = Math.min(minXZ, frameXZ);
                  minY = Math.min(minY, frameY);
                  maxXZ = Math.max(maxXZ, frameXZ);
               }

               int width = (int)Math.abs(maxXZ - minXZ) + (29108 ^ -26779 ^ 7054 ^ -674);
               int height = (int)Math.abs(maxY - minY) + (8198 ^ -11878 ^ 20182 ^ -16565);
               BufferedImage image = new BufferedImage(width * (11719 ^ -11841 ^ 28971 ^ -29229), height * (29690 ^ -25934 ^ 22437 ^ -16787), 24960 ^ -19378 ^ 30148 ^ -24565);
               ArrayList<0dp> frameList = new ArrayList();
               Iterator var31 = frames.iterator();

               while(var31.hasNext()) {
                  EntityItemFrame frame = (EntityItemFrame)var31.next();
                  double frameX = rotation ? 2TpT7PTij2(frame) - minXZ : 5wQ6AI9OlT(frame) - minXZ;
                  double frameY = aVIud7wnTb(frame) - minY;
                  if (6y6F03AXCQ(frame) == Float.intBitsToFloat(238610 ^ 224755 ^ 3096 ^ 346125794 ^ 241043 ^ '鼶' ^ 251491 ^ 346117341) || DmLsguAqGs(frame) == Float.intBitsToFloat('跪' ^ 'ꂡ' ^ 3818 ^ 1295259744 ^ 20822 ^ 16740813 ^ '슥' ^ 260037119)) {
                     frameX = (double)width - frameX - Double.longBitsToDouble(8026339025220714228L ^ 5806064408927059700L);
                  }

                  ItemMap map = (ItemMap)frame.getDisplayedItem().getItem();
                  MapData mapData = map.getMapData(frame.getDisplayedItem(), bot.getWorld());
                  BufferedImage captchaFrame = mapToImage(getMapData(UeQLyupBAh(mapData)), (boolean)(4737 ^ -11627 ^ 31830 ^ -17342));
                  frameList.add(new 0dp(frame.getEntityId(), (int)((double)width - frameX - Double.longBitsToDouble(-5233710703866082185L ^ -8597899625511842697L)), (int)((double)height - frameY - Double.longBitsToDouble(-6902387722542499666L ^ -6933912919934093138L))));
                  image.createGraphics().drawImage(frame.getRotation() != 0 ? rotateImage(captchaFrame, frame.getRotation() * (9188 ^ -16806 ^ 8238 ^ -16950)) : captchaFrame, (int)((double)width - frameX - Double.longBitsToDouble(8366669199432086443L ^ 5470854639032857515L)) * (11427 ^ -10058 ^ 31172 ^ -29359), (int)((double)height - frameY - Double.longBitsToDouble(-5789024044997270284L ^ -8045327458309888780L)) * (19129 ^ -5388 ^ 18512 ^ -5987), (ImageObserver)null);
               }

               if (iFYOaQnKP8(WXILdyQQ8I())) {
                  if ((float)RUUMb5krYj(bot).size() < nN27IEVSGf(w6V7RoiFXB())) {
                     6FMT1VeDXG(bot).add(image);
                     return null;
                  }

                  image = 0dt.compareImages(lA24wmIv4v(bot));
                  B6oviMS6qG(bot).clear();
               }

               return new 0dn(0dt.imageToHash(image), image, frameList, bot);
            }
         }
      }
   }

   private static double BsFfdxZ17g(EntityItemFrame var0) {
      return var0.posX;
   }

   private static float _y6F03AXCQ/* $FF was: 6y6F03AXCQ*/(EntityItemFrame var0) {
      return var0.rotationYaw;
   }

   private static List B6oviMS6qG(0cC var0) {
      return var0.gifExemplars;
   }

   private static List RUUMb5krYj(0cC var0) {
      return var0.gifExemplars;
   }

   private static boolean iFYOaQnKP8(0bv var0) {
      return var0.value;
   }

   private static byte[] UeQLyupBAh(MapData var0) {
      return var0.colors;
   }

   private static double _TpT7PTij2/* $FF was: 2TpT7PTij2*/(EntityItemFrame var0) {
      return var0.posZ;
   }

   private static 0bv WXILdyQQ8I() {
      return 0ce.gifCaptchaFix;
   }

   private static 0cD vwBrVlQg6t(0cC var0) {
      return var0.player;
   }

   private static 0cD rd9LrKA2Gf(0cC var0) {
      return var0.player;
   }

   private static double _wQ6AI9OlT/* $FF was: 5wQ6AI9OlT*/(EntityItemFrame var0) {
      return var0.posX;
   }

   private static float DmLsguAqGs(EntityItemFrame var0) {
      return var0.rotationYaw;
   }

   private static double _atjrJof2Z/* $FF was: 9atjrJof2Z*/(EntityItemFrame var0) {
      return var0.posZ;
   }

   private static double aVIud7wnTb(EntityItemFrame var0) {
      return var0.posY;
   }

   private static 0cD NMz27hsTuS(0cC var0) {
      return var0.player;
   }

   private static 0bz w6V7RoiFXB() {
      return 0ce.gifFrames;
   }

   public static BufferedImage rotateImage(BufferedImage image, int rotate) {
      int width = image.getWidth();
      int height = image.getHeight();
      BufferedImage frame = new BufferedImage(height, width, 2019 ^ -3041 ^ 12572 ^ -15647);
      Graphics2D g2d = frame.createGraphics();
      g2d.rotate(Math.toRadians((double)rotate), (double)(width / (9035 ^ -20493 ^ 30736 ^ -2902)), (double)(height / (1510 ^ -23599 ^ 16574 ^ -6517)));
      g2d.drawImage(image, 8398 ^ -16110 ^ 16900 ^ -23592, 4630 ^ -3656 ^ 3992 ^ -5066, (ImageObserver)null);
      g2d.dispose();
      return frame;
   }

   private static EnumFacing U94nS4Ga7g() {
      return EnumFacing.SOUTH;
   }

   private static EnumFacing JtIkrDOR6l() {
      return EnumFacing.WEST;
   }

   public static boolean checkRot(List<EntityItemFrame> frames) {
      if (frames.size() <= (2842 ^ -19942 ^ 4899 ^ -21982)) {
         return (boolean)(20214 ^ -27830 ^ 28627 ^ -19858);
      } else {
         int firstPosX = (int)BsFfdxZ17g((EntityItemFrame)frames.get(17686 ^ -8191 ^ 24095 ^ -1272));
         Iterator var2 = frames.iterator();

         int currentPosX;
         do {
            if (!var2.hasNext()) {
               return (boolean)(151 ^ -30923 ^ 22409 ^ -12246);
            }

            EntityItemFrame frame = (EntityItemFrame)var2.next();
            currentPosX = (int)DcmYQe72By(frame);
         } while(currentPosX == firstPosX);

         return (boolean)(9137 ^ -18150 ^ 28489 ^ -2590);
      }
   }

   private static boolean checkFrame(Entity player, Entity frame) {
      return (boolean)((!player.getHorizontalFacing().equals(U94nS4Ga7g()) || !frame.getHorizontalFacing().equals(Bt64yZ818F())) && (!player.getHorizontalFacing().equals(JtIkrDOR6l()) || !frame.getHorizontalFacing().equals(NUd6nUWmXF())) && (!player.getHorizontalFacing().equals(71QYr7FQNh()) || !frame.getHorizontalFacing().equals(NSniTqGYB6())) && (!player.getHorizontalFacing().equals(C0H1CSuJeW()) || !frame.getHorizontalFacing().equals(MSsXtwzq3t())) ? 31795 ^ -858 ^ 15016 ^ -17859 : 25017 ^ -26571 ^ 28499 ^ -26914);
   }

   private static EnumFacing MSsXtwzq3t() {
      return EnumFacing.WEST;
   }

   private static EnumFacing NUd6nUWmXF() {
      return EnumFacing.EAST;
   }

   private static EnumFacing _1QYr7FQNh/* $FF was: 71QYr7FQNh*/() {
      return EnumFacing.NORTH;
   }

   private static EnumFacing Bt64yZ818F() {
      return EnumFacing.NORTH;
   }

   private static double O9QnBdwXPT(EntityItemFrame var0) {
      return var0.posY;
   }

   private static byte[] _OBhitIXyv/* $FF was: 3OBhitIXyv*/(MapData var0) {
      return var0.colors;
   }

   private static float nN27IEVSGf(0bz var0) {
      return var0.value;
   }

   private static List _WBAfIvO7H/* $FF was: 9WBAfIvO7H*/(0cU var0) {
      return var0.loadedEntityList;
   }

   private static double DcmYQe72By(EntityItemFrame var0) {
      return var0.posX;
   }

   public _dq/* $FF was: 0dq*/() {
   }

   private static List lA24wmIv4v(0cC var0) {
      return var0.gifExemplars;
   }

   private static EnumFacing NSniTqGYB6() {
      return EnumFacing.SOUTH;
   }

   private static double _QyL3q2jJQ/* $FF was: 9QyL3q2jJQ*/(EntityItemFrame var0) {
      return var0.posX;
   }

   public static int[] getMapData(byte[] data) {
      if (data == null) {
         return null;
      } else {
         int[] arr = new int[data.length];
         int[] colors = new int[arr.length];

         int a;
         int index;
         for(a = 4752 ^ -13428 ^ 17583 ^ -25165; a < (4934 ^ -10129 ^ 25759 ^ -20682); ++a) {
            for(index = 5718 ^ -20578 ^ 29589 ^ -13731; index < (29230 ^ -32527 ^ 25039 ^ -27760); ++index) {
               colors[a + index * (19276 ^ 23349 ^ 4315 ^ 34)] = data[a + index * (26965 ^ -1445 ^ 7901 ^ -29357)];
            }
         }

         for(a = 9855 ^ -25842 ^ 21089 ^ -4336; a < (9725 ^ 23185 ^ 881 ^ 15389); ++a) {
            index = colors[a] & (389 ^ -26715 ^ 16654 ^ -10287);
            if (index / (23512 ^ -26964 ^ 10629 ^ -6923) == 0) {
               arr[a] = (a + a / (5624 ^ -22337 ^ 18767 ^ -2936) & (29706 ^ -32239 ^ 19872 ^ -17478)) * (7672 ^ -30358 ^ 4792 ^ -31198) + (10761 ^ -5119 ^ 3480 ^ -13440) << (6193 ^ -19841 ^ 22526 ^ -600);
            } else {
               arr[a] = 1ciVXolQh9()[index / (14841 ^ -128 ^ 26417 ^ -24244)].getMapColor(index & (14237 ^ -9313 ^ 24530 ^ -19501));
            }

            arr[a] |= 32613 ^ 33525296 ^ 22276 ^ -16800175;
         }

         return arr;
      }
   }

   private static List kCe1WTOmgu(0cU var0) {
      return var0.loadedEntityList;
   }

   public static BufferedImage mapToImage(int[] mapTextureData, boolean monochrome) {
      try {
         int width = 15521 ^ -12884 ^ 526 ^ -3197;
         int height = 4102 ^ -24035 ^ 23142 ^ -5891;
         byte[] bdata = new byte[7570 ^ 125813 ^ 16506 ^ '뚝'];
         int byte_index = 14722 ^ -26302 ^ 17801 ^ -6839;
         int[] var6 = mapTextureData;
         int var7 = mapTextureData.length;

         for(int var8 = 21174 ^ -15527 ^ 5419 ^ -31548; var8 < var7; ++var8) {
            int color = var6[var8];
            byte r = (byte)(color >> (8875 ^ -15542 ^ 31387 ^ -25750) & (7670 ^ -6916 ^ 17975 ^ -16446));
            byte g = (byte)(color >> (20119 ^ -13538 ^ 10144 ^ -24031) & (24513 ^ 25165 ^ 15709 ^ 46));
            byte b = (byte)(color & (31658 ^ -15532 ^ 4371 ^ -22254));
            bdata[byte_index++] = r;
            bdata[byte_index++] = g;
            bdata[byte_index++] = b;
         }

         DataBuffer buffer = new DataBufferByte(bdata, bdata.length);
         int[] var10000;
         int[] bandOffsets;
         if (!monochrome) {
            var10000 = new int[24795 ^ -18307 ^ 7420 ^ -15271];
            var10000[20863 ^ -5136 ^ 12077 ^ -27230] = 13090 ^ -26897 ^ 29873 ^ -11908;
            var10000[12785 ^ -5559 ^ 23540 ^ -32691] = 23846 ^ -18942 ^ 29535 ^ -26502;
            var10000[9890 ^ -12442 ^ 15866 ^ -11204] = 17357 ^ -24088 ^ 2934 ^ -5807;
            bandOffsets = var10000;
         } else {
            var10000 = new int[21757 ^ -281 ^ 3522 ^ -22565];
            var10000[7506 ^ -18908 ^ 27843 ^ -14411] = 21387 ^ -29745 ^ 2360 ^ -11908;
            var10000[5863 ^ -4153 ^ 19829 ^ -19372] = 30017 ^ -20044 ^ 5980 ^ -11351;
            var10000[5570 ^ -31353 ^ 3507 ^ -25100] = 21048 ^ -30253 ^ 18023 ^ -25204;
            bandOffsets = var10000;
         }

         WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, (27739 ^ -9528 ^ 14587 ^ -29077) * width, 3747 ^ -18178 ^ 22168 ^ -7994, bandOffsets, (Point)null);
         ColorSpace cs = ColorModel.getRGBdefault().getColorSpace();
         ColorModel cm = new ComponentColorModel(cs, (boolean)(15810 ^ -3160 ^ 17001 ^ -29693), (boolean)(23347 ^ -23478 ^ 9693 ^ -9563), 9838 ^ -13793 ^ 25105 ^ -29087, 15183 ^ -1085 ^ 579 ^ -15665);
         return new BufferedImage(cm, raster, (boolean)(774 ^ -8108 ^ 15842 ^ -8528), (Hashtable)null);
      } catch (Exception var13) {
         Exception e = var13;
         e.printStackTrace();
         return null;
      }
   }

   private static MapColor[] _ciVXolQh9/* $FF was: 1ciVXolQh9*/() {
      return MapColor.COLORS;
   }
}
