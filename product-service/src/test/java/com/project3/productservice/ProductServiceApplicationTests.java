package com.project3.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project3.productservice.dto.ProductRequest;
import com.project3.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;


import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultMatcher;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc



class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
@Autowired
private static MockMvc mockMvc;
@Autowired
private static ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) throws JsonProcessingException {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);




		@Test
		void shouldCreateProduct() throws Exception {
			ProductRequest productRequest = getProductRequest();
			String productRequestString = objectMapper.writeValueAsString(productRequest);
			mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
							.contentType(MediaType.APPLICATION_JSON)
							.content(productRequestString))
					.andExpect(status().isCreated());
			Assertions.assertEquals(1, productRepository.findAll().size());
		}

	private static ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iphone 14")
				.description("iphone 14")
				.price(BigDecimal.valueOf(1200))
				.build()
	}

}