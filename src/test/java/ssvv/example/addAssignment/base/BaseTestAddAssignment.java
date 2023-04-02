package ssvv.example.addAssignment.base;

import org.junit.Before;
import org.junit.Ignore;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.utils.XMLRepoServiceOperations;

@Ignore("Base class for concrete addAssignment test classes")
public class BaseTestAddAssignment {
    protected TemaXMLRepo repository = XMLRepoServiceOperations.getAssignmentRepo();
    protected Service service = XMLRepoServiceOperations.getService();

    @Before
    public void setup() {
        XMLRepoServiceOperations.reInitObjects();
        service = XMLRepoServiceOperations.getService();
        repository = XMLRepoServiceOperations.getAssignmentRepo();
    }
}
