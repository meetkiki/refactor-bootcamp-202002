package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataUtils {

    static final String dataFormat = "yyyy年MM月dd日";


    /**
     * 根据日期获取 星期 （2019-05-06 ——> 星期一）
     *
     * @param currentDate
     * @return
     */
    public static String dateToWeekCn(LocalDate currentDate) {
        return WeekEnum.weekToCn(String.valueOf(currentDate.getDayOfWeek()));
    }

    /**
     * 根据日期获取 星期枚举 （2019-05-06 ——> 星期一）
     *
     * @param currentDate
     * @return
     */
    public static WeekEnum dateToWeek(LocalDate currentDate) {
        return WeekEnum.weekToEnum(String.valueOf(currentDate.getDayOfWeek()));
    }

    public static boolean determineWeek(WeekEnum weekEnum, LocalDate localDate) {
        return dateToWeek(localDate).equals(weekEnum);
    }


    public static String generateNowDate() {
        return dateToString(LocalDate.now());
    }

    /**
     * 时间转换为dataFormate格式
     *
     * @param currentDate
     * @return
     */
    public static String dateToString(LocalDate currentDate) {
        return currentDate.format(DateTimeFormatter.ofPattern(dataFormat));
    }

    /**
     * 字符串转化为时间
     *
     * @param dateStr
     * @return
     */
    public static LocalDate strToDate(String dateStr) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        return LocalDate.parse(dateStr,timeFormatter);
    }
}
