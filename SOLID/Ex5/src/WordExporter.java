import java.nio.charset.StandardCharsets;

public class WordExporter extends Exporter {
    @Override
    public ExportResult doExport(ExportRequest req) {
        String body = req.getBody() == null ? "" : req.getBody();
        String fakeWord = "WORD(" + req.getTitle() + "):\n" + body;
        return ExportResult.ok("application/msword", fakeWord.getBytes(StandardCharsets.UTF_8));
    }
    
}
