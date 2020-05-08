package de.jaggl.sqlbuilder.core.conditions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.conditions.CombinedCondition;

class CombinedConditionTest
{
    @Test
    void testGetCopy()
    {
        assertThat(CombinedCondition.getCopy(null)).isNull();
    }
}
