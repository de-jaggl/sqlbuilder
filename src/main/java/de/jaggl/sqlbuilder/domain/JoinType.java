package de.jaggl.sqlbuilder.domain;

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

    private JoinType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
