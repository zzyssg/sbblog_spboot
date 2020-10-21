package com.zzy.sbblog.handler;

import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * 全局异常捕获处理
     *
     * @param e
     * @return
     **/

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(HttpServletRequest request, CommonException e) throws Exception {
        logger.error("request url : {},e :{}", request.getRequestURI(), e.getStackTrace());

        ResponseBean responseBean = new ResponseBean(e.getRetCode(),e.getRetMsg(), e);
        return responseBean;
    }


}
