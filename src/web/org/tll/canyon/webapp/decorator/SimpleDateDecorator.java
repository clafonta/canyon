package org.tll.canyon.webapp.decorator;

import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * Stolen from http://www.docjar.com/html/api/org/displaytag/sample/LongDateWrapper.java.html
 * 
 * @author u159282
 *
 */
public class SimpleDateDecorator implements DisplaytagColumnDecorator {
    
          /**
           * FastDateFormat used to format the date object.
           */
          private FastDateFormat dateFormat = FastDateFormat.getInstance("MM/dd/yyyy"); //$NON-NLS-1$
      
          /**
           * transform the given object into a String representation. The object is supposed to be a date.
           * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(Object, PageContext, MediaTypeEnum)
           */
          public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException
          {
              Date date = (Date) columnValue;
              return this.dateFormat.format(date);
          }
      }
