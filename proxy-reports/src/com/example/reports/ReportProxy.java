package com.example.reports;

import java.nio.file.AccessDeniedException;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    private RealReport real = null;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user){
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED -> user=" + user.getName()
                    + " role=" + user.getRole()
                    + " required=" + classification);
            return;
        }

        if (real == null) {
            // lazy construction, althought it is violating dependency injection that is fine
            real = new RealReport(reportId, title, classification);
        }

        real.display(user);
    }

}
