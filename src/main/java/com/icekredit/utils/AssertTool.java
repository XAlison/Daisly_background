package com.icekredit.utils;

import com.icekredit.pojo.Assertion;
import com.icekredit.pojo.AssertionModel;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

/**
 * Created by root on 17-5-9.
 */
public class AssertTool {
    public static Matcher assertionMatcher(AssertionModel assertion) {
        switch (assertion.getComparison()) {
            case "equals(JSON)":
                return  sameJSONAs(assertion.getOnLineModelJsonPath()).allowingAnyArrayOrdering();
            case "equals(String)":
                return Matchers.equalTo(assertion.getOnLineModelJsonPath());
            case "does not equal":
                return not(assertion.getOnLineModelJsonPath());
            case "is empty(String)":
                return isEmptyString();
            case "is not empty(String)":
                return Matchers.is(not(isEmptyString()));
            case "contains":
                return Matchers.containsString(assertion.getOnLineModelJsonPath());
            case "does not contains":
                return Matchers.not(containsString(assertion.getOnLineModelJsonPath()));
            case "is a number":
                return IsANumMatcher.generateMatcher();
            case "equals(number)":
                return Matchers.equalTo(Double.parseDouble(assertion.getOnLineModelJsonPath()));
            case "less than or equal":
                return Matchers.lessThanOrEqualTo(Double.parseDouble(assertion.getOnLineModelJsonPath()));
            case "greater than":
                return Matchers.greaterThan(Double.parseDouble(assertion.getOnLineModelJsonPath()));
            case "greater than or equal":
                return Matchers.greaterThanOrEqualTo(Double.parseDouble(assertion.getOnLineModelJsonPath()));
            default:
                return nullValue();
        }
    }
    public static void main(String args[]){
        assertThat("xxxxxxxxx",
                "{\"age\":43, \"friend_ids\":[16, 52, 23]}",
                sameJSONAs("{\"friend_ids\":[52, 23, 16]}")
//                        .allowingExtraUnexpectedFields()
                        .allowingAnyArrayOrdering());
    }
}
