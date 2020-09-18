package com.dili.account.common.handler;

import com.dili.account.common.Constant;
import com.dili.account.dto.BaseDto;
import com.dili.account.util.AssertUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 用于获取用户session、验证公共参数、赋值操作员信息
 * @author xuliang
 */
public interface IControllerHandler {

    /**
    *
    * @author miaoguoxin
    * @date 2020/9/18
    */
    default void buildFirmId(BaseDto baseDto){
        Long firmId = getFirmId();
        if (firmId == null){
            AssertUtils.notNull(baseDto.getFirmId(),"市场id不能为空");
        }else {
            baseDto.setFirmId(firmId);
        }
    }

    /**
    *
    * @author miaoguoxin
    * @date 2020/9/18
    */
   default Long getFirmId(){
       RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
       if (requestAttributes == null) {
           return null;
       }
       HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
       Cookie[] cookies = request.getCookies();
       return Arrays.stream(cookies)
               .filter(cookie -> Constant.UAP_FIRMID.equals(cookie.getName()))
               .findFirst()
               .map(c -> Long.valueOf(c.getValue()))
               .orElse(null);
   }
}
