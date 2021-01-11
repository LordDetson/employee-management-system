package by.babanin.ems.resource;

public enum EmployeeResource implements Resource {

    EMPTY_LIST("The list of employees is empty. Please add new employees.");

    private final String resource;

    EmployeeResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String get() {
        return resource;
    }
}
