package de.jaggl.sqlbuilder.core.schema;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor(access = PRIVATE)
@Getter
@ToString
public class Schema
{
    private String name;

    /**
     * Creates a {@link Table} with the given name in the current {@link Schema}
     *
     * @param tableName the name for the {@link Table}
     * @return the created {@link Table}
     */
    public Table table(String tableName)
    {
        return new Table(this, tableName);
    }

    /**
     * Creates a {@link Schema} with the given name
     *
     * @param name the name for the {@link Schema}
     * @return the created {@link Schema}
     */
    public static Schema create(String name)
    {
        return new Schema(name);
    }
}
