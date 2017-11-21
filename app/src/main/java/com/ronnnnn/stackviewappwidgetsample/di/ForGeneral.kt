package com.ronnnnn.stackviewappwidgetsample.di

import javax.inject.Qualifier

/**
 * Created by kokushiseiya on 2017/11/22.
 */
@Qualifier
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.PROPERTY
)
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ForGeneral