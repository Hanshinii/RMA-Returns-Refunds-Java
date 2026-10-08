import java.util.ArrayList;

public class RMAppMain {
    public static void main(String[] args) {
        RMAService service = new RMAService();
        Order o1 = new Order("ORD1", "Arun", 55000, "2026-10-01");
        o1.addItem("Laptop");
        o1.addItem("Mouse");
        Order o2 = new Order("ORD2", "Priya", 12000, "2026-10-03");
        o2.addItem("Headphones");
        Order o3 = new Order("ORD3", "Kumar", 3000, "2026-10-05");
        o3.addItem("Keyboard");
        service.addOrder(o1);
        service.addOrder(o2);
        service.addOrder(o3);
        System.out.println("===== ORDERS =====");
        o1.displayOrder();
        o2.displayOrder();
        o3.displayOrder();
        System.out.println("Total orders loaded: " + Order.getTotalOrders());
        System.out.println("\n===== CREATING RMAs =====");
        ReturnRequest r1 = service.createRMA("ORD1", "Laptop", "Screen flickering");           
        ReturnRequest r2 = service.createRMA("ORD2", "Headphones", "Left side not working", true); 
        ArrayList<String> multi = new ArrayList<String>();
        multi.add("Laptop");
        multi.add("Mouse");
        ReturnRequest r3 = service.createRMA("ORD1", multi, "Wrong order");                    
        ReturnRequest r4 = service.createRMA("ORD3", "Keyboard", "Changed my mind");

        r1.displayRequest();
        r2.displayRequest();
        r3.displayRequest();
        r4.displayRequest();

        
        System.out.println("\n===== INSPECT & DECIDE =====");
        service.inspectItem(r1, "DEFECTIVE");
        service.approve(r1);

        service.inspectItem(r2, "DEFECTIVE");
        service.approve(r2);

        service.inspectItem(r4, "USED - DAMAGED");
        service.deny(r4);
        System.out.println("\n--- Testing invalid transition ---");
        service.approve(r4);
        System.out.println("\n===== PROCESSING REFUNDS =====");
        Refund f1 = new CardRefund(service.newRefundId(), r1.getRmaId(), 55000, "2026-10-08", "1234", "HDFC");
        Refund f2 = new WalletRefund(service.newRefundId(), r2.getRmaId(), 12000, "2026-10-08", "PayWallet", "W-9876");
        service.processRefund(r1, f1);
        service.processRefund(r2, f2);
        service.processRefund(r4, new CardRefund(service.newRefundId(), r4.getRmaId(), 3000,
                "2026-10-08", "1111", "SBI"));
        service.printDailySummary();
    }
}
