package com.ecom;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;



import com.ecom.listy.grocery.item.GroceryItemRepository;
import com.ecom.listy.util.LoadDB_FromExcel;

//import com.ecom.listy.grocery.item.GroceryItemRepository;

//@Import(RepositoryRestMvcConfiguration.class)
//@ImportResource({"classpath*:applicationContext.xml"})
@SpringBootApplication
public class ListyApplication {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoadDB_FromExcel loadDB_FromExcel;
	
    public static void main(String[] args) {
        SpringApplication.run(ListyApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(GroceryItemRepository groceryItemRepository){
    	return args -> {
    		
    		System.out.println(" items :"+groceryItemRepository.findAll().size());
    		
    		if (groceryItemRepository.findAll().size() == 0){
    			
    			System.out.println("load database ...");
    			loadDB_FromExcel.dbLoads();
    		
    			System.out.println(" items :"+groceryItemRepository.findAll().size());
    		}
    		
    	};
    }
}
