package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundManagerMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundManagerConverter;
import com.sdu.fund.core.model.trade.bo.FundManager;
import com.sdu.fund.core.repository.FundManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 基金公司仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class FundManagerRepositoryImpl implements FundManagerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundManagerRepositoryImpl.class);

    @Autowired
    private FundManagerMapper fundManagerMapper;

    @Override
    public FundManager get(String managerId) {
        Validator.notNull(managerId);
        try {
            FundManager fundManager =
                    FundManagerConverter.FundManagerDoconvert2FundManager(fundManagerMapper.selectByPrimaryKey(managerId));
            return fundManager;
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息查询失败，managerId={},errCode={}", managerId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金经理信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("基金经理信息查询失败，managerId={},errCode={}", managerId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金经理信息查询失败");
        }
    }

    @Override
    public void add(FundManager fundManager) {
        try {
            // 预校验
            preCheck(fundManager);
            int id = fundManagerMapper.insertSelective(FundManagerConverter.FundManagerconvert2FundManagerDo(fundManager));
            if (id <= 0) {
                LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金经理信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金经理信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金经理信息插入失败");
        }
    }

    @Override
    public void update(FundManager fundManager) {
        try {
            preCheck(fundManager);
            int count =
                fundManagerMapper.updateByPrimaryKeySelective(FundManagerConverter.FundManagerconvert2FundManagerDo(fundManager));
            if (count <= 0) {
                LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金经理信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金经理信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金经理信息更新失败");
        }
    }

    @Override
    public void delete(String managerId) {
        try {
            int count = fundManagerMapper.deleteByPrimaryKey(managerId);
            if (count <= 0) {
                LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金经理信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
                ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金经理信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金经理信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(FundManager fundManager) {
        Validator.notNull(fundManager);
        Validator.notNull(fundManager.getManagerId());
    }
}
