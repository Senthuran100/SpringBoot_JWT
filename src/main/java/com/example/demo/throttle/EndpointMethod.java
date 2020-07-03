package com.example.demo.throttle;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EndpointMethod {

    private final Class targetClass;
    private final String targetMethod;

    public EndpointMethod(Class targetClass, String targetMethod) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EndpointMethod that = (EndpointMethod) o;

        return new EqualsBuilder()
                .append(targetClass, that.targetClass)
                .append(targetMethod, that.targetMethod)
                .isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(17, 37)
                .append(targetClass)
                .append(targetMethod)
                .toHashCode();
    }

}
