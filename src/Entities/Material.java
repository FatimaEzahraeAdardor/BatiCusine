package Entities;

public class Material extends Component {
    private int id;
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double coefficientQuality;
    public Material(String name, String componentType, double vatRate, Project project, double unitCost, double quantity, double transportCost, double coefficientQuality) {
        super( name, componentType, vatRate, project);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.coefficientQuality = coefficientQuality;
    }

    public Material(){}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getCoefficientQuality() {
        return coefficientQuality;
    }

    public void setCoefficientQuality(double coefficientQuality) {
        this.coefficientQuality = coefficientQuality;
    }

    @Override
    public Double calculateTotalCost() {
        Double costBeforeVatRate = (getUnitCost() * getQuantity() * getCoefficientQuality()) + getTransportCost();
        Double costWithVatRate = costBeforeVatRate * (1 + (getVatRate() / 100));
        return costWithVatRate;
    }
}
