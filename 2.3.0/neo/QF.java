package neo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;

public class QF {
   public QF() {
   }

   public static QQ readCompressed(InputStream is) throws IOException {
      DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(is)));

      QQ nbttagcompound;
      try {
         nbttagcompound = read(datainputstream, QL.INFINITE);
      } finally {
         datainputstream.close();
      }

      return nbttagcompound;
   }

   public static void writeCompressed(QQ compound, OutputStream outputStream) throws IOException {
      DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputStream)));

      try {
         write(compound, (DataOutput)dataoutputstream);
      } finally {
         dataoutputstream.close();
      }

   }

   public static void safeWrite(QQ compound, File fileIn) throws IOException {
      File file1 = new File(fileIn.getAbsolutePath() + "_tmp");
      if (file1.exists()) {
         file1.delete();
      }

      write(compound, file1);
      if (fileIn.exists()) {
         fileIn.delete();
      }

      if (fileIn.exists()) {
         throw new IOException("Failed to delete " + fileIn);
      } else {
         file1.renameTo(fileIn);
      }
   }

   public static void write(QQ compound, File fileIn) throws IOException {
      DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(fileIn));

      try {
         write(compound, (DataOutput)dataoutputstream);
      } finally {
         dataoutputstream.close();
      }

   }

   @Nullable
   public static QQ read(File fileIn) throws IOException {
      if (!fileIn.exists()) {
         return null;
      } else {
         DataInputStream datainputstream = new DataInputStream(new FileInputStream(fileIn));

         QQ nbttagcompound;
         try {
            nbttagcompound = read(datainputstream, QL.INFINITE);
         } finally {
            datainputstream.close();
         }

         return nbttagcompound;
      }
   }

   public static QQ read(DataInputStream inputStream) throws IOException {
      return read(inputStream, QL.INFINITE);
   }

   public static QQ read(DataInput input, QL accounter) throws IOException {
      QH nbtbase = read(input, 0, accounter);
      if (nbtbase instanceof QQ) {
         return (QQ)nbtbase;
      } else {
         throw new IOException("Root tag must be a named compound tag");
      }
   }

   public static void write(QQ compound, DataOutput output) throws IOException {
      writeTag(compound, output);
   }

   private static void writeTag(QH tag, DataOutput output) throws IOException {
      output.writeByte(tag.getId());
      if (tag.getId() != 0) {
         output.writeUTF("");
         tag.write(output);
      }

   }

   private static QH read(DataInput input, int depth, QL accounter) throws IOException {
      byte b0 = input.readByte();
      if (b0 == 0) {
         return new QS();
      } else {
         input.readUTF();
         QH nbtbase = QH.create(b0);

         try {
            nbtbase.read(input, depth, accounter);
            return nbtbase;
         } catch (IOException var8) {
            IOException ioexception = var8;
            Er crashreport = Er.makeCrashReport(ioexception, "Loading NBT data");
            Ey crashreportcategory = crashreport.makeCategory("NBT Tag");
            crashreportcategory.addCrashSection("Tag type", b0);
            throw new ReportedException(crashreport);
         }
      }
   }
}
