package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import jakarta.xml.bind.Element;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import lombok.Getter;
import lombok.Setter;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
class AbstractType {

    @Override
    public String toString() {
        return super.toString() + '{' +
                "unknownAttributes=" + unknownAttributes +
                ",unknownElements=" + unknownElements +
                '}';
    }

    // -------------------------------------------------------------------------------------------------------------
    @XmlAnyAttribute
    private Map<QName, Object> unknownAttributes;

    @XmlAnyElement
    private List<Element> unknownElements;
}
