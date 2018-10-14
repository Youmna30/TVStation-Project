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
public class TVStationTest {
    
    public TVStationTest() {
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
     * Test of checkValidation method, of class TVStation.
     */
    //enter a valid name, url and country
    @Test
    public void testCheckValidation() {
        System.out.println("checkValidation");
        TVStation station = new TVStation("dmc","https://www.youtube.com/watch?v=ajSo3572H5Y","Egypt");
        String expResult = "";
        String result = station.checkValidation(station);
        assertEquals(expResult, result);
      
    }
    //enter a not valid name, url and country
   @Test
    public void testCheckValidation1() {
        System.out.println("checkValidation");
        TVStation station = new TVStation("123","www.google.com","egy1222");
        String expResult = "Enter a valid Name <br>"+"Enter a valid Youtube URL <br>"+"Enter a valid Country <br>";
        String result = station.checkValidation(station);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of addStation method, of class TVStation.
     */
    // enter a name ,url and country that accept to added database
    @Test
    public void testAddStation() throws Exception {
        System.out.println("addStation");
        TVStation station = new TVStation("dmc","https://www.youtube.com/watch?v=ajSo3572H5Y","Egypt");;
        String userEmail = "youmna@gmail.com"; //must be existed in database
        String expResult = "";
        String result = station.addStation(station, userEmail);
        assertEquals(expResult, result);
    }
    // enter a name ,url and country that aren't accepted by the admin
    @Test
    public void testAddStation1() throws Exception {
        System.out.println("addStation");
        TVStation station = new TVStation("dmc","https://www.youtube.com/watch?v=ajSo3572H5Y","Egypt");;
        String userEmail = "youmna@gmail.com"; //must be existed in database
        String expResult = "Wait for confirmation";
        String result = station.addStation(station, userEmail);
        assertEquals(expResult, result);
    }
    // enter a name ,url and country that are already existed
    @Test
    public void testAddStation2() throws Exception {
        System.out.println("addStation");
        TVStation station = new TVStation("dmc","https://www.youtube.com/watch?v=ajSo3572H5Y","Egypt");;
        String userEmail = "youmna@gmail.com"; //must be existed in database
        String expResult = "This station already existed";
        String result = station.addStation(station, userEmail);
        assertEquals(expResult, result);
    }
    /**
     * Test of reject method, of class TVStation.
     */
    // if sent id exists in database 
    @Test
    public void testReject() throws Exception {
        System.out.println("reject");
        int id = 4;
        TVStation instance = new TVStation();
        String expResult = "You rejected the station,Successfully";
        String result = instance.reject(id);
        assertEquals(expResult, result);
      
    }
       // if sent id doesn't exist in database 
    @Test
    public void testReject1() throws Exception {
        System.out.println("reject");
        int id = 0;
        TVStation instance = new TVStation();
        String expResult = "Something went wrong, the id not found in database";
        String result = instance.reject(id);
        assertEquals(expResult, result);
      
    }
    /**
     * Test of accept method, of class TVStation.
     */
    // if sent id exists in database 
    
    @Test
    public void testAccept() throws Exception {
        System.out.println("accept");
        int id = 4;
        TVStation instance = new TVStation();
        String expResult = "You added the station,Successfully";
        String result = instance.accept(id);
        assertEquals(expResult, result);
      
    }
      // if sent id doesn't exist in database 
    
    @Test
    public void testAccept1() throws Exception {
        System.out.println("accept");
        int id = 4;
        TVStation instance = new TVStation();
        String expResult = "Something went wrong, the id not found in database";
        String result = instance.accept(id);
        assertEquals(expResult, result);     
    }

    /**
     * Test of getUrl method, of class TVStation.
     */
    // if id doesn't exist
    // if id exists the function will resturn the url from database
    @Test
    public void testGetUrl_int() throws Exception {
        System.out.println("getUrl");
        int id = 0;
        TVStation instance = new TVStation();
        String expResult = "";
        String result = instance.getUrl(id);
        assertEquals(expResult, result);
    
    }
    
}
