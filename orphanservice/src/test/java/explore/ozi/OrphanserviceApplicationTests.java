package explore.ozi;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrphanserviceApplication.class)
public class OrphanserviceApplicationTests {

	@Autowired
	private CamelContext camelContext;

	@Test
	public void messagesShouldBeProduced() {
		NotifyBuilder notifyTestIsDone = new NotifyBuilder(camelContext).whenDone(1).create();

		Assert.assertTrue(notifyTestIsDone.matches(10, TimeUnit.DAYS.SECONDS));
	}

}
