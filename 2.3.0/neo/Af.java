package neo;

import java.util.Collection;

public class Af implements Ad {
   private final Collection<AD> languages;

   public Af(Collection<AD> languagesIn) {
      this.languages = languagesIn;
   }

   public Collection<AD> getLanguages() {
      return this.languages;
   }
}
