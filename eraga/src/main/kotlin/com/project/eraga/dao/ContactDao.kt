package com.project.eraga.dao

import com.project.eraga.entity.Contact

interface ContactDao {
    fun findContact(id: Int): Contact?
    fun saveContact(contact: Contact?)
    fun updateContact(contact: Contact?)
    fun deleteContact(contact: Contact?)
    fun findContact(name: String?): Contact?
}