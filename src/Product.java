import java.util.List;

public class Product {
    private String name;
    private String steelBrand;
    private String workshop;
    private List<Parameter> parameters;
    private List<PhysicsParameter> physicsParameters;
    private String customer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteelBrand() {
        return steelBrand;
    }

    public void setSteelBrand(String steelBrand) {
        this.steelBrand = steelBrand;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<PhysicsParameter> getPhysicsParameters() {
        return physicsParameters;
    }

    public void setPhysicsParameters(List<PhysicsParameter> physicsParameters) {
        this.physicsParameters = physicsParameters;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}

