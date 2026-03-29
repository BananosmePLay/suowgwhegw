package net.minecraft.util;

import neo.Er;

public class ReportedException extends RuntimeException {
   private final Er crashReport;

   public ReportedException(Er report) {
      this.crashReport = report;
   }

   public Er getCrashReport() {
      return this.crashReport;
   }

   public Throwable getCause() {
      return this.crashReport.getCrashCause();
   }

   public String getMessage() {
      return this.crashReport.getDescription();
   }
}
