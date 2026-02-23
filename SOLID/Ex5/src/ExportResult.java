public class ExportResult {
    public final String contentType;
    private final byte[] data;
    private final boolean success;
    private final String errorMessage;

    public ExportResult(boolean success, String contentType, byte[] data, String errorMessage) {
        this.success = success;
        this.contentType = contentType;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static ExportResult ok(String contentType, byte[] data) {
        return new ExportResult(true, contentType, data, null);
    }

    public static ExportResult error(String message) {
        return new ExportResult(false, null, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public byte[] getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
