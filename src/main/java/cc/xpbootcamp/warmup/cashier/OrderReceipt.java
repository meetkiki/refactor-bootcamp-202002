package cc.xpbootcamp.warmup.cashier;


import static cc.xpbootcamp.warmup.cashier.Character.INVOICE;
import static cc.xpbootcamp.warmup.cashier.Character.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.Character.SALES_TAX;
import static cc.xpbootcamp.warmup.cashier.Character.TABS;
import static cc.xpbootcamp.warmup.cashier.Character.TOTAL_AMOUNT;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return INVOICE +
                // customer information
                order.getCustomerName() +
                order.getCustomerAddress() +
                // prints item
                order.generateItemDetail() +
                // prints the state tax
                SALES_TAX + order.getTotalSalesTax() +
                // print total amounts
                TOTAL_AMOUNT + order.getTotalAmount();
    }
}