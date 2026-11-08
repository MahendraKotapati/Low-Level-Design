import java.util.Map;

public class Intersection {
    private final String id;
    private final Map<Direction, TrafficLight> signals;
    private final Map<Direction, Map<String, Integer>> signalDurations;
    private final TrafficSignalSystemController controller;


    public Intersection(String id, Map<Direction, TrafficLight> signals, Map<Direction, Map<String, Integer>> signalDurations) {
        this.id = id;
        this.signals = signals;
        this.signalDurations = signalDurations;
        this.controller = new TrafficSignalSystemController(signals, signalDurations);
    }

    public void start(Direction direction) {    
        controller.start(direction);
    }

    public void manualOverride(Direction direction) {
        System.out.println("Manual Override: setting " + direction + " to GREEN" );
        controller.manualOverride(direction);
    }

    public TrafficLight getSignal(Direction direction) {
        return signals.get(direction);
    }
}
