package com.ibtech.mall.database.manager;

import java.util.List;

public interface ICrudRepo<T> {
    boolean save(T t);

    List<T> findAll();

    T findById(long id);

    void delete(long id);

}
