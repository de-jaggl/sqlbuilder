package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static java.sql.Types.TIMESTAMP;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.OtherColumnTestSupport;

class NowTest implements OtherColumnTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testNow()
    {
        assertThat(select(now().as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT NOW() AS `alias` FROM `table`");

        assertThat(select().from(TABLE)
                .where(Function.now().isBefore(Date.from(LocalDateTime.of(2020, 1, 5, 19, 41, 54).atZone(ZoneId.systemDefault()).toInstant())))
                .build(MYSQL)).isEqualTo("SELECT * FROM `table` WHERE NOW() < '2020-01-05 19:41:54.000000'");
    }

    @Test
    void testGetSqlType()
    {
        assertThat(now().getSqlType()).isEqualTo(TIMESTAMP);
    }
}
