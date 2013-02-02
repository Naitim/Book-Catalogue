package com.eleybourn.bookcatalogue.datamanager;

import com.eleybourn.bookcatalogue.R;

/**
 * Validator that requires a blank field
 * 
 * @author Philip Warner
 *
 */
public class BlankValidator implements DataValidator {
	@Override
	public void validate(DataManager data, Datum datum, boolean crossValidating) {
		if (!datum.isVisible()) {
			// No validation required for invisible fields
			return;
		}
		if (crossValidating)
			return;
		try {
			String s = data.getString(datum);
			if (!s.equals("")) {
				s = s.trim();
				if (!s.equals("")) {
					throw new ValidatorException(R.string.vldt_blank_required, new Object[]{datum.getKey()});
				}
				data.putString(datum, s);
			}
			return;
		} catch (Exception e) {
			throw new ValidatorException(R.string.vldt_blank_required, new Object[]{datum.getKey()});
		}
	}
}