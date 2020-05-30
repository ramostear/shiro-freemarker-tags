package com.ramostear.sft.base;

import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * @author :       ramostear/树下魅狐
 * @version :     v1.0.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 7:15.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public abstract class AbstractSecurityModel implements TemplateDirectiveModel {

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        checkParams(map);
        render(environment,map,templateDirectiveBody);
    }

    public abstract void render(Environment environment,Map map,TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException;

    protected String getValue(Map map,String field) throws TemplateModelException {
        Object value = map.get(field);
        if(value instanceof SimpleScalar){
            return ((SimpleScalar)value).getAsString();
        }else if(value instanceof TemplateScalarModel){
            return ((TemplateScalarModel)value).getAsString();
        }
        return null;
    }

    protected Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    protected void checkParams(Map map) throws TemplateModelException{}

    protected void output(Environment environment,TemplateDirectiveBody body) throws IOException, TemplateException {
        if(body != null){
            body.render(environment.getOut());
        }
    }
}
