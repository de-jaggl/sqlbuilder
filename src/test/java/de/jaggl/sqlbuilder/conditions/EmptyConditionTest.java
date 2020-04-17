package de.jaggl.sqlbuilder.conditions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class EmptyConditionTest
{
    @Test
    void testDoBuild()
    {
        assertThatThrownBy(() -> new EmptyCondition().doBuild(null, null)).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("the EmptyCondition is not meant to be build");
    }
}
