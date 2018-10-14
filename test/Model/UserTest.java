/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BEST WAY
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkValidation method, of class User.
     * enter a valid data checked before saved to database
     */
    @Test
    public void testCheckValidation() {
        System.out.println("checkValidation");
        User user = new User("Youmna Ashraf","youmna@gmail.com","01111111111","123456");
        String expResult = "";
        String result = user.checkValidation(user);
        assertEquals(expResult, result);
    }
   
    //enter a not valid email, name, password and mobile number
    @Test
    public void testCheckValidation1() {
        System.out.println("checkValidation");
        User user = new User("3aly","3ali ahmed","486655448","    ");
        String expResult = "Enter a valid Email <br>"+"Enter a valid Mobile Number <br>"+"Enter a valid Name <br>"+"Your password must contains any characters";
        String result = user.checkValidation(user);
        assertEquals(expResult, result);
    }
    //enter a not valid email, name, mobile number and password less than 6 charachters
    @Test
    public void testCheckValidation2() {
        System.out.println("checkValidation");
        User user = new User("3aly","3ali ahmed","486655448","123");
        String expResult = "Enter a valid Email <br>"+"Enter a valid Mobile Number <br>"+"Enter a valid Name <br>"+"Your password must be at least 6 characters";
        String result = user.checkValidation(user);
        assertEquals(expResult, result);
    }
    /**
     * Test of addUser method, of class User.
     * test for a new user to be added not exist before in database
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        User user = new User("Youmna Ashraf","youmna12@gmail.com","01111111111","123456");
        String expResult = "";
        String result = user.addUser(user);
        assertEquals(expResult, result);
      }
    //if this email is waiting for confirmation from the admin
    @Test
    public void testAddUser1() throws Exception {
        System.out.println("addUser");
        User user = new User("Youmna Ashraf","youmna12@gmail.com","01111111111","123456");
        String expResult = "Wait for confirmation";
        String result = user.addUser(user);
        assertEquals(expResult, result);
      }
    //if this email s already a member
     @Test
    public void testAddUser2() throws Exception {
        System.out.println("addUser");
        User user = new User("Youmna Ashraf","youmna@gmail.com","01111111111","123456");
        String expResult = "You are already a user";
        String result = user.addUser(user);
        assertEquals(expResult, result);
    }
    /**
     * Test of loginValidation method, of class User.
     * check for a user login already existed in database
     */
    @Test
    public void testLoginValidation() throws Exception {
        System.out.println("loginValidation");
        String email = "youmna@gmail.com";
        String password = "123456";
        User instance = new User();
        String expResult = "Done";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    //if email not found in database
    @Test
    public void testLoginValidation1() throws Exception {
        System.out.println("loginValidation");
        String email = "youmna22@gmail.com";
        String password = "123456";
        User instance = new User();
        String expResult = "You aren't a User, SignUp please ";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    //if password  is wrong
    @Test
    public void testLoginValidation2() throws Exception {
        System.out.println("loginValidation");
        String email = "youmna@gmail.com";
        String password = "12345666";
        User instance = new User();
        String expResult = "Wrong password";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    //if login is Admin
     @Test
    public void testLoginValidation3() throws Exception {
        System.out.println("loginValidation");
        String email = "Admin";
        String password = "admin";
        User instance = new User();
        String expResult = "Admin";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    //if login is Admin and password wrong
     @Test
    public void testLoginValidation4() throws Exception {
        System.out.println("loginValidation");
        String email = "Admin";
        String password = "123456";
        User instance = new User();
        String expResult = "Wrong password";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    //if login isn't accepted by the admin
     @Test
    public void testLoginValidation5() throws Exception {
        System.out.println("loginValidation");
        String email = "youmna12@gmail.com";
        String password = "123456";
        User instance = new User();
        String expResult = "Wait for confirmation";
        String result = instance.loginValidation(email, password);
        assertEquals(expResult, result);
    }
    /**
     * Test of reject method, of class User.
     * if sent id doesn't existed in database
     */
    @Test
    public void testReject() throws Exception {
        System.out.println("reject");
        int id = 0;
        User instance = new User();
        String expResult = "Something went wrong, the user doesn't exist in database";
        String result = instance.reject(id);
        assertEquals(expResult, result);
          }
    
    // if sent id doesn't exist in database
    @Test
    public void testReject1() throws Exception {
        System.out.println("reject");
        int id = 7;
        User instance = new User();
        String expResult = "Your deleted the user, successfully";
        String result = instance.reject(id);
        assertEquals(expResult, result);
          }

    /**
     * Test of accept method, of class User.
     * if sent id doesn't exist in database
     */
    @Test
    public void testAccept() throws Exception {
        System.out.println("accept");
        int id = 0;
        User instance = new User();
        String expResult = "Something went wrong, the user doesn't exist in database";
        String result = instance.accept(id);
        assertEquals(expResult, result);
    }
    //if sent id exist in database
    @Test
    public void testAccept1() throws Exception {
        System.out.println("accept");
        int id = 4;
        User instance = new User();
        String expResult = "You accepted the user,Successfully";
        String result = instance.accept(id);
        assertEquals(expResult, result);
    }
    
}
