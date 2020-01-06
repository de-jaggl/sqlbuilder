package de.jaggl.sqlbuilder.queries;

import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.Getter;

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
