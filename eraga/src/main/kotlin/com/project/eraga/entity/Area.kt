package com.project.eraga.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "area")
class Area(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        private var id: Int = 0,

        @Column(name = "name")
        var name: String? = null,

        @OneToMany(mappedBy = "area")
        var contacts: MutableList<Contact>? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "region_id")
        @JsonIgnore
        private var region: Region? = null

) {

        constructor(name: String) : this() {
                this.name = name
        }
        fun getRegion(): Region? {
                return region
        }

        fun setRegion(region: Region?) {
                this.region = region
        }

        fun addContact(contact: Contact) {
                contact.setArea(this)
                contacts?.add(contact)
        }

        fun deleteContact(contact: Contact) {
                contact.setArea(null)
                contacts?.remove(contact)
        }
        
        override fun toString(): String {
                return "Area{" +
                        " name='" + name + '\'' +
                        ", contacts=" + contacts +
                        '}'
        }
}
//CREATE TABLE "area"
//        (
//        id SERIAL PRIMARY KEY,
//        name VARCHAR(20) NOT NULL,
//        region_id int,
//        FOREIGN KEY (region_id) REFERENCES region (id)
//        );