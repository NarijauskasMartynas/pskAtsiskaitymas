package lt.vu.usecases.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@MyInterceptor
@Interceptor
public class InterceptorImpl implements Serializable {

    @AroundInvoke
    public Object doSomething(InvocationContext ctx) throws Exception{
        System.out.println("interceptor called");
        Object result = ctx.proceed();
        return result;
    }
}
