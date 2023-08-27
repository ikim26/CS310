
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;






public class P5Tester {
  public static void main(String args[]){
      org.junit.runner.JUnitCore.main("P5Tester");
    }
   
  
  static final double ERROR = 0.000001;
 
 
private static ByteArrayOutputStream localOut, localErr;
private static ByteArrayInputStream localIn;
private static PrintStream sysOut, sysErr;
private static InputStream sysIn;
public static final String [] empty = {};

//@BeforeClass
public static void setUp() {
  sysOut = System.out;
  sysErr = System.err;
  sysIn  = System.in;
}

//@AfterClass
public static void cleanUp() {
	  unsetCapture();
	  unsetInput();
}

public static void resetIO() {
	  setCapture();
	  setInput("");
}

// Before every test is run, reset the streams to capture
// stdout/stderr
//@Before
public static void setCapture() {
  localOut = new ByteArrayOutputStream();
  localErr = new ByteArrayOutputStream();
  System.setOut(null);
  System.setErr(null);
  System.setOut(new PrintStream(localOut));
  System.setErr(new PrintStream(localErr));
}

public static String getCapture() {
  return localOut.toString().replaceAll("\\r?\\n", "\n");
}

// After every test, restore stdout/stderr
//@After
public static void unsetCapture() {
  System.setOut(null);
  System.setOut(sysOut);
  System.setErr(null);
  System.setErr(sysErr);
}

public static void setInput(String input) {
  localIn = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
  System.setIn(localIn);
}

public static void unsetInput() {
  try {
    if (localIn != null) localIn.close();
  }
  catch (Exception e) {}
  System.setIn(null);
  System.setIn(sysIn);
}




	Driver d = new Driver();
	AVLTree<Integer> tree;
	Integer dataAdd[][]= {{5,2,8,1,3,7,10,4,9,6,11,12}, {12,9,15,18,11,6,7, 5}, {9,2,11,6,1,10,12,13},{20,4,26,3,9,21,2},{9, 4, 25, 3, 7, 11, 26, 2, 18, 30}};	
    String outputAdd[] = {"5 2 8 1 3 7 10 4 6 9 12 11 25 ", "12 7 15 6 9 18 5 8 11 ","9 2 11 1 6 10 13 12 15 ","20 4 26 2 9 21 1 3 ","9 4 25 3 7 17 26 2 11 18 30 "};
	Integer dataRemove[][]= {{5, 2, 8, 1, 3, 7, 10, 4, 6,  12, 11, 25}, {15, 9, 17, 5 ,16, 13, 7, 14, 26,27,8}};
    String outputRemove[]= {"5 2 8 1 3 7 12 4 6 11 25 ","15 7 17 5 9 16 26 8 27 "};
	
    
    @Test(timeout = 100) public void Test_add_to_RL_rotation() {
		tree = d.make_tree(dataAdd[4]);
		tree.add(17);
		assertEquals("Add 17 with Right-Left double  rotation is not working", outputAdd[4],d.display(tree));
	}

	@Test(timeout = 100) public void Test_add_for_Right_rotation() {
		tree = d.make_tree(dataAdd[3]);
		tree.add(1);
		assertEquals("Add 1 with Right  rotation is not working", outputAdd[3],d.display(tree));
}
	

	@Test(timeout = 100) public void Test_add_for_LR_rotation() {
		
		
			tree = d.make_tree(dataAdd[1]);
			tree.add(8);
			assertEquals("Add 8 with Left-Right double rotation is not working", outputAdd[1],d.display(tree));
	}

@Test(timeout = 100) public void Test_add_for_Left_rotation() {
	
			tree = d.make_tree(dataAdd[0]);
			tree.add(25);
			assertEquals("Add 25 with Left rotation is not working", outputAdd[0],d.display(tree));
	}
	
@Test(timeout = 100)
public void Test_remove_with_LeftRotation() {
	tree = d.make_tree(dataRemove[0]);
	tree.remove(10);
	assertEquals("Remove 10 with Left rotation is not working", outputRemove[0],d.display(tree));
}

@Test(timeout = 100)
public void Test_remove_with_RightRotation() {
	tree = d.make_tree(dataRemove[1]);
	tree.remove(14);
	tree.remove(13);
	assertEquals("Remove 14 followed by remove 13 with right rotation is not working", outputRemove[1],d.display(tree));
}
}
