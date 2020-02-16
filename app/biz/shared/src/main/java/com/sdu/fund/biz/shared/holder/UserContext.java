package com.sdu.fund.biz.shared.holder;

import com.sdu.fund.core.model.account.bo.User;
import org.springframework.stereotype.Component;

/**
 * @program: fundtrade
 * @description: 用户信息上下文
 * @author: anonymous
 * @create: 2020/2/15 17:10
 **/
@Component
public class UserContext {

    private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    public User getCurrentUser() {
        return USER_HOLDER.get();
    }

    public void setCurrentUser(User currentUser) {
        USER_HOLDER.set(currentUser);
    }

    public void removeCurrentUser() {
        USER_HOLDER.remove();
    }
}
