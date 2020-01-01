package de.jaggl.sqlbuilder.columns;

import de.jaggl.sqlbuilder.domain.Size;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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

    public String getDefinitionName()
    {
        return definitionName;
    }

    public Size getSize()
    {
        return size;
    }

    public boolean isNullable()
    {
        return isNullable;
    }

    public boolean isDefaultNull()
    {
        return isDefaultNull;
    }

    public boolean isUnsigned()
    {
        return isUnsigned;
    }

    public boolean isAutoIncrement()
    {
        return isAutoIncrement;
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }
}
