package neo;

import java.nio.ByteBuffer;
import java.util.List;

public class zV {
   public zV() {
   }

   public void draw(tN bufferBuilderIn) {
      if (bufferBuilderIn.getVertexCount() > 0) {
         if (bufferBuilderIn.getDrawMode() == 7 && XH.isQuadsToTriangles()) {
            bufferBuilderIn.quadsToTriangles();
         }

         zO vertexformat = bufferBuilderIn.getVertexFormat();
         int i = vertexformat.getSize();
         ByteBuffer bytebuffer = bufferBuilderIn.getByteBuffer();
         List<zR> list = vertexformat.getElements();
         boolean flag = bnK.ForgeVertexFormatElementEnumUseage_preDraw.exists();
         boolean flag1 = bnK.ForgeVertexFormatElementEnumUseage_postDraw.exists();

         int j1;
         int i1;
         for(j1 = 0; j1 < list.size(); ++j1) {
            zR vertexformatelement = (zR)list.get(j1);
            zQ vertexformatelement$enumusage = vertexformatelement.getUsage();
            if (flag) {
               bnK.callVoid(vertexformatelement$enumusage, bnK.ForgeVertexFormatElementEnumUseage_preDraw, vertexformat, j1, i, bytebuffer);
            } else {
               int k = vertexformatelement.getType().getGlConstant();
               i1 = vertexformatelement.getIndex();
               bytebuffer.position(vertexformat.getOffset(j1));
               switch (vertexformatelement$enumusage) {
                  case POSITION:
                     yh.glVertexPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                     yh.glEnableClientState(32884);
                     break;
                  case UV:
                     ys.setClientActiveTexture(ys.defaultTexUnit + i1);
                     yh.glTexCoordPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                     yh.glEnableClientState(32888);
                     ys.setClientActiveTexture(ys.defaultTexUnit);
                     break;
                  case COLOR:
                     yh.glColorPointer(vertexformatelement.getElementCount(), k, i, bytebuffer);
                     yh.glEnableClientState(32886);
                     break;
                  case NORMAL:
                     yh.glNormalPointer(k, i, bytebuffer);
                     yh.glEnableClientState(32885);
               }
            }
         }

         if (bufferBuilderIn.isMultiTexture()) {
            bufferBuilderIn.drawMultiTexture();
         } else if (XH.isShaders()) {
            bpz.drawArrays(bufferBuilderIn.getDrawMode(), 0, bufferBuilderIn.getVertexCount(), bufferBuilderIn);
         } else {
            yh.glDrawArrays(bufferBuilderIn.getDrawMode(), 0, bufferBuilderIn.getVertexCount());
         }

         j1 = 0;

         for(int k1 = list.size(); j1 < k1; ++j1) {
            zR vertexformatelement1 = (zR)list.get(j1);
            zQ vertexformatelement$enumusage1 = vertexformatelement1.getUsage();
            if (flag1) {
               bnK.callVoid(vertexformatelement$enumusage1, bnK.ForgeVertexFormatElementEnumUseage_postDraw, vertexformat, j1, i, bytebuffer);
            } else {
               i1 = vertexformatelement1.getIndex();
               switch (vertexformatelement$enumusage1) {
                  case POSITION:
                     yh.glDisableClientState(32884);
                     break;
                  case UV:
                     ys.setClientActiveTexture(ys.defaultTexUnit + i1);
                     yh.glDisableClientState(32888);
                     ys.setClientActiveTexture(ys.defaultTexUnit);
                     break;
                  case COLOR:
                     yh.glDisableClientState(32886);
                     yh.resetColor();
                     break;
                  case NORMAL:
                     yh.glDisableClientState(32885);
               }
            }
         }
      }

      bufferBuilderIn.reset();
   }
}
