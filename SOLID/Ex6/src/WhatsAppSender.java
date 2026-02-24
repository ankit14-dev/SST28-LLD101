public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validate(Notification n) {
        if (!n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
    }

    @Override
    public void deliver(Notification n) {
        // LSP violation: tightens precondition

        System.out.println("WA -> to=" + n.phone + " body=" + n.body);

        audit.add("wa sent");
    }
}
