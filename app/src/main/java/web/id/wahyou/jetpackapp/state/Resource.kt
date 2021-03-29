package web.id.wahyou.jetpackapp.state

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
 
        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)
 
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }
}