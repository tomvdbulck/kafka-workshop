package be.ordina.workshop.streaming;

import be.ordina.workshop.streaming.domain.TrafficEvent;
import org.apache.kafka.streams.kstream.KStream;

import org.springframework.cloud.stream.annotation.Input;

//lab 4 - make me
public interface KStreamSink {

    String INPUT = "native-input";

    @Input(INPUT)
    KStream<String, TrafficEvent> input();
}