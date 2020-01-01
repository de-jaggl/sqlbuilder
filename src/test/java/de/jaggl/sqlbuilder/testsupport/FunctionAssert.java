package de.jaggl.sqlbuilder.testsupport;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.functions.Function;
import de.jaggl.sqlbuilder.utils.Indentation;

public interface FunctionAssert
{
    default AbstractStringAssert<?> assertFunction(Function function)
    {
        var indentation = Indentation.indent(false);
        return assertThat(function.getValue(new BuildingContext(MYSQL, indentation.getDelimiter()), indentation));
    }
}
