package tn.esprit.rh.achat;

 
	import org.junit.Test;

	import static org.junit.Assert.*;
	import static org.mockito.Mockito.any;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;

	
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.List;

	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IReglementService;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

	@ContextConfiguration(classes = {ReglementServiceImpl.class})
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class ReglementServiceTest {
	    @MockBean
	    private ReglementRepository rgrepo;

	  
	    @Autowired
	     ReglementServiceImpl reglementServiceImpl;
	    
	    
	    @MockBean
	    private  FactureServiceImpl factureServiceImpl;
	@MockBean
	private FactureRepository facturerepo;

	@Autowired
	IReglementService regServ;

	    @Test
	   public void   testRetrive() {
	            ArrayList<Reglement> reglementList = new ArrayList<>();
	            when(rgrepo.findAll()).thenReturn(reglementList);
	            List<Reglement> actualRetrieveAllRegResult = reglementServiceImpl.retrieveAllReglements();
	            assertSame(reglementList, actualRetrieveAllRegResult);
	            assertTrue(actualRetrieveAllRegResult.isEmpty());
	            verify(rgrepo).findAll();
	        }
	    
	    @Test
	    
	    public void testAddReg() throws ParseException {
	        Reglement reg = new Reglement();
	        reg.setDateReglement(new SimpleDateFormat( "yyyyMMdd" ).parse( "20220520" ));
	        reg.setFacture(null);
	        reg.setIdReglement(1L);
	        reg.setMontantPaye((float) 15.500);
	        reg.setMontantRestant((float) 0.750);
	        reg.setPayee(false);
	        assertSame(reg, reglementServiceImpl.addReglement(reg));
	        verify(rgrepo).save((Reglement) any());
	    }
	    
	    
	   
	    
	   
	}


