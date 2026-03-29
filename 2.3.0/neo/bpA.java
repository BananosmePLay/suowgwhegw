package neo;

public class bpA {
   public static final int vertexSizeBlock = 14;
   public static final int offsetMidTexCoord = 8;
   public static final int offsetTangent = 10;
   public static final int offsetEntity = 12;
   public static final zO defVertexFormatTextured = makeDefVertexFormatTextured();

   public bpA() {
   }

   public static zO makeDefVertexFormatBlock() {
      zO vertexformat = new zO();
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.POSITION, 3));
      vertexformat.addElement(new zR(0, zP.UBYTE, zQ.COLOR, 4));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.UV, 2));
      vertexformat.addElement(new zR(1, zP.SHORT, zQ.UV, 2));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.NORMAL, 3));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.PADDING, 1));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.PADDING, 2));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      return vertexformat;
   }

   public static zO makeDefVertexFormatItem() {
      zO vertexformat = new zO();
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.POSITION, 3));
      vertexformat.addElement(new zR(0, zP.UBYTE, zQ.COLOR, 4));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.UV, 2));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 2));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.NORMAL, 3));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.PADDING, 1));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.PADDING, 2));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      return vertexformat;
   }

   public static zO makeDefVertexFormatTextured() {
      zO vertexformat = new zO();
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.POSITION, 3));
      vertexformat.addElement(new zR(0, zP.UBYTE, zQ.PADDING, 4));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.UV, 2));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 2));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.NORMAL, 3));
      vertexformat.addElement(new zR(0, zP.BYTE, zQ.PADDING, 1));
      vertexformat.addElement(new zR(0, zP.FLOAT, zQ.PADDING, 2));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      vertexformat.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      return vertexformat;
   }

   public static void setDefBakedFormat(zO vf) {
      if (vf != null) {
         vf.clear();
         vf.addElement(new zR(0, zP.FLOAT, zQ.POSITION, 3));
         vf.addElement(new zR(0, zP.UBYTE, zQ.COLOR, 4));
         vf.addElement(new zR(0, zP.FLOAT, zQ.UV, 2));
         vf.addElement(new zR(0, zP.SHORT, zQ.PADDING, 2));
         vf.addElement(new zR(0, zP.BYTE, zQ.NORMAL, 3));
         vf.addElement(new zR(0, zP.BYTE, zQ.PADDING, 1));
         vf.addElement(new zR(0, zP.FLOAT, zQ.PADDING, 2));
         vf.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
         vf.addElement(new zR(0, zP.SHORT, zQ.PADDING, 4));
      }

   }

   public static zO duplicate(zO src) {
      if (src == null) {
         return null;
      } else {
         zO vertexformat = new zO();
         copy(src, vertexformat);
         return vertexformat;
      }
   }

   public static void copy(zO src, zO dst) {
      if (src != null && dst != null) {
         dst.clear();

         for(int i = 0; i < src.getElementCount(); ++i) {
            dst.addElement(src.getElement(i));
         }
      }

   }
}
