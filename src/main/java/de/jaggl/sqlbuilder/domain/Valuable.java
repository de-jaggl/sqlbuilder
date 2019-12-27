package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;

public interface Valuable
{
    String getValue(BuildingContext context, Indentation indentation);
}
