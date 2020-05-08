package de.jaggl.sqlbuilder.core.dialect;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.dialect.Dialects.SYBASE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.dialect.Dialect;
import de.jaggl.sqlbuilder.core.domain.exceptions.UnknownDialectException;

class DialectTest
{
    @Test
    void testForNameRegisterAndUnregister()
    {
        assertThat(Dialect.forName("MYSQL")).isSameAs(MYSQL);
        assertThat(Dialect.forName("SYBASE")).isSameAs(SYBASE);

        Dialect.unregister("MYSQL");

        assertThatThrownBy(() -> Dialect.forName("MYSQL")).isInstanceOf(UnknownDialectException.class)
                .hasMessage("no dialect registered for name 'MYSQL'");
        assertThat(Dialect.forName("SYBASE")).isSameAs(SYBASE);

        Dialect.register(MYSQL);

        assertThat(Dialect.forName("MYSQL")).isSameAs(MYSQL);
        assertThat(Dialect.forName("SYBASE")).isSameAs(SYBASE);
    }
}
