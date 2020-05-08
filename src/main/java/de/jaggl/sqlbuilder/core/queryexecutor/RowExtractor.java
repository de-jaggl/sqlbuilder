package de.jaggl.sqlbuilder.core.queryexecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface RowExtractor<T>
{
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
