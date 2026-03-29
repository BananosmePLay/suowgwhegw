package neo;

public enum MB {
   FULL(0, "options.chat.visibility.full"),
   SYSTEM(1, "options.chat.visibility.system"),
   HIDDEN(2, "options.chat.visibility.hidden");

   private static final MB[] ID_LOOKUP = new MB[values().length];
   private final int chatVisibility;
   private final String resourceKey;

   private MB(int id, String resourceKey) {
      this.chatVisibility = id;
      this.resourceKey = resourceKey;
   }

   public int getChatVisibility() {
      return this.chatVisibility;
   }

   public static MB getEnumChatVisibility(int id) {
      return ID_LOOKUP[id % ID_LOOKUP.length];
   }

   public String getResourceKey() {
      return this.resourceKey;
   }

   static {
      MB[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         MB entityplayer$enumchatvisibility = var0[var2];
         ID_LOOKUP[entityplayer$enumchatvisibility.chatVisibility] = entityplayer$enumchatvisibility;
      }

   }
}
