import java.util.ArrayList;
public class RMAService {
    private ArrayList<Order> orders;
    private ArrayList<ReturnRequest> returns;
    private ArrayList<Refund> refunds;  
    private static int rmaCounter = 1000;
    private static int refundCounter = 5000;

    public RMAService() {
        orders = new ArrayList<Order>();
        returns = new ArrayList<ReturnRequest>();
        refunds = new ArrayList<Refund>();
    }

    public void addOrder(Order o) { orders.add(o); }

    public Order findOrder(String orderId) {
        for (Order o : orders) {
            if (o.getOrderId().equals(orderId)) return o;
        }
        return null;
    }

    public ReturnRequest findRMA(String rmaId) {
        for (ReturnRequest r : returns) {
            if (r.getRmaId().equals(rmaId)) return r;
        }
        return null;
    }
    public ReturnRequest createRMA(String orderId, String item, String reason) {
        Order o = findOrder(orderId);
        if (o == null || !o.getItems().contains(item)) {
            System.out.println("  Order/item not found: " + orderId + " / " + item);
            return null;
        }
        rmaCounter++;
        ReturnRequest r = new ReturnRequest("RMA" + rmaCounter, orderId, reason);
        r.addItem(item);
        returns.add(r);
        return r;
    }
    public ReturnRequest createRMA(String orderId, String item, String reason, boolean photos) {
        ReturnRequest r = createRMA(orderId, item, reason);
        if (r != null) r.setHasPhotos(photos);
        return r;
    }
    public ReturnRequest createRMA(String orderId, ArrayList<String> items, String reason) {
        Order o = findOrder(orderId);
        if (o == null) {
            System.out.println("  Order not found: " + orderId);
            return null;
        }
        rmaCounter++;
        ReturnRequest r = new ReturnRequest("RMA" + rmaCounter, orderId, reason);
        for (String it : items) r.addItem(it);
        returns.add(r);
        return r;
    }

    public void inspectItem(ReturnRequest r, String condition) {
        r.setCondition(condition);
        System.out.println("  " + r.getRmaId() + " inspected. Condition: " + condition);
    }

    public void approve(ReturnRequest r) {
        if (r.approve()) System.out.println("  " + r.getRmaId() + " APPROVED.");
    }

    public void deny(ReturnRequest r) {
        if (r.deny()) System.out.println("  " + r.getRmaId() + " DENIED.");
    }
    public void processRefund(ReturnRequest r, Refund refund) {
        if (!r.getStatus().equals("APPROVED")) {
            System.out.println("  Cannot refund " + r.getRmaId() + " - status is " + r.getStatus());
            return;
        }
        double paid = refund.processRefund();   // calls the overridden version
        refunds.add(refund);
        r.markRefunded();
        System.out.println("  Refund " + refund.getRefundId() + " done. Paid: Rs." + paid);
    }

    public String newRefundId() {
        refundCounter++;
        return "RF" + refundCounter;
    }

    public void printDailySummary() {
        System.out.println("\n===== DAILY RMA SUMMARY =====");
        int requested = 0, approved = 0, denied = 0, refunded = 0;
        for (ReturnRequest r : returns) {
            r.displayRequest();
            String s = r.getStatus();
            if (s.equals("REQUESTED")) requested++;
            else if (s.equals("APPROVED")) approved++;
            else if (s.equals("DENIED")) denied++;
            else if (s.equals("REFUNDED")) refunded++;
        }
        System.out.println("Total RMAs: " + returns.size() + " | Requested: " + requested
                + " | Approved: " + approved + " | Denied: " + denied + " | Refunded: " + refunded);

        System.out.println("\n===== DAILY REFUND SUMMARY =====");
        double total = 0;
        for (Refund f : refunds) {
            f.displayRefund();
            total += f.getAmount();
        }
        System.out.println("Total refunds: " + refunds.size() + " | Total amount: Rs." + total);
    }
}
