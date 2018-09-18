package com.icekredit.utils;

import org.hamcrest.*;

/**
 * Created by root on 17-1-10.
 */
public class IsANumMatcher extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String t) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return t.matches(reg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue("not a number");
    }

    @Factory
    public static Matcher<String> generateMatcher(){
        return new IsANumMatcher();
    }
}
