package BatiCuisine.Entities;

public class Labor extends Component {
    private int id;
    private double hourlyCost;
    private double workingHours;
    private double workerProductivity;
    public Labor( String name, String componentType, double vatRate, Project project, double hourlyCost, double workingHours, double workerProductivity) {
        super(name, componentType, vatRate, project);
        this.hourlyCost = hourlyCost;
        this.workingHours = workingHours;
        this.workerProductivity = workerProductivity;
    }

    public Labor(){

    }

    public double getHourlyCost() {
        return hourlyCost;
    }

    public void setHourlyCost(double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }


    @Override
    public Double calculateTotalCost() {
        return null;
    }
}
