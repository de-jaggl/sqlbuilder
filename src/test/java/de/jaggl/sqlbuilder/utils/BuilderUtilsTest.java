package de.jaggl.sqlbuilder.utils;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;
import static de.jaggl.sqlbuilder.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.schema.Schema;

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
        assertThat(getValued(Integer.valueOf(8), context, disabled())).isEqualTo("8");
        assertThat(getValued(Long.valueOf(8), context, disabled())).isEqualTo("8");
        assertThat(getValued(Double.valueOf(55.8), context, disabled())).isEqualTo("55.8");
        assertThat(getValued(Float.valueOf(55.8f), context, disabled())).isEqualTo("55.8");
        assertThat(getValued(LocalDate.of(2019, 12, 27), context, disabled())).isEqualTo("'2019-12-27'");
        assertThat(getValued(LocalDateTime.of(2019, 12, 27, 19, 38, 5, 234567), context, disabled())).isEqualTo("'2019-12-27 19:38:05.234567'");
        assertThat(getValued("anyValue", context, disabled())).isEqualTo("'anyValue'");
    }
}
