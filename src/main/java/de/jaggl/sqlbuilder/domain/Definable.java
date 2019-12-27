package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;

public interface Definable
{
    ColumnDefinition getColumnDefinition();
}
