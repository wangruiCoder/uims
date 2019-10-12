package org.uims.common.result;

/**
 * 含有数据的统一结果集
 * @author kyrie
 * @since 1.0
 */
public class HasDataResult extends Result {
    private Object data;

    public HasDataResult(String code, String message){
        super(code, message);
    }

    public HasDataResult(String code, String message, Object data){
        super(code, message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
