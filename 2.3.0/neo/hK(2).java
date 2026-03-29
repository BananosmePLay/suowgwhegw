package neo;

public class hK {
   public static final hK[] COLORS = new hK[64];
   public static final hK[] BLOCK_COLORS = new hK[16];
   public static final hK AIR = new hK(0, 0);
   public static final hK GRASS = new hK(1, 8368696);
   public static final hK SAND = new hK(2, 16247203);
   public static final hK CLOTH = new hK(3, 13092807);
   public static final hK TNT = new hK(4, 16711680);
   public static final hK ICE = new hK(5, 10526975);
   public static final hK IRON = new hK(6, 10987431);
   public static final hK FOLIAGE = new hK(7, 31744);
   public static final hK SNOW = new hK(8, 16777215);
   public static final hK CLAY = new hK(9, 10791096);
   public static final hK DIRT = new hK(10, 9923917);
   public static final hK STONE = new hK(11, 7368816);
   public static final hK WATER = new hK(12, 4210943);
   public static final hK WOOD = new hK(13, 9402184);
   public static final hK QUARTZ = new hK(14, 16776437);
   public static final hK ADOBE = new hK(15, 14188339);
   public static final hK MAGENTA = new hK(16, 11685080);
   public static final hK LIGHT_BLUE = new hK(17, 6724056);
   public static final hK YELLOW = new hK(18, 15066419);
   public static final hK LIME = new hK(19, 8375321);
   public static final hK PINK = new hK(20, 15892389);
   public static final hK GRAY = new hK(21, 5000268);
   public static final hK SILVER = new hK(22, 10066329);
   public static final hK CYAN = new hK(23, 5013401);
   public static final hK PURPLE = new hK(24, 8339378);
   public static final hK BLUE = new hK(25, 3361970);
   public static final hK BROWN = new hK(26, 6704179);
   public static final hK GREEN = new hK(27, 6717235);
   public static final hK RED = new hK(28, 10040115);
   public static final hK BLACK = new hK(29, 1644825);
   public static final hK GOLD = new hK(30, 16445005);
   public static final hK DIAMOND = new hK(31, 6085589);
   public static final hK LAPIS = new hK(32, 4882687);
   public static final hK EMERALD = new hK(33, 55610);
   public static final hK OBSIDIAN = new hK(34, 8476209);
   public static final hK NETHERRACK = new hK(35, 7340544);
   public static final hK WHITE_STAINED_HARDENED_CLAY = new hK(36, 13742497);
   public static final hK ORANGE_STAINED_HARDENED_CLAY = new hK(37, 10441252);
   public static final hK MAGENTA_STAINED_HARDENED_CLAY = new hK(38, 9787244);
   public static final hK LIGHT_BLUE_STAINED_HARDENED_CLAY = new hK(39, 7367818);
   public static final hK YELLOW_STAINED_HARDENED_CLAY = new hK(40, 12223780);
   public static final hK LIME_STAINED_HARDENED_CLAY = new hK(41, 6780213);
   public static final hK PINK_STAINED_HARDENED_CLAY = new hK(42, 10505550);
   public static final hK GRAY_STAINED_HARDENED_CLAY = new hK(43, 3746083);
   public static final hK SILVER_STAINED_HARDENED_CLAY = new hK(44, 8874850);
   public static final hK CYAN_STAINED_HARDENED_CLAY = new hK(45, 5725276);
   public static final hK PURPLE_STAINED_HARDENED_CLAY = new hK(46, 8014168);
   public static final hK BLUE_STAINED_HARDENED_CLAY = new hK(47, 4996700);
   public static final hK BROWN_STAINED_HARDENED_CLAY = new hK(48, 4993571);
   public static final hK GREEN_STAINED_HARDENED_CLAY = new hK(49, 5001770);
   public static final hK RED_STAINED_HARDENED_CLAY = new hK(50, 9321518);
   public static final hK BLACK_STAINED_HARDENED_CLAY = new hK(51, 2430480);
   public int colorValue;
   public final int colorIndex;

   private hK(int index, int color) {
      if (index >= 0 && index <= 63) {
         this.colorIndex = index;
         this.colorValue = color;
         COLORS[index] = this;
      } else {
         throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
      }
   }

   public int getMapColor(int index) {
      int i = 220;
      if (index == 3) {
         i = 135;
      }

      if (index == 2) {
         i = 255;
      }

      if (index == 1) {
         i = 220;
      }

      if (index == 0) {
         i = 180;
      }

      int j = (this.colorValue >> 16 & 255) * i / 255;
      int k = (this.colorValue >> 8 & 255) * i / 255;
      int l = (this.colorValue & 255) * i / 255;
      return -16777216 | j << 16 | k << 8 | l;
   }

   public static hK getBlockColor(Om dyeColorIn) {
      return BLOCK_COLORS[dyeColorIn.getMetadata()];
   }

   static {
      BLOCK_COLORS[Om.WHITE.getMetadata()] = SNOW;
      BLOCK_COLORS[Om.ORANGE.getMetadata()] = ADOBE;
      BLOCK_COLORS[Om.MAGENTA.getMetadata()] = MAGENTA;
      BLOCK_COLORS[Om.LIGHT_BLUE.getMetadata()] = LIGHT_BLUE;
      BLOCK_COLORS[Om.YELLOW.getMetadata()] = YELLOW;
      BLOCK_COLORS[Om.LIME.getMetadata()] = LIME;
      BLOCK_COLORS[Om.PINK.getMetadata()] = PINK;
      BLOCK_COLORS[Om.GRAY.getMetadata()] = GRAY;
      BLOCK_COLORS[Om.SILVER.getMetadata()] = SILVER;
      BLOCK_COLORS[Om.CYAN.getMetadata()] = CYAN;
      BLOCK_COLORS[Om.PURPLE.getMetadata()] = PURPLE;
      BLOCK_COLORS[Om.BLUE.getMetadata()] = BLUE;
      BLOCK_COLORS[Om.BROWN.getMetadata()] = BROWN;
      BLOCK_COLORS[Om.GREEN.getMetadata()] = GREEN;
      BLOCK_COLORS[Om.RED.getMetadata()] = RED;
      BLOCK_COLORS[Om.BLACK.getMetadata()] = BLACK;
   }
}
