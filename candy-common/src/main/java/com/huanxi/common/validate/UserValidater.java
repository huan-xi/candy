package com.huanxi.common.validate;

/**
 * 用户验证器
 */
public class UserValidater {
    /**
     * 密码必须包含字母
     * 长度大于6位
     * @param passwd
     * @return
     */
    public static boolean passwdValidate(String passwd){
        if (passwd.matches(".*[a-zA-Z]+.*")&&passwd.length()>=6)
            return true;
        return false;
    }

    /**
     * 手机号格式验证
     * 1开头11位数字
     * @param phone
     * @return
     */
    public static boolean phoneValidate(String phone){
        if (phone.matches("^1\\d{10}$"))
            return true;
        return false;
    }
}
