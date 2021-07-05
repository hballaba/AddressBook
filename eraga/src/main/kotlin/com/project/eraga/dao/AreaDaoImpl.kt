package com.project.eraga.dao

import com.project.eraga.entity.Area
import com.project.eraga.entity.Region
import com.project.eraga.util.getSessionFactory

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.Restrictions

class AreaDaoImpl : AreaDao {
    override fun saveArea(area: Area?) {
        val session = getSessionFactory()?.openSession()
        val transaction = session?.beginTransaction()
        session?.save(area)
        transaction?.commit()
        session?.close()
    }

    override fun updateArea(area: Area?) {
        val session = getSessionFactory()?.openSession()
        val tx1 = session?.beginTransaction()
        session?.update(area)
        tx1?.commit()
        session?.close()
    }

    override fun findArea(name: String?): Area? {
        val session = getSessionFactory()?.openSession()
        val areaCriteria = session?.createCriteria(Area::class.java)
        areaCriteria?.add(Restrictions.eq("name", name))
        return if (areaCriteria?.uniqueResult() == null) {
            session?.close()
            null
        } else {
            var area: Area = areaCriteria?.uniqueResult() as Area
            session?.close()
            area
        }
    }

    override fun changeArea(name: String?) {}
}