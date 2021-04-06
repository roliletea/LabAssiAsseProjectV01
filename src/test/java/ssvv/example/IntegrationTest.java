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

public class IntegrationTest {

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
    public void addAssignmentIntegration(){
        int result = service.saveTema("4", "descriere", 5, 2);
        assertEquals(0, result);
    }

    @Test
    public void addStudentIntegration(){
        int result = service.saveStudent("10", "Steve", 923);
        assertEquals(0, result);
    }

    @Test
    public void addGradeIntegration(){
        int result = service.saveNota("10", "4", 10, 4, "testing");
        assertEquals(0, result);
    }

    @Test
    public void addIntegrateAllTC() {
        addAssignmentIntegration();
        addStudentIntegration();
        addGradeIntegration();
    }
}
