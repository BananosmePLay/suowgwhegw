package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class tp {
   private hT<?> name;
   private String suffix;
   private final List<hT<?>> ignored = Lists.newArrayList();

   public tp() {
   }

   public tp withName(hT<?> builderPropertyIn) {
      this.name = builderPropertyIn;
      return this;
   }

   public tp withSuffix(String builderSuffixIn) {
      this.suffix = builderSuffixIn;
      return this;
   }

   public tp ignore(hT<?>... ignores) {
      Collections.addAll(this.ignored, ignores);
      return this;
   }

   public tq build() {
      return new tq(this.name, this.suffix, this.ignored);
   }
}
