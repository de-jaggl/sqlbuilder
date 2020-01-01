package de.jaggl.sqlbuilder.domain;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class IntSize implements Size
{
    private final int value;

    private IntSize(int value)
    {
        this.value = value;
    }

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
