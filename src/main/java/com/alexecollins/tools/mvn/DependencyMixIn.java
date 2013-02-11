package com.alexecollins.tools.mvn;

import org.codehaus.jackson.annotate.JsonIgnore;
abstract class DependencyMixIn {
	@JsonIgnore
	abstract String getManagementKey();
	@JsonIgnore
	abstract void setOptional( String optional );
}
