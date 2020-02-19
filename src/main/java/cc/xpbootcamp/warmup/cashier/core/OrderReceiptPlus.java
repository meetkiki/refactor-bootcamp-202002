package cc.xpbootcamp.warmup.cashier.core;


import cc.xpbootcamp.warmup.cashier.model.Order;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.INVOICE_CN;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LONG_LINE;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceiptPlus {

    private Order order;

    public OrderReceiptPlus(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return INVOICE_CN + LINE_BREAK + LINE_BREAK +
                // date
                order.generateDateString() + LINE_BREAK + LINE_BREAK +
                // prints item
                order.generateItemDetailPlus() +
                // print line
                LONG_LINE +
                // print total
                order.generateTotalDetail();
    }
}