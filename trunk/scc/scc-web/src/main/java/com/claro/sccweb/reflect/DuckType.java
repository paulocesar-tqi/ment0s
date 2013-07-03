package com.claro.sccweb.reflect;

import static java.util.Arrays.asList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;

/*
 * Duck type implementation for Java.
 * 
 * http://en.wikipedia.org/wiki/Duck_typing
 * 
 * by Alexandre Amaral (https://github.com/aamaral)
 * 
 * based on Ronie Uliana's  (https://github.com/ruliana) implementation
 * 
 */
public class DuckType {

    private static StackTraceElement extractCaller(final StackTraceElement[] stackTrace, final Collection<String> collection) {
        int i = 2;
        StackTraceElement current = null;
        for (final String regex : collection) {
            current = stackTrace[i];
            String lastMatch = null;
            while (current.toString().matches(regex)) {
                i++;
                current = stackTrace[i];
                lastMatch = regex;
            }
            // Not who called the DuckType method,
            // but outer method;
            if (lastMatch == null || lastMatch.matches(".*\\.DuckType\\..*")) current = stackTrace[i + 1];
            else current = stackTrace[i];
        }
        return current;
    }

    private static StackTraceElement extractCaller(final StackTraceElement[] stackTrace, final String... regexesToIgnore) {
        // Nothing found or stack trace too shallow
        if (stackTrace == null || stackTrace.length < 3) return new StackTraceElement("", "", "", 0);

        return extractCaller(stackTrace, ignoreListWithMyself(regexesToIgnore));
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(final Object object, final String attribute) {
        return (T) get(object, attribute, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Object object, final String attribute, final T defaultValue) {
        if (null == object) return defaultValue;
        if (null == attribute) return defaultValue;

        for (final String attr : attribute.split("\\.")) {
            final DuckType duckType = new DuckType(object);
            // First, try "getXxx"
            object = duckType.call("get" + StringUtils.capitalize(attr));
            // If no success, try "isXxx"
            if (null == object) object = duckType.call("is" + StringUtils.capitalize(attr));

            // No success at all, return default
            if (null == object) return defaultValue;
        }

        return (T) object;
    }

    private static Collection<String> ignoreListWithMyself(final String... regexesToIgnore) {
        final Deque<String> toIgnore = regexesToIgnore == null ? new LinkedList<String>() : new LinkedList<String>(asList(regexesToIgnore));

        toIgnore.addFirst(".*\\.DuckType\\..*"); // Ignore myself
        return toIgnore;
    }

    public static String theMethodWhoCalledThisMethod() {
        return whoCalledThis().getMethodName();
    }

    public static String theMethodWhoCalledThisMethodIgnoring(final String... regexMethodName) {
        return whoCalledThis(regexMethodName).getMethodName();
    }

    private static StackTraceElement whoCalledThis(final String... regexesToIgnore) {
        /*
         * Dirty trick to find who called the actual method (excluding this one).
         */
        try {
            throw new Exception("This is a really slow and dirty trick (but it works)");
        } catch (final Exception e) {
            return extractCaller(e.getStackTrace(), regexesToIgnore);
        }
    }

    public static String whoCalledThisMethod() {
        return whoCalledThisMethodIgnoring((String[]) null);
    }

    public static String whoCalledThisMethodIgnoring(final String... regexMethodName) {
        return whoCalledThis(regexMethodName).toString();
    }

    private final Object object;

    public DuckType(final Object object) {
        this.object = object;
    }
    
    public DuckType(final String className, Object... args) throws Exception {
    	this(Class.forName(className), args);
    }

    public DuckType(Class<?> c, Object... args) throws Exception {
    	if ( null != args && args.length > 0 ) {
    		Class<?>[] ptypes = new Class[args.length];
    		for(int i = 0; i < args.length; i++) ptypes[i] = args[i].getClass();
    		Constructor<?> constructor = c.getConstructor(ptypes);
    		this.object = constructor.newInstance(args);
    	} else
    		this.object = c.newInstance();
    }
    
    public Object getContext() {
    	return this.object;
    }

    @SuppressWarnings("unchecked")
    public <T> T call(final String methodName) {
        return (T) call(methodName, (Object[]) null);
    }

    @SuppressWarnings("unchecked")
    public <T> T call(final String methodName, final Object... params) {
        try {
            Method method = null;
            if (null == params) method = object.getClass().getMethod(methodName);
            else for (final Method m : object.getClass().getMethods())
                if (m.getName().equals(methodName) && m.getParameterTypes().length == params.length) {
                    boolean found = true;
                    for (int i = 0; i < m.getParameterTypes().length; i++)
                        if (!(params[i] == null || m.getParameterTypes()[i].isAssignableFrom(params[i].getClass()))) {
                            found = false;
                            break;
                        }

                    if (found) {
                        method = m;
                        break;
                    }
                }

            return null != method ? (T) method.invoke(object, params) : null;

        } catch (final ClassCastException e) {
            // method doesn't matches the desired return type, ignore it
            return null;
        } catch (final NoSuchMethodException e) {
            // if it doesn't exists, we will ignore it
            return null;
        } catch (final SecurityException e) {
            // Programming error = RuntimeException
            throw new RuntimeException(e);
        } catch (final IllegalArgumentException e) {
            // "Impossible" to happen
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            // Programming error = RuntimeException
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            // "Impossible" to happen
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public boolean hasField(final String fieldName, final Class<? extends Object> returnType) {
        try {
			final Class clazz = object instanceof Class ? (Class) object : object.getClass();
            final Field field = clazz.getDeclaredField(fieldName);
            return field.getType().equals(returnType);

        } catch (final NoSuchFieldException e) {
            // ignore exception. This is the purpose of this method, simply
            // return true or false.
            return false;
        } catch (final SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
