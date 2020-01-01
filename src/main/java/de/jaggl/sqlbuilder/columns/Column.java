package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Definable;
import de.jaggl.sqlbuilder.domain.Groupable;
import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class Column implements Groupable, Selectable, Definable
{
    protected Table table;
    protected String name;

    private String alias;

    protected ColumnDefinition columnDefinition;

    public Column(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        this.table = table;
        this.name = name;
        this.alias = alias;
        this.columnDefinition = columnDefinition;
    }

    @Override
    public ColumnDefinition getColumnDefinition()
    {
        return columnDefinition;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return getFullNameOrAlias(context);
    }

    @Override
    public String getAlias()
    {
        return alias;
    }

    public Table getTable()
    {
        return table;
    }

    public String getName()
    {
        return name;
    }

    public String getFullName(BuildingContext context)
    {
        return table.getFullName(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }

    public String getFullNameOrAlias(BuildingContext context)
    {
        return table.getFullNameOrAlias(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }

    public Condition isNull()
    {
        return new GenericCondition(IS_NULL, this);
    }

    public Condition isNotNull()
    {
        return new GenericCondition(IS_NOT_NULL, this);
    }

    public Condition isEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_EQUAL_TO, this, otherColumn);
    }

    public Condition isNotEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, otherColumn);
    }
}
