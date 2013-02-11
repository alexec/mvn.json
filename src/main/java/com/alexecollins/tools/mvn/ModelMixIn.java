package com.alexecollins.tools.mvn;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
abstract class ModelMixIn {
	@JsonIgnore
	abstract String getId();
	@JsonIgnore
	abstract String getModelEncoding();
}
