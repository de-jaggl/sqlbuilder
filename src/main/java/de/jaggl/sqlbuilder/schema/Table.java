package de.jaggl.sqlbuilder.schema;

import static lombok.AccessLevel.PACKAGE;

import java.util.ArrayList;
import java.util.List;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.datetime.DateColumnBuilder;
import de.jaggl.sqlbuilder.columns.datetime.DateTimeColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.DecimalColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.FloatColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.BigIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.MediumIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.SmallIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.TinyIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.CharColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.TextColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.JoinableTable;
import de.jaggl.sqlbuilder.domain.Queryable;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@RequiredArgsConstructor(access = PACKAGE)
@Getter
@ToString
public class Table implements Queryable
{
    private Schema schema;
    private final String name;

    private String alias;

    @ToString.Exclude
    private List<Column> columns;

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

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return getFullName(context);
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
