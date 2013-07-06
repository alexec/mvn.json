package com.alexecollins.tools.mvn;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@RunWith(Parameterized.class)
public class PomConverterTest {


	public PomConverterTest(URI pom, String name) {
		this.pom = pom;
		this.name = name;
	}

	@Parameterized.Parameters(name = "{1}")
	public static Collection<Object[]> data() throws IOException, URISyntaxException {
		final List<Object[]> objects = new ArrayList<Object[]>();

		final BufferedReader reader = new BufferedReader(new InputStreamReader(PomConverterTest.class.getResourceAsStream("/ls.txt")));
		String l;
		while ((l=reader.readLine()) !=null) {
			final URI uri = PomConverterTest.class.getResource("/" + l).toURI();
			final String name = uri.getPath().replaceAll("^.*\\/", "");
			objects.add(new Object[]{uri, name});
		}

		return objects;

	}

	final URI pom;
	private final String name;

	public String resourceAsString(URI uri) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
		String l;
		final StringBuilder out = new StringBuilder();
		while ((l=reader.readLine()) !=null) {
			out.append(l).append('\n');
		}
		return out.toString();
	}

	static File TMP = new File("target/tmp/");


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceDelete(TMP);
		TMP.mkdirs();
	}

	@Test
	public void testPom() throws Exception {


		final String orig = resourceAsString(pom);

		final File a = new File(TMP, name.replaceAll("\\.", ".a."));
		FileUtils.writeStringToFile(a, orig, Charset.forName("UTF-8"));
		System.out.println("created " + a);

		final String json = PomConverter.xmlToJson(orig);

		FileUtils.writeStringToFile(new File(TMP, name.replaceAll("\\.xml", ".json")), json, Charset.forName("UTF-8"));

		final String xml = PomConverter.jsonToXml(json);

		final File b = new File(TMP, name.replaceAll("\\.", ".b."));
		FileUtils.writeStringToFile(b, xml, Charset.forName("UTF-8"));
		System.out.println("created " + b );
	}
}
