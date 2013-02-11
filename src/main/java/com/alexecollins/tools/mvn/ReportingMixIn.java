
package com.alexecollins.tools.mvn;

import org.codehaus.jackson.annotate.JsonIgnore;

abstract class ReportingMixIn {
	@JsonIgnore
	abstract void setExcludeDefaults( String excludeDefaults );
}
