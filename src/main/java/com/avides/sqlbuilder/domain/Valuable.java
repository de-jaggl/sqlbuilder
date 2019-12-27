package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.utils.Indentation;

public interface Valuable
{
    String getValue(BuildingContext context, Indentation indentation);
}
