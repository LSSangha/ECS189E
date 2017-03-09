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
public class TestAdmin {
    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {

        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    /*
        Check to see if an instructor could teach more than 2 classes per year
     */
    @Test
    public void testMakeClass2()  {
        this.admin.createClass("One",2017, "Instructor", 15);
        this.admin.createClass("Two",2017, "Instructor", 15);
        this.admin.createClass("Three",2017, "Instructor", 15);
        boolean check = this.admin.classExists("One",2017)&&this.admin.classExists("Two",2017)&&this.admin.classExists("Three",2017);
        assertFalse(check);
    }

    /*
        Classroom with size 0 exists
     */
    @Test
    public void testMakeClass3()  {
        this.admin.createClass("One",2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    /*
        Check to see if the new change in
        capacity is less than or equal to
        old capacity.
     */
    @Test
    public void testMakeClass4() {
        this.admin.createClass("Test", 2017, "Instructor", 3);
        this.student.registerForClass("One", "Test",2017);
        this.student.registerForClass("One","Test", 2017);
        this.student.registerForClass("One","Test",2017);
        int cap = this.admin.getClassCapacity("Test", 2017);
        System.out.println(cap);
        this.admin.changeCapacity("Test",2017,2);
        int cap2 = this.admin.getClassCapacity("Test", 2017);
        assertTrue(cap <= cap2);
    }

}
