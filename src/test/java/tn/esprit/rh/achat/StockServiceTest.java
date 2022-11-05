package tn.esprit.rh.achat;

import java.util.ArrayList;
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

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
  class StockServiceTest {
	@Mock
	  StockRepository sr = Mockito.mock(StockRepository.class);
	
	  @InjectMocks
	  IStockService ss = new StockServiceImpl();
	  
	    Stock stock1 = new Stock("stock1", 100, 1);
	    Stock stock2 = new Stock("stock2", 200, 2);
	    
	   List<Stock> listStocks = new ArrayList<Stock>() {
	        {
	            add(new Stock("Fruits", 80,10));
	            add(new Stock("Légumes", 90,20));
	        }
	    };
	   
	    @Test
	     void addStockTest()  {
	    	//MockitoAnnotations.initMocks(this);
	    	stock2.setIdStock(23L);
	        ss.addStock(stock2);
	        Mockito.verify(sr, Mockito.times(1)).save(stock2);
	        System.out.println(stock2);
	        System.out.println("++++++++addStockTest !+++++++");
	    }
	    
	    @Test
	    void testRetrieveStockByid() {
	    	Mockito.when(sr.findById(Mockito.anyLong())).thenReturn(Optional.of(stock1));
	        Stock stockq = ss.retrieveStock(1L);
	        System.out.println(stockq);
	        Assertions.assertNotNull(stockq);
	        System.out.println("++++++++testRetrieveStockByid !+++++++");
	    }
	    
	    @Test
	     void testRetrieveAllStocks() {
	    	//MockitoAnnotations.initMocks(this);
	        List<Stock> stocks = new ArrayList();
	        stocks.add(new Stock());
	        Mockito.when(sr.findAll()).thenReturn(stocks);
	        List<Stock> expected = ss.retrieveAllStocks();
	        Assertions.assertEquals(expected, stocks);
	        Mockito.verify(sr).findAll();
	        System.out.println("++++++++testRetrieveAllStocks !+++++++");
	    }
	      
		    @Test
		     void DeleteStockTest() {
		    	//MockitoAnnotations.initMocks(this);
		        sr.save(stock1);
		        ss.deleteStock(stock1.getIdStock());
		        Mockito.verify(sr, Mockito.times(1)).deleteById(stock1.getIdStock());
		        System.out.println("++++++++DeleteStockTest!+++++++");
		    	}
}

