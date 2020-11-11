package com.example;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionTarget;

public class Utils {
    public static <T> T inject(T target) {
        CDI<Object> cdi = CDI.current();
        BeanManager bm = cdi.getBeanManager();
        AnnotatedType<T> type = bm.createAnnotatedType((Class<T>) target.getClass());
        InjectionTarget<T> it = bm.getInjectionTargetFactory(type)
                                  .createInjectionTarget(null);
        if (!it.getInjectionPoints().isEmpty()) {
            // インジェクション可能なときのみ，インジェクションする
            CreationalContext<T> ctx = bm.createCreationalContext(null);
            it.inject(target, ctx);
            it.postConstruct(target);
        }
        return target;
    }
}
