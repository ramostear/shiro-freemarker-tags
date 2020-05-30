package com.ramostear.sft.core;

import com.ramostear.sft.base.AbstractRoleModel;
import org.apache.shiro.subject.Subject;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 8:09.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class LacksRole extends AbstractRoleModel {
    @Override
    protected boolean showBody(String role) {
        Subject subject = getSubject();
        boolean isShow = subject != null && subject.hasRole(role);
        return !isShow;
    }
}
