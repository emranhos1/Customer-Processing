package com.eh.cp.helper;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
/**
 * This class for getting session
 *
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
*/
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * This method return SessionFactory
     *
     * @author MD. EMRAN HOSSAIN
     * @return sessionFactory - SessionFactory
     * @since 24 MAR, 2023
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    /**
     * This method return jwt token
     *
     * @author MD. EMRAN HOSSAIN
     * @param request - HttpServletRequest
     * @return data - String
     * @since 24 MAR, 2023
     */
    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
