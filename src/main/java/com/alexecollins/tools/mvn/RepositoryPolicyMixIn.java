
package com.alexecollins.tools.mvn;

import org.codehaus.jackson.annotate.JsonIgnore;
abstract class RepositoryPolicyMixIn{

	@JsonIgnore
	abstract void setEnabled( String enabled );
}
