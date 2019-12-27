package de.jaggl.sqlbuilder.utils;

public class Indentation
{
    private boolean enabled;
    private int currentIndentation;

    private Indentation(boolean enabled, int currentIndentation)
    {
        this.enabled = enabled;
        this.currentIndentation = currentIndentation >= 0 ? currentIndentation : 0;
    }

    private Indentation(boolean enabled)
    {
        this.enabled = enabled;
        currentIndentation = 0;
    }

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

    public boolean isEnabled()
    {
        return enabled;
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
