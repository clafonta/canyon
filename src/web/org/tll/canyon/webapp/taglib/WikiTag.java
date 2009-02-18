package org.tll.canyon.webapp.taglib;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseInitialRenderContext;
import org.springframework.web.servlet.support.RequestContext;


/**
 * <p>Render text as a slug, putting a limit on text display.</p> 
 * 
 * <p>It is designed to be used as follows:
 * <pre>&lt;tag:slug key="someObject.username" value="someObject.value" maxLength="25" /&gt;</pre>
 *
 * @jsp.tag name="wiki" bodycontent="empty"
 */
public class WikiTag extends TagSupport {
    
	private static final long serialVersionUID = -180731361590899622L;
	protected RequestContext requestContext;
    protected transient final Log log = LogFactory.getLog(WikiTag.class);
    protected String value = null;

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
        String wikiText = null;
        if (value!=null){
        	wikiText = (String) ExpressionEvaluatorManager.evaluate(
                    "value", this.value, String.class,
                    this, super.pageContext );

        }else {
        	wikiText = "MISSING VALUE entry";
        }
        
        InitialRenderContext initialContext = new BaseInitialRenderContext();
        initialContext.set(RenderContext.INPUT_LOCALE, locale);
        RenderEngine engineWithContext = new BaseRenderEngine(initialContext);
        String xhtmlResult = engineWithContext.render(wikiText, initialContext);

        try {
            writeTxt(xhtmlResult);
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
    protected void writeTxt(String txt) throws IOException {
        pageContext.getOut().write(txt);
    }
    
   
    /**
     * @jsp.attribute required="false" rtexprvalue="true"
     */
    public void setValue(String value) {
        this.value = value;
    }

    
    
    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        value = null;        
    } 
}
