package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;
	private static String PRICE_URL = "";

	@Before
	public void init() {
		PRICE_URL = "http://localhost:" + port + "/prices/";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testRetrievePrice() {
		Price result = testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		Assert.assertNotNull(result);
	}

	@Test
	public void testPriceFields() {
		
		Long id = 123L;
		String currency = "USD";
		BigDecimal price = BigDecimal.valueOf(12300.99);

		Price newPrice = new Price(id, currency, price);

		HttpEntity<Price> request = new HttpEntity<>(newPrice);
		testRestTemplate.postForObject(PRICE_URL, request, Price.class);
		
		assertThat(request.getBody().getVehicleId(), equalTo(id));
		assertThat(request.getBody().getPrice(), equalTo(price));
		assertThat(request.getBody().getCurrency(), equalTo(currency));
	}

	@Test
	public void testUpdatedPrice() {
		BigDecimal newPrice = BigDecimal.valueOf(10000.01);

		Price currentPrice = this.testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		
		currentPrice.setPrice(newPrice);
		HttpEntity<Price> request = new HttpEntity<>(currentPrice);
		ResponseEntity<Price> result = this.testRestTemplate.exchange(PRICE_URL + "1", HttpMethod.PUT, request, Price.class);

		Price updatedPrice = this.testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		assertThat(updatedPrice.getPrice(), equalTo(newPrice));

	}

	@Test
	public void testDeletePrice() {
		String url = PRICE_URL + "2";
		testRestTemplate.delete(url);
		
		Price result = testRestTemplate.getForObject(url, Price.class);
		Assert.assertNull(result);
	}

}
