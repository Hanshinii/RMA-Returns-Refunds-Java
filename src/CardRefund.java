public class CardRefund extends Refund {
    private String cardLast4;
    private String bankName;
    private double feePercent;    
    private int daysToCredit;

    public CardRefund(String refundId, String rmaId, double amount, String processedDate,
                      String cardLast4, String bankName) {
        super(refundId, rmaId, "CARD", amount, processedDate);
        this.cardLast4 = cardLast4;
        this.bankName = bankName;
        this.feePercent = 2.0;
        this.daysToCredit = 5;
    }

    public String getCardLast4() { return cardLast4; }
    public String getBankName() { return bankName; }
    public double getFeePercent() { return feePercent; }
    public int getDaysToCredit() { return daysToCredit; }
    public void setFeePercent(double feePercent) { this.feePercent = feePercent; }

    @Override
    public double processRefund() {
        double fee = amount * feePercent / 100;
        double finalAmount = amount - fee;
        System.out.println("  Card refund to " + bankName + " card ****" + cardLast4
                + ": Rs." + finalAmount + " (fee Rs." + fee + "), credited in "
                + daysToCredit + " days.");
        return finalAmount;
    }
}
