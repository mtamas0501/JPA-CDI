package hu.ulyssys.java.course.jpa.cdi.dao.Impl;

import hu.ulyssys.java.course.jpa.cdi.dao.BlogPostDAO;
import hu.ulyssys.java.course.jpa.cdi.entity.BlogPost;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class BlogPostDAOImpl implements BlogPostDAO {

    private static final String PERSISNTENCE_UNIT = "TestPersistence";

    private EntityManager createEntityManager(){
        return Persistence.createEntityManagerFactory(PERSISNTENCE_UNIT).createEntityManager();
    }

    @Override
    public void save(BlogPost blogPost){
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(blogPost);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(BlogPost blogPost){
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(blogPost);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<BlogPost> findAll(){
        TypedQuery<BlogPost> typedQuery = createEntityManager().createQuery("select n from BlogPost n", BlogPost.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<BlogPost> findByTitle(String title) {
        TypedQuery<BlogPost> query = createEntityManager().createQuery("select n from BlogPost n where n.title=:title", BlogPost.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
}
