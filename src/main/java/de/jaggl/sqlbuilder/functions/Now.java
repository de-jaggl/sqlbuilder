package de.jaggl.sqlbuilder.functions;

import static java.sql.Types.TIMESTAMP;

import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

import de.jaggl.sqlbuilder.conditions.DateTimeConditions;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Now implements Function, DateTimeConditions
{
    @Override
    public java.util.function.Function<ZonedDateTime, Temporal> getDateConversion()
    {
        return ZonedDateTime::toLocalDateTime;
    }

    @Getter
    private String alias;

    public Now as(@SuppressWarnings("hiding") String alias)
    {
        return new Now(alias);
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return "NOW()";
    }

    @Override
    public int getSqlType()
    {
        return TIMESTAMP;
    }
}
