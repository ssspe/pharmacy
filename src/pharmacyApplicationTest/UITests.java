package pharmacyApplicationTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pharmacyApplication.FactoryDAL;
import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.PrescriptionUI;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class UITests {
	private InterfaceDAL mockDependency;
	
	@Before
	public void setUp() throws Exception {
		mockDependency = createMock(InterfaceDAL.class);
		FactoryDAL.setInstance(mockDependency);
	}
	
	@After
	public void tearDown() throws Exception {
		verify(mockDependency);
	}

	@Test
	public void test() throws InterruptedException, SQLException {
		List<String> mock = new ArrayList<String>();
		mock.add("Hello");
		mock.add("How");
		mockDependency.connect("jdbc:mysql://localhost:3306/doc-u-med", "spencer", "password");
		expectLastCall();
		expect(mockDependency.getPharmaName()).andReturn(mock);
		
		mockDependency.setCurrentPharmaName("Hello");
		expectLastCall();
		
		mockDependency.setCurrentPharmaName("How");
		expectLastCall();
		
		replay(mockDependency);
		
		PrescriptionUI window = new PrescriptionUI("jdbc:mysql://localhost:3306/doc-u-med", "spencer", "password");
		window.frame.setVisible(true);
		window.frame.setResizable(false);
	}

}
