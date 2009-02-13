package org.tll.canyon.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.servlet.support.RequestContext;

/**
 * <p>
 * Designed to provide a highlight/focus to a label with information
 * 
 * <p>
 * It is designed to be used as follows:
 * 
 * <pre>
 * &lt;tag:label key=&quot;userForm.username&quot;/&gt;
 * </pre>
 * 
 * @jsp.tag name="focus" bodycontent="empty"
 */
public class FocusTag extends TagSupport {
	private static final long serialVersionUID = -5310144023136517119L;
	protected RequestContext requestContext;
	protected transient final Log log = LogFactory.getLog(FocusTag.class);
	protected String text = null;
	protected String textStyleClass = "";
	protected String flagMessage = null;
	protected String flagStyleClass = "fieldError";
	protected String flag = "false";
	

	public int doStartTag() throws JspException {

		String textEL = null;
		String flagMessageEL = null;
		
		if(text!=null) {
			textEL = (String) ExpressionEvaluatorManager.evaluate("text",
				this.text, String.class, this, super.pageContext);
		}
		if(flagMessage!=null) {
			flagMessageEL = (String) ExpressionEvaluatorManager.evaluate("flagMessage",
				this.flagMessage, String.class, this, super.pageContext);
		}
		
		boolean focusFlag = ((Boolean) ExpressionEvaluatorManager.evaluate(
		        "flag",flag,Boolean.class,this,pageContext)).booleanValue();

		
		StringBuffer label = new StringBuffer();
		
		// Display MESSAGE only if not null && focusFlag is on. 
		if(flagMessageEL!=null && flagMessageEL.trim().length()>0 && focusFlag){
			label.append("<label id=\"focusmessage\" class="+textStyleClass+" >");
			label.append("<span class=\"" +flagStyleClass+ "\" >"+ flagMessageEL + "</span>" );
			label.append("</label>");
		}
		// Display TEXT only if not null. 
		if(textEL!=null && textEL.trim().length()>0 ){
			label.append("<label id=\"focustext\" class="+textStyleClass+" >");
			label.append((focusFlag) ? "<span class=\"" +flagStyleClass+ "\" >"+ textEL + "</span>" : textEL );
			label.append("</label>");
		}

		// Print the retrieved message to our output writer
		try {
			writeMessage(label.toString());
		} catch (IOException io) {
			io.printStackTrace();
			throw new JspException("Error writing label: " + io.getMessage());
		}

		// Continue processing this page
		return (SKIP_BODY);
	}

	/**
	 * Extract the error messages from the given ObjectError list.
	 */
	/*
	 * private String getErrorMessages(List fes) throws NoSuchMessageException {
	 * StringBuffer message = new StringBuffer(); for (int i = 0; i <
	 * fes.size(); i++) { ObjectError error = (ObjectError) fes.get(i);
	 * message.append(this.requestContext.getMessage(error, true)); } return
	 * message.toString(); }
	 */

	/**
	 * Write the message to the page.
	 * 
	 * @param msg
	 *            the message to write
	 * @throws IOException
	 *             if writing failed
	 */
	protected void writeMessage(String msg) throws IOException {
		pageContext.getOut().write(msg);
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setText(String txt) {
		this.text = txt;
	}

	/**
	 * Setter for specifying whether to include a CSS
	 * 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/**
	 * Setter for specifying whether to include a CSS
	 * 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setFlagMessage(String flagMessage) {
		this.flagMessage = flagMessage;
	}

	/**
	 * Setter for assigning a CSS class, default is label.
	 * 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setTextStyleClass(String textStyleClass) {
		this.textStyleClass = textStyleClass;
	}
	
	/**
	 * Setter for assigning a CSS class, default is label.
	 * 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setFlagStyleClass(String flagStyleClass) {
		this.flagStyleClass = flagStyleClass;
	}

	/**
	 * Release all allocated resources.
	 */
	public void release() {
		super.release();
		text = null;
		flag = null;
		textStyleClass = null;
		flagStyleClass = null;
	}
}
