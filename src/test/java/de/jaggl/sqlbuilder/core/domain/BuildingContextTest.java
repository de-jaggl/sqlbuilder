package de.jaggl.sqlbuilder.core.domain;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.utils.Indentation.enabled;
import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.domain.BuildingContext;

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
