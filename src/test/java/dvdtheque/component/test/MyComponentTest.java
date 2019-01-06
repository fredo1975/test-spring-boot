package dvdtheque.component.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dvdtheque.component.MyComponent;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyComponentTest {
	protected Logger logger = LoggerFactory.getLogger(MyComponentTest.class);
	/*
	@Autowired
    private FilmDao filmDao;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void findAllFilms() {
		Query query = this.entityManager.getEntityManager().createQuery("from Film film left join fetch film.acteurs left join fetch film.realisateurs real ");
		List<Film> films = new ArrayList<Film>(new HashSet<Film>(query.getResultList()));
		assertNotNull(films);
		logger.info("films.size()="+films.size());
	}*/
	
	@Autowired
	MyComponent myComponent;
	@Test
	public void myComp() {
		logger.info(myComponent.sayHello());
	}
	
}
