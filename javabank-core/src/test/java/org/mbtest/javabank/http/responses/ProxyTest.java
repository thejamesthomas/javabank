package org.mbtest.javabank.http.responses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.mbtest.javabank.http.predicates.PredicateGenerator;
import org.mbtest.javabank.http.predicates.PredicateType;

//@formatter:off
public class ProxyTest {

	@Test
	public void shouldBuildProxy() throws ParseException {
		JSONObject expectedjson = (JSONObject) new JSONParser()
				.parse("{\"proxy\":{\"mode\":\"proxyAlways\",\"predicateGenerators\":[{\"equals\":{\"path\":true,\"query\":true}}],\"to\":\"http://someapiurl:0000/context-root/api\"}}");
		
		Proxy proxy = new Proxy(ProxyType.PROXY_ALWAYS)
							.proxyTo("http://someapiurl:0000/context-root/api")
							.addPredicateGenerators(buildPredicateGenerators());
		
		assertThat(proxy.getJSON(), is(expectedjson));
	}

	private List<PredicateGenerator> buildPredicateGenerators() {
		List<PredicateGenerator> predicateGenerators = new ArrayList<>();
		PredicateGenerator predicateGenerator = new PredicateGenerator(PredicateType.EQUALS)
														.withPath()
														.withQueryParameters();
		predicateGenerators.add(predicateGenerator);
		return predicateGenerators;
	}

}
