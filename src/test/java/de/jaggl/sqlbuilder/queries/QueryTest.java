package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static de.jaggl.sqlbuilder.schema.Table.create;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;

import org.junit.jupiter.api.Test;

class QueryTest
{
    @Test
    void testPrintAndPrintln()
    {
        var query = select().from(create("table"));

        query.print();
        System.out.println();
        query.println();
        query.print(MYSQL);
        System.out.println();
        query.println(MYSQL);
        query.print("MYSQL");
        System.out.println();
        query.println("MYSQL");
        query.print(enabled());
        System.out.println();
        query.println(enabled());
        query.print(MYSQL, enabled());
        System.out.println();
        query.println(MYSQL, enabled());
        query.print("MYSQL", enabled());
        System.out.println();
        query.println("MYSQL", enabled());

        query.print(System.out);
        System.out.println();
        query.println(System.out);
        query.print(System.out, MYSQL);
        System.out.println();
        query.println(System.out, MYSQL);
        query.print(System.out, "MYSQL");
        System.out.println();
        query.println(System.out, "MYSQL");
        query.print(System.out, enabled());
        System.out.println();
        query.println(System.out, enabled());
        query.print(System.out, MYSQL, enabled());
        System.out.println();
        query.println(System.out, MYSQL, enabled());
        query.print(System.out, "MYSQL", enabled());
        System.out.println();
        query.println(System.out, "MYSQL", enabled());
    }
}
