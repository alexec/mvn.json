
package com.alexecollins.tools.mvn;

import org.codehaus.jackson.annotate.JsonIgnore;

abstract class ResourceMixIn {
	@JsonIgnore
	abstract void setFiltering( String filtering );
}
