package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.utils.Indentation;
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
public class PlainSelectable implements Selectable
{
    private String value;

    @Getter
    private String alias;

    public Selectable as(@SuppressWarnings("hiding") String alias)
    {
        return new PlainSelectable(value, alias);
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return value;
    }
}
