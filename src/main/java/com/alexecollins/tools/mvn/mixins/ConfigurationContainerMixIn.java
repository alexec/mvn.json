package com.alexecollins.tools.mvn.mixins;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class ConfigurationContainerMixIn {
	@JsonIgnore
	abstract void setInherited( String inherited );

	//@JsonDeserialize (using = ConfigurationDes.class)
	//abstract void setConfiguration( Object configuration );
}
