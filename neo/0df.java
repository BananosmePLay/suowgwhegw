package neo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3i;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class 0df {
   public static List<Integer> blocksAvoid = new ArrayList();

   public static boolean canStandOn(Vector3i pos, World world) {
      Vector3i pos0 = new Vector3i(ykvToMlK9p(pos), Vbv2ryFOIA(pos) - (1106 ^ -9495 ^ 25505 ^ -17125), 6vA1GsrnDU(pos));
      boolean block0 = 15896 ^ -1223 ^ 7748 ^ -9372;
      boolean block1 = 17099 ^ -753 ^ 6708 ^ -23055;
      if (checkBlock(pos0, world) != Tu4MgPU2wK()) {
         block0 = 10151 ^ -25065 ^ 26402 ^ -8558;
      }

      Vector3i pos1 = new Vector3i(yByVvOBW7z(pos), VeortLpHDv(pos), XNp5NfbiNO(pos));
      0de check = checkBlock(pos1, world);
      boolean block1TopSolid = isTopSolid(pos1, world);
      if (!block1TopSolid && check != dGVlt8IopD()) {
         block1 = 25830 ^ -31606 ^ 2153 ^ -6140;
      } else if (check != FcwbXI5xBj()) {
         block1 = 9029 ^ -17011 ^ 30497 ^ -5655;
      }

      if ((block1TopSolid || block1 != 0) && (block0 == 0 || block1 == 0)) {
         return (boolean)(15222 ^ -21162 ^ 17766 ^ -11450);
      } else {
         Vector3i pos2 = new Vector3i(Gfp0viGwdg(pos), SeqYleJwB7(pos) + (14978 ^ -23506 ^ 8219 ^ -16714), qLjFNShL2t(pos));
         return (boolean)(checkBlock(pos2, world) == bwkYa1jHxq() ? 32118 ^ -27451 ^ 3451 ^ -6967 : 54 ^ -17465 ^ 227 ^ -17646);
      }
   }

   private static int B8bXvAxy2l(Vector3i var0) {
      return var0.x;
   }

   private static int OleHaIqYJi(Vector3i var0) {
      return var0.y;
   }

   private static int I9IkIrqVBJ(Vector3i var0) {
      return var0.y;
   }

   private static int wpTuOrXDbQ(Vector3i var0) {
      return var0.z;
   }

   private static 0de _KgCOJ19eF/* $FF was: 4KgCOJ19eF*/() {
      return 0de.PASSABLE;
   }

   public static 0de checkBlock(Vector3i pos, World world) {
      Block block = 0dm.getBlockByPos(world, 0dm.vec3i_toBlockPos(pos));
      if (block == null) {
         return JrLKHef2D3();
      } else if (Block.getIdFromBlock(block) == (9784 ^ -29988 ^ 3880 ^ -23716)) {
         return jTppwDnABV();
      } else if (Block.getIdFromBlock(block) == (27049 ^ -27777 ^ 5189 ^ -4425)) {
         return Khevy7id4b();
      } else if (Block.getIdFromBlock(block) == (12518 ^ -14789 ^ 14490 ^ -12766)) {
         return LTDYIjb6SZ();
      } else if (Block.getIdFromBlock(block) != (11903 ^ -20941 ^ 32606 ^ -230) && Block.getIdFromBlock(block) != (26880 ^ -30530 ^ 780 ^ -7493)) {
         if (Block.getIdFromBlock(block) == (13122 ^ -24453 ^ 22786 ^ -13571)) {
            return DvqkWeXqS2();
         } else {
            Iterator var3 = wEGYflO9Ct().iterator();

            Integer id;
            int blockId;
            do {
               if (!var3.hasNext()) {
                  if (block.isPassable(world, 0dm.vec3i_toBlockPos(pos))) {
                     return sEGn79Qv6e();
                  }

                  return bpw9001Ri2();
               }

               id = (Integer)var3.next();
               blockId = Block.getIdFromBlock(block);
            } while(blockId != id);

            return VoIZJYBqIw();
         }
      } else {
         return xndY19TrbA();
      }
   }

   private static 0de dGVlt8IopD() {
      return 0de.PASSABLE;
   }

   private static 0de xndY19TrbA() {
      return 0de.SOLID;
   }

   private static int _4QQnWnSOo/* $FF was: 24QQnWnSOo*/(Vector3i var0) {
      return var0.y;
   }

   private static 0de JrLKHef2D3() {
      return 0de.PASSABLE;
   }

   private static int LuJ7DarJ4a(Vector3i var0) {
      return var0.y;
   }

   private static int lh1FgaatOD(Vector3i var0) {
      return var0.x;
   }

   public static boolean canMoveToDiagonal(Vector3i startPos, Vector3i endPos, World world) {
      if (!canMoveTo(startPos, endPos, world)) {
         return (boolean)(6235 ^ -13176 ^ 4745 ^ -14758);
      } else {
         boolean res1 = canExistOn(new Vector3i(sarM21KCJO(endPos), XSm8Ss6F1X(endPos), Vl4YkzyZJi(startPos)), world);
         boolean res2 = canExistOn(new Vector3i(hXXbBK4IM1(startPos), 62ZloVBS76(endPos), 1YbAvAGqqg(endPos)), world);
         boolean res3 = canExistOn(new Vector3i(2iU2Bv7sDr(endPos), 7Xe9wabPqF(startPos), A4IgW1Povr(startPos)), world);
         boolean res4 = canExistOn(new Vector3i(B8bXvAxy2l(startPos), DiVqQFnByD(startPos), UCNa7U5Ug2(endPos)), world);
         boolean resFinal = 19499 ^ -12336 ^ 7607 ^ -25011;
         int ydif = LO35TmYBYr(startPos) - QgWJVcyqbV(endPos);

         for(int y = 25659 ^ -20464 ^ 8353 ^ -2934; y <= ydif; ++y) {
            if (checkBlock(new Vector3i(beii6N1x2t(endPos), IdicFJtzi5(startPos) + y, 0ykT9Tyni8(endPos)), world) != epgdSRJFw1()) {
               resFinal = 8957 ^ -29372 ^ 28290 ^ -16069;
            }
         }

         return (boolean)(!res1 && !res2 || resFinal == 0 || !res3 && !res4 ? 8890 ^ -4126 ^ 27117 ^ -23371 : 32302 ^ -1050 ^ 22423 ^ -11682);
      }
   }

   private static 0de Khevy7id4b() {
      return 0de.PASSABLE;
   }

   private static int XNp5NfbiNO(Vector3i var0) {
      return var0.z;
   }

   private static int beii6N1x2t(Vector3i var0) {
      return var0.x;
   }

   private static int _Ry766hvNa/* $FF was: 2Ry766hvNa*/(Vector3i var0) {
      return var0.y;
   }

   private static int _ykT9Tyni8/* $FF was: 0ykT9Tyni8*/(Vector3i var0) {
      return var0.z;
   }

   private static int ykvToMlK9p(Vector3i var0) {
      return var0.x;
   }

   private static int JbjTNj2Did(Vector3i var0) {
      return var0.y;
   }

   private static int L2uElLDL72(Vector3i var0) {
      return var0.y;
   }

   private static int Vl4YkzyZJi(Vector3i var0) {
      return var0.z;
   }

   static {
      blocksAvoid.add(16721 ^ -31882 ^ 5627 ^ -10282);
      blocksAvoid.add(240 ^ -16511 ^ 26071 ^ -9555);
   }

   private static int _Br6lo7Vnj/* $FF was: 2Br6lo7Vnj*/(Vector3i var0) {
      return var0.x;
   }

   private static int ywV32yyKR4(Vector3i var0) {
      return var0.y;
   }

   private static 0de bpw9001Ri2() {
      return 0de.SOLID;
   }

   private static int qLjFNShL2t(Vector3i var0) {
      return var0.z;
   }

   private static int XVyhHDxRxN(Vector3i var0) {
      return var0.x;
   }

   public static boolean isTopSolid(BlockPos pos, World world) {
      IBlockState state = 0dm.getBlockStateByPos(world, pos);
      return (boolean)(state == null ? 7098 ^ -935 ^ 16532 ^ -22665 : state.isTopSolid());
   }

   private static int LO35TmYBYr(Vector3i var0) {
      return var0.y;
   }

   public static boolean isTopSolid(Vector3i pos, World world) {
      IBlockState state = 0dm.getBlockStateByPos(world, 0dm.vec3i_toBlockPos(pos));
      if (state == null) {
         return (boolean)(18568 ^ -14385 ^ 7189 ^ -27822);
      } else {
         Block block = 0dm.getBlockByPos(world, 0dm.vec3i_toBlockPos(pos));
         return (boolean)(block != null && Block.getIdFromBlock(block) == (7318 ^ -13631 ^ 20035 ^ -26503) ? 15162 ^ -14745 ^ 20576 ^ -21188 : state.isTopSolid());
      }
   }

   private static int hXXbBK4IM1(Vector3i var0) {
      return var0.x;
   }

   private static List wEGYflO9Ct() {
      return blocksAvoid;
   }

   private static int _vA1GsrnDU/* $FF was: 6vA1GsrnDU*/(Vector3i var0) {
      return var0.z;
   }

   private static int OebpJgFarO(Vector3i var0) {
      return var0.z;
   }

   private static int VeortLpHDv(Vector3i var0) {
      return var0.y;
   }

   private static int SjyLYIBnHg(Vector3i var0) {
      return var0.z;
   }

   private static int YR8PgkoTJC(Vector3i var0) {
      return var0.y;
   }

   private static 0de LTDYIjb6SZ() {
      return 0de.PASSABLE;
   }

   public static boolean canExistOn(Vector3i pos, World world) {
      Vector3i pos0 = new Vector3i(lh1FgaatOD(pos), 24QQnWnSOo(pos), wpTuOrXDbQ(pos));
      Vector3i pos1 = new Vector3i(2Br6lo7Vnj(pos), I9IkIrqVBJ(pos) + (21988 ^ -30856 ^ 28301 ^ -17392), UnOAyYxDBw(pos));
      boolean b0 = checkBlock(pos0, world) == BbqQiAFWYZ() ? 23899 ^ -12557 ^ 18436 ^ -9299 : 13552 ^ -29948 ^ 23869 ^ -7479;
      boolean b1 = checkBlock(pos1, world) == do2RgynCb3() ? 11812 ^ -11213 ^ 19495 ^ -18895 : 9787 ^ -21050 ^ 31124 ^ -3479;
      return (boolean)(b0 != 0 && b1 != 0 ? 5907 ^ -6441 ^ 32734 ^ -29157 : 11124 ^ -20917 ^ 27238 ^ -4263);
   }

   private static 0de FcwbXI5xBj() {
      return 0de.PASSABLE;
   }

   private static int yByVvOBW7z(Vector3i var0) {
      return var0.x;
   }

   public _df/* $FF was: 0df*/() {
   }

   private static int sarM21KCJO(Vector3i var0) {
      return var0.x;
   }

   private static int XSm8Ss6F1X(Vector3i var0) {
      return var0.y;
   }

   private static int A4IgW1Povr(Vector3i var0) {
      return var0.z;
   }

   private static int QgWJVcyqbV(Vector3i var0) {
      return var0.y;
   }

   private static int SeqYleJwB7(Vector3i var0) {
      return var0.y;
   }

   private static int IdicFJtzi5(Vector3i var0) {
      return var0.y;
   }

   private static int u7csjVr88I(Vector3i var0) {
      return var0.x;
   }

   private static int Gfp0viGwdg(Vector3i var0) {
      return var0.x;
   }

   private static 0de do2RgynCb3() {
      return 0de.PASSABLE;
   }

   private static 0de Tu4MgPU2wK() {
      return 0de.SOLID;
   }

   private static int DiVqQFnByD(Vector3i var0) {
      return var0.y;
   }

   private static int uQevGRlD5o(Vector3i var0) {
      return var0.x;
   }

   private static 0de sEGn79Qv6e() {
      return 0de.PASSABLE;
   }

   private static int UnOAyYxDBw(Vector3i var0) {
      return var0.z;
   }

   private static 0de epgdSRJFw1() {
      return 0de.PASSABLE;
   }

   private static int _Xe9wabPqF/* $FF was: 7Xe9wabPqF*/(Vector3i var0) {
      return var0.y;
   }

   private static int UCNa7U5Ug2(Vector3i var0) {
      return var0.z;
   }

   private static int HPBA6iJ0NG(Vector3i var0) {
      return var0.y;
   }

   private static 0de jTppwDnABV() {
      return 0de.SOLID;
   }

   private static int oGX9y3jBCE(Vector3i var0) {
      return var0.z;
   }

   private static int _YbAvAGqqg/* $FF was: 1YbAvAGqqg*/(Vector3i var0) {
      return var0.z;
   }

   private static 0de bwkYa1jHxq() {
      return 0de.PASSABLE;
   }

   private static int _2ZloVBS76/* $FF was: 62ZloVBS76*/(Vector3i var0) {
      return var0.y;
   }

   private static 0de BbqQiAFWYZ() {
      return 0de.PASSABLE;
   }

   public static boolean canMoveTo(Vector3i startPos, Vector3i endPos, World world) {
      boolean canStandOnEnd = canStandOn(endPos, world);
      if (!canStandOnEnd) {
         return (boolean)(9601 ^ -15556 ^ 12268 ^ -13999);
      } else {
         for(int y = LuJ7DarJ4a(endPos); y <= L2uElLDL72(startPos); ++y) {
            if (checkBlock(new Vector3i(uQevGRlD5o(endPos), y, oGX9y3jBCE(endPos)), world) != 4KgCOJ19eF()) {
               return (boolean)(25180 ^ -12456 ^ 17026 ^ -4218);
            }
         }

         if (OleHaIqYJi(startPos) < 2Ry766hvNa(endPos)) {
            boolean result = canExistOn(new Vector3i(XVyhHDxRxN(startPos), ywV32yyKR4(endPos), OebpJgFarO(startPos)), world);
            if (result) {
               Vector3i endPosFloor = endPos.sub(11366 ^ -11647 ^ 23340 ^ -23093, 19003 ^ -4898 ^ 8599 ^ -30861, 23510 ^ -9915 ^ 20587 ^ -11528);
               boolean isEndUpPassable = checkBlock(endPos, world) == ZTTeXWDtLk() ? 22537 ^ -30084 ^ 22881 ^ -29931 : 24268 ^ -1881 ^ 25738 ^ -15647;
               if (isEndUpPassable == 0) {
                  return (boolean)(16894 ^ -27656 ^ 17292 ^ -28278);
               }

               boolean isTopSolidS = isTopSolid(startPos.sub(14422 ^ -17127 ^ 9325 ^ -24286, 1067 ^ -18308 ^ 13075 ^ -28859, 4304 ^ -19654 ^ 22465 ^ -3029), world);
               boolean isTopSolidE = isTopSolid(endPosFloor, world);
               if (!isTopSolidS && !isTopSolidE) {
                  return (boolean)(6716 ^ -18453 ^ 1177 ^ -22193);
               }

               if (isTopSolidS && !isTopSolidE) {
                  return (boolean)(16518 ^ -18108 ^ 28666 ^ -27079);
               }

               if (!isTopSolidS && isTopSolidE) {
                  return (boolean)(18275 ^ -9421 ^ 10074 ^ -17654);
               }

               if (isTopSolidS && isTopSolidE) {
                  return (boolean)(19447 ^ -2586 ^ 449 ^ -16431);
               }
            }

            return result;
         } else if (JbjTNj2Did(startPos) > YR8PgkoTJC(endPos)) {
            return canExistOn(new Vector3i(u7csjVr88I(endPos), HPBA6iJ0NG(startPos), SjyLYIBnHg(endPos)), world);
         } else {
            return canStandOnEnd;
         }
      }
   }

   private static 0de VoIZJYBqIw() {
      return 0de.AVOID;
   }

   private static int _iU2Bv7sDr/* $FF was: 2iU2Bv7sDr*/(Vector3i var0) {
      return var0.x;
   }

   private static int Vbv2ryFOIA(Vector3i var0) {
      return var0.y;
   }

   private static 0de DvqkWeXqS2() {
      return 0de.SOLID;
   }

   private static 0de ZTTeXWDtLk() {
      return 0de.PASSABLE;
   }
}
