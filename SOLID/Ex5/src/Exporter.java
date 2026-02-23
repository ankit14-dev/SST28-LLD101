public abstract class Exporter {
    /**
     * Base Contract:
     * 1. request must not be null
     * 2. request.getContent() must not be null
     * 3. Never throw runtime exceptions for valid requests
     * 4. Always return non-null ExportResult
     */
    public final ExportResult export(ExportRequest request) {
        if (request == null || request.getBody() == null) {
            return ExportResult.error("Invalid request");
        }

        try {
            return doExport(request);
        } catch (Exception e) {
            return ExportResult.error(e.getMessage());
        }
    }

    protected abstract ExportResult doExport(ExportRequest request);
}
