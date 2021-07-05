package com.project.eraga.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "contact")
class Contact (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        private var id: Int = 0,

        @Column(name = "name", nullable = false)
        var name: String? = null,

        @Column(name = "photo", nullable = true)
        var photo: String? = null,

        @Column(name = "comment")
        var comment: String? = null,

        @Column(name = "region")
        @JsonIgnore
        private var tmpRegion: String? = null,

        @Column(name = "area")
        @JsonIgnore
        private var tmpArea: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "region_id")
        @JsonIgnore
        private var region: Region? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "area_id")
        @JsonIgnore
        private var area: Area? = null,

        @OneToMany(
        mappedBy = "contact",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER)
        var infos: MutableList<Info>? = null

) {
        constructor(name: String, tmpRegion: String, tmpArea: String?, photo: String?, comment: String?) : this() {
                this.name = name
                this.photo = photo
                this.comment = comment
                this.tmpRegion = tmpRegion
                this.tmpArea = tmpArea
        }

        open fun getTmpRegion(): String? {
                return tmpRegion
        }

        fun setRegion(region: Region?) {
                this.region = region
        }

        fun getTmpArea(): String? {
                return tmpArea
        }

        fun getArea(): Area? {
                return area
        }

        fun setArea(area: Area?) {
                this.area = area
        }

        fun setTmpArea(area: String) {
                this.tmpArea = area
        }

        fun setTmpRegion(region: String) {
                this.tmpRegion = region
        }

        fun addInfo(info: Info) {
                infos?.add(info)
        }

        override fun toString(): String {
        return "Contact{" +
                " name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", comment='" + comment + '\'' +
                ", infos=" + infos +
                '}'
        }
}

//    CREATE TABLE "contact"
//        (
//        id SERIAL PRIMARY KEY,
//        name VARCHAR(20) NOT NULL,
//        region VARCHAR(30) NOT NULL,
//        area VARCHAR(30),
//        photo VARCHAR(255),
//        comment VARCHAR(255),
//        region_id int,
//        area_id int,
//        FOREIGN KEY (region_id) REFERENCES region (id),
//        FOREIGN KEY (area_id) REFERENCES area (id)
//
//        );
