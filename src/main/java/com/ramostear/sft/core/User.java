package com.ramostear.sft.core;

import com.ramostear.sft.base.AbstractSecurityModel;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 8:31.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class User extends AbstractSecurityModel {

    @Override
    public void render(Environment environment, Map map, TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException {
        Subject subject = getSubject();
        if(subject != null && subject.getPrincipal() != null){
            output(environment,templateDirectiveBody);
        }
    }
}
