package cc.xpbootcamp.warmup.cashier.core;


import cc.xpbootcamp.warmup.cashier.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.COLON;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.COMMA;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LONG_LINE;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.dataFormat;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceiptPlus {

    private static final String TITLE_CN = "老王超市，值得信赖";
    private static final String INVOICE_CN = "======" + TITLE_CN + "======";
    private static final String SALES_TAX_CN = "税额" + COLON;
    private static final String TOTAL_AMOUNT_CN = "总价" + COLON;
    private static final String DISCOUNT_TAX_CN = "折扣" + COLON;

    private Order order;

    public OrderReceiptPlus(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return generateInvoice() +
                // date
                generateDateString() +
                // prints item
                generateItemDetailPlus() +
                // print line
                generateLongLine() +
                // print total
                generateTotalDetail();
    }

    public String generateInvoice() {
        return INVOICE_CN + LINE_BREAK + LINE_BREAK;
    }


    public String generateLongLine() {
        return LONG_LINE + LINE_BREAK + LINE_BREAK;
    }


    public String generateDateString() {
        return order.getLocalDate().format(DateTimeFormatter.ofPattern(dataFormat, Locale.CHINA));
    }

    public String generateItemDetailPlus() {
        StringBuilder result = new StringBuilder();
        order.getLineItems().forEach(item -> result.append(item.getDescription()).append(COMMA)
                .append(item.generateItemLine())
                .append(LINE_BREAK));
        return result.toString();
    }


    public String generateTotalDetail() {
        StringBuilder result = new StringBuilder();
        // prints the state tax
        result.append(SALES_TAX_CN).append(order.getTotalSalesTax()).append(LINE_BREAK);
        if (order.hasDiscount()) {
            // print discount
            result.append(DISCOUNT_TAX_CN).append(order.getDiscountAmount()).append(LINE_BREAK);
        }
        // print total amounts
        result.append(TOTAL_AMOUNT_CN).append(order.getTotalAmount()).append(LINE_BREAK);
        return result.toString();
    }
}