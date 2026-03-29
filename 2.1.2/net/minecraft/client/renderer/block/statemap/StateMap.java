package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class StateMap extends StateMapperBase {
   private final IProperty<?> name;
   private final String suffix;
   private final List<IProperty<?>> ignored;

   private StateMap(@Nullable IProperty<?> name, @Nullable String suffix, List<IProperty<?>> ignored) {
      this.name = name;
      this.suffix = suffix;
      this.ignored = ignored;
   }

   protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
      Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
      String s;
      if (this.name == null) {
         s = ((ResourceLocation)Block.REGISTRY.getNameForObject(state.getBlock())).toString();
      } else {
         s = this.removeName(this.name, map);
      }

      if (this.suffix != null) {
         s = s + this.suffix;
      }

      Iterator var4 = this.ignored.iterator();

      while(var4.hasNext()) {
         IProperty<?> iproperty = (IProperty)var4.next();
         map.remove(iproperty);
      }

      return new ModelResourceLocation(s, this.getPropertyString(map));
   }

   private <T extends Comparable<T>> String removeName(IProperty<T> property, Map<IProperty<?>, Comparable<?>> values) {
      return property.getName((Comparable)values.remove(this.name));
   }

   // $FF: synthetic method
   StateMap(IProperty x0, String x1, List x2, Object x3) {
      this(x0, x1, x2);
   }

   public static class Builder {
      private IProperty<?> name;
      private String suffix;
      private final List<IProperty<?>> ignored = Lists.newArrayList();

      public Builder() {
      }

      public Builder withName(IProperty<?> builderPropertyIn) {
         this.name = builderPropertyIn;
         return this;
      }

      public Builder withSuffix(String builderSuffixIn) {
         this.suffix = builderSuffixIn;
         return this;
      }

      public Builder ignore(IProperty<?>... ignores) {
         Collections.addAll(this.ignored, ignores);
         return this;
      }

      public StateMap build() {
         return new StateMap(this.name, this.suffix, this.ignored);
      }
   }
}
