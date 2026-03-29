package neo;

import java.io.IOException;

public interface Sz<T extends RH> {
   void readPacketData(SA var1) throws IOException;

   void writePacketData(SA var1) throws IOException;

   void processPacket(T var1);

   String toString();
}
