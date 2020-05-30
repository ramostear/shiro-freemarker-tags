package com.ramostear.sft.core;

import com.ramostear.sft.base.AbstractSecurityModel;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.apache.shiro.subject.Subject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 8:12.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class Principal extends AbstractSecurityModel {

    @Override
    public void render(Environment environment, Map map, TemplateDirectiveBody templateDirectiveBody) throws IOException, TemplateException {
        String result = null;
        Subject subject = getSubject();
        if(subject != null){
            Object principal;
            if(getType(map) == null){
                principal = subject.getPrincipal();
            }else{
                principal  = getPrincipalFromClassName(map);
            }
            if(principal != null){
                String property = getProperty(map);
                if(property == null){
                    result = principal.toString();
                }else{
                    result = getPrincipalProperty(principal,property);
                }
            }
        }
        if(result != null){
            try {
                environment.getOut().write(result);
            }catch (IOException e){
                throw new TemplateException("Error writing ["+result+"] to freemarker template.",e,environment);
            }
        }
    }

    private String getType(Map map) throws TemplateModelException {
        return getValue(map,"type");
    }

    private String getProperty(Map map) throws TemplateModelException {
        return getValue(map,"property");
    }

    @SuppressWarnings("unchecked")
    private Object getPrincipalFromClassName(Map map) throws TemplateModelException {
        String type =getType(map);
        try {
            Class clz = Class.forName(type);
            return getSubject().getPrincipals().oneByType(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPrincipalProperty(Object principal,String property) throws TemplateModelException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(principal.getClass());
            for(PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()){
                if(descriptor.getName().equals(property)){
                    Object value = descriptor.getReadMethod().invoke(principal, (Object) null);
                    return String.valueOf(value);
                }
            }
            throw new TemplateModelException("Property: "+property+" not found in principal of type ["+principal.getClass().getName()+"]");
        }catch (Exception e){
            throw new TemplateModelException("Error reading property: "+property+" from principal of type ["+principal.getClass().getName()+"]",e);
        }
    }
}
