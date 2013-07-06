package com.alexecollins.tools.mvn.mixins;

import org.apache.maven.model.Plugin;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Map;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class PluginContainerMixIn {
	@JsonIgnore
	abstract Map<String, Plugin> getPluginsAsMap();
}
