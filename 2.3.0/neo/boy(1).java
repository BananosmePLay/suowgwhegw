package neo;

public class boy extends bou {
   public boy(String name) {
      super(name, (String)null, (String)null, new String[0], (String)null, (String)null);
   }

   public String getNameText() {
      return bpq.translate("screen." + this.getName(), this.getName());
   }

   public String getDescriptionText() {
      return bpq.translate("screen." + this.getName() + ".comment", (String)null);
   }
}
