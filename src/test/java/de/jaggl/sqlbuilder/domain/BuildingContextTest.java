package de.jaggl.sqlbuilder.domain;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BuildingContextTest
{
    @Test
    void testAll()
    {
        var context = new BuildingContext(MYSQL, enabled().getDelimiter());
        System.out.println(context);
        assertThat(context.getDialect()).isSameAs(MYSQL);
        assertThat(context.getDelimiter()).isEqualTo(lineSeparator());
    }
}
