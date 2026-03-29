package neo;

public class pq {
   private final String lanServerMotd;
   private final String lanServerIpPort;
   private long timeLastSeen;

   public pq(String p_i47130_1_, String p_i47130_2_) {
      this.lanServerMotd = p_i47130_1_;
      this.lanServerIpPort = p_i47130_2_;
      this.timeLastSeen = nC.getSystemTime();
   }

   public String getServerMotd() {
      return this.lanServerMotd;
   }

   public String getServerIpPort() {
      return this.lanServerIpPort;
   }

   public void updateLastSeen() {
      this.timeLastSeen = nC.getSystemTime();
   }
}
