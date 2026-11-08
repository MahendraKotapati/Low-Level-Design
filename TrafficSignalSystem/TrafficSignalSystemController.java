import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficSignalSystemController {
    private final Map<Direction, TrafficLight> signals;
    private final Map<Direction, Map<String, Integer>> signalDurations;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public TrafficSignalSystemController(Map<Direction, TrafficLight> signals, Map<Direction, Map<String, Integer>> signalDurations) {
        this.signals = signals;
        this.signalDurations = signalDurations;
    }

    public void start(Direction direction) {
        TrafficLight light = this.getTrafficLight(direction);
        light.setState(new GreenState());
        light.handle(this);
    }

    public TrafficLight getTrafficLight(Direction direction) {
        return signals.get(direction);
    }

    public int getSignalDuration(Direction direction, SignalState signalState) {
        return signalDurations.get(direction).get(signalState.getName());
    }
    

    public Direction getNextDirection(Direction currentDirection) {
        Direction[] directions = Direction.values();
        int nextDirection = (currentDirection.ordinal()+1) % directions.length; 
        return directions[nextDirection];
    }

    public void scheduleStateChange(int delaySeconds, TrafficLight light, SignalState nextState) {
        scheduler.schedule(() -> {
            light.setState(nextState);
            light.handle(this);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    public void manualOverride(Direction direction) {

        // immediately set the specified direction to GREEN and start its cycle
        TrafficLight light = signals.get(direction);
        light.setState(new GreenState());
        light.handle(this);
    }
}
