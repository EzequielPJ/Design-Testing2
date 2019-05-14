
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Box;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class BoxServiceTest extends AbstractTest {

	@Autowired
	private BoxService	boxService;


	/*
	 * Requirement tested: An actor who is authenticated must be able to:
	 * Update a box that is not system box.
	 * Total instructions: 277
	 * Covered instructions: 53
	 * Analysis of sentence coverage of boxService: 19.1%
	 * Analysis of data coverage: 100%
	 * Attribute: name | Bad value: null | Normal value: Yes | Coverage: 100% |
	 */
	@Test
	public void ActorTest() {
		final Object testingData[][] = {
			{
				//Positive test: Update a box
				"company1", "pruebaBox", "box31", null
			}, {
				//Negative test: The business rule that has been violated: Box name can not be null
				"company1", "", "box31", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], super.getEntityId((String) testingData[i][2]), (Class<?>) testingData[i][3]);
	}
	protected void template(final String username, final String name, final int idBox, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			//Update a box
			Box b;
			b = this.boxService.createBox();
			b.setName(name);
			this.boxService.save(b);
			this.boxService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
