package neo;

import java.util.Iterator;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class Eh extends CA implements Dz {
   private final Xx server;

   public Eh(Xx serverIn) {
      this.server = serverIn;
      this.registerCommand(new CV());
      this.registerCommand(new Cx());
      this.registerCommand(new Cp());
      this.registerCommand(new Co());
      this.registerCommand(new CC());
      this.registerCommand(new CX());
      this.registerCommand(new Da());
      this.registerCommand(new Dc());
      this.registerCommand(new CY());
      this.registerCommand(new Ec());
      this.registerCommand(new Cz());
      this.registerCommand(new CI());
      this.registerCommand(new CT());
      this.registerCommand(new Cq());
      this.registerCommand(new Cr());
      this.registerCommand(new CF());
      this.registerCommand(new DL());
      this.registerCommand(new CQ());
      this.registerCommand(new CB());
      this.registerCommand(new Cn());
      this.registerCommand(new DO());
      this.registerCommand(new DJ());
      this.registerCommand(new CP());
      this.registerCommand(new DZ());
      this.registerCommand(new Cy());
      this.registerCommand(new Cj());
      this.registerCommand(new Ed());
      this.registerCommand(new CS());
      this.registerCommand(new CG());
      this.registerCommand(new DX());
      this.registerCommand(new Cu());
      this.registerCommand(new CZ());
      this.registerCommand(new Cd());
      this.registerCommand(new DG());
      this.registerCommand(new Eb());
      this.registerCommand(new DY());
      this.registerCommand(new Cv());
      this.registerCommand(new Cl());
      this.registerCommand(new Cm());
      this.registerCommand(new Ci());
      this.registerCommand(new Ee());
      this.registerCommand(new DP());
      this.registerCommand(new Db());
      this.registerCommand(new CW());
      this.registerCommand(new Cs());
      this.registerCommand(new CU());
      this.registerCommand(new CD());
      this.registerCommand(new CH());
      this.registerCommand(new Cw());
      if (serverIn.isDedicatedServer()) {
         this.registerCommand(new DQ());
         this.registerCommand(new DK());
         this.registerCommand(new Ea());
         this.registerCommand(new DU());
         this.registerCommand(new DV());
         this.registerCommand(new DW());
         this.registerCommand(new DH());
         this.registerCommand(new DR());
         this.registerCommand(new DI());
         this.registerCommand(new DM());
         this.registerCommand(new DS());
         this.registerCommand(new CN());
         this.registerCommand(new DN());
         this.registerCommand(new Ef());
         this.registerCommand(new CO());
      } else {
         this.registerCommand(new DT());
      }

      Ch.setCommandListener(this);
   }

   public void notifyListener(DB sender, Dy command, int flags, String translationKey, Object... translationArgs) {
      boolean flag = true;
      Xx minecraftserver = this.server;
      if (!sender.sendCommandFeedback()) {
         flag = false;
      }

      ITextComponent itextcomponent = new TextComponentTranslation("chat.type.admin", new Object[]{sender.getName(), new TextComponentTranslation(translationKey, translationArgs)});
      itextcomponent.getStyle().setColor(TextFormatting.GRAY);
      itextcomponent.getStyle().setItalic(true);
      if (flag) {
         Iterator var9 = minecraftserver.getPlayerList().getPlayers().iterator();

         label85:
         while(true) {
            ME entityplayer;
            boolean flag1;
            boolean flag2;
            do {
               do {
                  do {
                     do {
                        if (!var9.hasNext()) {
                           break label85;
                        }

                        entityplayer = (ME)var9.next();
                     } while(entityplayer == sender);
                  } while(!minecraftserver.getPlayerList().canSendCommands(entityplayer.getGameProfile()));
               } while(!command.checkPermission(this.server, sender));

               flag1 = sender instanceof Xx && this.server.shouldBroadcastConsoleToOps();
               flag2 = sender instanceof Vo && this.server.shouldBroadcastRconToOps();
            } while(!flag1 && !flag2 && (sender instanceof Vo || sender instanceof Xx));

            entityplayer.sendMessage(itextcomponent);
         }
      }

      if (sender != minecraftserver && minecraftserver.worlds[0].getGameRules().getBoolean("logAdminCommands")) {
         minecraftserver.sendMessage(itextcomponent);
      }

      boolean flag3 = minecraftserver.worlds[0].getGameRules().getBoolean("sendCommandFeedback");
      if (sender instanceof XZ) {
         flag3 = ((XZ)sender).shouldTrackOutput();
      }

      if ((flags & 1) != 1 && flag3 || sender instanceof Xx) {
         sender.sendMessage(new TextComponentTranslation(translationKey, translationArgs));
      }

   }

   protected Xx getServer() {
      return this.server;
   }
}
