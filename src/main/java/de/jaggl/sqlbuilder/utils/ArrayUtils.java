package de.jaggl.sqlbuilder.utils;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public interface ArrayUtils
{
    public static <T> List<T> toList(T value, T[] furtherValues)
    {
        List<T> list = new ArrayList<>(furtherValues.length + 1);
        list.add(value);
        list.addAll(asList(furtherValues));
        return list;
    }
}
