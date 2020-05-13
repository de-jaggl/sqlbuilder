package de.jaggl.sqlbuilder.core.utils;

import static de.jaggl.sqlbuilder.core.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.core.domain.LikeType.BEFORE;
import static de.jaggl.sqlbuilder.core.domain.LikeType.BOTH;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.domain.LikeType;
import de.jaggl.sqlbuilder.core.domain.Placeholder;
import de.jaggl.sqlbuilder.core.domain.Plain;
import de.jaggl.sqlbuilder.core.functions.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BuilderUtils
{
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
        if (Plain.class.isAssignableFrom(value.getClass()))
        {
            return ((Plain) value).getValue();
        }
        if (Placeholder.class.isAssignableFrom(value.getClass()))
        {
            return ((Placeholder) value).getValue(context, indentation);
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
        if (LocalTime.class.isAssignableFrom(value.getClass()))
        {
            return valueApostrophe(prefix + context.getDialect().getTimeFormatter().format((LocalTime) value) + postfix, context);
        }
        return valueApostrophe(prefix + String.valueOf(value) + postfix, context);
    }

    public static String columnApostrophe(String value, BuildingContext context)
    {
        return apostrophe(value, context.getDialect().getLabels().getColumnApostrophe(), context);
    }

    public static String valueApostrophe(String value, BuildingContext context)
    {
        return apostrophe(value, context.getDialect().getLabels().getValueApostrophe(), context);
    }

    private static String apostrophe(String value, char apostrophe, BuildingContext context)
    {
        return apostrophe + context.getDialect().escape(value, apostrophe) + apostrophe;
    }

    public static String buildLikePart(Object value, LikeType likeType, BuildingContext context, Indentation indentation)
    {
        var prefix = likeType == BEFORE || likeType == BOTH ? "%" : "";
        var postfix = likeType == AFTER || likeType == BOTH ? "%" : "";
        return getValued(value, prefix, postfix, context, indentation);
    }
}
