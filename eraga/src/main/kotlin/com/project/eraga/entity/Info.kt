package com.project.eraga.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "info")
data class Info(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        private var id: Int = 0,

        @Column(name = "phone")
        var phone: String? = null,

        @Column(name = "email")
        var email: String? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "contact_id")
        @JsonIgnore
        private var contact: Contact? = null

) {
        constructor(phone: String, email: String) : this() {
                this.phone = phone
                this.email = email
        }

        fun setContact(contact: Contact){
                this.contact = contact
        }

        override fun toString(): String {
                return "Info{" +
                        " phone='" + phone + '\'' +
                        ", email='" + email + '\'' +
                        '}'
        }
}
//CREATE TABLE info
//(
//id SERIAL PRIMARY KEY,
//contact_id SERIAL,
//email VARCHAR(50),
//phone VARCHAR(20),
//FOREIGN KEY (contact_id) REFERENCES "contact" (id)
//);