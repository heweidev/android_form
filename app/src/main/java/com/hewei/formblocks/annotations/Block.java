package com.hewei.formblocks.annotations;

import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.factory.InvalidBlockFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Block {
    String NONE_ID = "";
    int index() default 0;
    String id() default NONE_ID;
    Class<? extends BlockFactory<?>> factory() default InvalidBlockFactory.class;
    int size() default 1;
    String[] args() default {};
}
