package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final String ZERO = "0.0";
    private static final String TABS = "\t";
    private static final String LINE_BREAK = "\n";

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        output.append("======Printing Orders======"+LINE_BREAK);

        // print date, bill no, customer name
        // output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
        // output.append(order.getCustomerLoyaltyNumber());

        // prints lineItems
        BigDecimal totalSalesTax = new BigDecimal(ZERO);
        BigDecimal totalAmount = new BigDecimal(ZERO);
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append(TABS);
            output.append(lineItem.getPrice());
            output.append(TABS);
            output.append(lineItem.getQuantity());
            output.append(TABS);
            output.append(lineItem.totalAmount());
            output.append(LINE_BREAK);

            // calculate sales tax @ rate of 10%
            BigDecimal salesTax = lineItem.totalAmount().divide(BigDecimal.TEN, BigDecimal.ROUND_HALF_UP);
            totalSalesTax = totalSalesTax.add(salesTax);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount = totalAmount.add(lineItem.totalAmount().add(salesTax));
        }

        // prints the state tax
        output.append("Sales Tax").append(TABS).append(totalSalesTax.toString());

        // print total amount
        output.append("Total Amount").append(TABS).append(totalAmount.toString());
        return output.toString();
    }
}