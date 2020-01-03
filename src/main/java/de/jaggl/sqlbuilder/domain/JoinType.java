package de.jaggl.sqlbuilder.domain;

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
public enum JoinType
{
    LEFT("LEFT"),
    LEFT_OUTER("LEFT OUTER"),
    RIGHT("RIGHT"),
    RIGHT_OUTER("RIGHT OUTER"),
    INNER("INNER"),
    OUTER("OUTER"),
    FULL_OUTER("FULL OUTER");

    private String value;
}
