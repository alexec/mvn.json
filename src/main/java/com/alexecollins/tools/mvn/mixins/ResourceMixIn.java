
package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class ResourceMixIn {
	@JsonIgnore
	abstract void setFiltering( String filtering );
}
