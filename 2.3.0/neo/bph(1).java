package neo;

import java.util.ArrayList;
import java.util.List;

public class bph {
   private List<bpg> programs = new ArrayList();
   private bpg programNone;

   public bph() {
      this.programNone = this.make("", bpj.NONE, true);
   }

   public bpg make(String name, bpj programStage, bpg backupProgram) {
      int i = this.programs.size();
      bpg program = new bpg(i, name, programStage, backupProgram);
      this.programs.add(program);
      return program;
   }

   private bpg make(String name, bpj programStage, boolean ownBackup) {
      int i = this.programs.size();
      bpg program = new bpg(i, name, programStage, ownBackup);
      this.programs.add(program);
      return program;
   }

   public bpg makeGbuffers(String name, bpg backupProgram) {
      return this.make(name, bpj.GBUFFERS, backupProgram);
   }

   public bpg makeComposite(String name) {
      return this.make(name, bpj.COMPOSITE, this.programNone);
   }

   public bpg makeDeferred(String name) {
      return this.make(name, bpj.DEFERRED, this.programNone);
   }

   public bpg makeShadow(String name, bpg backupProgram) {
      return this.make(name, bpj.SHADOW, backupProgram);
   }

   public bpg makeVirtual(String name) {
      return this.make(name, bpj.NONE, true);
   }

   public bpg[] makeComposites(String prefix, int count) {
      bpg[] aprogram = new bpg[count];

      for(int i = 0; i < count; ++i) {
         String s = i == 0 ? prefix : prefix + i;
         aprogram[i] = this.makeComposite(s);
      }

      return aprogram;
   }

   public bpg[] makeDeferreds(String prefix, int count) {
      bpg[] aprogram = new bpg[count];

      for(int i = 0; i < count; ++i) {
         String s = i == 0 ? prefix : prefix + i;
         aprogram[i] = this.makeDeferred(s);
      }

      return aprogram;
   }

   public bpg getProgramNone() {
      return this.programNone;
   }

   public int getCount() {
      return this.programs.size();
   }

   public bpg getProgram(String name) {
      if (name == null) {
         return null;
      } else {
         for(int i = 0; i < this.programs.size(); ++i) {
            bpg program = (bpg)this.programs.get(i);
            String s = program.getName();
            if (s.equals(name)) {
               return program;
            }
         }

         return null;
      }
   }

   public String[] getProgramNames() {
      String[] astring = new String[this.programs.size()];

      for(int i = 0; i < astring.length; ++i) {
         astring[i] = ((bpg)this.programs.get(i)).getName();
      }

      return astring;
   }

   public bpg[] getPrograms() {
      bpg[] aprogram = (bpg[])((bpg[])this.programs.toArray(new bpg[this.programs.size()]));
      return aprogram;
   }

   public bpg[] getPrograms(bpg programFrom, bpg programTo) {
      int i = programFrom.getIndex();
      int j = programTo.getIndex();
      if (i > j) {
         int k = i;
         i = j;
         j = k;
      }

      bpg[] aprogram = new bpg[j - i + 1];

      for(int l = 0; l < aprogram.length; ++l) {
         aprogram[l] = (bpg)this.programs.get(i + l);
      }

      return aprogram;
   }

   public String toString() {
      return this.programs.toString();
   }
}
