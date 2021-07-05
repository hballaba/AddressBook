package com.project.eraga.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import lombok.Data
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Data
@Entity
@Table(name = "region")
data class Region(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore
        private var id: Int = 0,

        @JsonView
        @Column(name = "name")
        var name: String? = null,

        @JsonView
        @OneToMany(mappedBy = "region")
        var areas: MutableList<Area>? = null,

        // mappet указывает на поле region  в классе area
        @JsonView
        @OneToMany(mappedBy = "region")
        var contacts: MutableList<Contact>? = null
)
{

        constructor(name: String) : this() {
                this.name = name
        }

        fun addContact(contact: Contact) {
                contact.setRegion(this)
                contacts?.add(contact)
        }

        fun addArea(area: Area) {
                area.setRegion(this)
                areas?.add(area)
        }

        override fun toString(): String {
                return "Region{" +
                        " name='" + name + '\'' +
                        ", areas=" + areas +
                        ", contacts=" + contacts +
                        '}'
        }
}


//    CREATE TABLE "region"
//        (
//        id SERIAL PRIMARY KEY,
//        name VARCHAR(20) NOT NULL
//        )