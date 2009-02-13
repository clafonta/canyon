package org.tll.canyon.webapp.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * <p>Render text as a slug, putting a limit on text display of max 50 chars</p>
 * 
 * @author u159282
 *
 */
public class SlugDecorator implements DisplaytagColumnDecorator {
      
          /**
           * transform the given object into a String representation. 
           * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(Object, PageContext, MediaTypeEnum)
           */
          public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException
          {
              String displayText = columnValue.toString();
              if(displayText.length()>50){
            	  displayText = (displayText.substring(0,46) + "...");
              }
              return displayText;
          }
      }
