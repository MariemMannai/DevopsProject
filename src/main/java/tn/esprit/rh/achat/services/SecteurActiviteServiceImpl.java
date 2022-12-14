package tn.esprit.rh.achat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.achat.model.SecteurConverter;
import tn.esprit.achat.model.SecteurModel;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.List;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService{
	
	SecteurConverter customerConverter;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	@Override
	public List<SecteurActivite> retrieveAllSecteurActivite() {
		return (List<SecteurActivite>) secteurActiviteRepository.findAll();
	}

	@Override
	public SecteurActivite addSecteurActivite(SecteurActivite sa) {
		secteurActiviteRepository.save(sa);
		return sa;
	}

	@Override
	public void deleteSecteurActivite(Long id) {
		secteurActiviteRepository.deleteById(id);
		
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActivite sa) {
		
		return secteurActiviteRepository.save(sa);
	}

	@Override
	public SecteurActivite retrieveSecteurActivite(Long id) {
		
		return secteurActiviteRepository.findById(id).orElse(null);
	}

	@Override
	public SecteurModel saveSecteur(SecteurModel secteurModel) {
		SecteurActivite customer = customerConverter.convertDtoToEntity(secteurModel);
        customer = secteurActiviteRepository.save(customer);
        return customerConverter.convertEntityToDto(customer);
		
	}
	

}
