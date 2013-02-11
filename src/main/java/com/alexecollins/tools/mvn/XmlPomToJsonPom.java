package com.alexecollins.tools.mvn;

import com.fasterxml.jackson.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.*;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class XmlPomToJsonPom {

	public static void main(String[] args) throws Exception {
		final String json;
		{
			final XmlMapper in = new XmlMapper();
			in.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			in.getDeserializationConfig().addMixInAnnotations(ConfigurationContainer.class, ConfigurationContainerMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Dependency.class, DependencyMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Plugin.class, PluginMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Reporting.class, ReportingMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(RepositoryPolicy.class, RepositoryPolicyMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Resource.class, ResourceMixIn.class);

			final ObjectMapper out = new ObjectMapper();
			out.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
			out.getSerializationConfig().addMixInAnnotations(ConfigurationContainer.class, ConfigurationContainerMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(Dependency.class, DependencyMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(Plugin.class, PluginMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(Reporting.class, ReportingMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(RepositoryPolicy.class, RepositoryPolicyMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(Resource.class, ResourceMixIn.class);
			// ignoring fields only on output
			out.getSerializationConfig().addMixInAnnotations(Model.class, ModelMixIn.class);
			out.getSerializationConfig().addMixInAnnotations(PluginContainer.class, PluginContainerMixIn.class);

			json = out.writerWithDefaultPrettyPrinter().writeValueAsString(in.readValue(new File("pom.xml"), Model.class));
		}
		System.out.println(json);

		final File pomJson = new File("pom.json");
		FileUtils.writeStringToFile(pomJson, json, "UTF-8");

		{
			final ObjectMapper in = new ObjectMapper();
			in.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			in.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
			in.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			in.getDeserializationConfig().addMixInAnnotations(ConfigurationContainer.class, ConfigurationContainerMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Dependency.class, DependencyMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Plugin.class, PluginMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Reporting.class, ReportingMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(RepositoryPolicy.class, RepositoryPolicyMixIn.class);
			in.getDeserializationConfig().addMixInAnnotations(Resource.class, ResourceMixIn.class);

			final Model model = in.readValue(pomJson, Model.class);

			final XStream xstream = new XStream(new DomDriver());
			xstream.alias("pom", Model.class);
			xstream.alias("plugin", Plugin.class);
			xstream.alias("dependency", Dependency.class);
			xstream.addImplicitMap(Plugin.class, "configuration", Map.class, "key");
			final StringWriter out = new StringWriter();
			xstream.toXML(model, out);
			System.out.println(out);

			//new MavenXpp3Writer().write(new StringWriter(), model);
		}

	}
}
