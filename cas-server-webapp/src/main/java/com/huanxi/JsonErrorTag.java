package com.huanxi;

import org.apache.shiro.session.mgt.SessionContext;
import org.springframework.binding.message.Message;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.webflow.execution.RequestContextHolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Json显示错误
 */
public class JsonErrorTag extends ErrorsTag {

    private static final long serialVersionUID = -5309285189827209297L;
    /*    private String lt;
            private String execution;
            private String _eventId;*/
    private String error;

    /* 标签初始方法 */
 /*   public int doStartTag() throws JspTagException{
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }*/
    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        MessageContext messageContext = RequestContextHolder.getRequestContext().getMessageContext();
        Message[] messages = messageContext.getAllMessages();
        if (messages != null && messages.length > 0) {
            try {
                out.print(messages[0].getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                out.print("登入成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    /*    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String get_eventId() {
        return _eventId;
    }

    public void set_eventId(String _eventId) {
        this._eventId = _eventId;
    }*/
}
