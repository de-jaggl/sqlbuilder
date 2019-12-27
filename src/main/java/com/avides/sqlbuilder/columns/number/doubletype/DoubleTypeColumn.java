package com.avides.sqlbuilder.columns.number.doubletype;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.columns.number.NumberColumn;
import com.avides.sqlbuilder.schema.Table;

public abstract class DoubleTypeColumn<T extends DoubleTypeColumn<T>> extends NumberColumn<T, Double>
{
    private static final DecimalFormat FORMAT = new DecimalFormat("0.0");

    static
    {
        FORMAT.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
    }

    public DoubleTypeColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }
}
