package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.dialect.Dialect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@Getter
@ToString
public class BuildingContext
{
    private Dialect dialect;
    private String delimiter;
}
