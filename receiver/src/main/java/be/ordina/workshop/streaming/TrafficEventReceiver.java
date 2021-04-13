package be.ordina.workshop.streaming;

import be.ordina.workshop.streaming.domain.TrafficEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
//lab3 and lab4: add something over here
@EnableBinding({ Sink.class})
public class TrafficEventReceiver {

	private static final Logger logger =
			LoggerFactory.getLogger(TrafficEventReceiver.class);

	//lab3: consume events
	@StreamListener(Sink.INPUT)
	public void consumeEvent(TrafficEvent event) {
		logger.info("Received event: {}", event);
	}

	//lab 4: Native Kafka Stream processing
	public void consumeEvent() {
		//lab 4:
		// - take in a stream
		// - only process VehicleClass.CAR
		// - determine key
		// - group on this key: think about serialization
		// - use aggregate function with helper class: Average.java
		// - map
		// - print output

	}

}
