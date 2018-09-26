package com.capgemini.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dto.Products;

@Repository
@EnableTransactionManagement
public class DAOLayerImplMerchant implements DAOLayerMerchant {
	

	@PersistenceContext
	private EntityManager em;
	@Override
	@Transactional
	public boolean addProduct(Products product) {
		
		
			em.persist(product);
			return true;
		
	}
}
