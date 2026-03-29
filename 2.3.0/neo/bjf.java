package neo;

public class bjf {
   private int profession;
   private int[] careers;

   public bjf(int profession) {
      this(profession, (int[])null);
   }

   public bjf(int profession, int career) {
      this(profession, new int[]{career});
   }

   public bjf(int profession, int[] careers) {
      this.profession = profession;
      this.careers = careers;
   }

   public boolean matches(int prof, int car) {
      if (this.profession != prof) {
         return false;
      } else {
         return this.careers == null || XH.equalsOne(car, this.careers);
      }
   }

   private boolean hasCareer(int car) {
      return this.careers == null ? false : XH.equalsOne(car, this.careers);
   }

   public boolean addCareer(int car) {
      if (this.careers == null) {
         this.careers = new int[]{car};
         return true;
      } else if (this.hasCareer(car)) {
         return false;
      } else {
         this.careers = XH.addIntToArray(this.careers, car);
         return true;
      }
   }

   public int getProfession() {
      return this.profession;
   }

   public int[] getCareers() {
      return this.careers;
   }

   public String toString() {
      return this.careers == null ? "" + this.profession : "" + this.profession + ":" + XH.arrayToString(this.careers);
   }
}
