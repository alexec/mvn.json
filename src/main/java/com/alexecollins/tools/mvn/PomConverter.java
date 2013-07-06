package com.alexecollins.tools.mvn;

import com.alexecollins.tools.mvn.mixins.*;
import com.fasterxml.jackson.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.maven.model.*;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class PomConverter {

	public static String xmlToJson(String pom) throws Exception {

		final XmlMapper in = new XmlMapper();
		in.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configDeserialiser(in.getDeserializationConfig());

		final ObjectMapper out = new ObjectMapper();
		out.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
		out.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
		out.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		configureSerializer(out.getSerializationConfig());

		return out.writerWithDefaultPrettyPrinter().writeValueAsString(in.readValue(pom, Model.class));
	}

	private static void configureSerializer(SerializationConfig cfg) {
		cfg.addMixInAnnotations(ConfigurationContainer.class, ConfigurationContainerMixIn.class);
		cfg.addMixInAnnotations(Dependency.class, DependencyMixIn.class);
		cfg.addMixInAnnotations(Plugin.class, PluginMixIn.class);
		cfg.addMixInAnnotations(Reporting.class, ReportingMixIn.class);
		cfg.addMixInAnnotations(RepositoryPolicy.class, RepositoryPolicyMixIn.class);
		cfg.addMixInAnnotations(Resource.class, ResourceMixIn.class);
		// ignoring fields only on output
		cfg.addMixInAnnotations(Model.class, ModelMixIn.class);
		cfg.addMixInAnnotations(PluginContainer.class, PluginContainerMixIn.class);
		cfg.addMixInAnnotations(PluginExecution.class, PluginExecutionMixIn.class);
	}

	public static String jsonToXml(String json) throws IOException {


		final ObjectMapper in = new ObjectMapper();
		in.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		in.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
		in.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		in.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		configDeserialiser(in.getDeserializationConfig());

		final Model model = in.readValue(json, Model.class);

		final XStream xstream = new XStream(new DomDriver());
		xstream.alias("pom", Model.class);
		xstream.alias("plugin", Plugin.class);
		xstream.alias("dependency", Dependency.class);
		// xstream.addImplicitMap(Plugin.class, "configuration", Map.class, "key");
		final StringWriter out = new StringWriter();
		xstream.toXML(model, out);

		//new MavenXpp3Writer().write(new StringWriter(), model);
		return out.toString();
	}

	private static void configDeserialiser(DeserializationConfig cfg) {
		cfg.addMixInAnnotations(ConfigurationContainer.class, ConfigurationContainerMixIn.class);
		cfg.addMixInAnnotations(Dependency.class, DependencyMixIn.class);
		cfg.addMixInAnnotations(Plugin.class, PluginMixIn.class);
		cfg.addMixInAnnotations(Reporting.class, ReportingMixIn.class);
		cfg.addMixInAnnotations(RepositoryPolicy.class, RepositoryPolicyMixIn.class);
		cfg.addMixInAnnotations(Resource.class, ResourceMixIn.class);
		cfg.addMixInAnnotations(PluginExecution.class, PluginExecutionMixIn.class);
	}
}
