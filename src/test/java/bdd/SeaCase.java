package bdd;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SeaCase {

  public static Test suite() {

	 TestSuite suite = new TestSuite();
    suite.addTestSuite(Login.class);
    suite.addTestSuite(TestPriv.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
