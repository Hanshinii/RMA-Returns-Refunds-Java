import java.util.ArrayList;

public class Order {
    private String orderId;
    private String buyer;
    private ArrayList<String> items;
    private double totalAmount;
    private String purchaseDate;
    private static int totalOrders = 0;
    public Order(String orderId, String buyer, double totalAmount, String purchaseDate) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.items = new ArrayList<String>();
        totalOrders++;
    }
    public void addItem(String item) {
        items.add(item);
    }

    public String getOrderId() { return orderId; }
    public String getBuyer() { return buyer; }
    public ArrayList<String> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getPurchaseDate() { return purchaseDate; }
    public void setBuyer(String buyer) { this.buyer = buyer; }
    public static int getTotalOrders() { return totalOrders; }

    public void displayOrder() {
        System.out.println("Order " + orderId + " | Buyer: " + buyer + " | Items: " + items
                + " | Total: Rs." + totalAmount + " | Date: " + purchaseDate);
    }
}
