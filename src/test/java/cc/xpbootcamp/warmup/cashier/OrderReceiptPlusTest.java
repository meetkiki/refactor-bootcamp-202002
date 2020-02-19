package cc.xpbootcamp.warmup.cashier;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static cc.xpbootcamp.warmup.cashier.WeekEnum.WEDNESDAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class OrderReceiptPlusTest {

    @Test
    public void shouldPrintCustomerInformationOnOrderNoDiscount() {

        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.5, 2));
            add(new LineItem("小白菜", 10.0, 1));
        }};
        Order order = new Order(DataUtils.strToDate("2020年02月17日"), lineItems);
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

        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.5, 2));
            add(new LineItem("小白菜", 10.0, 1));
        }};
        Order order = new Order(DataUtils.strToDate("2020年02月19日"), lineItems)
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

}
