package de.jaggl.sqlbuilder.dialect;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Labels
{
    default String getSelect()
    {
        return DefaultLabels.SELECT;
    }

    default String getUpdate()
    {
        return DefaultLabels.UPDATE;
    }

    default String getInsertInto()
    {
        return DefaultLabels.INSERT_INTO;
    }

    default String getDelete()
    {
        return DefaultLabels.DELETE;
    }

    default String getCreateTable()
    {
        return DefaultLabels.CREATE_TABLE;
    }

    default String getNull()
    {
        return DefaultLabels.NULL;
    }

    default String getAnd()
    {
        return DefaultLabels.AND;
    }

    default String getOr()
    {
        return DefaultLabels.OR;
    }

    default String getNot()
    {
        return DefaultLabels.NOT;
    }

    default String getIsEqualTo()
    {
        return DefaultLabels.IS_EQUAL_TO;
    }

    default String getIsNotEqualTo()
    {
        return DefaultLabels.IS_NOT_EQUAL_TO;
    }

    default String getIsGreaterThan()
    {
        return DefaultLabels.IS_GREATER_THAN;
    }

    default String getIsGreaterThanOrEqualTo()
    {
        return DefaultLabels.IS_GREATER_THAN_OR_EQUAL_TO;
    }

    default String getIsLessThan()
    {
        return DefaultLabels.IS_LESS_THAN;
    }

    default String getIsLessThanOrEqualTo()
    {
        return DefaultLabels.IS_LESS_THAN_OR_EQUAL_TO;
    }

    default String getIsBetween()
    {
        return DefaultLabels.IS_BETWEEN;
    }

    default String getIsIn()
    {
        return DefaultLabels.IS_IN;
    }

    default String getIsNotIn()
    {
        return DefaultLabels.IS_NOT_IN;
    }

    default String getIsLike()
    {
        return DefaultLabels.IS_LIKE;
    }

    default String getIsNotLike()
    {
        return DefaultLabels.IS_NOT_LIKE;
    }

    default String getIsNull()
    {
        return DefaultLabels.IS_NULL;
    }

    default String getIsNotNull()
    {
        return DefaultLabels.IS_NOT_NULL;
    }

    default char getColumnApostrophe()
    {
        return DefaultLabels.COLUMN_APOSTROPHE;
    }

    default char getValueApostrophe()
    {
        return DefaultLabels.VALUE_APOSTROPHE;
    }

    default String getWhere()
    {
        return DefaultLabels.WHERE;
    }

    default String getHaving()
    {
        return DefaultLabels.HAVING;
    }

    default String getSet()
    {
        return DefaultLabels.SET;
    }

    default String getFrom()
    {
        return DefaultLabels.FROM;
    }

    default String getLimit()
    {
        return DefaultLabels.LIMIT;
    }

    default String getGroupBy()
    {
        return DefaultLabels.GROUP_BY;
    }

    default String getOrderBy()
    {
        return DefaultLabels.ORDER_BY;
    }

    default String getDistinct()
    {
        return DefaultLabels.DISTINCT;
    }

    default String getAs()
    {
        return DefaultLabels.AS;
    }
}
