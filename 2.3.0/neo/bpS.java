package neo;

import java.util.ArrayList;
import java.util.List;

public class bpS {
   private final List<bpQ> listUniforms = new ArrayList();

   public bpS() {
   }

   public void setProgram(int program) {
      for(int i = 0; i < this.listUniforms.size(); ++i) {
         bpQ shaderuniformbase = (bpQ)this.listUniforms.get(i);
         shaderuniformbase.setProgram(program);
      }

   }

   public void reset() {
      for(int i = 0; i < this.listUniforms.size(); ++i) {
         bpQ shaderuniformbase = (bpQ)this.listUniforms.get(i);
         shaderuniformbase.reset();
      }

   }

   public bpK make1i(String name) {
      bpK shaderuniform1i = new bpK(name);
      this.listUniforms.add(shaderuniform1i);
      return shaderuniform1i;
   }

   public bpM make2i(String name) {
      bpM shaderuniform2i = new bpM(name);
      this.listUniforms.add(shaderuniform2i);
      return shaderuniform2i;
   }

   public bpP make4i(String name) {
      bpP shaderuniform4i = new bpP(name);
      this.listUniforms.add(shaderuniform4i);
      return shaderuniform4i;
   }

   public bpJ make1f(String name) {
      bpJ shaderuniform1f = new bpJ(name);
      this.listUniforms.add(shaderuniform1f);
      return shaderuniform1f;
   }

   public bpN make3f(String name) {
      bpN shaderuniform3f = new bpN(name);
      this.listUniforms.add(shaderuniform3f);
      return shaderuniform3f;
   }

   public bpO make4f(String name) {
      bpO shaderuniform4f = new bpO(name);
      this.listUniforms.add(shaderuniform4f);
      return shaderuniform4f;
   }

   public bpR makeM4(String name) {
      bpR shaderuniformm4 = new bpR(name);
      this.listUniforms.add(shaderuniformm4);
      return shaderuniformm4;
   }
}
