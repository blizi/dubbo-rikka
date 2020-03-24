package com.rikka.sso.shiro.factory;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/***
 * 关闭shiro 的 session
 */
public class StateLessSubjectFactory extends DefaultWebSubjectFactory {
    public Subject createSubject(SubjectContext context) {
        AuthenticationToken token = context.getAuthenticationToken();
        //do not create session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
