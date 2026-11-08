public class GreenState implements SignalState {
    @Override
    public String getName() {
        return "GREEN";
    }

    @Override 
    public void handle(TrafficLight light, TrafficSignalSystemController controller, Direction direction) {
        System.out.println("Direction: " + direction + " | State: GREEN");

        int waitDuration = controller.getSignalDuration(direction, this);
        controller.scheduleStateChange(waitDuration, light, new YellowState());
    }
}
