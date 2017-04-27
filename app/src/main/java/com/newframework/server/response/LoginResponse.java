package com.newframework.server.response;

/**
 * Created by MyPC on 2017/4/26.
 */

public class LoginResponse {

    private String docId;
    private String token;
    private ResultEntity result;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }


    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        private long code;
        private String message;

        public long getCode() {
            return code;
        }

        public void setCode(long code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
