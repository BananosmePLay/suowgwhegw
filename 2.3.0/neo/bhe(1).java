package neo;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class bhe extends TypeAdapter<bhf> {
   public bhe() {
   }

   public void write(JsonWriter p_write_1_, bhf p_write_2_) throws IOException {
      p_write_1_.value(bhf.access$000(p_write_2_));
   }

   public bhf read(JsonReader p_read_1_) throws IOException {
      return bhf.fromString(p_read_1_.nextString());
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object read(JsonReader var1) throws IOException {
      return this.read(var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void write(JsonWriter var1, Object var2) throws IOException {
      this.write(var1, (bhf)var2);
   }
}
