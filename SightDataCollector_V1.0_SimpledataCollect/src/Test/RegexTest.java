package Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static String newlinkByfilter(String regex, String url) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		boolean flag = matcher.find();
		System.out.println(flag);
		String newUrl = matcher.group();
		return newUrl;
	}

	public static void main(String[] args) {
		String newUrl = newlinkByfilter("http://you.ctrip.com/(.)*.html", "http://you.ctrip.com/place/abusimbel19950.html#cate_travel");
		System.out.println(newUrl);
	}

}
