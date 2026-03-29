package neo;

import javax.annotation.Nullable;

public class bhJ extends bhH {
   public bhJ() {
      super((bgm)null);
   }

   @Nullable
   public bhZ getOrLoadData(Class<? extends bhZ> clazz, String dataIdentifier) {
      return (bhZ)this.loadedDataMap.get(dataIdentifier);
   }

   public void setData(String dataIdentifier, bhZ data) {
      this.loadedDataMap.put(dataIdentifier, data);
   }

   public void saveAllData() {
   }

   public int getUniqueDataId(String key) {
      return 0;
   }
}
