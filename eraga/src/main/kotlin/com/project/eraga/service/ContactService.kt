package com.project.eraga.service

import com.project.eraga.dao.ContactDaoImpl
import com.project.eraga.entity.Area
import com.project.eraga.entity.Contact
import com.project.eraga.entity.Region

class ContactService(
        private var contactDaoImpl: ContactDaoImpl? = ContactDaoImpl()
) {
    fun findContact(id: Int): Contact? {
        return contactDaoImpl!!.findContact(id)
    }

    fun findContact(name: String?): Contact? {
        return contactDaoImpl!!.findContact(name)
    }

    fun saveContact(contact: Contact?) {
        contactDaoImpl!!.saveContact(contact)
    }

    fun updateContact(contact: Contact?) {
        if (contact != null) {
            contactDaoImpl!!.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact?) {
        contactDaoImpl!!.deleteContact(contact)
    }

    fun setRegion(contact: Contact) {
        val regionService = RegionService()
        val areaService = AreaService()
        val contactService = ContactService()
        var region: Region? = regionService.findRegion(contact.getTmpRegion())
        if (region != null) {
            println("Exist")
        } else {
            region = Region(contact.getTmpRegion().toString())
            regionService.saveRegion(region)
            println("No")
        }

        var area = contact.getArea()
        if (area == null) {
            contact.setRegion(region)
            region.addContact(contact)
            contactService.updateContact(contact)
        } else {
            area.setRegion(region)
            region.addArea(area)
            areaService.updateArea(area)
        }
        regionService.updateRegion(region)
    }

    fun setArea(contact: Contact) {
        var area: Area? = contact.getArea()
        if (area != null) {
            area.deleteContact(contact)
        }
        if (contact.getTmpArea() == null) {
            return
        }
        val areaService = AreaService()
        val contactService = ContactService()
        area = areaService.findArea(contact.getTmpArea())
        if (area != null) {
            println("Exist")
        } else {
            area = Area(contact.getTmpArea()!!)
            areaService.saveArea(area)
            println("No")
        }
        contact.setArea(area)
        area.addContact(contact)
        areaService.updateArea(area)
        contactService.updateContact(contact)
    }
}