package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.COMMA;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.DISCOUNT_TAX_CN;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.MULTIPLY;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.SALES_TAX_CN;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.SPACE;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.TABS;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.TOTAL_AMOUNT_CN;
import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.ZERO;

public class Order {
    private String customerName;
    private String customerAddress;
    private LocalDate localDate;
    private List<LineItem> lineItemList;

    private Discount discount;

    private BigDecimal totalSalesTax;
    private BigDecimal totalAmount;

    public Order(List<LineItem> lineItemList) {
        this(LocalDate.now(), lineItemList);
    }

    public Order(LocalDate localDate, List<LineItem> lineItemList) {
        this(localDate, null, null, lineItemList);
    }

    public Order( String customerName, String customerAddress,List<LineItem> lineItemList) {
        this(LocalDate.now(), customerName, customerAddress, lineItemList);
    }

    public Order(LocalDate localDate, String customerName, String customerAddress, List<LineItem> lineItemList) {
        this.localDate = localDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItemList = lineItemList;
        this.totalSalesTax = new BigDecimal(ZERO);
        this.totalAmount = new BigDecimal(ZERO);

        this.calculateOrderAmount(lineItemList);
    }

    public Order discount(Discount discount){
        this.discount = discount;
        return this;
    }

    private void calculateOrderAmount(List<LineItem> lineItemList) {
        for (LineItem item : lineItemList) {
            this.totalAmount = this.totalAmount.add(item.totalAmount());
        }
        // calculate sales tax @ rate of 10%
        this.totalSalesTax = this.totalAmount.divide(BigDecimal.TEN, BigDecimal.ROUND_HALF_UP);
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        this.totalAmount = this.totalAmount.add(this.totalSalesTax);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public BigDecimal getTotalSalesTax() {
        return totalSalesTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String generateDateString() {
        return DataUtils.dateToString(localDate) + COMMA + DataUtils.dateToWeekCn(localDate);
    }

    public String generateItemDetail() {
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
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

    public String generateItemDetailPlus() {
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
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
        result.append(SALES_TAX_CN).append(getTotalSalesTax());
        // print discount
        result.append(discountCalculate());
        // print total amounts
        result.append(TOTAL_AMOUNT_CN).append(getTotalAmount());
        return result.toString();
    }

    private String discountCalculate() {
        StringBuilder result = new StringBuilder();
        if (Objects.nonNull(discount) && discount.hasDiscount(localDate)) {
            BigDecimal discountAmount = discount.discountAmount(localDate, totalAmount);
            result.append(DISCOUNT_TAX_CN)
                    .append(discountAmount)
                    .append(LINE_BREAK);
            this.totalAmount = this.totalAmount.subtract(discountAmount);
        }
        return result.toString();
    }

}
