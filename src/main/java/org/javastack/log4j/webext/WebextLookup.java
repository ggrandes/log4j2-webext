/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.javastack.log4j.webext;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.util.Strings;
import org.apache.logging.log4j.web.WebLoggerContextUtils;

@Plugin(name = "webext", category = "Lookup")
public class WebextLookup extends AbstractLookup {

	@Override
	public String lookup(final LogEvent event, final String key) {
		final ServletContext ctx = WebLoggerContextUtils.getServletContext();
		if (ctx == null) {
			return null;
		}

		if ("contextBaseFileName".equals(key)) {
			String cn = ctx.getContextPath();
			if (cn.isEmpty()) {
				return "ROOT";
			} else {
				if (cn.charAt(0) == '/') {
					cn = cn.substring(1);
				}
				return cn.replace('/', '#');
			}
		}

		ctx.log(getClass().getName() + " unable to resolve key " + Strings.quote(key));
		return null;
	}
}
