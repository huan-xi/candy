package user.service;

import com.huanxi.pojo.User;

/**
 * 用户信息服务层接口
 */
public interface UserService {
    /**
     * 注册用户
     */
    public void add(User user);
    /**
     * 修改
     */
    public void update(User user);
    /**
     * 根据ID获取实体
     */
    public User findUserById(long id);
    /**
     * 根据邮箱获取用户
     * @param email
     * @return User
     */
    public User findUserByEmail(String email);

    /**
     * 根据手机号获取用户
     * @param phone
     * @return User
     */
    public User findUserByPhone(String phone);
}
