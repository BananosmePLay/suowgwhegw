package net.minecraft.util.datafix.walkers;

import neo.Ig;
import neo.Ir;
import neo.QQ;
import neo.Yg;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;

public abstract class Filtered implements IDataWalker {
   private final ResourceLocation key;

   public Filtered(Class<?> p_i47309_1_) {
      if (Ig.class.isAssignableFrom(p_i47309_1_)) {
         this.key = Ir.getKey(p_i47309_1_);
      } else if (Yg.class.isAssignableFrom(p_i47309_1_)) {
         this.key = Yg.getKey(p_i47309_1_);
      } else {
         this.key = null;
      }

   }

   public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
      if ((new ResourceLocation(compound.getString("id"))).equals(this.key)) {
         compound = this.filteredProcess(fixer, compound, versionIn);
      }

      return compound;
   }

   abstract QQ filteredProcess(IDataFixer var1, QQ var2, int var3);
}
