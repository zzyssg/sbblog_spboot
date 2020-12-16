package com.zzy.sbblog.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonException extends RuntimeException {

    private String retCode;
    private String retMsg;

}
