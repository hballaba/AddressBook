package com.project.eraga.util

import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

import com.project.eraga.entity.Area
import com.project.eraga.entity.Info
import com.project.eraga.entity.Region
import com.project.eraga.entity.Contact

    fun  getSessionFactory(): SessionFactory? {

        var sessionFactory: SessionFactory
            try {
                val configuration = Configuration().configure()
                configuration.addAnnotatedClass(Contact::class.java)
                configuration.addAnnotatedClass(Region::class.java)
                configuration.addAnnotatedClass(Area::class.java)
                configuration.addAnnotatedClass(Info::class.java)
                val builder = StandardServiceRegistryBuilder().applySettings(configuration.properties)
                sessionFactory = configuration.buildSessionFactory(builder.build())
                return sessionFactory
            } catch (e: Exception) {
                println("Exception!!! $e")
            }
        return null
    }
