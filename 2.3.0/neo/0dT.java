package neo;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface 0dT extends Library {
   0dT INSTANCE = (0dT)Native.loadLibrary("discord-rpc", 0dT.class);

   void Discord_UpdateHandlers(0dQ var1);

   void Discord_Register(String var1, String var2);

   void Discord_UpdateConnection();

   void Discord_Initialize(String var1, 0dQ var2, boolean var3, String var4);

   void Discord_ClearPresence();

   void Discord_UpdatePresence(0dS var1);

   void Discord_Respond(String var1, int var2);

   void Discord_RegisterSteamGame(String var1, String var2);

   void Discord_RunCallbacks();

   void Discord_Shutdown();
}
