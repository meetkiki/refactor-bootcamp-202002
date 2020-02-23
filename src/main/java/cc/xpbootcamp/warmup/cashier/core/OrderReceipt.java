package cc.xpbootcamp.warmup.cashier.core;


import cc.xpbootcamp.warmup.cashier.model.ProductItem;
import cc.xpbootcamp.warmup.cashier.model.Order;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.INVOICE;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.SALES_TAX;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.TABS;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.TOTAL_AMOUNT;

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
        return INVOICE + LINE_BREAK +
                // customer information
                order.getCustomerName() +
                order.getCustomerAddress() +
                // prints item
                generateItemDetail() +
                // prints the state tax
                SALES_TAX + order.getTotalSalesTax() +
                // print total amounts
                TOTAL_AMOUNT + order.getTotalAmount();
    }

    public String generateItemDetail() {
        StringBuilder result = new StringBuilder();
        for (ProductItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription())
                    .append(TABS)
                    .append(lineItem.getPrice())
                    .append(TABS)
                    .append(lineItem.getQuantity())
                    .append(TABS)
                    .append(lineItem.totalAmount())
                    .append(LINE_BREAK);
        }
        return result.toString();
    }
}