package neo;

public enum nj {
   MOVEMENT_KEYS(0, 0),
   MOUSE(1, 0),
   TREE(2, 0),
   RECIPE_BOOK(0, 1),
   WOODEN_PLANKS(1, 1);

   private final int column;
   private final int row;

   private nj(int columnIn, int rowIn) {
      this.column = columnIn;
      this.row = rowIn;
   }

   public void draw(jI guiIn, int x, int y) {
      yh.enableBlend();
      guiIn.drawTexturedModalRect(x, y, 176 + this.column * 20, this.row * 20, 20, 20);
      yh.enableBlend();
   }
}
