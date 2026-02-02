package sa.edu.kau.fcit.cpit252;

import sa.edu.kau.fcit.cpit252.orders.Order;
import sa.edu.kau.fcit.cpit252.orders.Shipment;

public class App {
    public static void main(String[] args) {
        Order o1 = new Order();
        Shipment sh1 = new Shipment("Ahmed",
                "248 NE. Pleasant St. Niceville, FL 32578",
                "123-477-0001");
    }
}