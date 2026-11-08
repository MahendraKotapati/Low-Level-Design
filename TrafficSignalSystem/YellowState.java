public class YellowState implements SignalState {
    @Override
    public String getName() {
        return "YELLOW";
    }

    @Override 
    public void handle(TrafficLight light, TrafficSignalSystemController controller, Direction direction) {
        System.out.println("Direction: " + direction + " | State: YELLOW");
        int waitDuration = controller.getSignalDuration(direction, this);
        controller.scheduleStateChange(waitDuration, light, new RedState());
    }
}
