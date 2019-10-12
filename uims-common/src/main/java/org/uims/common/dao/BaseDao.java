package org.uims.common.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    int insertOne(T t);

    T quaryOneById(int id);

    int updateOneByObject(T t);

    int deleteOneById(int id);

    List<T> getListPage(Map<String,Object> map);

    List<T> getList(Map<String,Object> map);
}
