/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.execution.injection.sample;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * @since 5.0
 */
public class CustomAnnotationParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {

		// Workaround for GitHub issue #1345
		//
		// Executable executable = parameterContext.getDeclaringExecutable();
		//
		// Parameter parameter = parameterContext.getParameter();
		// // Take into account a bug in javac in JDK 8:
		// if (executable.getParameters().length == executable.getParameterAnnotations().length + 1) {
		//     parameter = executable.getParameters()[parameterContext.getIndex() - 1];
		// }
		//
		// return parameter.isAnnotationPresent(CustomAnnotation.class);

		return parameterContext.getParameter().isAnnotationPresent(CustomAnnotation.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return ReflectionUtils.newInstance(parameterContext.getParameter().getType());
	}

}
