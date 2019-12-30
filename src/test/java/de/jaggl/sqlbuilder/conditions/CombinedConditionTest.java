package de.jaggl.sqlbuilder.conditions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CombinedConditionTest
{
    @Test
    public void testGetCopy()
    {
        assertThat(CombinedCondition.getCopy(null)).isNull();
    }
}
