package de.jaggl.sqlbuilder.core.queries;

import de.jaggl.sqlbuilder.core.dialect.Dialect;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.Getter;

/**
 * @author Martin Schumacher
 *
 * @since 2.4.0
 */
@Getter
public class CreateTable implements ExecutableQuery
{
    private Table table;

    CreateTable(CreateTable createTable)
    {
        table = createTable.table;
    }

    CreateTable(Table table)
    {
        this.table = table;
    }

    @Override
    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public static CreateTable copy(CreateTable createTable)
    {
        return new CreateTable(createTable);
    }
}
