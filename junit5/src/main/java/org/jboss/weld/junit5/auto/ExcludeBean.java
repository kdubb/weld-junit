package org.jboss.weld.junit5.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code ExcludeBean} excludes a class with a bean defining annotation (e.g. scope) from automatic discovery. This
 * can be helpful to allow replacing a bean class with a different implementation; typically a mock.
 *
 * The type of bean to exclude is implied by the annotated field's type or annotated method's return type.
 *
 * NOTE: This annotation will only exclude beans defined by class annotations. It will not exclude beans of the
 * implied type that are defined by {@link javax.enterprise.inject.Produces producer methods or fields}
 *
 * Example:
 * <pre>
 * &#64;EnableAutoWeld
 * class TestSomeFoo {
 *
 *   &#64;Inject
 *   SomeFoo someFoo;   // SomeFoo depends upon application scoped bean Foo
 *
 *   &#64;Produces
 *   &#64;ExcludeBean   // Excludes Foo bean class from automatic discovery
 *   Foo mockFoo = mock(Foo.class);  // mockFoo is now produced in place of original Foo impl
 *
 *   &#64;Test
 *   void test(Foo myFoo) {
 *     assertNotNull(myFoo);
 *     assertEquals(myFoo.getBar(), "mock-foo");
 *   }
 * }
 * </pre>
 *
 * @see ExcludeBeans
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface ExcludeBean {
}
