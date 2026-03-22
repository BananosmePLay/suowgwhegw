package neo;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface 0dY extends Library {
   0dY INSTANCE = (0dY)Native.loadLibrary("discord-rpc", 0dY.class);

   void Discord_Register(String var1, String var2);

   void Discord_RunCallbacks();

   void Discord_ClearPresence();

   void Discord_UpdateHandlers(0dV var1);

   void Discord_Initialize(String var1, 0dV var2, boolean var3, String var4);

   void Discord_UpdateConnection();

   void Discord_RegisterSteamGame(String var1, String var2);

   void Discord_Shutdown();

   void Discord_UpdatePresence(0dX var1);

   void Discord_Respond(String var1, int var2);
}
