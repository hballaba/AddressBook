package com.project.eraga.controller

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.node.ObjectNode
import com.project.eraga.entity.Contact
import com.project.eraga.entity.Info
import com.project.eraga.entity.Region
import com.project.eraga.service.ContactService
import com.project.eraga.service.RegionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/"])
class ContactController {

    @GetMapping("contactId/{id}")
    @Operation(summary = "получение контакт по id контакта")
    fun findContactId(@PathVariable id: Int): ResponseEntity<Contact?>? {
        val contactService = ContactService()
        val contact: Contact? = contactService.findContact(id)
        return if (contact != null) {
            ResponseEntity.ok<Contact?>(contact)
        } else {
            ResponseEntity.notFound().build<Contact?>()
        }
    }

    @GetMapping("contactName/{name}")
    @JsonSerialize
    @Operation(summary = "получение контакта по имени контакта")
    fun findContactName(@PathVariable name: String?): ResponseEntity<Contact> {
        val contactService = ContactService()
        val contact: Contact? = contactService.findContact(name)
        return if (contact != null) {
            ResponseEntity.ok(contact)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("addContact")
    @Operation(summary = "добавление контакта")
    fun addContact(@RequestBody j: ObjectNode): ResponseEntity<Contact?>? {
        if (j["name"].textValue() == null) {
            return ResponseEntity.badRequest().header("Failure", "The name cannot be null").body(null)
        }
        if (j["region"].textValue() == null) {
            return ResponseEntity.badRequest().header("Failure", "The region cannot be null").body(null)
        }
        var contactService = ContactService()
        var contact: Contact? = contactService.findContact(j["name"].textValue())
        println("contact findName = " + contact)
        if (contact == null) {
            contact = Contact(j["name"].textValue(), j["region"].textValue(), j["area"].textValue(), j["photo"].textValue(), j["comment"].textValue())
            contactService.saveContact(contact)
            contactService.setArea(contact)
            contactService.setRegion(contact)
            return ResponseEntity.ok<Contact?>(contact)
        }
        return ResponseEntity.badRequest().header("Failure", "Contact is already exist").body<Contact?>(contact)
    }

    @PostMapping("addInfo")
    @Operation(summary = "добавление дополнительной информации контакту")
    fun addInfo(@RequestBody j: ObjectNode): ResponseEntity<Contact?>? {
        if (j["phone"].textValue() == null || j["email"].textValue() == null) {
            return ResponseEntity.badRequest().header("Failure", "The email and the phone cannot be null").body(null)
        }
        val contactService = ContactService()
        val contact: Contact? = contactService.findContact(j["contactName"].textValue())
        return if (contact != null) {
            val info = Info(j["phone"].textValue(), j["email"].textValue())
            info.setContact(contact)
            contact.addInfo(info)
            contactService.updateContact(contact)
            ResponseEntity.ok<Contact?>(contact)
        } else {
            ResponseEntity.notFound().build<Contact?>()
        }
    }

    @DeleteMapping("deleteContact/{name}")
    @Operation(summary = "удаление контакта")
    fun deleteContact(@PathVariable name: String?) {
        val contactService = ContactService()
        val contact: Contact? = contactService.findContact(name)
        if (contact != null) {
            contactService.deleteContact(contact)
        }
    }

    @PutMapping("updateContact/{name}")
    @Operation(summary = "обновление информации у контакта")
    fun updateContact(@PathVariable name: String?, @RequestBody j: ObjectNode): ResponseEntity<Contact?>? {
        val contactService = ContactService()
        val contact: Contact? = contactService.findContact(name)
        return if (contact != null) {
            if (contact.getArea() == null) {
                val regionService = RegionService()
                var region: Region? = regionService.findRegion(contact.getTmpRegion())
                region?.contacts?.remove(contact)
            }
            contact.name = j["name"].textValue()
            contact.photo= j["photo"].textValue()
            contact.setTmpRegion(j["region"].textValue())
            contact.setTmpArea(j["area"].textValue())
            contact.comment = j["comment"].textValue()
            contactService.setArea(contact)
            contactService.setRegion(contact)
            contactService.updateContact(contact)
            ResponseEntity.ok<Contact?>(contact)
        } else {
            ResponseEntity.notFound().build<Contact?>()
        }
    }
}