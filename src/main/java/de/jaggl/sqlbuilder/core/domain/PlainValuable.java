package de.jaggl.sqlbuilder.core.domain;

import static lombok.AccessLevel.PACKAGE;

import de.jaggl.sqlbuilder.core.utils.BuilderUtils;
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
