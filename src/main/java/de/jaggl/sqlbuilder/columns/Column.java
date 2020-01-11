package de.jaggl.sqlbuilder.columns;

import de.jaggl.sqlbuilder.conditions.EqualityConditions;
import de.jaggl.sqlbuilder.conditions.NullableConditions;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Definable;
import de.jaggl.sqlbuilder.domain.Groupable;
import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.domain.SqlTypeSupplier;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@Getter
@ToString
public abstract class Column implements Groupable, Selectable, Definable, NullableConditions, EqualityConditions, SqlTypeSupplier
{
    @ToString.Exclude
    protected Table table;
    protected String name;

    private String alias;

    protected ColumnDefinition columnDefinition;

    private int sqlType;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return getFullNameOrAlias(context);
    }

    public String getFullName(BuildingContext context)
    {
        return table.getFullName(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }

    public String getFullNameOrAlias(BuildingContext context)
    {
        return table.getFullNameOrAlias(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }
}
