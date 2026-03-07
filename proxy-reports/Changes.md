# Changes (Proxy Reports refactor)

Summary of edits made for the Proxy refactor:

- Introduced/used the Report abstraction (interface) as the client-facing type.
- Implemented RealReport:
  - Moved expensive loadFromDisk logic from ReportFile into RealReport.display().
  - RealReport.loadFromDisk() logs disk activity, simulates delay, and returns content.
- Implemented ReportProxy:
  - Holds metadata: reportId, title, classification.
  - Holds AccessControl instance.
  - Keeps a nullable RealReport field (cache).
  - display(User):
    - Calls accessControl.canAccess(user, classification) and logs ACCESS DENIED when false.
    - Lazily constructs RealReport only when access is granted (real == null).
    - Delegates to real.display(user) after construction.
- Updated ReportViewer:
  - Now depends on the Report interface and calls report.display(user).
- Updated App and QuickCheck:
  - Construct report objects as ReportProxy instances (public/faculty/admin reports).
  - Use ReportViewer with Report proxies.
- ReportFile:
  - Left as legacy/placeholder (expensive load moved to RealReport) — consider removing to avoid confusion.
- Minor: removed unused imports/warnings where applicable.

How to verify:
- Compile & run from project src:
  cd "/Users/suja/Suja's Folder/LLD 101/SST28-LLD101/proxy-reports/src"
  javac com/example/reports/*.java
  java com.example.reports.QuickCheck
  java com.example.reports.App

Expected behavior:
- Unauthorized access prints ACCESS DENIED and does NOT show [disk] load.
- First allowed view prints a single [disk] load.
- Repeated views via the same ReportProxy do not trigger additional [disk] loads.
// ...existing code...