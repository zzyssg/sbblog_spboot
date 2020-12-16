package com.zzy.sbblog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@ToString
public class ResponseBean implements Serializable {

   private String retCode;
   private String retMsg;
   private Object result;

    public ResponseBean(String retCode, String retMsg, Object result) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.result = result;
    }

    public ResponseBean() {
    }
}
