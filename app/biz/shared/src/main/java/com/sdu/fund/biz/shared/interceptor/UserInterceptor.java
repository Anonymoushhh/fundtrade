package com.sdu.fund.interceptor;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.query.service.UserQueryService;
import com.sdu.fund.holder.UserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    private UserHolder UserHolder;
    @SofaReference
    private UserQueryService userQueryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        UseruseruserQueryService.queryLoginUser(token);
//        Claims claims = TokenUtil.verifyJwt(token); //验证token
//        if (claims == null) {
//            response.getWriter().write("token is invalid");
//        }else {
//            filterChain.doFilter(request,response);
//        }
//        UserAccount currentUser = wycsUserService.getLoginUser(token);
//        LOG.info("UserInterceptor token:{} user:{}",token, JSON.toJSONString(currentUser));
//        if (currentUser == null) {
//            sendNoLoginMsg(response);
//            return false;
//        }
//        UserHolder.setCurrentUser(currentUser);
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        wycsUserHolder.removeCurrentUser();
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        wycsUserHolder.removeCurrentUser();
//    }
//
//    private void sendNoLoginMsg(HttpServletResponse response) throws Exception{
//        String rltJson = JSON.toJSONString(CommonResult.of(CommonRltCode.ERROR_NOLOGIN.getCode(),CommonRltCode.ERROR_NOLOGIN.getMsg(),null));
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-type", "text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(rltJson);
//        out.flush();
//        out.close();
//    }
}
