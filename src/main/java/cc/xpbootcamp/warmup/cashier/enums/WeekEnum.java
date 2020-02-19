package cc.xpbootcamp.warmup.cashier.enums;

import java.util.Arrays;

public enum  WeekEnum {

    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    String week_cn;

    WeekEnum(String weekCn) {
        this.week_cn = weekCn;
    }

    public String getWeekCn() {
        return week_cn;
    }

    public static WeekEnum weekToEnum(String week){
        return Arrays.stream(WeekEnum.values())
                .filter(weekEnum -> weekEnum.name().equals(week)).findFirst()
                .orElseThrow(()->new IllegalArgumentException("Unparseable date"));
    }

    public static String weekToCn(String week){
        return weekToEnum(week).getWeekCn();
    }
}
