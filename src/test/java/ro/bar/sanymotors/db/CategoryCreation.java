package ro.bar.sanymotors.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.bar.sanymotors.model.impl.CategoryImpl;
import ro.bar.sanymotors.service.GenericService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-sany-motors-config.xml")
public class CategoryCreation {

	@Autowired
	private GenericService genericService;
	
	@Test
	public void testAddCategories(){
		CategoryImpl category = new CategoryImpl();
		category.setElementId(1);
		category.setName("Sport/Touring");
		genericService.addEntity(category);
		category = new CategoryImpl();
		category.setElementId(2);
		category.setName("Chopper/Cruiser");
		genericService.addEntity(category);
		category = new CategoryImpl();
		category.setElementId(3);
		category.setName("Street/Naked");
		genericService.addEntity(category);
		category = new CategoryImpl();
		category.setElementId(4);
		category.setName("Cross Enduro");
		genericService.addEntity(category);
		category = new CategoryImpl();
		category.setElementId(5);
		category.setName("Scooter");
		genericService.addEntity(category);
		category = new CategoryImpl();
		category.setElementId(6);
		category.setName("ATV/Quad");
		genericService.addEntity(category);
	}
}
