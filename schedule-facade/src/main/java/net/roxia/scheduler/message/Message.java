package net.roxia.scheduler.message;

import java.io.Serializable;

// 消息的主体
public class Message implements Serializable {

    public static String default_null = "--roxia--";

    private Header header;

    private String body;

    public Message(Header header, String body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,key=%s,name=%s,alias=%s,body=%s]",
                header.getVersion(),
                header.getKey(),
                header.getName(),
                header.getAlias(),
                body);
    }
}