package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.Element;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractType {

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "unknownAttributes=" + unknownAttributes +
                ",unknownElements=" + unknownElements +
                '}';
    }

    // ----------------------------------------------------------------------------------------- Jakarta Bean Validation
    //@AssertTrue(message = "unknown attributes should be empty")
    private boolean isUnknownAttributesEmpty() {
        return unknownAttributes == null || unknownAttributes.isEmpty();
    }

    //@AssertTrue(message = "unknown elements should be empty")
    private boolean isUnknownElementsEmpty() {
        return unknownElements == null || unknownElements.isEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // should be empty!
    @JsonbTransient
    @JsonIgnore
    @Size(max = 0, message = "no unknown attributes are expected")
    @XmlAnyAttribute
    private Map<QName, Object> unknownAttributes;

    // should be empty!
    @JsonbTransient
    @JsonIgnore
    @Size(max = 0, message = "no unknown elements are expected")
    @XmlAnyElement
    private List<Element> unknownElements;
}
