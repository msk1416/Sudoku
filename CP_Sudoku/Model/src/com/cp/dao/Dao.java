package com.cp.dao;

import com.cp.exception.CPFileException;

public interface Dao<T> {
    public T read() throws CPFileException;
    public void write(T obj) throws CPFileException;
}
