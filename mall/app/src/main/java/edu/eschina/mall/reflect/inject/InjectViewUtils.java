package edu.eschina.mall.reflect.inject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectViewUtils {

    @SuppressLint("ResourceType")
    public static void injectView(Activity activity){
        Class<? extends Activity> cls=activity.getClass();


        //获得此类所有的成员
        Field[] declaredFields = cls.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            //判断属性是否被InjectView注解申明
            if(declaredField.isAnnotationPresent(InjectViewBinding.class)){
                InjectViewBinding injectViewBinding=declaredField.getAnnotation(InjectViewBinding.class);
                //获得了注解中设置得id
                int id =injectViewBinding.value();
                View view = activity.findViewById(id);
                //反射设置 属性的值
                declaredField.setAccessible(true);  //设置访问权限，允许操作private的属性
                try {
                    declaredField.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
