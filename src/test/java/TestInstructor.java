import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by LovepreetSangha on 3/8/17.
 */
public class TestInstructor {
    private IStudent student;
    private IAdmin admin;
    private IInstructor instructor;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();
    }

    /*
        Test to see if the instructor can assign a grade
        to a non-registered student in the class
     */

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test",2017,"HW","Here");
        this.student.registerForClass("One","Test",2017);
        this.student.submitHomework("One", "HW","ans","Test",2017);
        this.student.submitHomework("Two", "HW","ans","Test",2017);
        this.instructor.assignGrade("Instructor","Test", 2017, "HW","One",85);
        this.instructor.assignGrade("Instructor","Test", 2017, "HW","Two",85);
        int enrolled = this.instructor.getGrade("Test",2017,"HW","One");
        int notEnrolled = this.instructor.getGrade("Test",2017,"HW","Two");
        assertFalse(enrolled == notEnrolled);

    }

    /*
        Check to see if an instructor can assign a grade
        to a class that they are not teaching and to a
        student that is not in the class
     */

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test2", 2017, "Instructor2", 15);
        this.instructor.addHomework("Instructor", "Test", 2017, "HW", "Here");
        this.instructor.addHomework("Instructor2", "Test2", 2017, "HW2", "Here2");
        this.student.registerForClass("One", "Test", 2017);
        this.student.registerForClass("Two", "Test2", 2017);
        this.student.submitHomework("One", "HW", "ans", "Test", 2017);
        this.student.submitHomework("Two", "HW2", "ans", "Test2", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "One", 85);
        this.instructor.assignGrade("Instructor2", "Test", 2017, "HW", "Two", 85);
        int enrolled = this.instructor.getGrade("Test", 2017, "HW", "One");
        int notEnrolled = this.instructor.getGrade("Test", 2017, "HW", "Two");
        assertFalse(enrolled == notEnrolled);
    }

    /*
        Check to see if an instructor can assign a grade
       to an assignment that hasn't been submitted or
       added
     */
    @Test
    public void testMakeClass3() {
        this.admin.createClass("Test", 2017, "Teach", 15);
        this.student.registerForClass("One","Test",2017);
        this.student.registerForClass("Two","Test",2017);
        int invalid_score = this.instructor.getGrade("Test", 2017,"HW1","One");

        assertTrue(invalid_score == 100);
    }
}

