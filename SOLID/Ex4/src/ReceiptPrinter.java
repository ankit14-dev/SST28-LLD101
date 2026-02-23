public class ReceiptPrinter {
    public static void print(BookingRequest req, FeeBreakdown fees) {
        System.out.println("Room: " + LegacyRoomTypes.nameOf(req.roomType) + " | AddOns: " + req.addOns);
        System.out.println("Monthly: " + fees.getMonthly());
        System.out.println("Deposit: " + fees.getDeposit());
        System.out.println("TOTAL DUE NOW: " + fees.totalDueNow());
    }
}
