package de.jaggl.sqlbuilder.core.utils;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.domain.Placeholder.placeholder;
import static de.jaggl.sqlbuilder.core.functions.Function.now;
import static de.jaggl.sqlbuilder.core.utils.BuilderUtils.getValued;
import static de.jaggl.sqlbuilder.core.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.domain.Plain;
import de.jaggl.sqlbuilder.core.schema.Schema;

class BuilderUtilsTest
{
    @Test
    void testGetValued()
    {
        var context = new BuildingContext(MYSQL, "");
        var forename = Schema.create("dba").table("persons").varCharColumn("forename").size(50).noDefault().build();

        assertThat(getValued(null, context, disabled())).isEqualTo("NULL");
        assertThat(getValued(forename, context, disabled())).isEqualTo("`dba`.`persons`.`forename`");
        assertThat(getValued(now(), context, disabled())).isEqualTo("NOW()");
        assertThat(getValued(new Plain("plain"), context, disabled())).isEqualTo("plain");
        assertThat(getValued(placeholder(), context, disabled())).isEqualTo("?");
        assertThat(getValued(Integer.valueOf(8), context, disabled())).isEqualTo("8");
        assertThat(getValued(Long.valueOf(8), context, disabled())).isEqualTo("8");
        assertThat(getValued(Double.valueOf(55.8), context, disabled())).isEqualTo("55.8");
        assertThat(getValued(Float.valueOf(55.8f), context, disabled())).isEqualTo("55.8");
        assertThat(getValued(LocalDate.of(2019, 12, 27), context, disabled())).isEqualTo("'2019-12-27'");
        assertThat(getValued(LocalDateTime.of(2019, 12, 27, 19, 38, 5, 234567), context, disabled())).isEqualTo("'2019-12-27 19:38:05.234567'");
        assertThat(getValued(LocalTime.of(19, 38, 5, 234567), context, disabled())).isEqualTo("'19:38:05.234567'");
        assertThat(getValued("anyValue", context, disabled())).isEqualTo("'anyValue'");
    }
}
