package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.util.List;

import static cc.xpbootcamp.warmup.cashier.Character.LINE_BREAK;
import static cc.xpbootcamp.warmup.cashier.Character.TABS;
import static cc.xpbootcamp.warmup.cashier.Character.ZERO;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItemList;

    private BigDecimal totalSalesTax;
    private BigDecimal totalAmount;

    public Order(String customerName, String customerAddress, List<LineItem> lineItemList) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItemList = lineItemList;
        this.totalSalesTax = new BigDecimal(ZERO);
        this.totalAmount = new BigDecimal(ZERO);

        calculateOrderAmount(lineItemList);
    }

    private void calculateOrderAmount(List<LineItem> lineItemList) {
        for (LineItem item : lineItemList) {
            this.totalAmount = this.totalAmount.add(item.totalAmount());
        }
        // calculate sales tax @ rate of 10%
        this.totalSalesTax = this.totalAmount.divide(BigDecimal.TEN, BigDecimal.ROUND_HALF_UP);
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        this.totalAmount =  this.totalAmount.add(this.totalSalesTax);
    }

    public String generateItemDetail(){
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : lineItemList) {
            result.append(lineItem.getDescription());
            result.append(TABS);
            result.append(lineItem.getPrice());
            result.append(TABS);
            result.append(lineItem.getQuantity());
            result.append(TABS);
            result.append(lineItem.totalAmount());
            result.append(LINE_BREAK);
        }
        return result.toString();
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
}
