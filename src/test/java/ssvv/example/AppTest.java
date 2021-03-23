package ssvv.example;


import static org.junit.Assert.*;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    Service service;
    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    @Before
    public void initData(){
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        Validator<Student> studentValidator = new StudentValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void addStudentRepo(){

        assertTrue(service.saveStudent("23", "Samantha", 937) == 0);
        assertEquals(fileRepository1.findOne("23").getNume(), "Samantha");

    }

    public void deleteAllStudents() {
        ArrayList<String> ids = new ArrayList();
        service.findAllStudents().forEach(student -> ids.add(student.getID()));
        for (int i = 0; i < ids.size(); i++) {
            service.deleteStudent(ids.get(i));
        }
    }

    //BBT for addStudentRepo

    @Test
    public void saveStudent00() {
        deleteAllStudents();
        int result = service.saveStudent("10", "Steve", 923);

        assertEquals(1, result);
    }

    @Test
    public void saveStudent01() {
        deleteAllStudents();
        int result = service.saveStudent("", "Mary", 922);

        assertEquals(0, result);
    }

    @Test
    public void saveStudent02() {
        deleteAllStudents();
        int result = service.saveStudent(null, "Mary", 922);
        assertEquals(0, result);
    }

    @Test
    public void saveStudent03() {
        deleteAllStudents();
        int result = service.saveStudent("2", "", 922);

        assertEquals(0, result);
    }
    @Test
    public void saveStudent04() {
        deleteAllStudents();
        int result = service.saveStudent("4", "Mary", 109);

        assertEquals(0, result);
    }
    @Test
    public void saveStudent05() {
        deleteAllStudents();
        int result = service.saveStudent("5", "Mary", 111);

        assertEquals(1, result);
    }
    @Test
    public void saveStudent06() {
        deleteAllStudents();
        int result = service.saveStudent("9", "Mary", 939);

        assertEquals(0, result);
    }

    @Test
    public void addTemaRepo(){

        assertTrue(service.saveTema("5", "test", 7, 5) == 0);
        assertEquals(fileRepository2.findOne("5").getDescriere(), "test");

    }

    /**
     * Rigorous Test :-     )
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
