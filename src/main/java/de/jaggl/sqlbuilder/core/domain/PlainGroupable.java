package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.utils.Indentation;
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
