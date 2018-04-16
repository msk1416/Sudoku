package com.cp.dao;

public interface Dao<T> {
    public T read();
    public void write(T obj);
}
