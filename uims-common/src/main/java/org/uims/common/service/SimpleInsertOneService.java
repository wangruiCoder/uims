package org.uims.common.service;

/**
 * 统一公用的插入单个
 * @author kyrie
 * @param <T>
 */
public interface SimpleInsertOneService<T> {
    int insertOne(T t);
}
