package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class DoubleTypeColumn<T extends DoubleTypeColumn<T>> extends NumberColumn<T, Double>
{
    private static final DecimalFormat FORMAT = new DecimalFormat("0.0");

    static
    {
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
    }

    public DoubleTypeColumn(Table table, String name, String alias, ColumnDefinition columnDefinition, int sqlType)
    {
        super(table, name, alias, columnDefinition, sqlType);
    }
}
