package de.jaggl.sqlbuilder.schema;

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

    public Table table(String tableName)
    {
        return new Table(this, tableName);
    }

    public static Schema create(String name)
    {
        return new Schema(name);
    }
}
