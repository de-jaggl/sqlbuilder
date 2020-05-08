package de.jaggl.sqlbuilder.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class Limit
{
    private long value;

    @Getter
    private long offset;

    public long getLimit()
    {
        return value;
    }
}
