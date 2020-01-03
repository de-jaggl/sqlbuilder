package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class PlainGroupable implements Groupable
{
    private String value;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return value;
    }
}
