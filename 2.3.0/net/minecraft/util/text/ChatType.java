package net.minecraft.util.text;

public enum ChatType {
   CHAT((byte)0),
   SYSTEM((byte)1),
   GAME_INFO((byte)2);

   private final byte id;

   private ChatType(byte id) {
      this.id = id;
   }

   public byte getId() {
      return this.id;
   }

   public static ChatType byId(byte idIn) {
      ChatType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ChatType chattype = var1[var3];
         if (idIn == chattype.id) {
            return chattype;
         }
      }

      return CHAT;
   }
}
