package com.km.database.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.km.database.entity.Person;

@Repository
@Transactional
public class PersonRepo {

	// connect to data-base
	@PersistenceContext
	private EntityManager em;

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = em.createNamedQuery("find_all_query", Person.class);
		return namedQuery.getResultList();
	}

	public Person findById(int id) {
		return em.find(Person.class, id);
	}

	public Person insert(Person person) {
		return em.merge(person);
	}

	public Person update(Person person) {
		return em.merge(person);
	}

	public void deleteById(int id) {
		Person person = findById(id);
		em.remove(person);
	}
}
