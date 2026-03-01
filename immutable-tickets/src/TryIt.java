import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create ticket
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created : " + t);

        // 2. Assign — returns NEW ticket, original unchanged
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        System.out.println("\nAssigned : " + assigned);
        System.out.println("Original unchanged: " + t.getAssigneeEmail()); // still null

        // 3. Escalate — returns NEW ticket
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nEscalated: " + escalated);

        // 4. Prove tags are safe — external mutation has no effect
        List<String> tags = t.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE"); // should throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTag mutation blocked! Immutability works ✅");
        }
    }
}
