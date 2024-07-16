package com.github.jinahya.epost.openapi.proxy.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.Element;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import javax.xml.namespace.QName;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractType
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1503731084647974030L;

//    // -----------------------------------------------------------------------------------------------------------------
//    protected static <TYPE extends AbstractType> TYPE deserializeInstance(final ObjectReader objectReader,
//                                                                          final Class<TYPE> typeClass,
//                                                                          final Object valueSource) {
//        Objects.requireNonNull(objectReader, "objectReader is null");
//        Objects.requireNonNull(valueSource, "valueSource is null");
//        return typeClass.cast(ObjectReaderUtils.readValue(objectReader, valueSource));
//    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "unknownAttributes=" + unknownAttributes +
                ",unknownElements=" + unknownElements +
                ",unknownProperties=" + unknownProperties +
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

    // ----------------------------------------------------------------------------------------------- unknownAttributes
    public Map<QName, Object> getUnknownAttributes() {
        if (unknownAttributes == null) {
            unknownAttributes = new HashMap<>();
        }
        return unknownAttributes;
    }

    // ------------------------------------------------------------------------------------------------- unknownElements
    public List<Element> getUnknownElements() {
        if (unknownElements == null) {
            unknownElements = new ArrayList<>();
        }
        return unknownElements;
    }

    // ----------------------------------------------------------------------------------------------- unknownProperties

    public Map<String, Object> getUnknownProperties() {
        if (unknownProperties == null) {
            unknownProperties = new HashMap<>();
        }
        return unknownProperties;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // should be empty!
    @JsonIgnore
    @Size(max = 0, message = "no unknown attributes are expected")
    @XmlAnyAttribute
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Map<QName, Object> unknownAttributes;

    // should be empty!
    @JsonIgnore
    @Size(max = 0, message = "no unknown elements are expected")
    @XmlAnyElement
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private List<Element> unknownElements;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = 0, message = "no unknown properties are expected")
    @JsonAnySetter
    @XmlTransient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Map<String, Object> unknownProperties;
}
