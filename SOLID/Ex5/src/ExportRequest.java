public class ExportRequest {
    private final String title;
    private final String body;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public ExportRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
