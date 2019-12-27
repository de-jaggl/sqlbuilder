package com.avides.sqlbuilder.conditions;

import static java.util.Arrays.asList;

import java.text.MessageFormat;
import java.util.function.Function;

import com.avides.sqlbuilder.dialect.Labels;
import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.utils.BuilderUtils;
import com.avides.sqlbuilder.utils.Indentation;

public class GenericCondition extends Condition
{
    private GenericConditionType type;
    private Object[] values;

    public GenericCondition(GenericConditionType type, Object... values)
    {
        this.type = type;
        this.values = values;
    }

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        return MessageFormat
                .format(type.getRenderingString(context), asList(values).stream().map(value -> BuilderUtils.getValued(value, context, indentation)).toArray());
    }

    public enum GenericConditionType
    {
        IS_EQUAL_TO(Labels::getIsEqualTo),
        IS_NOT_EQUAL_TO(Labels::getIsNotEqualTo),
        IS_GREATER_THAN(Labels::getIsGreaterThan),
        IS_GREATER_THAN_OR_EQUAL_TO(Labels::getIsGreaterThanOrEqualTo),
        IS_LESS_THAN(Labels::getIsLessThan),
        IS_LESS_THAN_OR_EQUAL_TO(Labels::getIsLessThanOrEqualTo),
        IS_LIKE(Labels::getIsLike),
        IS_NOT_LIKE(Labels::getIsNotLike),
        IS_BETWEEN(Labels::getIsBetween),
        IS_NULL(Labels::getIsNull),
        IS_NOT_NULL(Labels::getIsNotNull);

        private Function<Labels, String> renderingStringFunction;

        private GenericConditionType(Function<Labels, String> renderingStringFunction)
        {
            this.renderingStringFunction = renderingStringFunction;
        }

        public String getRenderingString(BuildingContext context)
        {
            return renderingStringFunction.apply(context.getDialect().getLabels());
        }
    }
}
