package be.ordina.workshop.streaming;

import be.ordina.workshop.streaming.domain.TrafficEvent;
import be.ordina.workshop.streaming.domain.VehicleClass;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
//lab3 and lab4: add something over here
@EnableBinding({ Sink.class, KStreamSink.class })
public class TrafficEventReceiver {

	private static final Logger logger =
			LoggerFactory.getLogger(TrafficEventReceiver.class);

	//lab3: consume events
	@StreamListener(Sink.INPUT)
	public void consumeEvent(TrafficEvent event) {
		logger.info("Received event: {}", event);
	}

	//lab 4: Native Kafka Stream processing
	@StreamListener
	public void consumeEvent(@Input(KStreamSink.INPUT) KStream<String, TrafficEvent> stream) {
		//lab 4:
		// - determine key
		// - group on this key: think about serialization
		// - use aggregate function with helper class: Average.java
		// - map
		// - print output

		stream.filter(((key, trafficEvent) -> VehicleClass.CAR == trafficEvent.getVehicleClass()))
				.selectKey((key, value) -> value.getSensorId())
				.groupByKey(Grouped.with(Serdes.String(), new JsonSerde<>(TrafficEvent.class)))
				.windowedBy(TimeWindows.of(120_000L))
				.aggregate(Average::new, (sensorId, trafficEvent, average) -> {
					average.addSpeed(trafficEvent.getTrafficIntensity(),
							trafficEvent.getVehicleSpeedCalculated());
					return average;
				}, Materialized.with(Serdes.String(), new JsonSerde<>(Average.class)))
				.mapValues(Average::average)
				.toStream()
				.print(Printed.toSysOut());
	}

}
