package be.ordina.workshop.streaming;

import java.util.List;
import java.util.function.Function;

import generated.traffic.Miv;
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
 * @author Tim Ysewyn
 */
@Component
@EnableBinding(Source.class)
@EnableScheduling
public class TrafficDataEmitter {

	private final TrafficDataConverter trafficDataConverter = new TrafficDataConverter();
	private final TrafficDataRetriever trafficDataRetriever;
	private final Source source;

	public TrafficDataEmitter(TrafficDataRetriever trafficDataRetriever, Source source) {
		this.trafficDataRetriever = trafficDataRetriever;
		this.source = source;
	}

	@Scheduled(fixedRate = 5_000L)
	public void sendTrafficEvents() {
		this.getTrafficDataEventsAsList().stream()
				.map(trafficEvent -> MessageBuilder.withPayload(trafficEvent).build())
				.forEach(message -> this.source.output().send(message));
	}

	private List<TrafficEvent> getTrafficDataEventsAsList() {
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
