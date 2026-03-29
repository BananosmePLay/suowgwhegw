package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ca {
   private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   private final h advancementProgress;
   private Date obtained;

   public ca(h advancementProgressIn) {
      this.advancementProgress = advancementProgressIn;
   }

   public boolean isObtained() {
      return this.obtained != null;
   }

   public void obtain() {
      this.obtained = new Date();
   }

   public void reset() {
      this.obtained = null;
   }

   public Date getObtained() {
      return this.obtained;
   }

   public String toString() {
      return "CriterionProgress{obtained=" + (this.obtained == null ? "false" : this.obtained) + '}';
   }

   public void write(SA buf) {
      buf.writeBoolean(this.obtained != null);
      if (this.obtained != null) {
         buf.writeTime(this.obtained);
      }

   }

   public JsonElement serialize() {
      return (JsonElement)(this.obtained != null ? new JsonPrimitive(DATE_TIME_FORMATTER.format(this.obtained)) : JsonNull.INSTANCE);
   }

   public static ca read(SA buf, h advancementProgressIn) {
      ca criterionprogress = new ca(advancementProgressIn);
      if (buf.readBoolean()) {
         criterionprogress.obtained = buf.readTime();
      }

      return criterionprogress;
   }

   public static ca fromDateTime(h advancementProgressIn, String dateTime) {
      ca criterionprogress = new ca(advancementProgressIn);

      try {
         criterionprogress.obtained = DATE_TIME_FORMATTER.parse(dateTime);
         return criterionprogress;
      } catch (ParseException var4) {
         ParseException parseexception = var4;
         throw new JsonSyntaxException("Invalid datetime: " + dateTime, parseexception);
      }
   }
}
