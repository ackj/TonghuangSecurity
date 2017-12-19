package com.meilun.security.smart.entity.bean;

import java.io.Serializable;

/**
 * Created by leguang on 2017/7/30 0030.
 * 联系邮箱:langmanleguang@qq.com
 */
public class ResultBean implements Serializable {
    private static final long serialVersionUID = 3251981777080320167L;
    int Operation;
    int Request_Type;
    int Result;

    public ResultBean() {
    }

    public int getOperation() {
        return this.Operation;
    }

    public void setOperation(int operation) {
        this.Operation = operation;
    }

    public int getRequest_Type() {
        return this.Request_Type;
    }

    public void setRequest_Type(int request_Type) {
        this.Request_Type = request_Type;
    }

    public int getResult() {
        return this.Result;
    }

    public void setResult(int result) {
        this.Result = result;
    }
}
