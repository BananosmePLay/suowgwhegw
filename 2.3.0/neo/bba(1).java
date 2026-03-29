package neo;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class bba {
   private final TreeMap<String, baY> rules = new TreeMap();

   public bba() {
      this.addGameRule("doFireTick", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("mobGriefing", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("keepInventory", "false", baZ.BOOLEAN_VALUE);
      this.addGameRule("doMobSpawning", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("doMobLoot", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("doTileDrops", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("doEntityDrops", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("commandBlockOutput", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("naturalRegeneration", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("doDaylightCycle", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("logAdminCommands", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("showDeathMessages", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("randomTickSpeed", "3", baZ.NUMERICAL_VALUE);
      this.addGameRule("sendCommandFeedback", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("reducedDebugInfo", "false", baZ.BOOLEAN_VALUE);
      this.addGameRule("spectatorsGenerateChunks", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("spawnRadius", "10", baZ.NUMERICAL_VALUE);
      this.addGameRule("disableElytraMovementCheck", "false", baZ.BOOLEAN_VALUE);
      this.addGameRule("maxEntityCramming", "24", baZ.NUMERICAL_VALUE);
      this.addGameRule("doWeatherCycle", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("doLimitedCrafting", "false", baZ.BOOLEAN_VALUE);
      this.addGameRule("maxCommandChainLength", "65536", baZ.NUMERICAL_VALUE);
      this.addGameRule("announceAdvancements", "true", baZ.BOOLEAN_VALUE);
      this.addGameRule("gameLoopFunction", "-", baZ.FUNCTION);
   }

   public void addGameRule(String key, String value, baZ type) {
      this.rules.put(key, new baY(value, type));
   }

   public void setOrCreateGameRule(String key, String ruleValue) {
      baY gamerules$value = (baY)this.rules.get(key);
      if (gamerules$value != null) {
         gamerules$value.setValue(ruleValue);
      } else {
         this.addGameRule(key, ruleValue, baZ.ANY_VALUE);
      }

   }

   public String getString(String name) {
      baY gamerules$value = (baY)this.rules.get(name);
      return gamerules$value != null ? gamerules$value.getString() : "";
   }

   public boolean getBoolean(String name) {
      baY gamerules$value = (baY)this.rules.get(name);
      return gamerules$value != null ? gamerules$value.getBoolean() : false;
   }

   public int getInt(String name) {
      baY gamerules$value = (baY)this.rules.get(name);
      return gamerules$value != null ? gamerules$value.getInt() : 0;
   }

   public QQ writeToNBT() {
      QQ nbttagcompound = new QQ();
      Iterator var2 = this.rules.keySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         baY gamerules$value = (baY)this.rules.get(s);
         nbttagcompound.setString(s, gamerules$value.getString());
      }

      return nbttagcompound;
   }

   public void readFromNBT(QQ nbt) {
      Iterator var2 = nbt.getKeySet().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         this.setOrCreateGameRule(s, nbt.getString(s));
      }

   }

   public String[] getRules() {
      Set<String> set = this.rules.keySet();
      return (String[])((String[])set.toArray(new String[set.size()]));
   }

   public boolean hasRule(String name) {
      return this.rules.containsKey(name);
   }

   public boolean areSameType(String key, baZ otherValue) {
      baY gamerules$value = (baY)this.rules.get(key);
      return gamerules$value != null && (gamerules$value.getType() == otherValue || otherValue == baZ.ANY_VALUE);
   }
}
