package readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import consolewriters.ConsoleWriter;
import model.Employee;
import model.EmployeesContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JsonFileReader extends AbstractReader {
    public JsonFileReader(String path) {
        super(path);
    }

    public EmployeesContainer read(){
        try {
            return deserializeWithPropertyName();
        } catch(IOException e){
            try {
                return deserializeWithoutPropertyName();
            } catch (IOException ex) {
                return new EmployeesContainer();
            }
        }
    }

    private EmployeesContainer deserializeWithPropertyName() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), EmployeesContainer.class);
    }

    /* Another method which deserializes JSON file if it doesn't contain "employees:" property inside.
       For JSON files like:
     [
      {
        "id": 1,
        "name": "Mark",
        ...
      },
        ... */
    private EmployeesContainer deserializeWithoutPropertyName() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = mapper.readValue(new File(path), new TypeReference<List<Employee>>(){});
        return new EmployeesContainer(employeeList);
    }
}
