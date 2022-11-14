package tn.esprit.rh.achat.services;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import java.util.Date;









@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

public class FactureServiceTest {

@Mock
FactureRepository factureRepository;

@InjectMocks
FactureServiceImpl factureService;

@Test
public void getFactures()
{
List<Facture> list = new ArrayList<Facture>() {

{
add(new Facture(1L,200,200,new Date(),new Date(),true,null , null,null));



}};


when(factureService.retrieveAllFactures()).thenReturn(list);
//test
List<Facture> List = factureService.retrieveAllFactures();
assertEquals(1,List.size());
}


@Test
public void createFactureTest()
{

Facture facture = new Facture(2L,300,300,new Date(),new Date(),true,null , null,null);
facture.setIdFacture(2L);

factureService.addFacture(facture);
verify(factureRepository, times(1)).save(facture);


}



}



