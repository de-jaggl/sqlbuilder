package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Definable
{
    ColumnDefinition getColumnDefinition();
}
