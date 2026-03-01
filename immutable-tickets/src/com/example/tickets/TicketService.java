package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {



    public IncidentTicket createTicket(String id, String reporterEmail, String title) {

        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .tags(List.of("NEW"))
                .build();                // validation happens here, object created once
    }

// Right now escalateToCritical and assign manually copy every single field —
//  messy and error prone. If you add a new field tomorrow, you must update every method.




    // public IncidentTicket escalateToCritical(IncidentTicket t) {
    //     //i will have to create a new object if something changes, i can't reuse the old one since its immutable
    //     List<String> newTags = new ArrayList<>(t.getTags());
    //     newTags.add("ESCALATED");
    //             return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
    //             .priority("CRITICAL")
    //             .tags(newTags)
    //             .assigneeEmail(t.getAssigneeEmail())
    //             .customerVisible(t.isCustomerVisible())
    //             .slaMinutes(t.getSlaMinutes())
    //             .source(t.getSource())
    //             .build();

    // }

        public IncidentTicket escalateToCritical(IncidentTicket t) {
        List<String> newTags = new ArrayList<>(t.getTags());
        newTags.add("ESCALATED");
        return t.toBuilder()
                .priority("CRITICAL")
                .tags(newTags)
                .build();
    }




    // public void assign(IncidentTicket t, String assigneeEmail) {
    // public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
    //     if (assigneeEmail != null && !assigneeEmail.contains("@")) {
    //         throw new IllegalArgumentException("assigneeEmail invalid");
    //     }
    //     // can't mutate — return new ticket with assignee set
    //     return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
    //             .priority(t.getPriority())
    //             .tags(t.getTags())
    //             .customerVisible(t.isCustomerVisible())
    //             .slaMinutes(t.getSlaMinutes())
    //             .source(t.getSource())
    //             .assigneeEmail(assigneeEmail)
    //             .build();
    // }

        public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        if (assigneeEmail != null && !assigneeEmail.contains("@")) {
            throw new IllegalArgumentException("assigneeEmail invalid");
        }
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
