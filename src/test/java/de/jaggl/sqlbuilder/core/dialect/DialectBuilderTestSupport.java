package de.jaggl.sqlbuilder.core.dialect;

import static lombok.AccessLevel.PRIVATE;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.dialect.DefaultDialect;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DialectBuilderTestSupport
{
    public static <T extends DefaultDialect> String buildColumnDefinition(T dialect, ColumnDefinition definition, BuildingContext context,
            Indentation indentation)
    {
        return dialect.buildColumnDefinition(definition, context, indentation);
    }
}
