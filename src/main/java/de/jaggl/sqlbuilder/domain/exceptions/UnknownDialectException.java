package de.jaggl.sqlbuilder.domain.exceptions;

public class UnknownDialectException extends RuntimeException
{
    private static final long serialVersionUID = -3030241047976213273L;

    public UnknownDialectException(String message)
    {
        super(message);
    }
}
