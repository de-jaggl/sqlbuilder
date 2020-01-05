package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

public interface NullableConditions
{
    default Condition isNull()
    {
        return new GenericCondition(IS_NULL, this);
    }

    default Condition isNotNull()
    {
        return new GenericCondition(IS_NOT_NULL, this);
    }

}
