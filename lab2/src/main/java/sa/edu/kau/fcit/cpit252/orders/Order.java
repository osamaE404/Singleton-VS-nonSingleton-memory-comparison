package sa.edu.kau.fcit.cpit252.orders;

import sa.edu.kau.fcit.cpit252.logging.Logger;

import java.util.Random;
import java.time.LocalDate;

public class Order {
    private int orderNumber;
    private LocalDate orderDate;
    private Logger log;
    private int getRandomNumber(){
        Random ran = new Random();
        return ran.nextInt(Integer.MAX_VALUE);
    }
    public Order(){
        this(Logger.getLoggerInstance());
    }
    public Order(Logger logger){
        this.log = logger;

        this.orderNumber = getRandomNumber();
        this.orderDate = LocalDate.now();
        log.info("A new order was created");
        log.info(this.toString());
    }
    public String toString(){
        return "Order info:\nOrder number: " + this.orderNumber +
                "\nDate" + this.orderDate;
    }
}