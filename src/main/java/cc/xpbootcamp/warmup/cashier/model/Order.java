package cc.xpbootcamp.warmup.cashier.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.ZERO;


public class Order {
    private String customerName;
    private String customerAddress;
    private LocalDate localDate;
    private List<ProductItem> lineItemList;

    private Discount discount;

    private BigDecimal totalSalesTax;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;

    public Order(LocalDate localDate, List<ProductItem> lineItemList) {
        this(localDate, null, null, lineItemList);
    }

    public Order(String customerName, String customerAddress, List<ProductItem> lineItemList) {
        this(LocalDate.now(), customerName, customerAddress, lineItemList);
    }

    public Order(LocalDate localDate, String customerName, String customerAddress, List<ProductItem> lineItemList) {
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
        if (hasDiscount()) {
            this.discountAmount = discount.discountAmount(localDate, totalAmount);
        }
    }

    public boolean hasDiscount(){
        return Objects.nonNull(discount) && discount.isDiscount(localDate);
    }


    private void calculateOrderAmount(List<ProductItem> lineItemList) {
        for (ProductItem item : lineItemList) {
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

    public List<ProductItem> getLineItems() {
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
