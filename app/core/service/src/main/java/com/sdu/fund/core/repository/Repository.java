package com.sdu.fund.core.repository;

import com.sdu.fund.common.result.Result;

/**
 * @program: fundproduct
 * @description: 仓储层基类
 * @author: anonymous
 * @create: 2019-11-29 17:55
 **/
public interface Repository<T,K> {

    public T get(K primaryKey);

    public void add(T entity);

    public void update(T entity);

    public void delete(K primaryKey);
}
