package ro.bar.sanymotors.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ro.bar.sanymotors.service.GenericService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-sany-motors-config.xml")
@Transactional
public class CreateTables {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private GenericService genericService;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Value("${database.schema}")
	private String dbSchema;
	
	@Test
	public void createTables(){
		getCurrentSession().createSQLQuery("CREATE  TABLE "+ dbSchema +".IMAGE (ELEMENTID INT NOT NULL AUTO_INCREMENT ,CONTENT LONGBLOB NOT NULL ,PRIMARY KEY (ELEMENTID) );").executeUpdate();
		getCurrentSession().createSQLQuery("CREATE  TABLE "+ dbSchema +".CATEGORY (ELEMENTID INT NOT NULL AUTO_INCREMENT ,NAME VARCHAR(2000) NOT NULL, PRIMARY KEY (ELEMENTID) );").executeUpdate();
		getCurrentSession().createSQLQuery("CREATE  TABLE "+ dbSchema +".POST(ELEMENTID INT NOT NULL AUTO_INCREMENT,TITLE VARCHAR(2000) NOT NULL ,DESCRIPTION VARCHAR(4000) NULL, DATE_CREATED VARCHAR(200),STATE INT NOT NULL, PRICE VARCHAR(200),"
				+ "IMAGE_FK INT, CATEGORY_FK INT, PRIMARY KEY (ELEMENTID), CONSTRAINT FK_POST_CATEGORY FOREIGN KEY (CATEGORY_FK) REFERENCES CATEGORY(ELEMENTID), CONSTRAINT FK_POST_IMAGE FOREIGN KEY (IMAGE_FK) REFERENCES IMAGE(ELEMENTID));").executeUpdate();
		getCurrentSession().createSQLQuery("CREATE  TABLE "+ dbSchema +".ADDITIONALIMAGES (POST_FK INT NOT NULL ,IMAGE_FK INT NOT NULL, CONSTRAINT FK_POST_IMAGES FOREIGN KEY (IMAGE_FK) REFERENCES IMAGE(ELEMENTID),"
				+ "CONSTRAINT FK_IMAGE_POSTS FOREIGN KEY (POST_FK) REFERENCES POST(ELEMENTID));").executeUpdate();
		getCurrentSession().createSQLQuery("CREATE  TABLE "+ dbSchema +".ATTRIBUTE (ELEMENTID INT NOT NULL AUTO_INCREMENT ,NAME VARCHAR(2000) NOT NULL, VALUE VARCHAR(200), POST_FK INT, INTERFACEVALUE INT,"
				+ "PRIMARY KEY (ELEMENTID), CONSTRAINT FK_POST_ATTRIBUTE FOREIGN KEY (POST_FK) REFERENCES POST(ELEMENTID));").executeUpdate();
	}
	
	
	@Test
	public void deleteTables(){
		getCurrentSession().createSQLQuery("DROP SCHEMA "+ dbSchema).executeUpdate();

	}
	
}
