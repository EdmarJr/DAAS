package br.jus.stj.saad.teste;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TesteInterceptor {

	@AroundInvoke
	public Object interceptInvocation(InvocationContext context) throws Exception {
		Method method = context.getMethod();
		Object[] parameters = context.getParameters();
		String invocationLog = createInvocationLog(method, parameters);
		System.err.println(invocationLog);
		return context.proceed();
	}

	private String createInvocationLog(Method method, Object[] parameters) throws Exception {
		String className = method.getDeclaringClass().getCanonicalName();
		String methodName = method.getName();
		Class<?>[] parameterTypes = method.getParameterTypes();
		
		StringBuilder invocationLog = new StringBuilder()
		.append("- Invocated Method -\n")
		.append(className).append(".").append(methodName).append("(/***/)\n");
		if(parameterTypes != null && parameterTypes.length > 0) {
			invocationLog.append("Parameters:\n");
			for(int i=0; i<parameterTypes.length; i++) {
				if(i > parameters.length) break; // var-args not provided
				Class<?> clazz = parameterTypes[i];
				Object parameter = parameters[i];
				logParameter(parameter, clazz, invocationLog);
			}
		}
		
		return invocationLog.toString();
	}
	
	private void logParameter(Object parameter, Class<?> parameterType, StringBuilder invocationLog) {
		invocationLog.append("\t");
		if(parameterType.isArray()) {
			Class<?> arrayType = parameterType.getComponentType();
			invocationLog
			.append(arrayType.isPrimitive() ? arrayType.getSimpleName() : arrayType.getCanonicalName())
			.append("[] = ")
			.append("{");
			if(parameter == null) {
				invocationLog.append(" null }");
			} else {
				int length = Array.getLength(parameter);
				if(length > 0) {
					invocationLog.append("\n");
				} else {
					invocationLog.append(" }");
				}
				for(int j=0; j<length; j++) {
					Object arrParameter = Array.get(parameter, j);
					invocationLog
					.append("\t\t")
					.append(arrayType.isPrimitive() ? arrParameter : arrParameter.toString())
					.append((j < length - 1) ? " ,\n" : " }");
				}
			}
		} else if(parameterType.isPrimitive()) {
			invocationLog.append(parameterType.getSimpleName()).append(" = ").append(parameter);
		} else {
			invocationLog.append(parameterType.getCanonicalName()).append(" = ").append(parameter != null ? parameter.toString() : "null");
		}
		invocationLog.append("\n");
	}
	
}
