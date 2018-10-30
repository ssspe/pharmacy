package pharmacyApplicationTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pharmacyApplication.FactoryDAL;
import pharmacyApplication.InterfaceDAL;
import pharmacyApplication.PrescriptionUI;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class PrescriptionUITest {
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
	public void test() throws SQLException {
//		expect(mockDependency.connect("sql_url", "username", "password"));
		PrescriptionUI p = new PrescriptionUI("sql_url", "username", "password");
	}

}
