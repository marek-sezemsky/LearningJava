/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person.sezemsky.marek.learning.java;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class RandomPersonGeneratorTest {
    
    public RandomPersonGeneratorTest() {
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
     * Test of getRandomPerson method, of class RandomPersonGenerator.
     */
    @Test
    public void test_notNull() {
        assertNotNull(new RandomPersonGenerator());
    }
    
    
    /**
     * Test of getRandomPerson method with provided random generator.
     */
    @Test
    public void test_notNullWithRandom() {
        assertNotNull(new RandomPersonGenerator(new Random()));
    }
    
    
}
