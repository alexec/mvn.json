package com.alexecollins.tools.mvn;

import org.apache.maven.model.Plugin;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Map;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
abstract class PluginContainerMixIn {
	@JsonIgnore
	abstract Map<String, Plugin> getPluginsAsMap();
}
