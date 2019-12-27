package com.avides.sqlbuilder.schema;

import java.util.ArrayList;
import java.util.List;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.datetime.DateColumnBuilder;
import com.avides.sqlbuilder.columns.datetime.DateTimeColumnBuilder;
import com.avides.sqlbuilder.columns.number.doubletype.DecimalColumnBuilder;
import com.avides.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import com.avides.sqlbuilder.columns.number.doubletype.FloatColumnBuilder;
import com.avides.sqlbuilder.columns.number.integer.BigIntColumnBuilder;
import com.avides.sqlbuilder.columns.number.integer.IntColumnBuilder;
import com.avides.sqlbuilder.columns.number.integer.MediumIntColumnBuilder;
import com.avides.sqlbuilder.columns.number.integer.SmallIntColumnBuilder;
import com.avides.sqlbuilder.columns.number.integer.TinyIntColumnBuilder;
import com.avides.sqlbuilder.columns.string.CharColumnBuilder;
import com.avides.sqlbuilder.columns.string.TextColumnBuilder;
import com.avides.sqlbuilder.columns.string.VarCharColumnBuilder;
import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.dialect.Dialect;
import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.domain.JoinableTable;
import com.avides.sqlbuilder.domain.Queryable;
import com.avides.sqlbuilder.utils.BuilderUtils;
import com.avides.sqlbuilder.utils.Indentation;

public class Table implements Queryable
{
    private Schema schema;
    private String name;

    private String alias;

    private List<Column> columns;

    Table(String name)
    {
        this.name = name;
    }

    Table(Schema schema, String name)
    {
        this.schema = schema;
        this.name = name;
    }

    Table(Schema schema, String name, String alias)
    {
        this.schema = schema;
        this.name = name;
        this.alias = alias;
    }

    public Schema getSchema()
    {
        return schema;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return getFullName(context);
    }

    @Override
    public String getAlias()
    {
        return alias;
    }

    public String getFullName(BuildingContext context)
    {
        return (getSchema() != null ? BuilderUtils.columnApostrophe(getSchema().getName(), context) + "." : "") + BuilderUtils
                .columnApostrophe(getName(), context);
    }

    public String getFullNameOrAlias(BuildingContext context)
    {
        return alias != null ? BuilderUtils.columnApostrophe(alias, context) : getFullName(context);
    }

    public List<Column> getColumns()
    {
        return columns;
    }

    public Table as(@SuppressWarnings("hiding") String alias)
    {
        return new Table(schema, name, alias);
    }

    public JoinableTable on(Condition condition)
    {
        return new JoinableTable(null, this, condition);
    }

    public BigIntColumnBuilder bigIntColumn(String columnName)
    {
        return new BigIntColumnBuilder(this, columnName);
    }

    public CharColumnBuilder charColumn(String columnName)
    {
        return new CharColumnBuilder(this, columnName);
    }

    public DecimalColumnBuilder decimalColumn(String columnName)
    {
        return new DecimalColumnBuilder(this, columnName);
    }

    public DoubleColumnBuilder doubleColumn(String columnName)
    {
        return new DoubleColumnBuilder(this, columnName);
    }

    public FloatColumnBuilder floatColumn(String columnName)
    {
        return new FloatColumnBuilder(this, columnName);
    }

    public IntColumnBuilder intColumn(String columnName)
    {
        return new IntColumnBuilder(this, columnName);
    }

    public MediumIntColumnBuilder mediumIntColumn(String columnName)
    {
        return new MediumIntColumnBuilder(this, columnName);
    }

    public SmallIntColumnBuilder smallIntColumn(String columnName)
    {
        return new SmallIntColumnBuilder(this, columnName);
    }

    public TextColumnBuilder textColumn(String columnName)
    {
        return new TextColumnBuilder(this, columnName);
    }

    public TinyIntColumnBuilder tinyIntColumn(String columnName)
    {
        return new TinyIntColumnBuilder(this, columnName);
    }

    public VarCharColumnBuilder varCharColumn(String columnName)
    {
        return new VarCharColumnBuilder(this, columnName);
    }

    public DateColumnBuilder dateColumn(String columnName)
    {
        return new DateColumnBuilder(this, columnName);
    }

    public DateTimeColumnBuilder dateTimeColumn(String columnName)
    {
        return new DateTimeColumnBuilder(this, columnName);
    }

    <T extends Column> T addColumn(T column)
    {
        if (columns == null)
        {
            columns = new ArrayList<>();
        }
        columns.add(column);
        return column;
    }

    public String buildCreateTable(Dialect syntax, Indentation indentation)
    {
        return syntax.buildCreate(this, indentation);
    }

    public String buildCreateTable(Dialect syntax)
    {
        return syntax.buildCreate(this, Indentation.disabled());
    }

    public static Table create(String name)
    {
        return new Table(name);
    }
}
