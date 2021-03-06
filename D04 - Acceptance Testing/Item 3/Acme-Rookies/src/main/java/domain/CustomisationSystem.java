
package domain;

import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CustomisationSystem extends DomainEntity {

	private String				systemName;
	private String				banner;
	private String				message;
	private Map<String, String>	spamwords;
	private Integer				hoursFinder;
	private Integer				resultFinder;
	private Integer				phonePrefix;
	private Double				vat;
	private Double				flat_rate;


	@NotBlank
	@SafeHtml
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}
	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@NotBlank
	@SafeHtml
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@ElementCollection(targetClass = String.class)
	@MapKeyColumn(name = "lang")
	public Map<String, String> getSpamwords() {
		return this.spamwords;
	}

	public void setSpamwords(final Map<String, String> spamwords) {
		this.spamwords = spamwords;
	}
	@Range(min = 1, max = 24)
	public Integer getHoursFinder() {
		return this.hoursFinder;
	}

	public void setHoursFinder(final Integer hoursFinder) {
		this.hoursFinder = hoursFinder;
	}
	@Range(min = 10, max = 100)
	public Integer getResultFinder() {
		return this.resultFinder;
	}

	public void setResultFinder(final Integer resultFinder) {
		this.resultFinder = resultFinder;
	}

	public Integer getPhonePrefix() {
		return this.phonePrefix;
	}

	public void setPhonePrefix(final Integer phonePrefix) {
		this.phonePrefix = phonePrefix;
	}

	@Range(min = 0, max = 1)
	public Double getVat() {
		return this.vat;
	}

	public void setVat(final Double vat) {
		this.vat = vat;
	}

	@Min(0)
	public Double getFlat_rate() {
		return this.flat_rate;
	}

	public void setFlat_rate(final Double flat_rate) {
		this.flat_rate = flat_rate;
	}

}
