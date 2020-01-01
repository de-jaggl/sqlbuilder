package de.jaggl.sqlbuilder.dialect;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class MySqlDialect extends DefaultDialect
{
    private static final MySqlDialect instance;

    static
    {
        instance = new MySqlDialect();
        Dialect.register(instance);
    }

    private MySqlDialect()
    {
        // private constructor to hide the public one
    }

    @Override
    public String getName()
    {
        return "MySQL";
    }

    public static MySqlDialect getInstance()
    {
        return instance;
    }
}
