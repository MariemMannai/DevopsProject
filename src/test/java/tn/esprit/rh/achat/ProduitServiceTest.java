package tn.esprit.rh.achat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Date;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {
	
	@Mock
	ProduitRepository produitRepository;

	@InjectMocks
	ProduitServiceImpl produitService;
	
	
	@Test
	public void createProduitTest() throws Exception
	{
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date dateCreation = dateFormat.parse("30/09/2021");
	Date dateDerniereModification = dateFormat.parse("30/02/2022");

    Produit produit1 = new Produit(2L,"codeP","fromage",(float) 22.8, dateCreation, dateDerniereModification,null,null,null);

  
	Produit p= produitService.addProduit(produit1);
	verify(produitRepository, times(1)).save(produit1);
	System.out.println(produit1);
	Assertions.assertNotNull(p.getIdProduit());
	System.out.println("Success : product created");
	}

	
	@Test
	 void RetrieveProduitTest() throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("30/09/2021");
		Date dateDerniereModification = dateFormat.parse("30/02/2022");
	
		List<Produit> ProduitList = new ArrayList<Produit>() {

	{
			add(new Produit(2L,"codeP1","fromage",(float) 22.8, dateCreation, dateDerniereModification,null,null,null));
			add(new Produit(2L,"codeP2","tomate",(float) 29.1, dateCreation, dateDerniereModification,null,null,null));
			
	}};


	when(produitService.retrieveAllProduits()).thenReturn(ProduitList);
	List<Produit> regList = produitService.retrieveAllProduits();
	assertEquals(2,regList.size());
	System.out.println(" ---------------------------------------produitService works------------------------------ ");

	}
	
	@Test
    void testRetrieveProduitById() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("30/09/2021");
		Date dateDerniereModification = dateFormat.parse("30/02/2022"); 
		Produit p= new Produit (2L,"codeP3","carotte",(float) 29.1, dateCreation, dateDerniereModification,null,null,null);
		Produit produit=produitService.addProduit(p);
    	Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        Produit prod = produitService.retrieveProduit(1L);
        System.out.println(prod);
        Assertions.assertNotNull(prod);
        System.out.println("--------------------------------------- Test retrieve produit By id works ------------------------------ ");
    }

	   @Test
	     void DeleteProduitTest() throws ParseException {
	    	//MockitoAnnotations.initMocks(this);
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateCreation = dateFormat.parse("30/09/2021");
			Date dateDerniereModification = dateFormat.parse("30/02/2022"); 
		    Produit p= new Produit (2L,"codeP3","carotte",(float) 29.1, dateCreation, dateDerniereModification,null,null,null);
		   produitRepository.save(p);
		   produitService.deleteProduit(p.getIdProduit());
	        Mockito.verify(produitRepository, Mockito.times(1)).deleteById(p.getIdProduit());
	        System.out.println("--------------------------------------- Delete produit test works ------------------------------ ");
	    	}
	
	
	/*   
	   @Test
	    public void updateProduit() throws ParseException {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateCreation = dateFormat.parse("30/09/2021");
			Date dateDerniereModification = dateFormat.parse("30/02/2022"); 
		    Produit p= new Produit (2L,"codeP3","carotte",(float) 29.1, dateCreation, dateDerniereModification,null,null,null);
	        when(produitRepository.save(p)).thenReturn(p);
	        assertNotNull(p);
	        assertEquals(p, produitService.updateProduit(p));

	        System.out.println("---------------------------------------Update TEST :O !-----------------------------");
	    }
	   
	   
	   */
	   /*
	   @Test
	    public void updateProduit2() throws ParseException {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateCreation = dateFormat.parse("30/09/2021");
			Date dateDerniereModification = dateFormat.parse("30/02/2022"); 
		    Produit p= new Produit (2L,"codeP3","carotte",(float) 29.1, dateCreation, dateDerniereModification,null,null,null);
		    
		    Produit savedProduit = produitService.addProduit(p);
		    savedProduit.setLibelleProduit("potato");
		    Produit updateProduit = produitService.updateProduit(savedProduit);
		    
		    
		    when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p));
			
			when(produitRepository.save(any(Produit.class))).thenReturn(p);
		    
		    
		    
		    
	      //  when(produitRepository.save(updateProduit)).thenReturn(updateProduit);
	        
	       assertNotNull(updateProduit);
	        assertEquals(updateProduit.getLibelleProduit(), "potato");

	        System.out.println("---------------------------------------Update TEST :O !-----------------------------");
	    }
	   
	   */
	   
	   
	   
	   @Test
		public void updateProduit() throws ParseException {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateCreation = dateFormat.parse("30/09/2021");
			Date dateDerniereModification = dateFormat.parse("30/02/2022"); 
		    Produit p= new Produit (2L,"codeP3","carotte",(float) 29.1, dateCreation, dateDerniereModification,null,null,null);
		    
			//when(produitRepository.findById(anyLong())).thenReturn(Optional.of(p));
			
			when(produitRepository.save(any(Produit.class))).thenReturn(p);
			
			p.setLibelleProduit("POTATO");
			//ProduitDTO prm=modelMapper.map(p1, ProduitDTO.class);
			Produit exisitingProduit = produitService.updateProduit(p);
			
			assertNotNull(exisitingProduit);
			assertEquals("POTATO", exisitingProduit.getLibelleProduit());
			
			 System.out.println("---------------------------------------Update TEST :O !-----------------------------");
		}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
    /* @Autowired
    IProduitService ps;

    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Produit> listProduits = ps.retrieveAllProduits();
        Assertions.assertEquals(0, listProduits.size());
    }
    */
    
	

}

