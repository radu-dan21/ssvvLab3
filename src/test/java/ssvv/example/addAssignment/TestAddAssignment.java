package ssvv.example.addAssignment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Tema;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.utils.XMLRepoOperations;

import java.util.Collection;

public class TestAddAssignment {
    private static TemaXMLRepo repository = XMLRepoOperations.getAssignmentsRepo();

    private static Tema getValidAssignment() {
        int saptDeadline = 10;
        int saptPrimire = 1;
        return new Tema("nrTema", "descriereTema", saptDeadline, saptPrimire);
    }

    @Before
    public void setup() {
        XMLRepoOperations.reInitRepos();
        repository = XMLRepoOperations.getAssignmentsRepo();
    }

    @Test
    public void testAddAssignmentPasses() {
        Tema t = getValidAssignment();

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 0);
        Assert.assertNull(repository.save(t));
        Assert.assertEquals(repository.findOne(t.getID()), t);

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
    }

    @Test
    public void testAddDuplicateAssignmentFails() {
        Tema t = getValidAssignment();

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 0);
        Assert.assertNull(repository.save(t));
        Assert.assertEquals(repository.findOne(t.getID()), t);

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
        Assert.assertEquals(repository.save(t), t);
        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
    }
}
