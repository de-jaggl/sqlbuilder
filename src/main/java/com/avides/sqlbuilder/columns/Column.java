package com.avides.sqlbuilder.columns;

import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.conditions.GenericCondition;
import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.domain.Definable;
import com.avides.sqlbuilder.domain.Groupable;
import com.avides.sqlbuilder.domain.Selectable;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.BuilderUtils;
import com.avides.sqlbuilder.utils.Indentation;

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
        return (table != null ? table.getFullName(context) + "." : "") + BuilderUtils.columnApostrophe(name, context);
    }

    public String getFullNameOrAlias(BuildingContext context)
    {
        return (table != null ? table.getFullNameOrAlias(context) + "." : "") + BuilderUtils.columnApostrophe(name, context);
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
