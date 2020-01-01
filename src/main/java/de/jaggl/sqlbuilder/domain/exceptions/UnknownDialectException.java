package de.jaggl.sqlbuilder.domain.exceptions;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class UnknownDialectException extends RuntimeException
{
    private static final long serialVersionUID = -3030241047976213273L;

    public UnknownDialectException(String message)
    {
        super(message);
    }
}
