package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.utils.Indentation.disabled;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.3.0
 */
public interface Query
{
    String build(Dialect dialect, Indentation indentation);

    default String build()
    {
        return build(Dialect.getDefault());
    }

    default String build(Indentation indentation)
    {
        return build(Dialect.getDefault(), indentation);
    }

    default String build(Dialect dialect)
    {
        return build(dialect, disabled());
    }

    default String build(String dialectName)
    {
        return build(Dialect.forName(dialectName));
    }

    default String build(String dialectName, Indentation indentation)
    {
        return build(Dialect.forName(dialectName), indentation);
    }

    default void print()
    {
        print(System.out); // NOSONAR
    }

    default void print(Indentation indentation)
    {
        print(System.out, indentation); // NOSONAR
    }

    default void print(Dialect dialect)
    {
        print(System.out, dialect); // NOSONAR
    }

    default void print(String dialectName)
    {
        print(Dialect.forName(dialectName));
    }

    default void print(Dialect dialect, Indentation indentation)
    {
        print(System.out, dialect, indentation); // NOSONAR
    }

    default void print(String dialectName, Indentation indentation)
    {
        print(Dialect.forName(dialectName), indentation);
    }

    default void print(PrintStream printStream)
    {
        printStream.print(build());
    }

    default void print(PrintStream printStream, Indentation indentation)
    {
        printStream.print(build(indentation));
    }

    default void print(PrintStream printStream, Dialect dialect)
    {
        printStream.print(build(dialect));
    }

    default void print(PrintStream printStream, String dialectName)
    {
        print(printStream, Dialect.forName(dialectName));
    }

    default void print(PrintStream printStream, Dialect dialect, Indentation indentation)
    {
        printStream.print(build(dialect, indentation));
    }

    default void print(PrintStream printStream, String dialectName, Indentation indentation)
    {
        print(printStream, Dialect.forName(dialectName), indentation);
    }

    default void println()
    {
        println(System.out); // NOSONAR
    }

    default void println(Indentation indentation)
    {
        println(System.out, indentation); // NOSONAR
    }

    default void println(Dialect dialect)
    {
        println(System.out, dialect); // NOSONAR
    }

    default void println(String dialectName)
    {
        println(Dialect.forName(dialectName));
    }

    default void println(Dialect dialect, Indentation indentation)
    {
        println(System.out, dialect, indentation); // NOSONAR
    }

    default void println(String dialectName, Indentation indentation)
    {
        println(Dialect.forName(dialectName), indentation);
    }

    default void println(PrintStream printStream)
    {
        printStream.println(build());
    }

    default void println(PrintStream printStream, Indentation indentation)
    {
        printStream.println(build(indentation));
    }

    default void println(PrintStream printStream, Dialect dialect)
    {
        printStream.println(build(dialect));
    }

    default void println(PrintStream printStream, String dialectName)
    {
        println(printStream, Dialect.forName(dialectName));
    }

    default void println(PrintStream printStream, Dialect dialect, Indentation indentation)
    {
        printStream.println(build(dialect, indentation));
    }

    default void println(PrintStream printStream, String dialectName, Indentation indentation)
    {
        println(printStream, Dialect.forName(dialectName), indentation);
    }

    default PreparedStatement prepare(Connection connection) throws SQLException
    {
        return connection.prepareStatement(build());
    }

    default PreparedStatement prepare(Connection connection, Dialect dialect) throws SQLException
    {
        return connection.prepareStatement(build(dialect));
    }

    default PreparedStatement prepare(Connection connection, String dialectName) throws SQLException
    {
        return connection.prepareStatement(build(dialectName));
    }
}
