package org.tll.canyon.webapp.taglib;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;


/**
 * <p>Render text as a slug, putting a limit on text display.</p> 
 * 
 * <p>It is designed to be used as follows:
 * <pre>&lt;tag:slug key="someObject.username" value="someObject.value" maxLength="25" /&gt;</pre>
 *
 * @jsp.tag name="slug" bodycontent="empty"
 */
public class SlugTag extends TagSupport {
    
	private static final long serialVersionUID = -180731361590899622L;
	protected RequestContext requestContext;
    protected transient final Log log = LogFactory.getLog(SlugTag.class);
    protected String key = null;
    protected String value = null;
    protected int maxLength = 25;

    public int doStartTag() throws JspException {
        
        try {
            this.requestContext =   
                new RequestContext((HttpServletRequest) this.pageContext.getRequest());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex) {
            pageContext.getServletContext().log("Exception in custom tag", ex);
        }
        
        // Look up this key to see if its a field of the current form
        
        
        Locale locale = pageContext.getRequest().getLocale();

        if (locale == null) {
            locale = Locale.getDefault();
        }
        
        
        String message = null;
        
        // Retrieve the message string we are looking for
        if(key!=null){
        try {
        	message = getMessageSource().getMessage(key, null, locale);
        } catch (NoSuchMessageException nsm) {
            message = "???" + key + "???";
        }
        }else if (value!=null){
        	message = (String) ExpressionEvaluatorManager.evaluate(
                    "value", this.value, String.class,
                    this, super.pageContext );

        }else {
        	message = "MISSING KEY or VALUE entries";
        }
       
        // SLUG it.  
        if(message!=null && message.length()>maxLength){
        	message = (message.substring(0, maxLength-1)) + "...";
        }
        
        // Print the retrieved message to our output writer
        try {
            writeMessage(message);
        } catch (IOException io) {
            io.printStackTrace();
            throw new JspException("Error writing label: " + io.getMessage());
        }

        // Continue processing this page
        return (SKIP_BODY);
    }
  
    /**
     * Write the message to the page.
     * @param msg the message to write
     * @throws IOException if writing failed
     */
    protected void writeMessage(String msg) throws IOException {
        pageContext.getOut().write(msg);
    }
    
    /**
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Setter for setting display max length, default is 25.
     *
     * @jsp.attribute required="false" rtexprvalue="false"
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        key = null;        
    }
   
    /**
     * Use the application context itself for default message resolution.
     */
    private MessageSource getMessageSource() {
        return requestContext.getWebApplicationContext();
    }
}
