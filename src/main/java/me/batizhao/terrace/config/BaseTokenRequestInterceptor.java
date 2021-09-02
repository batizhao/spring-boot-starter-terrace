/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.batizhao.terrace.config;

import feign.RequestInterceptor;
import org.springframework.util.Assert;

/**
 * The base request interceptor.
 *
 * @author Jakub Narloch
 */
public abstract class BaseTokenRequestInterceptor implements RequestInterceptor {

	/**
	 * The encoding properties.
	 */
	private final TerraceClientProperties properties;

	/**
	 * Creates new instance of {@link BaseTokenRequestInterceptor}.
	 * @param properties the encoding properties
	 */
	protected BaseTokenRequestInterceptor(TerraceClientProperties properties) {
		Assert.notNull(properties, "Properties can not be null");
		this.properties = properties;
	}

	protected TerraceClientProperties getProperties() {
		return this.properties;
	}

}
