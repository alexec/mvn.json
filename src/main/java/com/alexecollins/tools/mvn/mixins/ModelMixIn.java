package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@JsonPropertyOrder({"modelVersion", "groupId","artifactId","version","packing","parent","dependencies","build"})
public abstract class ModelMixIn {
	@JsonIgnore
	abstract String getId();
	@JsonIgnore
	abstract String getModelEncoding();
}
