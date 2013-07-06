package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;
public abstract class DependencyMixIn {
	@JsonIgnore
	abstract String getManagementKey();
	@JsonIgnore
	abstract void setOptional( String optional );
}
