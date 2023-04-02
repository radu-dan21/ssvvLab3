package ssvv.example.addAssignment;

import org.junit.Assert;
import org.junit.Test;
import ssvv.example.addAssignment.base.BaseTestAddAssignment;
import ssvv.example.domain.Tema;

import java.util.Collection;

public class TestAddValidAssignment extends BaseTestAddAssignment {
    private static Tema getValidAssignment() {
        int saptDeadline = 10;
        int saptPrimire = 1;
        return new Tema("nrTema", "descriereTema", saptDeadline, saptPrimire);
    }

    @Test
    public void testAddAssignmentPasses() {
        Tema t = getValidAssignment();

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 0);
        Assert.assertNull(service.addTema(t));
        Assert.assertEquals(repository.findOne(t.getID()), t);

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
    }

    @Test
    public void testAddDuplicateAssignmentFails() {
        Tema t = getValidAssignment();

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 0);
        Assert.assertNull(service.addTema(t));
        Assert.assertEquals(repository.findOne(t.getID()), t);

        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
        Assert.assertEquals(service.addTema(t), t);
        Assert.assertEquals(((Collection<?>) repository.findAll()).size(), 1);
    }
}
