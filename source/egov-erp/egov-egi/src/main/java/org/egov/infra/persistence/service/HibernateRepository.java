package org.egov.infra.persistence.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.infra.persistence.entity.AbstractPersistable;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public abstract class HibernateRepository<T extends AbstractPersistable <? extends Serializable>> {

    @PersistenceContext
    protected EntityManager entityManager;
    
    protected Class<T> entityType;

    protected HibernateRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    public void save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        getCurrentSession().flush();
    }

    public void evict(T entity) {
        getCurrentSession().evict(entity);
    }

    public void create(T entity) {
        getCurrentSession().save(entity);
        getCurrentSession().flush();
    }

    public T merge(T entity) {
        T mergedEntity = (T) getCurrentSession().merge(entity);
        getCurrentSession().flush();
        return mergedEntity;
    }

    public T get(Long id) {
        return (T) getCurrentSession().get(entityType, id);
    }
    
    public T load(Long id) {
        return (T) getCurrentSession().load(entityType, id);
    }
    
    public Criteria createCriteria(Class<T> clazz) {
        return getCurrentSession().createCriteria(clazz);
    }

    public T findByField(String field, String value) {
        Criteria criteria = createCriteria(entityType);
        return (T) criteria.add(Restrictions.eq(field, value)).uniqueResult();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery(String.format("from %s", entityType.getSimpleName())).list();
    }
    
    public List<T> findAllLike(String fieldName, String value) {
        return createCriteria(entityType).add(Restrictions.ilike(fieldName, value, MatchMode.ANYWHERE)).list();
    }
    
    public Query query(String queryName){
        return getCurrentSession().getNamedQuery(queryName);
    }

    protected void flushAndClear() {
        Session currentSession = getCurrentSession();
        currentSession .flush();
        currentSession .clear();
    }

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }
}