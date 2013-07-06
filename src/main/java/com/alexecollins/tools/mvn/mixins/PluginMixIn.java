package com.alexecollins.tools.mvn.mixins;

import org.apache.maven.model.PluginExecution;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Map;

public abstract class PluginMixIn {
	@JsonIgnore
	abstract void setExtensions( String extensions );
	@JsonIgnore
	abstract String getKey();
	@JsonIgnore
	abstract String getId();
	@JsonIgnore
	abstract Map<String, PluginExecution> getExecutionsAsMap();

	abstract Map getConfiguration();
}
