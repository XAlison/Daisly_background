package com.icekredit.http;

import java.io.Serializable;
import java.util.Objects;

/**
 * A simple data structure which contains a name-value pair for various attributes/properties in an
 * HTTP request. This includes but is not limited to: Headers and Query Parameters.
 */
public class ApiProperty implements Serializable {

    private String name;
    private String value;

    /**
     * Basic constructor taking a name-value pair.
     *
     * @param name  Non-{@literal null} name/key to use for this property.
     * @param value Non-{@literal null} value for use for this property.
     */
    public ApiProperty(String name, String value) {
        setName(name);
        setValue(value);
    }

    protected ApiProperty() {
        // required for serialization
    }

    /**
     * Gets the name currently set for this property.
     *
     * @return This property objects's current name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to use for this property.
     *
     * @param name Non-{@literal null} string to use as a new name.
     */
    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Gets the value currently set for this property.
     *
     * @return This property object's current value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value to use for this property.
     *
     * @param value Non-{@literal null} string to use as a new value.
     */
    public void setValue(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        ApiProperty that = (ApiProperty) o;

        if (!getName().equals(that.getName())) {
            return false;
        }
        return getValue().equals(that.getValue());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = (31 * result) + getValue().hashCode();
        return result;
    }
}
