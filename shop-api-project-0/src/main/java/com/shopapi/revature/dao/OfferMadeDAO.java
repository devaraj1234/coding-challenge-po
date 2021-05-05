package com.shopapi.revature.dao;

import com.shopapi.revature.model.Offeres;

public interface OfferMadeDAO extends GenericDAO<Offeres> {
	
	public boolean acceptOffer(Offeres offer);
	public boolean rejectOffer(int offer_no);

}
