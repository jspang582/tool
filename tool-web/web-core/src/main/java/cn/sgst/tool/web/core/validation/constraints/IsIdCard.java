package cn.sgst.tool.web.core.validation.constraints;

import cn.sgst.tool.web.core.validation.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})//表明注解标注的位置
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Constraint(validatedBy = IdCardValidator.class)//用于校验的类
public @interface IsIdCard {

    String message() default "{javax.validation.constraints.IsIdCard.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
