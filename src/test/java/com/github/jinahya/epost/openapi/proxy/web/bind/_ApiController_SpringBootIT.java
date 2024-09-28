package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * An abstract test class for testing subclasses of {@link _ApiController} class.
 *
 * @param <CONTROLLER> subclass type parameter
 * @param <SERVICE>    service type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@SuppressWarnings({
        "java:S101", // class _Api...
        "java:S119"  // <CONTROLLER ...>
})
public abstract class _ApiController_SpringBootIT<
        CONTROLLER extends _ApiController<SERVICE>,
        SERVICE extends _ApiService
        >
        extends __ApiController_SpringBootIT<CONTROLLER> {

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private SERVICE service;
}
