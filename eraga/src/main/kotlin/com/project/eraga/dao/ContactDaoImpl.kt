package com.project.eraga.dao

import com.project.eraga.entity.Contact
import com.project.eraga.util.getSessionFactory
import org.hibernate.criterion.Restrictions

class ContactDaoImpl : ContactDao {
    override fun findContact(id: Int): Contact? {
        val session = getSessionFactory()?.openSession()
        println("id $id")
        val contact: Contact? = session?.get(Contact::class.java, id)
        session?.close()
        println("Contact = $contact")
        return contact
    }

    override fun saveContact(contact: Contact?) {
        val session = getSessionFactory()?.openSession()
        val transaction = session?.beginTransaction()
        session?.save(contact)
        transaction?.commit()
        session?.close()
    }

    override fun updateContact(contact: Contact?) {
        val session = getSessionFactory()?.openSession()

        val tx1 = session?.beginTransaction()
        session?.update(contact)
        tx1?.commit()
        session?.close()
    }

    override fun deleteContact(contact: Contact?) {
        val session = getSessionFactory()?.openSession()
        val tx = session?.beginTransaction()
        session?.delete(contact)
        tx?.commit()
        session?.close()
    }

    override fun findContact(name: String?): Contact? {
        val session = getSessionFactory()?.openSession()
        val ContactCriteria = session?.createCriteria(Contact::class.java)
        ContactCriteria?.add(Restrictions.eq("name", name))
        val contact = ContactCriteria?.uniqueResult() as Contact?
        session?.close()
        return contact
    }
}