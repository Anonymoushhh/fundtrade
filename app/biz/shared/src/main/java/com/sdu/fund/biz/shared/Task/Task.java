package com.sdu.fund.biz.shared.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/1/31 14:19
 **/
public interface Task {

    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void execute();
}
