package com.ramostear.sft.core;

import com.ramostear.sft.base.AbstractRoleModel;
import org.apache.shiro.subject.Subject;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 7:59.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class HasAnyRoles extends AbstractRoleModel {
    @Override
    protected boolean showBody(String roles) {
        boolean show = false;
        Subject subject = getSubject();
        if(subject != null){
            for(String role : roles.split(",")){
                if(subject.hasRole(role)){
                    show = true;
                    break;
                }
            }
        }
        return show;
    }
}
