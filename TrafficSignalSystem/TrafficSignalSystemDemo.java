import java.util.EnumMap;
import java.util.Map;


/*

Problem: https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/traffic-signal.md


Requirements:

1. The traffic signal system should control the flow of traffic at an intersection with multiple roads.
2. The system should support different types of signals, such as red, yellow, and green.
3. The duration of each signal should be configurable and adjustable based on traffic conditions.
4. The system should handle the transition between signals smoothly, ensuring safe and efficient traffic flow.
5. The system should be able to detect and handle emergency situations, such as an ambulance or fire truck approaching the intersection.
6. The system should be scalable and extensible to support additional features and functionality.




*/

public class TrafficSignalSystemDemo {

    public static void main(String args[]) {
        Map<Direction, Map<String , Integer>> signalDurations = new EnumMap<>(Direction.class);
        signalDurations.put(Direction.NORTH, Map.of("GREEN", 4, "YELLOW", 2, "RED", 3));
        signalDurations.put(Direction.SOUTH, Map.of("GREEN", 3, "YELLOW", 2, "RED", 4));
        signalDurations.put(Direction.EAST, Map.of("GREEN", 5, "YELLOW", 2, "RED", 3));
        signalDurations.put(Direction.WEST, Map.of("GREEN", 2, "YELLOW", 2, "RED", 5));


        Map<Direction, TrafficLight> signals = new EnumMap<>(Direction.class);
        for(Direction direction: Direction.values()) {
            signals.put(direction, new TrafficLight(direction));
        }

        Intersection intersection1 = new Intersection("1", signals, signalDurations);
        intersection1.start(Direction.NORTH);
    }    
}
