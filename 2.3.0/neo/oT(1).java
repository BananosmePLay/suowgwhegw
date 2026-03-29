package neo;

import net.minecraft.util.math.Vec3d;

public class oT {
   public oS[] vertexPositions;
   public int nVertices;
   private boolean invertNormal;

   public oT(oS[] vertices) {
      this.vertexPositions = vertices;
      this.nVertices = vertices.length;
   }

   public oT(oS[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight) {
      this(vertices);
      float f = 0.0F / textureWidth;
      float f1 = 0.0F / textureHeight;
      vertices[0] = vertices[0].setTexturePosition((float)texcoordU2 / textureWidth - f, (float)texcoordV1 / textureHeight + f1);
      vertices[1] = vertices[1].setTexturePosition((float)texcoordU1 / textureWidth + f, (float)texcoordV1 / textureHeight + f1);
      vertices[2] = vertices[2].setTexturePosition((float)texcoordU1 / textureWidth + f, (float)texcoordV2 / textureHeight - f1);
      vertices[3] = vertices[3].setTexturePosition((float)texcoordU2 / textureWidth - f, (float)texcoordV2 / textureHeight - f1);
   }

   public void flipFace() {
      oS[] apositiontexturevertex = new oS[this.vertexPositions.length];

      for(int i = 0; i < this.vertexPositions.length; ++i) {
         apositiontexturevertex[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
      }

      this.vertexPositions = apositiontexturevertex;
   }

   public void draw(tN renderer, float scale) {
      Vec3d vec3d = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[0].vector3D);
      Vec3d vec3d1 = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[2].vector3D);
      Vec3d vec3d2 = vec3d1.crossProduct(vec3d).normalize();
      float f = (float)vec3d2.x;
      float f1 = (float)vec3d2.y;
      float f2 = (float)vec3d2.z;
      if (this.invertNormal) {
         f = -f;
         f1 = -f1;
         f2 = -f2;
      }

      if (XH.isShaders()) {
         renderer.begin(7, bpA.defVertexFormatTextured);
      } else {
         renderer.begin(7, zK.OLDMODEL_POSITION_TEX_NORMAL);
      }

      for(int i = 0; i < 4; ++i) {
         oS positiontexturevertex = this.vertexPositions[i];
         renderer.pos(positiontexturevertex.vector3D.x * (double)scale, positiontexturevertex.vector3D.y * (double)scale, positiontexturevertex.vector3D.z * (double)scale).tex((double)positiontexturevertex.texturePositionX, (double)positiontexturevertex.texturePositionY).normal(f, f1, f2).endVertex();
      }

      yN.getInstance().draw();
   }
}
