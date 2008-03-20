package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.*;

public class ArticleContentDaoImpl extends SqlMapClientDaoSupport implements
        ArticleContentDao {

    @Override
    public int create(String newInstance) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().insert("createArticleContent",
                newInstance);
    }

    @Override
    public String read(int id) throws DataAccessException {
        // TODO
        // Import multiple line into one
        return (String) getSqlMapClientTemplate().queryForObject(
                "readArticleContent", id);
    }

    @Override
    public int update(String transientObject) throws DataAccessException {
        // TODO
        return getSqlMapClientTemplate().update("updateArticleContent",
                transientObject);
    }

    @Override
    public int delete(String persistentObject) throws DataAccessException {
        // TODO
        return getSqlMapClientTemplate().delete("deleteArticleContent",
                persistentObject);
    }

    @Override
    public int deleteByCategoryId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().delete(
                "deleteArticleContentByCategoryId", id);
    }

    @Override
    public int countByCategoryId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countArticleContentByCategoryId", id);
    }

    @Override
    public List<String> listByCategoryId(int id, int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("id", id);
        return getSqlMapClientTemplate().queryForList(
                "listArticleContentByCategoryId", param);
    }

    @Override
    public List<String> listAllByCategoryId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listAllArticleContentByCategoryId", id);
    }

}