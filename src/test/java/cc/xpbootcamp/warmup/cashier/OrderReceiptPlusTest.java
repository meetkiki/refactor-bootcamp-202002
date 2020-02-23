package cc.xpbootcamp.warmup.cashier;

import cc.xpbootcamp.warmup.cashier.core.OrderReceiptPlus;
import cc.xpbootcamp.warmup.cashier.model.Discount;
import cc.xpbootcamp.warmup.cashier.model.ProductItem;
import cc.xpbootcamp.warmup.cashier.model.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static cc.xpbootcamp.warmup.cashier.enums.WeekEnum.WEDNESDAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class OrderReceiptPlusTest {

    @Test
    public void shouldPrintCustomerInformationOnOrderNoDiscount() {

        List<ProductItem> lineItems = new ArrayList<ProductItem>() {{
            add(new ProductItem("巧克力", 21.5, 2));
            add(new ProductItem("小白菜", 10.0, 1));
        }};
        LocalDate localDate = LocalDate.of(2020, 2, 17);
        Order order = new Order(localDate, lineItems);
        OrderReceiptPlus receipt = new OrderReceiptPlus(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("老王超市，值得信赖"));
        assertThat(output, containsString("巧克力，21.50 x 2，43.00"));
        assertThat(output, containsString("小白菜，10.00 x 1，10.00"));
        assertThat(output, containsString("税额：5.30"));
        assertThat(output, containsString("总价：58.30"));
    }

    @Test
    public void shouldPrintCustomerInformationOnOrderDiscount() {

        List<ProductItem> lineItems = new ArrayList<ProductItem>() {{
            add(new ProductItem("巧克力", 21.5, 2));
            add(new ProductItem("小白菜", 10.0, 1));
        }};
        LocalDate localDate = LocalDate.of(2020, 2, 19);
        Order order = new Order(localDate, lineItems)
                .discount(new Discount(WEDNESDAY, new BigDecimal("0.98")));
        OrderReceiptPlus receipt = new OrderReceiptPlus(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("老王超市，值得信赖"));
        assertThat(output, containsString("巧克力，21.50 x 2，43.00"));
        assertThat(output, containsString("小白菜，10.00 x 1，10.00"));
        assertThat(output, containsString("税额：5.30"));
        assertThat(output, containsString("折扣：1.17"));
        assertThat(output, containsString("总价：57.13"));
    }



    @Test
    public void shouldPrintCustomerInformationOnOrderDiscountNoAmount() {
        List<ProductItem> lineItems = new ArrayList<ProductItem>() {{
            add(new ProductItem("巧克力", 21.5, 2));
            add(new ProductItem("小白菜", 10.0, 1));
        }};
        LocalDate localDate = LocalDate.of(2020, 2, 19);
        Order order = new Order(localDate, lineItems)
                .discount(new Discount(WEDNESDAY, new BigDecimal("1.00")));
        OrderReceiptPlus receipt = new OrderReceiptPlus(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("老王超市，值得信赖"));
        assertThat(output, containsString("巧克力，21.50 x 2，43.00"));
        assertThat(output, containsString("小白菜，10.00 x 1，10.00"));
        assertThat(output, containsString("税额：5.30"));
        assertThat(output, containsString("折扣：0.00"));
        assertThat(output, containsString("总价：58.30"));
    }
}
