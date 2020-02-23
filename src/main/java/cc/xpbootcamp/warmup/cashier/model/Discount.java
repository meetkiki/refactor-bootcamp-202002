package cc.xpbootcamp.warmup.cashier.model;

import cc.xpbootcamp.warmup.cashier.enums.WeekEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Discount {

    private WeekEnum discountWeek;

    private BigDecimal discountRatio;

    public Discount(WeekEnum discountWeek, BigDecimal discountRatio) {
        this.discountWeek = discountWeek;
        this.discountRatio = discountRatio;
    }

    public boolean isDiscount(LocalDate curDate) {
        if (Objects.nonNull(discountWeek)) {
            return WeekEnum.weekToEnum(String.valueOf(curDate.getDayOfWeek())).equals(discountWeek);
        }
        return Boolean.FALSE;
    }

    public BigDecimal discountAmount(LocalDate curDate, BigDecimal totalAmount) {
        if (isDiscount(curDate)) {
            return totalAmount.multiply(BigDecimal.ONE.subtract(discountRatio)).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
