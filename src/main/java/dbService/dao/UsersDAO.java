package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }


    public String getUserPassword(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getPassword();
    }

    
    public UsersDataSet readByName(String name) {
        Criteria criteria = session.createCriteria(UsersDataSet.class);

        return (UsersDataSet) criteria
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    public long insertUser(String name, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name, password));
    }

    public long insertUser(String name, String password, String sessionId) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name, password, sessionId));
    }

    public List<UsersDataSet> getAll(){
        Criteria criteria = session.createCriteria(UsersDataSet.class);

        return (List<UsersDataSet>) criteria.add(Restrictions.isNotNull("name")).list();

    }

    public void deleteUser(String name) throws  HibernateException {
       /*
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet user = (UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        */
        session.delete("name", name);
    }
}
