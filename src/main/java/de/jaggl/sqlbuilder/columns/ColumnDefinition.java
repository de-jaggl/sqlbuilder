package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Size;
import de.jaggl.sqlbuilder.utils.Indentation;

public class ColumnDefinition
{
    private final String definitionName;
    private final Size size;
    private final boolean isNullable;
    private final boolean isDefaultNull;
    private final boolean isUnsigned;
    private final boolean isAutoIncrement;
    private final Object defaultValue;

    public ColumnDefinition(String definitionName, Size size, boolean isNullable, boolean isDefaultNull, boolean isUnsigned, boolean isAutoIncrement,
            Object defaultValue)
    {
        this.definitionName = definitionName;
        this.size = size;
        this.isNullable = isNullable;
        this.isDefaultNull = isDefaultNull;
        this.isUnsigned = isUnsigned;
        this.isAutoIncrement = isAutoIncrement;
        this.defaultValue = defaultValue;
    }

    public String getDefinition(BuildingContext context, Indentation indentation)
    {
        StringBuilder builder = new StringBuilder(definitionName);

        if (size != null)
        {
            builder.append("(").append(size.getValue()).append(")");
        }

        if (isUnsigned)
        {
            builder.append(" UNSIGNED");
        }

        if (isNullable)
        {
            if (isDefaultNull)
            {
                builder.append(" DEFAULT NULL");
            }
        }
        else
        {
            builder.append(" NOT NULL");
        }

        if (defaultValue != null)
        {
            builder.append(" DEFAULT ");
            builder.append(getValued(defaultValue, context, indentation));
        }

        if (isAutoIncrement)
        {
            builder.append(" AUTO_INCREMENT");
        }

        return builder.toString();
    }
}
