package be.ordina.workshop.streaming;

import be.ordina.workshop.streaming.domain.TrafficEvent;
import be.ordina.workshop.streaming.domain.VehicleClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Tom Van den Bulck
 */
@Component
//lab 2: add something over here
@EnableBinding(Source.class)
@EnableScheduling
public class TrafficDataEmitter {

	private static final Logger logger = LoggerFactory.getLogger(TrafficDataRetriever.class);

	//private final TrafficDataConverter trafficDataConverter = new TrafficDataConverter();
	//private final TrafficDataRetriever trafficDataRetriever;

	//lab 2: create a constructor and inject something over here
	private final Source source;

	public TrafficDataEmitter(Source source) {
		//this.trafficDataRetriever = trafficDataRetriever;
		this.source = source;
	}

	@Scheduled(fixedRate = 60_000L)
	public void sendTrafficEvents() {
		logger.info("send traffic events");
		//lab 2: send out the events
		this.getTrafficDataEventsAsList().stream()
				.map(trafficEvent -> MessageBuilder.withPayload(trafficEvent).build())
				.forEach(message -> this.source.output().send(message));
	}

	private List<TrafficEvent> getTrafficDataEventsAsList() {

		List<TrafficEvent> randomTrafficEvents = new ArrayList<>();

		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 1, 50, 80, 150
				, "s1", "E19 Mechelen Noord Left Lane", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 0, 0, 80, 90
				, "s1", "E19 Mechelen Noord Left Lane", new Date(), new Date()));

		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 10, 80, 60, 130
				, "s2", "E19 Mechelen Noord Center Lane", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 2, 4, 50, 90
				, "s2", "E19 Mechelen Noord Center Lane", new Date(), new Date()));

		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 100, 500, 10, 125
				, "s3", "E19 Mechelen Noord Right", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 5, 50, 10, 90
				, "s3", "E19 Mechelen Noord Right", new Date(), new Date()));


		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 1, 50, 80, 150
				, "s11", "E19 Mechelen Zuid Left Lane", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 0, 0, 80, 90
				, "s11", "E19 Mechelen Zuid Left Lane", new Date(), new Date()));

		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 10, 80, 60, 130
				, "s12", "E19 Mechelen Zuid Center Lane", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 2, 4, 50, 90
				, "s12", "E19 Mechelen Zuid Center Lane", new Date(), new Date()));

		randomTrafficEvents.add(createTrafficEvent(VehicleClass.CAR, 100, 500, 10, 125
				, "s13", "E19 Mechelen Zuid Right", new Date(), new Date()));
		randomTrafficEvents.add(createTrafficEvent(VehicleClass.TRUCK_OR_BUS, 5, 50, 10, 90
				, "s13", "E19 Mechelen Zuid Right", new Date(), new Date()));


		return randomTrafficEvents;
	}

	private TrafficEvent createTrafficEvent(VehicleClass vehicleClass, int trafficIntensityMin, int trafficIntensityMax, int vehicleSpeedCalculatedMin, int vehicleSpeedCalculatedMax
			, String sensorId, String sensorDescription, Date timeRegistration, Date lastUpdated) {

		Random random = new Random();
		int trafficIntensityCalculated = random.nextInt((trafficIntensityMax - trafficIntensityMin)+1) + trafficIntensityMin;
		int vehicleSpeedCalculated = random.nextInt((vehicleSpeedCalculatedMax - vehicleSpeedCalculatedMin)+1) + vehicleSpeedCalculatedMin;

		return new TrafficEvent(vehicleClass, trafficIntensityCalculated, vehicleSpeedCalculated, vehicleSpeedCalculated,
				sensorId, sensorDescription, 0, timeRegistration, lastUpdated, false, false);
	}

	/** I no longer work because MIV meetpunt data is no longer working :-(
	 *
	 * private Flux<TrafficEvent> getTrafficDataEvents() {
		return this.trafficDataRetriever.getTrafficData()
				.map(Miv::getMeetpunt)
				.flatMapIterable(Function.identity())
				.flatMap(meetpunt ->
					Flux.fromStream(meetpunt.getMeetdata().stream()
							.map(meetdata -> Tuples.of(meetpunt, meetdata))))
				.map(trafficDataConverter::convertToTrafficEvent);
	}
	 */

}
