
package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class ReportingMixIn {
	@JsonIgnore
	abstract void setExcludeDefaults( String excludeDefaults );
}
