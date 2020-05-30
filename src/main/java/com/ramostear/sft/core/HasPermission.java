package com.ramostear.sft.core;

import com.ramostear.sft.base.AbstractPermitModel;

/**
 * @author :       ramostear/树下魅狐
 * @version :     Una-Boot-1.3.0
 * <p>This java file was created by ramostear in 2020/5/30 0030 8:02.
 * The following is the description information about this file:</p>
 * <p>description:</p>
 */
public class HasPermission extends AbstractPermitModel {
    @Override
    protected boolean showBody(String permit) {
        return isPermitted(permit);
    }
}
