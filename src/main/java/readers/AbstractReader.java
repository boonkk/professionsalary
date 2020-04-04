package readers;

import model.EmployeesContainer;

import java.io.FileNotFoundException;

public abstract class AbstractReader {
    protected final String path;

    public AbstractReader(String path) {
        this.path = path;
    }

    public abstract EmployeesContainer read();
}
