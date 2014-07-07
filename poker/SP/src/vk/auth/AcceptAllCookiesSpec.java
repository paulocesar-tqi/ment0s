package vk.auth;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BestMatchSpec;

/**
 * User:   Evgeny Avsievich
 * E-mail: ray.evg@gmail.com
 * Date:   12/8/11
 * Time:   4:54 PM
 */
class AcceptAllCookiesSpec extends BestMatchSpec {
    public static final String ID = "_acceptAllCookies";

    public AcceptAllCookiesSpec(String[] patterns, boolean singleHeader) {
        super(patterns, singleHeader);
    }

    public AcceptAllCookiesSpec() {
    }

    @Override
    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        try {
            super.validate(cookie, origin);
        } catch (MalformedCookieException e) {
            if (!e.getMessage().contains("Domain of origin"))
                throw e;
        }
    }
}
