
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.OptionValueDao;
import org.tll.canyon.model.OptionValue;
import org.tll.canyon.service.OptionValueManager;



public class OptionValueManagerImpl extends BaseManager implements OptionValueManager {
    private OptionValueDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setOptionValueDao(OptionValueDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.OptionValueManager#getOptionValues(org.tll.canyon.model.OptionValue)
     */
    public List<OptionValue> getOptionValues(final OptionValue optionValue) {
        return dao.getOptionValues(optionValue);
    }

    /**
     * @see org.tll.canyon.service.OptionValueManager#getOptionValue(String id)
     */
    public OptionValue getOptionValue(final String id) {
        return dao.getOptionValue(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.OptionValueManager#saveOptionValue(OptionValue optionValue)
     */
    public void saveOptionValue(OptionValue optionValue) {
        dao.saveOptionValue(optionValue);
    }

    /**
     * @see org.tll.canyon.service.OptionValueManager#removeOptionValue(String id)
     */
    public void removeOptionValue(final String id) {
        dao.removeOptionValue(new Long(id));
    }
}
