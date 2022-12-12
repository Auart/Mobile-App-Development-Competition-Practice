package edu.eschina.mall.reflect.inject;

import androidx.annotation.RawRes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectViewBinding {
   @RawRes int value();
}
