package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.columns.Column;
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
