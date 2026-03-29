package neo;

public class bke implements bjT {
   public bke() {
   }

   public blU getParameter(String name) {
      bkb renderentityparameterbool = bkb.parse(name);
      if (renderentityparameterbool != null) {
         return renderentityparameterbool;
      } else {
         bkd renderentityparameterfloat = bkd.parse(name);
         return renderentityparameterfloat != null ? renderentityparameterfloat : null;
      }
   }
}
