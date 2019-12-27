package com.avides.sqlbuilder.dialect;

public class DefaultLabels implements Labels
{
    private static DefaultLabels instance;

    static final char COLUMN_APOSTROPHE = '`';
    static final char VALUE_APOSTROPHE = '\'';

    static final String SELECT = "SELECT";
    static final String UPDATE = "UPDATE";
    static final String INSERT_INTO = "INSERT INTO";
    static final String DELETE = "DELETE";
    static final String CREATE_TABLE = "CREATE TABLE";

    static final String NULL = "NULL";
    static final String AND = "AND";
    static final String OR = "OR";
    static final String NOT = "NOT";

    static final String WHERE = "WHERE";
    static final String HAVING = "HAVING";
    static final String SET = "SET";
    static final String FROM = "FROM";
    static final String LIMIT = "LIMIT";
    static final String ORDER_BY = "ORDER BY";
    static final String GROUP_BY = "GROUP BY";
    static final String DISTINCT = "DISTINCT";
    static final String AS = "AS";

    static final String IS_EQUAL_TO = "{0} = {1}";
    static final String IS_NOT_EQUAL_TO = "{0} != {1}";
    static final String IS_GREATER_THAN = "{0} > {1}";
    static final String IS_GREATER_THAN_OR_EQUAL_TO = "{0} >= {1}";
    static final String IS_LESS_THAN = "{0} < {1}";
    static final String IS_LESS_THAN_OR_EQUAL_TO = "{0} <= {1}";
    static final String IS_BETWEEN = "{0} IS BETWEEN {1} AND {2}";
    static final String IS_IN = "{0} IN ({1})";
    static final String IS_NOT_IN = "{0} NOT IN ({1})";
    static final String IS_LIKE = "{0} LIKE {1}";
    static final String IS_NOT_LIKE = "{0} NOT LIKE {1}";
    static final String IS_NULL = "{0} IS NULL";
    static final String IS_NOT_NULL = "{0} IS NOT NULL";

    static
    {
        instance = new DefaultLabels();
    }

    private DefaultLabels()
    {
        // private constructor to hide the public one
    }

    public static DefaultLabels getInstance()
    {
        return instance;
    }
}
