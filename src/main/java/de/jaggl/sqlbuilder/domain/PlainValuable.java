package de.jaggl.sqlbuilder.domain;

import static lombok.AccessLevel.PACKAGE;

import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
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
public class PlainValuable implements Valuable
{
    @Getter(PACKAGE)
    protected Object value;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return BuilderUtils.getValued(value, context, indentation);
    }
}
