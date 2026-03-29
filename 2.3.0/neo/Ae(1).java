package neo;

import com.google.gson.JsonDeserializer;

public interface Ae<T extends Ad> extends JsonDeserializer<T> {
   String getSectionName();
}
