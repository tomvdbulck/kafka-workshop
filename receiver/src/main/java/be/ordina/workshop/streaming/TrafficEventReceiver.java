package be.ordina.workshop.streaming;

import be.ordina.workshop.streaming.domain.TrafficEvent;
import be.ordina.workshop.streaming.domain.VehicleClass;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
//lab 3 and lab 4: add something over here
@EnableBinding({Sink.class, KStreamSink.class})
public class TrafficEventReceiver {

	private final Sink sink;
	private final KStreamSink kStreamSink;

	public TrafficEventReceiver(Sink sink, KStreamSink kstreamSink) {
		this.sink = sink;
		this.kStreamSink = kstreamSink;
	}

	private static final Logger logger =
			LoggerFactory.getLogger(TrafficEventReceiver.class);

	//lab 3: consume events
	@StreamListener(Sink.INPUT)
	public void consumeEvent(TrafficEvent event) {
		//logger.info("Received event: {}", event);
	}

	@StreamListener
	public void consumeEvent(@Input(KStreamSink.INPUT) KStream<String, TrafficEvent> stream) {
		stream.filter(((key, trafficEvent) -> VehicleClass.CAR == trafficEvent.getVehicleClass()))
				.print(Printed.toSysOut());
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
