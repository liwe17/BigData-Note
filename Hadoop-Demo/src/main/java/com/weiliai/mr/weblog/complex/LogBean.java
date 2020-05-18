package com.weiliai.mr.weblog.complex;

/**
 * @Author: Doug Li
 * @Date 2020/5/18
 * @Describe: Log的bean
 */
public class LogBean {

    private String remoteAddr; //记录客户端IP地址

    private String remoteUser; //记录客户端用户名称,忽略属性"-"

    private String timeLocal; //记录访问时间与时区

    private String request; //记录请求的url与http协议

    private String status; //记录请求状态:成功是200

    private String bodyBytesSent; //记录发送给客户端文件主体内容大小

    private String httpReferer; //用来记录从哪个页面连接访问过来的

    private String httpUserAgent; //记录客户浏览器相关信息

    private boolean valid = true; //判断数据是否合法

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public LogBean setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
        return this;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public LogBean setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
        return this;
    }

    public String getTimeLocal() {
        return timeLocal;
    }

    public LogBean setTimeLocal(String timeLocal) {
        this.timeLocal = timeLocal;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public LogBean setRequest(String request) {
        this.request = request;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public LogBean setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getBodyBytesSent() {
        return bodyBytesSent;
    }

    public LogBean setBodyBytesSent(String bodyBytesSent) {
        this.bodyBytesSent = bodyBytesSent;
        return this;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public LogBean setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
        return this;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public LogBean setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
        return this;
    }

    public boolean isValid() {
        return valid;
    }

    public LogBean setValid(boolean valid) {
        this.valid = valid;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.valid);
        sb.append("\001").append(this.remoteAddr);
        sb.append("\001").append(this.remoteUser);
        sb.append("\001").append(this.timeLocal);
        sb.append("\001").append(this.request);
        sb.append("\001").append(this.status);
        sb.append("\001").append(this.bodyBytesSent);
        sb.append("\001").append(this.httpReferer);
        sb.append("\001").append(this.httpUserAgent);
        return sb.toString();
    }
}
