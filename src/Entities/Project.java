package BatiCuisine.Entities;
import BatiCuisine.Enums.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String projectName;
    private double profitMargin;
    private double totalCost;
    private ProjectStatus status;
    private Client client;
    List<Component> components;

    public Project(String projectName, double profitMargin, double totalCost, ProjectStatus status, Client client) {
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.components = new ArrayList<>();
        this.status = status;
        this.client = client;

    }

    public Project() {

    }


    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", profitMargin=" + profitMargin +
                ", totalCost=" + totalCost +
                ", status=" + status +
                ", client=" + client +
                ", components=" + components +
                '}';
    }
}
