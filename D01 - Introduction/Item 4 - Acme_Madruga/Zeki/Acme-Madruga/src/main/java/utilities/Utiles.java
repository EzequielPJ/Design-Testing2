
package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import security.Authority;
import domain.Box;
import domain.Message;

public class Utiles {

	public static Collection<String>	spamWords	= new ArrayList<String>();
	public static Collection<String>	goodWords	= new ArrayList<String>();
	public static Collection<String>	badWords	= new ArrayList<String>();
	public static Collection<String>	priorities	= new HashSet<String>();

	public static Integer				hoursFinder;
	public static Integer				resultsFinder;
	public static Integer				phonePrefix;

	public static String				systemName;
	public static String				banner;
	public static String				mess;


	public static Collection<Box> initBoxes() {

		List<Box> boxesSystem;

		boxesSystem = new ArrayList<Box>();

		boxesSystem.add(Utiles.createBox(true, "In Box"));
		boxesSystem.add(Utiles.createBox(true, "Out Box"));
		boxesSystem.add(Utiles.createBox(true, "Spam Box"));
		boxesSystem.add(Utiles.createBox(true, "Notification Box"));
		boxesSystem.add(Utiles.createBox(true, "Trash Box"));

		return boxesSystem;

	}
	public static Box createBox(final boolean fromSystem, final String name) {

		Box b;

		b = new Box();

		b.setMessage(new ArrayList<Message>());

		b.setFromSystem(fromSystem);

		b.setName(name);

		return b;

	}

	public static Collection<String> limpiaString(String s) {
		s = s.replaceAll("[^a-zA-Z0-9$]", "#");

		final List<String> textoRoto = Arrays.asList(s.split("##|#"));
		return textoRoto;
	}
	public static boolean spamWord(final Collection<String> contentMessage) {
		boolean res = false;

		Map<String, Boolean> result;
		result = new HashMap<>();

		for (final String word : Utiles.spamWords)
			result.put(word, contentMessage.contains(word.toLowerCase()));

		for (final Boolean b : result.values())
			if (b) {
				res = true;
				break;
			}

		return res;
	}

	public static String buildUrl(final Map<String, String> requestParams) {
		String s = "";
		if (requestParams.size() > 1)
			for (int i = requestParams.size() - 1; i >= 0; i--) {
				final String key = (String) requestParams.keySet().toArray()[i];
				s = s + key + "=" + requestParams.get(key) + "&";
			}
		else
			s = s + requestParams.keySet().toArray()[0] + "=" + requestParams.values().toArray()[0] + "&";
		return s.substring(0, s.length() - 1);
	}

	public static boolean checkURL(final Collection<String> urls) {
		boolean res = false;

		for (final String url : urls) {
			res = url.startsWith("http://") || url.startsWith("https://");
			if (res == false)
				break;
		}

		return res;
	}

	public static String hashPassword(final String old) {
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String passEncoded;
		passEncoded = encoder.encodePassword(old, null);

		return passEncoded;
	}

	public static String generateTicker() {
		SimpleDateFormat formato;
		formato = new SimpleDateFormat("yyMMdd");

		String formated;

		formated = formato.format(new Date());

		final Character[] ch = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		String c = "";

		Random random;

		random = new Random();

		for (int i = 0; i < 6; i++)
			c += ch[random.nextInt(ch.length)];

		return formated + "-" + c;
	}
	public static void setParameters(final String systemName, final String banner, final String mess, final Integer hours, final Integer results, final Integer phonePrefix) {
		Utiles.systemName = systemName;
		Utiles.banner = banner;
		Utiles.mess = mess;
		Utiles.hoursFinder = hours;
		Utiles.resultsFinder = results;
		Utiles.phonePrefix = phonePrefix;
	}
	public static Boolean findAuthority(final Collection<Authority> comp, final String a) {
		Boolean res = false;
		if (comp.size() > 1) {
			Authority aut;
			aut = new Authority();
			aut.setAuthority(a);
			res = comp.contains(aut);
		} else
			for (final Authority authority : comp)
				if (authority.toString().equals(a))
					res = true;

		return res;
	}
}
