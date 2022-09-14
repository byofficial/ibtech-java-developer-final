package com.ibtech.mall.database.manager;

import com.ibtech.mall.database.connection.DbCon;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<E> extends DbCon {
    protected List<E> parseList(ResultSet resultSet) throws Exception {
        List<E> entityList = new ArrayList<>();
        while (resultSet.next()) {
            E entity = parse(resultSet);
            entityList.add(entity);
        }
        return entityList;
    }

    protected abstract E parse(ResultSet resultSet) throws Exception;

}
