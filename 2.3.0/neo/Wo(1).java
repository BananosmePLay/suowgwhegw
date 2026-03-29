package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.text.TextFormatting;

public interface Wo {
   Map<String, Wo> INSTANCES = Maps.newHashMap();
   Wo DUMMY = new Wu("dummy");
   Wo TRIGGER = new Wu("trigger");
   Wo DEATH_COUNT = new Wu("deathCount");
   Wo PLAYER_KILL_COUNT = new Wu("playerKillCount");
   Wo TOTAL_KILL_COUNT = new Wu("totalKillCount");
   Wo HEALTH = new Ww("health");
   Wo FOOD = new Wx("food");
   Wo AIR = new Wx("air");
   Wo ARMOR = new Wx("armor");
   Wo XP = new Wx("xp");
   Wo LEVEL = new Wx("level");
   Wo[] TEAM_KILL = new Wo[]{new Wv("teamkill.", TextFormatting.BLACK), new Wv("teamkill.", TextFormatting.DARK_BLUE), new Wv("teamkill.", TextFormatting.DARK_GREEN), new Wv("teamkill.", TextFormatting.DARK_AQUA), new Wv("teamkill.", TextFormatting.DARK_RED), new Wv("teamkill.", TextFormatting.DARK_PURPLE), new Wv("teamkill.", TextFormatting.GOLD), new Wv("teamkill.", TextFormatting.GRAY), new Wv("teamkill.", TextFormatting.DARK_GRAY), new Wv("teamkill.", TextFormatting.BLUE), new Wv("teamkill.", TextFormatting.GREEN), new Wv("teamkill.", TextFormatting.AQUA), new Wv("teamkill.", TextFormatting.RED), new Wv("teamkill.", TextFormatting.LIGHT_PURPLE), new Wv("teamkill.", TextFormatting.YELLOW), new Wv("teamkill.", TextFormatting.WHITE)};
   Wo[] KILLED_BY_TEAM = new Wo[]{new Wv("killedByTeam.", TextFormatting.BLACK), new Wv("killedByTeam.", TextFormatting.DARK_BLUE), new Wv("killedByTeam.", TextFormatting.DARK_GREEN), new Wv("killedByTeam.", TextFormatting.DARK_AQUA), new Wv("killedByTeam.", TextFormatting.DARK_RED), new Wv("killedByTeam.", TextFormatting.DARK_PURPLE), new Wv("killedByTeam.", TextFormatting.GOLD), new Wv("killedByTeam.", TextFormatting.GRAY), new Wv("killedByTeam.", TextFormatting.DARK_GRAY), new Wv("killedByTeam.", TextFormatting.BLUE), new Wv("killedByTeam.", TextFormatting.GREEN), new Wv("killedByTeam.", TextFormatting.AQUA), new Wv("killedByTeam.", TextFormatting.RED), new Wv("killedByTeam.", TextFormatting.LIGHT_PURPLE), new Wv("killedByTeam.", TextFormatting.YELLOW), new Wv("killedByTeam.", TextFormatting.WHITE)};

   String getName();

   boolean isReadOnly();

   Wn getRenderType();
}
