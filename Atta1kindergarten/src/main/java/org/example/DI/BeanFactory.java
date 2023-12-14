package org.example.DI;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public void scanAndInstantiate(String basePackage) {
        Set<Class<?>> componentClasses = scanComponents(basePackage);
        instantiateAndRegisterBeans(componentClasses);
    }

    public void registerBean(Class<?> beanClass, Object beanInstance) {
        beans.put(beanClass, beanInstance);
    }

    public <T> T getBean(Class<T> beanClass) {
        return beanClass.cast(beans.get(beanClass));
    }

    private Set<Class<?>> scanComponents(String basePackage) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(basePackage))
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));

        return reflections.getTypesAnnotatedWith(Component.class);
    }

    private void instantiateAndRegisterBeans(Set<Class<?>> componentClasses) {
        for (Class<?> clazz : componentClasses) {
            try {
                Object beanInstance = createBean(clazz);
                registerBean(clazz, beanInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object createBean(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Object beanInstance = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object fieldInstance = getBean(field.getType());
                field.set(beanInstance, fieldInstance);
            }
        }

        return beanInstance;
    }
}
