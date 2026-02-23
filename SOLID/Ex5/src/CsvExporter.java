import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult doExport(ExportRequest req) {
        // LSP issue: changes meaning by lossy conversion
        String body = req.getBody() == null ? "" : req.getBody().replace("\n", " ").replace(",", " ");

        String csv = "title,body\n" + req.getTitle() + "," + body + "\n";
        return ExportResult.ok("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
