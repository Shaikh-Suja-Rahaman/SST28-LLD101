public class ClubConsole {
    private final BudgetLedger ledger;
    private final MinutesBook minutes;
    private final EventPlanner events;

    public ClubConsole(BudgetLedger ledger, MinutesBook minutes, EventPlanner events) {
        this.ledger = ledger; this.minutes = minutes; this.events = events;
    }

    public void run() {
        // Use only relevant interfaces for each role
        FinanceTool treasurer = new TreasurerTool(ledger);      // Only finance methods
        MinutesTool secretary = new SecretaryTool(minutes);     // Only minutes methods
        EventTool lead = new EventLeadTool(events);             // Only event methods

        treasurer.addIncome(5000, "sponsor");
        secretary.addMinutes("Meeting at 5pm");
        lead.createEvent("HackNight", 2000);

        System.out.println("Summary: ledgerBalance=" + ledger.balanceInt() + ", minutes=" + minutes.count() + ", events=" + lead.getEventsCount());
    }
}
// Now ClubConsole depends only on minimal interfaces per role — ISP respected
