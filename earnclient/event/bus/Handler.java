package earnclient.event.bus;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    int value() default 2;
}
