package de.jaggl.sqlbuilder.utils;

import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.domain.LikeType.BEFORE;
import static de.jaggl.sqlbuilder.domain.LikeType.BOTH;

import java.time.LocalDate;
import java.time.LocalDateTime;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.functions.Function;

public final class BuilderUtils
{
    private BuilderUtils()
    {
        // private constructor to hide the public one
    }

    public static String getValued(Object value, BuildingContext context, Indentation indentation)
    {
        return getValued(value, "", "", context, indentation);
    }

    private static String getValued(Object value, String prefix, String postfix, BuildingContext context, Indentation indentation)
    {
        if (value == null)
        {
            return context.getDialect().getLabels().getNull();
        }
        if (Column.class.isAssignableFrom(value.getClass()))
        {
            return ((Column) value).getFullNameOrAlias(context);
        }
        if (Function.class.isAssignableFrom(value.getClass()))
        {
            return ((Function) value).getValue(context, indentation);
        }
        if (Number.class.isAssignableFrom(value.getClass()))
        {
            return String.valueOf(value);
        }
        if (LocalDate.class.isAssignableFrom(value.getClass()))
        {
            return valueApostrophe(prefix + context.getDialect().getDateFormatter().format((LocalDate) value) + postfix, context);
        }
        if (LocalDateTime.class.isAssignableFrom(value.getClass()))
        {
            return valueApostrophe(prefix + context.getDialect().getDateTimeFormatter().format((LocalDateTime) value) + postfix, context);
        }
        return valueApostrophe(prefix + String.valueOf(value) + postfix, context);
    }

    public static String columnApostrophe(String value, BuildingContext context)
    {
        return apostrophe(value, context.getDialect().getLabels().getColumnApostrophe());
    }

    public static String valueApostrophe(String value, BuildingContext context)
    {
        return apostrophe(value, context.getDialect().getLabels().getValueApostrophe());
    }

    private static String apostrophe(String value, char apostrophe)
    {
        return apostrophe + value.replaceAll("\\\\", "\\\\\\\\").replaceAll(Character.toString(apostrophe), "\\\\" + apostrophe) + apostrophe;
    }

    public static String buildLikePart(Object value, LikeType likeType, BuildingContext context, Indentation indentation)
    {
        var prefix = likeType == BEFORE || likeType == BOTH ? "%" : "";
        var postfix = likeType == AFTER || likeType == BOTH ? "%" : "";
        return getValued(value, prefix, postfix, context, indentation);
    }
}
