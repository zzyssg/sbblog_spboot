package com.zzy.sbblog.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @Date 2020/12/15 15:34
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommonException extends RuntimeException {

    /**
     * 异常状态码
     */
    private String retCode;
    /**
     * 异常信息
     */
    private String retMsg;

}
