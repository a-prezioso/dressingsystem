package com.sviluppatoredisuccesso.dressing.dto;

public class GeneralDto {
    private Object rowId;
    private boolean result = false;
    private String msg;

    public GeneralDto(){}
    public GeneralDto(boolean result){this.result=result;}
    public GeneralDto(String msg, boolean result){ this.msg=msg;this.result=result;}
    public GeneralDto(Object rowId, String msg, boolean result){ this.rowId=rowId;this.msg=msg;this.result=result;}

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRowId() {
        return rowId;
    }

    public void setRowId(Object rowId) {
        this.rowId = rowId;
    }

    @Override
    public String toString() {
        return "CommonOperationResultDto{" +
                "rowId=" + rowId +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}
