package thaumcraft.registry;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(CLASS)
public @interface ThaumcraftItem {
	String[] value();
}
