public class WalletRefund extends Refund {
    private String walletName;
    private String walletId;
    private double bonusPercent; 
    private boolean instant;

    public WalletRefund(String refundId, String rmaId, double amount, String processedDate,
                        String walletName, String walletId) {
        super(refundId, rmaId, "WALLET", amount, processedDate);
        this.walletName = walletName;
        this.walletId = walletId;
        this.bonusPercent = 5.0;
        this.instant = true;
    }

    public String getWalletName() { return walletName; }
    public String getWalletId() { return walletId; }
    public double getBonusPercent() { return bonusPercent; }
    public boolean isInstant() { return instant; }
    @Override
    public double processRefund() {
        double bonus = amount * bonusPercent / 100;
        double finalAmount = amount + bonus;
        System.out.println("  Wallet refund to " + walletName + " (" + walletId
                + "): Rs." + finalAmount + " (bonus Rs." + bonus + "), instant credit.");
        return finalAmount;
    }
}
