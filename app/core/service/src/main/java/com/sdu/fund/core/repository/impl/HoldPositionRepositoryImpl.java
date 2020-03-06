package com.sdu.fund.core.repository.impl;

import com.google.common.collect.Lists;
import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.entity.HoldPositionDo;
import com.sdu.fund.common.dal.extMapper.ExtHoldPositionsMapper;
import com.sdu.fund.common.dal.mapper.HoldPositionsMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.HoldPositionConverter;
import com.sdu.fund.core.model.trade.bo.HoldPosition;
import com.sdu.fund.core.repository.HoldPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * @program: fundproduct
 * @description: 持仓信息仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class HoldPositionRepositoryImpl implements HoldPositionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HoldPositionRepositoryImpl.class);

    @Autowired
    private HoldPositionsMapper holdPositionsMapper;

    @Autowired
    private ExtHoldPositionsMapper extHoldPositionsMapper;

    @Override
    public HoldPosition get(Long id) {
        Validator.notNull(id);
        try {
            HoldPosition holdPosition =
                    HoldPositionConverter.HoldPositionDoconvert2HoldPosition(holdPositionsMapper.selectByPrimaryKey(id));
            return holdPosition;
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息查询失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息查询失败，id={},errCode={}", id, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息查询失败");
        }
    }

    @Override
    public void add(HoldPosition holdPosition) {
        try {
            // 预校验
            preCheck(holdPosition);
            int id =
                    holdPositionsMapper.insertSelective(HoldPositionConverter.HoldPositionconvert2HoldPositionDo(holdPosition));
            if (id <= 0) {
                LOGGER.error("持仓信息插入失败，id={},errCode={}", holdPosition.getId(), ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("持仓信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息插入失败，id={},errCode={}", holdPosition.getId(), ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息插入失败，id={},errCode={}", holdPosition.getId(), ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息插入失败");
        }
    }

    @Override
    public void update(HoldPosition holdPosition) {
        try {
            // 预校验
            preCheck(holdPosition);
            int count =
                    holdPositionsMapper.updateByPrimaryKeySelective(HoldPositionConverter.HoldPositionconvert2HoldPositionDo(holdPosition));
            if (count <= 0) {
                LOGGER.error("持仓信息更新失败，id={},errCode={}", holdPosition.getId(), ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("持仓信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息更新失败，id={},errCode={}", holdPosition.getId(), ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息更新失败，id={},errCode={}", holdPosition.getId(), ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息更新失败");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            int count = holdPositionsMapper.deleteByPrimaryKey(id);
            if (count <= 0) {
                LOGGER.error("持仓信息删除失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("持仓信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息删除失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息删除失败，id={},errCode={}", id, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(HoldPosition holdPosition) {
        Validator.notNull(holdPosition);
        Validator.notNull(holdPosition.getId());
    }

    @Override
    public HoldPosition lock(Long id) {
        Validator.notNull(id);
        try {
            return HoldPositionConverter.HoldPositionDoconvert2HoldPosition(extHoldPositionsMapper.lockByPrimaryKey(id));
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息锁定失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息锁定失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息锁定失败，id={},errCode={}", id, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息锁定失败");
        }
    }

    @Override
    public List<HoldPosition> getByUserId(Long userId) {
        Validator.notNull(userId);
        try {
            List<HoldPositionDo> holdPositionDos = extHoldPositionsMapper.selectByUserId(userId);
            List<HoldPosition> holdPositions = Lists.newArrayList();
            for (HoldPositionDo holdPositionDo : holdPositionDos) {
                holdPositions.add(HoldPositionConverter.HoldPositionDoconvert2HoldPosition(holdPositionDo));
            }
            return holdPositions;
        } catch (DataAccessException e1) {
            LOGGER.error("根据userId查询持仓信息失败，userId={},errCode={},msg={}", userId, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("根据userId查询持仓信息失败");
        } catch (Exception e2) {
            LOGGER.error("根据userId查询持仓信息失败，userId={},errCode={},msg={}", userId, ResultCode.SERVER_EXCEPTION,
                    e2.getMessage());
            throw new CommonException("根据userId查询持仓信息失败");
        }
    }

    @Override
    public List<HoldPosition> getByUserIdAndFundCode(Long userId, String fundCode) {
        Validator.notNull(userId);
        Validator.notNull(fundCode);
        try {
            List<HoldPositionDo> holdPositionDos = extHoldPositionsMapper.selectByUserIdAndFundCode(userId, fundCode);
            List<HoldPosition> holdPositions = Lists.newArrayList();
            for (HoldPositionDo holdPositionDo : holdPositionDos) {
                holdPositions.add(HoldPositionConverter.HoldPositionDoconvert2HoldPosition(holdPositionDo));
            }
            return holdPositions;
        } catch (DataAccessException e1) {
            LOGGER.error("根据userId,fundCode查询持仓信息失败，userId={},errCode={},msg={}", userId,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("根据userId,fundCode查询持仓信息失败");
        } catch (Exception e2) {
            LOGGER.error("根据userId,fundCode查询持仓信息失败，userId={},errCode={},msg={}", userId, ResultCode.SERVER_EXCEPTION
                    , e2.getMessage());
            throw new CommonException("根据userId,fundCode查询持仓信息失败");
        }
    }

    @Override
    public void addIgnore(HoldPosition holdPosition) {
        try {
            // 预校验
            preCheck(holdPosition);
            int id =
                    extHoldPositionsMapper.insertSelectiveIgnore(HoldPositionConverter.HoldPositionconvert2HoldPositionDo(holdPosition));
            if (id == 0) {
                LOGGER.info("持仓信息并发插入，id={}", holdPosition.getId());
            }
        } catch (DataAccessException e1) {
            LOGGER.error("持仓信息插入失败，id={},errCode={}", holdPosition.getId(), ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("持仓信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("持仓信息插入失败，id={},errCode={}", holdPosition.getId(), ResultCode.SERVER_EXCEPTION);
            throw new CommonException("持仓信息插入失败");
        }
    }

    @Override
    public void makeValid(Long id) {
        Validator.notNull(id);
        try {
            int count =
                    extHoldPositionsMapper.makeValid(id);
            if (count <= 0) {
                LOGGER.error("使持仓信息合法失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("使持仓信息合法失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("使持仓信息合法失败，id={},errCode={},msg={}", id, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("使持仓信息合法失败");
        } catch (Exception e2) {
            LOGGER.error("使持仓信息合法失败，id={},errCode={},msg={}", id, ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("使持仓信息合法失败");
        }
    }

    @Override
    public void makeInValid(Long id) {
        Validator.notNull(id);
        try {
            int count =
                    extHoldPositionsMapper.makeInValid(id);
            if (count <= 0) {
                LOGGER.error("使持仓信息非法失败，id={},errCode={}", id, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("使持仓信息非法失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("使持仓信息非法失败，id={},errCode={},msg={}", id, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("使持仓信息非法失败");
        } catch (Exception e2) {
            LOGGER.error("使持仓信息非法失败，id={},errCode={},msg={}", id, ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("使持仓信息非法失败");
        }
    }
}
