package com.sdu.fund.biz.shared.query.serviceImpl;


import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sdu.fund.biz.shared.query.service.HoldPositionQueryService;
import com.sdu.fund.biz.shared.vo.HoldPositionsVO;
import com.sdu.fund.core.model.trade.bo.HoldPosition;
import com.sdu.fund.core.repository.HoldPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/3/4 12:47
 **/
public class HoldPositionQueryServiceImpl implements HoldPositionQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserQueryServiceImpl.class);

    @SofaReference
    private HoldPositionRepository holdPositionRepository;

    @Override
    public HoldPositionsVO queryHoldPosition(long userId) {
        // sql中已按照fundCode，gmtModified排序
        List<HoldPosition> holdPositions = holdPositionRepository.getByUserId(userId);
        // 按fundCode分类
        Map<String,HoldPosition> holdPositionsOrderByFundCode = Maps.newLinkedHashMap();
        HoldPosition holdPositionExist;
        for(HoldPosition holdPosition:holdPositions){
            holdPositionExist = holdPositionsOrderByFundCode.get(holdPosition.getFundCode());
            if(holdPositionExist == null){
                holdPositionsOrderByFundCode.put(holdPosition.getFundCode(),holdPosition);
            }else{
                holdPositionExist.setHoldAmount(holdPositionExist.getHoldAmount().add(holdPosition.getHoldAmount()));
            }
        }

        return HoldPositionsVO.convert(holdPositions);
    }
}
