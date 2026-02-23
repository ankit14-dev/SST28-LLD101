import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult doExport(ExportRequest req) {
        String body = req.getBody();
        if (body.length() > 20) {
            return ExportResult.error("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.getTitle() + "):" + req.getBody();
        return ExportResult.ok("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
        // return new ExportResult("application/pdf",
        // fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
