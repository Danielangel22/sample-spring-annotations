package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is an annotation that provides a base template for sending emails.
 * 
 * @author Daniel Angel
 * @Category Utilities
 * @Date 04-08-2021
 * 
 * @version 1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseMail {
}
