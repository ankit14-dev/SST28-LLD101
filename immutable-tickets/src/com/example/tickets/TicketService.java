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
        List<String> tags = new ArrayList<>();
        tags.add("NEW");
        IncidentTicket t = new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .setSource("CLI")
                .setCustomerVisible(false)
                .tags(tags)
                .build();

        return t;
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        List<String> tags = new ArrayList<>(t.getTags());
        tags.add("ESCALATED");

        return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
                .description(t.getDescription())
                .priority("CRITICAL")
                .tags(tags)
                .assigneeEmail(t.getAssigneeEmail())
                .setCustomerVisible(t.isCustomerVisible())
                .setSlaMinutes(t.getSlaMinutes())
                .setSource(t.getSource())
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        if (assigneeEmail != null && !assigneeEmail.contains("@")) {
            throw new IllegalArgumentException("assigneeEmail invalid");
        }

        return new IncidentTicket.Builder(t.getId(), t.getReporterEmail(), t.getTitle())
                .description(t.getDescription())
                .priority(t.getPriority())
                .tags(t.getTags())
                .assigneeEmail(assigneeEmail)
                .setCustomerVisible(t.isCustomerVisible())
                .setSlaMinutes(t.getSlaMinutes())
                .setSource(t.getSource())
                .build();
    }
}
