package com.huanxi;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.jasig.cas.web.support.WebUtils.getHttpServletRequest;

/**
 * 自定义处理验证码登入逻辑
 */
public class AuthenticationViaCaptchaFormAction extends AuthenticationViaFormAction {

    public final String checkCaptcha(final RequestContext context, final Credential credentials, final MessageContext messageContext) throws Exception {
        final HttpServletRequest request = getHttpServletRequest(context);
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        AccountPasswordCaptchaCredential upc = (AccountPasswordCaptchaCredential) credentials;
        String submitCaptcha = upc.getCaptcha();
        if (!StringUtils.hasText(captcha) || !StringUtils.hasText(submitCaptcha)) {
            messageContext.addMessage(new MessageBuilder().error().code("required.captcha").build());
            return ERROR;
        }
        if (!submitCaptcha.equals(captcha)) {
//            session.setAttribute("isValidate", false); //设置不需要验证码
            return SUCCESS;
        }
        messageContext.addMessage(new MessageBuilder().error().code("error.authentication.captcha.bad").build());
        return ERROR;
    }
}
