package com.huanxi;

import org.jasig.cas.authentication.UsernamePasswordCredential;

import java.util.Objects;

public class AccountPasswordCaptchaCredential extends UsernamePasswordCredential {

private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountPasswordCaptchaCredential that = (AccountPasswordCaptchaCredential) o;
        return Objects.equals(captcha, that.captcha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), captcha);
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}