package be.vdab.emailservice.core.util;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.Collections;
import java.util.List;

public abstract class EqualsByStateObject {
    
    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other, excludedFields());
    }
    
    @Override
    public int hashCode() {
        return reflectionHashCode(this, excludedFields());
    }
    
    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
    protected List<String> excludedFields() {
        return Collections.emptyList();
    }
}
