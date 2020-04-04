package readers;

import model.EmployeesContainer;

public abstract class AbstractReader {
    final String path;

    AbstractReader(String path) {
        this.path = path;
    }

    public abstract EmployeesContainer read();
}
