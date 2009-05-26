package org.tll.canyon.webapp.taglib;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
/**
 * <p>Render text for a textarea, keeping break lines. Value is EL enabled. </p> 
 * 
 * <p>It is designed to be used as follows:
 * <pre>&lt;tag:option value="someObject.value" /&gt;</pre>
 *
 * @jsp.tag name="option" bodycontent="empty"
 */
public class OptionTag extends TagSupport {

	private static final long serialVersionUID = -8902512566431524818L;
	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
     * @jsp.attribute required="true" rtexprvalue="true"
     */
	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() {
		try {
			value = (String)ExpressionEvaluatorManager.evaluate("value", value, String.class, pageContext);	        
			
			JspWriter out = pageContext.getOut();
			// 'println' appends a line break to argument.
			out.println(value);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Error("All is not well in the world.", ex);
		}
		// Must return SKIP_BODY because we are not supporting a body for this
		// tag.
		return SKIP_BODY;
	}

	
}

    
    
   