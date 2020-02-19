package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import static cc.xpbootcamp.warmup.cashier.SpecialCharacter.DISCOUNT_TAX_CN;

public class Discount {

    private WeekEnum discountWeek;

    private BigDecimal discountRatio;

    public Discount(WeekEnum discountWeek, BigDecimal discountRatio) {
        this.discountWeek = discountWeek;
        this.discountRatio = discountRatio;
    }

    public boolean hasDiscount(LocalDate curDate){
        if (Objects.nonNull(discountWeek)){
            return DataUtils.determineWeek(discountWeek,curDate);
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
