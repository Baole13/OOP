package coffeeshop.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatUtil {
    private static final DecimalFormatSymbols SYMBOLS = new DecimalFormatSymbols();
    private static final DecimalFormat DEC = new DecimalFormat("#,###");
    static {
        SYMBOLS.setGroupingSeparator('.');
        DEC.setDecimalFormatSymbols(SYMBOLS);
        DEC.setMaximumFractionDigits(0);
        DEC.setMinimumFractionDigits(0);
    }

    public static String formatCurrency(double amount) {
        long rounded = Math.round(amount);
        String formatted = DEC.format(rounded);
        return formatted + " VND";
    }
}
