package expt.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

public class JavassistLearnFactory{


	public static void main(String[] args) throws Exception{
		ProxyFactory factory=new ProxyFactory();
		//设置父类，ProxyFactory将会动态生成一个类，继承该父类
		factory.setSuperclass(JavassistLearn.class);
		//设置过滤器，判断哪些方法调用需要被拦截
		factory.setFilter(new MethodFilter() {
			@Override
			public boolean isHandled(Method m) {
				if(m.getName().equals("getName")){
					return true;
				}
				return false;
			}
		});
		//设置拦截处理
		factory.setHandler(new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed,
								 Object[] args) throws Throwable {
				//拦截后前置处理，改写name属性的内容
				//实际情况可根据需求修改
				JavassistLearn o=(JavassistLearn) self;
				o.setName("haha");
				return proceed.invoke(self, args);
			}
		});

		Class<?> c=factory.createClass();
		JavassistLearn object=(JavassistLearn) c.newInstance();
		System.out.println(object.getName());

	}

}

