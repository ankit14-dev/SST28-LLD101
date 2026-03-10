public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        PlagiarismCheckerInterface plagiarismChecker = new PlagiarismChecker();
        CodeGraderInterface grader = new CodeGrader();
        ReportWriterInterface writer = new ReportWriter();

        EvaluationPipeline pipeline = new EvaluationPipeline(plagiarismChecker, grader, writer);

        pipeline.evaluate(sub);
    }
}
