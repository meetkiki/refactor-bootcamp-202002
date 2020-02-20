package cc.xpbootcamp.warmup.cashier.core;


import cc.xpbootcamp.warmup.cashier.model.LineItem;
import cc.xpbootcamp.warmup.cashier.model.Order;
import cc.xpbootcamp.warmup.cashier.utils.DataUtil;

import java.math.RoundingMode;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.COMMA;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.DISCOUNT_TAX_CN;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.INVOICE_CN;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LONG_LINE;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.MULTIPLY;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.SALES_TAX_CN;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.SPACE;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.TOTAL_AMOUNT_CN;

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
                generateDateString() + LINE_BREAK + LINE_BREAK +
                // prints item
                generateItemDetailPlus() +
                // print line
                LONG_LINE +
                // print total
                generateTotalDetail();
    }

    public String generateDateString() {
        return DataUtil.dateToString(order.getLocalDate()) + COMMA + DataUtil.dateToWeekCn(order.getLocalDate());
    }

    public String generateItemDetailPlus() {
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription()).append(COMMA)
                    .append(lineItem.getPrice().setScale(2, RoundingMode.HALF_UP))
                    .append(SPACE).append(MULTIPLY).append(SPACE)
                    .append(lineItem.getQuantity()).append(COMMA)
                    .append(lineItem.totalAmount().setScale(2, RoundingMode.HALF_UP))
                    .append(LINE_BREAK);
        }
        return result.toString();
    }


    public String generateTotalDetail() {
        StringBuilder result = new StringBuilder();
        // prints the state tax
        result.append(SALES_TAX_CN).append(order.getTotalSalesTax()).append(LINE_BREAK);
        // print discount
        result.append(DISCOUNT_TAX_CN).append(order.getDiscountAmount()).append(LINE_BREAK);
        // print total amounts
        result.append(TOTAL_AMOUNT_CN).append(order.getTotalAmount()).append(LINE_BREAK);
        return result.toString();
    }
}