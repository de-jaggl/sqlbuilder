package de.jaggl.sqlbuilder.core.schema;

import static lombok.AccessLevel.PACKAGE;

import java.util.ArrayList;
import java.util.List;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.datetime.TimeColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.TimeColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DecimalColumn;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DecimalColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.FloatColumn;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.FloatColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.BigIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.BigIntColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.MediumIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.MediumIntColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.SmallIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.SmallIntColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.TinyIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.TinyIntColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.string.CharColumn;
import de.jaggl.sqlbuilder.core.columns.string.CharColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.string.TextColumn;
import de.jaggl.sqlbuilder.core.columns.string.TextColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.domain.JoinableTable;
import de.jaggl.sqlbuilder.core.domain.Queryable;
import de.jaggl.sqlbuilder.core.queries.Select;
import de.jaggl.sqlbuilder.core.utils.BuilderUtils;
import de.jaggl.sqlbuilder.core.utils.Indentation;
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

    /**
     * Creates a clone from the current {@link Table} but with the given alias
     *
     * @param alias the alias for the {@link Table}
     * @return the created {@link Table}-clone with the alias
     */
    public Table as(@SuppressWarnings("hiding") String alias)
    {
        return new Table(schema, name, alias);
    }

    /**
     * Creates a {@link JoinableTable} to be used in {@link Select#join(JoinableTable)} on the given {@link Condition}
     *
     * @param condition the {@link Condition} on which the join should be done
     * @return the {@link JoinableTable}
     */
    public JoinableTable on(Condition condition)
    {
        return new JoinableTable(null, this, condition);
    }

    /**
     * Creates a {@link BigIntColumnBuilder} to build a {@link BigIntColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link BigIntColumn} to be build
     * @return the {@link BigIntColumnBuilder} to build a {@link BigIntColumn} from
     */
    public BigIntColumnBuilder bigIntColumn(String columnName)
    {
        return new BigIntColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link CharColumnBuilder} to build a {@link CharColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link CharColumn} to be build
     * @return the {@link CharColumnBuilder} to build a {@link CharColumn} from
     */
    public CharColumnBuilder charColumn(String columnName)
    {
        return new CharColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link DecimalColumnBuilder} to build a {@link DecimalColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link DecimalColumn} to be build
     * @return the {@link DecimalColumnBuilder} to build a {@link DecimalColumn} from
     */
    public DecimalColumnBuilder decimalColumn(String columnName)
    {
        return new DecimalColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link DoubleColumnBuilder} to build a {@link DoubleColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link DoubleColumn} to be build
     * @return the {@link DoubleColumnBuilder} to build a {@link DoubleColumn} from
     */
    public DoubleColumnBuilder doubleColumn(String columnName)
    {
        return new DoubleColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link FloatColumnBuilder} to build a {@link FloatColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link FloatColumn} to be build
     * @return the {@link FloatColumnBuilder} to build a {@link FloatColumn} from
     */
    public FloatColumnBuilder floatColumn(String columnName)
    {
        return new FloatColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link IntColumnBuilder} to build a {@link IntColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link IntColumn} to be build
     * @return the {@link IntColumnBuilder} to build a {@link IntColumn} from
     */
    public IntColumnBuilder intColumn(String columnName)
    {
        return new IntColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link MediumIntColumnBuilder} to build a {@link MediumIntColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link MediumIntColumn} to be build
     * @return the {@link MediumIntColumnBuilder} to build a {@link MediumIntColumn} from
     */
    public MediumIntColumnBuilder mediumIntColumn(String columnName)
    {
        return new MediumIntColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link SmallIntColumnBuilder} to build a {@link SmallIntColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link SmallIntColumn} to be build
     * @return the {@link SmallIntColumnBuilder} to build a {@link SmallIntColumn} from
     */
    public SmallIntColumnBuilder smallIntColumn(String columnName)
    {
        return new SmallIntColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link TextColumnBuilder} to build a {@link TextColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link TextColumn} to be build
     * @return the {@link TextColumnBuilder} to build a {@link TextColumn} from
     */
    public TextColumnBuilder textColumn(String columnName)
    {
        return new TextColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link TinyIntColumnBuilder} to build a {@link TinyIntColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link TinyIntColumn} to be build
     * @return the {@link TinyIntColumnBuilder} to build a {@link TinyIntColumn} from
     */
    public TinyIntColumnBuilder tinyIntColumn(String columnName)
    {
        return new TinyIntColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link VarCharColumnBuilder} to build a {@link VarCharColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link VarCharColumn} to be build
     * @return the {@link VarCharColumnBuilder} to build a {@link VarCharColumn} from
     */
    public VarCharColumnBuilder varCharColumn(String columnName)
    {
        return new VarCharColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link DateColumnBuilder} to build a {@link DateColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link DateColumn} to be build
     * @return the {@link DateColumnBuilder} to build a {@link DateColumn} from
     */
    public DateColumnBuilder dateColumn(String columnName)
    {
        return new DateColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link DateTimeColumnBuilder} to build a {@link DateTimeColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link DateTimeColumn} to be build
     * @return the {@link DateTimeColumnBuilder} to build a {@link DateTimeColumn} from
     */
    public DateTimeColumnBuilder dateTimeColumn(String columnName)
    {
        return new DateTimeColumnBuilder(this, columnName);
    }

    /**
     * Creates a {@link TimeColumnBuilder} to build a {@link TimeColumn} with the given name for the current {@link Table}
     *
     * @param columnName the name for the {@link TimeColumn} to be build
     * @return the {@link TimeColumnBuilder} to build a {@link TimeColumn} from
     */
    public TimeColumnBuilder timeColumn(String columnName)
    {
        return new TimeColumnBuilder(this, columnName);
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

    /**
     * Creates a {@link Table} with the given name
     *
     * @param name the name for the {@link Table}
     * @return the created {@link Table}
     */
    public static Table create(String name)
    {
        return new Table(name);
    }
}
