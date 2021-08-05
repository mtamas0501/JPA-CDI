package hu.ulyssys.java.course.jpa.cdi.dao;

import hu.ulyssys.java.course.jpa.cdi.entity.BlogPost;

import java.util.List;

public interface BlogPostDAO {

    void save(BlogPost blogPost);

    void update(BlogPost blogPost);

    List<BlogPost> findAll();

    List<BlogPost> findByTitle(String title);

}
