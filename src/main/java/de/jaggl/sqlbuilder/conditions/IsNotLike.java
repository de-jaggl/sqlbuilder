package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.utils.BuilderUtils.buildLikePart;
import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;

import java.text.MessageFormat;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString(callSuper = true)
public class IsNotLike extends Condition
{
    private Object value1;
    private Object value2;
    private LikeType likeType;

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        return MessageFormat
                .format(context.getDialect()
                        .getLabels()
                        .getIsNotLike(), getValued(value1, context, indentation), buildLikePart(value2, likeType, context, indentation));
    }
}
