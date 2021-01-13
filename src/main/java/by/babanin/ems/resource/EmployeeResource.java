package by.babanin.ems.resource;

public enum EmployeeResource implements Resource {

    EMPTY_LIST("The list of employees is empty. Please add new employees."),
    NOT_EXIST("Employee with %s '%s' doesn't exist.")
    ;

    private final String resource;

    EmployeeResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String get() {
        return resource;
    }

    @Override
    public String format(Object... args) {
        return String.format(resource, args);
    }
}
