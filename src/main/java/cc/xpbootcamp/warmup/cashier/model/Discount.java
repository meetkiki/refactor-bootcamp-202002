package cc.xpbootcamp.warmup.cashier.model;

import cc.xpbootcamp.warmup.cashier.enums.WeekEnum;
import cc.xpbootcamp.warmup.cashier.utils.DataUtil;

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

    public boolean hasDiscount(LocalDate curDate){
        if (Objects.nonNull(discountWeek)){
            return DataUtil.determineWeek(discountWeek,curDate);
        }
        return Boolean.FALSE;
    }

    public BigDecimal discountAmount(LocalDate curDate,BigDecimal totalAmount){
        if (hasDiscount(curDate)){
            return totalAmount.multiply(BigDecimal.ONE.subtract(discountRatio)).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
