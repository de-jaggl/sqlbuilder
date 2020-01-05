package de.jaggl.sqlbuilder.dialect;

import static lombok.AccessLevel.PRIVATE;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;
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
