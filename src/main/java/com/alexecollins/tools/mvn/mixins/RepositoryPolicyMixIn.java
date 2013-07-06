
package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;
public abstract class RepositoryPolicyMixIn{

	@JsonIgnore
	abstract void setEnabled( String enabled );
}
