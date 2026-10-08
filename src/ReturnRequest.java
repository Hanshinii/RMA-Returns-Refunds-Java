import java.util.ArrayList;
public class ReturnRequest {
    private String rmaId;
    private String orderId;
    private String reason;
    private String condition;          
    private String status;       
    private ArrayList<String> itemsToReturn;
    private boolean hasPhotos;
    public ReturnRequest(String rmaId, String orderId, String reason) {
        this.rmaId = rmaId;
        this.orderId = orderId;
        this.reason = reason;
        this.condition = "NOT INSPECTED";
        this.status = "REQUESTED";
        this.itemsToReturn = new ArrayList<String>();
        this.hasPhotos = false;
    }
    public String getRmaId() { return rmaId; }
    public String getOrderId() { return orderId; }
    public String getReason() { return reason; }
    public String getCondition() { return condition; }
    public String getStatus() { return status; }
    public boolean isHasPhotos() { return hasPhotos; }
    public ArrayList<String> getItemsToReturn() { return itemsToReturn; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setHasPhotos(boolean hasPhotos) { this.hasPhotos = hasPhotos; }
    public void addItem(String item) { itemsToReturn.add(item); }
    public boolean approve() {
        if (status.equals("REQUESTED")) {
            status = "APPROVED";
            return true;
        }
        System.out.println("  Cannot approve. Current status: " + status);
        return false;
    }

    public boolean deny() {
        if (status.equals("REQUESTED")) {
            status = "DENIED";
            return true;
        }
        System.out.println("  Cannot deny. Current status: " + status);
        return false;
    }

    public boolean markRefunded() {
        if (status.equals("APPROVED")) {
            status = "REFUNDED";
            return true;
        }
        System.out.println("  Cannot refund. Current status: " + status);
        return false;
    }

    public void displayRequest() {
        System.out.println("RMA " + rmaId + " | Order: " + orderId + " | Reason: " + reason
                + " | Condition: " + condition + " | Status: " + status
                + " | Photos: " + hasPhotos + " | Items: " + itemsToReturn);
    }
}
