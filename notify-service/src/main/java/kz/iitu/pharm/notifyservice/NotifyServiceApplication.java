package kz.iitu.pharm.notifyservice;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
public class NotifyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyServiceApplication.class, args);
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

		return props;
	}

	@Bean
	public ConsumerFactory<String, DrugRequest> consumerFactory() {
		JsonDeserializer<DrugRequest> deserializer = new JsonDeserializer<>(DrugRequest.class);
//		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);

		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DrugRequest> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DrugRequest> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}
}
