package com.project.eraga.dao

import com.project.eraga.entity.Region
import com.project.eraga.util.getSessionFactory
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.Restrictions
import javax.persistence.criteria.CriteriaQuery

class RegionDaoImpl: RegionDao {

    override fun saveRegion(region: Region?) {
        val session = getSessionFactory()?.openSession()
        val transaction = session?.beginTransaction()
        session?.save(region)
        transaction?.commit()
        session?.close()
    }

    override fun updateRegion(region: Region?) {
        val session = getSessionFactory()?.openSession()
        val tx1 = session?.beginTransaction()
        session?.update(region)
        tx1?.commit()
        session?.close()
    }

    override fun findRegion(name: String?): Region? {
        val session = getSessionFactory()?.openSession()
        val regionCriteria = session?.createCriteria(Region::class.java)
        regionCriteria?.add(Restrictions.eq("name", name))
        println("region")
        return if (regionCriteria?.uniqueResult() == null) {
            session?.close()
            null
        } else {
            var region: Region = regionCriteria?.uniqueResult() as Region
            session?.close()
            region
        }
    }

    override fun getAllRegions(): List<Region?>? {
        val session = getSessionFactory()?.openSession()
        val builder = session?.criteriaBuilder
        val criteria = builder?.createQuery(Region::class.java)
        criteria?.from(Region::class.java)
        val data = session?.createQuery(criteria)?.getResultList()
        session?.close()
        return data
    }
}