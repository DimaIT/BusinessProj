package business

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BusinessController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Business.list(params), model:[businessCount: Business.count()]
    }

    def show(Business business) {
        respond business
    }

    def create() {
        respond new Business(params)
    }

    def edit(Business business) {
        respond business
    }

    @Transactional
    def save(Business business) {
        if (business == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (business.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond business.errors, view:'create'
            return
        }

        business.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'business.label', default: 'Business'), business.id])
                redirect business
            }
            '*' { respond business, [status: CREATED] }
        }
    }

    @Transactional
    def update(Business business) {
        if (business == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (business.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond business.errors, view:'edit'
            return
        }

        business.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'business.label', default: 'Business'), business.id])
                redirect business
            }
            '*'{ respond business, [status: OK] }
        }
    }

    @Transactional
    def delete(Business business) {

        if (business == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        business.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'business.label', default: 'Business'), business.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'business.label', default: 'Business'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
