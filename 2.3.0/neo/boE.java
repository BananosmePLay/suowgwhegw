package neo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class boE {
   private static final Pattern PATTERN_VERSION = Pattern.compile("^\\s*#version\\s+.*$");
   private static final Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
   private static final Set<String> setConstNames = makeSetConstNames();
   private static final Map<String, Integer> mapAlphaFuncs = makeMapAlphaFuncs();
   private static final Map<String, Integer> mapBlendFactors = makeMapBlendFactors();

   public boE() {
   }

   public static bou[] parseShaderPackOptions(bpa shaderPack, String[] programNames, List<Integer> listDimensions) {
      if (shaderPack == null) {
         return new bou[0];
      } else {
         Map<String, bou> map = new HashMap();
         collectShaderOptions(shaderPack, "/shaders", programNames, map);
         Iterator<Integer> iterator = listDimensions.iterator();

         while(iterator.hasNext()) {
            int i = (Integer)iterator.next();
            String s = "/shaders/world" + i;
            collectShaderOptions(shaderPack, s, programNames, map);
         }

         Collection<bou> collection = map.values();
         bou[] ashaderoption = (bou[])((bou[])collection.toArray(new bou[collection.size()]));
         Comparator<bou> comparator = new Comparator<bou>() {
            public int compare(bou o1, bou o2) {
               return o1.getName().compareToIgnoreCase(o2.getName());
            }

            // $FF: synthetic method
            // $FF: bridge method
            public int compare(Object var1, Object var2) {
               return this.compare((bou)var1, (bou)var2);
            }
         };
         Arrays.sort(ashaderoption, comparator);
         return ashaderoption;
      }
   }

   private static void collectShaderOptions(bpa shaderPack, String dir, String[] programNames, Map<String, bou> mapOptions) {
      for(int i = 0; i < programNames.length; ++i) {
         String s = programNames[i];
         if (!s.equals("")) {
            String s1 = dir + "/" + s + ".vsh";
            String s2 = dir + "/" + s + ".fsh";
            collectShaderOptions(shaderPack, s1, mapOptions);
            collectShaderOptions(shaderPack, s2, mapOptions);
         }
      }

   }

   private static void collectShaderOptions(bpa sp, String path, Map<String, bou> mapOptions) {
      String[] astring = getLines(sp, path);

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         bou shaderoption = getShaderOption(s, path);
         if (shaderoption != null && !shaderoption.getName().startsWith(bot.getPrefixMacro()) && (!shaderoption.checkUsed() || isOptionUsed(shaderoption, astring))) {
            String s1 = shaderoption.getName();
            bou shaderoption1 = (bou)mapOptions.get(s1);
            if (shaderoption1 != null) {
               if (!XH.equals(shaderoption1.getValueDefault(), shaderoption.getValueDefault())) {
                  XH.warn("Ambiguous shader option: " + shaderoption.getName());
                  XH.warn(" - in " + XH.arrayToString((Object[])shaderoption1.getPaths()) + ": " + shaderoption1.getValueDefault());
                  XH.warn(" - in " + XH.arrayToString((Object[])shaderoption.getPaths()) + ": " + shaderoption.getValueDefault());
                  shaderoption1.setEnabled(false);
               }

               if (shaderoption1.getDescription() == null || shaderoption1.getDescription().length() <= 0) {
                  shaderoption1.setDescription(shaderoption.getDescription());
               }

               shaderoption1.addPaths(shaderoption.getPaths());
            } else {
               mapOptions.put(s1, shaderoption);
            }
         }
      }

   }

   private static boolean isOptionUsed(bou so, String[] lines) {
      for(int i = 0; i < lines.length; ++i) {
         String s = lines[i];
         if (so.isUsedInLine(s)) {
            return true;
         }
      }

      return false;
   }

   private static String[] getLines(bpa sp, String path) {
      try {
         List<String> list = new ArrayList();
         String s = loadFile(path, sp, 0, list, 0);
         if (s == null) {
            return new String[0];
         } else {
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(s.getBytes());
            String[] astring = XH.readLines((InputStream)bytearrayinputstream);
            return astring;
         }
      } catch (IOException var6) {
         IOException ioexception = var6;
         XH.dbg(ioexception.getClass().getName() + ": " + ioexception.getMessage());
         return new String[0];
      }
   }

   private static bou getShaderOption(String line, String path) {
      bou shaderoption = null;
      if (shaderoption == null) {
         shaderoption = boz.parseOption(line, path);
      }

      if (shaderoption == null) {
         shaderoption = boB.parseOption(line, path);
      }

      if (shaderoption != null) {
         return shaderoption;
      } else {
         if (shaderoption == null) {
            shaderoption = boA.parseOption(line, path);
         }

         if (shaderoption == null) {
            shaderoption = boC.parseOption(line, path);
         }

         return shaderoption != null && setConstNames.contains(shaderoption.getName()) ? shaderoption : null;
      }
   }

   private static Set<String> makeSetConstNames() {
      Set<String> set = new HashSet();
      set.add("shadowMapResolution");
      set.add("shadowMapFov");
      set.add("shadowDistance");
      set.add("shadowDistanceRenderMul");
      set.add("shadowIntervalSize");
      set.add("generateShadowMipmap");
      set.add("generateShadowColorMipmap");
      set.add("shadowHardwareFiltering");
      set.add("shadowHardwareFiltering0");
      set.add("shadowHardwareFiltering1");
      set.add("shadowtex0Mipmap");
      set.add("shadowtexMipmap");
      set.add("shadowtex1Mipmap");
      set.add("shadowcolor0Mipmap");
      set.add("shadowColor0Mipmap");
      set.add("shadowcolor1Mipmap");
      set.add("shadowColor1Mipmap");
      set.add("shadowtex0Nearest");
      set.add("shadowtexNearest");
      set.add("shadow0MinMagNearest");
      set.add("shadowtex1Nearest");
      set.add("shadow1MinMagNearest");
      set.add("shadowcolor0Nearest");
      set.add("shadowColor0Nearest");
      set.add("shadowColor0MinMagNearest");
      set.add("shadowcolor1Nearest");
      set.add("shadowColor1Nearest");
      set.add("shadowColor1MinMagNearest");
      set.add("wetnessHalflife");
      set.add("drynessHalflife");
      set.add("eyeBrightnessHalflife");
      set.add("centerDepthHalflife");
      set.add("sunPathRotation");
      set.add("ambientOcclusionLevel");
      set.add("superSamplingLevel");
      set.add("noiseTextureResolution");
      return set;
   }

   public static boG[] parseProfiles(Properties props, bou[] shaderOptions) {
      String s = "profile.";
      List<boG> list = new ArrayList();
      Iterator var4 = props.keySet().iterator();

      while(var4.hasNext()) {
         Object s11 = var4.next();
         String s1 = (String)s11;
         if (s1.startsWith(s)) {
            String s2 = s1.substring(s.length());
            props.getProperty(s1);
            Set<String> set = new HashSet();
            boG shaderprofile = parseProfile(s2, props, set, shaderOptions);
            if (shaderprofile != null) {
               list.add(shaderprofile);
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         boG[] ashaderprofile = (boG[])((boG[])list.toArray(new boG[list.size()]));
         return ashaderprofile;
      }
   }

   public static Map<String, blV> parseProgramConditions(Properties props, bou[] shaderOptions) {
      String s = "program.";
      Pattern pattern = Pattern.compile("program\\.([^.]+)\\.enabled");
      Map<String, blV> map = new HashMap();
      Iterator var5 = props.keySet().iterator();

      while(var5.hasNext()) {
         Object s11 = var5.next();
         String s1 = (String)s11;
         Matcher matcher = pattern.matcher(s1);
         if (matcher.matches()) {
            String s2 = matcher.group(1);
            String s3 = props.getProperty(s1).trim();
            blV iexpressionbool = parseOptionExpression(s3, shaderOptions);
            if (iexpressionbool == null) {
               bpx.severe("Error parsing program condition: " + s1);
            } else {
               map.put(s2, iexpressionbool);
            }
         }
      }

      return map;
   }

   private static blV parseOptionExpression(String val, bou[] shaderOptions) {
      try {
         bow shaderoptionresolver = new bow(shaderOptions);
         blM expressionparser = new blM(shaderoptionresolver);
         blV iexpressionbool = expressionparser.parseBool(val);
         return iexpressionbool;
      } catch (bmd var5) {
         bmd parseexception = var5;
         bpx.warning(parseexception.getClass().getName() + ": " + parseexception.getMessage());
         return null;
      }
   }

   public static Set<String> parseOptionSliders(Properties props, bou[] shaderOptions) {
      Set<String> set = new HashSet();
      String s = props.getProperty("sliders");
      if (s == null) {
         return set;
      } else {
         String[] astring = XH.tokenize(s, " ");

         for(int i = 0; i < astring.length; ++i) {
            String s1 = astring[i];
            bou shaderoption = bpt.getShaderOption(s1, shaderOptions);
            if (shaderoption == null) {
               XH.warn("Invalid shader option: " + s1);
            } else {
               set.add(s1);
            }
         }

         return set;
      }
   }

   private static boG parseProfile(String name, Properties props, Set<String> parsedProfiles, bou[] shaderOptions) {
      String s = "profile.";
      String s1 = s + name;
      if (parsedProfiles.contains(s1)) {
         XH.warn("[Shaders] Profile already parsed: " + name);
         return null;
      } else {
         parsedProfiles.add(name);
         boG shaderprofile = new boG(name);
         String s2 = props.getProperty(s1);
         String[] astring = XH.tokenize(s2, " ");

         for(int i = 0; i < astring.length; ++i) {
            String s3 = astring[i];
            if (s3.startsWith(s)) {
               String s4 = s3.substring(s.length());
               boG shaderprofile1 = parseProfile(s4, props, parsedProfiles, shaderOptions);
               if (shaderprofile != null) {
                  shaderprofile.addOptionValues(shaderprofile1);
                  shaderprofile.addDisabledPrograms(shaderprofile1.getDisabledPrograms());
               }
            } else {
               String[] astring1 = XH.tokenize(s3, ":=");
               String s7;
               if (astring1.length == 1) {
                  s7 = astring1[0];
                  boolean flag = true;
                  if (s7.startsWith("!")) {
                     flag = false;
                     s7 = s7.substring(1);
                  }

                  String s5 = "program.";
                  if (s7.startsWith(s5)) {
                     String s6 = s7.substring(s5.length());
                     if (!bpq.isProgramPath(s6)) {
                        XH.warn("Invalid program: " + s6 + " in profile: " + shaderprofile.getName());
                     } else if (flag) {
                        shaderprofile.removeDisabledProgram(s6);
                     } else {
                        shaderprofile.addDisabledProgram(s6);
                     }
                  } else {
                     bou shaderoption1 = bpt.getShaderOption(s7, shaderOptions);
                     if (!(shaderoption1 instanceof boz)) {
                        XH.warn("[Shaders] Invalid option: " + s7);
                     } else {
                        shaderprofile.addOptionValue(s7, String.valueOf(flag));
                        shaderoption1.setVisible(true);
                     }
                  }
               } else if (astring1.length != 2) {
                  XH.warn("[Shaders] Invalid option value: " + s3);
               } else {
                  s7 = astring1[0];
                  String s9 = astring1[1];
                  bou shaderoption = bpt.getShaderOption(s7, shaderOptions);
                  if (shaderoption == null) {
                     XH.warn("[Shaders] Invalid option: " + s3);
                  } else if (!shaderoption.isValidValue(s9)) {
                     XH.warn("[Shaders] Invalid value: " + s3);
                  } else {
                     shaderoption.setVisible(true);
                     shaderprofile.addOptionValue(s7, s9);
                  }
               }
            }
         }

         return shaderprofile;
      }
   }

   public static Map<String, bop> parseGuiScreens(Properties props, boG[] shaderProfiles, bou[] shaderOptions) {
      Map<String, bop> map = new HashMap();
      parseGuiScreen("screen", props, map, shaderProfiles, shaderOptions);
      return map.isEmpty() ? null : map;
   }

   private static boolean parseGuiScreen(String key, Properties props, Map<String, bop> map, boG[] shaderProfiles, bou[] shaderOptions) {
      String s = props.getProperty(key);
      if (s == null) {
         return false;
      } else {
         List<bou> list = new ArrayList();
         Set<String> set = new HashSet();
         String[] astring = XH.tokenize(s, " ");

         String s1;
         for(int i = 0; i < astring.length; ++i) {
            s1 = astring[i];
            if (s1.equals("<empty>")) {
               list.add((Object)null);
            } else if (set.contains(s1)) {
               XH.warn("[Shaders] Duplicate option: " + s1 + ", key: " + key);
            } else {
               set.add(s1);
               if (s1.equals("<profile>")) {
                  if (shaderProfiles == null) {
                     XH.warn("[Shaders] Option profile can not be used, no profiles defined: " + s1 + ", key: " + key);
                  } else {
                     bov shaderoptionprofile = new bov(shaderProfiles, shaderOptions);
                     list.add(shaderoptionprofile);
                  }
               } else if (s1.equals("*")) {
                  bou shaderoption1 = new box("<rest>");
                  list.add(shaderoption1);
               } else if (s1.startsWith("[") && s1.endsWith("]")) {
                  String s3 = bqP.removePrefixSuffix(s1, "[", "]");
                  if (!s3.matches("^[a-zA-Z0-9_]+$")) {
                     XH.warn("[Shaders] Invalid screen: " + s1 + ", key: " + key);
                  } else if (!parseGuiScreen("screen." + s3, props, map, shaderProfiles, shaderOptions)) {
                     XH.warn("[Shaders] Invalid screen: " + s1 + ", key: " + key);
                  } else {
                     boy shaderoptionscreen = new boy(s3);
                     list.add(shaderoptionscreen);
                  }
               } else {
                  bou shaderoption = bpt.getShaderOption(s1, shaderOptions);
                  if (shaderoption == null) {
                     XH.warn("[Shaders] Invalid option: " + s1 + ", key: " + key);
                     list.add((Object)null);
                  } else {
                     shaderoption.setVisible(true);
                     list.add(shaderoption);
                  }
               }
            }
         }

         bou[] ashaderoption = (bou[])((bou[])list.toArray(new bou[list.size()]));
         s1 = props.getProperty(key + ".columns");
         int j = XH.parseInt(s1, 2);
         bop screenshaderoptions = new bop(key, ashaderoption, j);
         map.put(key, screenshaderoptions);
         return true;
      }
   }

   public static BufferedReader resolveIncludes(BufferedReader reader, String filePath, bpa shaderPack, int fileIndex, List<String> listFiles, int includeLevel) throws IOException {
      String s = "/";
      int i = filePath.lastIndexOf("/");
      if (i >= 0) {
         s = filePath.substring(0, i);
      }

      CharArrayWriter chararraywriter = new CharArrayWriter();
      int j = -1;
      Set<bor> set = new LinkedHashSet();
      int k = 1;

      while(true) {
         String s1 = reader.readLine();
         String s3;
         bor shadermacro1;
         if (s1 == null) {
            char[] achar = chararraywriter.toCharArray();
            if (j >= 0 && set.size() > 0) {
               StringBuilder stringbuilder = new StringBuilder();
               Iterator var25 = set.iterator();

               while(var25.hasNext()) {
                  shadermacro1 = (bor)var25.next();
                  stringbuilder.append("#define ");
                  stringbuilder.append(shadermacro1.getName());
                  stringbuilder.append(" ");
                  stringbuilder.append(shadermacro1.getValue());
                  stringbuilder.append("\n");
               }

               s3 = stringbuilder.toString();
               StringBuilder stringbuilder1 = new StringBuilder(new String(achar));
               stringbuilder1.insert(j, s3);
               String s9 = stringbuilder1.toString();
               achar = s9.toCharArray();
            }

            CharArrayReader chararrayreader = new CharArrayReader(achar);
            return new BufferedReader(chararrayreader);
         }

         Matcher matcher1;
         String s6;
         String s8;
         if (j < 0) {
            matcher1 = PATTERN_VERSION.matcher(s1);
            if (matcher1.matches()) {
               s6 = bot.getFixedMacroLines() + bot.getOptionMacroLines();
               s3 = s1 + "\n" + s6;
               s8 = "#line " + (k + 1) + " " + fileIndex;
               s1 = s3 + s8;
               j = chararraywriter.size() + s3.length();
            }
         }

         matcher1 = PATTERN_INCLUDE.matcher(s1);
         if (matcher1.matches()) {
            s6 = matcher1.group(1);
            boolean flag = s6.startsWith("/");
            s8 = flag ? "/shaders" + s6 : s + "/" + s6;
            if (!listFiles.contains(s8)) {
               listFiles.add(s8);
            }

            int l = listFiles.indexOf(s8) + 1;
            s1 = loadFile(s8, shaderPack, l, listFiles, includeLevel);
            if (s1 == null) {
               throw new IOException("Included file not found: " + filePath);
            }

            if (s1.endsWith("\n")) {
               s1 = s1.substring(0, s1.length() - 1);
            }

            String s5 = "#line 1 " + l + "\n";
            if (s1.startsWith("#version ")) {
               s5 = "";
            }

            s1 = s5 + s1 + "\n#line " + (k + 1) + " " + fileIndex;
         }

         if (j >= 0 && s1.contains(bot.getPrefixMacro())) {
            bor[] ashadermacro = findMacros(s1, bot.getExtensions());

            for(int i1 = 0; i1 < ashadermacro.length; ++i1) {
               shadermacro1 = ashadermacro[i1];
               set.add(shadermacro1);
            }
         }

         chararraywriter.write(s1);
         chararraywriter.write("\n");
         ++k;
      }
   }

   private static bor[] findMacros(String line, bor[] macros) {
      List<bor> list = new ArrayList();

      for(int i = 0; i < macros.length; ++i) {
         bor shadermacro = macros[i];
         if (line.contains(shadermacro.getName())) {
            list.add(shadermacro);
         }
      }

      bor[] ashadermacro = (bor[])((bor[])list.toArray(new bor[list.size()]));
      return ashadermacro;
   }

   private static String loadFile(String filePath, bpa shaderPack, int fileIndex, List<String> listFiles, int includeLevel) throws IOException {
      if (includeLevel >= 10) {
         throw new IOException("#include depth exceeded: " + includeLevel + ", file: " + filePath);
      } else {
         ++includeLevel;
         InputStream inputstream = shaderPack.getResourceAsStream(filePath);
         if (inputstream == null) {
            return null;
         } else {
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream, "ASCII");
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            bufferedreader = resolveIncludes(bufferedreader, filePath, shaderPack, fileIndex, listFiles, includeLevel);
            CharArrayWriter chararraywriter = new CharArrayWriter();

            while(true) {
               String s = bufferedreader.readLine();
               if (s == null) {
                  return chararraywriter.toString();
               }

               chararraywriter.write(s);
               chararraywriter.write("\n");
            }
         }
      }
   }

   public static bpC parseCustomUniforms(Properties props) {
      String s = "uniform";
      String s1 = "variable";
      String s2 = s + ".";
      String s3 = s1 + ".";
      Map<String, blU> map = new HashMap();
      List<bpB> list = new ArrayList();
      Iterator var7 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s4;
            String[] astring;
            do {
               if (!var7.hasNext()) {
                  if (list.size() <= 0) {
                     return null;
                  }

                  bpB[] acustomuniform = (bpB[])((bpB[])list.toArray(new bpB[list.size()]));
                  bpC customuniforms = new bpC(acustomuniform, map);
                  return customuniforms;
               }

               Object s44 = var7.next();
               s4 = (String)s44;
               astring = XH.tokenize(s4, ".");
            } while(astring.length != 3);

            String s5 = astring[0];
            String s6 = astring[1];
            String s7 = astring[2];
            String s8 = props.getProperty(s4).trim();
            if (map.containsKey(s7)) {
               bpx.warning("Expression already defined: " + s7);
            } else if (s5.equals(s) || s5.equals(s1)) {
               bpx.info("Custom " + s5 + ": " + s7);
               bpB customuniform = parseCustomUniform(s5, s7, s6, s8, map);
               if (customuniform != null) {
                  map.put(s7, customuniform.getExpression());
                  if (!s5.equals(s1)) {
                     list.add(customuniform);
                  }
               }
            }
         }
      }
   }

   private static bpB parseCustomUniform(String kind, String name, String type, String src, Map<String, blU> mapExpressions) {
      try {
         bpV uniformtype = bpV.parse(type);
         if (uniformtype == null) {
            bpx.warning("Unknown " + kind + " type: " + uniformtype);
            return null;
         } else {
            bpD shaderexpressionresolver = new bpD(mapExpressions);
            blM expressionparser = new blM(shaderexpressionresolver);
            blU iexpression = expressionparser.parse(src);
            blN expressiontype = iexpression.getExpressionType();
            if (!uniformtype.matchesExpressionType(expressiontype)) {
               bpx.warning("Expression type does not match " + kind + " type, expression: " + expressiontype + ", " + kind + ": " + uniformtype + " " + name);
               return null;
            } else {
               iexpression = makeExpressionCached(iexpression);
               bpB customuniform = new bpB(name, uniformtype, iexpression);
               return customuniform;
            }
         }
      } catch (bmd var11) {
         bmd parseexception = var11;
         bpx.warning(parseexception.getClass().getName() + ": " + parseexception.getMessage());
         return null;
      }
   }

   private static blU makeExpressionCached(blU expr) {
      if (expr instanceof blX) {
         return new blK((blX)expr);
      } else {
         return (blU)(expr instanceof blY ? new blJ((blY)expr) : expr);
      }
   }

   public static void parseAlphaStates(Properties props) {
      Iterator var1 = props.keySet().iterator();

      while(var1.hasNext()) {
         Object ss = var1.next();
         String s = (String)ss;
         String[] astring = XH.tokenize(s, ".");
         if (astring.length == 2) {
            String s1 = astring[0];
            String s2 = astring[1];
            if (s1.equals("alphaTest")) {
               bpg program = bpq.getProgram(s2);
               if (program == null) {
                  bpx.severe("Invalid program name: " + s2);
               } else {
                  String s3 = props.getProperty(s).trim();
                  bnY glalphastate = parseAlphaState(s3);
                  if (glalphastate != null) {
                     program.setAlphaState(glalphastate);
                  }
               }
            }
         }
      }

   }

   private static bnY parseAlphaState(String str) {
      String[] astring = XH.tokenize(str, " ");
      String s2;
      if (astring.length == 1) {
         s2 = astring[0];
         if (s2.equals("off") || s2.equals("false")) {
            return new bnY(false);
         }
      } else if (astring.length == 2) {
         s2 = astring[0];
         String s1 = astring[1];
         Integer integer = (Integer)mapAlphaFuncs.get(s2);
         float f = XH.parseFloat(s1, -1.0F);
         if (integer != null && f >= 0.0F) {
            return new bnY(true, integer, f);
         }
      }

      bpx.severe("Invalid alpha test: " + str);
      return null;
   }

   public static void parseBlendStates(Properties props) {
      Iterator var1 = props.keySet().iterator();

      while(var1.hasNext()) {
         Object ss = var1.next();
         String s = (String)ss;
         String[] astring = XH.tokenize(s, ".");
         if (astring.length == 2) {
            String s1 = astring[0];
            String s2 = astring[1];
            if (s1.equals("blend")) {
               bpg program = bpq.getProgram(s2);
               if (program == null) {
                  bpx.severe("Invalid program name: " + s2);
               } else {
                  String s3 = props.getProperty(s).trim();
                  bnZ glblendstate = parseBlendState(s3);
                  if (glblendstate != null) {
                     program.setBlendState(glblendstate);
                  }
               }
            }
         }
      }

   }

   private static bnZ parseBlendState(String str) {
      String[] astring = XH.tokenize(str, " ");
      String s4;
      if (astring.length == 1) {
         s4 = astring[0];
         if (s4.equals("off") || s4.equals("false")) {
            return new bnZ(false);
         }
      } else if (astring.length == 2 || astring.length == 4) {
         s4 = astring[0];
         String s1 = astring[1];
         String s2 = s4;
         String s3 = s1;
         if (astring.length == 4) {
            s2 = astring[2];
            s3 = astring[3];
         }

         Integer integer = (Integer)mapBlendFactors.get(s4);
         Integer integer1 = (Integer)mapBlendFactors.get(s1);
         Integer integer2 = (Integer)mapBlendFactors.get(s2);
         Integer integer3 = (Integer)mapBlendFactors.get(s3);
         if (integer != null && integer1 != null && integer2 != null && integer3 != null) {
            return new bnZ(true, integer, integer1, integer2, integer3);
         }
      }

      bpx.severe("Invalid blend mode: " + str);
      return null;
   }

   public static void parseRenderScales(Properties props) {
      Iterator var1 = props.keySet().iterator();

      while(var1.hasNext()) {
         Object ss = var1.next();
         String s = (String)ss;
         String[] astring = XH.tokenize(s, ".");
         if (astring.length == 2) {
            String s1 = astring[0];
            String s2 = astring[1];
            if (s1.equals("scale")) {
               bpg program = bpq.getProgram(s2);
               if (program == null) {
                  bpx.severe("Invalid program name: " + s2);
               } else {
                  String s3 = props.getProperty(s).trim();
                  boo renderscale = parseRenderScale(s3);
                  if (renderscale != null) {
                     program.setRenderScale(renderscale);
                  }
               }
            }
         }
      }

   }

   private static boo parseRenderScale(String str) {
      String[] astring = XH.tokenize(str, " ");
      float f = XH.parseFloat(astring[0], -1.0F);
      float f1 = 0.0F;
      float f2 = 0.0F;
      if (astring.length > 1) {
         if (astring.length != 3) {
            bpx.severe("Invalid render scale: " + str);
            return null;
         }

         f1 = XH.parseFloat(astring[1], -1.0F);
         f2 = XH.parseFloat(astring[2], -1.0F);
      }

      if (XH.between(f, 0.0F, 1.0F) && XH.between(f1, 0.0F, 1.0F) && XH.between(f2, 0.0F, 1.0F)) {
         return new boo(f, f1, f2);
      } else {
         bpx.severe("Invalid render scale: " + str);
         return null;
      }
   }

   public static void parseBuffersFlip(Properties props) {
      Iterator var1 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            String s2;
            String s3;
            do {
               String[] astring;
               do {
                  if (!var1.hasNext()) {
                     return;
                  }

                  Object ss = var1.next();
                  s = (String)ss;
                  astring = XH.tokenize(s, ".");
               } while(astring.length != 3);

               s1 = astring[0];
               s2 = astring[1];
               s3 = astring[2];
            } while(!s1.equals("flip"));

            bpg program = bpq.getProgram(s2);
            if (program == null) {
               bpx.severe("Invalid program name: " + s2);
            } else {
               Boolean[] aboolean = program.getBuffersFlip();
               int i = bpq.getBufferIndexFromString(s3);
               if (i >= 0 && i < aboolean.length) {
                  String s4 = props.getProperty(s).trim();
                  Boolean obool = XH.parseBoolean(s4, (Boolean)null);
                  if (obool == null) {
                     bpx.severe("Invalid boolean value: " + s4);
                  } else {
                     aboolean[i] = obool;
                  }
               } else {
                  bpx.severe("Invalid buffer name: " + s3);
               }
            }
         }
      }
   }

   private static Map<String, Integer> makeMapAlphaFuncs() {
      Map<String, Integer> map = new HashMap();
      map.put("NEVER", new Integer(512));
      map.put("LESS", new Integer(513));
      map.put("EQUAL", new Integer(514));
      map.put("LEQUAL", new Integer(515));
      map.put("GREATER", new Integer(516));
      map.put("NOTEQUAL", new Integer(517));
      map.put("GEQUAL", new Integer(518));
      map.put("ALWAYS", new Integer(519));
      return Collections.unmodifiableMap(map);
   }

   private static Map<String, Integer> makeMapBlendFactors() {
      Map<String, Integer> map = new HashMap();
      map.put("ZERO", new Integer(0));
      map.put("ONE", new Integer(1));
      map.put("SRC_COLOR", new Integer(768));
      map.put("ONE_MINUS_SRC_COLOR", new Integer(769));
      map.put("DST_COLOR", new Integer(774));
      map.put("ONE_MINUS_DST_COLOR", new Integer(775));
      map.put("SRC_ALPHA", new Integer(770));
      map.put("ONE_MINUS_SRC_ALPHA", new Integer(771));
      map.put("DST_ALPHA", new Integer(772));
      map.put("ONE_MINUS_DST_ALPHA", new Integer(773));
      map.put("CONSTANT_COLOR", new Integer(32769));
      map.put("ONE_MINUS_CONSTANT_COLOR", new Integer(32770));
      map.put("CONSTANT_ALPHA", new Integer(32771));
      map.put("ONE_MINUS_CONSTANT_ALPHA", new Integer(32772));
      map.put("SRC_ALPHA_SATURATE", new Integer(776));
      return Collections.unmodifiableMap(map);
   }
}
