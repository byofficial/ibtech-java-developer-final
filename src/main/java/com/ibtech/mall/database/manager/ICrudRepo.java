package com.ibtech.mall.database.manager;

import java.util.List;

public interface ICrudRepo<T> {
    boolean save(T t);
    boolean update(T t, long id);

    List<T> findAll();

    T findById(long id);

    boolean delete(long id);

}
