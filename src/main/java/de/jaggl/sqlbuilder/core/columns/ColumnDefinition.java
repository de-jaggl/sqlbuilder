package de.jaggl.sqlbuilder.core.columns;

import de.jaggl.sqlbuilder.core.domain.Size;
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
public class ColumnDefinition
{
    private final String definitionName;
    private final Size size;
    private final boolean isNullable;
    private final boolean isDefaultNull;
    private final boolean isUnsigned;
    private final boolean isAutoIncrement;
    private final Object defaultValue;
}
