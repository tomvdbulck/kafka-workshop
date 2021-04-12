package be.ordina.workshop.streaming;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import be.ordina.workshop.streaming.domain.VehicleClass;
import generated.traffic.Miv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import be.ordina.workshop.streaming.domain.TrafficEvent;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Tom Van den Bulck
 */
@Component
@EnableBinding(Source.class)
@EnableScheduling
public class TrafficDataEmitter {

	private static final Logger logger = LoggerFactory.getLogger(TrafficDataRetriever.class);

	private final TrafficDataConverter trafficDataConverter = new TrafficDataConverter();
	private final TrafficDataRetriever trafficDataRetriever;
	private final Source source;

	public TrafficDataEmitter(TrafficDataRetriever trafficDataRetriever, Source source) {
		this.trafficDataRetriever = trafficDataRetriever;
		this.source = source;
	}

	@Scheduled(fixedRate = 5_000L)
	public void sendTrafficEvents() {
		logger.info("send traffic events");
		this.getTrafficDataEventsAsList().stream()
				.map(trafficEvent -> MessageBuilder.withPayload(trafficEvent).build())
				.forEach(message -> this.source.output().send(message));
	}

	private List<TrafficEvent> getTrafficDataEventsAsList() {

		/**TrafficEvent trafficEvent = new TrafficEvent(VehicleClass.CAR, 50, 70, 70,
				"s1", "test sensor", 0, Date.from(Instant.now()), Date.from(Instant.now()),
				false, false);

		return Collections.singletonList(trafficEvent);
		 */


		return this.getTrafficDataEvents().collectList().block();
	}

	private Flux<TrafficEvent> getTrafficDataEvents() {
		return this.trafficDataRetriever.getTrafficData()
				.map(Miv::getMeetpunt)
				.flatMapIterable(Function.identity())
				.flatMap(meetpunt ->
					Flux.fromStream(meetpunt.getMeetdata().stream()
							.map(meetdata -> Tuples.of(meetpunt, meetdata))))
				.map(trafficDataConverter::convertToTrafficEvent);
	}

}
