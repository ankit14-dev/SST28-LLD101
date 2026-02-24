public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        if (n == null || n.body == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
        validate(n);
        deliver(n);
    }

    protected void validate(Notification notification) {
        // default: no validation
    }

    protected abstract void deliver(Notification notification);
}
