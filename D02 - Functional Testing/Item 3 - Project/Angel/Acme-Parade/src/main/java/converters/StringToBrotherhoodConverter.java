
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.BrotherhoodRepository;
import domain.Brotherhood;

@Component
@Transactional
public class StringToBrotherhoodConverter implements Converter<String, Brotherhood> {

	@Autowired
	BrotherhoodRepository	brotherhoodRepository;


	@Override
	public Brotherhood convert(final String text) {
		Brotherhood result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.brotherhoodRepository.findOne(id);
			}
		} catch (final Throwable opps) {
			throw new IllegalArgumentException(opps);
		}
		return result;
	}

}