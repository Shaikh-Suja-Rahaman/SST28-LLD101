package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.tags = new ArrayList<>(builder.tags);
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
        this.assigneeEmail = builder.assigneeEmail;
    }

    // Getters only — no setters
    public String getId()              { return id; }
    public String getReporterEmail()   { return reporterEmail; }
    public String getTitle()           { return title; }
    public String getDescription()     { return description; }
    public String getPriority()        { return priority; }
    public List<String> getTags()      { return Collections.unmodifiableList(tags); } // fixed leak
    public String getAssigneeEmail()   { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes()     { return slaMinutes; }
    public String getSource()          { return source; }


        public Builder toBuilder() {
            return new Builder(id, reporterEmail, title)
                    .description(description)
                    .priority(priority)
                    .tags(tags)
                    .assigneeEmail(assigneeEmail)
                    .customerVisible(customerVisible)
                    .slaMinutes(slaMinutes)
                    .source(source);
        }

    public static class Builder {

        private final String id;
        private final String reporterEmail;
        private final String title;

        private String description    = null;
        private String priority       = "MEDIUM";
        private List<String> tags     = new ArrayList<>();
        private String assigneeEmail  = null;
        private boolean customerVisible = false;
        private Integer slaMinutes    = null;
        private String source         = null;

        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder priority(String p) {
            this.priority = p;
            return this;
        }

        public Builder tags(List<String> t) {
            this.tags = new ArrayList<>(t); // defensive copy to make sure that i dont change the value of the arrayList even by mistake through a leak
            return this;
        }

        public Builder assigneeEmail(String email) {
            this.assigneeEmail = email;
            return this;
        }

        public Builder customerVisible(boolean visible) {
            this.customerVisible = visible;
            return this;
        }

        public Builder slaMinutes(Integer sla) {
            this.slaMinutes = sla;
            return this;
        }

        public Builder source(String src) {
            this.source = src;
            return this;
        }

        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            if (assigneeEmail != null)
                Validation.requireEmail(assigneeEmail, "assigneeEmail");

            return new IncidentTicket(this);
        }

    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
