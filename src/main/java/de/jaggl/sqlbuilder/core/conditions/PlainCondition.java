package de.jaggl.sqlbuilder.core.conditions;

import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString(callSuper = true)
public class PlainCondition extends Condition
{
    private String value;

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        return value;
    }
}
