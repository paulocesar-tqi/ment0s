package vk.api.utils;

import java.util.*;

/**
 * User:   Evgeny Avsievich
 * E-mail: ray.evg@gmail.com
 * Date:   12/8/11
 * Time:   5:18 PM
 */
public class P {
    private HashMap<String, String> params = new HashMap<String, String>();

    private String removeSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\n')
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }


    public P(){}

    /**
     *
     * @param s Params to any request to API, should look like "param1 = value1, param2 = value2"
     */
    public P(String s) {
        s = removeSpaces(s);
        String tmp[] = s.split(",");
        for (String i : tmp) {
            params.put(i.split("=")[0], i.split("=")[1]);
        }
    }

    public Map<String, String> getParamsAsMap() {
        return params;
    }

    public List<String> getParamsAsList() {
        Iterator<String> it = params.keySet().iterator();
        List<String> x = new LinkedList<String>();
        while (it.hasNext()) {
            String a = it.next();
            String b = params.get(a);
            x.add(a + "=" + b);
        }
        return x;
    }

    public String getParamsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String i : getParamsAsList()) {
            sb.append(i);
            sb.append("&");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }
}
