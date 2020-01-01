package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Definable
{
    ColumnDefinition getColumnDefinition();
}
