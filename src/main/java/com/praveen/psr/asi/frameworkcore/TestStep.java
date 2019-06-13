package com.praveen.psr.asi.frameworkcore;

import java.util.ArrayList;
import java.util.HashMap;

public class TestStep {

    public static HashMap<String, TestStep> GenericTestStepsMap = new HashMap<String, TestStep>();
    public static HashMap<String, ArrayList<String>> TestModulesMap = new HashMap<String, ArrayList<String>>();

    private String stepNo = "1";
    private String objKey;
    private String actionKeyword;
    private String reqParam;
    private String MappedTCnumber;

    public String getMappedTCnumber() {
        return MappedTCnumber;
    }

    public void setMappedTCnumber(String mappedTCnumber) {
        MappedTCnumber = mappedTCnumber;
    }

    public String getStepNo() {
        return stepNo;
    }

    public void setStepNo(String stepNo) {
        this.stepNo = stepNo;
    }

    public String getObjKey() {
        return objKey;
    }

    public void setObjKey(String objKey) {
        this.objKey = objKey;
    }

    public String getActionKeyword() {
        return actionKeyword;
    }

    public void setActionKeyword(String actionKeyword) {
        this.actionKeyword = actionKeyword;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

}
