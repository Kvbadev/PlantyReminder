package com.example.plantyreminder.domain

//sealed class ErrorEntity() {
//    object Network : ErrorEntity()
//    object AccessDenied : ErrorEntity()
//    object ServiceUnavailable : ErrorEntity()
//    sealed class DatabaseException : ErrorEntity() {
//        object ConstraintException : DatabaseException()
//        object CantOpenException: DatabaseException()
//    }
//    object Unknown : ErrorEntity()
//}
//
sealed interface ErrorEntity {
    fun message(): String

    enum class Default(private val message: String) : ErrorEntity {
        Network("Network Error"),
        AccessDenied("Access Denied"),
        ServiceUnavailable("Service was unavailable"),
        Unknown("Error");

        override fun message() = this.message
    }

    enum class Database(private val message: String) : ErrorEntity {
        ConstraintException("Value was not unique"),
        CantOpenException("Could not open the database");

        override fun message() = this.message
    }
}
