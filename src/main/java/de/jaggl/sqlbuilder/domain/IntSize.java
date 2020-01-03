package de.jaggl.sqlbuilder.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class IntSize implements Size
{
    private final int value;

    @Override
    public String getValue()
    {
        return Integer.toString(value);
    }

    public static IntSize valueOf(Integer size)
    {
        return size != null ? new IntSize(size.intValue()) : null;
    }
}
