package erp.service;

import java.util.ArrayList;
import java.util.List;

import erp.dao.WareDao;
import erp.model.Ware;
import erp.model.WareCategory;

public class WareService {

    private WareDao wareDao;

    public WareService() {
    }

    public Ware getWareById(int id) {
        return wareDao.read(id);
    }

    public int createWare(Ware obj) {
        return wareDao.create(obj);
    }

    public boolean deleteWare(int id) {
        boolean ret = false;
        Ware obj = wareDao.read(id);
        if (null != obj) {
            wareDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateWare(Ware obj) {
        wareDao.update(obj);
        return true;
    }

    public void check(Ware obj, boolean flag, int chkNumber, double chkCost,
            double fee) {
        int oldNumber;
        int newNumber;
        double newCost;
        if (flag) {
            oldNumber = (obj.getNumber() < 0) ? 0 : obj.getNumber();
            newNumber = obj.getNumber() + chkNumber;
            newCost = (obj.getCost() * oldNumber + (chkCost + fee) * chkNumber)
                    / (oldNumber + chkNumber);
        } else {
            oldNumber = obj.getNumber();
            newNumber = oldNumber - chkNumber;
            if (newNumber < 0) {
                newCost = obj.getCost();
            } else {
                newCost = (obj.getCost() * oldNumber - (chkCost + fee)
                        * chkNumber)
                        / newNumber;
            }
        }
        obj.setCost(newCost);
        obj.setNumber(newNumber);
        wareDao.update(obj);
    }

    public int getCount(int status) {
        return wareDao.count(status);
    }

    public List<Ware> list(int status, int index, int num) {
        return wareDao.list(status, index, num);
    }

    public List<Ware> listLimited(int status) {
        return wareDao.listLimited(status);
    }

    public List<Ware> listByCategory(WareCategory category, int status) {
        return wareDao.listByCategoryId(category.getId(), status);
    }

    public List<Ware> findByBarcode(String barcode) {
        return wareDao.findByBarcode(barcode);
    }

    public List<Ware> findByKeywords(String keywords) {
        List<String> keywordList = new ArrayList<String>();
        String[] list = keywords.split(" ");
        for (int i = 0; i < list.length; i++) {
            keywordList.add(list[i]);
        }
        return wareDao.findByKeywords(keywordList);
    }

    public List<Ware> fullTextSearch(String content) {
        return wareDao.fullTextSearch(content);
    }

    public void setWareDao(WareDao dao) {
        wareDao = dao;
    }

}
