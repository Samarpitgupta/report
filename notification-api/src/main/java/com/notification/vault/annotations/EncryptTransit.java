package com.notification.vault.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD) // this annotation can only be applied to methods
@Retention(RetentionPolicy.RUNTIME) // this annotation will be available at runtime
public @interface EncryptTransit {
    // This is a marker annotation for methods that require encryption of transit data
}
