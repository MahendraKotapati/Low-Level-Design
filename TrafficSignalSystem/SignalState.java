public interface SignalState {
    String getName();
    void handle(TrafficLight light, TrafficSignalSystemController controller, Direction direction);
}
