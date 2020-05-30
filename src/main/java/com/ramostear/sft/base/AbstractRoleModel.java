package com.ramostear.sft.base;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 7:30.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public abstract class AbstractRoleModel extends AbstractSecurityModel {
    @Override
    public void render(Environment environment, Map map, TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException {
        boolean show = showBody(getRole(map));
        if(show){
            output(environment,templateDirectiveBody);
        }
    }

    private String getRole(Map map) throws TemplateModelException {
        return getValue(map,"name");
    }

    protected abstract boolean showBody(String role);
}
