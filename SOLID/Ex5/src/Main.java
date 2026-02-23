public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());

        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();
        Exporter word = new WordExporter();

        printResult("PDF", pdf.export(req));
        printResult("CSV", csv.export(req));
        printResult("JSON", json.export(req));
        printResult("WORD", word.export(req));
    }

    private static void printResult(String type, ExportResult result) {

        if (result.isSuccess()) {
            System.out.println(type + ": OK bytes=" + result.getData().length);
        } else {
            System.out.println(type + ": ERROR: " + result.getErrorMessage());
        }
    }

    // private static String safe(Exporter e, ExportRequest r) {

    // try {
    // ExportResult out = e.export(r);
    // return "OK bytes=" + out.data.length;
    // } catch (RuntimeException ex) {
    // return "ERROR: " + ex.getMessage();
    // }
    // }
}
