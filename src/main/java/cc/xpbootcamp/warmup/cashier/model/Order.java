package cc.xpbootcamp.warmup.cashier.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.TABS;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.ZERO;


public class Order {
    private String customerName;
    private String customerAddress;
    private LocalDate localDate;
    private List<LineItem> lineItemList;

    private Discount discount;

    private BigDecimal totalSalesTax;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;

    public Order(List<LineItem> lineItemList) {
        this(LocalDate.now(), lineItemList);
    }

    public Order(LocalDate localDate, List<LineItem> lineItemList) {
        this(localDate, null, null, lineItemList);
    }

    public Order(String customerName, String customerAddress, List<LineItem> lineItemList) {
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

    public Order discount(Discount discount) {
        this.discount = discount;
        this.discountCalculate();
        return this;
    }

    public void discountCalculate() {
        if (Objects.nonNull(discount) && discount.hasDiscount(localDate)) {
            this.discountAmount = discount.discountAmount(localDate, totalAmount);
        }
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
        return Objects.isNull(this.discountAmount) ? this.totalAmount : this.totalAmount.subtract(this.discountAmount);
    }





    public LocalDate getLocalDate() {
        return localDate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
}
