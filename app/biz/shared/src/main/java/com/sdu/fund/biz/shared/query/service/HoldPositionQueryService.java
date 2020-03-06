package com.sdu.fund.biz.shared.query.service;

import com.sdu.fund.biz.shared.vo.HoldPositionsVO;

/**
 * @program: fundtrade
 * @description: 持仓服务类
 * @author: anonymous
 * @create: 2020/3/4 12:46
 **/
public interface HoldPositionQueryService {

    public HoldPositionsVO queryHoldPosition(long userId);

}
