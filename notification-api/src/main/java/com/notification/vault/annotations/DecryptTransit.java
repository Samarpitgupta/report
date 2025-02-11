package com.notification.vault.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Specifies that this annotation can only be applied to methods
@Retention(RetentionPolicy.RUNTIME) // Specifies that this annotation will be available at runtime
public @interface DecryptTransit {
    // Marker annotation for methods that require decryption of transit data
}