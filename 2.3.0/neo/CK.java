package neo;

import javax.annotation.Nullable;

public enum CK {
   SUCCESS_COUNT(0, "SuccessCount"),
   AFFECTED_BLOCKS(1, "AffectedBlocks"),
   AFFECTED_ENTITIES(2, "AffectedEntities"),
   AFFECTED_ITEMS(3, "AffectedItems"),
   QUERY_RESULT(4, "QueryResult");

   final int typeID;
   final String typeName;

   private CK(int id, String name) {
      this.typeID = id;
      this.typeName = name;
   }

   public int getTypeID() {
      return this.typeID;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public static String[] getTypeNames() {
      String[] astring = new String[values().length];
      int i = 0;
      CK[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         CK commandresultstats$type = var2[var4];
         astring[i++] = commandresultstats$type.getTypeName();
      }

      return astring;
   }

   @Nullable
   public static CK getTypeByName(String name) {
      CK[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CK commandresultstats$type = var1[var3];
         if (commandresultstats$type.getTypeName().equals(name)) {
            return commandresultstats$type;
         }
      }

      return null;
   }
}
