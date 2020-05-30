package com.ramostear.sft;

import com.ramostear.sft.core.*;
import freemarker.template.*;


/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 7:55.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class ShiroFreemarkerTags extends SimpleHash{

   public ShiroFreemarkerTags(ObjectWrapper objectWrapper){
        super(objectWrapper);
        put("authenticated",new Authenticated());
        put("guest",new Guest());
        put("hasAnyRoles",new HasAnyRoles());
        put("hasPermission",new HasPermission());
        put("hasRole",new HasRole());
        put("lacksRole",new LacksRole());
        put("lacksPermission",new LacksPermission());
        put("noAuthenticated",new NotAuthenticated());
        put("principal",new Principal());
        put("user",new User());
    }
}
