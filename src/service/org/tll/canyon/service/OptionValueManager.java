
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.OptionValue;


public interface OptionValueManager extends Manager {
    /**
     * Retrieves all of the optionValues
     */
    public List<OptionValue> getOptionValues(OptionValue optionValue);

    /**
     * Gets optionValue's information based on id.
     * @param id the optionValue's id
     * @return optionValue populated optionValue object
     */
    public OptionValue getOptionValue(final String id);

    /**
     * Saves a optionValue's information
     * @param optionValue the object to be saved
     */
    public void saveOptionValue(OptionValue optionValue);

    /**
     * Removes a optionValue from the database by id
     * @param id the optionValue's id
     */
    public void removeOptionValue(final Long id);
}

