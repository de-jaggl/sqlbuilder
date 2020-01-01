package de.jaggl.sqlbuilder.schema;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Schema
{
    private String name;

    private Schema(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Table table(String tableName)
    {
        return new Table(this, tableName);
    }

    public static Schema create(String name)
    {
        return new Schema(name);
    }
}
