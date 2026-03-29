package neo;

import java.lang.reflect.Field;

public class zK {
   public static zO BLOCK = new zO();
   public static zO ITEM = new zO();
   private static final zO BLOCK_VANILLA;
   private static final zO ITEM_VANILLA;
   public static bnL Attributes;
   public static bnN Attributes_DEFAULT_BAKED_FORMAT;
   private static final zO FORGE_BAKED;
   public static final zO OLDMODEL_POSITION_TEX_NORMAL;
   public static final zO PARTICLE_POSITION_TEX_COLOR_LMAP;
   public static final zO POSITION;
   public static final zO POSITION_COLOR;
   public static final zO POSITION_TEX;
   public static final zO POSITION_NORMAL;
   public static final zO POSITION_TEX_COLOR;
   public static final zO POSITION_TEX_NORMAL;
   public static final zO POSITION_TEX_LMAP_COLOR;
   public static final zO POSITION_TEX_COLOR_NORMAL;
   public static final zR POSITION_3F;
   public static final zR COLOR_4UB;
   public static final zR TEX_2F;
   public static final zR TEX_2S;
   public static final zR NORMAL_3B;
   public static final zR PADDING_1B;

   public zK() {
   }

   public static void updateVertexFormats() {
      if (XH.isShaders()) {
         BLOCK = bpA.makeDefVertexFormatBlock();
         ITEM = bpA.makeDefVertexFormatItem();
         if (Attributes_DEFAULT_BAKED_FORMAT.exists()) {
            bpA.setDefBakedFormat((zO)Attributes_DEFAULT_BAKED_FORMAT.getValue());
         }
      } else {
         BLOCK = BLOCK_VANILLA;
         ITEM = ITEM_VANILLA;
         if (Attributes_DEFAULT_BAKED_FORMAT.exists()) {
            bpA.copy(FORGE_BAKED, (zO)Attributes_DEFAULT_BAKED_FORMAT.getValue());
         }
      }

   }

   public static Object getFieldValue(bnN p_getFieldValue_0_) {
      try {
         Field field = p_getFieldValue_0_.getTargetField();
         if (field == null) {
            return null;
         } else {
            Object object = field.get((Object)null);
            return object;
         }
      } catch (Throwable var3) {
         Throwable throwable = var3;
         throwable.printStackTrace();
         return null;
      }
   }

   static {
      BLOCK_VANILLA = BLOCK;
      ITEM_VANILLA = ITEM;
      Attributes = new bnL("net.minecraftforge.client.model.Attributes");
      Attributes_DEFAULT_BAKED_FORMAT = new bnN(Attributes, "DEFAULT_BAKED_FORMAT");
      FORGE_BAKED = bpA.duplicate((zO)getFieldValue(Attributes_DEFAULT_BAKED_FORMAT));
      OLDMODEL_POSITION_TEX_NORMAL = new zO();
      PARTICLE_POSITION_TEX_COLOR_LMAP = new zO();
      POSITION = new zO();
      POSITION_COLOR = new zO();
      POSITION_TEX = new zO();
      POSITION_NORMAL = new zO();
      POSITION_TEX_COLOR = new zO();
      POSITION_TEX_NORMAL = new zO();
      POSITION_TEX_LMAP_COLOR = new zO();
      POSITION_TEX_COLOR_NORMAL = new zO();
      POSITION_3F = new zR(0, zP.FLOAT, zQ.POSITION, 3);
      COLOR_4UB = new zR(0, zP.UBYTE, zQ.COLOR, 4);
      TEX_2F = new zR(0, zP.FLOAT, zQ.UV, 2);
      TEX_2S = new zR(1, zP.SHORT, zQ.UV, 2);
      NORMAL_3B = new zR(0, zP.BYTE, zQ.NORMAL, 3);
      PADDING_1B = new zR(0, zP.BYTE, zQ.PADDING, 1);
      BLOCK.addElement(POSITION_3F);
      BLOCK.addElement(COLOR_4UB);
      BLOCK.addElement(TEX_2F);
      BLOCK.addElement(TEX_2S);
      ITEM.addElement(POSITION_3F);
      ITEM.addElement(COLOR_4UB);
      ITEM.addElement(TEX_2F);
      ITEM.addElement(NORMAL_3B);
      ITEM.addElement(PADDING_1B);
      OLDMODEL_POSITION_TEX_NORMAL.addElement(POSITION_3F);
      OLDMODEL_POSITION_TEX_NORMAL.addElement(TEX_2F);
      OLDMODEL_POSITION_TEX_NORMAL.addElement(NORMAL_3B);
      OLDMODEL_POSITION_TEX_NORMAL.addElement(PADDING_1B);
      PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(POSITION_3F);
      PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2F);
      PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(COLOR_4UB);
      PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2S);
      POSITION.addElement(POSITION_3F);
      POSITION_COLOR.addElement(POSITION_3F);
      POSITION_COLOR.addElement(COLOR_4UB);
      POSITION_TEX.addElement(POSITION_3F);
      POSITION_TEX.addElement(TEX_2F);
      POSITION_NORMAL.addElement(POSITION_3F);
      POSITION_NORMAL.addElement(NORMAL_3B);
      POSITION_NORMAL.addElement(PADDING_1B);
      POSITION_TEX_COLOR.addElement(POSITION_3F);
      POSITION_TEX_COLOR.addElement(TEX_2F);
      POSITION_TEX_COLOR.addElement(COLOR_4UB);
      POSITION_TEX_NORMAL.addElement(POSITION_3F);
      POSITION_TEX_NORMAL.addElement(TEX_2F);
      POSITION_TEX_NORMAL.addElement(NORMAL_3B);
      POSITION_TEX_NORMAL.addElement(PADDING_1B);
      POSITION_TEX_LMAP_COLOR.addElement(POSITION_3F);
      POSITION_TEX_LMAP_COLOR.addElement(TEX_2F);
      POSITION_TEX_LMAP_COLOR.addElement(TEX_2S);
      POSITION_TEX_LMAP_COLOR.addElement(COLOR_4UB);
      POSITION_TEX_COLOR_NORMAL.addElement(POSITION_3F);
      POSITION_TEX_COLOR_NORMAL.addElement(TEX_2F);
      POSITION_TEX_COLOR_NORMAL.addElement(COLOR_4UB);
      POSITION_TEX_COLOR_NORMAL.addElement(NORMAL_3B);
      POSITION_TEX_COLOR_NORMAL.addElement(PADDING_1B);
   }
}
