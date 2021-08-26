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

import java.time.Duration;

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

	//@StreamListener
	//public void consumeEvent(@Input(KStreamSink.INPUT) KStream<String, TrafficEvent> stream) {
	//	stream.filter(((key, trafficEvent) -> VehicleClass.CAR == trafficEvent.getVehicleClass()))
	//			.print(Printed.toSysOut());
	//}

	@StreamListener
	public void consumeEvent(@Input(KStreamSink.INPUT) KStream<String, TrafficEvent> stream) {
		stream.filter(((key, trafficEvent) -> VehicleClass.CAR == trafficEvent.getVehicleClass()))
				.selectKey((key, value) -> value.getSensorId())
				.groupByKey(Grouped.with(Serdes.String(), new JsonSerde<>(TrafficEvent.class)))
				.windowedBy(TimeWindows.of(Duration.ofMinutes(2L)))
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
