package hu.ulyssys.java.course.jpa.cdi;


import hu.ulyssys.java.course.jpa.cdi.dao.AuthorDAO;
import hu.ulyssys.java.course.jpa.cdi.dao.BlogPostDAO;
import hu.ulyssys.java.course.jpa.cdi.entity.Author;
import hu.ulyssys.java.course.jpa.cdi.entity.BlogPost;
import hu.ulyssys.java.course.jpa.cdi.entity.Category;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BlogPostDAO blogPostDAO;

    public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        Main main = cdi.select(Main.class).get();
        main.main(Arrays.asList(args));
    }
    protected void main(List<String> args) {
        Author author = new Author();
        author.setFirstName("testFirstName");
        author.setLastName("testLastName");
        author.setUserName("testUsername");
        author.setCreatedDate(new Date());
        author.setLastModifiedDate(new Date());
        authorDAO.save(author);
        authorDAO.findAll().forEach(author1 -> {
            System.out.println(author1.getId() + " " + author1.getFirstName() + " " +author1.getLastName());
        });

        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Test title");
        blogPost.setCategory(Category.SQL);
        blogPost.setContent("test content");
        blogPost.setCreatedDate(new Date(System.currentTimeMillis()));
        blogPost.setLastModifiedDate(new Date());
        blogPost.setPublishedDate(new Date());
        blogPostDAO.save(blogPost);
        blogPostDAO.findAll().forEach(blogPost1 -> {
            System.out.println(blogPost1.getId() + " " + blogPost1.getTitle() + " " +blogPost1.getCategory());
        });
    }
}
