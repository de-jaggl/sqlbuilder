package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.columns.Column;
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
public class OrderBy
{
    private Column column;
    private OrderDirection direction;
}
