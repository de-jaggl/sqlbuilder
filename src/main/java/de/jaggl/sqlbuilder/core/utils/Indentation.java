package de.jaggl.sqlbuilder.core.utils;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor(access = PRIVATE)
@RequiredArgsConstructor(access = PRIVATE)
@ToString
public class Indentation
{
    @Getter
    private final boolean enabled;

    private int currentIndentation = 0;

    public String getIndent()
    {
        return enabled ? repeat("  ", currentIndentation) : "";
    }

    public String getDelimiter()
    {
        return enabled ? System.lineSeparator() : " ";
    }

    public Indentation indent()
    {
        return new Indentation(enabled, currentIndentation + 1);
    }

    public Indentation deIndent()
    {
        return new Indentation(enabled, currentIndentation - 1);
    }

    public static Indentation enabled()
    {
        return new Indentation(true);
    }

    public static Indentation disabled()
    {
        return new Indentation(false);
    }

    public static Indentation indent(boolean indent)
    {
        return new Indentation(indent);
    }

    private static String repeat(String input, int count)
    {
        var builder = new StringBuilder();
        for (int i = 0; i < count; i++)
        {
            builder.append(input);
        }
        return builder.toString();
    }
}
