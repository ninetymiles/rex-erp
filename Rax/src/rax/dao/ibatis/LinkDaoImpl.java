package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.LinkDao;
import rax.model.Link;

public class LinkDaoImpl extends SqlMapClientDaoSupport implements LinkDao {

    public Long create(Link newInstance) throws DataAccessException {
        return (Long) getSqlMapClientTemplate().insert("createLink",
                newInstance);
    }

    @Override
    public Link read(Long id) throws DataAccessException {
        return (Link) getSqlMapClientTemplate().queryForObject("readLink", id);
    }

    @Override
    public int update(Link transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateLink", transientObject);
    }

    @Override
    public int delete(Link persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteLink",
                persistentObject.getId());
    }

    public int deleteByCategoryId(Long id) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteLinkByCategoryId", id);
    }

    @Override
    public long count() throws DataAccessException {
        return count(false);
    }

    public long count(boolean bOnlyPub) throws DataAccessException {
        return (Long) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubLink" : "countAllLink");
    }

    public long countByCategoryId(Long id) throws DataAccessException {
        return countByCategoryId(id, false);
    }

    public long countByCategoryId(Long id, boolean bOnlyPub)
            throws DataAccessException {

        return (Long) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubLinkByCategoryId"
                        : "countAllLinkByCategoryId");
    }

    @Override
    public List<Link> list(int index, int num) throws DataAccessException {
        return list(index, num, false);
    }

    public List<Link> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("num", num);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubLink" : "listLink", param);
    }

    public List<Link> listByCategoryId(Long id, int index, int num)
            throws DataAccessException {
        return listByCategoryId(id, index, num, false);
    }

    public List<Link> listByCategoryId(Long id, int index, int num,
            boolean bOnlyPub) throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("num", num);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubLinkByCategoryId" : "listLinkByCategoryId",
                param);
    }

    @Override
    public List<Link> listAll() throws DataAccessException {
        return listAll(false);
    }

    public List<Link> listAll(boolean bOnlyPub) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubLink" : "listAllLink");
    }

    public List<Link> listAllByCategoryId(long id) throws DataAccessException {
        return listAllByCategoryId(id, false);
    }

    public List<Link> listAllByCategoryId(long id, boolean bOnlyPub)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubLinkByCategoryId"
                        : "listAllLinkByCategoryId");
    }

}
