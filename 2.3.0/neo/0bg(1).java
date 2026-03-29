package neo;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import de.florianmichael.viamcp.MCPVLBPipeline;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

final class 0bg extends ChannelInitializer<Channel> {
   // $FF: synthetic field
   public final 0bi val$botNetwork;
   // $FF: synthetic field
   public final 0a val$bot;
   private static int _DSC GG NEOWARECLIENT _;

   _bg/* $FF was: 0bg*/(0a var1, 0bi var2) {
      this.val$bot = var1;
      this.val$botNetwork = var2;
   }

   protected void initChannel(Channel c) {
      try {
         c.config().setOption(nNJcaeGkDs(), Boolean.valueOf((boolean)(2590 ^ -15935 ^ 18553 ^ -31833)));
      } catch (ChannelException var3) {
      }

      ChannelPipeline var10000;
      ChannelHandler[] var10001;
      if (5QVTFwkAyo().method_bna()) {
         ProxyHandler a = 2oxwiQ0QVo(this).getProxy().method_bBT();
         if (a == null) {
            throw new NullPointerException(6EAQg1A1cq("ϩϫ϶ϡϠιϰϪιϷϬϵϵ"));
         }

         a.setConnectTimeoutMillis(9500L);
         var10000 = c.pipeline();
         var10001 = new ChannelHandler[18256 ^ -7846 ^ 7235 ^ -17848];
         var10001[24611 ^ -5685 ^ 22779 ^ -12013] = a;
         var10000.addLast(var10001);
      }

      c.pipeline().addLast(6EAQg1A1cq("ϭϰϴϼ϶Ϭϭ"), new ReadTimeoutHandler(23356 ^ -23861 ^ 12009 ^ -10496)).addLast(6EAQg1A1cq("Ϫϩϵϰϭϭϼϫ"), new Sg()).addLast(6EAQg1A1cq("ϽϼϺ϶Ͻϼϫ"), new Se(GqjMD126Wa())).addLast(6EAQg1A1cq("ϩϫϼϩϼϷϽϼϫ"), new Sh()).addLast(6EAQg1A1cq("ϼϷϺ϶Ͻϼϫ"), new Sf(dXZSeHQy7G())).addLast(6EAQg1A1cq("ϩϸϺϲϼϭφϱϸϷϽϵϼϫ"), vWoJZwoXXr(this));
      if (c instanceof SocketChannel && ViaLoadingBase.getInstance().getTargetVersion().getVersion() != (11139 ^ -30413 ^ 15670 ^ -24878)) {
         UserConnection b = new UserConnectionImpl(c, (boolean)(23713 ^ -22941 ^ 22771 ^ -24016));
         new ProtocolPipelineImpl(b);
         var10000 = c.pipeline();
         var10001 = new ChannelHandler[306 ^ -27861 ^ 12589 ^ -23755];
         var10001[25303 ^ -17120 ^ 1064 ^ -9249] = new MCPVLBPipeline(b);
         var10000.addLast(var10001);
      }

   }

   private static 0bi vWoJZwoXXr(0bg var0) {
      return var0.val$botNetwork;
   }

   private static RC dXZSeHQy7G() {
      return RC.SERVERBOUND;
   }

   private static 0a _oxwiQ0QVo/* $FF was: 2oxwiQ0QVo*/(0bg var0) {
      return var0.val$bot;
   }

   private static 0cp _QVTFwkAyo/* $FF was: 5QVTFwkAyo*/() {
      return 0bH.field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _EAQg1A1cq/* $FF was: 6EAQg1A1cq*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 5652 ^ -21962 ^ 13074 ^ -28880; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 27545 ^ -2955 ^ 20716 ^ -13159));
      }

      return var1.toString();
   }

   private static ChannelOption nNJcaeGkDs() {
      return ChannelOption.TCP_NODELAY;
   }

   private static RC GqjMD126Wa() {
      return RC.CLIENTBOUND;
   }
}
