package org.gerdoc.dao.impl;

import org.gerdoc.dao.LoginDao;
import org.gerdoc.model.Login;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LoginDaoImpl implements LoginDao
{
    private Session session;

    public LoginDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<Login> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Login> criteria =
                builder.createQuery(Login.class);
        criteria.from(Login.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public Login findById(Long id)
    {
        return session.get( Login.class, id );
    }

    @Override
    public Login save(Login login)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( login );
            transaction.commit( );
            return login;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }

    @Override
    public void deleteById(Long id)
    {
        Transaction transaction = null;
        Login login = null;
        try
        {
            login = findById( id );
            if( login != null )
            {
                transaction = session.beginTransaction();
                session.delete( login );
                transaction.commit( );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
        }
    }

    @Override
    public Login update(Login login)
    {
        Transaction transaction = null;
        Login updatedLogin = null;
        try
        {
            transaction = session.beginTransaction();
            updatedLogin = (Login) session.merge( login );
            transaction.commit( );
            return updatedLogin;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            transaction.rollback( );
            return null;
        }
    }
}
