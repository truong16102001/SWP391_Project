package dao.inter;

import java.util.List;
import model.Blog;
import model.User;

/**
 *
 * @author ThinkPro
 */
public interface IBlogDAO {

    int getTotalBlog(String searchKey, String cb_idStr, String status, String author);

    List<Blog> getBlogWithPaging(int page, int PAGE_SIZE, String searchKey, String cb_idStr, String type, String value, String status, String author);

    List<User> getAllAuthor();

    Blog getBlogNew();

    void addNewBlog(String title, int user_id, String content, String brief_infor, int category_id, boolean status, String url_thumbnail);

    void updateStatusBlog(int blog_id, boolean status);

    Blog getBlogByBlogId(int blog_id);

    void updateBlog(int blog_id, String title, int user_id, String content, String brief_infor, int category_id, boolean status, String url_Thumbnail);

     List<Blog> getBLogByCategory(int cb_id);

}
