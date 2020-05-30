package com.ramostear.sft.base;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 7:37.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public abstract class AbstractPermitModel extends AbstractSecurityModel {
    @Override
    public void render(Environment environment, Map map, TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException {
       String permit = getPermit(map);
       boolean isShow = showBody(permit);
       if(isShow){
           output(environment,templateDirectiveBody);
       }
    }

    @Override
    protected void checkParams(Map map) throws TemplateModelException {
        String permit = getPermit(map);
        if(permit == null || permit.trim().equals("")){
            throw new TemplateModelException("The 'name' of tag attribute must be set.");
        }
    }

    private String getPermit(Map map) throws TemplateModelException {
        return getValue(map,"name");
    }

    protected boolean isPermitted(String permit){
        Subject subject = getSubject();
        return subject != null &&
                (subject.hasRole("admin") ||
                 subject.hasRole("Admin") ||
                 subject.hasRole("ADMIN")||
                 subject.hasRole("manager") ||
                 subject.hasRole("Manager") ||
                 subject.hasRole("MANAGER") ||
                 subject.hasRole("administrator") ||
                 subject.hasRole("Administrator") ||
                 subject.hasRole("ADMINISTRATOR") ||
                 subject.hasRole("master") ||
                 subject.hasRole("Master") ||
                 subject.hasRole("MASTER") ||
                 subject.isPermitted(permit)
                );
    }

    protected abstract boolean showBody(String permit);
}
