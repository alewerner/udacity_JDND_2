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
import org.springframework.http.HttpStatus;
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

	/**
	 * Creates pre-requisites for testing, the URL and the right port.
	 */
	@Before
	public void init() {
		PRICE_URL = "http://localhost:" + port + "/prices/";
	}

	/**
	 * Tests if a given URL and a ID returns the price
	 */
	@Test
	public void testRetrievePrice() {
		Price result = testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		Assert.assertNotNull(result);
	}

	/**
	 * Test if a given Price contains the correct values
	 * */
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

	/**
	 * Test the update price for a single ID
	 * */
	@Test
	public void testUpdatedPrice() {
		BigDecimal newPrice = BigDecimal.valueOf(10000.01);

		Price currentPrice = testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		
		currentPrice.setPrice(newPrice);
		HttpEntity<Price> request = new HttpEntity<>(currentPrice);
		ResponseEntity<Price> result = testRestTemplate.exchange(PRICE_URL + "1", HttpMethod.PUT, request, Price.class);

		Price updatedPrice = testRestTemplate.getForObject(PRICE_URL + "1", Price.class);
		assertThat(updatedPrice.getPrice(), equalTo(newPrice));

	}

	/**
	 * Test the delete operation for a single price ID
	 * */
	@Test
	public void testDeletePrice() {
		String url = PRICE_URL + "2";
		testRestTemplate.delete(url);
		
		Price result = testRestTemplate.getForObject(url, Price.class);
		Assert.assertNull(result);
	}

	/**
	 * Test if not found a given ID returns NOT_FOUND http code
	 * */
	@Test
	public void testGetNotFoundEntity() {
		ResponseEntity<Price> priceResponseEntity =
				testRestTemplate.getForEntity(PRICE_URL + "-1", Price.class);

		Assert.assertNotNull(priceResponseEntity);
		Assert.assertEquals(HttpStatus.NOT_FOUND, priceResponseEntity.getStatusCode());
		Assert.assertNull(priceResponseEntity.getBody());
	}

}
