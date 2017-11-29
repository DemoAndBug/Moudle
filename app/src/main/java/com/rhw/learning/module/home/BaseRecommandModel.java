package com.rhw.learning.module.home;


import com.rhw.learning.module.BaseModel;
/**
 * Author:renhongwei
 * Date:2017/11/26
 */
public class BaseRecommandModel extends BaseModel {

    public String ecode;
    public String emsg;
    public RecommandModel data;

    @Override
    public String toString() {
        return "BaseRecommandModel{" +
                "ecode='" + ecode + '\'' +
                ", emsg='" + emsg + '\'' +
                ", data=" + data +
                '}';
    }
}