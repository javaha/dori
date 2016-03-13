/**
@file HtmlContentBuilder.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | HtmlContentBuilder.java |    
| Package | com.paintee.common.mail |    
| Project name | paintee-admin |    
| Type name | HtmlContentBuilder |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 13. 오후 4:22:12 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

/**
@class HtmlContentBuilder
com.paintee.common.mail \n
   ㄴ HtmlContentBuilder.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 13. 오후 4:22:12 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - html content 생성
*/
@Component("com.paintee.common.mail.HtmlContentBuilder")
public class HtmlContentBuilder {
	private final static Logger logger = LoggerFactory.getLogger(HtmlContentBuilder.class);

    public final static String HANDLEBARS_TEMPLATE_SUFFIX = ".html";
    public final static String HANDLEBARS_TEMPLATE_EMAIL_FILE_PATH = "/templates/email";

    public String getSignupConfirmMail(ConfirmMailVO confirmMailVO) {
    	String mailContents = "";

    	com.github.jknack.handlebars.Template template;

        try {
            TemplateLoader loader = new ClassPathTemplateLoader();
            loader.setPrefix(HANDLEBARS_TEMPLATE_EMAIL_FILE_PATH);
            loader.setSuffix(HANDLEBARS_TEMPLATE_SUFFIX);

            logger.debug("template path : {}", loader.getPrefix());

            template = new Handlebars(loader).compile("signup_confirm_mail");

            if (template != null && template.apply(null).length() > 0) {
            	logger.debug("---> template : {}", template.apply(confirmMailVO));
            	mailContents = template.apply(confirmMailVO);
            }

        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("{}", e);
        }

        return mailContents;
    }
}
