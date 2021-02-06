package com.surya.spring.service;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surya.spring.dao.IdGeneratorDao;

/**
 * @author Surya
 *
 */


@Service
public class IdGeneratorService {

	@Autowired
	IdGeneratorDao idGeneratorDao;

	
	public int increaseNumberForID() {
		
		int result = 0;
		try {
		result=	idGeneratorDao.doIncrementWithLock();
		System.out.println("data saved....");
		}catch (StaleObjectStateException e) {
			System.out.println("************** race condition occured, try again for perform this. *******************");
			/*try {
				Thread.sleep(1000);
				increaseNumberForID();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} */
			
		}catch (Exception e) {
			throw e;
		}
		
		return result;
		
	}
	
	
}
