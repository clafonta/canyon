
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.OptionValue;


public interface OptionValueDao extends Dao {

  
    public List<OptionValue> getOptionValues(OptionValue optionValue);

   
    public OptionValue getOptionValue(final Long id);

       
    public void saveOptionValue(OptionValue optionValue);

    
    public void removeOptionValue(final Long id);
}

