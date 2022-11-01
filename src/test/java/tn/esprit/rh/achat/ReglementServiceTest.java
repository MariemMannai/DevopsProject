package tn.esprit.rh.achat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IFactureService;
@Slf4j

@SpringBootTest()
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class FactureServiceImplTest {
@Autowired
IFactureService ifact;
@Mock
FactureRepository us;
@InjectMocks
FactureServiceImpl fact;

@Test
@Order(1)
 void testRetrieveAllFacture() {
List<Facture> listUsers = ifact.retrieveAllFactures();
 log.info("Liste des factures ********** "+ifact.retrieveAllFactures());
Assertions.assertEquals(0, listUsers.size());
}
Facture fac = new Facture(1L,200,200,new Date(),new Date(),true,null , null,null);
List<Facture> listUsers = new ArrayList<Facture>() {
{
add(new Facture(2L,400,100,new Date(),new Date(),true,null , null,null));
add(new Facture(3L,300,100,new Date(),new Date(),false,null , null,null));
}
};
@Test
 void testaddFacture() {
Mockito.when(us.findById(Mockito.anyLong())).thenReturn(Optional.of(fac));
Facture f = fact.retrieveFacture(3L);
Assertions.assertNotNull(f);
}
}