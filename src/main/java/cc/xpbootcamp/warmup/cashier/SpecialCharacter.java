package cc.xpbootcamp.warmup.cashier;

class SpecialCharacter {

    static final String ZERO = "0.00";
    static final String TABS = "\t";
    static final String MULTIPLY = "x";
    static final String SPACE = " ";
    static final String COLON = "：";
    static final String COMMA = "，";
    static final String LINE_BREAK = "\n";
    static final String LONG_LINE = "-------------------------";


    // old
    static final String TITLE = "Printing Orders";
    static final String INVOICE = "======" + TITLE + "======";
    static final String SALES_TAX = "Sales Tax" + TABS;
    static final String TOTAL_AMOUNT = "Total Amount" + TABS;


    // plus
    static final String TITLE_CN = "老王超市，值得信赖";
    static final String INVOICE_CN = "======" + TITLE_CN + "======";
    static final String SALES_TAX_CN = "税额" + COLON;
    static final String TOTAL_AMOUNT_CN = "总价" + COLON;
    static final String DISCOUNT_TAX_CN = "折扣" + COLON;

}
