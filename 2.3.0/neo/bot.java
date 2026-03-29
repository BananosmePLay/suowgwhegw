package neo;

import net.minecraft.util.Util;

public class bot {
   private static String PREFIX_MACRO = "MC_";
   public static final String MC_VERSION = "MC_VERSION";
   public static final String MC_GL_VERSION = "MC_GL_VERSION";
   public static final String MC_GLSL_VERSION = "MC_GLSL_VERSION";
   public static final String MC_OS_WINDOWS = "MC_OS_WINDOWS";
   public static final String MC_OS_MAC = "MC_OS_MAC";
   public static final String MC_OS_LINUX = "MC_OS_LINUX";
   public static final String MC_OS_OTHER = "MC_OS_OTHER";
   public static final String MC_GL_VENDOR_ATI = "MC_GL_VENDOR_ATI";
   public static final String MC_GL_VENDOR_INTEL = "MC_GL_VENDOR_INTEL";
   public static final String MC_GL_VENDOR_NVIDIA = "MC_GL_VENDOR_NVIDIA";
   public static final String MC_GL_VENDOR_XORG = "MC_GL_VENDOR_XORG";
   public static final String MC_GL_VENDOR_OTHER = "MC_GL_VENDOR_OTHER";
   public static final String MC_GL_RENDERER_RADEON = "MC_GL_RENDERER_RADEON";
   public static final String MC_GL_RENDERER_GEFORCE = "MC_GL_RENDERER_GEFORCE";
   public static final String MC_GL_RENDERER_QUADRO = "MC_GL_RENDERER_QUADRO";
   public static final String MC_GL_RENDERER_INTEL = "MC_GL_RENDERER_INTEL";
   public static final String MC_GL_RENDERER_GALLIUM = "MC_GL_RENDERER_GALLIUM";
   public static final String MC_GL_RENDERER_MESA = "MC_GL_RENDERER_MESA";
   public static final String MC_GL_RENDERER_OTHER = "MC_GL_RENDERER_OTHER";
   public static final String MC_FXAA_LEVEL = "MC_FXAA_LEVEL";
   public static final String MC_NORMAL_MAP = "MC_NORMAL_MAP";
   public static final String MC_SPECULAR_MAP = "MC_SPECULAR_MAP";
   public static final String MC_RENDER_QUALITY = "MC_RENDER_QUALITY";
   public static final String MC_SHADOW_QUALITY = "MC_SHADOW_QUALITY";
   public static final String MC_HAND_DEPTH = "MC_HAND_DEPTH";
   public static final String MC_OLD_HAND_LIGHT = "MC_OLD_HAND_LIGHT";
   public static final String MC_OLD_LIGHTING = "MC_OLD_LIGHTING";
   private static bor[] extensionMacros;

   public bot() {
   }

   public static String getOs() {
      Util.EnumOS util$enumos = Util.getOSType();
      switch (util$enumos) {
         case WINDOWS:
            return "MC_OS_WINDOWS";
         case OSX:
            return "MC_OS_MAC";
         case LINUX:
            return "MC_OS_LINUX";
         default:
            return "MC_OS_OTHER";
      }
   }

   public static String getVendor() {
      String s = XH.openGlVendor;
      if (s == null) {
         return "MC_GL_VENDOR_OTHER";
      } else {
         s = s.toLowerCase();
         if (s.startsWith("ati")) {
            return "MC_GL_VENDOR_ATI";
         } else if (s.startsWith("intel")) {
            return "MC_GL_VENDOR_INTEL";
         } else if (s.startsWith("nvidia")) {
            return "MC_GL_VENDOR_NVIDIA";
         } else {
            return s.startsWith("x.org") ? "MC_GL_VENDOR_XORG" : "MC_GL_VENDOR_OTHER";
         }
      }
   }

   public static String getRenderer() {
      String s = XH.openGlRenderer;
      if (s == null) {
         return "MC_GL_RENDERER_OTHER";
      } else {
         s = s.toLowerCase();
         if (s.startsWith("amd")) {
            return "MC_GL_RENDERER_RADEON";
         } else if (s.startsWith("ati")) {
            return "MC_GL_RENDERER_RADEON";
         } else if (s.startsWith("radeon")) {
            return "MC_GL_RENDERER_RADEON";
         } else if (s.startsWith("gallium")) {
            return "MC_GL_RENDERER_GALLIUM";
         } else if (s.startsWith("intel")) {
            return "MC_GL_RENDERER_INTEL";
         } else if (s.startsWith("geforce")) {
            return "MC_GL_RENDERER_GEFORCE";
         } else if (s.startsWith("nvidia")) {
            return "MC_GL_RENDERER_GEFORCE";
         } else if (s.startsWith("quadro")) {
            return "MC_GL_RENDERER_QUADRO";
         } else if (s.startsWith("nvs")) {
            return "MC_GL_RENDERER_QUADRO";
         } else {
            return s.startsWith("mesa") ? "MC_GL_RENDERER_MESA" : "MC_GL_RENDERER_OTHER";
         }
      }
   }

   public static String getPrefixMacro() {
      return PREFIX_MACRO;
   }

   public static bor[] getExtensions() {
      if (extensionMacros == null) {
         String[] astring = XH.getOpenGlExtensions();
         bor[] ashadermacro = new bor[astring.length];

         for(int i = 0; i < astring.length; ++i) {
            ashadermacro[i] = new bor(PREFIX_MACRO + astring[i], "");
         }

         extensionMacros = ashadermacro;
      }

      return extensionMacros;
   }

   public static String getFixedMacroLines() {
      StringBuilder stringbuilder = new StringBuilder();
      addMacroLine(stringbuilder, "MC_VERSION", XH.getMinecraftVersionInt());
      addMacroLine(stringbuilder, "MC_GL_VERSION " + XH.getGlVersion().toInt());
      addMacroLine(stringbuilder, "MC_GLSL_VERSION " + XH.getGlslVersion().toInt());
      addMacroLine(stringbuilder, getOs());
      addMacroLine(stringbuilder, getVendor());
      addMacroLine(stringbuilder, getRenderer());
      return stringbuilder.toString();
   }

   public static String getOptionMacroLines() {
      StringBuilder stringbuilder = new StringBuilder();
      if (bpq.configAntialiasingLevel > 0) {
         addMacroLine(stringbuilder, "MC_FXAA_LEVEL", bpq.configAntialiasingLevel);
      }

      if (bpq.configNormalMap) {
         addMacroLine(stringbuilder, "MC_NORMAL_MAP");
      }

      if (bpq.configSpecularMap) {
         addMacroLine(stringbuilder, "MC_SPECULAR_MAP");
      }

      addMacroLine(stringbuilder, "MC_RENDER_QUALITY", bpq.configRenderResMul);
      addMacroLine(stringbuilder, "MC_SHADOW_QUALITY", bpq.configShadowResMul);
      addMacroLine(stringbuilder, "MC_HAND_DEPTH", bpq.configHandDepthMul);
      if (bpq.isOldHandLight()) {
         addMacroLine(stringbuilder, "MC_OLD_HAND_LIGHT");
      }

      if (bpq.isOldLighting()) {
         addMacroLine(stringbuilder, "MC_OLD_LIGHTING");
      }

      return stringbuilder.toString();
   }

   private static void addMacroLine(StringBuilder sb, String name, int value) {
      sb.append("#define ");
      sb.append(name);
      sb.append(" ");
      sb.append(value);
      sb.append("\n");
   }

   private static void addMacroLine(StringBuilder sb, String name, float value) {
      sb.append("#define ");
      sb.append(name);
      sb.append(" ");
      sb.append(value);
      sb.append("\n");
   }

   private static void addMacroLine(StringBuilder sb, String name) {
      sb.append("#define ");
      sb.append(name);
      sb.append("\n");
   }
}
