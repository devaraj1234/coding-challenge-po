package com.shopapi.revature.dao;

import com.shopapi.revature.model.OfferedMade;

public interface OfferMadeDAO extends GenericDAO<OfferedMade> {
	
	public boolean acceptOffer(OfferedMade offer);
	public boolean rejectOffer(int offer_no);

}
