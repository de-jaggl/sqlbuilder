package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.dialect.Dialect;

public class BuildingContext
{
    private Dialect dialect;
    private String delimiter;

    public BuildingContext(Dialect dialect, String delimiter)
    {
        this.dialect = dialect;
        this.delimiter = delimiter;
    }

    public Dialect getDialect()
    {
        return dialect;
    }

    public String getDelimiter()
    {
        return delimiter;
    }
}
