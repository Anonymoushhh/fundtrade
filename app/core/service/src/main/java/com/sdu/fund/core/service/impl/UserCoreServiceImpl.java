package com.sdu.fund.core.service.impl;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.SnowflakeIdUtil;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.UserStatusEnum;
import com.sdu.fund.core.repository.UserAccountRepository;
import com.sdu.fund.core.repository.UserRepository;
import com.sdu.fund.core.service.UserCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class UserCoreServiceImpl implements UserCoreService {

    private static final Logger LOG = LoggerFactory.getLogger(UserCoreServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public User login(User user) {
        // TODO 暂未有业务逻辑
        return user;
    }

    @Override
    public User register(User user) {
        // 生成用户id
        user.setUserId(SnowflakeIdUtil.getInstance().nextId());
        // 设置用户权限级别，当前默认为普通用户
        user.setAuthority(AuthorityEnum.CONSUMER);
        // 设置用户状态为合法
        user.setStatus(UserStatusEnum.VALID);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 初始化用户和账户
                userRepository.add(user);
                UserAccount userAccount = UserAccount.initUserAccount(user.getUserId());
                userAccountRepository.add(userAccount);
            }
        });

        return user;
    }
}
