package neo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;

public enum RB {
   HANDSHAKING(-1) {
      {
         this.registerPacket(RC.SERVERBOUND, RD.class);
      }
   },
   PLAY(0) {
      {
         this.registerPacket(RC.CLIENTBOUND, UP.class);
         this.registerPacket(RC.CLIENTBOUND, UM.class);
         this.registerPacket(RC.CLIENTBOUND, UN.class);
         this.registerPacket(RC.CLIENTBOUND, UO.class);
         this.registerPacket(RC.CLIENTBOUND, UQ.class);
         this.registerPacket(RC.CLIENTBOUND, UR.class);
         this.registerPacket(RC.CLIENTBOUND, Tx.class);
         this.registerPacket(RC.CLIENTBOUND, UT.class);
         this.registerPacket(RC.CLIENTBOUND, Tz.class);
         this.registerPacket(RC.CLIENTBOUND, Vg.class);
         this.registerPacket(RC.CLIENTBOUND, Ty.class);
         this.registerPacket(RC.CLIENTBOUND, TA.class);
         this.registerPacket(RC.CLIENTBOUND, Vc.class);
         this.registerPacket(RC.CLIENTBOUND, UG.class);
         this.registerPacket(RC.CLIENTBOUND, UU.class);
         this.registerPacket(RC.CLIENTBOUND, TD.class);
         this.registerPacket(RC.CLIENTBOUND, Un.class);
         this.registerPacket(RC.CLIENTBOUND, TK.class);
         this.registerPacket(RC.CLIENTBOUND, TF.class);
         this.registerPacket(RC.CLIENTBOUND, Uo.class);
         this.registerPacket(RC.CLIENTBOUND, Vi.class);
         this.registerPacket(RC.CLIENTBOUND, Vj.class);
         this.registerPacket(RC.CLIENTBOUND, UJ.class);
         this.registerPacket(RC.CLIENTBOUND, TL.class);
         this.registerPacket(RC.CLIENTBOUND, TM.class);
         this.registerPacket(RC.CLIENTBOUND, TN.class);
         this.registerPacket(RC.CLIENTBOUND, TP.class);
         this.registerPacket(RC.CLIENTBOUND, Ud.class);
         this.registerPacket(RC.CLIENTBOUND, Ug.class);
         this.registerPacket(RC.CLIENTBOUND, UZ.class);
         this.registerPacket(RC.CLIENTBOUND, TC.class);
         this.registerPacket(RC.CLIENTBOUND, Uj.class);
         this.registerPacket(RC.CLIENTBOUND, TE.class);
         this.registerPacket(RC.CLIENTBOUND, TR.class);
         this.registerPacket(RC.CLIENTBOUND, Up.class);
         this.registerPacket(RC.CLIENTBOUND, Ui.class);
         this.registerPacket(RC.CLIENTBOUND, Uk.class);
         this.registerPacket(RC.CLIENTBOUND, TV.class);
         this.registerPacket(RC.CLIENTBOUND, TS.class);
         this.registerPacket(RC.CLIENTBOUND, TU.class);
         this.registerPacket(RC.CLIENTBOUND, TT.class);
         this.registerPacket(RC.CLIENTBOUND, Ul.class);
         this.registerPacket(RC.CLIENTBOUND, UK.class);
         this.registerPacket(RC.CLIENTBOUND, Uq.class);
         this.registerPacket(RC.CLIENTBOUND, Ur.class);
         this.registerPacket(RC.CLIENTBOUND, TJ.class);
         this.registerPacket(RC.CLIENTBOUND, Uw.class);
         this.registerPacket(RC.CLIENTBOUND, Uy.class);
         this.registerPacket(RC.CLIENTBOUND, Vh.class);
         this.registerPacket(RC.CLIENTBOUND, UA.class);
         this.registerPacket(RC.CLIENTBOUND, TO.class);
         this.registerPacket(RC.CLIENTBOUND, UB.class);
         this.registerPacket(RC.CLIENTBOUND, UC.class);
         this.registerPacket(RC.CLIENTBOUND, UD.class);
         this.registerPacket(RC.CLIENTBOUND, TZ.class);
         this.registerPacket(RC.CLIENTBOUND, UF.class);
         this.registerPacket(RC.CLIENTBOUND, Vm.class);
         this.registerPacket(RC.CLIENTBOUND, TB.class);
         this.registerPacket(RC.CLIENTBOUND, Uh.class);
         this.registerPacket(RC.CLIENTBOUND, TQ.class);
         this.registerPacket(RC.CLIENTBOUND, Ua.class);
         this.registerPacket(RC.CLIENTBOUND, TW.class);
         this.registerPacket(RC.CLIENTBOUND, Uf.class);
         this.registerPacket(RC.CLIENTBOUND, TY.class);
         this.registerPacket(RC.CLIENTBOUND, UH.class);
         this.registerPacket(RC.CLIENTBOUND, Vd.class);
         this.registerPacket(RC.CLIENTBOUND, UE.class);
         this.registerPacket(RC.CLIENTBOUND, UI.class);
         this.registerPacket(RC.CLIENTBOUND, UV.class);
         this.registerPacket(RC.CLIENTBOUND, Vf.class);
         this.registerPacket(RC.CLIENTBOUND, US.class);
         this.registerPacket(RC.CLIENTBOUND, UW.class);
         this.registerPacket(RC.CLIENTBOUND, UY.class);
         this.registerPacket(RC.CLIENTBOUND, UL.class);
         this.registerPacket(RC.CLIENTBOUND, Us.class);
         this.registerPacket(RC.CLIENTBOUND, TG.class);
         this.registerPacket(RC.CLIENTBOUND, Ue.class);
         this.registerPacket(RC.CLIENTBOUND, Tw.class);
         this.registerPacket(RC.CLIENTBOUND, Uc.class);
         this.registerPacket(RC.CLIENTBOUND, TX.class);
         this.registerPacket(RC.SERVERBOUND, SK.class);
         this.registerPacket(RC.SERVERBOUND, Tm.class);
         this.registerPacket(RC.SERVERBOUND, SE.class);
         this.registerPacket(RC.SERVERBOUND, SI.class);
         this.registerPacket(RC.SERVERBOUND, SG.class);
         this.registerPacket(RC.SERVERBOUND, SL.class);
         this.registerPacket(RC.SERVERBOUND, SO.class);
         this.registerPacket(RC.SERVERBOUND, SF.class);
         this.registerPacket(RC.SERVERBOUND, SJ.class);
         this.registerPacket(RC.SERVERBOUND, SN.class);
         this.registerPacket(RC.SERVERBOUND, Tp.class);
         this.registerPacket(RC.SERVERBOUND, ST.class);
         this.registerPacket(RC.SERVERBOUND, SY.class);
         this.registerPacket(RC.SERVERBOUND, SV.class);
         this.registerPacket(RC.SERVERBOUND, SW.class);
         this.registerPacket(RC.SERVERBOUND, SX.class);
         this.registerPacket(RC.SERVERBOUND, Tq.class);
         this.registerPacket(RC.SERVERBOUND, Tl.class);
         this.registerPacket(RC.SERVERBOUND, SU.class);
         this.registerPacket(RC.SERVERBOUND, SZ.class);
         this.registerPacket(RC.SERVERBOUND, Tb.class);
         this.registerPacket(RC.SERVERBOUND, SQ.class);
         this.registerPacket(RC.SERVERBOUND, SS.class);
         this.registerPacket(RC.SERVERBOUND, Tf.class);
         this.registerPacket(RC.SERVERBOUND, Th.class);
         this.registerPacket(RC.SERVERBOUND, Tj.class);
         this.registerPacket(RC.SERVERBOUND, SR.class);
         this.registerPacket(RC.SERVERBOUND, SM.class);
         this.registerPacket(RC.SERVERBOUND, Tn.class);
         this.registerPacket(RC.SERVERBOUND, SD.class);
         this.registerPacket(RC.SERVERBOUND, Tk.class);
         this.registerPacket(RC.SERVERBOUND, Td.class);
         this.registerPacket(RC.SERVERBOUND, Tc.class);
      }
   },
   STATUS(1) {
      {
         this.registerPacket(RC.SERVERBOUND, Vw.class);
         this.registerPacket(RC.CLIENTBOUND, VD.class);
         this.registerPacket(RC.SERVERBOUND, Vv.class);
         this.registerPacket(RC.CLIENTBOUND, VC.class);
      }
   },
   LOGIN(2) {
      {
         this.registerPacket(RC.CLIENTBOUND, RQ.class);
         this.registerPacket(RC.CLIENTBOUND, RS.class);
         this.registerPacket(RC.CLIENTBOUND, RT.class);
         this.registerPacket(RC.CLIENTBOUND, RR.class);
         this.registerPacket(RC.SERVERBOUND, RK.class);
         this.registerPacket(RC.SERVERBOUND, RJ.class);
      }
   };

   private static final RB[] STATES_BY_ID = new RB[4];
   private static final Map<Class<? extends Sz<?>>, RB> STATES_BY_CLASS = Maps.newHashMap();
   private final int id;
   private final Map<RC, BiMap<Integer, Class<? extends Sz<?>>>> directionMaps;

   private RB(int protocolId) {
      this.directionMaps = Maps.newEnumMap(RC.class);
      this.id = protocolId;
   }

   protected RB registerPacket(RC direction, Class<? extends Sz<?>> packetClass) {
      BiMap<Integer, Class<? extends Sz<?>>> bimap = (BiMap)this.directionMaps.get(direction);
      if (bimap == null) {
         bimap = HashBiMap.create();
         this.directionMaps.put(direction, bimap);
      }

      if (((BiMap)bimap).containsValue(packetClass)) {
         String s = direction + " packet " + packetClass + " is already known to ID " + ((BiMap)bimap).inverse().get(packetClass);
         LogManager.getLogger().fatal(s);
         throw new IllegalArgumentException(s);
      } else {
         ((BiMap)bimap).put(((BiMap)bimap).size(), packetClass);
         return this;
      }
   }

   public Integer getPacketId(RC direction, Sz<?> packetIn) throws Exception {
      return (Integer)((BiMap)this.directionMaps.get(direction)).inverse().get(packetIn.getClass());
   }

   @Nullable
   public Sz<?> getPacket(RC direction, int packetId) throws InstantiationException, IllegalAccessException {
      Class<? extends Sz<?>> oclass = (Class)((BiMap)this.directionMaps.get(direction)).get(packetId);
      return oclass == null ? null : (Sz)oclass.newInstance();
   }

   public int getId() {
      return this.id;
   }

   public static RB getById(int stateId) {
      return stateId >= -1 && stateId <= 2 ? STATES_BY_ID[stateId - -1] : null;
   }

   public static RB getFromPacket(Sz<?> packetIn) {
      return (RB)STATES_BY_CLASS.get(packetIn.getClass());
   }

   // $FF: synthetic method
   RB(int x2, Object x3) {
      this(x2);
   }

   static {
      RB[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         RB enumconnectionstate = var0[var2];
         int i = enumconnectionstate.getId();
         if (i < -1 || i > 2) {
            throw new Error("Invalid protocol ID " + Integer.toString(i));
         }

         STATES_BY_ID[i - -1] = enumconnectionstate;
         Iterator var5 = enumconnectionstate.directionMaps.keySet().iterator();

         while(var5.hasNext()) {
            RC enumpacketdirection = (RC)var5.next();

            Class oclass;
            for(Iterator var7 = ((BiMap)enumconnectionstate.directionMaps.get(enumpacketdirection)).values().iterator(); var7.hasNext(); STATES_BY_CLASS.put(oclass, enumconnectionstate)) {
               oclass = (Class)var7.next();
               if (STATES_BY_CLASS.containsKey(oclass) && STATES_BY_CLASS.get(oclass) != enumconnectionstate) {
                  throw new Error("Packet " + oclass + " is already assigned to protocol " + STATES_BY_CLASS.get(oclass) + " - can't reassign to " + enumconnectionstate);
               }

               try {
                  oclass.newInstance();
               } catch (Throwable var10) {
                  throw new Error("Packet " + oclass + " fails instantiation checks! " + oclass);
               }
            }
         }
      }

   }
}
