package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentDetails;
import com.tech.studentapp.model.StudentResponse;
import com.tech.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks
    StudentServiceImpl studentService;
    @Mock
    StudentRepository studentRepository;

    @BeforeAll
    public static void init(){
        System.out.println("Before All...");
    }

    @BeforeEach
    public void inittest(){
       System.out.println("Before Each...");
    }

    @AfterAll
    public static void destroy(){
        System.out.println("After All....");
    }
    @AfterEach
    public void cleanup(){
        System.out.println("After Each");
    }

    @Test
    void saveStudentTest(){
        Student student=new Student();
        List<Student> studentList=List.of(student);
        student.setId(1L);
        student.setStudentName("xyz");
        student.setDoj("10-09-2020");
        student.setDoe("10-04-2025");
        student.setLpa(1600000);
        student.setPlacedCompany("avasoft");

        when(studentRepository.saveAll(studentList)).thenReturn(studentList);

       List<Student> addedStudent=studentService.saveStudent(studentList);
       assertEquals(student.getStudentName(),addedStudent.get(0).getStudentName());
       verify(studentRepository, times(1)).saveAll(studentList);
       System.out.println("test executed");
    }
    @Test
    public void deleteStudentTest() {
        Mockito.doNothing().when(studentRepository).deleteById(1L);
        studentService.deleteStudent(1L);
       verify(studentRepository, times(1)).deleteById(1L);
       System.out.println("Deleted");
    }
    @Test
    void validateStudentNameTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       Method validateName= StudentServiceImpl.class.getDeclaredMethod("validateStudentName",String.class);
       validateName.setAccessible(true);
      Boolean Name= (Boolean) validateName.invoke(studentService,"xyz");
      assertTrue(Name);
    }

    @Test
    void inValidStudentNameTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateName= StudentServiceImpl.class.getDeclaredMethod("validateStudentName",String.class);
        validateName.setAccessible(true);
        Boolean Name= (Boolean) validateName.invoke(studentService,"");
        assertFalse(Name);
    }

    @Test
    void getAllStudentTest(){
        Student student1=Student.builder()
                .id(1L)
                .studentName("abc")
                .doj("10-10-2023")
                .doe("10-10-2028")
                .lpa(600000)
                .placedCompany("HCL")
                .build();

        Student student2=Student.builder()
                .id(2L)
                .studentName("xyz")
                .doj("10-10--2019")
                .doe("10-10-20223")
                .lpa(700000)
                .placedCompany("Wipro")
                .build();

        List<Student> mockedStudent=List.of(student1,student2);
        when(studentRepository.findAll()).thenReturn(mockedStudent);

        List<Student> students=studentService.getAllStudents();

        assertEquals(2,students.size());
        assertEquals("abc",students.get(0).getStudentName());
        assertEquals("xyz",students.get(1).getStudentName());
        verify(studentRepository, times(1)).findAll();

    }

    @Test
    void getStudentByIdTest(){

        StudentDetails studentDetails=new StudentDetails();
        studentDetails.setId(1l);
        studentDetails.setRegNo("REG123");
        studentDetails.setCity("chennai");
        studentDetails.setCgpa(8.88);
        studentDetails.setDepartment("cse");

        Student student=new Student();
        student.setId(2l);
        student.setStudentName("alice");
        student.setDoj("09-09-2024");
        student.setDoe("09-09-2029");
        student.setLpa(500000);
        student.setPlacedCompany("cognizant");
        student.setStudentDetails(studentDetails);

        when(studentRepository.findById(2l)).thenReturn(Optional.of(student));
        StudentResponse response=studentService.getStudentById(2L);

        assertEquals(2l,response.getId());
        assertEquals("alice",response.getStudentName());
        assertEquals("09-09-2024",response.getDoj());
        assertEquals("09-09-2029",response.getDoe());
        assertEquals("cognizant",response.getPlacedCompany());

        assertNotNull(response.getStudentDetailResponse());
        assertEquals("REG123",response.getStudentDetailResponse().getRegNo());
        assertEquals("chennai",response.getStudentDetailResponse().getCity());
        assertEquals(8.88,response.getStudentDetailResponse().getCgpa());
        assertEquals("cse",response.getStudentDetailResponse().getDepartment());

        verify(studentRepository, times(1)).findById(2l);

    }
    @Test
    void getStudentByIdNullTest(){
        Student student = new Student();
        student.setId(2L);
        student.setStudentName("Bob");
        student.setDoj("01-01-2021");
        student.setDoe("01-01-2025");
        student.setLpa(1000000);
        student.setPlacedCompany("Infosys");
        student.setStudentDetails(null);

        when(studentRepository.findById(2L)).thenReturn(Optional.of(student));

        StudentResponse studentResponse=studentService.getStudentById(2L);

        assertEquals("Bob",studentResponse.getStudentName());
        assertNull(studentResponse.getStudentDetailResponse());
        verify(studentRepository, times(1)).findById(2L);

    }
    @Test
    void updateStudentTest(){
        StudentDetails existingDetails=new StudentDetails();
        existingDetails.setId(10L);
        existingDetails.setRegNo("OLD123");
        existingDetails.setCity("OLDcity");
        existingDetails.setDepartment("cse");
        existingDetails.setCgpa(8.89);

        Student existingStudent=new Student();
        existingStudent.setId(1L);
        existingStudent.setStudentName("ram");
        existingStudent.setDoj("01-01-2020");
        existingStudent.setDoe("01-01-2024");
        existingStudent.setLpa(1000000);
        existingStudent.setPlacedCompany("RapidData");
        existingStudent.setStudentDetails(existingDetails);

        //UPDATED STUDENT
        StudentDetails newdetails=new StudentDetails();
        newdetails.setRegNo("OLD123");
        newdetails.setCity("Delhi");
       newdetails.setDepartment("cse");
        newdetails.setCgpa(8.89);

        Student updateData=new Student();
        updateData.setStudentName("ram");
        updateData.setDoj("01-01-2020");
        updateData.setDoe("01-01-2024");
        updateData.setLpa(2000000);
        updateData.setPlacedCompany("RapidData");
        updateData.setStudentDetails(newdetails);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(Mockito.any(Student.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Student updatedStudent=studentService.updateStudent(1L,updateData);

        assertNotNull(updatedStudent);
        assertEquals("ram",updatedStudent.getStudentName());
        assertEquals(2000000,updatedStudent.getLpa());

        assertNotNull(updatedStudent.getStudentDetails());
        assertEquals("Delhi",updatedStudent.getStudentDetails().getCity());
        assertEquals("cse",updatedStudent.getStudentDetails().getDepartment());

        verify(studentRepository, times(1)).findById(1L);

    }



}