package de.jaggl.sqlbuilder.core.domain;

import static java.util.Locale.GERMAN;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class DoubleSize implements Size
{
    private static final DecimalFormat FORMAT = new DecimalFormat("0.0");

    static
    {
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(GERMAN));
    }

    private final double value;

    @Override
    public String getValue()
    {
        return FORMAT.format(value);
    }

    public static DoubleSize valueOf(Double size)
    {
        return size != null ? new DoubleSize(size.doubleValue()) : null;
    }
}
