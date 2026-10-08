public class Refund {
    protected String refundId;
    protected String rmaId;
    protected String method;
    protected double amount;
    protected String processedDate;
    public Refund(String refundId, String rmaId, String method, double amount, String processedDate) {
        this.refundId = refundId;
        this.rmaId = rmaId;
        this.method = method;
        this.amount = amount;
        this.processedDate = processedDate;
    }

    public String getRefundId() { return refundId; }
    public String getRmaId() { return rmaId; }
    public String getMethod() { return method; }
    public double getAmount() { return amount; }
    public String getProcessedDate() { return processedDate; }
    public void setAmount(double amount) { this.amount = amount; }
    public double processRefund() {
        System.out.println("  Generic refund of Rs." + amount + " processed.");
        return amount;
    }

    public void displayRefund() {
        System.out.println("Refund " + refundId + " | RMA: " + rmaId + " | Method: " + method
                + " | Amount: Rs." + amount + " | Date: " + processedDate);
    }
}
