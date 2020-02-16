package com.sdu.fund.biz.shared.interceptor;

import com.alibaba.fastjson.JSON;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.holder.UserContext;
import com.sdu.fund.biz.shared.query.service.UserQueryService;
import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.core.model.account.bo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    private UserContext userContext;
    @SofaReference
    private UserQueryService userQueryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        User currentUser = userQueryService.queryLoginUser(token);
        LOG.info("UserInterceptor token:{} user:{}", token, JSON.toJSONString(currentUser));

        if (currentUser == null) {
            sendNoLoginMsg(response);
            return false;
        }
        userContext.setCurrentUser(currentUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
    modelAndView) throws Exception {
        userContext.removeCurrentUser();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
    ex) throws Exception {
        userContext.removeCurrentUser();
    }

    private void sendNoLoginMsg(HttpServletResponse response) throws Exception {
        String rltJson = JSON.toJSONString(ResultUtil.buildFailedResult(ResultCode.NO_LOGIN));
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(rltJson);
        out.flush();
        out.close();
    }
}
