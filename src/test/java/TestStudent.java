import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by LovepreetSangha on 3/8/17.
 */


public class TestStudent {
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
        Check to see if a student can register for
        a class that hits above the capacity
     */
    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.student.registerForClass("One", "Test",  2017);
        this.student.registerForClass("Two", "Test", 2017);
        boolean is_reg = this.student.isRegisteredFor("Two", "Test", 2017);
        assertFalse(is_reg);
    }

    /*
        Test to see if a non-registered student can
        turn in the homework
     */
    @Test
    public void testMakeClass2()  {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("One", "Test", 2017);
        this.instructor.addHomework("Prof", "Test", 2017, "HW","Here");
        this.student.submitHomework("One", "HW","H","Test", 2017);
        this.student.submitHomework("Two","HW","H","Test",2017);
        assertFalse(this.student.hasSubmitted("Two","HW","Test",2017));
    }


}

